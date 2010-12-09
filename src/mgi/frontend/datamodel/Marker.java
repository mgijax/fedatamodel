package mgi.frontend.datamodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Marker
 * @author mhall, jsb
 * This is the core Marker object.
 */

@Entity
@Table(name="marker")
@SecondaryTables (
		{ @SecondaryTable (name="marker_counts", pkJoinColumns= {
			@PrimaryKeyJoinColumn(name="marker_key", referencedColumnName="marker_key") } ) 
		} )
@JsonIgnoreProperties({"ids", "synonyms", "references", "annotations", 
		"orthologousMarkers", "referenceAssociations", "notes", "sortedReferenceAssociations", 
		"comparableValue", "countOfReferences", "countOfSequences"})
public class Marker {

	private List<MarkerAnnotation> annotations;
	private Integer countOfAlleles;
	private Integer countOfGOTerms;
	private Integer countOfGXDAssays;
	private Integer countOfOrthologs;
	private Integer countOfReferences;
	private Integer countOfSequences;
	private Integer countOfGXDResults;
	private Integer countOfGXDLiterature;
	private Integer countOfGXDTissues;
	private Integer countOfGXDImages;
	private Integer countOfMappingExperiments;
	private Integer countOfRefSeqSequences;
	private Integer countOfUniProtSequences;
	private Integer countOfCdnaSources;
	private Integer countOfMicroarrayProbesets;
	private Set<MarkerID> ids;
	private List<MarkerLocation> locations;
	private List<MarkerCountSetItem> countSetItems;
	private int markerKey;
	private String markerSubtype;
	private String markerType;
	private String name;
	private List<MarkerNote> notes;
	private String organism;
	private Set<MarkerOrthology> orthologousMarkers;
    private String primaryID;
    private List<MarkerReferenceAssociation> referenceAssociations;	
	private List<Reference> references;
	private String status;
	private String symbol;
	private Set<MarkerSynonym> synonyms;
	private Integer countOfPhenotypeImages;
	private Integer countOfHumanDiseases;
	private Integer countOfAllelesWithHumanDiseases;
	private Integer countOfAntibodies;
	
    // ================= Instance Methods ===================== //
	
	/** used to make other convenience methods to extract only a certain type
	 * of annotations from the full List of annotations
	 */
	@Transient
	private List<MarkerAnnotation> filterAnnotations (String annotationType) {
		ArrayList<MarkerAnnotation> sublist = new ArrayList<MarkerAnnotation>();
		Iterator<MarkerAnnotation> it = this.getAnnotations().iterator();
		MarkerAnnotation annotation;
		
		while (it.hasNext()) {
			annotation = it.next();
			if (annotation.getAnnotationType().equals(annotationType)) {
				sublist.add(annotation);
			}
		}
		return sublist;
	}

	/** used to make other convenience methods to pull out certain sets
	 * of counts
	 */
	@Transient
	private List<MarkerCountSetItem> filterCountSetItems (String setType) {
		ArrayList<MarkerCountSetItem> sublist = new ArrayList<MarkerCountSetItem>();
		Iterator<MarkerCountSetItem> it = this.getCountSetItems().iterator();
		MarkerCountSetItem item;
		
		while (it.hasNext()) {
			item = it.next();
			if (item.getSetType().equals(setType)) {
				sublist.add(item);
			}
		}
		return sublist;
	}

	/** retrieve the first location (the highest priority one) with the given
	 * location type
	 */
	@Transient
	private MarkerLocation filterLocations(String locationType) {
		MarkerLocation loc = null;
		Iterator<MarkerLocation> it = this.getLocations().iterator();
		while (it.hasNext()) {
			loc = it.next();
			if (loc.getLocationType().equals(locationType)) {
				return loc;
			}
		}
		return null;
	}
	
	/** retrieve the IDs from the given logical database name
	 */
	@Transient
	private List<MarkerID> filterMarkerIDs (String logicalDatabase) {
		ArrayList<MarkerID> sublist = new ArrayList<MarkerID>();
		Iterator<MarkerID> it = this.getIds().iterator();
		MarkerID item;
		
		while (it.hasNext()) {
			item = it.next();
			if (item.getLogicalDB().equals(logicalDatabase)) {
				sublist.add(item);
			}
		}
		return sublist;
	}
	
	/** return the list of counts of alleles by type
	 */
	@Transient
	public List<MarkerCountSetItem> getAlleleCountsByType() {
		return this.filterCountSetItems("Alleles");
	}

	/**
	 * Returns a collection of marker annotation objects, which 
	 * extend the base annotation class.
	 */
	@OneToMany (targetEntity=MarkerAnnotation.class)
	@JoinColumn(name="marker_key")
	public List<MarkerAnnotation> getAnnotations() {
		return annotations;
	}

	@Column(table="marker_counts", name="allele_count")
    @JoinColumn(name="marker_key")
    public Integer getCountOfAlleles() {
        return countOfAlleles;
    }

	@Column(table="marker_counts", name="alleles_with_human_disease_count")
    @JoinColumn(name="marker_key")
	public Integer getCountOfAllelesWithHumanDiseases() {
		return countOfAllelesWithHumanDiseases;
	}

	@Column(table="marker_counts", name="antibody_count")
    @JoinColumn(name="marker_key")
	public Integer getCountOfAntibodies() {
		return countOfAntibodies;
	}

	@Column(table="marker_counts", name="cdna_source_count")
    @JoinColumn(name="marker_key")
	public Integer getCountOfCdnaSources() {
		return countOfCdnaSources;
	}
	
	@Column(table="marker_counts", name="go_term_count")
    @JoinColumn(name="marker_key")
    public Integer getCountOfGOTerms() {
        return countOfGOTerms;
    }
	
	@Column(table="marker_counts", name="gxd_assay_count")
    @JoinColumn(name="marker_key")
    public Integer getCountOfGXDAssays() {
        return countOfGXDAssays;
    }

	@Column(table="marker_counts", name="gxd_image_count")
    @JoinColumn(name="marker_key")
	public Integer getCountOfGXDImages() {
		return countOfGXDImages;
	}

	@Column(table="marker_counts", name="gxd_literature_count")
    @JoinColumn(name="marker_key")
	public Integer getCountOfGXDLiterature() {
		return countOfGXDLiterature;
	}

    @Column(table="marker_counts", name="gxd_result_count")
    @JoinColumn(name="marker_key")
	public Integer getCountOfGXDResults() {
		return countOfGXDResults;
	}

    @Column(table="marker_counts", name="gxd_tissue_count")
    @JoinColumn(name="marker_key")
	public Integer getCountOfGXDTissues() {
		return countOfGXDTissues;
	}
	
    @Column(table="marker_counts", name="human_disease_count")
    @JoinColumn(name="marker_key")
	public Integer getCountOfHumanDiseases() {
		return countOfHumanDiseases;
	}

    @Column(table="marker_counts", name="mapping_expt_count")
    @JoinColumn(name="marker_key")
	public Integer getCountOfMappingExperiments() {
		return countOfMappingExperiments;
	}
	
    @Column(table="marker_counts", name="microarray_probeset_count")
    @JoinColumn(name="marker_key")
	public Integer getCountOfMicroarrayProbesets() {
		return countOfMicroarrayProbesets;
	}
	
	@Column(table="marker_counts", name="ortholog_count")
    @JoinColumn(name="marker_key")
    public Integer getCountOfOrthologs() {
        return countOfOrthologs;
    }

    @Column(table="marker_counts", name="phenotype_image_count")
    @JoinColumn(name="marker_key")
	public Integer getCountOfPhenotypeImages() {
		return countOfPhenotypeImages;
	}

    @Column(table="marker_counts", name="reference_count")
	@JoinColumn(name="marker_key")
	public Integer getCountOfReferences() {
		return countOfReferences;
	}

    @Column(table="marker_counts", name="sequence_refseq_count")
	@JoinColumn(name="marker_key")
	public Integer getCountOfRefSeqSequences() {
		return countOfRefSeqSequences;
	}

    @Column(table="marker_counts", name="sequence_count")
	@JoinColumn(name="marker_key")
	public Integer getCountOfSequences() {
		return countOfSequences;
	}

	@Column(table="marker_counts", name="sequence_uniprot_count")
	@JoinColumn(name="marker_key")
	public Integer getCountOfUniProtSequences() {
		return countOfUniProtSequences;
	}
	
	/** returns a collection of marker count set items, which extend the
	 * base count set item class.
	 */
	@OneToMany (targetEntity=MarkerCountSetItem.class)
	@JoinColumn(name="marker_key")
	@OrderBy("sequenceNum")
	public List<MarkerCountSetItem> getCountSetItems() {
		return countSetItems;
	}

	/** get the Ensembl Gene Model ID for this marker, or null if none
	 */
	@Transient
	public MarkerID getEnsemblGeneModelID() {
		return this.getSingleID("Ensembl Gene Model");
	}

	/** get the Entrez Gene ID for this marker, or null if none
	 */
	@Transient
	public MarkerID getEntrezGeneID() {
		return this.getSingleID("Entrez Gene");
	}

	/** get the Gene Ontology (GO) annotations for this marker
	 */
	@Transient
	public List<MarkerAnnotation> getGOAnnotations () {
		return this.filterAnnotations("GO/Marker");
	}

	/** return the list of counts of GXD assays by assay type
	 */
	@Transient
	public List<MarkerCountSetItem> getGXDAssayCountsByType() {
		return this.filterCountSetItems("Expression Assays by Assay Type");
	}
	
	/** return the list of counts of GXD results by Theiler Stage
	 */
	@Transient
	public List<MarkerCountSetItem> getGXDResultCountsByStage() {
		return this.filterCountSetItems("Expression Results by Theiler Stage");
	}
	
	/** return the list of counts of GXD results by assay type
	 */
	@Transient
	public List<MarkerCountSetItem> getGXDResultCountsByType() {
		return this.filterCountSetItems("Expression Results by Assay Type");
	}
	
	/**
	 * Returns a collection representing all possible ID's for 
	 * this marker.
	 * @return
	 */
	@OneToMany (targetEntity=MarkerID.class)
	@JoinColumn(name="marker_key")
	public Set<MarkerID> getIds() {
		return ids;
	}
	
	/**
	 * Returns a collection representing all possible locations for 
	 * a given marker.
	 * @return
	 * 
	 * This collection is ordered by sequence number, with the location 
	 * that is most likely needed for a display page first. 
	 * 
	 */
	
	@OneToMany (targetEntity=MarkerLocation.class)
	@JoinColumn(name="marker_key")
	@OrderBy("sequenceNum")
	public List<MarkerLocation> getLocations() {
		return locations;
	}

	/** convenience method to pull the marker clip out of the list of notes
	 */
	@Transient
	public String getMarkerClip() {
		Iterator<MarkerNote> it = this.getNotes().iterator();
		MarkerNote note;

		while (it.hasNext()) {
			note = it.next();
			if (note.getNoteType().equals("marker clip")) {
				return note.getNote();
			}
		}
		return null;
	}

	@Id
	@Column(name="marker_key")
	public int getMarkerKey() {
		return markerKey;
	}
	
	@Column(name="marker_subtype")
	public String getMarkerSubtype() {
		return markerSubtype;
	}

	@Column(name="marker_type")
	public String getMarkerType() {
		return markerType;
	}

	/** return the list of counts of molecular reagents by type
	 */
	@Transient
	public List<MarkerCountSetItem> getMolecularReagentCountsByType() {
		return this.filterCountSetItems("Molecular reagents");
	}

	/** get the Mammalian Phenotype (MP) annotations for this marker's
	 *  alleles
	 */
	@Transient
	public List<MarkerAnnotation> getMPAnnotations () {
		return this.filterAnnotations("Mammalian Phenotype/Genotype");
	}

	public String getName() {
		return name;
	}

	/**
	 * A collection of possible marker notes for a marker.
	 * @return
	 * This object extends the note class.
	 */
	@OneToMany (targetEntity=MarkerNote.class)
	@JoinColumn(name="marker_key")
	public List<MarkerNote> getNotes() {
		return notes;
	}
	
    /** get the OMIM annotations for this marker's alleles
	 */
	@Transient
	public List<MarkerAnnotation> getOMIMAnnotations () {
		return this.filterAnnotations("OMIM/Genotype");
	} 	

	public String getOrganism() {
		return organism;
	}
	
    /**
	 * Return a collection of marker orthologs.
	 * @return
	 */
	@OneToMany
	@JoinColumn(name="mouse_marker_key")
	public Set<MarkerOrthology> getOrthologousMarkers() {
		return orthologousMarkers;
	}

	/** return the list of counts of polymorphisms by type
	 */
	@Transient
	public List<MarkerCountSetItem> getPolymorphismCountsByType() {
		return this.filterCountSetItems("Polymorphisms");
	}
	
    /** get the preferred centimorgan location for the marker, or null if none
	 */
	@Transient
	public MarkerLocation getPreferredCentimorgans() {
		return this.filterLocations("centimorgans");
	}
    
    /** get the preferred genome coordinates for the marker, or null if none
	 */
	@Transient
	public MarkerLocation getPreferredCoordinates() {
		return this.filterLocations("coordinates");
	}   
    
    @Column(name="primary_id")
	public String getPrimaryID() {
		return primaryID;
	}
	
	/**
	 * Returns reference associations.
	 * @return
	 * This might be no longer needed.
	 */
	@OneToMany (fetch=FetchType.LAZY)
	@JoinColumn(name="marker_key")
	public List<MarkerReferenceAssociation> getReferenceAssociations() {
		return referenceAssociations;
	}
    
    /**
	 * Return a collection of references
	 * @return
	 * This is order by year and then jnum
	 */
	@OneToMany
	@JoinTable (name="marker_to_reference",
			joinColumns=@JoinColumn(name="marker_key"),
			inverseJoinColumns=@JoinColumn(name="reference_key")
			)
	@OrderBy("year, jnumNumeric")
	public List<Reference> getReferences() {
		return references;
	}   
    
    /** get the RefSeq ID for this marker, or null if none
	 */
	@Transient
	public MarkerID getRefSeqID() {
		MarkerID id = null;
		Set<MarkerID> allIDs = this.getIds();
		Iterator<MarkerID> it = allIDs.iterator();
		while (it.hasNext()) {
			id = it.next();
			if (id.getLogicalDB().equals("RefSeq")) {
				return id;
			}
		}
		return null;
	}
    
    /** get the Genbank/RefSeq IDs for this marker
	 */
	@Transient
	public List<MarkerID> getSequenceIDs() {
		List<MarkerID> sublist = this.filterMarkerIDs("Sequence DB");
		sublist.addAll(this.filterMarkerIDs("RefSeq"));
		return sublist;
	}  

	/** get the a single ID for the given logical database, or null if none
	 */
	@Transient
	private MarkerID getSingleID (String logicalDatabase) {
		List<MarkerID> ids = this.filterMarkerIDs(logicalDatabase);
		if (ids.size() > 0) {
			return ids.get(0);
		}
		return null;
	}

	public String getStatus() {
		return status;
	}
	
	public String getSymbol() {
		return symbol;
	}

	@OneToMany (targetEntity=MarkerSynonym.class)
	@JoinColumn(name="marker_key")
	public Set<MarkerSynonym> getSynonyms() {
		return synonyms;
	}

	/** get the VEGA Gene Model ID for this marker, or null if none
	 */
	@Transient
	public MarkerID getVegaGeneModelID() {
		return this.getSingleID("VEGA Gene Model");
	}

	public void setAnnotations(List<MarkerAnnotation> annotations) {
		this.annotations = annotations;
	}

	public void setCountOfAlleles(Integer countOfAlleles) {
		this.countOfAlleles = countOfAlleles;
	}

	public void setCountOfAllelesWithHumanDiseases(
			Integer countOfAllelesWithHumanDiseases) {
		this.countOfAllelesWithHumanDiseases = countOfAllelesWithHumanDiseases;
	}

	public void setCountOfAntibodies(Integer countOfAntibodies) {
		this.countOfAntibodies = countOfAntibodies;
	}

	public void setCountOfCdnaSources(Integer countOfCdnaSources) {
		this.countOfCdnaSources = countOfCdnaSources;
	}

	public void setCountOfGOTerms(Integer countOfGOTerms) {
        this.countOfGOTerms = countOfGOTerms;
    }

	public void setCountOfGXDAssays(Integer countOfGXDAssays) {
        this.countOfGXDAssays = countOfGXDAssays;
    }

	public void setCountOfGXDImages(Integer countOfGXDImages) {
		this.countOfGXDImages = countOfGXDImages;
	}

	public void setCountOfGXDLiterature(Integer countOfGXDLiterature) {
		this.countOfGXDLiterature = countOfGXDLiterature;
	}

	public void setCountOfGXDResults(Integer countOfGXDResults) {
		this.countOfGXDResults = countOfGXDResults;
	}

	public void setCountOfGXDTissues(Integer countOfGXDTissues) {
		this.countOfGXDTissues = countOfGXDTissues;
	}

	public void setCountOfHumanDiseases(Integer countOfHumanDiseases) {
		this.countOfHumanDiseases = countOfHumanDiseases;
	}

	public void setCountOfMappingExperiments(Integer countOfMappingExperiments) {
		this.countOfMappingExperiments = countOfMappingExperiments;
	}

	public void setCountOfMicroarrayProbesets(Integer countOfMicroarrayProbesets) {
		this.countOfMicroarrayProbesets = countOfMicroarrayProbesets;
	}

	public void setCountOfOrthologs(Integer countOfOrthologs) {
        this.countOfOrthologs = countOfOrthologs;
    }

	public void setCountOfPhenotypeImages(Integer countOfPhenotypeImages) {
		this.countOfPhenotypeImages = countOfPhenotypeImages;
	}

	public void setCountOfReferences(Integer countOfReferences) {
		this.countOfReferences = countOfReferences;
	}

	public void setCountOfRefSeqSequences(Integer countOfRefSeqSequences) {
		this.countOfRefSeqSequences = countOfRefSeqSequences;
	}

	public void setCountOfSequences(Integer countOfSequences) {
        this.countOfSequences = countOfSequences;
    }

	public void setCountOfUniProtSequences(Integer countOfUniProtSequences) {
		this.countOfUniProtSequences = countOfUniProtSequences;
	}

	public void setCountSetItems(List<MarkerCountSetItem> countSetItems) {
		this.countSetItems = countSetItems;
	}

	public void setIds(Set<MarkerID> ids) {
		this.ids = ids;
	}

	public void setLocations(List<MarkerLocation> locations) {
		this.locations = locations;
	}

	public void setMarkerKey(int markerKey) {
		this.markerKey = markerKey;
	}

	public void setMarkerSubtype(String markerSubtype) {
		this.markerSubtype = markerSubtype;
	}

	public void setMarkerType(String markerType) {
		this.markerType = markerType;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNotes(List<MarkerNote> notes) {
		this.notes = notes;
	}

	public void setOrganism(String organism) {
		this.organism = organism;
	}

	public void setOrthologousMarkers(Set<MarkerOrthology> orthologousMarkers) {
		this.orthologousMarkers = orthologousMarkers;
	}

	public void setPrimaryID(String primaryID) {
		this.primaryID = primaryID;
	}

	public void setReferenceAssociations(
			List<MarkerReferenceAssociation> referenceAssociations) {
		this.referenceAssociations = referenceAssociations;
	}

	public void setReferences(List<Reference> references) {
		this.references = references;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public void setSynonyms(Set<MarkerSynonym> synonyms) {
		this.synonyms = synonyms;
	}

	@Override
	public String toString() {
		return "Marker ["
				+ (countOfReferences != null ? "countOfReferences="
						+ countOfReferences + ", " : "")
				+ (ids != null ? "ids=" + ids + ", " : "")
				+ "markerKey="
				+ markerKey
				+ ", "
				+ (markerSubtype != null ? "markerSubtype=" + markerSubtype
						+ ", " : "")
				+ (markerType != null ? "markerType=" + markerType + ", " : "")
				+ (name != null ? "name=" + name + ", " : "")
				+ (organism != null ? "organism=" + organism + ", " : "")
				+ (orthologousMarkers != null ? "orthologousMarkers="
						+ orthologousMarkers + ", " : "")
				+ (primaryID != null ? "primaryID=" + primaryID + ", " : "")
				+ (status != null ? "status=" + status + ", " : "")
				+ (symbol != null ? "symbol=" + symbol + ", " : "")
				+ (synonyms != null ? "synonyms=" + synonyms : "") + "]";
	}
}
