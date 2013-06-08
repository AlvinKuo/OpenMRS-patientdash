package org.openmrs.module.dicomecg.servlet;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.openmrs.api.context.Context;
import org.openmrs.module.dicomecg.DicomEcg;
import org.openmrs.module.dicomecg.api.DicomEcgService;
import org.openmrs.util.OpenmrsUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
 

@SuppressWarnings("restriction")
public class ViewEcg extends HttpServlet{
	
    // Properties ---------------------------------------------------------------------------------
	//private static final String ecgPath = OpenmrsUtil.getApplicationDataDirectory() + "/patient_dicom";
	private String ecgPath;
	private static final int height = 2050;
	private static final int width = 2410;
	
	
	private Integer patiendId;
	private String patientName;
	private String nurseId;
	private String nurseName;
	private String filename;
	private String measureTime;
	
	
	private static final long serialVersionUID = 1L;
	
	private short[][] ecg_data;
	private int ecg_data_length;
	private float grid;
	
   public void init() throws ServletException {
        this.ecgPath = OpenmrsUtil.getApplicationDataDirectory() + "/patient_dicom";
    }
    
    @RequestMapping(value = "/module/dicomecg/manage", method = RequestMethod.POST)
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
/*    	response.setContentType("text/html");
    	PrintWriter out = response.getWriter();   
    	out.print(NurseName);					*/ 	
    	
    	filename = request.getParameter("filename");   	
    	
    	
    	try{
    		
    		DicomEcgService ecgservice = Context.getService(DicomEcgService.class);
        	List<DicomEcg> Filename= ecgservice.getfilename(filename);
        	Iterator<DicomEcg> res= Filename.iterator();
        	if(res.hasNext())
        	{
        		nurseName=Filename.get(0).getNurseName(); 
        		patiendId=Filename.get(0).getPatiendId();
        		patientName=Filename.get(0).getPatientName();
        		measureTime=Filename.get(0).getMeasureTime();
        	}
        	else{
        		
        	}
    		
    	}catch(Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	response.setContentType("image/jpeg");
    	setFileName(filename);    	
		createImage(response.getOutputStream());
	
    }
    
  
	
	//--讀取檔案 解析DICOM ECG
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
			
			grid = 2000 / (float)(ecg_data_length); 
			
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
		
		
		//plot_thread.setRunning(true);
		//plot_thread.start();
	}

    
	private void createImage(OutputStream out) {
		
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		Graphics2D g = bi.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, // Anti-alias!
		        RenderingHints.VALUE_ANTIALIAS_ON);
		g.setBackground(Color.WHITE);
		g.clearRect(0, 0, width, height);
		
		drawGrid(g);
		draw(g);
		g.dispose();
		bi.flush();
		
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bi);
		param.setQuality(1.0f, false);
		try{
			encoder.encode(bi);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		}
	
	//--畫出格線圖
	public void drawGrid(Graphics2D g) {
		g.setColor(Color.RED);
		g.setStroke(new BasicStroke(1.0f));
		for (int i=0; i<61;i++) {			
			g.drawLine(i*40, 0, i*40, height - 10);
		}
		for(int i=0;i<52;i++) {
			g.drawLine(0, i*40, width - 10, i*40);
		}		
	}
	
	//--畫波形圖 law data 
	public void draw(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.setStroke(new BasicStroke(1.0f));
		for (int i=0;i<ecg_data_length-1;i++) {
			g.drawLine((int)(200 + i*grid), 200 - ecg_data[0][i], (int)(200 + (i+1)*grid), 200 - ecg_data[0][i+1]);
			g.drawLine((int)(200 + i*grid), 360 - ecg_data[1][i], (int)(200 + (i+1)*grid), 360 - ecg_data[1][i+1]);
			g.drawLine((int)(200 + i*grid), 520 - ecg_data[2][i], (int)(200 + (i+1)*grid), 520 - ecg_data[2][i+1]);
			g.drawLine((int)(200 + i*grid), 680 - ecg_data[3][i], (int)(200 + (i+1)*grid), 680 - ecg_data[3][i+1]);
			g.drawLine((int)(200 + i*grid), 840 - ecg_data[4][i], (int)(200 + (i+1)*grid), 840 - ecg_data[4][i+1]);
			g.drawLine((int)(200 + i*grid), 1000 - ecg_data[5][i], (int)(200 + (i+1)*grid), 1000 - ecg_data[5][i+1]);
			g.drawLine((int)(200 + i*grid), 1160 - ecg_data[6][i], (int)(200 + (i+1)*grid), 1160 - ecg_data[6][i+1]);
			g.drawLine((int)(200 + i*grid), 1320 - ecg_data[7][i], (int)(200 + (i+1)*grid), 1320 - ecg_data[7][i+1]);
			g.drawLine((int)(200 + i*grid), 1480 - ecg_data[8][i], (int)(200 + (i+1)*grid), 1480 - ecg_data[8][i+1]);
			g.drawLine((int)(200 + i*grid), 1640 - ecg_data[9][i], (int)(200 + (i+1)*grid), 1640 - ecg_data[9][i+1]);
			g.drawLine((int)(200 + i*grid), 1800 - ecg_data[10][i], (int)(200 + (i+1)*grid), 1800 - ecg_data[10][i+1]);
			g.drawLine((int)(200 + i*grid), 1960 - ecg_data[11][i], (int)(200 + (i+1)*grid), 1960 - ecg_data[11][i+1]);
		}
		g.setFont(new Font("Serif", Font.BOLD, 28));
		g.drawString("I", 160, 200);
		g.drawString("II", 160, 360);
		g.drawString("III", 160, 520);
		g.drawString("aVR", 160, 680);
		g.drawString("aVL", 160, 840);
		g.drawString("aVF", 160, 1000);
		g.drawString("V1", 160, 1160);
		g.drawString("V2", 160, 1320);
		g.drawString("V3", 160, 1480);
		g.drawString("V4", 160, 1640);
		g.drawString("V5", 160, 1800);
		g.drawString("V6", 160, 1960);
		
		g.setFont(new Font("Tahoma", Font.BOLD, 36));
		g.drawString("Patient Id       :  " + patiendId, 160, 40);
		g.drawString("Patient Name :  " + patientName, 160, 80);
		g.drawString("Nurse              :  " + nurseName, 800, 40);
		g.drawString("Measure Time :  " + measureTime, 800, 80);
	}   
	
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
