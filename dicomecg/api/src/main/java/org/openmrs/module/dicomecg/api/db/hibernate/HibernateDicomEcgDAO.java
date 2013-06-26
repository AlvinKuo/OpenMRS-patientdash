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
package org.openmrs.module.dicomecg.api.db.hibernate;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.openmrs.PatientIdentifier;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.dicomecg.DicomEcg;
import org.openmrs.module.dicomecg.DicomEcgAttribute;
import org.openmrs.module.dicomecg.DicomEcgConfirm;
import org.openmrs.module.dicomecg.DicomEcgWave;
import org.openmrs.module.dicomecg.api.db.DicomEcgDAO;

/**
 * It is a default implementation of  {@link DicomEcgDAO}.
 */
public class HibernateDicomEcgDAO implements DicomEcgDAO {
	protected final Log log = LogFactory.getLog(this.getClass());
	
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

	@SuppressWarnings("unchecked")
	public List<DicomEcg> getAllDicomEcg() {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(DicomEcg.class);
		return criteria.list();
	}

	@Override
	public DicomEcg saveDicomEcg(DicomEcg dicomEcg) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(dicomEcg);		
		return dicomEcg;
	}
	
	@Override
	public DicomEcg getDicomEcg(Integer id) {
		// TODO Auto-generated method stub
		return (DicomEcg) sessionFactory.getCurrentSession().get(DicomEcg.class,id);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<DicomEcg> getfilename(String filename) {
		// TODO Auto-generated method stub		
		//---sql query---
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(DicomEcg.class);		
		criteria.add(Restrictions.eq("filename",filename));						
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<PatientIdentifier> getPatientID(String Identifier) throws DAOException
	{		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(PatientIdentifier.class);
		criteria.add(Restrictions.eq("identifier", Identifier));				
		return criteria.list();				
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DicomEcg> mapPatientEcgData(Integer id) {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(DicomEcg.class);
		criteria.add(Restrictions.eq("patiendId", id));
		return criteria.list();	
	}

	@Override
	public DicomEcgAttribute saveDicomEcgAttribute(DicomEcgAttribute attribute) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(attribute);		
		return attribute;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DicomEcgAttribute> getDicomEcgAttribute(String filename){
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(DicomEcgAttribute.class);
		criteria.add(Restrictions.eq("filename", filename));
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<DicomEcgConfirm> getDicomEcgConfirm(){
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(DicomEcgConfirm.class);		
		return criteria.list();
	}

	@Override
	public boolean checkAttribute(String filename) {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(DicomEcgAttribute.class);
		Iterator res = criteria.add(Restrictions.eq("filename",filename)).list().iterator();
		if(res.hasNext()){
			return true;
		}
		else{
			return false;
		}	
	}

	@Override
	public DicomEcgWave saveDicomWave(DicomEcgWave wave) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(wave);		
		return wave;
	}


	@SuppressWarnings("unchecked")
	public List<DicomEcgWave> getDicomEcgWave(String filename){
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(DicomEcgWave.class);
		criteria.add(Restrictions.eq("filename", filename));
		return criteria.list();
	}

	@Override
	public boolean checkWave(String filename) {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(DicomEcgWave.class);
		Iterator res = criteria.add(Restrictions.eq("filename",filename)).list().iterator();
		if(res.hasNext()){
			return true;
		}
		else{
			return false;
		}
	}
	
	@Override
	public DicomEcg getDicomWaveId(Integer id) {
		// TODO Auto-generated method stub
		return (DicomEcg) sessionFactory.getCurrentSession().get(DicomEcg.class,id);
	}

}