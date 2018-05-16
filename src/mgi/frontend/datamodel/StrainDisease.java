package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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

    @Column(name="sequence_num")
    public int getSequenceNum() {
	return sequenceNum;
    }

	@Column(name="strain_key")
	public int getStrainKey() {
		return strainKey;
	}

	@Id
	@Column(name="strain_disease_key")
	public int getStrainDiseaseKey() {
		return strainDiseaseKey;
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

	public void setSequenceNum(int sequenceNum) {
    	this.sequenceNum = sequenceNum;
    }

	public void setStrainKey(int strainKey) {
		this.strainKey = strainKey;
	}

	public void setStrainDiseaseKey(int strainDiseaseKey) {
		this.strainDiseaseKey = strainDiseaseKey;
	}

	@Override
	public String toString() {
		return "StrainDisease [uniqueKey=" + strainDiseaseKey + ", strainKey=" + strainKey + ", diseaseKey=" + diseaseKey
				+ ", diseaseID=" + diseaseID + ", disease=" + disease + ", sequenceNum=" + sequenceNum + "]";
	}
}
