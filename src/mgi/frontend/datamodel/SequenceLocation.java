package mgi.frontend.datamodel;

import javax.persistence.*;

@Entity
@Table (name="sequence_location")
public class SequenceLocation extends Location implements SortableObject {
	protected Integer sequenceKey;
	//private int uniqueKey;
	
/*	@Id
    public int getUniqueKey() {
        return uniqueKey;
    }

    public void setUniqueKey(int uniqueKey) {
        this.uniqueKey = uniqueKey;
    }*/
	
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
