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
package org.openmrs.module.dicomecg.api.impl;

import java.util.List;

import org.openmrs.PatientIdentifier;
import org.openmrs.PersonAddress;
import org.openmrs.api.db.DAOException;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.dicomecg.DicomEcg;
import org.openmrs.module.dicomecg.DicomEcgAttribute;
import org.openmrs.module.dicomecg.DicomEcgConfirm;
import org.openmrs.module.dicomecg.DicomEcgWave;
import org.openmrs.module.dicomecg.api.DicomEcgService;
import org.openmrs.module.dicomecg.api.db.DicomEcgDAO;
import org.springframework.transaction.annotation.Transactional;

/**
 * It is a default implementation of {@link DicomEcgService}.
 */
public class DicomEcgServiceImpl extends BaseOpenmrsService implements DicomEcgService {
	
	protected final Log log = LogFactory.getLog(this.getClass());
	
	private DicomEcgDAO dicomEcgDAO;
	
	/**
     * @param dao the dao to set
     */
    public void setDao(DicomEcgDAO dicomEcgDAO) {
	    this.dicomEcgDAO = dicomEcgDAO;
    }
    
    /**
     * @return the dao
     */
    public DicomEcgDAO getDao() {
	    return dicomEcgDAO;
    }

    /*
     * (non-Javadoc)
     * @see org.openmrs.module.dicomecg.api.DicomEcgService#getAllDicomEcg()
     */
	@Override
	@Transactional(readOnly = true)
	public List<DicomEcg> getAllDicomEcg() {
		// TODO Auto-generated method stub
		return dicomEcgDAO.getAllDicomEcg();
	}

	/*
	 * (non-Javadoc)
	 * @see org.openmrs.module.dicomecg.api.DicomEcgService#saveDicomEcg(org.openmrs.module.dicomecg.DicomEcg)
	 */
	@Override
	public DicomEcg saveDicomEcg(DicomEcg dicomEcg) {
		// TODO Auto-generated method stub
		return dicomEcgDAO.saveDicomEcg(dicomEcg);
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see org.openmrs.module.dicomecg.api.DicomEcgService#getDicomEcg(java.lang.Integer)
	 */
	@Override
	public DicomEcg getDicomEcg(Integer id) {
		// TODO Auto-generated method stub
		return dicomEcgDAO.getDicomEcg(id);
	}

	@Override
	public List<DicomEcg> getfilename(String filename) {
		// TODO Auto-generated method stub		
		return dicomEcgDAO.getfilename(filename);
	}
	
	public List<PatientIdentifier> getPatientID(String Identifier) throws DAOException{
		
		return dicomEcgDAO.getPatientID(Identifier);		
	}

	@Override
	public List<DicomEcg> mapPatientEcgData(Integer id) {
		// TODO Auto-generated method stub
		return dicomEcgDAO.mapPatientEcgData(id);
	}

	@Override
	public DicomEcgAttribute saveDicomEcgAttribute(DicomEcgAttribute attribute) {
		// TODO Auto-generated method stub
		return dicomEcgDAO.saveDicomEcgAttribute(attribute);
	}

	public List<DicomEcgAttribute> getDicomEcgAttribute(String filename){
		return dicomEcgDAO.getDicomEcgAttribute(filename);
	}
	
	public List<DicomEcgConfirm> getDicomEcgConfirm(){
		return dicomEcgDAO.getDicomEcgConfirm();
	}
	
	public boolean checkAttribute(String filename){
		return dicomEcgDAO.checkAttribute(filename);
	}
	
	public DicomEcgWave saveDicomWave(DicomEcgWave wave) {
		// TODO Auto-generated method stub
		return dicomEcgDAO.saveDicomWave(wave);
	}
	
	public List<DicomEcgWave> getDicomEcgWave(String filename){
		return dicomEcgDAO.getDicomEcgWave(filename);
	}

	@Override
	public boolean checkWave(String filename) {
		// TODO Auto-generated method stub
		return dicomEcgDAO.checkWave(filename);
	}
	
	public DicomEcg getDicomWaveId(Integer id){
		return dicomEcgDAO.getDicomWaveId(id);
	}

	@Override
	public List<PersonAddress> getPersonMail(Integer patiendId) {
		// TODO Auto-generated method stub
		return dicomEcgDAO.getPersonMail(patiendId);
	}

	@Override
	public DicomEcgConfirm saveDicomEcgConfirm(DicomEcgConfirm uploadConfirm) {
		// TODO Auto-generated method stub
		return dicomEcgDAO.saveDicomEcgConfirm(uploadConfirm);
	}

	@Override
	public DicomEcgConfirm getDicomConfirmId(Integer confirmId) {
		// TODO Auto-generated method stub
		return dicomEcgDAO.getDicomConfirmId(confirmId);
	}

	@Override
	public List<DicomEcgConfirm> getConfirm(String filename) {
		// TODO Auto-generated method stub
		return dicomEcgDAO.getConfirm(filename);
	}
	
}