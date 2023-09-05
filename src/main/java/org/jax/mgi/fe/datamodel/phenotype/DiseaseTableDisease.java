package org.jax.mgi.fe.datamodel.phenotype;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;
import org.jax.mgi.fe.datamodel.VocabTerm;

@Entity
@Table(name="diseasetable_disease")
public class DiseaseTableDisease {

	private int diseaseTableDiseaseKey;
	private int alleleKey;
	private String disease;
	private int diseaseSeq;
	private String diseaseID;
	private VocabTerm vocabTerm;

	private boolean isHeader;
	private List<DiseaseTableDiseaseCell> diseaseCells;
	
	@Id
	@Column(name="diseasetable_disease_key")
	public int getDiseaseTableDiseaseKey() {
		return diseaseTableDiseaseKey;
	}

	@Column(name="allele_key")
	public int getAlleleKey() {
		return alleleKey;
	}

	@Column(name="disease")
	public String getDisease() {
		return disease;
	}

	@Column(name="disease_seq")
	public int getDiseaseSeq() {
		return diseaseSeq;
	}

	@Column(name="disease_id")
	public String getDiseaseID() {
		return diseaseID;
	}
	
	@Column(name="is_header")
	public boolean getIsHeader()
	{
		return isHeader;
	}
	
	@OneToOne (targetEntity=VocabTerm.class)
	@JoinColumn (name="term_key")
	public VocabTerm getVocabTerm() {
		return vocabTerm;
	}
	public void setVocabTerm(VocabTerm vocabTerm) {
		this.vocabTerm = vocabTerm;
	}

	@OneToMany (targetEntity=DiseaseTableDiseaseCell.class,fetch=FetchType.EAGER)
	@BatchSize(size=250)
	@JoinColumn(name="diseasetable_disease_key")
	@OrderBy("cellSeq")
	public List<DiseaseTableDiseaseCell> getCells() {
		return diseaseCells;
	}
	
	public void setDiseaseTableDiseaseKey(int diseaseTableDiseaseKey) {
		this.diseaseTableDiseaseKey = diseaseTableDiseaseKey;
	}

	public void setAlleleKey(int alleleKey) {
		this.alleleKey = alleleKey;
	}

	public void setDisease(String disease) {
		this.disease= disease;
	}

	public void setDiseaseSeq(int diseaseSeq) {
		this.diseaseSeq = diseaseSeq;
	}

	public void setDiseaseID(String diseaseID) {
		this.diseaseID = diseaseID;
	}

	public void setIsHeader(boolean isHeader)
	{
		this.isHeader=isHeader;
	}
	
	public void setCells(List<DiseaseTableDiseaseCell> diseaseCells)
	{
		this.diseaseCells = diseaseCells;
	}
}
