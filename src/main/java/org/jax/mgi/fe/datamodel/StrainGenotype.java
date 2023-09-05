package org.jax.mgi.fe.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * StrainGenotype
 */
@Entity
@Table(name="strain_genotype")
public class StrainGenotype {
    
	private int strainGenotypeKey;
	private int strainKey;
	private int genotypeKey;
	private String genotypeID;
	private String abbreviation;
	private int hasDisease;
	private int sequenceNum;

    // ================= Getters and Setters ===================== //
	

	@Column(name="abbreviation")
	public String getAbbreviation() {
		return abbreviation;
	}

	@Column(name="genotype_id")
	public String getGenotypeID() {
		return genotypeID;
	}

	@Column(name="genotype_key")
	public int getGenotypeKey() {
		return genotypeKey;
	}

	@Column(name="has_disease")
	public int getHasDisease() {
		return hasDisease;
	}

	@Column(name="sequence_num")
    public int getSequenceNum() {
	return sequenceNum;
    }

	@Id
	@Column(name="strain_genotype_key")
	public int getStrainGenotypeKey() {
		return strainGenotypeKey;
	}

	@Column(name="strain_key")
	public int getStrainKey() {
		return strainKey;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public void setGenotypeID(String genotypeID) {
		this.genotypeID = genotypeID;
	}

	public void setGenotypeKey(int genotypeKey) {
		this.genotypeKey = genotypeKey;
	}

    public void setHasDisease(int hasDisease) {
		this.hasDisease = hasDisease;
	}

	public void setSequenceNum(int sequenceNum) {
    	this.sequenceNum = sequenceNum;
    }

	public void setStrainGenotypeKey(int strainGenotypeKey) {
		this.strainGenotypeKey = strainGenotypeKey;
	}

	public void setStrainKey(int strainKey) {
		this.strainKey = strainKey;
	}

	@Override
	public String toString() {
		return "StrainGenotype [strainGenotypeKey=" + strainGenotypeKey + ", strainKey=" + strainKey + ", genotypeKey="
				+ genotypeKey + ", genotypeID=" + genotypeID + ", abbreviation=" + abbreviation + ", hasDisease="
				+ hasDisease + ", sequenceNum=" + sequenceNum + "]";
	}
}
