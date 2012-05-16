package mgi.frontend.datamodel;

import java.util.Iterator;
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
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

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
public class Sequence {

	private String biotype;
	private Integer countOfMarkers;
	private Integer countOfProbes;
	private String description;
	private String division;
	private Integer exonCount;
	private Integer goodHitCount;
	private Set<SequenceID> ids;
	private Integer length;
	private String library;
	private List<SequenceLocation> locations;
	private String logicalDB;
	private Set<Marker> markers;
	private String markerType;
	private String organism;
	private Float pointCoordinate;
	private String primaryID;
	private Set<Probe> probes;
	private String provider;
	private String quality;
	private String recordDate;
	private List<Reference> references;
	private String reverseComplement;
	private String sequenceDate;
	private int sequenceKey;
	private String sequenceType;
	private List<SequenceSource> sources;
	private List<SequenceCloneCollection> cloneCollections;
	private String status;
	private String tagMethod;
	private Integer transcriptCount;
	private String vectorEnd;
	private String version;
	private int hasCloneCollection;

    // ================= Getters and Setters ===================== //

	@Column(table="sequence_gene_model")
	@JoinColumn(name="sequence_key")
	public String getBiotype() {
		return biotype;
	}

	@OneToMany (targetEntity=SequenceCloneCollection.class)
    @JoinColumn(name="sequence_key")
    @OrderBy("collection")
	public List<SequenceCloneCollection> getCloneCollections() {
		return cloneCollections;
	}

	@Column(table="sequence_counts", name="marker_count")
	@JoinColumn(name="sequence_key")
	public Integer getCountOfMarkers() {
		return countOfMarkers;
	}

	@Column(table="sequence_counts", name="probe_count")
	@JoinColumn(name="sequence_key")
	public Integer getCountOfProbes() {
		return countOfProbes;
	}

	public String getDescription() {
		return description;
	}

	public String getDivision() {
		return division;
	}

	@Column(name="exon_count", table="sequence_gene_model")
	@JoinColumn(name="sequence_key")
	public Integer getExonCount() {
		return exonCount;
	}

	@Column(name="good_hit_count", table="sequence_gene_trap")
	@JoinColumn(name="sequence_key")
	public Integer getGoodHitCount() {
		return goodHitCount;
	}

	@Column(name="has_clone_collection")
	public int getHasCloneCollection() {
		return hasCloneCollection;
	}

	/**
	 * Return the a collection of all possible sequence IDs
	 * for a sequence.
	 *
	 * @return
	 */
	@OneToMany (targetEntity=SequenceID.class)
	@JoinColumn(name="sequence_key")
	public Set<SequenceID> getIds() {
		return ids;
	}

	public Integer getLength() {
		return length;
	}

	/**
	 * Returns a string given the sequence type.
	 * @return
	 */
	@Transient
	public String getLengthUnit() {

        String lengthUnit = "bp";
        if (sequenceType.equals("Polypeptide")) {
			lengthUnit = "aa";
		}
        return lengthUnit;
	}

	public String getLibrary() {
		return library;
	}

	/**
	 * Return the location for sequences, ordered by sequence number
	 * @return
	 * The locations are placed into the database in a specific order
	 * with the preferred location being first, second most second.. etc.
	 */

	@OneToMany (targetEntity=SequenceLocation.class)
    @JoinColumn(name="sequence_key")
    @OrderBy("sequenceNum")
	public List<SequenceLocation> getLocations() {
		return locations;
	}

	@Column(name="logical_db")
	public String getLogicalDB() {
		return logicalDB;
	}

	/**
	 * Return a collection of markers
	 * @return
	 */
	@OneToMany (targetEntity=Marker.class)
    @JoinTable (name="marker_to_sequence",
            joinColumns=@JoinColumn(name="sequence_key"),
            inverseJoinColumns=@JoinColumn(name="marker_key")
            )
    @OrderBy("symbol")
    public Set<Marker> getMarkers() {
        return markers;
    }

	@Column(name="marker_type", table="sequence_gene_model")
	@JoinColumn(name="sequence_key")
	public String getMarkerType() {
		return markerType;
	}

	public String getOrganism() {
		return organism;
	}

	@Column(name="point_coordinate", table="sequence_gene_trap")
	@JoinColumn(name="sequence_key")
	public Float getPointCoordinate() {
		return pointCoordinate;
	}

	@Column(name="primary_id")
	public String getPrimaryID() {
		return primaryID;
	}

	/**
	 * Return a collection of probes
	 * @return
	 */
	@OneToMany (targetEntity=Probe.class)
    @JoinTable (name="probe_to_sequence",
            joinColumns=@JoinColumn(name="sequence_key"),
            inverseJoinColumns=@JoinColumn(name="probe_key")
            )
    @OrderBy("cloneid")
	public Set<Probe> getProbes() {
        return probes;
    }

	public String getProvider() {
		return provider;
	}

	public String getQuality() {
		return quality;
	}

	@Column(name="record_date")
	public String getRecordDate() {
		return recordDate;
	}

	/**
	 * Return a collection of references, ordered by
	 * year and then jnum.
	 * @return
	 */
	@OneToMany
    @JoinTable (name="reference_to_sequence",
            joinColumns=@JoinColumn(name="sequence_key"),
            inverseJoinColumns=@JoinColumn(name="reference_key")
            )
    @OrderBy("jnumNumeric")
    public List<Reference> getReferences() {
        return references;
    }

	@Column(name="reverse_complement", table="sequence_gene_trap")
	@JoinColumn(name="sequence_key")
	public String getReverseComplement() {
		return reverseComplement;
	}

	@Column(name="sequence_date")
	public String getSequenceDate() {
		return sequenceDate;
	}

	@Id
	@Column(name="sequence_key")
	public int getSequenceKey() {
		return sequenceKey;
	}

	@Column(name="sequence_type")
	public String getSequenceType() {
		return sequenceType;
	}

	@OneToMany (targetEntity=SequenceSource.class)
    @JoinColumn(name="sequence_key")
    public List<SequenceSource> getSources() {
        return sources;
    }

	public String getStatus() {
		return status;
	}

	@Column(name="tag_method", table="sequence_gene_trap")
	@JoinColumn(name="sequence_key")
	public String getTagMethod() {
		return tagMethod;
	}

	@Column(name="transcript_count", table="sequence_gene_model")
	@JoinColumn(name="sequence_key")
	public Integer getTranscriptCount() {
		return transcriptCount;
	}

	@Column(name="vector_end", table="sequence_gene_trap")
	@JoinColumn(name="sequence_key")
	public String getVectorEnd() {
		return vectorEnd;
	}

	public String getVersion() {
		return version;
	}

	/** returns true if the sequence or any of its sources uses raw
	 * values.  (These values are marked by an asterisk and indicate
	 * those that could not be mapped to an MGI controlled vocabulary.)
	 */
	@Transient
	public boolean hasRawValues() {
		if ((this.library != null) && this.library.endsWith("*")) {
			return true;
		}
		if ((this.organism != null) && this.organism.endsWith("*")) {
			return true;
		}

		List<SequenceSource> sources = this.getSources();
		Iterator<SequenceSource> it = sources.iterator();
		SequenceSource source = null;

		while (it.hasNext()) {
			source = it.next();
			if (source.hasRawValues()) {
				return true;
			}
		}
		return false;
	}

	public void setBiotype(String biotype) {
		this.biotype = biotype;
	}

	public void setCloneCollections(List<SequenceCloneCollection> cloneCollections) {
		this.cloneCollections = cloneCollections;
	}

	public void setCountOfMarkers(Integer countOfMarkers) {
		this.countOfMarkers = countOfMarkers;
	}

	public void setCountOfProbes(Integer countOfProbes) {
		this.countOfProbes = countOfProbes;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public void setExonCount(Integer exonCount) {
		this.exonCount = exonCount;
	}

	public void setGoodHitCount(Integer goodHitCount) {
		this.goodHitCount = goodHitCount;
	}

	public void setHasCloneCollection(int hasCloneCollection) {
		this.hasCloneCollection = hasCloneCollection;
	}

	public void setIds(Set<SequenceID> ids) {
		this.ids = ids;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public void setLibrary(String library) {
		this.library = library;
	}

	public void setLocations(List<SequenceLocation> locations) {
		this.locations = locations;
	}

	public void setLogicalDB(String logicalDB) {
		this.logicalDB = logicalDB;
	}

	public void setMarkers(Set<Marker> markers) {
        this.markers = markers;
    }

	public void setMarkerType(String markerType) {
		this.markerType = markerType;
	}

	public void setOrganism(String organism) {
		this.organism = organism;
	}

	public void setPointCoordinate(Float pointCoordinate) {
		this.pointCoordinate = pointCoordinate;
	}

	public void setPrimaryID(String primaryID) {
		this.primaryID = primaryID;
	}

	public void setProbes(Set<Probe> probes) {
        this.probes = probes;
    }

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}

    public void setReferences(List<Reference> references) {
        this.references = references;
    }

    public void setReverseComplement(String reverseComplement) {
		this.reverseComplement = reverseComplement;
	}

    public void setSequenceDate(String sequenceDate) {
		this.sequenceDate = sequenceDate;
	}

    public void setSequenceKey(int sequenceKey) {
		this.sequenceKey = sequenceKey;
	}

    public void setSequenceType(String sequenceType) {
		this.sequenceType = sequenceType;
	}

    public void setSources(List<SequenceSource> sources) {
        this.sources = sources;
    }

    public void setStatus(String status) {
		this.status = status;
	}

	public void setTagMethod(String tagMethod) {
		this.tagMethod = tagMethod;
	}

    public void setTranscriptCount(Integer transcriptCount) {
		this.transcriptCount = transcriptCount;
	}

    public void setVectorEnd(String vectorEnd) {
		this.vectorEnd = vectorEnd;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "Sequence ["
				+ (description != null ? "description=" + description + ", "
						: "")
				+ (primaryID != null ? "primaryID=" + primaryID : "") + "]";
	}
}
