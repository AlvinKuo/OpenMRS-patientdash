package org.openmrs.module.dicomecg.web.controller;


import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.dicomecg.DicomEcg;
import org.openmrs.module.dicomecg.DicomEcgAttribute;
import org.openmrs.module.dicomecg.DicomEcgConfirm;
import org.openmrs.module.dicomecg.DicomEcgWave;
import org.openmrs.module.dicomecg.api.DicomEcgService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DicomEcgPhysicianController {
	
	protected final Log log = LogFactory.getLog(getClass());
	
	boolean checkExitAttribute;
	
	@ModelAttribute
	
	@RequestMapping(value = "/module/dicomecg/doctorpage", method = RequestMethod.GET)
	public void manage(ModelMap model) {
		model.addAttribute("user", Context.getAuthenticatedUser());
	}
	
	@RequestMapping(value = "/module/dicomecg/doctorpage", method = RequestMethod.POST)
	public void setpage(ModelMap model,
			@RequestParam(required = false, value = "filename") String filename
			){
		
		//get admin
		model.addAttribute("user", Context.getAuthenticatedUser());model.addAttribute("user", Context.getAuthenticatedUser());
		
		//use filename to get the right ecg information 
		DicomEcgService physicianService = Context.getService(DicomEcgService.class);
		List<DicomEcgConfirm> confirmEcg = physicianService.getConfirm(filename);
		Iterator<DicomEcgConfirm> confirmRes= confirmEcg.iterator();
		
		if(confirmRes.hasNext())
		{
			List<DicomEcg> physicianEcg= physicianService.getfilename(filename);
			List<DicomEcgAttribute> patientAttribute = physicianService.getDicomEcgAttribute(filename);
			List<DicomEcgWave> patientWave = physicianService.getDicomEcgWave(filename);
			model.addAttribute("attribute", patientAttribute);
			model.addAttribute("doctorpage", physicianEcg);
			model.addAttribute("wave", patientWave);
			model.addAttribute("confirm", confirmEcg);
		}
		else
		{
			try{				
				List<DicomEcg> physicianEcg= physicianService.getfilename(filename);
				List<DicomEcgAttribute> patientAttribute = physicianService.getDicomEcgAttribute(filename);
				List<DicomEcgWave> patientWave = physicianService.getDicomEcgWave(filename);
				
				model.addAttribute("doctorpage", physicianEcg);
				model.addAttribute("attribute", patientAttribute);				
				model.addAttribute("wave", patientWave);
				
			}catch(Exception e){
				System.out.print(e.getMessage());
			}
		}		
	}
	
	//---get concept Id
/*	@ModelAttribute
	public Concept getConcept(@RequestParam(required = false, value = "conceptId") Concept concept){
		return concept;
	}*/
	
}
