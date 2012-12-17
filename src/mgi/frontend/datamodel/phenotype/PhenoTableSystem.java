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
@Table(name="phenotable_system")
public class PhenoTableSystem {

	private int phenoTableSystemKey;
	private int alleleKey;
	private String system;
	private int systemSeq;
	private List<PhenoTableTerm> phenoTableTerms;
	private List<PhenoTableSystemCell> systemCells;


    // ================= Getters ======================================== //

	@Id
	@Column(name="phenotable_system_key")
	public int getPhenoTableSystemKey() {
		return phenoTableSystemKey;
	}

	@Column(name="allele_key")
	public int getAlleleKey() {
		return alleleKey;
	}

	@Column(name="system")
	public String getSystem() {
		return system;
	}

	@Column(name="system_seq")
	public int getSystemSeq() {
		return systemSeq;
	}

	@OneToMany (targetEntity=PhenoTableTerm.class)
	@JoinColumn(name="phenotable_system_key")
	@OrderBy("termSeq")
	public List<PhenoTableTerm> getPhenoTableTerms() {
		this.phenoTableTerms.size(); // do not remove
		return phenoTableTerms;
	}
	@OneToMany (targetEntity=PhenoTableSystemCell.class,fetch=FetchType.EAGER)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name="phenotable_system_key")
	@OrderBy("cellSeq")
	public List<PhenoTableSystemCell> getCells() {
		return systemCells;
	}

	/* Transient Properties */
	
    @Transient
    public String getCssClass() {
        String cssClass = new String(this.system);
        cssClass = cssClass.replace("/", "_");
        cssClass = cssClass.replace("-", "_");
        cssClass = cssClass.replace(" ", "_");
        return cssClass + "_class";
    }

    @Transient
    public String getCssId() {
        String cssId = new String(this.system);
        cssId = cssId.replace("/", "_");
        cssId = cssId.replace("-", "_");
        cssId = cssId.replace(" ", "_");
        return cssId+ "_id";
    }

    // ================= Setters ======================================== //

	public void setPhenoTableSystemKey(int phenoTableSystemKey) {
		this.phenoTableSystemKey = phenoTableSystemKey;
	}

	public void setAlleleKey(int alleleKey) {
		this.alleleKey = alleleKey;
	}

	public void setSystem(String system) {
		this.system= system;
	}

	public void setSystemSeq(int systemSeq) {
		this.systemSeq = systemSeq;
	}

	public void setPhenoTableTerms(List<PhenoTableTerm> phenoTableTerms) {
		this.phenoTableTerms = phenoTableTerms;
	}
	
	public void setCells(List<PhenoTableSystemCell> systemCells)
	{
		this.systemCells = systemCells;
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
