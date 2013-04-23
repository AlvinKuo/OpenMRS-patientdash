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

import org.openmrs.api.impl.BaseOpenmrsService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.dicomecg.api.DicomEcgService;
import org.openmrs.module.dicomecg.api.db.DicomEcgDAO;

/**
 * It is a default implementation of {@link DicomEcgService}.
 */
public class DicomEcgServiceImpl extends BaseOpenmrsService implements DicomEcgService {
	
	protected final Log log = LogFactory.getLog(this.getClass());
	
	private DicomEcgDAO dao;
	
	/**
     * @param dao the dao to set
     */
    public void setDao(DicomEcgDAO dao) {
	    this.dao = dao;
    }
    
    /**
     * @return the dao
     */
    public DicomEcgDAO getDao() {
	    return dao;
    }
}