package org.openmrs.module.dicomecg.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.SessionFactory;
import org.openmrs.Patient;
import org.openmrs.PatientIdentifier;
import org.openmrs.Person;
import org.openmrs.api.context.Context;
import org.openmrs.module.dicomecg.DicomEcg;
import org.openmrs.module.dicomecg.api.DicomEcgService;
import org.openmrs.web.controller.PortletController;
import org.springframework.stereotype.Controller;


@Controller
public class DicomEcgPortletController extends PortletController   {
	
	private SessionFactory sessionFactory;
	//private String Identifier = "A123456789";
	
	
	@Override
	protected void populateModel(HttpServletRequest request, Map<String , Object> model) {
		
		/*Person pid = Context.getPersonService().getPerson(Integer.parseInt(request.getParameter("patientId")));*/
		
		Integer id = Integer.parseInt(request.getParameter("patientId"));   //--get patient id 4 for kuo
		Patient px = Context.getPatientService().getPatient(id);   //--get patient 4
		PatientIdentifier pid = px.getPatientIdentifier();
		
		model.put( "personx" , pid );
		
		if(pid != null)
		{			
			DicomEcgService portlet = Context.getService(DicomEcgService.class);
			List<DicomEcg> portletEcg = portlet.getAllDicomEcg();
			model.put( "portecg" , portletEcg );
		}
		
	}

}
