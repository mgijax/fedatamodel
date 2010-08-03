package mgi.frontend.datamodel;

import java.util.List;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table (name="sequence")
@SecondaryTables (
		{ @SecondaryTable (name="sequenceCounts", pkJoinColumns= {
			@PrimaryKeyJoinColumn(name="sequenceKey", referencedColumnName="sequenceKey") } ),
		  @SecondaryTable (name="sequenceGeneModel", pkJoinColumns= {
		  	@PrimaryKeyJoinColumn(name="sequenceKey", referencedColumnName="sequenceKey") } ),
		  @SecondaryTable (name="sequenceGeneTrap", pkJoinColumns= {
		  	@PrimaryKeyJoinColumn(name="sequenceKey", referencedColumnName="sequenceKey") } )	  
		} )
public class Sequence implements SortableObject {
	private int sequenceKey;	
	private String sequenceType;
	private String quality;
	private String status;
	private String provider;
	private String organism;
	private Integer length;
	private String description;
	private String version;
	private String division;
/*	private boolean isVirtual;*/
	private String sequenceDate;
	private String recordDate;
	private String primaryID;
	private String logicalDB;
/*	private String strain;
	private String tissue;
	private String age;
	private String sex;*/
	private String library;
/*	private String cellLine;*/
	private Integer countOfMarkers;
	private String markerType;
	private String biotype;
	private Integer exonCount;
	private Integer transcriptCount;
	private String tagMethod;
	private String vectorEnd;
	private String reverseComplement;
	private Integer goodHitCount;
	private Float pointCoordinate;
	private Set<SequenceID> ids;
	private List<SequenceSource> sources;
	private List<Reference> references;
	private List<SequenceLocation> locations;
	
/*	private List<MarkerSequenceAssociation> markerAssociations;*/
/*	private List<SequenceLocation> locations;*/
	
	public static String PROVIDER = "Sequence.Provider";
	public static String LENGTH = "Sequence.Length";
	public static String ID = "Sequence.ID";
	public static String SEQUENCE_TYPE = "Sequence.Type";
	public static String TISSUE = "Sequence.Tissue";
	public static String STRAIN = "Sequence.Strain";
	
	public Sequence() {}
	
	@Id
	public int getSequenceKey() {
		return sequenceKey;
	}

	public void setSequenceKey(int sequenceKey) {
		this.sequenceKey = sequenceKey;
	}

	public String getSequenceType() {
		return sequenceType;
	}

	public void setSequenceType(String sequenceType) {
		this.sequenceType = sequenceType;
	}

	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getOrganism() {
		return organism;
	}

	public void setOrganism(String organism) {
		this.organism = organism;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

/*	public boolean isVirtual() {
		return isVirtual;
	}

	public void setVirtual(boolean isVirtual) {
		this.isVirtual = isVirtual;
	}*/

	public String getSequenceDate() {
		return sequenceDate;
	}

	public void setSequenceDate(String sequenceDate) {
		this.sequenceDate = sequenceDate;
	}

	public String getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}

	public String getPrimaryID() {
		return primaryID;
	}

	public void setPrimaryID(String primaryID) {
		this.primaryID = primaryID;
	}

	public String getLogicalDB() {
		return logicalDB;
	}

	public void setLogicalDB(String logicalDB) {
		this.logicalDB = logicalDB;
	}

/*    @Column(table="sequencesource", name="strain")
    @JoinColumn(name="sequenceKey")	
	public String getStrain() {
		return strain;
	}

	public void setStrain(String strain) {
		this.strain = strain;
	}

    @Column(table="sequencesource", name="tissue")
    @JoinColumn(name="sequenceKey")
	public String getTissue() {
		return tissue;
	}

	public void setTissue(String tissue) {
		this.tissue = tissue;
	}

    @Column(table="sequencesource", name="age")
    @JoinColumn(name="sequenceKey")	
	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

    @Column(table="sequencesource", name="sex")
    @JoinColumn(name="sequenceKey")	
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
*/
	public String getLibrary() {
		return library;
	}

	public void setLibrary(String library) {
		this.library = library;
	}

/*    @Column(table="sequenceSource", name="celline")
    @JoinColumn(name="sequenceKey")
	public String getCellLine() {
		return cellLine;
	}

	public void setCellLine(String cellLine) {
		this.cellLine = cellLine;
	}*/

	@Column(table="sequenceCounts", name="markerCount")
	@JoinColumn(name="sequenceKey")
	public Integer getCountOfMarkers() {
		return countOfMarkers;
	}

	public void setCountOfMarkers(Integer countOfMarkers) {
		this.countOfMarkers = countOfMarkers;
	}

	@Column(table="sequenceGeneModel")
	@JoinColumn(name="sequenceKey")
	public String getMarkerType() {
		return markerType;
	}

	public void setMarkerType(String markerType) {
		this.markerType = markerType;
	}

	@Column(table="sequenceGeneModel")
	@JoinColumn(name="sequenceKey")
	public String getBiotype() {
		return biotype;
	}

	public void setBiotype(String biotype) {
		this.biotype = biotype;
	}

	@Column(table="sequenceGeneModel")
	@JoinColumn(name="sequenceKey")
	public Integer getExonCount() {
		return exonCount;
	}

	public void setExonCount(Integer exonCount) {
		this.exonCount = exonCount;
	}

	@Column(table="sequenceGeneModel")
	@JoinColumn(name="sequenceKey")
	public Integer getTranscriptCount() {
		return transcriptCount;
	}

	public void setTranscriptCount(Integer transcriptCount) {
		this.transcriptCount = transcriptCount;
	}

	@Column(table="sequenceGeneTrap")
	@JoinColumn(name="sequenceKey")
	public String getTagMethod() {
		return tagMethod;
	}

	public void setTagMethod(String tagMethod) {
		this.tagMethod = tagMethod;
	}

	@Column(table="sequenceGeneTrap")
	@JoinColumn(name="sequenceKey")
	public String getVectorEnd() {
		return vectorEnd;
	}

	public void setVectorEnd(String vectorEnd) {
		this.vectorEnd = vectorEnd;
	}

	@Column(table="sequenceGeneTrap")
	@JoinColumn(name="sequenceKey")
	public String getReverseComplement() {
		return reverseComplement;
	}

	public void setReverseComplement(String reverseComplement) {
		this.reverseComplement = reverseComplement;
	}

	@Column(table="sequenceGeneTrap")
	@JoinColumn(name="sequenceKey")
	public Integer getGoodHitCount() {
		return goodHitCount;
	}

	public void setGoodHitCount(Integer goodHitCount) {
		this.goodHitCount = goodHitCount;
	}

	@Column(table="sequenceGeneTrap")
	@JoinColumn(name="sequenceKey")
	public Float getPointCoordinate() {
		return pointCoordinate;
	}

	public void setPointCoordinate(Float pointCoordinate) {
		this.pointCoordinate = pointCoordinate;
	}

	@OneToMany (targetEntity=SequenceID.class)
	@JoinColumn(name="sequenceKey")
	public Set<SequenceID> getIds() {
		return ids;
	}

	public void setIds(Set<SequenceID> ids) {
		this.ids = ids;
	}

    @OneToMany
    @JoinTable (name="referenceToSequence",
            joinColumns=@JoinColumn(name="sequenceKey"),
            inverseJoinColumns=@JoinColumn(name="referenceKey")
            )
    @OrderBy("year, jnumNumeric")
    public List<Reference> getReferences() {
        return references;
    }

    public void setReferences(List<Reference> references) {
        this.references = references;
    }	
	
/*	public List<MarkerSequenceAssociation> getMarkerAssociations() {
		return markerAssociations;
	}

	public void setMarkerAssociations(
			List<MarkerSequenceAssociation> markerAssociations) {
		this.markerAssociations = markerAssociations;
	}*/

    @OneToMany (targetEntity=SequenceSource.class)
    @JoinColumn(name="sequenceKey")
    public List<SequenceSource> getSources() {
        return sources;
    }

    public void setSources(List<SequenceSource> sources) {
        this.sources = sources;
    }
    
    @OneToMany (targetEntity=SequenceLocation.class)
    @JoinColumn(name="sequenceKey")
    @OrderBy("sequenceNum")
	public List<SequenceLocation> getLocations() {
		return locations;
	}

	public void setLocations(List<SequenceLocation> locations) {
		this.locations = locations;
	}

	@Override
	public Comparable getComparableValue(String fieldname) throws NoSuchFieldException {
		Comparable value;
		
		if (fieldname.equals(PROVIDER)) {
			value = this.getProvider();
		} else if (fieldname.equals(ID)) {
			value = this.getPrimaryID();
		} else if (fieldname.equals(LENGTH)) {
			value = this.getLength();
		} else if (fieldname.equals(SEQUENCE_TYPE)) {
			value = this.getSequenceType();
		} else {
			throw new NoSuchFieldException("Unknown field: " + fieldname);
		}
		return value;
	}

	@Override
	public String toString() {
		return "Sequence ["
				+ (description != null ? "description=" + description + ", "
						: "")
				+ (primaryID != null ? "primaryID=" + primaryID : "") + "]";
	}
}
