package mgi.frontend.datamodel;

import java.util.*;
import javax.persistence.*;

import org.hibernate.annotations.SortType;

/**
 * annotation object for gneo clusters in the HDP grid drill down.
 */

@Entity
@Table(name="hdp_genocluster_annotation")
public class HdpGenoClusterAnnotation implements HdpGridAnnotation{

    private int uniqueKey;
    private int genoClusterKey;
    private String term;
    private String termId;
    private String termType;
    private Integer annotationType;
    private String qualifier;
    private boolean hasBackgroundNote;


    // ================= Getters and Setters ===================== //

    @Id
    @Column(name="unique_key")
    public int getUniqueKey() {
		return uniqueKey;
	}
	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

    @Column(name="hdp_genocluster_key")
    public int getGenoClusterKey() {
		return genoClusterKey;
	}
	public void setGenoClusterKey(int genoClusterKey) {
		this.genoClusterKey = genoClusterKey;
	}

    @Column(name="term")
    public String getTerm() {
        return term;
    }
    public void setTerm(String term) {
        this.term = term;
    }

    @Column(name="term_id")
    public String getTermId() {
		return termId;
	}
    public void setTermId(String termId) {
		this.termId = termId;
	}
    
    @Column(name="term_type")
    public String getTermType() {
		return termType;
	}
    public void setTermType(String termType) {
		this.termType = termType;
	}
    
    @Column(name="annotation_type")
	public Integer getAnnotationType() {
		return annotationType;
	}
	public void setAnnotationType(Integer annotationType) {
		this.annotationType = annotationType;
	}
	
    @Column(name="qualifier_type")
	public String getQualifier() {
		return qualifier;
	}
	public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}
	
	@Column(name="has_backgroundnote")
	public boolean getHasBackgroundNote() {
		return hasBackgroundNote;
	}
	public void setHasBackgroundNote(boolean hasBackgroundNote) {
		this.hasBackgroundNote = hasBackgroundNote;
	}
	
	@Transient
	public String getTermIdentifier()
	{
		if("header".equalsIgnoreCase(this.termType))
		{
			return this.term;
		}
		return this.termId;
	}

	@Transient
	public void setAnnotCount(Integer annotCount) {
		// NOT APPLICABLE FOR THIS OBJECT TYPE
	}
	@Transient
	public Integer getAnnotCount() {
		// NOT APPLICABLE FOR THIS OBJECT TYPE
		return 0;
	}
	@Transient
	public void setHumanAnnotCount(Integer annotCount) {
		// NOT APPLICABLE FOR THIS OBJECT TYPE
	}
	@Transient
	public Integer getHumanAnnotCount() {
		// NOT APPLICABLE FOR THIS OBJECT TYPE
		return 0;
	}

    // ================= Convenience ================= //
	@Override
	public String toString() {
		return "HdpGenoClusterAnnotation [uniqueKey=" + uniqueKey
				+ ", gridClusterKey=" + genoClusterKey + ", term=" + term
				+ ", termId=" + termId + ", annotationType=" + annotationType
				+ "]";
	}
}
