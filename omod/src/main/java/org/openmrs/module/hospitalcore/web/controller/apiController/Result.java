/**
 * 
 */
package org.openmrs.module.hospitalcore.web.controller.apiController;

import java.util.ArrayList;

/**
 * @author Ghanshyam
 *
 */
public class Result {
	 public String txn_id;
	    public String from_date;
	    public String to_date;
	    public String hf_id_hmis;
	    public String hf_id_abdm;
	    public String health_facility_name;
	    public ArrayList<ModuleWiseKpi> module_wise_kpi;
		public String getTxn_id() {
			return txn_id;
		}
		public void setTxn_id(String txn_id) {
			this.txn_id = txn_id;
		}
		public String getFrom_date() {
			return from_date;
		}
		public void setFrom_date(String from_date) {
			this.from_date = from_date;
		}
		public String getTo_date() {
			return to_date;
		}
		public void setTo_date(String to_date) {
			this.to_date = to_date;
		}
		public String getHf_id_hmis() {
			return hf_id_hmis;
		}
		public void setHf_id_hmis(String hf_id_hmis) {
			this.hf_id_hmis = hf_id_hmis;
		}
		public String getHf_id_abdm() {
			return hf_id_abdm;
		}
		public void setHf_id_abdm(String hf_id_abdm) {
			this.hf_id_abdm = hf_id_abdm;
		}
		public String getHealth_facility_name() {
			return health_facility_name;
		}
		public void setHealth_facility_name(String health_facility_name) {
			this.health_facility_name = health_facility_name;
		}
		public ArrayList<ModuleWiseKpi> getModule_wise_kpi() {
			return module_wise_kpi;
		}
		public void setModule_wise_kpi(ArrayList<ModuleWiseKpi> module_wise_kpi) {
			this.module_wise_kpi = module_wise_kpi;
		}
}
