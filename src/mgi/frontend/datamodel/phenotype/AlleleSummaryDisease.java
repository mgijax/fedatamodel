package mgi.frontend.datamodel.phenotype;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AlleleSummaryDisease
 *
 * This object represents a disease on the allele summary
 */
@Entity
@Table(name="allele_summary_disease")
public class AlleleSummaryDisease {

	private int alleleSummaryDiseaseKey;
	private int alleleKey;
	private String disease;
	private String doID;
	
    // ================= Getters ======================================== //
	@Id
	@Column(name="allele_disease_key")
	public int getAlleleSummaryDiseaseKey() {
		return alleleSummaryDiseaseKey;
	}

	@Column(name="allele_key")
	public int getAlleleKey() {
		return alleleKey;
	}

	@Column(name="disease")
	public String getDisease() {
		return disease;
	}

	// TODO change femover column name
	@Column(name="omim_id")
	public String getDoID() {
		return doID;
	}
	
    // ================= Setters ======================================== //
	public void setAlleleSummaryDiseaseKey(int alleleSummaryDiseaseKey) {
		this.alleleSummaryDiseaseKey = alleleSummaryDiseaseKey;
	}

	public void setAlleleKey(int alleleKey) {
		this.alleleKey = alleleKey;
	}

	public void setDisease(String disease) {
		this.disease= disease;
	}

	public void setDoID(String doID) {
		this.doID = doID;
	}

	@Override
	public String toString() {
		return "AlleleSummaryDisease [alleleSummaryDiseaseKey="
				+ alleleSummaryDiseaseKey + ", alleleKey=" + alleleKey
				+ ", disease=" + disease + ", doID=" + doID + "]";
	}
}
