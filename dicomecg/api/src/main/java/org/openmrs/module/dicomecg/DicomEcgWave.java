package org.openmrs.module.dicomecg;

import java.io.Serializable;

import org.openmrs.BaseOpenmrsObject;

public class DicomEcgWave extends BaseOpenmrsObject implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer patientId;
	private String 	filename;
	private String 	comment;
	private String 	heartrate;
	
	@Override
	public void setId(Integer id) {
		// TODO Auto-generated method stub
		this.id = id;
	}
	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return id;
	}
	
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Integer getPatientId() {
		return patientId;
	}
	
	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilename() {
		return filename;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getComment() {
		return comment;
	}
	
	public void setHeartrate(String heartrate) {
		this.heartrate = heartrate;
	}

	public String getHeartrate() {
		return heartrate;
	}
}
