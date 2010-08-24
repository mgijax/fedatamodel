package mgi.frontend.datamodel;

import javax.persistence.*;

@Entity
@Table (name="marker_location")
public class MarkerLocation extends Location implements SortableObject {
	protected Integer markerKey;
	private int uniqueKey;
	
	@Id
    @Column(name="unique_key")
    public int getUniqueKey() {
        return uniqueKey;
    }

    public void setUniqueKey(int uniqueKey) {
        this.uniqueKey = uniqueKey;
    }
	
	@Column(name = "marker_key")
	public Integer getMarkerKey() {
		return markerKey;
	}

	public void setMarkerKey(Integer markerKey) {
		this.markerKey = markerKey;
	}

	@Override
	public String toString() {
		return "MarkerLocation ["
				+ (markerKey != null ? "markerKey=" + markerKey + ", " : "")
				+ (buildIdentifier != null ? "buildIdentifier="
						+ buildIdentifier + ", " : "")
				+ (chromosome != null ? "chromosome=" + chromosome + ", " : "")
				+ ", "
				+ "endCoordinate="
				+ endCoordinate
				+ ", "
				+ (locationType != null ? "locationType=" + locationType + ", "
						: "")
				+ (mapUnits != null ? "mapUnits=" + mapUnits + ", " : "")
				+ (provider != null ? "provider=" + provider + ", " : "")
				+ "sequenceNum=" + sequenceNum + ", startCoordinate="
				+ startCoordinate + ", uniqueKey=" + uniqueKey + "]";
	}
}
