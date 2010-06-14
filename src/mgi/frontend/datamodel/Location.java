package mgi.frontend.datamodel;

import javax.persistence.*;

@MappedSuperclass
public class Location implements SortableObject {

	public static String SEQUENCE_NUM = "Location.SequenceNum";
	
	// subclasses will need to add their particular objects and getter/setter methods
	protected int uniqueKey;
	protected int sequenceNum;
	protected String chromosome;
	protected float cmOffset;
	protected String cytogeneticOffset;
	protected float startCoordinate;
	protected float endCoordinate;
	protected String buildIdentifier;
	protected String locationType;
	protected String mapUnits;
	protected String provider;
	
	public Location() {}

	@Id
	public int getUniqueKey() {
		return uniqueKey;
	}

	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

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

	public float getCmOffset() {
		return cmOffset;
	}

	public void setCmOffset(float cmOffset) {
		this.cmOffset = cmOffset;
	}

	public String getCytogeneticOffset() {
		return cytogeneticOffset;
	}

	public void setCytogeneticOffset(String cytogeneticOffset) {
		this.cytogeneticOffset = cytogeneticOffset;
	}

	public float getStartCoordinate() {
		return startCoordinate;
	}

	public void setStartCoordinate(float startCoordinate) {
		this.startCoordinate = startCoordinate;
	}

	public float getEndCoordinate() {
		return endCoordinate;
	}

	public void setEndCoordinate(float endCoordinate) {
		this.endCoordinate = endCoordinate;
	}

	public String getBuildIdentifier() {
		return buildIdentifier;
	}

	public void setBuildIdentifier(String buildIdentifier) {
		this.buildIdentifier = buildIdentifier;
	}

	public String getLocationType() {
		return locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

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
				+ "cmOffset="
				+ cmOffset
				+ ", "
				+ (cytogeneticOffset != null ? "cytogeneticOffset="
						+ cytogeneticOffset + ", " : "")
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
