package org.openmrs.module.dicomecg.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Person;
import org.openmrs.api.context.Context;
import org.openmrs.module.dicomecg.DicomEcg;
import org.openmrs.module.dicomecg.api.DicomEcgService;
import org.openmrs.web.controller.PortletController;
import org.springframework.stereotype.Controller;


@Controller
public class DicomEcgPortletController extends PortletController   {
	
	@Override
	protected void populateModel(HttpServletRequest request, Map<String , Object> model) {
		
		Person pid = Context.getPersonService().getPerson(Integer.parseInt(request.getParameter("patientId")));
		if(pid != null)
		{			
			DicomEcgService portlet = Context.getService(DicomEcgService.class);
			List<DicomEcg> portletEcg = portlet.getAllDicomEcg();
			model.put( "test" , portletEcg );
		}
		
	}

}
