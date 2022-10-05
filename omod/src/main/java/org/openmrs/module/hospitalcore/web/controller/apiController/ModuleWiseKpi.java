/**
 * 
 */
package org.openmrs.module.hospitalcore.web.controller.apiController;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Ghanshyam
 *
 */
public class ModuleWiseKpi {
	public String module_code;
	public String module_name;
	public String hmis_code;
	public int opd_count;
	public List<PatientDetails> patient_details;

	public String getModule_code() {
		return module_code;
	}

	public void setModule_code(String module_code) {
		this.module_code = module_code;
	}

	public String getModule_name() {
		return module_name;
	}

	public void setModule_name(String module_name) {
		this.module_name = module_name;
	}

	public String getHmis_code() {
		return hmis_code;
	}

	public void setHmis_code(String hmis_code) {
		this.hmis_code = hmis_code;
	}

	public int getOpd_count() {
		return opd_count;
	}

	public void setOpd_count(int opd_count) {
		this.opd_count = opd_count;
	}

	public List<PatientDetails> getPatient_details() {
		return patient_details;
	}

	public void setPatient_details(List<PatientDetails> patient_details) {
		this.patient_details = patient_details;
	}
}
