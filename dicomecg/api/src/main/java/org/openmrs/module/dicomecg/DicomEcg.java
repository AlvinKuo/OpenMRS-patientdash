/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.dicomecg;

import java.io.Serializable;
import org.openmrs.BaseOpenmrsObject;
import org.openmrs.BaseOpenmrsMetadata;

/**
 * It is a model class. It should extend either {@link BaseOpenmrsObject} or {@link BaseOpenmrsMetadata}.
 */
public class DicomEcg extends BaseOpenmrsObject implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer patiendId;
	private String 	identifier;
	private String 	patientName;
	private String 	nurseId;
	private String 	nurseName;
	private String	filename;
	private String	measureTime;
	private String 	uploadTime;

	
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
	
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setNurseId(String nurseId) {
		this.nurseId = nurseId;
	}

	public String getNurseId() {
		return nurseId;
	}

	public void setNurseName(String nurseName) {
		this.nurseName = nurseName;
	}

	public String getNurseName() {
		return nurseName;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilename() {
		return filename;
	}

	public void setMeasureTime(String measureTime) {
		this.measureTime = measureTime;
	}

	public String getMeasureTime() {
		return measureTime;
	}

	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getUploadTime() {
		return uploadTime;
	}
}