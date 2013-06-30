package org.openmrs.module.dicomecg.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openmrs.Person;
import org.openmrs.PersonAddress;
import org.openmrs.api.context.Context;
import org.openmrs.module.dicomecg.DicomEcg;
import org.openmrs.module.dicomecg.DicomEcgConfirm;
import org.openmrs.module.dicomecg.api.DicomEcgService;
import org.springframework.util.StringUtils;

@SuppressWarnings("serial")
public class SaveInterpretation extends HttpServlet{
	
	private Integer patiendId;
	private String ecgId;
	private String confirmId;
	private String identifier;
	private String filename;
	private String confirmName;
	private String confirmTime;
	private String comment;
	private String mail;
	
	private String patientName;
	private String nurseId;
	private String nurseName;
	private String measureTime;
	private String uploadTime;

	private void processRequest(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException{
		
		//response.setContentType("text/html");
		//PrintWriter out = response.getWriter();
		
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		DicomEcgService saveConfirm = Context.getService(DicomEcgService.class);
		
		//try{
			
			
			confirmId = request.getParameter("confirmId") ;
			
			if(!StringUtils.hasText(confirmId)){
				
				patiendId =  Integer.parseInt(request.getParameter("patientId"));
				identifier = request.getParameter("identifier");		
				filename = request.getParameter("filename");
				confirmName = request.getParameter("confirmName");
				confirmTime = df.format(date);
				comment =  request.getParameter("comments");

				ecgId =  request.getParameter("ecgId");
				patientName =  request.getParameter("patientName");
				nurseId =  request.getParameter("nurseId");
				nurseName =  request.getParameter("nurseName");
				measureTime =  request.getParameter("measureTime");
				uploadTime =  request.getParameter("uploadTime");
				
				//---get address form patient address
				Person a = Context.getPersonService().getPerson(patiendId);
				PersonAddress personMail = a.getPersonAddress();		
				mail = personMail.getAddress1();
				
				//---save new confirm				
				DicomEcgConfirm saveNewConfirm = new DicomEcgConfirm();
				saveNewConfirm.setPatientId(patiendId);
				saveNewConfirm.setIdentifier(identifier);
				saveNewConfirm.setFilename(filename);
				saveNewConfirm.setConfirmTime(confirmTime);
				saveNewConfirm.setConfirmName(confirmName);
				saveNewConfirm.setComment(comment);
				saveNewConfirm.setMail(mail);				
				saveConfirm.saveDicomEcgConfirm(saveNewConfirm);
				
				//---update ecg confirm column
				DicomEcg updateEcgConfirmColumn = new DicomEcg();
				updateEcgConfirmColumn = saveConfirm.getDicomEcg(Integer.valueOf(ecgId));
				updateEcgConfirmColumn.setPatiendId(patiendId);
				updateEcgConfirmColumn.setIdentifier(identifier);
				updateEcgConfirmColumn.setPatientName(patientName);
				updateEcgConfirmColumn.setNurseId(nurseId);
				updateEcgConfirmColumn.setNurseName(nurseName);
				updateEcgConfirmColumn.setFilename(filename);
				updateEcgConfirmColumn.setMeasureTime(measureTime);
				updateEcgConfirmColumn.setUploadTime(uploadTime);
				updateEcgConfirmColumn.setConfirm("Confirm by " + confirmName);
				saveConfirm.saveDicomEcg(updateEcgConfirmColumn);				
				
				response.sendRedirect(request.getContextPath()+ "/patientDashboard.form?patientId=" + patiendId + "&phrase=" +  identifier );
				
			}
			else{
				
				patiendId =  Integer.parseInt(request.getParameter("patientId"));
				identifier = request.getParameter("identifier");		
				filename = request.getParameter("filename");
				confirmName = request.getParameter("confirmName");
				confirmTime = df.format(date);
				comment =  request.getParameter("comments");
				
				//---get address form patient address
				Person a = Context.getPersonService().getPerson(patiendId);
				PersonAddress personMail = a.getPersonAddress();		
				mail = personMail.getAddress1();
				
				//---update new confirm instead of old confirm 
				DicomEcgConfirm updateOldConfirm = new DicomEcgConfirm();				
				//---echo the old information and then update all column from request
				updateOldConfirm = saveConfirm.getDicomConfirmId(Integer.valueOf(confirmId));				
				updateOldConfirm.setPatientId(patiendId);
				updateOldConfirm.setIdentifier(identifier);
				updateOldConfirm.setFilename(filename);
				updateOldConfirm.setConfirmTime(confirmTime);
				updateOldConfirm.setConfirmName(confirmName);
				updateOldConfirm.setComment(comment);
				updateOldConfirm.setMail(mail);				
				saveConfirm.saveDicomEcgConfirm(updateOldConfirm);
				response.sendRedirect(request.getContextPath()+ "/patientDashboard.form?patientId=" + patiendId + "&phrase=" +  identifier );
			}
		
		
	}
	
	

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
}
