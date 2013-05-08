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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class DicomUpload extends HttpServlet {
	
	private static final int height = 2050;
	private static final int width = 2410;
	
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
		
    	//response.setContentType("image/jpeg");
		//createImage(response.getOutputStream());	
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		uploadTime = df.format(date);
		out.print(uploadTime);
		
		
		patiendId = request.getParameter("patiendId");
		patientName = request.getParameter("patientName");
		nurseId = request.getParameter("nurseId");
		nurseName = request.getParameter("nurseName");
		filename = request.getParameter("filename");
		measureTime = request.getParameter("measureTime");
		out.println(patiendId);
		out.print(patiendId);
		out.print(patientName);
		out.print(nurseId);
		out.print(nurseName);
		out.print(filename);
		out.print(measureTime);
		
				
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
/*
	public void drawGrid(Graphics2D g) {

		g.setColor(Color.RED);
		g.setStroke(new BasicStroke(1.0f));
		for (int i = 0; i < 61; i++) {
			g.drawLine(i * 40, 0, i * 40, height - 10);
		}
		for (int i = 0; i < 52; i++) {
			g.drawLine(0, i * 40, width - 10, i * 40);
		}
	}

	private void createImage(OutputStream out) {
		BufferedImage bi = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g = bi.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, // Anti-alias!
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.setBackground(Color.WHITE);
		g.clearRect(0, 0, width, height);
		drawGrid(g);

		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bi);
		param.setQuality(1.0f, false);
		try {
			encoder.encode(bi);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}*/

}
