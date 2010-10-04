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

    // ================= Getters and Setters ===================== //
	
	@Column(name="marker_key")
	public int getMarkerKey() {
		return markerKey;
	}

	public void setMarkerKey(int markerKey) {
		this.markerKey = markerKey;
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
