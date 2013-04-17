package org.openmrs.module.patientdash.api;

import org.openmrs.api.OpenmrsService;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface PatientDICOMViewerService extends OpenmrsService{

	
	@Transactional
	void initializeTables();

}
