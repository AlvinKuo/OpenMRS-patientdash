package org.openmrs.module.dicomecg.extension.html;

import org.openmrs.module.web.extension.PatientDashboardTabExt;



public class DicomEcgPDboard extends PatientDashboardTabExt{

	@Override
	public String getTabName() {
		// TODO Auto-generated method stub
		return "Patient ECG";
	}
	
	@Override
	public String getTabId() {
		// TODO Auto-generated method stub
		return "ecgPortlet";
	}
	@Override
	public String getRequiredPrivilege() {
		// TODO Auto-generated method stub
		return "Patient Dashboard - Manage Patient ECG";
	}
	
	@Override
	public String getPortletUrl() {
		// TODO Auto-generated method stub
		return "ecgPortlet";
	}

}
