/**
 * 
 */
package org.openmrs.module.hospitalcore.web.controller.apiController;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.openmrs.Encounter;
import org.openmrs.EncounterType;
import org.openmrs.Obs;
import org.openmrs.Person;
import org.openmrs.PersonAttribute;
import org.openmrs.api.ConceptService;
import org.openmrs.api.context.Context;
import org.openmrs.module.ModuleMustStartException;
import org.openmrs.module.hospitalcore.HospitalCoreService;
import org.openmrs.module.hospitalcore.IpdService;
import org.openmrs.module.hospitalcore.PatientQueueService;
import org.openmrs.module.hospitalcore.model.IpdPatientAdmissionLog;
import org.openmrs.module.hospitalcore.model.OpdPatientQueueLog;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Ghanshyam
 *
 */
@Controller
@RequestMapping("/ws/rest")
public class PatientInfoDetailsController {
	@RequestMapping(value = "/patient-info-details", method = RequestMethod.GET)
	public void getPatientInfoDetails(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value = "hfidABDM", required = false) String hfidABDM,
			@RequestParam(value = "hfidHMIS", required = false) String hfidHMIS,
			@RequestParam(value = "startDate", required = false) String startFromDate,
			@RequestParam(value = "endDate", required = false) String endFromDate,
			@RequestParam(value = "module_code", required = false) String module_code)
			throws JsonGenerationException, JsonMappingException, IOException, ParseException {
		System.out.println("xxxxxxxxxxxxxxxxxx-" + Context.isAuthenticated());
		HospitalCoreService hospitalCoreService = Context.getService(HospitalCoreService.class);
		PatientQueueService patientQueueService = Context.getService(PatientQueueService.class);
		IpdService ipdService = Context.getService(IpdService.class);
		ConceptService conceptService = Context.getService(ConceptService.class);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		// SimpleDateFormat formatterExt = new SimpleDateFormat("MM/dd/yyyy");
		// String startFromDate = startDateInStr + " 00:00:00";
		// String endFromDate = endDateInStr + " 23:59:59";
		System.out.println("aaaaaaaaaaaaaaaaaaaa-" + startFromDate);
		System.out.println("BBBBBBBBBBBBBBBBBBBBB-" + endFromDate);
		Date startDate = formatter.parse(startFromDate);
		Date endDate = formatter.parse(endFromDate);
		try {
			Context.openSession();
			Context.authenticate("admin", "Hamirpur@1234");
			System.out.println("yyyyyyyyyyyyyyyyyyyyyyyy-" + Context.isAuthenticated());
			String hfidABDMg = Context.getAdministrationService().getGlobalProperty("hfidABDM");
			String hfidHMISg = Context.getAdministrationService().getGlobalProperty("hfidHMIS");
			String hmisId = Context.getAdministrationService().getGlobalProperty("hmis_health_facility_id");
			String hmisCode = Context.getAdministrationService().getGlobalProperty("hmis_code");
			String hfn = Context.getAdministrationService().getGlobalProperty("health_facility_name");
			if (hfidABDMg.equals(hfidABDM) && hfidHMISg.equals(hfidHMIS)) {
				// String hmisId =
				// Context.getAdministrationService().getGlobalProperty("hmis_health_facility_id");
				// String hmisId =
				// Context.getAdministrationService().getGlobalProperty("hmis_health_facility_id");
				System.out.println("zzzzzzzzzzzzzzzzzz-" + hmisId);
				response.setContentType("application/json");
				ServletOutputStream out = response.getOutputStream();
				EncounterType opdEncounterType = Context.getEncounterService().getEncounterType("OPDENCOUNTER");
				EncounterType ipdEncounterType = Context.getEncounterService().getEncounterType("IPDENCOUNTER");
				/*
				 * Date endDate = new Date(); Calendar cal = Calendar.getInstance();
				 * cal.setTime(endDate); cal.add(Calendar.DATE, -3); Date startDate =
				 * cal.getTime();
				 */
				Set<Encounter> opdPatients = hospitalCoreService.getEncountersByEncounterType(opdEncounterType,
						startDate, endDate);
				Set<Encounter> ipdPatients = hospitalCoreService.getEncountersByEncounterType(ipdEncounterType,
						startDate, endDate);
				System.out.println("pppppppppppppppp-" + opdPatients.size());
				System.out.println("qqqqqqqqqqqqqqqq-" + ipdPatients.size());
				PatientInfoDetails patientInfoDetails = new PatientInfoDetails();
				ArrayList<Result> results = new ArrayList<Result>();
				Result result = new Result();
				ArrayList<ModuleWiseKpi> mkpis = new ArrayList<ModuleWiseKpi>();
				/*
				 * ModuleWiseKpi mkpi = new ModuleWiseKpi(); mkpi.setHmis_code("123");
				 * mkpi.setModule_code("456"); mkpi.setModule_name("01"); mkpi.setOpd_count(1);
				 * mkpis.add(mkpi);
				 */
				List<PatientDetails> patientDetailsList = new LinkedList<PatientDetails>();
				PatientDetails patientDetails;
				if (module_code.equals("01")) {
					System.out.println("rrrrrrrrrrrrrrrrrrrrrrr-");
					ModuleWiseKpi mkpi = new ModuleWiseKpi();
					mkpi.setHmis_code(hmisCode);
					mkpi.setModule_code("01");
					mkpi.setModule_name("OPD");
					mkpi.setOpd_count(opdPatients.size());
					for (Encounter opdPatient : opdPatients) {
						System.out.println("ssssssssssssssssssssss-" + opdPatient);
						patientDetails = new PatientDetails();
						Person person = opdPatient.getPatient().getPersonName().getPerson();
						patientDetails.setPatient_name(OpenmrsUtil.getName(person));
						patientDetails.setVisit_id(null);
						patientDetails.setYear_of_birth(person.getBirthdate().getYear());
						patientDetails.setAddress(person.getAddresses().toString());
						patientDetails.setPatient_abha_id(null);
						patientDetails.setPatient_identification_proof("Aadhaar Card");
						PersonAttribute aadhaarNo = hospitalCoreService.getPersonAttributeByPersonAndAttributeName(
								person, Context.getPersonService().getPersonAttributeTypeByName("Aadhar Card Number"));
						PersonAttribute mobileNo = hospitalCoreService.getPersonAttributeByPersonAndAttributeName(
								person, Context.getPersonService().getPersonAttributeTypeByName("Phone Number"));
						OpdPatientQueueLog opdPatientQueueLog = patientQueueService
								.getOpdPatientQueueLogByEncounter(opdPatient);
						Obs newPatient = patientQueueService.getObservationByPersonConceptAndEncounter(
								opdPatient.getPatient().getPersonName().getPerson(),
								conceptService.getConceptByName("NEW PATIENT"), opdPatient);
						Obs revisitPatient = patientQueueService.getObservationByPersonConceptAndEncounter(
								opdPatient.getPatient().getPersonName().getPerson(),
								conceptService.getConceptByName("REVISIT"), opdPatient);
						if (aadhaarNo != null) {
							patientDetails.setPatient_identification_number(aadhaarNo.getValue());
						}
						if (mobileNo != null) {
							patientDetails.setPatient_mobile_number(aadhaarNo.getValue());
						}
						if (newPatient != null) {
							patientDetails.setTransaction_type("1");
						} else if (revisitPatient != null) {
							patientDetails.setTransaction_type("2");
						}
						patientDetails.setUhid_number("");
						patientDetails.setDepartment_visited_code("");
						patientDetails.setDepartment_visited_name(opdPatientQueueLog.getOpdConceptName());
						patientDetails.setDatetime_of_transaction(formatter.format(opdPatientQueueLog.getCreatedOn()));
						patientDetailsList.add(patientDetails);
					}
					mkpi.setPatient_details(patientDetailsList);
					System.out.println("tttttttttttttttttt-" + patientDetailsList);
					System.out.println("uuuuuuuuuuuuuuuuuuuuu-" + patientDetailsList.size());
					mkpis.add(mkpi);
					result.setHealth_facility_name(hfn);
					result.setModule_wise_kpi(mkpis);
					result.setTxn_id("");
					result.setFrom_date(startFromDate);
					result.setTo_date(endFromDate);
					result.setHf_id_hmis(hfidHMIS);
					result.setHf_id_abdm(hfidABDM);
					results.add(result);
				} else if (module_code.equals("02")) {
					result = new Result();
					ModuleWiseKpi mkpi = new ModuleWiseKpi();
					mkpi.setHmis_code(hmisCode);
					mkpi.setModule_code("02");
					mkpi.setModule_name("IPD");
					mkpi.setOpd_count(ipdPatients.size());
					for (Encounter ipdPatient : ipdPatients) {
						patientDetails = new PatientDetails();
						Person person = ipdPatient.getPatient().getPersonName().getPerson();
						patientDetails.setPatient_name(OpenmrsUtil.getName(person));
						patientDetails.setVisit_id(null);
						patientDetails.setYear_of_birth(person.getBirthdate().getYear());
						patientDetails.setAddress(person.getAddresses().toString());
						patientDetails.setPatient_abha_id(null);
						patientDetails.setPatient_identification_proof("Aadhaar Card");
						PersonAttribute aadhaarNo = hospitalCoreService.getPersonAttributeByPersonAndAttributeName(
								person, Context.getPersonService().getPersonAttributeTypeByName("Aadhaar card number"));
						PersonAttribute mobileNo = hospitalCoreService.getPersonAttributeByPersonAndAttributeName(
								person, Context.getPersonService().getPersonAttributeTypeByName("Phone Number"));
						IpdPatientAdmissionLog ipdPatientAdmissionLog = ipdService
								.getIpdPatientAdmissionLogByEncounter(ipdPatient);
						Obs newPatient = patientQueueService.getObservationByPersonConceptAndEncounter(
								ipdPatient.getPatient().getPersonName().getPerson(),
								conceptService.getConceptByName("NEW PATIENT"), ipdPatient);
						Obs revisitPatient = patientQueueService.getObservationByPersonConceptAndEncounter(
								ipdPatient.getPatient().getPersonName().getPerson(),
								conceptService.getConceptByName("REVISIT"), ipdPatient);
						if (aadhaarNo != null) {
							patientDetails.setPatient_identification_number(aadhaarNo.getValue());
						}
						if (mobileNo != null) {
							patientDetails.setPatient_mobile_number(aadhaarNo.getValue());
						}
						if (newPatient != null) {
							patientDetails.setTransaction_type("1");
						} else if (revisitPatient != null) {
							patientDetails.setTransaction_type("2");
						}
						patientDetails.setUhid_number("");
						patientDetails.setAdmission_number("");
						patientDetails.setDepartment_visited_code("");
						patientDetails.setDepartment_visited_name(
								ipdPatientAdmissionLog.getAdmissionWard().getName().getName());
						patientDetails.setDatetime_of_transaction(
								formatter.format(ipdPatientAdmissionLog.getAdmissionDate()));
						patientDetailsList.add(patientDetails);
					}
					mkpi.setPatient_details(patientDetailsList);
					mkpis.add(mkpi);
					result.setHealth_facility_name(hfn);
					result.setModule_wise_kpi(mkpis);
					result.setTxn_id("");
					result.setFrom_date(startFromDate);
					result.setTo_date(endFromDate);
					result.setHf_id_hmis(hfidHMIS);
					result.setHf_id_abdm(hfidABDM);
					results.add(result);
				}
				patientInfoDetails.setResult(results);
				new ObjectMapper().writeValue(out, patientInfoDetails);
			}
		} catch (ModuleMustStartException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			delete(request, response);
		}

	}

	public PatientInfoDetails getPatientDetails(int opdCount) {
		PatientInfoDetails patientInfoDetails = new PatientInfoDetails();
		// patientInfoDetails.setOpdCount(opdCount);
		return patientInfoDetails;
	}

	public void delete(HttpServletRequest request, HttpServletResponse response) {
		Context.logout();
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}

	}
}
