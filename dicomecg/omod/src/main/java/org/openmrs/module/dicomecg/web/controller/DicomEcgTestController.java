package org.openmrs.module.dicomecg.web.controller;

import org.openmrs.api.context.Context;
import org.openmrs.module.dicomecg.DicomMail;
import org.openmrs.module.dicomecg.api.DicomEcgService;


public class DicomEcgTestController {
	
	DicomEcgService mial = Context.getService(DicomEcgService.class);
	DicomMail mail = mial.sendMail("cyculab501@gmail.com" ,"cyculab501@gmail.com", "testteste","123456");
	

}
