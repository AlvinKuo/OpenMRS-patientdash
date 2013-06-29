package org.openmrs.module.dicomecg.web.controller;

import java.util.List;

import org.openmrs.api.context.Context;
import org.openmrs.module.dicomecg.DicomEcgConfirm;
import org.openmrs.module.dicomecg.api.DicomEcgService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UpdateColumnTest {
	
	@RequestMapping(value = "/module/dicomecg/updatecolumn", method = RequestMethod.GET)
	public void manage(ModelMap model) {
		model.addAttribute("user", Context.getAuthenticatedUser());
		
		DicomEcgService showFirst = Context.getService(DicomEcgService.class);
		List<DicomEcgConfirm> comfirmAll = showFirst.getDicomEcgConfirm();
		model.addAttribute("confirmall",comfirmAll);
		
	}
	
	@RequestMapping(value = "/module/dicomecg/updatecolumn", method = RequestMethod.POST)
	public void process(ModelMap map,
			@RequestParam(required=false, value="id") String Id,
			@RequestParam(required=false, value="patientId") String patientId,
			@RequestParam(required=false, value="identifier") String identifier,
			@RequestParam(required=false, value="confirmTime") String confirmTime,
			@RequestParam(required=false, value="confirmName") String confirmName,
			@RequestParam(required=false, value="comment") String comment,
			@RequestParam(required=false, value="filename") String filename,
			@RequestParam(required=false, value="mail") String mail
			){
		
		DicomEcgService confirm = Context.getService(DicomEcgService.class);
		
		if(!StringUtils.hasText(Id)){
			
			DicomEcgService saveConfirm = Context.getService(DicomEcgService.class);
			DicomEcgConfirm uploadConfirm = new DicomEcgConfirm();
			uploadConfirm.setPatientId(Integer.valueOf(patientId));
			uploadConfirm.setIdentifier(identifier);
			uploadConfirm.setFilename(filename);
			uploadConfirm.setConfirmTime(confirmTime);
			uploadConfirm.setConfirmName(confirmName);
			uploadConfirm.setComment(comment);
			uploadConfirm.setMail(mail);
			saveConfirm.saveDicomEcgConfirm(uploadConfirm);
			
		}
		
		else{
			DicomEcgConfirm dicomEdit = new DicomEcgConfirm();
			dicomEdit = confirm.getDicomConfirmId(Integer.valueOf(Id));
			
			if(StringUtils.hasText(patientId))
			{
				dicomEdit.setPatientId(Integer.valueOf(patientId));
				dicomEdit.setIdentifier(identifier);
				dicomEdit.setFilename(filename);
				dicomEdit.setConfirmTime(confirmTime);
				dicomEdit.setConfirmName(confirmName);
				dicomEdit.setComment(comment);
				dicomEdit.setMail(mail);
				confirm.saveDicomEcgConfirm(dicomEdit);
			}
			else{
				map.addAttribute("confirmEdit", dicomEdit);				
			}
		}
		
		List<DicomEcgConfirm> comfirmAll = confirm.getDicomEcgConfirm();
		map.addAttribute("confirmall", comfirmAll);
	}

}
