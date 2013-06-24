package org.openmrs.module.dicomecg;

import java.io.Serializable;

import org.openmrs.BaseOpenmrsObject;

public class DicomEcgConfirm extends BaseOpenmrsObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer patientId;
	private String 	identifier;
	private String 	confirmTime;
	private String 	confirmName;
	private String 	comment;
	private String 	filename;
	private String 	mail;

	
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

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getIdentifier() {
		return identifier;
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
	
	public void setMail(String mail){
		this.mail=mail;
	}
	public String getMail(){
		return mail;
	}

}
