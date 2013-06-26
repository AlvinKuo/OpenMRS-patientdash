package org.openmrs.module.dicomecg.servlet;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.PatientIdentifier;
import org.openmrs.api.context.Context;
import org.openmrs.module.dicomecg.DicomEcg;
import org.openmrs.module.dicomecg.DicomEcgAttribute;
import org.openmrs.module.dicomecg.DicomEcgWave;
import org.openmrs.module.dicomecg.api.DicomEcgService;
import org.openmrs.module.dicomecg.extension.html.SoAndChen;
import org.openmrs.util.OpenmrsUtil;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@SuppressWarnings("serial")
public class DicomUpload extends HttpServlet {
	
	protected final Log log = LogFactory.getLog(getClass());
	boolean flag  = false;
	private Integer patiendId;
	private String identifier;
	private String patientName;
	private String nurseId;
	private String nurseName;
	private String filename; 
	private String measureTime;
	private String uploadTime;
	boolean checkIdentifier;
	
	private String 	gender;
	private String 	height;
	private String 	weight;
	private String 	birthdate;
	
	private String ecgPath;
	private int data_length;	
	private int Ascii;
	String AsciiToString;
	
	
	private short[][] ecg_data;
	private int ecg_data_length;
	private int[] tmp = new int[1];
	private Integer HR;
	private Integer rr;
	private float r5;
	private String heartRate;
	private String RRintervel;
	private SoAndChen sac = new SoAndChen();
	
	private boolean waveexist;
	
	
	
	
	//initial path
	public void init() throws ServletException {
		this.ecgPath = OpenmrsUtil.getApplicationDataDirectory() + "/patient_dicom";
		}
	
	
	/*
	 * doGet and doPost used processRequest 
	 * 
	 * when android device transmit the dicom file by wi-fi 
	 * this page will add the information such as patiendId、patientName、nurseId、nurseName、filename and measureTime
	 * into MySQL database
	 */
	
	@RequestMapping(value = "/module/dicomecg/manage", method = RequestMethod.POST)
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		//------------update date-------------------
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		

		identifier = request.getParameter("patient_id");
		patientName = request.getParameter("patient_name");
		nurseId = request.getParameter("nurse_id");
		nurseName = request.getParameter("nurse_name");
		filename = request.getParameter("filename");
		measureTime = request.getParameter("measure_time");
		uploadTime = df.format(date);
		
		checkIdentifier = identifierCheckSum(identifier);
		if(checkIdentifier==true)
		{

			DicomEcgService UploadEcgService = Context.getService(DicomEcgService.class);
			List<PatientIdentifier> PId = UploadEcgService.getPatientID(identifier);
			Iterator<PatientIdentifier> res= PId.iterator();
			if(res.hasNext()){
				patiendId = PId.get(0).getPatient().getPatientId();
				try{
					
					//--save ecg table
					DicomEcg UploadEcgData = new DicomEcg();
					UploadEcgData.setPatiendId(patiendId);
					UploadEcgData.setIdentifier(identifier);
					UploadEcgData.setPatientName(patientName);
					UploadEcgData.setNurseId(nurseId);
					UploadEcgData.setNurseName(nurseName);
					UploadEcgData.setFilename(filename);
					UploadEcgData.setMeasureTime(measureTime);
					UploadEcgData.setUploadTime(uploadTime);
					UploadEcgData.setConfirm("NEW");
					UploadEcgService.saveDicomEcg(UploadEcgData);				
					
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					
					//--save ecg attribute
					DicomEcgService uploadAttributeService = Context.getService(DicomEcgService.class);
					DicomEcgAttribute uploadAttribute = new DicomEcgAttribute();
					
					birthdate = readPatientBirthdate(filename);
					gender = readPatientSex(filename);
					height = readPatientHeight(filename);
					weight = readPatientWeight(filename);
					
					uploadAttribute.setPatiendId(patiendId);
					uploadAttribute.setGender(gender);
					uploadAttribute.setHeight(height + "m");
					uploadAttribute.setWeight(weight + "kg");
					uploadAttribute.setBirthdate(birthdate);
					uploadAttribute.setFilename(filename);
					uploadAttributeService.saveDicomEcgAttribute(uploadAttribute);
					
					//----save ecg heart rate
					setFileName(filename);
					for (int i=0;i<ecg_data_length-1;i++) {
						tmp[0]=ecg_data[1][i];
						sac.setData(tmp[0]);
					}
					
					HR = sac.getHeartrate();
					heartRate = Integer.toString(HR);
					
					r5 = sac.getR5();
					NumberFormat nf = NumberFormat.getInstance();		
					nf.setMaximumFractionDigits(1);		
					RRintervel = nf.format(r5);
			
					DicomEcgService uploadWaveService = Context.getService(DicomEcgService.class);
					DicomEcgWave uploadwave = new DicomEcgWave();
					uploadwave.setPatientId(patiendId);
					uploadwave.setFilename(filename);
					uploadwave.setComment(RRintervel + "ms");
					uploadwave.setHeartrate(heartRate);
					uploadWaveService.saveDicomWave(uploadwave);
					
					flag = true;
					}catch(Exception e){
						out.print(e.getMessage());
					}
			}
			
			if(flag == true){
				out.print("Y");
				
				
				flag=false;
			}
			else{
				out.print("N");
			}
				
			
		}
	}
	
	/*
	 *  Check identifier valid return true
	 *  
	 * */
    
    protected boolean identifierCheckSum(String id){
    	int[] num=new int[10];
    	int[] rdd={10,11,12,13,14,15,16,17,34,18,19,20,21,22,35,23,24,25,26,27,28,29,32,30,31,33};
    	id=id.toUpperCase();
    	
    	if(id.charAt(0)<'A'||id.charAt(0)>'Z'){    		
    		return false;
    		}
    	if(id.charAt(1)!='1' && id.charAt(1)!='2'){
    		return false;
    		}
    	
    	for(int i=1;i<10;i++){
    		   if(id.charAt(i)<'0'||id.charAt(i)>'9'){
    			   return false;
    		   }
    	 }
    	 
    	 for(int i=1;i<10;i++){
    		   num[i]=(id.charAt(i)-'0');
    		  }
    	 num[0]=rdd[id.charAt(0)-'A'];
    	 int sum=((int)num[0]/10+(num[0]%10)*9);
    	 for(int i=0;i<8;i++){
    		 sum+=num[i+1]*(8-i);
    	 }
    	 if(10-sum%10==num[9]){
    		 return true;
    	 }
    	 else{
    		 return false;
    	 }
    }
    
 private String readPatientBirthdate(String fileName) throws IOException{
    	
    	// (0010,0030)    	
    	String Birthdate = "";
    	File file = new File(ecgPath, fileName);		
		try{
        	RandomAccessFile f = new RandomAccessFile(file, "r");
        	f.seek(0);
        	short tmp = 0;
        	while (f.getFilePointer() < f.length() - 1)
        	{
        		tmp = f.readShort();
				if(tmp == 0x1000) {
					tmp = f.readShort();
					if(tmp == 0x3000)
						break;
				}
        	}        	
        	f.seek(f.getFilePointer() + 2);
        	int[] birth = new int[2];
        	birth[0] = f.readUnsignedByte();
        	birth[1] = f.readUnsignedByte();
        	data_length = (birth[0] + (birth[1]<<8));
        	AsciiToString="";
        	
        	for(int i=0;i<data_length;i++){        		
        		Ascii = f.readUnsignedByte();
        		AsciiToString += new Character((char)Ascii).toString();
        	}
        	Birthdate = AsciiToString;

			f.close();
			
    	} catch(Exception e){
    		e.getMessage();
    	}
		return Birthdate;
    }
    
    private String readPatientSex(String fileName) throws IOException{
    	
    	//(0010,0040)
    	String Sex = "";
    	File file = new File(ecgPath, fileName);		
		try{
        	RandomAccessFile f = new RandomAccessFile(file, "r");
        	f.seek(0);
        	short tmp = 0;
        	while (f.getFilePointer() < f.length() - 1)
        	{
        		tmp = f.readShort();
				if(tmp == 0x1000) {
					tmp = f.readShort();
					if(tmp == 0x4000)
						break;
				}
        	}        	
        	f.seek(f.getFilePointer() + 2);
        	int[] birth = new int[2];
        	birth[0] = f.readUnsignedByte();
        	birth[1] = f.readUnsignedByte();
        	data_length = (birth[0] + (birth[1]<<8));
        	AsciiToString="";
        	
        	for(int i=0;i<data_length;i++){        		
        		Ascii = f.readUnsignedByte();
        		AsciiToString += new Character((char)Ascii).toString();
        	}
        	Sex = AsciiToString;
			f.close();
			
    	} catch(Exception e){
    		e.getMessage();
    	}
		return Sex;
    	
    }
    
    private String readPatientHeight(String fileName) throws IOException{
    	
    	//(0010,1020)
    	String Height="";
    	File file = new File(ecgPath, fileName);		
		try{
        	RandomAccessFile f = new RandomAccessFile(file, "r");
        	f.seek(0);
        	short tmp = 0;
        	while (f.getFilePointer() < f.length() - 1)
        	{
        		tmp = f.readShort();
				if(tmp == 0x1000) {
					tmp = f.readShort();
					if(tmp == 0x2010)
						break;
				}
        	}        	
        	f.seek(f.getFilePointer() + 2);
        	int[] birth = new int[2];
        	birth[0] = f.readUnsignedByte();
        	birth[1] = f.readUnsignedByte();
        	data_length = (birth[0] + (birth[1]<<8));
        	AsciiToString="";
        	
        	for(int i=0;i<data_length;i++){        		
        		Ascii = f.readUnsignedByte();
        		AsciiToString += new Character((char)Ascii).toString();
        	}
        	Height = AsciiToString;
			f.close();
			
    	} catch(Exception e){
    		e.getMessage();
    	}
    	return Height;    	
    }
    
    private String readPatientWeight(String fileName) throws IOException{
    	
    	//(0010,1030)
    	String Weight="";
    	File file = new File(ecgPath, fileName);		
		try{
        	RandomAccessFile f = new RandomAccessFile(file, "r");
        	f.seek(0);
        	short tmp = 0;
        	while (f.getFilePointer() < f.length() - 1)
        	{
        		tmp = f.readShort();
				if(tmp == 0x1000) {
					tmp = f.readShort();
					if(tmp == 0x3010)
						break;
				}
        	}        	
        	f.seek(f.getFilePointer() + 2);
        	int[] birth = new int[2];
        	birth[0] = f.readUnsignedByte();
        	birth[1] = f.readUnsignedByte();
        	data_length = (birth[0] + (birth[1]<<8));
        	AsciiToString="";
        	
        	for(int i=0;i<data_length;i++){        		
        		Ascii = f.readUnsignedByte();
        		AsciiToString += new Character((char)Ascii).toString();
        	}
        	Weight = AsciiToString;
			f.close();
			
    	} catch(Exception e){
    		e.getMessage();
    	}
    	return Weight;
    }
    
    
public void setFileName(String fileName) {
		
		File file = new File(ecgPath, fileName);
        
        try {
			RandomAccessFile f = new RandomAccessFile(file, "r");
			f.seek(0);
			short tmp = 0;
			
			while (f.getFilePointer() < f.length() - 1) {
				tmp = f.readShort();
				if(tmp == 0x0054) {
					tmp = f.readShort();
					if(tmp == 0x1010)
						break;
				}
			}
			
			f.seek(f.getFilePointer() + 4);
			int[] b = new int[4];
			b[0] = f.readUnsignedByte();
			b[1] = f.readUnsignedByte();
			b[2] = f.readUnsignedByte();
			b[3] = f.readUnsignedByte();
			ecg_data_length = (b[0] + (b[1] << 8) + (b[2] << 16) + (b[3] << 24)) / 24;
			
			ecg_data = new short[12][ecg_data_length];
			for (int j=0;j<ecg_data_length;j++) {
				short[] t = new short[24];
				for (int k=0;k<24;k++) {
					t[k] = (short) f.readUnsignedByte();
				}
				for (int k=0;k<12;k++) {
					short lb = t[k*2];
					short hb = t[k*2 + 1];
					if (t[k*2+1] < 128) {
						ecg_data[k][j] = (short) ((lb + (hb << 8)) / 20.49);
					} else {
						lb = (short) (256 - lb);
						hb = (short) (255 - hb);
						ecg_data[k][j] = (short) ((0 - (lb + (hb << 8))) / 20.49) ;
					}
				}
			}
			
			f.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    
    
    
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }    


}
