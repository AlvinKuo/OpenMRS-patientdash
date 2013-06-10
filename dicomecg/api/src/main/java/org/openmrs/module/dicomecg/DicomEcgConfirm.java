package org.openmrs.module.dicomecg;

import java.io.Serializable;

import org.openmrs.BaseOpenmrsObject;

public class DicomEcgConfirm extends BaseOpenmrsObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer ecg_confirm_id;
	private Integer patiendId;
	private String 	identifier;
	private String 	confirm;
	private String 	confirm_time;
	private String 	confirm_name;
	private String 	comment;
	private String 	filename;

	
	
	public DicomEcgConfirm(){
		
	}
	public void setEcgConfirmId(Integer ecg_confirm_id) {
		this.ecg_confirm_id = ecg_confirm_id;
	}
	
	public Integer getEcgConfirmId() {
		return ecg_confirm_id;
	}
	
	@Override
	public void setId(Integer id) {
		// TODO Auto-generated method stub
		setEcgConfirmId(id);
	}
	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return getEcgConfirmId();
	}
	
	public void setPatiendId(Integer patiendId) {
		this.patiendId = patiendId;
	}

	public Integer getPatiendId() {
		return patiendId;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getIdentifier() {
		return identifier;
	}
	
	
	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public String getConfirm() {
		return confirm;
	}
	
	public void setConfirmName(String confirm_name) {
		this.confirm_name = confirm_name;
	}

	public String getConfirmName() {
		return confirm_name;
	}
	
	public void setConfirmTime(String confirm_time) {
		this.confirm_time = confirm_time;
	}

	public String getConfirmTime() {
		return confirm_time;
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
