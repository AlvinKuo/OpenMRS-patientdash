package org.openmrs.module.dicomecg.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.dicomecg.DicomEcgAttribute;
import org.openmrs.module.dicomecg.api.DicomEcgService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@SuppressWarnings("serial")
public class AttributeTest extends HttpServlet{
	
	protected final Log log = LogFactory.getLog(getClass());
	private Integer id;
	private Integer patiendId;	
	private String 	gender;
	private String 	height;
	private String 	weight;
	private String 	birthdate;
	boolean attributeCheck;
	private String 	fileName;
	
	private String ecgPath;
	private int data_length;
	
	@RequestMapping(value = "/module/dicomecg/manage", method = RequestMethod.POST)
    private void processRequest(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException{
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		fileName = request.getParameter("filename");     	
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
        	data_length = (birth[0] + birth[1]<<8);
        	out.print(data_length);
        	for(int i=0;i<data_length;i++){
        		birthdate = Integer.toString(f.readUnsignedByte());
        		out.print(birthdate);
        	}
			f.close();		
    	} catch(Exception e){
    		e.getMessage();
    	}
		
		//readPatientInformation(fileName);  //A12345678920130528160544.dcm
		
		
		//--test  write data into ecg_attribute
/*		patiendId =  Integer.parseInt(request.getParameter("patiendId"));		
		gender = request.getParameter("gender");
		height = request.getParameter("height");
		weight = request.getParameter("weight");
		birthdate = request.getParameter("birthdate");
		
		DicomEcgService serviceAttribute = Context.getService(DicomEcgService.class);
		DicomEcgAttribute saveAttribute = new DicomEcgAttribute();
		saveAttribute.setId(id);
		saveAttribute.setPatiendId(patiendId);
		saveAttribute.setGender(gender);
		saveAttribute.setHeight(height);
		saveAttribute.setWeight(weight);
		saveAttribute.setBirthdate(birthdate);
		serviceAttribute.saveDicomEcgAttribute(saveAttribute);		
*/
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
    
    private void readPatientInformation(String fileName) throws IOException{
    	
    }

}
