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

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StringType;
import org.openmrs.module.dicomecg.DicomEcg;
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

    /*
     * (non-Javadoc)
     * @see org.openmrs.module.dicomecg.api.db.DicomEcgDAO#getAllDicomEcg()
     */
	@SuppressWarnings("unchecked")
	public List<DicomEcg> getAllDicomEcg() {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(DicomEcg.class);
		return criteria.list();
	}

	/*
	 * (non-Javadoc)
	 * @see org.openmrs.module.dicomecg.api.db.DicomEcgDAO#saveDicomEcg(org.openmrs.module.dicomecg.DicomEcg)
	 */
	@Override
	public DicomEcg saveDicomEcg(DicomEcg dicomEcg) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(dicomEcg);
		return dicomEcg;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.openmrs.module.dicomecg.api.db.DicomEcgDAO#getDicomEcg(java.lang.Integer)
	 */

	@Override
	public DicomEcg getDicomEcg(Integer id) {
		// TODO Auto-generated method stub
		return (DicomEcg) sessionFactory.getCurrentSession().get(DicomEcg.class , id);
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

	@Override
	public List<Object[]> filename(String filename) {

		// TODO Auto-generated method stub
		//SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery("select * from ecg where filename like 'A12345678920110818110811.dcm'"  );
		SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery("from ecg a where a.filename like 'A12345678920110818110811.dcm' "  );
		sqlQuery
		.addScalar("patient_id",  StringType.INSTANCE)
		.addScalar("patient_name",  StringType.INSTANCE)
		.addScalar("nurse_id",  StringType.INSTANCE)
		.addScalar("nurse_name",  StringType.INSTANCE)
		.addScalar("filename",  StringType.INSTANCE)
		.addScalar("measure_time",  StringType.INSTANCE)
		.addScalar("upload_time",  StringType.INSTANCE)
		.setResultTransformer(Transformers.aliasToBean(DicomEcg.class));
		List<Object[]> Filename = sqlQuery.list();
		
		return Filename;
	}
    
}