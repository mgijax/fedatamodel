package mgi.frontend.datamodel;

import javax.persistence.*;

@Entity
@Table (name="marker_note")
public class MarkerNote extends Note {
	protected Integer markerKey;

	@Column(name="marker_key")
	public Integer getMarkerKey() {
		return markerKey;
	}

	public void setMarkerKey(Integer markerKey) {
		this.markerKey = markerKey;
	}

	@Override
	public String toString() {
		return "MarkerNote ["
				+ (markerKey != null ? "markerKey=" + markerKey + ", " : "")
				+ (note != null ? "note=" + note + ", " : "")
				+ (noteType != null ? "noteType=" + noteType + ", " : "")
				+ "uniqueKey=" + uniqueKey + "]";
	}
}
