/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.patientdash.api.db.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.openmrs.module.patientdash.PatientDICOMViewer;
import org.openmrs.module.patientdash.api.db.PatientDICOMViewerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * It is a default implementation of  {@link PatientDICOMViewerDAO}.
 */

@Repository
public class HibernatePatientDICOMViewerDAO implements PatientDICOMViewerDAO {
	protected final Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private SessionFactory sessionFactory;
	
	/**
     * @param sessionFactory the sessionFactory to set
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
	    this.sessionFactory = sessionFactory;
    }
    
	/**
     * @return the sessionFactory
     */
    public SessionFactory getSessionFactory() {
	    return sessionFactory;
    }
    
	public void initializeTables() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		
    	PatientDICOMViewer ecgid = new PatientDICOMViewer();
    	ecgid.setId(5);    	
    	PatientDICOMViewer ecgpid = new PatientDICOMViewer();
    	ecgpid.setPatiendId("A987654321");    	
    	PatientDICOMViewer ecgpname = new PatientDICOMViewer();
    	ecgpname.setPatientName("CheWeiKuo");   	
    	PatientDICOMViewer ecgnid = new PatientDICOMViewer();
    	ecgnid.setNurseId("4321");    	
    	PatientDICOMViewer ecgnname = new PatientDICOMViewer();
    	ecgnname.setNurseName("Alan");    	
    	PatientDICOMViewer ecgfilename = new PatientDICOMViewer();
    	ecgfilename.setFilename("A987654321201304101012.dcm");    	
    	PatientDICOMViewer ecgmtime = new PatientDICOMViewer();
    	ecgmtime.setMeasureTime("2013-04-10 22:13");    	
    	PatientDICOMViewer ecgutime = new PatientDICOMViewer();
    	ecgutime.setUploadTime("2013-04-10 22:14");
    	
    	session.save(ecgpid);
    	session.save(ecgpname);
    	session.save(ecgnid);
    	session.save(ecgnname);
    	session.save(ecgfilename);
    	session.save(ecgmtime);
    	session.save(ecgutime);
	}
    
    
}