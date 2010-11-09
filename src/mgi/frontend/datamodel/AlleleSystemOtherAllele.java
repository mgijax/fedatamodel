package mgi.frontend.datamodel;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;

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
	private String systemKey;
	
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
	
	@Column(name="system_key")
	public String getSystemKey() {
		return systemKey;
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
    
    public void setSystemKey(String systemKey) {
		this.systemKey = systemKey;
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
				+ (systemKey != null ? "systemKey=" + systemKey + ", " : "")
				+ "uniqueKey=" + uniqueKey + "]";
	}
}
