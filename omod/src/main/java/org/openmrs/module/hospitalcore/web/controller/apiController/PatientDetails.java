/**
 * 
 */
package org.openmrs.module.hospitalcore.web.controller.apiController;

/**
 * @author Ghanshyam
 *
 */
public class PatientDetails {
	private String patient_name;
	private String visit_id;
	private int year_of_birth;
	private String address;
	private String patient_abha_id;
	private String patient_identification_proof;
	private String patient_identification_number;
	private String patient_mobile_number;
	private String transaction_type;
	private String uhid_number;
	private String admission_number;
	private String department_visited_name;
	private String department_visited_code;
	private String datetime_of_transaction;

	public String getPatient_name() {
		return patient_name;
	}

	public void setPatient_name(String patient_name) {
		this.patient_name = patient_name;
	}

	public String getVisit_id() {
		return visit_id;
	}

	public void setVisit_id(String visit_id) {
		this.visit_id = visit_id;
	}

	public int getYear_of_birth() {
		return year_of_birth;
	}

	public void setYear_of_birth(int year_of_birth) {
		this.year_of_birth = year_of_birth;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPatient_abha_id() {
		return patient_abha_id;
	}

	public void setPatient_abha_id(String patient_abha_id) {
		this.patient_abha_id = patient_abha_id;
	}

	public String getPatient_identification_proof() {
		return patient_identification_proof;
	}

	public void setPatient_identification_proof(String patient_identification_proof) {
		this.patient_identification_proof = patient_identification_proof;
	}

	public String getPatient_identification_number() {
		return patient_identification_number;
	}

	public void setPatient_identification_number(String patient_identification_number) {
		this.patient_identification_number = patient_identification_number;
	}

	public String getPatient_mobile_number() {
		return patient_mobile_number;
	}

	public void setPatient_mobile_number(String patient_mobile_number) {
		this.patient_mobile_number = patient_mobile_number;
	}

	public String getTransaction_type() {
		return transaction_type;
	}

	public void setTransaction_type(String transaction_type) {
		this.transaction_type = transaction_type;
	}

	public String getUhid_number() {
		return uhid_number;
	}

	public void setUhid_number(String uhid_number) {
		this.uhid_number = uhid_number;
	}

	public String getAdmission_number() {
		return admission_number;
	}

	public void setAdmission_number(String admission_number) {
		this.admission_number = admission_number;
	}

	public String getDepartment_visited_name() {
		return department_visited_name;
	}

	public void setDepartment_visited_name(String department_visited_name) {
		this.department_visited_name = department_visited_name;
	}

	public String getDepartment_visited_code() {
		return department_visited_code;
	}

	public void setDepartment_visited_code(String department_visited_code) {
		this.department_visited_code = department_visited_code;
	}

	public String getDatetime_of_transaction() {
		return datetime_of_transaction;
	}

	public void setDatetime_of_transaction(String datetime_of_transaction) {
		this.datetime_of_transaction = datetime_of_transaction;
	}
}
