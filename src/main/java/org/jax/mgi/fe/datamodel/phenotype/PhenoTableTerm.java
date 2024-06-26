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
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;

/**
 * PhenoTableTerm
 *
 * This object encapsulates a pheno term for the pheno-table
 */
@Entity
@Table(name="phenotable_term")
public class PhenoTableTerm {

    private int phenoTableTermKey;
    private int phenoTableSystemKey;
    private String term;
    private String termId;
    private int indentationDepth;
    private int termSeq;
    private List<PhenoTableTermCell> termCells;


    // ================= Getters and Setters ============================ //

    @Id
    @Column(name="phenotable_term_key")
    public int getPhenoTableTermKey() {
        return phenoTableTermKey;
    }

    @Column(name="phenotable_system_key")
    public int getPhenoTableSystemKey() {
        return phenoTableSystemKey;
    }

    @Column(name="term")
    public String getTerm() {
        return term;
    }

    @Column(name="term_id")
    public String getTermId() {
        return termId;
    }

    @Column(name="indentation_depth")
    public int getIndentationDepth() {
        return indentationDepth;
    }

    @Column(name="term_seq")
    public int getTermSeq() {
        return termSeq;
    }
    
    @OneToMany (targetEntity=PhenoTableTermCell.class,fetch=FetchType.EAGER)
    @BatchSize(size=250)
	@JoinColumn(name="phenotable_term_key")
	@OrderBy("cellSeq")
	public List<PhenoTableTermCell> getCells() {
		return termCells;
	}
    
    /* Transient properties */

    @Transient
    public int getDisplayIndent() {
        int indentFactor = 20; //number of pixels for spacing
        return indentFactor * indentationDepth;
    }
    @Transient
    public String getCssClass() {
        String cssClass = new String(this.termId);
        cssClass = cssClass.replace("/", "_");
        cssClass = cssClass.replace("-", "_");
        cssClass = cssClass.replace(" ", "_");
        return cssClass + "_class";
    }

    @Transient
    public String getCssId() {
        String cssId = new String(this.termId);
        cssId = cssId.replace("/", "_");
        cssId = cssId.replace("-", "_");
        cssId = cssId.replace(" ", "_");
        return cssId+ "_id";
    }


    // ================= Setters ======================================== //

    public void setPhenoTableTermKey(int phenoTableTermKey) {
        this.phenoTableTermKey = phenoTableTermKey;
    }

    public void setPhenoTableSystemKey(int phenoTableSystemKey) {
        this.phenoTableSystemKey = phenoTableSystemKey;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public void setTermId(String termId) {
        this.termId = termId;
    }

    public void setIndentationDepth(int indentationDepth) {
        this.indentationDepth = indentationDepth;
    }

    public void setTermSeq(int termSeq) {
        this.termSeq = termSeq;
    }

    public void setCells(List<PhenoTableTermCell> termCells)
	{
		this.termCells = termCells;
	}

}
