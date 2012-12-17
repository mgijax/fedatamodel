package mgi.frontend.datamodel.phenotype;

/**
 * This class represents a system (or header term) row in a genotype popup 
 * from the allele pheno summary table
 */
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
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="mp_system")
public class MPSystem {
	private Integer mpSystemKey;
	private String system;
	private Integer systemSeq;
	private List<MPTerm> terms;
	
	/* Getters */
	@Id
    @Column(name="mp_system_key")
	public Integer getMpSystemKey() {
		return mpSystemKey;
	}
	
	@Column(name="system")
	public String getSystem() {
		return system;
	}
	
	@Column(name="system_seq")
	public Integer getSystemSeq() {
		return systemSeq;
	}
	
	@OneToMany (targetEntity=MPTerm.class,fetch=FetchType.EAGER)
	@BatchSize(size=250)
	@JoinColumn(name="mp_system_key")
	@OrderBy("termSeq")
	public List<MPTerm> getTerms() {
		return terms;
	}
	
	/* Transient properties */
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
	
	/* Setters */
	public void setMpSystemKey(Integer mpSystemKey) {
		this.mpSystemKey = mpSystemKey;
	}
	public void setSystem(String system) {
		this.system = system;
	}
	public void setSystemSeq(Integer systemSeq) {
		this.systemSeq = systemSeq;
	}
	public void setTerms(List<MPTerm> terms) {
		this.terms = terms;
	}
	
	
}
