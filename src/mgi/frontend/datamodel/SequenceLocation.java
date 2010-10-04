package mgi.frontend.datamodel;

import javax.persistence.*;

/**
 * SequenceLocation
 * @author mhall
 * This class extends the Location class, so most of its 
 * methods are taken from it.
 */
@Entity
@Table (name="sequence_location")
public class SequenceLocation extends Location {
	protected Integer sequenceKey;
	
    // ================= Getters and Setters ===================== //
	
	@Column(name="sequence_key")
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
