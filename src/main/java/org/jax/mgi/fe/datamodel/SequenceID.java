package org.jax.mgi.fe.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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
	private Integer sequenceKey;
	
    // ================= Getters and Setters ===================== //

	@Column(name="sequence_key")
	public Integer getSequenceKey() {
		return sequenceKey;
	}

	public void setSequenceKey(Integer sequenceKey) {
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
