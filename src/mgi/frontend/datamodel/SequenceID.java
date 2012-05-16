package mgi.frontend.datamodel;

import javax.persistence.*;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * SequenceID
 * @author mhall
 * A generic object to hold all sequence ID objects.
 * It extends AccessionID, and thusly gets most of its methods
 * from that base class.
 */
@Entity
@Table (name="sequence_id")
public class SequenceID extends AccessionID {
	private int sequenceKey;
	
    // ================= Getters and Setters ===================== //

	@Column(name="sequence_key")
	public int getSequenceKey() {
		return sequenceKey;
	}

	public void setSequenceKey(int sequenceKey) {
		this.sequenceKey = sequenceKey;
	}

	@Override
	public String toString() {
		return "SequenceID [sequenceKey=" + sequenceKey + ", "
				+ (accID != null ? "accID=" + accID + ", " : "")
				+ "isPreferred=" + isPreferred + ", isPrivate=" + isPrivate
				+ ", "
				+ (logicalDB != null ? "logicalDB=" + logicalDB + ", " : "")
				+ "uniqueKey=" + uniqueKey + "]";
	}
}
