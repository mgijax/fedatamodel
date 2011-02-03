package mgi.frontend.datamodel;

import javax.persistence.*;

/**
 * Base object for location. 
 * @author mhall
 *
 */

@MappedSuperclass
public class Location {
	
	// subclasses will need to add their particular objects and getter/setter methods

	protected String buildIdentifier;
	protected String chromosome;
	protected Double endCoordinate;
	protected String locationType;
	protected String mapUnits;
	protected String provider;
	protected int sequenceNum;
	protected Double startCoordinate;
	protected int uniqueKey;

	protected String strand;
	
    // ================= Getters and Setters ===================== //

	@Column(name="build_identifier")
	public String getBuildIdentifier() {
		return buildIdentifier;
	}

	public String getChromosome() {
		return chromosome;
	}

	@Column(name="end_coordinate")
	public Double getEndCoordinate() {
		return endCoordinate;
	}

	@Column(name="location_type")
	public String getLocationType() {
		return locationType;
	}

	@Column(name="map_units")
	public String getMapUnits() {
		return mapUnits;
	}

	@Column(name="strand")
	public String getStrand() {
		return strand;
	}

	public String getProvider() {
		return provider;
	}

	/**
	 * Returns the sequence number.  These have been placed into the database in 
	 * a specific order, with the one needed for display getting the first sequence
	 * number.
	 * @return
	 */
	
	@Column(name="sequence_num")
	public int getSequenceNum() {
		return sequenceNum;
	}

	@Column(name="start_coordinate")
	public Double getStartCoordinate() {
		return startCoordinate;
	}

	@Id
	@Column(name="unique_key")
	public int getUniqueKey() {
		return uniqueKey;
	}
		
	public void setBuildIdentifier(String buildIdentifier) {
		this.buildIdentifier = buildIdentifier;
	}
	
	public void setChromosome(String chromosome) {
		this.chromosome = chromosome;
	}

	public void setEndCoordinate(Double endCoordinate) {
		this.endCoordinate = endCoordinate;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	public void setMapUnits(String mapUnits) {
		this.mapUnits = mapUnits;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public void setStrand(String strand) {
		this.strand = strand;
	}

	public void setSequenceNum(int sequenceNum) {
		this.sequenceNum = sequenceNum;
	}

	public void setStartCoordinate(Double startCoordinate) {
		this.startCoordinate = startCoordinate;
	}

	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

    @Override
	public String toString() {
		return "Location ["
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
				+ startCoordinate + "]";
	}
}
