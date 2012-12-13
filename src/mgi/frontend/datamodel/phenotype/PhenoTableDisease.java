package mgi.frontend.datamodel.phenotype;

import java.util.*;
import javax.persistence.*;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
	private String omimID;
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

	@Column(name="omim_id")
	public String getOmimID() {
		return omimID;
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

	public void setOmimID(String omimID) {
		this.omimID= omimID;
	}

	public void setIsHeader(boolean isHeader)
	{
		this.isHeader=isHeader;
	}
	
	public void setCells(List<PhenoTableDiseaseCell> diseaseCells)
	{
		this.diseaseCells = diseaseCells;
	}


//	@Override
//	public String toString() {
//		return "MarkerOrthology [mouseMarkerKey="
//				+ mouseMarkerKey
//				+ ", otherMarkerKey="
//				+ otherMarkerKey
//				+ ", "
//				+ (otherOrganism != null ? "otherOrganism=" + otherOrganism
//						+ ", " : "")
//				+ (otherSymbol != null ? "otherSymbol=" + otherSymbol + ", "
//						: "") + "uniqueKey=" + uniqueKey + "]";
//	}
}
