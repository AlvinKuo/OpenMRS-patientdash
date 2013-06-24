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
import java.io.PrintWriter;
import java.io.RandomAccessFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.openmrs.module.dicomecg.extension.html.SoAndChen;
import org.openmrs.util.OpenmrsUtil;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class HeartRateCount extends HttpServlet{
	

	private static final long serialVersionUID = 1L;
	
    private String ecgPath;
	private String filename;
	private short[][] ecg_data;
	private int ecg_data_length;
	private float grid;
	private int[] tmp = new int[1];
	private Integer HR; 
	
	
	private SoAndChen sac = new SoAndChen();
	
   public void init() throws ServletException {
        this.ecgPath = OpenmrsUtil.getApplicationDataDirectory() + "/patient_dicom";
    }  
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	PrintWriter out = response.getWriter();
    	response.setContentType("text/html");
    	
    	filename = request.getParameter("filename");    	
    	setFileName(filename);
    	
		for (int i=0;i<ecg_data_length-1;i++) {
			tmp[0]=ecg_data[1][i];
			sac.setData(tmp[0]);
			//out.print(tmp[0]);
		}
		HR = sac.getHeartrate();
		out.print(HR);
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
	}

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
	
}
