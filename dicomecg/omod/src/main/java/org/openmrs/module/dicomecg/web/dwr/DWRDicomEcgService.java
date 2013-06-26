package org.openmrs.module.dicomecg.web.dwr;

import org.openmrs.Concept;
import org.openmrs.api.context.Context;

import org.springframework.web.bind.annotation.RequestParam;



public class DWRDicomEcgService {
	
	
	public Concept getConcept(@RequestParam(required = false, value = "newConceptId") String newConceptId){
		
		Concept newConcept = Context.getConceptService().getConcept(newConceptId);
		
		return newConcept;
	}
	

}
