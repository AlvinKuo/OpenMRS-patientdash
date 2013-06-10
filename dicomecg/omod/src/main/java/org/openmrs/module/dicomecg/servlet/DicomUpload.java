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
	private Integer patiendId;
	private String identifier;
	private String patientName;
	private String nurseId;
	private String nurseName;
	private String filename; 
	private String measureTime;
	private String uploadTime;
	
	
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
		
		DicomEcgService UploadEcgService = Context.getService(DicomEcgService.class);
		List<PatientIdentifier> PId = UploadEcgService.getPatientID(identifier);
		Iterator<PatientIdentifier> res= PId.iterator();
		
		if(res.hasNext()){
			patiendId = PId.get(0).getPatient().getPatientId();
			
			try{				
				DicomEcg UploadEcgData = new DicomEcg();
				UploadEcgData.setPatiendId(patiendId);
				UploadEcgData.setIdentifier(identifier);
				UploadEcgData.setPatientName(patientName);
				UploadEcgData.setNurseId(nurseId);
				UploadEcgData.setNurseName(nurseName);
				UploadEcgData.setFilename(filename);
				UploadEcgData.setMeasureTime(measureTime);
				UploadEcgData.setUploadTime(uploadTime);								
				UploadEcgService.saveDicomEcg(UploadEcgData);				
				flag = true;
				out.print(patiendId);out.print(identifier);
				out.print(patientName);out.print(nurseId);
				out.print(nurseName);out.print(filename);
				out.print(measureTime);out.print(uploadTime);
				out.print(flag);out.print(UploadEcgData);
				
				}
			catch(Exception e)
				{
					out.print(e.getMessage());
				}
			}
		if(flag == true)
		{
			out.print("Y");	
			out.print(patiendId);			
			flag=false;
		}
		else
		{
			out.print("N");
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
