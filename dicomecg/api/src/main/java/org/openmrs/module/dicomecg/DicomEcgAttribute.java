package org.openmrs.module.dicomecg;

import java.io.Serializable;

import org.openmrs.BaseOpenmrsObject;

public class DicomEcgAttribute extends BaseOpenmrsObject implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer patiendId;	
	private String 	gender;
	private String 	height;
	private String 	weight;
	private String 	birthdate;
	
	
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

	public void setPatiendId(Integer patiendId) {
		this.patiendId = patiendId;
	}

	public Integer getPatiendId() {
		return patiendId;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getGender() {
		return gender;
	}
	
	public void setHeight(String height) {
		this.height = height;
	}

	public String getHeight() {
		return height;
	}
	
	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getWeight() {
		return weight;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getBirthdate() {
		return birthdate;
	}	
}
