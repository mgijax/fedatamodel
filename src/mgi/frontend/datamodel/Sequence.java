package mgi.frontend.datamodel;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;

@Entity
@Table (name="sequence")
@SecondaryTables (
		{ @SecondaryTable (name="sequence_counts", pkJoinColumns= {
			@PrimaryKeyJoinColumn(name="sequence_key", referencedColumnName="sequence_key") } ),
		  @SecondaryTable (name="sequence_gene_model", pkJoinColumns= {
		  	@PrimaryKeyJoinColumn(name="sequence_key", referencedColumnName="sequence_key") } ),
		  @SecondaryTable (name="sequence_gene_trap", pkJoinColumns= {
		  	@PrimaryKeyJoinColumn(name="sequence_key", referencedColumnName="sequence_key") } )	  
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
	private String sequenceDate;
	private String recordDate;
	private String primaryID;
	private String logicalDB;
	private String library;
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
	private Set<Marker> markers;
	//private Set<Probe> probes;
	
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
	@Column(name="sequence_key")
	public int getSequenceKey() {
		return sequenceKey;
	}

	public void setSequenceKey(int sequenceKey) {
		this.sequenceKey = sequenceKey;
	}

	@Column(name="sequence_type")
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

	@Column(name="sequence_date")
	public String getSequenceDate() {
		return sequenceDate;
	}

	public void setSequenceDate(String sequenceDate) {
		this.sequenceDate = sequenceDate;
	}

	@Column(name="record_date")
	public String getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}

	@Column(name="primary_id")
	public String getPrimaryID() {
		return primaryID;
	}

	public void setPrimaryID(String primaryID) {
		this.primaryID = primaryID;
	}

	@Column(name="logical_db")
	public String getLogicalDB() {
		return logicalDB;
	}

	public void setLogicalDB(String logicalDB) {
		this.logicalDB = logicalDB;
	}

	public String getLibrary() {
		return library;
	}

	public void setLibrary(String library) {
		this.library = library;
	}

	@Column(table="sequence_counts", name="marker_count")
	@JoinColumn(name="sequence_key")
	public Integer getCountOfMarkers() {
		return countOfMarkers;
	}

	public void setCountOfMarkers(Integer countOfMarkers) {
		this.countOfMarkers = countOfMarkers;
	}

	@Column(name="marker_type", table="sequence_gene_model")
	@JoinColumn(name="sequence_key")
	public String getMarkerType() {
		return markerType;
	}

	public void setMarkerType(String markerType) {
		this.markerType = markerType;
	}

	@Column(table="sequence_gene_model")
	@JoinColumn(name="sequence_key")
	public String getBiotype() {
		return biotype;
	}

	public void setBiotype(String biotype) {
		this.biotype = biotype;
	}

	@Column(name="exon_count", table="sequence_gene_model")
	@JoinColumn(name="sequence_key")
	public Integer getExonCount() {
		return exonCount;
	}

	public void setExonCount(Integer exonCount) {
		this.exonCount = exonCount;
	}

	@Column(name="transcript_count", table="sequence_gene_model")
	@JoinColumn(name="sequence_key")
	public Integer getTranscriptCount() {
		return transcriptCount;
	}

	public void setTranscriptCount(Integer transcriptCount) {
		this.transcriptCount = transcriptCount;
	}

	@Column(name="tag_method", table="sequence_gene_trap")
	@JoinColumn(name="sequence_key")
	public String getTagMethod() {
		return tagMethod;
	}

	public void setTagMethod(String tagMethod) {
		this.tagMethod = tagMethod;
	}

	@Column(name="vector_end", table="sequence_gene_trap")
	@JoinColumn(name="sequence_key")
	public String getVectorEnd() {
		return vectorEnd;
	}

	public void setVectorEnd(String vectorEnd) {
		this.vectorEnd = vectorEnd;
	}

	@Column(name="reverse_complement", table="sequence_gene_trap")
	@JoinColumn(name="sequence_key")
	public String getReverseComplement() {
		return reverseComplement;
	}

	public void setReverseComplement(String reverseComplement) {
		this.reverseComplement = reverseComplement;
	}

	@Column(name="good_hit_count", table="sequence_gene_trap")
	@JoinColumn(name="sequence_key")
	public Integer getGoodHitCount() {
		return goodHitCount;
	}

	public void setGoodHitCount(Integer goodHitCount) {
		this.goodHitCount = goodHitCount;
	}

	@Column(name="point_coordinate", table="sequence_gene_trap")
	@JoinColumn(name="sequence_key")
	public Float getPointCoordinate() {
		return pointCoordinate;
	}

	public void setPointCoordinate(Float pointCoordinate) {
		this.pointCoordinate = pointCoordinate;
	}

	@OneToMany (targetEntity=SequenceID.class)
	@JoinColumn(name="sequence_key")
	public Set<SequenceID> getIds() {
		return ids;
	}

	public void setIds(Set<SequenceID> ids) {
		this.ids = ids;
	}

    @OneToMany
    @JoinTable (name="reference_to_sequence",
            joinColumns=@JoinColumn(name="sequence_key"),
            inverseJoinColumns=@JoinColumn(name="reference_key")
            )
    @OrderBy("year, jnumNumeric")
    public List<Reference> getReferences() {
        return references;
    }

    public void setReferences(List<Reference> references) {
        this.references = references;
    }	
	
    @OneToMany (targetEntity=Marker.class)
    @JoinTable (name="marker_to_sequence",
            joinColumns=@JoinColumn(name="sequence_key"),
            inverseJoinColumns=@JoinColumn(name="marker_key")
            )
    public Set<Marker> getMarkers() {
        return markers;
    }

    public void setMarkers(Set<Marker> markers) {
        this.markers = markers;
    }    
    
    @OneToMany (targetEntity=SequenceSource.class)
    @JoinColumn(name="sequence_key")
    public List<SequenceSource> getSources() {
        return sources;
    }

    public void setSources(List<SequenceSource> sources) {
        this.sources = sources;
    }
    
    @OneToMany (targetEntity=SequenceLocation.class)
    @JoinColumn(name="sequence_key")
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
