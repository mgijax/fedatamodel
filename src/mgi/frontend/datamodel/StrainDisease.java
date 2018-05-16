package mgi.frontend.datamodel;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;

/**
 * StrainDisease
 */
@Entity
@Table(name="strain_disease")
public class StrainDisease {
    
	private int strainDiseaseKey;
	private int strainKey;
	private int diseaseKey;
	private String diseaseID;
	private String disease;
	private int sequenceNum;
	private List<StrainDiseaseToGenotype> joinedGenotypes;

    // ================= Getters and Setters ===================== //
	
	@Column(name="disease")
	public String getDisease() {
		return disease;
	}

	@Column(name="disease_id")
	public String getDiseaseID() {
		return diseaseID;
	}

	@Column(name="disease_key")
	public int getDiseaseKey() {
		return diseaseKey;
	}

	@OneToMany (targetEntity=StrainDiseaseToGenotype.class)
	@JoinColumn(name="strain_disease_key")
	@BatchSize(size=100)
	@OrderBy("sequenceNum")
	public List<StrainDiseaseToGenotype> getJoinedGenotypes() {
		return joinedGenotypes;
	}

    @Column(name="sequence_num")
    public int getSequenceNum() {
	return sequenceNum;
    }

	@Id
	@Column(name="strain_disease_key")
	public int getStrainDiseaseKey() {
		return strainDiseaseKey;
	}

	@Column(name="strain_key")
	public int getStrainKey() {
		return strainKey;
	}

	public void setDisease(String disease) {
		this.disease = disease;
	}

	public void setDiseaseID(String diseaseID) {
		this.diseaseID = diseaseID;
	}

	public void setDiseaseKey(int diseaseKey) {
		this.diseaseKey = diseaseKey;
	}

	public void setJoinedGenotypes(List<StrainDiseaseToGenotype> joinedGenotypes) {
		this.joinedGenotypes = joinedGenotypes;
	}

	public void setSequenceNum(int sequenceNum) {
    	this.sequenceNum = sequenceNum;
    }

	public void setStrainDiseaseKey(int strainDiseaseKey) {
		this.strainDiseaseKey = strainDiseaseKey;
	}

	public void setStrainKey(int strainKey) {
		this.strainKey = strainKey;
	}
	
	@Override
	public String toString() {
		return "StrainDisease [uniqueKey=" + strainDiseaseKey + ", strainKey=" + strainKey + ", diseaseKey=" + diseaseKey
				+ ", diseaseID=" + diseaseID + ", disease=" + disease + ", sequenceNum=" + sequenceNum + "]";
	}

	// get a flag indicating the value for the cell with the given genotype ID:
	// -1 : NOT annotation
	// 0 : no annotation (blank cell)
	// 1 : positive annotation
	@Transient
	public int getCellFlag(String genotypeID) {
		int flag = 0;
		for (StrainDiseaseToGenotype geno : this.getJoinedGenotypes()) {
			if (genotypeID.equals(geno.getGenotypeID())) {
				if (geno.getQualifier() == null) {
					flag = 1;
				} else {
					flag = -1;
				}
			}
		}
		return flag;
	}
}
