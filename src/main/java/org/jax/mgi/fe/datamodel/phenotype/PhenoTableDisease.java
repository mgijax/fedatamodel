package org.jax.mgi.fe.datamodel.phenotype;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 * PhenoTableSystem
 *
 * This object encapsulates a pheno system in the pheno-table
 */
@Entity
@Table(name="phenotable_disease")
public class PhenoTableDisease {

	private int phenoTableDiseaseKey;
	private int alleleKey;
	private String disease;
	private int diseaseSeq;
	private String doID;
	private boolean isHeader;
	private List<PhenoTableDiseaseCell> diseaseCells;
	
    // ================= Getters ======================================== //

	@Id
	@Column(name="phenotable_disease_key")
	public int getPhenoTableDiseaseKey() {
		return phenoTableDiseaseKey;
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

	@Column(name="do_id")
	public String getDoID() {
		return doID;
	}
	
	@Column(name="is_header")
	public boolean getIsHeader()
	{
		return isHeader;
	}

	@OneToMany (targetEntity=PhenoTableDiseaseCell.class,fetch=FetchType.EAGER)
	@JoinColumn(name="phenotable_disease_key")
	@OrderBy("cellSeq")
	public List<PhenoTableDiseaseCell> getCells() {
		return diseaseCells;
	}
	
//	@OneToMany (targetEntity=PhenoTableTerm.class,fetch=FetchType.EAGER)
//	@JoinColumn(name="phenotable_system_key")
//	@Fetch(FetchMode.SELECT)
//	@OrderBy("termSeq")
//	public List<PhenoTableTerm> getPhenoTableTerms() {
//		return phenoTableTerms;
//	}

//	@OneToMany (targetEntity=PhenoTableSystemCell.class,fetch=FetchType.EAGER)
//	@JoinColumn(name="phenotable_system_key")
//	@Fetch(FetchMode.SELECT)
//	@OrderBy("cellSeq")
//	public List<PhenoTableSystemCell> getCells() {
//		return systemCells;
//	}

	/* Transient Properties */
/*	
    @Transient
    public String getCssClass() {
        String cssClass = new String(this.system);
        cssClass = cssClass.replace("/", "_");
        cssClass = cssClass.replace("-", "_");
        cssClass = cssClass.replace(" ", "_");
        return cssClass + "_class";
    }
*/
	
/*	
    @Transient
    public String getCssId() {
        String cssId = new String(this.system);
        cssId = cssId.replace("/", "_");
        cssId = cssId.replace("-", "_");
        cssId = cssId.replace(" ", "_");
        return cssId+ "_id";
    }
*/
	
    // ================= Setters ======================================== //

	public void setPhenoTableDiseaseKey(int phenoTableDiseaseKey) {
		this.phenoTableDiseaseKey = phenoTableDiseaseKey;
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

	public void setDoID(String doID) {
		this.doID= doID;
	}

	public void setIsHeader(boolean isHeader)
	{
		this.isHeader=isHeader;
	}
	
	public void setCells(List<PhenoTableDiseaseCell> diseaseCells)
	{
		this.diseaseCells = diseaseCells;
	}
}
