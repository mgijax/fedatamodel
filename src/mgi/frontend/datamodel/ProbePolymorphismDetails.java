package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Is: a set of details about an allele for a ProbePolymorphism object
 */
@Entity
@Table(name="probe_polymorphism_details")
public class ProbePolymorphismDetails {
	private int uniqueKey;
	private int probePolymorphismKey;
	private String allele;
	private String fragments;
	private String strains;
	
    // ================= Getters and Setters ===================== //
	
	@Column(name="allele")
	public String getAllele() {
		return allele;
	}

	@Column(name="fragments")
	public String getFragments() {
		return fragments;
	}

	@Column(name="probe_polymorphism_key")
	public int getProbePolymorphismKey() {
		return probePolymorphismKey;
	}

	@Column(name="strains")
	public String getStrains() {
		return strains;
	}

	@Id
	@Column(name="unique_key")
	public int getUniqueKey() {
		return uniqueKey;
	}

	public void setAllele(String allele) {
		this.allele = allele;
	}

	public void setFragments(String fragments) {
		this.fragments = fragments;
	}

	public void setProbePolymorphismKey(int probePolymorphismKey) {
		this.probePolymorphismKey = probePolymorphismKey;
	}

	public void setStrains(String strains) {
		this.strains = strains;
	}

	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	@Override
	public String toString() {
		return "ProbePolymorphismDetails [uniqueKey=" + uniqueKey + ", probePolymorphismKey=" + probePolymorphismKey
				+ ", allele=" + allele + ", fragments=" + fragments + ", strains=" + strains + "]";
	}
}
