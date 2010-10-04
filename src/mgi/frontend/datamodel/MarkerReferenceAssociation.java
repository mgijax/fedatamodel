package mgi.frontend.datamodel;

import javax.persistence.*;

/**
 * Implements the MarkerReferenecAssociation object.
 * @author mhall
 * This object mapped an early idea of a marker->Reference association.
 * 
 * This object extends the association base object, so most of its methods
 * are inherited from this.
 * 
 * This may be depreciated now.
 */

@Entity
@Table (name="markerToReference")
public class MarkerReferenceAssociation extends Association {
	
    protected Marker marker;
    
    // ================= Getters and Setters ===================== //
	
	/**
	 * Return the marker for this association.
	 * @return
	 */
	@ManyToOne (targetEntity=Marker.class, fetch=FetchType.LAZY)
	@JoinColumn (name="markerKey")
	public Marker getMarker() {
		return marker;
	}

	public void setMarker(Marker marker) {
		this.marker = marker;
	}

	@Override
	public String toString() {
		return "MarkerReferenceAssociation ["
				+ (qualifier != null ? "qualifier=" + qualifier + ", " : "")
				+ "sequenceNumFwd="
				+ ", uniqueKey=" + uniqueKey + "]";
	}
}
