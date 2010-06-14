package mgi.frontend.datamodel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name="marker")
@SecondaryTables (
		{ @SecondaryTable (name="markerCounts", pkJoinColumns= {
			@PrimaryKeyJoinColumn(name="markerKey", referencedColumnName="markerKey") } ) 
		} )
public class Marker implements SortableObject {
	private int markerKey;
	private String symbol;
	private String name;
	private String markerType;
	private String markerSubtype;
	private String organism;
	private String primaryID;
	private String status;
	private Set<MarkerID> ids;
	private Set<MarkerSynonym> synonyms;
	private List<Reference> references;
	private List<MarkerAnnotation> annotations;
	private Set<MarkerOrthology> orthologousMarkers;
	private List<MarkerReferenceAssociation> referenceAssociations;
	private List<MarkerNote> notes;
	private List<MarkerLocation> locations;
	private Integer countOfReferences;
	private Integer countOfSequences;
	
	public static String SYMBOL = "Marker.Symbol";
	public static String NAME = "Marker.Name";
	public static String ID = "Marker.ID";
	public static String MARKER_TYPE = "Marker.Type";
	
	public Marker() {}
	
	@Id
	public int getMarkerKey() {
		return markerKey;
	}
	public void setMarkerKey(int markerKey) {
		this.markerKey = markerKey;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMarkerType() {
		return markerType;
	}
	public void setMarkerType(String markerType) {
		this.markerType = markerType;
	}
	public String getMarkerSubtype() {
		return markerSubtype;
	}
	public void setMarkerSubtype(String markerSubtype) {
		this.markerSubtype = markerSubtype;
	}
	public String getOrganism() {
		return organism;
	}
	public void setOrganism(String organism) {
		this.organism = organism;
	}
	public String getPrimaryID() {
		return primaryID;
	}
	public void setPrimaryID(String primaryID) {
		this.primaryID = primaryID;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	@OneToMany (targetEntity=MarkerID.class)
	@JoinColumn(name="markerKey")
	public Set<MarkerID> getIds() {
		return ids;
	}

	public void setIds(Set<MarkerID> ids) {
		this.ids = ids;
	}

	@OneToMany (targetEntity=MarkerSynonym.class)
	@JoinColumn(name="markerKey")
	public Set<MarkerSynonym> getSynonyms() {
		return synonyms;
	}

	public void setSynonyms(Set<MarkerSynonym> synonyms) {
		this.synonyms = synonyms;
	}

	@OneToMany
	@JoinTable (name="markerToReference",
			joinColumns=@JoinColumn(name="markerKey"),
			inverseJoinColumns=@JoinColumn(name="referenceKey")
			)
	@OrderBy("year, jnumNumeric")
	public List<Reference> getReferences() {
		return references;
	}

	public void setReferences(List<Reference> references) {
		this.references = references;
	}
	
	@OneToMany
	@JoinColumn(name="mouseMarkerKey")
	public Set<MarkerOrthology> getOrthologousMarkers() {
		return orthologousMarkers;
	}

	public void setOrthologousMarkers(Set<MarkerOrthology> orthologousMarkers) {
		this.orthologousMarkers = orthologousMarkers;
	}

	@Transient
	public List<MarkerReferenceAssociation> getSortedReferenceAssociations() {
		List<MarkerReferenceAssociation> refs = 
			this.getReferenceAssociations();
		
		// note that we need to copy the list and sort the copy, rather
		// than sorting the original in-place
		
		List<MarkerReferenceAssociation> refsCopy = 
			new ArrayList<MarkerReferenceAssociation>();
		Collections.copy (refsCopy, refs);
		Collections.sort (refsCopy, 
			new MarkerReferenceAssociationComparator (
				MarkerReferenceAssociationComparator.BY_REFERENCE));
		return refsCopy;
	}
	
	@OneToMany (fetch=FetchType.LAZY)
	@JoinColumn(name="markerKey")
	@OrderBy ("sequenceNumFwd")
	public List<MarkerReferenceAssociation> getReferenceAssociations() {
		return referenceAssociations;
	}

	public void setReferenceAssociations(
			List<MarkerReferenceAssociation> referenceAssociations) {
		this.referenceAssociations = referenceAssociations;
	}

	@Override
	public Comparable getComparableValue(String fieldname) throws NoSuchFieldException {
		Comparable value;
		
		if (fieldname.equals(SYMBOL)) {
			value = this.getSymbol().toLowerCase();
		} else if (fieldname.equals(ID)) {
			value = this.getPrimaryID();
		} else if (fieldname.equals(NAME)) {
			value = this.getName();
		} else if (fieldname.equals(MARKER_TYPE)) {
			value = this.getMarkerType();
		} else {
			throw new NoSuchFieldException("Unknown field: " + fieldname);
		}
		return value;
	}

	@Column(table="markerCounts", name="referenceCount")
	@JoinColumn(name="markerKey")
	public Integer getCountOfReferences() {
		return countOfReferences;
	}

	public void setCountOfReferences(Integer countOfReferences) {
		this.countOfReferences = countOfReferences;
	}

	@Column(table="markerCounts", name="sequenceCount")
	@JoinColumn(name="markerKey")
	public Integer getCountOfSequences() {
		return countOfSequences;
	}

	public void setCountOfSequences(Integer countOfSequences) {
		this.countOfSequences = countOfSequences;
	}

	@OneToMany (targetEntity=MarkerAnnotation.class)
	@JoinColumn(name="markerKey")
	public List<MarkerAnnotation> getAnnotations() {
		return annotations;
	}

	public void setAnnotations(List<MarkerAnnotation> annotations) {
		this.annotations = annotations;
	}

	@OneToMany (targetEntity=MarkerNote.class)
	@JoinColumn(name="markerKey")
	public List<MarkerNote> getNotes() {
		return notes;
	}

	public void setNotes(List<MarkerNote> notes) {
		this.notes = notes;
	}

	@OneToMany (targetEntity=MarkerLocation.class)
	@JoinColumn(name="markerKey")
	@OrderBy("sequenceNum")
	public List<MarkerLocation> getLocations() {
		return locations;
	}

	public void setLocations(List<MarkerLocation> locations) {
		this.locations = locations;
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
		//		+ (referenceAssociations != null ? "referenceAssociations="
			//			+ referenceAssociations + ", " : "")
			//	+ (references != null ? "references=" + references + ", " : "")
				+ (status != null ? "status=" + status + ", " : "")
				+ (symbol != null ? "symbol=" + symbol + ", " : "")
				+ (synonyms != null ? "synonyms=" + synonyms : "") + "]";
	}
}
