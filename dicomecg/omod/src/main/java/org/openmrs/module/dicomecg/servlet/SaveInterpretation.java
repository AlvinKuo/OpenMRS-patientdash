package org.openmrs.module.dicomecg.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openmrs.Person;
import org.openmrs.PersonAddress;
import org.openmrs.api.context.Context;
import org.openmrs.module.dicomecg.DicomEcgConfirm;
import org.openmrs.module.dicomecg.api.DicomEcgService;

@SuppressWarnings("serial")
public class SaveInterpretation extends HttpServlet{
	
	private Integer patiendId;
	private String identifier;
	private String filename;
	private String confirmName;
	private String confirmTime;
	private String comment;
	private String mail;

	private void processRequest(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException{
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		

		
		patiendId =  Integer.parseInt(request.getParameter("patientId"));
		identifier = request.getParameter("identifier");		
		filename = request.getParameter("filename");
		confirmName = request.getParameter("confirmName");
		confirmTime = df.format(date);
		comment =  request.getParameter("comments");
		
		Person a = Context.getPersonService().getPerson(patiendId);
		PersonAddress personMail = a.getPersonAddress();		
		mail = personMail.getAddress1();
		
		
		DicomEcgService saveConfirm = Context.getService(DicomEcgService.class);
		DicomEcgConfirm uploadConfirm = new DicomEcgConfirm();
		uploadConfirm.setPatientId(patiendId);
		uploadConfirm.setIdentifier(identifier);
		uploadConfirm.setFilename(filename);
		uploadConfirm.setConfirmTime(confirmTime);
		uploadConfirm.setConfirmName(confirmName);
		uploadConfirm.setComment(comment);
		uploadConfirm.setMail(mail);
		
		saveConfirm.saveDicomEcgConfirm(uploadConfirm);
		
		response.sendRedirect(request.getContextPath()+ "/patientDashboard.form?patientId=" + patiendId + "&phrase=" +  identifier );
	/*	
		out.println(patiendId);			
		out.println(identifier);
		out.println(filename);
		out.println(confirmName);
		out.println(confirmTime);
		out.println(comment);
		out.println(mail);
		
	*/
		
		
		
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
