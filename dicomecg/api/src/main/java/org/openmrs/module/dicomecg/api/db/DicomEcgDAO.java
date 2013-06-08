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
package org.openmrs.module.dicomecg.api.db;

import java.util.List;


import org.openmrs.PatientIdentifier;
import org.openmrs.module.dicomecg.DicomEcg;
import org.openmrs.module.dicomecg.api.DicomEcgService;

/**
 *  Database methods for {@link DicomEcgService}.
 */
public interface DicomEcgDAO {

	/*
	 * Get all data form ecg table
	 * @return  all DicomEcg
	 */
	public List<DicomEcg> getAllDicomEcg();
	
	/*
	 * Save one DicomEcg object to database
	 *  @return saved DicomEcg object
	 */
	public DicomEcg saveDicomEcg(DicomEcg dicomEcg);

	
	/*
	 *  Get one DicomEcg record based on the id
	 */
	public DicomEcg getDicomEcg(Integer id);

	public List<DicomEcg> getfilename(String filename);

	public List<PatientIdentifier> getPatientID(String identifier);
}