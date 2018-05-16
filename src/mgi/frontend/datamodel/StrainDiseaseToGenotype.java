package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * StrainDiseaseToGenotype
 */
@Entity
@Table(name="strain_disease_to_genotype")
public class StrainDiseaseToGenotype {
    
	private int uniqueKey;
	private int strainDiseaseKey;
	private String qualifier;
	private int sequenceNum;
	private StrainGenotype genotype;

    // ================= Getters and Setters ===================== //
	
	@OneToOne (targetEntity=StrainGenotype.class)
	@JoinColumn(name="strain_genotype_key")
	public StrainGenotype getGenotype() {
		return genotype;
	}

    @Column(name="qualifier")
	public String getQualifier() {
		return qualifier;
	}

    @Column(name="sequence_num")
    public int getSequenceNum() {
	return sequenceNum;
    }

	@Column(name="strain_disease_key")
	public int getStrainDiseaseKey() {
		return strainDiseaseKey;
	}

	@Id
	@Column(name="unique_key")
	public int getUniqueKey() {
		return uniqueKey;
	}

	public void setGenotype(StrainGenotype genotype) {
		this.genotype = genotype;
	}

	public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}

	public void setSequenceNum(int sequenceNum) {
    	this.sequenceNum = sequenceNum;
    }

	public void setStrainDiseaseKey(int strainDiseaseKey) {
		this.strainDiseaseKey = strainDiseaseKey;
	}

	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	@Transient
	public String getGenotypeID() {
		return this.genotype.getGenotypeID();
	}
	
	@Transient
	public String getGenotypeAbbreviation() {
		return this.genotype.getAbbreviation();
	}

	@Override
	public String toString() {
		return "StrainDiseaseToGenotype [uniqueKey=" + uniqueKey + ", strainDiseaseKey=" + strainDiseaseKey
				+ ", qualifier=" + qualifier + ", sequenceNum=" + sequenceNum + "]";
	}
}
