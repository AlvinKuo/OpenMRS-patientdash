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
package org.openmrs.module.dicomecg.web.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.dicomecg.DicomEcg;
import org.openmrs.module.dicomecg.DicomEcgAttribute;
import org.openmrs.module.dicomecg.api.DicomEcgService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * The main controller.
 */
@Controller
public class  DicomEcgManageController {
	
	protected final Log log = LogFactory.getLog(getClass());
	boolean attributeCheck;
	
/*	@RequestMapping(value = "/module/dicomecg/manage", method = RequestMethod.GET)
	public void manage(ModelMap model) {
		model.addAttribute("user", Context.getAuthenticatedUser());
	}*/
	
	@RequestMapping(value = "/module/dicomecg/manage", method=RequestMethod.GET)	
	public void preparePage(ModelMap map){
		
		DicomEcgService ecgservice = Context.getService(DicomEcgService.class);
		List<DicomEcg> dicomecg = ecgservice.getAllDicomEcg();
		map.addAttribute("dicomecg",dicomecg);
		
		DicomEcgService checkService = Context.getService(DicomEcgService.class);
		attributeCheck = checkService.checkAttribute(4);
		if(attributeCheck == true){
			List<DicomEcgAttribute> showAttribute = checkService.getDicomEcgAttribute(4);
			map.addAttribute("attribute",showAttribute);
		}
		
		
	}
		
	
	@RequestMapping(value = "/module/dicomecg/manage", method = RequestMethod.POST)
	public void processForm(ModelMap map,@RequestParam(required = false, value = "id") String ID1,
			@RequestParam(required = false, value = "patientId") Integer patientId,
			@RequestParam(required = false, value = "patientName") String patientName,
			@RequestParam(required = false, value = "nurseId") String nurseId,
			@RequestParam(required = false, value = "nurseName") String nurseName,
			@RequestParam(required = false, value = "filename") String filename,
			@RequestParam(required = false, value = "measureTime") String measureTime,
			@RequestParam(required = false, value = "uploadTime") String uploadTime,
			@RequestParam(required = false, value = "attributeId") String attributeId,
			@RequestParam(required = false, value = "burthdate") String burthdate,
			@RequestParam(required = false, value = "gender") String gender,
			@RequestParam(required = false, value = "height") String height,
			@RequestParam(required = false, value = "weight") String weight){
		
		DicomEcgService ecgservice = Context.getService(DicomEcgService.class);
		
		if(!StringUtils.hasText(ID1)){
			log.info("Processing post request ..." + ID1 + ", " + patientId + ", " + patientName + ", " + nurseId + ", " + nurseName + ", " + filename 
					+ ", " +measureTime+ ", " + uploadTime);
			DicomEcg dicomEcg = new DicomEcg();
			dicomEcg.setPatientId(patientId);
			dicomEcg.setPatientName(patientName);
			dicomEcg.setNurseId(nurseId);
			dicomEcg.setNurseName(nurseName);
			dicomEcg.setFilename(filename);
			dicomEcg.setMeasureTime(measureTime);
			dicomEcg.setUploadTime(uploadTime);
			DicomEcg decg = ecgservice.saveDicomEcg(dicomEcg);
			log.info(decg.getId());			
		}
		else
		{
			DicomEcg dicomecgEdit = new DicomEcg();
			
			dicomecgEdit = ecgservice.getDicomEcg(Integer.parseInt(ID1));
			if(!StringUtils.hasText(ID1))
			{
				dicomecgEdit.setPatientId(patientId);
				dicomecgEdit.setPatientName(patientName);
				dicomecgEdit.setNurseId(nurseId);
				dicomecgEdit.setNurseName(nurseName);
				dicomecgEdit.setFilename(filename);
				dicomecgEdit.setMeasureTime(measureTime);
				dicomecgEdit.setUploadTime(uploadTime);
				
				ecgservice.saveDicomEcg(dicomecgEdit);
			}
			
		}

		List<DicomEcg> dicomecg = ecgservice.getAllDicomEcg();
		map.addAttribute("dicomecg",dicomecg);		
	}
	
	
}
