package org.jax.mgi.fe.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="recombinase_other_system")
public class AlleleSystemOtherSystem {

    private int uniqueKey;
	private int alleleSystemKey;
	private String alleleID;
    private String otherSystem;
    
    // ================= Getters and Setters ===================== //
    
    @Column(name="allele_id")
    public String getAlleleID() {
		return alleleID;
	}
    
    @Column(name="allele_system_key")
    public int getAlleleSystemKey() {
        return alleleSystemKey;
    }

	@Column(name="other_system")
    public String getOtherSystem() {
        return otherSystem;
    }

    @Id
    @Column(name="unique_key")
	public int getUniqueKey() {
		return uniqueKey;
	}

	public void setAlleleID(String alleleID) {
		this.alleleID = alleleID;
	}

	public void setAlleleSystemKey(int alleleSystemKey) {
        this.alleleSystemKey = alleleSystemKey;
    }

	public void setOtherSystem(String system) {
        this.otherSystem = system;
    }
        
    public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	@Override
	public String toString() {
		return "AlleleSystemOtherSystem ["
				+ (alleleID != null ? "alleleID=" + alleleID + ", " : "")
				+ "alleleSystemKey="
				+ alleleSystemKey
				+ ", "
				+ (otherSystem != null ? "otherSystem=" + otherSystem + ", "
						: "") 
				+ ", uniqueKey=" + uniqueKey + "]";
	}
}
