package mgi.frontend.datamodel;

import java.util.ArrayList;
import java.util.Collections;
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
 * @author mhall
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
	private Set<MarkerID> ids;
	private List<MarkerLocation> locations;
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
	
    // ================= Getters and Setters ===================== //
	
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
	@Column(table="marker_counts", name="ortholog_count")
    @JoinColumn(name="marker_key")
    public Integer getCountOfOrthologs() {
        return countOfOrthologs;
    }
	@Column(table="marker_counts", name="reference_count")
	@JoinColumn(name="marker_key")
	public Integer getCountOfReferences() {
		return countOfReferences;
	}
	@Column(table="marker_counts", name="sequence_count")
	@JoinColumn(name="marker_key")
	public Integer getCountOfSequences() {
		return countOfSequences;
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
	@OrderBy ("sequenceNumFwd")
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

/*	@Transient
	public List<MarkerReferenceAssociation> getSortedReferenceAssociations() {
		List<MarkerReferenceAssociation> refs = 
			this.getReferenceAssociations();
				
		List<MarkerReferenceAssociation> refsCopy = 
			new ArrayList<MarkerReferenceAssociation>();
		Collections.copy (refsCopy, refs);
		Collections.sort (refsCopy, 
			new MarkerReferenceAssociationComparator (
				MarkerReferenceAssociationComparator.BY_REFERENCE));
		return refsCopy;
	}*/

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

	public void setAnnotations(List<MarkerAnnotation> annotations) {
		this.annotations = annotations;
	}
	
	public void setCountOfAlleles(Integer countOfAlleles) {
		this.countOfAlleles = countOfAlleles;
	}

	public void setCountOfGOTerms(Integer countOfGOTerms) {
        this.countOfGOTerms = countOfGOTerms;
    }

	public void setCountOfGXDAssays(Integer countOfGXDAssays) {
        this.countOfGXDAssays = countOfGXDAssays;
    }

	public void setCountOfOrthologs(Integer countOfOrthologs) {
        this.countOfOrthologs = countOfOrthologs;
    }

	public void setCountOfReferences(Integer countOfReferences) {
		this.countOfReferences = countOfReferences;
	}

	public void setCountOfSequences(Integer countOfSequences) {
        this.countOfSequences = countOfSequences;
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
