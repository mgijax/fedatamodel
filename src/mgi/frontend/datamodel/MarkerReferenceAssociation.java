package mgi.frontend.datamodel;

import javax.persistence.*;

@Entity
@Table (name="markerToReference")
public class MarkerReferenceAssociation extends Association implements SortableObject {
	protected Marker marker;

	public static String SYMBOL = "MarkerReferenceAssociation.Symbol";
	
	@ManyToOne (targetEntity=Marker.class, fetch=FetchType.LAZY)
	@JoinColumn (name="markerKey")
	public Marker getMarker() {
		return marker;
	}

	public void setMarker(Marker marker) {
		this.marker = marker;
	}

	@Override
	public Comparable getComparableValue(String fieldname) throws NoSuchFieldException {
		Comparable out;
		if (fieldname.equals(SYMBOL)) {
			out = this.marker.getComparableValue(Marker.SYMBOL);
		} else {
			out = super.getComparableValue(fieldname);
		}
		return out;
	}

	@Override
	public String toString() {
		return "MarkerReferenceAssociation ["
				+ (qualifier != null ? "qualifier=" + qualifier + ", " : "")
				+ "sequenceNumFwd="
				+ sequenceNumFwd + ", sequenceNumRev=" + sequenceNumRev
				+ ", uniqueKey=" + uniqueKey + "]";
	}
}
