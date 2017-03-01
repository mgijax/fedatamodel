package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Is: a summary of RI/RC data for a mapping experiment
 */
@Entity
@Table(name="marker_polymorphism_allele")
public class MarkerPolymorphismAllele {
	private int uniqueKey;
	private int polymorphismKey;
	private String allele;
	private String fragments;
	private String strains;
	private int sequenceNum;
	
    // ================= Getters and Setters ===================== //
	
	@Column(name="allele")
	public String getAllele() {
		return allele;
	}
	
	@Column(name="fragments")
	public String getFragments() {
		return fragments;
	}
	
	@Column(name="polymorphism_key")
	public int getPolymorphismKey() {
		return polymorphismKey;
	}
	
	@Column(name="sequence_num")
	public int getSequenceNum() {
		return sequenceNum;
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
	public void setPolymorphismKey(int polymorphismKey) {
		this.polymorphismKey = polymorphismKey;
	}
	public void setSequenceNum(int sequenceNum) {
		this.sequenceNum = sequenceNum;
	}
	public void setStrains(String strains) {
		this.strains = strains;
	}
	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}
	@Override
	public String toString() {
		return "MarkerPolymorphismAllele [uniqueKey=" + uniqueKey + ", polymorphismKey=" + polymorphismKey + ", allele="
				+ allele + ", fragments=" + fragments + ", strains=" + strains + ", sequenceNum=" + sequenceNum + "]";
	}
}
