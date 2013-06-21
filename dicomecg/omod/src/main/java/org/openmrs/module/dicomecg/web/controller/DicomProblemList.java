package org.openmrs.module.dicomecg.web.controller;


import java.util.Collection;

import org.openmrs.Concept;
import org.openmrs.ConceptName;
import org.openmrs.api.ConceptService;
import org.openmrs.api.context.Context;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DicomProblemList {

	
	@RequestMapping(value = "/module/dicomecg/listproblem", method = RequestMethod.GET)
	public void manage(ModelMap model) {
		model.addAttribute("user", Context.getAuthenticatedUser());
	}
	
	
	
	@RequestMapping(value = "/module/dicomecg/listproblem", method = RequestMethod.POST)	
	public void processTest(ModelMap model,
			@RequestParam(required = false, value = "conceptId") Integer ConceptId) {		
			model.addAttribute("user", Context.getAuthenticatedUser());		
	}
	
	@ModelAttribute("newConcept")
	public Concept getConcept(@RequestParam(required = false, value = "newConceptId") String newConceptId){
		
		Concept newConcept = Context.getConceptService().getConcept(newConceptId);
		
		return newConcept;
	}
	
	
}
