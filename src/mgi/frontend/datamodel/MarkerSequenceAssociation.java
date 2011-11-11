package mgi.frontend.datamodel;

import javax.persistence.*;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

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
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class MarkerSequenceAssociation extends Association {
	
    protected Marker marker;
	protected Sequence sequence;
	
    // ================= Getters and Setters ===================== //
	
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
