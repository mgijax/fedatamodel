package org.jax.mgi.fe.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * StrainSynonym
 */
@Entity
@Table(name="strain_synonym")
public class StrainSynonym {
    
	private int uniqueKey;
	private int strainKey;
	private String synonym;
	private String synonymType;
	private String jnumID;
	private int sequenceNum;

    // ================= Getters and Setters ===================== //
	
	@Column(name="jnum_id")
	public String getJnumID() {
		return jnumID;
	}

	@Column(name="strain_key")
	public int getStrainKey() {
		return strainKey;
	}

	public String getSynonym() {
		return synonym;
	}

	@Column(name="synonym_type")
	public String getSynonymType() {
		return synonymType;
	}

	@Id
	@Column(name="unique_key")
	public int getUniqueKey() {
		return uniqueKey;
	}

    @Column(name="sequence_num")
    public int getSequenceNum() {
	return sequenceNum;
    }

	public void setJnumID(String jnumID) {
		this.jnumID = jnumID;
	}

	public void setStrainKey(int strainKey) {
		this.strainKey = strainKey;
	}

	public void setSynonym(String synonym) {
		this.synonym = synonym;
	}

	public void setSynonymType(String synonymType) {
		this.synonymType = synonymType;
	}

	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	public void setSequenceNum(int sequenceNum) {
    	this.sequenceNum = sequenceNum;
    }

    
	@Override
	public String toString() {
		return "StrainSynonym ["
				+ (jnumID != null ? "jnumID=" + jnumID + ", " : "")
				+ "strainKey="
				+ strainKey
				+ ", "
				+ (synonym != null ? "synonym=" + synonym + ", " : "")
				+ (synonymType != null ? "synonymType=" + synonymType + ", "
						: "") + "uniqueKey=" + uniqueKey + "]";
	}
}
