package org.jax.mgi.fe.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * StrainID
 * This extends the general AccessionID Class.  As such most of its methods come from that class.
 */
@Entity
@Table (name="strain_id")
public class StrainID extends AccessionID {
	private int strainKey;
	private int sequenceNum;

    // ================= Getters and Setters ===================== //
	
	@Column(name="strain_key")
	public int getStrainKey() {
		return strainKey;
	}

	@Column(name="sequence_num")
	public int getSequenceNum() {
		return sequenceNum;
	}

	public void setStrainKey(int strainKey) {
		this.strainKey = strainKey;
	}

	public void setSequenceNum(int sequenceNum) {
		this.sequenceNum = sequenceNum;
	}

	@Override
	public String toString() {
		return "StrainID [strainKey=" + strainKey + ", "
				+ (accID != null ? "accID=" + accID + ", " : "")
				+ "isPreferred=" + isPreferred + ", isPrivate=" + isPrivate
				+ ", "
				+ (logicalDB != null ? "logicalDB=" + logicalDB + ", " : "")
				+ "uniqueKey=" + uniqueKey + "]";
	}
}
