package mgi.frontend.datamodel.phenotype;

/**
 * This entity represents a term row in the genotype popup
 * from the allele phenotype summary table.
 * It knows its display order and indentation
 */
import java.util.*;
import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="mp_term")
public class MPTerm {
	private Integer mpTermKey;
	private String term;
	private String termId;
	private Integer termSeq;
	private Integer indentationDepth;
	private List<MPAnnot> annots;
	private List<MPReference> refs;

	/* Getters */
	@Column(name="term")
	public String getTerm() {
		return term;
	}

	@Column(name="term_id")
	public String getTermId() {
		return termId;
	}

	@Id
    @Column(name="mp_term_key")
	public Integer getTermKey() {
		return mpTermKey;
	}

	@Column(name="term_seq")
	public Integer getTermSeq() {
		return termSeq;
	}

	@Column(name="indentation_depth")
	public Integer getIndentationDepth() {
		return indentationDepth;
	}

	@OneToMany (targetEntity=MPAnnot.class,fetch=FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	@JoinColumn(name="mp_term_key")
	@OrderBy("annotSeq")
	public List<MPAnnot> getAnnots()
	{
		return annots;
	}

	public void setAnnots(List<MPAnnot> annots)
	{
		this.annots=annots;
	}
	
	@OneToMany (targetEntity=MPReference.class,fetch=FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	@JoinColumn(name="mp_term_key")
    public List<MPReference> getReferences() {
		return refs;
	}
	
	/* Transient Properties */
	
	@Transient
    public int getDisplayIndent() {
        int indentFactor = 30; //number of pixels for spacing
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
	
	/* Setters */
	public void setTerm(String term) {
		this.term = term;
	}
	public void setTermId(String termId) {
		this.termId = termId;
	}
	public void setTermKey(Integer termKey) {
		this.mpTermKey = termKey;
	}
	public void setTermSeq(Integer termSeq) {
		this.termSeq = termSeq;
	}
	public void setIndentationDepth(Integer indentationDepth) {
		this.indentationDepth = indentationDepth;
	}
	public void setReferences(List<MPReference> refs) {
		this.refs = refs;
	}
}
