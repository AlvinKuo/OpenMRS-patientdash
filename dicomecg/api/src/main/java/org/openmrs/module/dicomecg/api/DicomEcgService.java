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
package org.openmrs.module.dicomecg.api;

import java.util.List;


import org.openmrs.PatientIdentifier;
import org.openmrs.PersonAddress;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.dicomecg.DicomEcg;
import org.openmrs.module.dicomecg.DicomEcgAttribute;
import org.openmrs.module.dicomecg.DicomEcgConfirm;
import org.openmrs.module.dicomecg.DicomEcgWave;
import org.springframework.transaction.annotation.Transactional;

/**
 * This service exposes module's core functionality. It is a Spring managed bean which is configured in moduleApplicationContext.xml.
 * <p>
 * It can be accessed only via Context:<br>
 * <code>
 * Context.getService(DicomEcgService.class).someMethod();
 * </code>
 * 
 * @see org.openmrs.api.context.Context
 */
@Transactional
public interface DicomEcgService extends OpenmrsService {

	@Transactional(readOnly = true)
	public List<DicomEcg> getAllDicomEcg();

	public DicomEcg saveDicomEcg(DicomEcg dicomEcg);

	public DicomEcg getDicomEcg(Integer id);

	public List<DicomEcg> getfilename(String filename);
	
	public List<PatientIdentifier> getPatientID(String identifier);	
	
	public List<DicomEcg> mapPatientEcgData(Integer id);

	public DicomEcgAttribute saveDicomEcgAttribute(DicomEcgAttribute attribute);
	
	public List<DicomEcgAttribute> getDicomEcgAttribute(String filename);
	
	public List<DicomEcgConfirm> getDicomEcgConfirm();
	
	public boolean checkAttribute(String filename);
	
	public DicomEcgWave saveDicomWave(DicomEcgWave wave);
	
	public List<DicomEcgWave> getDicomEcgWave(String filename);
	
	public boolean checkWave(String filename);
	
	public DicomEcg getDicomWaveId(Integer id);

	public List<PersonAddress> getPersonMail(Integer patiendId);

	public DicomEcgConfirm saveDicomEcgConfirm(DicomEcgConfirm uploadConfirm);

	public DicomEcgConfirm getDicomConfirmId(Integer confirmId);

	public List<DicomEcgConfirm> getConfirm(String filename);

	

	
}