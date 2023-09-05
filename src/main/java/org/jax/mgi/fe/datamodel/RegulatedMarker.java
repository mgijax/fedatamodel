package org.jax.mgi.fe.datamodel;

import java.util.Comparator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.jax.mgi.fe.datamodel.sort.SmartAlphaComparator;

/** This is the base class for regulated-marker objects.
 * @author jsb
 * This class provides common fields for regulated-marker objects, including
 * data for the marker, about the relationship, and including a reference.
 */

@Entity
@Table(name="marker_regulated_marker")
public class RegulatedMarker {
    private int regKey;
    private int markerKey;
    private Marker regulatedMarker;
    private int referenceKey;
    private int sequenceNum;
    private String regulatedMarkerSymbol;
    private String regulatedMarkerID;
    private String relationshipCategory;
    private String relationshipTerm;
    private String qualifier;
    private String evidenceCode;
    private String jnumID;
    private int inTeaser;

    /*---- Getters and Setters ----*/

    @Id
    @Column(name="reg_key")
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
    @JoinColumn(name="regulated_marker_key")
    public Marker getRegulatedMarker() {
	return regulatedMarker;
    }

    public void setRegulatedMarker(Marker regulatedMarker) {
	this.regulatedMarker = regulatedMarker;
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

    @Column(name="regulated_marker_symbol")
    public String getRegulatedMarkerSymbol() {
	return regulatedMarkerSymbol;
    }

    public void setRegulatedMarkerSymbol(String regulatedMarkerSymbol) { 
	this.regulatedMarkerSymbol = regulatedMarkerSymbol;
    }

    @Column(name="regulated_marker_id")
    public String getRegulatedMarkerID() {
	return regulatedMarkerID;
    }

    public void setRegulatedMarkerID(String regulatedMarkerID) { 
	this.regulatedMarkerID = regulatedMarkerID;
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

    /*--- Convenience Methods ---*/

    @Transient
    public String getRegulatedMarkerName() {
	Marker m = this.getRegulatedMarker();
	return m.getName();
    }

    @Transient
    public String getRegulatedMarkerFeatureType() {
	Marker m = this.getRegulatedMarker();
	return m.getMarkerSubtype();
    }

    @Transient
    public String getRegulatedMarkerLocation() {
	StringBuffer sb = new StringBuffer();
	Marker m = this.getRegulatedMarker();

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
    public Comparator<RegulatedMarker> getComparator() {
    	return new RegulatedMarkerComparator();
    }

    private class RegulatedMarkerComparator extends SmartAlphaComparator<RegulatedMarker> {
	public int compare(RegulatedMarker o1, RegulatedMarker o2) {
	    return super.compare(o1.getRegulatedMarkerSymbol(),
	    		o2.getRegulatedMarkerSymbol());
		}
    }
}
