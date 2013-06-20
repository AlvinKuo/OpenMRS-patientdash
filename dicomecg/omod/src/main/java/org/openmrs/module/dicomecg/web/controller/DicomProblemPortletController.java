package org.openmrs.module.dicomecg.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.openmrs.Concept;
import org.openmrs.api.context.Context;
import org.openmrs.web.controller.PortletController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class DicomProblemPortletController extends PortletController  {
	
	protected void populateModel(HttpServletRequest request, Map<String , Object> model) {
		
	}
	
	@ModelAttribute("newConcept")
	public Concept getConcept(@RequestParam(required = false, value = "newConceptId") String newConceptId){
		
		Concept newConcept = Context.getConceptService().getConcept(newConceptId);
		
		return newConcept;
	}
	
}
