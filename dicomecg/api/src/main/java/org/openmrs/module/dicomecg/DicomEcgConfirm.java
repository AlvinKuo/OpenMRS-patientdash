package org.openmrs.module.dicomecg;

import java.io.Serializable;

import org.openmrs.BaseOpenmrsObject;

public class DicomEcgConfirm  extends BaseOpenmrsObject implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer patientId;
	private String identify;
	private String confirmTime;
	private String confirmName;
	private String comment;
	private String filename;
	
	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public void setId(Integer id) {
		// TODO Auto-generated method stub
		this.id = id;
	}

	public Integer getPatientId() {
		// TODO Auto-generated method stub
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		// TODO Auto-generated method stub
		this.patientId = patientId;
	}
	
	public void setIdentify(String identify) {
		this.identify = identify;
	}

	public String getIdentify() {
		return identify;
	}
	
	public void setConfirmTime(String confirmTime) {
		this.confirmTime = confirmTime;
	}

	public String getConfirmTime() {
		return confirmTime;
	}
	
	public void setConfirmName(String confirmName) {
		this.confirmName = confirmName;
	}

	public String getConfirmName() {
		return confirmName;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getComment() {
		return comment;
	}
	
	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilename() {
		return filename;
	}
}
