package mgi.frontend.datamodel;

import javax.persistence.*;

/**
 * MarkerLocation   
 * @author mhall
 * This class extends the Location class, so almost all of the location
 * methods come from it.
 */
@Entity
@Table (name="marker_location")
public class MarkerLocation extends Location {
	
    protected Integer markerKey;
	private int uniqueKey;
	
    // ================= Getters and Setters ===================== //
	
	@Column(name = "marker_key")
	public Integer getMarkerKey() {
		return markerKey;
	}

    @Id
    @Column(name="unique_key")
    public int getUniqueKey() {
        return uniqueKey;
    }
	
	public void setMarkerKey(Integer markerKey) {
		this.markerKey = markerKey;
	}

	public void setUniqueKey(int uniqueKey) {
        this.uniqueKey = uniqueKey;
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
