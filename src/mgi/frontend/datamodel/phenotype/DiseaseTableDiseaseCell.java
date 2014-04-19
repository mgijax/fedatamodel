package mgi.frontend.datamodel.phenotype;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * PhenoTableDiseaseCell
 *
 * This object represents a cell for a disease row in the phenotable grid
 */
@Entity
@Table(name="diseasetable_disease_cell")
public class DiseaseTableDiseaseCell {

	private int diseaseTableDiseaseCellKey;
	private String call;
	private String genotypeID;
	private int cellSeq;
	private int genotypeSeq;

    // ================= Getters ======================================== //

	@Id
	@Column(name="diseasetable_disease_cell_key")
	public int getDiseaseTableCellKey() {
		return diseaseTableDiseaseCellKey;
	}

	@Column(name="call")
	public String getCall() {
		return call;
	}

	@Column(name="cell_seq")
	public int getCellSeq() {
		return cellSeq;
	}
	
	@Column(name="genotype_id")
	public String getGenotypeID()
	{
		return genotypeID;
	}

	@Column(name="genotype_seq")
    public int getGenotypeSeq() {
        return genotypeSeq;
    }
    
	/* Transient Properties */
	
	/*
	 * This is the html escape version of an annotation call.
	 */
	@Transient 
	public String getCallString()
	{
		if(this.call.equals("NA"))
		{
			return " ";
		}
		if(this.call.equals("Y"))
		{
			return "&#8730;";
		}
		return this.call;
	}
	@Transient 
	public boolean getHasCall()
	{
		return call.equals("Y") || call.equals("N");
	}

    // ================= Setters ======================================== //

	public void setDiseaseTableCellKey(int diseaseTableDiseaseCellKey) {
		this.diseaseTableDiseaseCellKey = diseaseTableDiseaseCellKey;
	}

	public void setCall(String call) {
		this.call = call;
	}

	public void setGenotypeID(String genotypeID) {
		this.genotypeID = genotypeID;
	}

	public void setCellSeq(int cellSeq) {
		this.cellSeq = cellSeq;
	}
	public void setGenotypeSeq(int genotypeSeq) {
		this.genotypeSeq=genotypeSeq;
	}
}
