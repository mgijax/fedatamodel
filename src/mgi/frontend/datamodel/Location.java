package mgi.frontend.datamodel;

import javax.persistence.*;

@MappedSuperclass
public class Location implements SortableObject {

	public static String SEQUENCE_NUM = "Location.SequenceNum";
	
	// subclasses will need to add their particular objects and getter/setter methods
	//protected int uniqueKey;
	protected int sequenceNum;
	protected String chromosome;
	//protected float cmOffset;
	//protected String cytogeneticOffset;
	protected Float startCoordinate;
	protected Float endCoordinate;
	protected String buildIdentifier;
	protected String locationType;
	protected String mapUnits;
	protected String provider;
	protected int uniqueKey;
	
	public Location() {}

	@Id
	@Column(name="unique_key")
	public int getUniqueKey() {
		return uniqueKey;
	}

	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	@Column(name="sequence_num")
	public int getSequenceNum() {
		return sequenceNum;
	}

	public void setSequenceNum(int sequenceNum) {
		this.sequenceNum = sequenceNum;
	}

	public String getChromosome() {
		return chromosome;
	}

	public void setChromosome(String chromosome) {
		this.chromosome = chromosome;
	}

/*	public float getCmOffset() {
		return cmOffset;
	}

	public void setCmOffset(float cmOffset) {
		this.cmOffset = cmOffset;
	}
*/
/*	public String getCytogeneticOffset() {
		return cytogeneticOffset;
	}

	public void setCytogeneticOffset(String cytogeneticOffset) {
		this.cytogeneticOffset = cytogeneticOffset;
	}*/

	@Column(name="start_coordinate")
	public Float getStartCoordinate() {
		return startCoordinate;
	}

	public void setStartCoordinate(Float startCoordinate) {
		this.startCoordinate = startCoordinate;
	}

	@Column(name="end_coordinate")
	public Float getEndCoordinate() {
		return endCoordinate;
	}

	public void setEndCoordinate(Float endCoordinate) {
		this.endCoordinate = endCoordinate;
	}
	
	@Column(name="build_identifier")
	public String getBuildIdentifier() {
		return buildIdentifier;
	}

	public void setBuildIdentifier(String buildIdentifier) {
		this.buildIdentifier = buildIdentifier;
	}

	@Column(name="location_type")
	public String getLocationType() {
		return locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	@Column(name="map_units")
	public String getMapUnits() {
		return mapUnits;
	}

	public void setMapUnits(String mapUnits) {
		this.mapUnits = mapUnits;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	@Override
	public Comparable getComparableValue(String fieldname) throws NoSuchFieldException {
		if (fieldname.equals (SEQUENCE_NUM)) {
			return this.getSequenceNum();
		}
		throw new NoSuchFieldException ("Unknown field: " + fieldname);
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
