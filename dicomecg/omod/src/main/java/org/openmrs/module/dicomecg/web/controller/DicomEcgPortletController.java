package org.openmrs.module.dicomecg.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.openmrs.api.context.Context;
import org.openmrs.module.dicomecg.DicomEcg;
import org.openmrs.module.dicomecg.api.DicomEcgService;
import org.openmrs.web.controller.PortletController;
import org.springframework.stereotype.Controller;


@Controller
public class DicomEcgPortletController extends PortletController   {
	
	@Override
	protected void populateModel(HttpServletRequest request, Map<String , Object> model) {
		
		Integer id = Integer.parseInt(request.getParameter("patientId"));   //--get patient id integer 4 	
		if(id != null)
		{
			DicomEcgService portlet = Context.getService(DicomEcgService.class);			
			List<DicomEcg> portletEcg = portlet.mapPatientEcgData(id);
			model.put( "portecg" , portletEcg );
		}		
	}

}

/*
		//Patient px = Context.getPatientService().getPatient(id);   //--get Patient#4
		//PatientIdentifier identifier = px.getPatientIdentifier();
		//model.put( "personx" , id );
		//model.put( "persony" , px );
		//Person pid = Context.getPersonService().getPerson(Integer.parseInt(request.getParameter("patientId")));
*/