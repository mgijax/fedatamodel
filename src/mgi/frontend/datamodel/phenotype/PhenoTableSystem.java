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
import javax.persistence.Transient;

import mgi.frontend.datamodel.util.DatamodelUtils;

import org.hibernate.annotations.BatchSize;

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
	@BatchSize(size=100)
	@JoinColumn(name="phenotable_system_key")
	@OrderBy("termSeq")
	public List<PhenoTableTerm> getPhenoTableTerms() {
		this.phenoTableTerms.size(); // do not remove
		return phenoTableTerms;
	}
	@OneToMany (targetEntity=PhenoTableSystemCell.class,fetch=FetchType.EAGER)
	@BatchSize(size=250)
	@JoinColumn(name="phenotable_system_key")
	@OrderBy("cellSeq")
	public List<PhenoTableSystemCell> getCells() {
		return systemCells;
	}

	/* Transient Properties */
	
    @Transient
    public String getCssClass() {
        return DatamodelUtils.makeCssSafe(this.system) + "_class";
    }

    @Transient
    public String getCssId() {
        return DatamodelUtils.makeCssSafe(this.system) + "_id";
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

}
