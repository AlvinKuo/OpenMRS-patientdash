package org.openmrs.module.dicomecg.extension.html;

import org.openmrs.module.web.extension.PatientDashboardTabExt;

public class PatientEcgBoard extends PatientDashboardTabExt{
	
    @Override
    public String getTabName() {
        return "dicomecg.ecgtitle";
    }
    
    @Override
    public String getTabId() {
        return "patientEcgForm";
    }

    @Override
    public String getRequiredPrivilege() {
        return "Patient ECG - Manage Patient Dashboard ECG";
    }

    @Override
    public String getPortletUrl() {
        return "patientEcgForm";
    }

}
