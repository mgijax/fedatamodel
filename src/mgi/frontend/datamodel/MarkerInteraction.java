package mgi.frontend.datamodel;

import java.util.Comparator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import mgi.frontend.datamodel.sort.SmartAlphaComparator;

/** This is the base class for marker interaction objects.
 * @author jsb
 * This class provides common fields for marker interaction objects, including
 * data for the marker, about the relationship, and including a reference.
 */

@Entity
@Table(name="marker_interaction")
public class MarkerInteraction {
    private int regKey;
    private int markerKey;
    private Marker interactingMarker;
    private int referenceKey;
    private int sequenceNum;
    private String interactingMarkerSymbol;
    private String interactingMarkerID;
    private String relationshipCategory;
    private String relationshipTerm;
    private String qualifier;
    private String evidenceCode;
    private String jnumID;
    private int inTeaser;
    private int isReversed;

    /*---- Getters and Setters ----*/

    @Id
    @Column(name="mi_key")
    public int getMrmKey() {
	return regKey;
    }

    public void setMrmKey(int regKey) {
	this.regKey = regKey;
    }

    // Do we provide a Marker object from here, with transient methods to pull
    // out name, chromosome, and coordinates, or do we encode them as
    // properties of the relationship?
    //
    // If as properties, should we store the location as a single
    // pre-formatted field?
    //
    // For now, let's just use the Marker.  We can come back and optimize it
    // here if needed.

    @Column(name="marker_key")
    public int getMarkerKey() {
	return markerKey;
    }

    public void setMarkerKey(int markerKey) {
	this.markerKey = markerKey;
    }

    @ManyToOne (targetEntity=Marker.class, fetch=FetchType.LAZY)
    @JoinColumn(name="interacting_marker_key")
    public Marker getInteractingMarker() {
	return interactingMarker;
    }

    public void setInteractingMarker(Marker interactingMarker) {
	this.interactingMarker = interactingMarker;
    }

    @Column(name="reference_key")
    public int getReferenceKey() {
	return referenceKey;
    }

    public void setReferenceKey(int referenceKey) {
	this.referenceKey = referenceKey;
    }

    @Column(name="sequence_num")
    public int getSequenceNum() {
	return sequenceNum;
    }

    public void setSequenceNum(int sequenceNum) {
	this.sequenceNum = sequenceNum;
    }

    @Column(name="interacting_marker_symbol")
    public String getInteractingMarkerSymbol() {
	return interactingMarkerSymbol;
    }

    public void setInteractingMarkerSymbol(String interactingMarkerSymbol) { 
	this.interactingMarkerSymbol = interactingMarkerSymbol;
    }

    @Column(name="interacting_marker_id")
    public String getInteractingMarkerID() {
	return interactingMarkerID;
    }

    public void setInteractingMarkerID(String interactingMarkerID) { 
	this.interactingMarkerID = interactingMarkerID;
    }

    @Column(name="relationship_term")
    public String getRelationshipTerm() {
	return relationshipTerm;
    }

    public void setRelationshipTerm(String relationshipTerm) { 
	this.relationshipTerm = relationshipTerm;
    }

    @Column(name="relationship_category")
    public String getRelationshipCategory() {
	return relationshipCategory;
    }

    public void setRelationshipCategory(String relationshipCategory) { 
	this.relationshipCategory = relationshipCategory;
    }

    @Column(name="qualifier")
    public String getQualifier() {
	return qualifier;
    }

    public void setQualifier(String qualifier) { 
	this.qualifier = qualifier;
    }

    @Column(name="evidence_code")
    public String getEvidenceCode() {
	return evidenceCode;
    }

    public void setEvidenceCode(String evidenceCode) { 
	this.evidenceCode = evidenceCode;
    }

    @Column(name="jnum_id")
    public String getJnumID() {
	return jnumID;
    }

    public void setJnumID(String jnumID) { 
	this.jnumID = jnumID;
    }

    @Column(name="in_teaser")
    public int getInTeaser() {
	return inTeaser;
    }

    public void setInTeaser(int inTeaser) {
	this.inTeaser = inTeaser;
    }

    @Column(name="is_reversed")
    public int getIsReversed() {
	return isReversed;
    }

    public void setIsReversed(int isReversed) {
	this.isReversed = isReversed;
    }

    /*--- Convenience Methods ---*/

    @Transient
    public String getInteractingMarkerName() {
	Marker m = this.getInteractingMarker();
	return m.getName();
    }

    @Transient
    public String getInteractingMarkerFeatureType() {
	Marker m = this.getInteractingMarker();
	return m.getMarkerSubtype();
    }

    @Transient
    public String getInteractingMarkerLocation() {
	StringBuffer sb = new StringBuffer();
	Marker m = this.getInteractingMarker();

	// location preferences...
	// 1. coordinates (with genomic chromosome)
	// 2. cytogenetic offset (with genetic chromosome)
	// 3. just chromosome + Syntenic

	MarkerLocation ml = m.getPreferredCoordinates();
	if (ml != null) {
	    sb.append("Chr");
	    sb.append(ml.getChromosome());
	    sb.append(":");
	    sb.append(ml.getStartCoordinate().longValue());
	    sb.append("-");
	    sb.append(ml.getEndCoordinate().longValue());

	    if (ml.getStrand() != null) {
		if (!ml.getStrand().equals("")) {
		    sb.append(" (");
		    sb.append(ml.getStrand());
		    sb.append(")");
		}
	    }

	} else {
	    ml = m.getPreferredCytoband();
	    if (ml != null) {
	        sb.append("Chr");
		sb.append(ml.getChromosome());
		sb.append(", cytoband ");
		sb.append(ml.getCytogeneticOffset());

	    } else {
		sb.append("Chr");
		sb.append(m.getChromosome());
		sb.append(", Syntenic");
	    } 
 
	}
	return sb.toString();
    }

    @Transient
    public Comparator<MarkerInteraction> getComparator() {
    	return new MarkerInteractionComparator();
    }

    
    private class MarkerInteractionComparator extends SmartAlphaComparator<MarkerInteraction> {
	public int compare(MarkerInteraction o1, MarkerInteraction o2) {
	    return super.compare(o1.getInteractingMarkerSymbol(),
	    		o2.getInteractingMarkerSymbol());
		}
    }
}
