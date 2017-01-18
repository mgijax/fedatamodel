package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
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
	
    // ================= Getters and Setters ===================== //

	@Column(name="term_key")
	public Integer getTermKey() {
		return termKey;
	}

	public void setTermKey(Integer termKey) {
		this.termKey = termKey;
	}

	@Override
	public String toString() {
		return "TermID ["
				+ (accID != null ? "accID=" + accID + ", " : "")
				+ "isPreferred=" + isPreferred + ", isPrivate=" + isPrivate
				+ ", "
				+ (logicalDB != null ? "logicalDB=" + logicalDB + ", " : "")
				+ "uniqueKey=" + uniqueKey + "]";
	}
}
