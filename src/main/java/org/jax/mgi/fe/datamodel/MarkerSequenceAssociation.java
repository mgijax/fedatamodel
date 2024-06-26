package org.jax.mgi.fe.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * MarkerSequenceAssociation
 * @author mhall
 * Captures Markers -> Sequences, with metadata about the assocation
 * available for review.
 *
 * This object implements the association object, so most of its methods have
 * been implemented there.
 */

@Entity
@Table (name="marker_to_sequence")
public class MarkerSequenceAssociation extends Association {

	protected int markerKey;
    protected Marker marker;
	protected Sequence sequence;

    // ================= Getters and Setters ===================== //

	@Column(name="marker_key",insertable=false,updatable=false)
	public int getMarkerKey()
	{
		return markerKey;
	}
	public void setMarkerKey(int markerKey)
	{
		this.markerKey=markerKey;
	}
	
	/**
	 * Return the actual marker for this relationship.
	 * @return
	 */

	@ManyToOne (targetEntity=Marker.class, fetch=FetchType.LAZY)
	@JoinColumn (name="marker_key")
	public Marker getMarker() {
		return marker;
	}

	/**
	 * Return the actual sequence object for this relationship
	 * @return
	 */

	@ManyToOne (targetEntity=Sequence.class, fetch=FetchType.LAZY)
	@JoinColumn (name="sequence_key")
	public Sequence getSequence() {
		return sequence;
	}

	public void setMarker(Marker marker) {
		this.marker = marker;
	}

	public void setSequence(Sequence sequence) {
		this.sequence = sequence;
	}

	@Override
	public String toString() {
		return "MarkerSequenceAssociation ["
				+ (marker != null ? "marker=" + marker + ", " : "")
				+ (sequence != null ? "sequence=" + sequence : "") + "]";
	}
}
