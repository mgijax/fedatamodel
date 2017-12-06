package mgi.frontend.datamodel;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;

/** This is the base class for terms related to other terms.
 * @author jsb
 * This class provides common fields for related-term objects, including
 * data for the term and about the relationship.  It handles the inverse
 * of the encoded relationships.  That is, if the relationship type is
 * "MP to EMAPA", the backward relationships go from the EMAPA terms to 
 * their corresponding MP terms.
 */

@Entity
@Table(name="term_to_term")
public class RelatedTermBackward {
    private int uniqueKey;
    private int termKey;
    private VocabTerm relatedTerm;
    private String rawRelationshipType;
    private String evidence;

    /*---- Getters and Setters ----*/

    @Id
    @Column(name="unique_key")
    public int getUniqueKey() {
		return uniqueKey;
    }

    public void setUniqueKey(int uniqueKey) {
    	this.uniqueKey = uniqueKey;
    }

    @Column(name="term_key_2")
    public int getTermKey() {
    	return termKey;
    }

    public void setTermKey(int termKey) {
    	this.termKey = termKey;
    }

	@ManyToOne (targetEntity=VocabTerm.class, fetch=FetchType.LAZY)
    @JoinColumn(name="term_key_1")
    public VocabTerm getRelatedTerm() {
		return relatedTerm;
	}

	public void setRelatedTerm(VocabTerm relatedTerm) {
		this.relatedTerm = relatedTerm;
	}

    @Column(name="relationship_type")
	public String getRawRelationshipType() {
		return rawRelationshipType;
	}

	public void setRawRelationshipType(String rawRelationshipType) {
		this.rawRelationshipType = rawRelationshipType;
	}

    @Column(name="evidence")
	public String getEvidence() {
		return evidence;
	}

	public void setEvidence(String evidence) {
		this.evidence = evidence;
	}
	
	@Transient
	public String getRelationshipType() {
		if (rawRelationshipType == null) { return rawRelationshipType; }
		
		String[] pieces = rawRelationshipType.split(" to ");
		if (pieces.length == 2) {
			return pieces[1] + " to " + pieces[0];
		}
		return rawRelationshipType;
	}
}
