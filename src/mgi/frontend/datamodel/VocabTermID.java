package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * VocabTermID
 * @author jsb
 * A generic object to hold VocabTerm IDs (primary and secondary).
 * It extends AccessionID, and inherits most of its methods from that base class.
 */
@Entity
@Table (name="term_id")
public class VocabTermID extends AccessionID {
	private Integer termKey;
	private VocabTerm vocabTerm;
	
    // ================= Getters and Setters ===================== //

	@Column(name="term_key")
	public Integer getTermKey() {
		return termKey;
	}

	public void setTermKey(Integer termKey) {
		this.termKey = termKey;
	}

	/** get a vocab term */
	@OneToOne (targetEntity=VocabTerm.class)
	@JoinColumn (name="term_key", insertable = false, updatable = false)
	public VocabTerm getVocabTerm() {
		return vocabTerm;
	}
	
	public void setVocabTerm(VocabTerm vocabTerm) {
		this.vocabTerm = vocabTerm;
	}
	
	@Override
	public String toString() {
		return "VocabTermID ["
				+ (accID != null ? "accID=" + accID + ", " : "")
				+ "isPreferred=" + isPreferred + ", isPrivate=" + isPrivate
				+ ", "
				+ (logicalDB != null ? "logicalDB=" + logicalDB + ", " : "")
				+ "uniqueKey=" + uniqueKey + "]";
	}
}
