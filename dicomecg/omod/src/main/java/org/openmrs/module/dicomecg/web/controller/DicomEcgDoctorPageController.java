package org.openmrs.module.dicomecg.web.controller;


import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.dicomecg.DicomEcg;
import org.openmrs.module.dicomecg.api.DicomEcgService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DicomEcgDoctorPageController {
	
	protected final Log log = LogFactory.getLog(getClass());

	@RequestMapping(value = "/module/dicomecg/doctorpage", method = RequestMethod.GET)
	public void manage(ModelMap model) {
		model.addAttribute("user", Context.getAuthenticatedUser());	
	}
	
	
	@RequestMapping(value = "/module/dicomecg/doctorpage", method = RequestMethod.POST)
	public void setpage(ModelMap model,
			@RequestParam(required = false, value = "filename") String filename){
		
		//get admin
		model.addAttribute("user", Context.getAuthenticatedUser());model.addAttribute("user", Context.getAuthenticatedUser());
		
		//use filename to get the right ecg information 
		DicomEcgService doctorpage = Context.getService(DicomEcgService.class);
		List<DicomEcg> doctorEcg= doctorpage.getfilename(filename);
		Iterator<DicomEcg> res= doctorEcg.iterator();
		if(res.hasNext())
		{
			model.addAttribute("doctorpage", doctorEcg);
		}
			
	}
	
	
}
