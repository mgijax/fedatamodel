package org.jax.mgi.fe.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * DiseaseGroupRow
 * This object represents one group-to-row object of a disease group on the disease browser.
 */
@Entity
@Table(name="disease_group_row")
public class DiseaseGroupRow {

	private int diseaseGroupRowKey;
	private String disease;
	private String annotatedDisease;
	private DiseaseRow diseaseRow;

 
    // ================= Getters and Setters ===================== //


	@Id
	@Column(name="disease_group_row_key")
	public int getDiseaseGroupRowKey() {
		return diseaseGroupRowKey;
	}

	@Column(name="disease")
	public String getDisease() {
		return disease;
	}

	@Column(name="annotated_disease")
	public String getAnnotatedDisease() {
		return annotatedDisease;
	}

	@ManyToOne (targetEntity=DiseaseRow.class, fetch=FetchType.LAZY)
	@JoinColumn (name="disease_row_key")
	public DiseaseRow getDiseaseRow() {
		return diseaseRow;
	}

	public void setDiseaseGroupRowKey(int diseaseGroupRowKey) {
		this.diseaseGroupRowKey = diseaseGroupRowKey;
	}

	public void setDiseaseRow(DiseaseRow diseaseRow) {
		this.diseaseRow = diseaseRow;
	}

	public void setDisease(String disease) {
		this.disease = disease;
	}
	public void setAnnotatedDisease(String annotatedDisease) {
		this.annotatedDisease = annotatedDisease;
	}
	
	@Override
	public String toString() {
		return "DiseaseGroupRow [diseaseGroupRowKey="
				+ diseaseGroupRowKey
				+ ", annotatedDisease="
				+ annotatedDisease
				+ "]";
	}
}
