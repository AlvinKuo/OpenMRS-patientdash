package org.openmrs.module.dicomecg.servlet;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.dicomecg.DicomEcg;
import org.openmrs.module.dicomecg.api.DicomEcgService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



public class DicomUpload extends HttpServlet {
	
	protected final Log log = LogFactory.getLog(getClass());
	boolean flag  = true;
	private String patiendId;
	private String patientName;
	private String nurseId;
	private String nurseName;
	private String filename; 
	private String measureTime;
	private String uploadTime;
		
	/*
	 * doGet and doPost used processRequest 
	 * 
	 */
	@RequestMapping(value = "/module/dicomecg/manage", method = RequestMethod.POST)
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/*		
		patiendId = request.getParameter("patient_id");
		patientName = request.getParameter("patient_name");
		nurseId = request.getParameter("nurse_id");
		nurseName = request.getParameter("nurse_name");
		filename = request.getParameter("filename");
		measureTime = request.getParameter("measure_time");
		
		uploadTime = df.format(date);		
		
		
		DicomEcgService UploadEcgService = Context.getService(DicomEcgService.class);
		
	try {
			
			DicomEcg UploadEcgData = new DicomEcg();
			UploadEcgData.setPatiendId(patiendId);
			UploadEcgData.setPatientName(patientName);
			UploadEcgData.setNurseId(nurseId);
			UploadEcgData.setNurseName(nurseName);
			UploadEcgData.setFilename(filename);
			UploadEcgData.setMeasureTime(measureTime);
			UploadEcgData.setUploadTime(uploadTime);
			
			DicomEcg decg = UploadEcgService.saveDicomEcg(UploadEcgData);
			flag = true;
			
		}catch(Exception e)
		{}*/
		
		if(flag == true)
		{			
			out.print("Y");
			flag=true;
		}
		else
		{
			out.print("N");
		}
		
	}	
	
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
