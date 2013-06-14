package org.openmrs.module.dicomecg.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
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
import org.openmrs.module.dicomecg.api.DicomEcgService;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@SuppressWarnings("serial")
public class DicomUpload extends HttpServlet {
	
	protected final Log log = LogFactory.getLog(getClass());
	boolean flag  = false;
	private Integer patientId;
	private String identifier;
	private String patientName;
	private String nurseId;
	private String nurseName;
	private String filename; 
	private String measureTime;
	private String uploadTime;
	boolean check;	
	
	/*
	 * doGet and doPost used processRequest 
	 * 
	 * when android device transmit the dicom file by wi-fi 
	 * this page will add the information such as patientId、patientName、nurseId、nurseName、filename and measureTime
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
		
		check = identifierCheckSum(identifier);
		if(check==true)
		{
			DicomEcgService UploadEcgService = Context.getService(DicomEcgService.class);
			List<PatientIdentifier> PId = UploadEcgService.getPatientID(identifier);
			Iterator<PatientIdentifier> res= PId.iterator();
			if(res.hasNext()){
				patientId = PId.get(0).getPatient().getPatientId();
				try{
					DicomEcg UploadEcgData = new DicomEcg();
					UploadEcgData.setPatientId(patientId);
					UploadEcgData.setIdentifier(identifier);
					UploadEcgData.setPatientName(patientName);
					UploadEcgData.setNurseId(nurseId);
					UploadEcgData.setNurseName(nurseName);
					UploadEcgData.setFilename(filename);
					UploadEcgData.setMeasureTime(measureTime);
					UploadEcgData.setUploadTime(uploadTime);
					UploadEcgData.setConfirm("NEW");
					UploadEcgService.saveDicomEcg(UploadEcgData);				
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
