package mgi.frontend.datamodel.phenotype;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AlleleSummarySystem
 *
 * This object represents a phenotype system on the allele summary
 */
@Entity
@Table(name="allele_summary_system")
public class AlleleSummarySystem {

	private int alleleSystemKey;
	private int alleleKey;
	private String system;


    // ================= Getters ======================================== //
	@Id
	@Column(name="allele_system_key")
	public int getAlleleSystemKey() {
		return alleleSystemKey;
	}

	@Column(name="allele_key")
	public int getAlleleKey() {
		return alleleKey;
	}

	@Column(name="system")
	public String getSystem() {
		return system;
	}
	
    // ================= Setters ======================================== //

	public void setAlleleSystemKey(int alleleSystemKey) {
		this.alleleSystemKey = alleleSystemKey;
	}

	public void setAlleleKey(int alleleKey) {
		this.alleleKey = alleleKey;
	}

	public void setSystem(String system) {
		this.system= system;
	}

	@Override
	public String toString() {
		return "AlleleSummarySystem [alleleSystemKey=" + alleleSystemKey
				+ ", alleleKey=" + alleleKey + ", system=" + system + "]";
	}
}
