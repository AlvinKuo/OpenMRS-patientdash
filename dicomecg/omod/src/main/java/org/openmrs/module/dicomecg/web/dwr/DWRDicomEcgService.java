package org.openmrs.module.dicomecg.web.dwr;

import org.openmrs.Concept;
import org.openmrs.api.context.Context;

public class DWRDicomEcgService {
	
	public Concept getConcept(String newConceptId){
		
		Concept newConcept = Context.getConceptService().getConcept(newConceptId);
		
		return newConcept;
	}
	

}
