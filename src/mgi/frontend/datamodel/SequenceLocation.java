package mgi.frontend.datamodel;

import javax.persistence.*;

@Entity
@Table (name="sequencelocation")
public class SequenceLocation extends Location implements SortableObject {
	protected Integer sequenceKey;

	public Integer getSequenceKey() {
		return sequenceKey;
	}

	public void setSequenceKey(Integer sequenceKey) {
		this.sequenceKey = sequenceKey;
	}

	@Override
	public String toString() {
		return "SequenceLocation ["
				+ (sequenceKey != null ? "sequenceKey=" + sequenceKey + ", "
						: "")
				+ (buildIdentifier != null ? "buildIdentifier="
						+ buildIdentifier + ", " : "")
				+ (chromosome != null ? "chromosome=" + chromosome + ", " : "")
				+ "endCoordinate="
				+ endCoordinate
				+ ", "
				+ (locationType != null ? "locationType=" + locationType + ", "
						: "")
				+ (mapUnits != null ? "mapUnits=" + mapUnits + ", " : "")
				+ (provider != null ? "provider=" + provider + ", " : "")
				+ "startCoordinate=" + startCoordinate + ", uniqueKey="
				+ uniqueKey + "]";
	}
}
