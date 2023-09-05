package org.jax.mgi.fe.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * DiseaseRowToModel
 * @author jsb
 * This object is a join table between a disease row (on the disease detail
 * page) and a mouse model for that human disease.
 */
@Entity
@Table(name="disease_row_to_model")
public class DiseaseRowToModel {

	private int uniqueKey;
	private int diseaseRowKey;
	private DiseaseModel diseaseModel;
	private int sequenceNum;

    // ================= Convenience Methods ===================== //

    // ================= Getters and Setters ===================== //

	@ManyToOne (targetEntity=DiseaseModel.class, fetch=FetchType.LAZY)
	@JoinColumn (name="disease_model_key")
	public DiseaseModel getDiseaseModel() {
		return diseaseModel;
	}

	@Id
	@Column(name="unique_key")
	public int getUniqueKey() {
		return uniqueKey;
	}

	@Column(name="disease_row_key")
	public int getDiseaseRowKey() {
		return diseaseRowKey;
	}

	@Column(name="sequence_num")
	public int getSequenceNum() {
		return sequenceNum;
	}

	public void setDiseaseModel(DiseaseModel diseaseModel) {
		this.diseaseModel = diseaseModel;
	}

	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	public void setDiseaseRowKey(int diseaseRowKey) {
		this.diseaseRowKey = diseaseRowKey;
	}

	public void setSequenceNum(int sequenceNum) {
		this.sequenceNum = sequenceNum;
	}

	@Override
	public String toString() {
		return "DiseaseRowToModel [uniqueKey="
				+ uniqueKey
				+ ", diseaseRowKey="
				+ diseaseRowKey
				+ ", sequenceNum="
				+ sequenceNum + "]";
	}
}
