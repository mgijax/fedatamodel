package mgi.frontend.datamodel;

import javax.persistence.*;

@Entity
@Table (name="markertosequence")
public class MarkerSequenceAssociation extends Association implements SortableObject {
	protected Marker marker;
	protected Sequence sequence;

	public static String SYMBOL = "MarkerReferenceAssociation.Symbol";
	public static String SEQ_ID = "MarkerReferenceAssociation.SequenceID";
	
	@ManyToOne (targetEntity=Marker.class, fetch=FetchType.LAZY)
	@JoinColumn (name="markerKey")
	public Marker getMarker() {
		return marker;
	}

	public void setMarker(Marker marker) {
		this.marker = marker;
	}

	@ManyToOne (targetEntity=Sequence.class, fetch=FetchType.LAZY)
	@JoinColumn (name="sequenceKey")
	public Sequence getSequence() {
		return sequence;
	}

	public void setSequence(Sequence sequence) {
		this.sequence = sequence;
	}

	@Override
	public Comparable getComparableValue(String fieldname) throws NoSuchFieldException {
		Comparable out;
		if (fieldname.equals(SYMBOL)) {
			out = this.marker.getComparableValue(Marker.SYMBOL);
		} else if (fieldname.equals(SEQ_ID)) {
			out = this.sequence.getComparableValue(Sequence.ID);
		} else {
			out = super.getComparableValue(fieldname);
		}
		return out;
	}

	@Override
	public String toString() {
		return "MarkerSequenceAssociation ["
				+ (marker != null ? "marker=" + marker + ", " : "")
				+ (sequence != null ? "sequence=" + sequence : "") + "]";
	}
}
