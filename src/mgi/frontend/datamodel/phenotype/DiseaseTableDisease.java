package mgi.frontend.datamodel.phenotype;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;

/**
 * PhenoTableSystem
 *
 * This object encapsulates a pheno system in the pheno-table
 */
@Entity
@Table(name="diseasetable_disease")
public class DiseaseTableDisease {

	private int diseaseTableDiseaseKey;
	private int alleleKey;
	private String disease;
	private int diseaseSeq;
	private String omimID;
	private boolean isHeader;
	private List<DiseaseTableDiseaseCell> diseaseCells;
	
    // ================= Getters ======================================== //

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

	@Column(name="omim_id")
	public String getOmimID() {
		return omimID;
	}
	
	@Column(name="is_header")
	public boolean getIsHeader()
	{
		return isHeader;
	}

	@OneToMany (targetEntity=DiseaseTableDiseaseCell.class,fetch=FetchType.EAGER)
	@BatchSize(size=250)
	@JoinColumn(name="diseasetable_disease_key")
	@OrderBy("cellSeq")
	public List<DiseaseTableDiseaseCell> getCells() {
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

	public void setOmimID(String omimID) {
		this.omimID= omimID;
	}

	public void setIsHeader(boolean isHeader)
	{
		this.isHeader=isHeader;
	}
	
	public void setCells(List<DiseaseTableDiseaseCell> diseaseCells)
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
