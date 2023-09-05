package org.jax.mgi.fe.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * recombinase_other_allele
 * @author mhall, jsb
 *
 * Other alleles for a given allele system.
 *
 */
@Entity
@Table (name="recombinase_other_allele")
public class AlleleSystemOtherAllele {

	private int uniqueKey;
	private int alleleSystemKey;
	private int otherAlleleKey;
	private String otherAlleleID;
	private String otherAlleleSymbol;
	
	@Column(name="allele_system_key")
	public int getAlleleSystemKey() {
		return alleleSystemKey;
	}
	
	@Column(name="other_allele_id")
	public String getOtherAlleleID() {
		return otherAlleleID;
	}
	
	@Column(name="other_allele_key")
	public int getOtherAlleleKey() {
		return otherAlleleKey;
	}
	
	@Column(name="other_allele_symbol")
	public String getOtherAlleleSymbol() {
		return otherAlleleSymbol;
	}
	
	@Id
	@Column(name="unique_key")
	public int getUniqueKey() {
		return uniqueKey;
	}
	
	public void setAlleleSystemKey(int alleleSystemKey) {
		this.alleleSystemKey = alleleSystemKey;
	}
	
    public void setOtherAlleleID(String otherAlleleID) {
		this.otherAlleleID = otherAlleleID;
	}
    
    public void setOtherAlleleKey(int otherAlleleKey) {
		this.otherAlleleKey = otherAlleleKey;
	}
    
    public void setOtherAlleleSymbol(String otherAlleleSymbol) {
		this.otherAlleleSymbol = otherAlleleSymbol;
	}
    
    public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	@Override
	public String toString() {
		return "AlleleSystemOtherAllele [alleleSystemKey="
				+ alleleSystemKey
				+ ", "
				+ (otherAlleleID != null ? "otherAlleleID=" + otherAlleleID
						+ ", " : "")
				+ "otherAlleleKey="
				+ otherAlleleKey
				+ ", "
				+ (otherAlleleSymbol != null ? "otherAlleleSymbol="
						+ otherAlleleSymbol + ", " : "")
				+ "uniqueKey=" + uniqueKey + "]";
	}
}
