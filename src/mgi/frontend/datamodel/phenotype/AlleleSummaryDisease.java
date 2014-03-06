package mgi.frontend.datamodel.phenotype;

import java.util.*;
import javax.persistence.*;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
	private String omimID;
	
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

	@Column(name="omim_id")
	public String getOmimID() {
		return omimID;
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

	public void setOmimID(String omimID) {
		this.omimID= omimID;
	}

	@Override
	public String toString() {
		return "AlleleSummaryDisease [alleleSummaryDiseaseKey="
				+ alleleSummaryDiseaseKey + ", alleleKey=" + alleleKey
				+ ", disease=" + disease + ", omimID=" + omimID + "]";
	}
}
