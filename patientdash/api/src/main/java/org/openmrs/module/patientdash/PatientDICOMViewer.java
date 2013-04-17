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
package org.openmrs.module.patientdash;

import java.io.Serializable;
import org.openmrs.BaseOpenmrsObject;
import org.openmrs.BaseOpenmrsMetadata;

/**
 * It is a model class. It should extend either {@link BaseOpenmrsObject} or {@link BaseOpenmrsMetadata}.
 */
public class PatientDICOMViewer extends BaseOpenmrsObject implements Serializable {

	
	/*	
	private Integer id;
	
	@Override
	public Integer getId() {
		return id;
	}
	
	@Override
	public void setId(Integer id) {
		this.id = id;
	}*/
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String patiendId;
	private String patientName;
	private String nurseId;
	private String nurseName;
	private String filename;
	private String measureTime;
	private String uploadTime;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setPatiendId(String patiendId) {
		this.patiendId = patiendId;
	}

	public String getPatiendId() {
		return patiendId;
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