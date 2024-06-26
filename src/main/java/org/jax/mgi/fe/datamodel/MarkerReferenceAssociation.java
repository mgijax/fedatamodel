package org.jax.mgi.fe.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
@Table (name="marker_to_reference")
public class MarkerReferenceAssociation extends Association {

    protected Marker marker;
    protected boolean isStrainSpecific;

    // ================= Getters and Setters ===================== //

	/**
	 * Return the marker for this association.
	 * @return
	 */
	@ManyToOne (targetEntity=Marker.class, fetch=FetchType.LAZY)
	@JoinColumn (name="marker_key")
	public Marker getMarker() {
		return marker;
	}

	@Column(name="is_strain_specific")
	public boolean isStrainSpecific () {
		return isStrainSpecific;
	}

	public void setMarker(Marker marker) {
		this.marker = marker;
	}

	public void setStrainSpecific (boolean isStrainSpecific) {
		this.isStrainSpecific = isStrainSpecific;
	}

	@Override
	public String toString() {
		return "MarkerReferenceAssociation [marker=" + marker
				+ ", isStrainSpecific=" + isStrainSpecific + ", qualifier="
				+ qualifier + ", reference=" + reference + ", uniqueKey="
				+ uniqueKey + "]";
	}
}
