package mgi.frontend.datamodel;

import javax.persistence.*;

/**
 * MarkerID
 * @author mhall
 * This extends the general AccessionID Class.  As such most of its methods
 * come from that class.
 */
@Entity
@Table (name="marker_id")
public class MarkerID extends AccessionID {
	private int markerKey;
	private int sequenceNum;
	private int isForOtherDbSection;

    // ================= Getters and Setters ===================== //
	
	@Column(name="is_for_other_db_section")
	public int getIsForOtherDbSection() {
		return isForOtherDbSection;
	}

	@Column(name="marker_key")
	public int getMarkerKey() {
		return markerKey;
	}
	
	@Column(name="sequence_num")
	public int getSequenceNum() {
		return sequenceNum;
	}

	public void setIsForOtherDbSection(int isForOtherDbSection) {
		this.isForOtherDbSection = isForOtherDbSection;
	}

	public void setMarkerKey(int markerKey) {
		this.markerKey = markerKey;
	}

	public void setSequenceNum(int sequenceNum) {
		this.sequenceNum = sequenceNum;
	}

	@Override
	public String toString() {
		return "MarkerID [markerKey=" + markerKey + ", "
				+ (accID != null ? "accID=" + accID + ", " : "")
				+ "isPreferred=" + isPreferred + ", isPrivate=" + isPrivate
				+ ", "
				+ (logicalDB != null ? "logicalDB=" + logicalDB + ", " : "")
				+ "uniqueKey=" + uniqueKey + "]";
	}
}
