package mgi.frontend.datamodel;

import javax.persistence.*;

@Entity
@Table (name="markerID")
public class MarkerID extends AccessionID {
	private int markerKey;

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
