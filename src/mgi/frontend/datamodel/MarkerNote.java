package mgi.frontend.datamodel;

import javax.persistence.*;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * MarkerNote
 * @author mhall
 * This extends the Note class, so most of its methods come from
 * that class.
 */
@Entity
@Table (name="marker_note")
public class MarkerNote extends Note {
	
    protected Integer markerKey;

    // ================= Getters and Setters ===================== //
    
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
