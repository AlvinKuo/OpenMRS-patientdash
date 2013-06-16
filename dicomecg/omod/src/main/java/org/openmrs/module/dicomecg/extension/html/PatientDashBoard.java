package org.openmrs.module.dicomecg.extension.html;

import org.openmrs.module.web.extension.PatientDashboardTabExt;

public class PatientDashBoard extends PatientDashboardTabExt{
	
    @Override
    public String getTabName() {
        return "patientdash.title";
    }
    
    @Override
    public String getTabId() {
        return "patientDashForm";
    }

    @Override
    public String getRequiredPrivilege() {
        return "Patient Dashboard - Manage Patient Dashboard Test";
    }

    @Override
    public String getPortletUrl() {
        return "patientDashForm";
    }

}
