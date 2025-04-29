package org.jax.mgi.fe.datamodel;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;

/** This is the base class for related-marker objects.
 * @author jsb
 * This class provides common fields for related-marker objects, including
 * data for the marker, about the relationship, and including a reference.
 */

@Entity
@Table(name="marker_related_marker")
public class RelatedMarker {
    private int mrmKey;
    private int markerKey;
    //private int relatedMarkerKey;
    private Marker relatedMarker;
    private int referenceKey;
    private int sequenceNum;
    private String relatedMarkerSymbol;
    private String relatedMarkerID;
    private String relationshipCategory;
    private String relationshipTerm;
    private Term   relationshipTermObj;
    private String qualifier;
    private String evidenceCode;
    private String jnumID;
    private List<RelatedMarkerProperty> properties;

    /*---- Getters and Setters ----*/

    @Id
    @Column(name="mrm_key")
    public int getMrmKey() {
	return mrmKey;
    }

    public void setMrmKey(int mrmKey) {
	this.mrmKey = mrmKey;
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
    @JoinColumn(name="related_marker_key")
    public Marker getRelatedMarker() {
	return relatedMarker;
    }

    public void setRelatedMarker(Marker relatedMarker) {
	this.relatedMarker = relatedMarker;
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

    @Column(name="related_marker_symbol")
    public String getRelatedMarkerSymbol() {
	return relatedMarkerSymbol;
    }

    public void setRelatedMarkerSymbol(String relatedMarkerSymbol) { 
	this.relatedMarkerSymbol = relatedMarkerSymbol;
    }

    @Column(name="related_marker_id")
    public String getRelatedMarkerID() {
	return relatedMarkerID;
    }

    public void setRelatedMarkerID(String relatedMarkerID) { 
	this.relatedMarkerID = relatedMarkerID;
    }

    @Column(name="relationship_term")
    public String getRelationshipTerm() {
	return relationshipTerm;
    }

    public void setRelationshipTerm(String relationshipTerm) { 
	this.relationshipTerm = relationshipTerm;
    }

    @ManyToOne (targetEntity=Term.class, fetch=FetchType.LAZY)
    @JoinColumn(name="relationship_term_key")
    public Term getRelationshipTermObj() {
	return relationshipTermObj;
    }

    public void setRelationshipTermObj(Term relationshipTermObj) {
	this.relationshipTermObj = relationshipTermObj;
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

    @OneToMany(targetEntity=RelatedMarkerProperty.class)
    @BatchSize(size=250)
    @JoinColumn(name="mrm_key")
    @OrderBy("sequenceNum")
    public List<RelatedMarkerProperty> getProperties() {
	return this.properties;
    }

    public void setProperties(List<RelatedMarkerProperty> properties) {
	this.properties = properties;
    }

    /*--- Convenience Methods ---*/

    @Transient
    public String getProperty(String name) {
	List<RelatedMarkerProperty> props = this.getProperties();

	for (RelatedMarkerProperty p : props) {
	    if (p.getName().equals(name)) {
		return p.getValue();
	    }
	}
	return null;
    }

    @Transient
    public String getRelatedMarkerName() {
	Marker m = this.getRelatedMarker();
	return m.getName();
    }

    @Transient
    public String getRelatedMarkerFeatureType() {
	Marker m = this.getRelatedMarker();
	return m.getMarkerSubtype();
    }

    @Transient
    // Returns the related marker's genetic (cM) location as a formatted string.
    // If the marker has no genetic location, returns an empty string.
    public String getRelatedMarkerGeneticLocation() {
        Marker rm = this.getRelatedMarker();
        MarkerLocation ml = rm.getPreferredCentimorgans();
        if (ml == null) return "";
        StringBuffer sb = new StringBuffer();
        sb.append("Chr");
        sb.append(rm.getChromosome());
        sb.append(", ");
        if (ml.getCmOffset() == -1.0) {
            sb.append(" syntenic");
        } else {
            sb.append(String.format("%.2f", ml.getCmOffset()));
            sb.append(" cM");
        }
        return sb.toString();
    }

    @Transient
    // Returns the related marker's genomic location as a formatted string.
    // If the marker has no genomic location, returns an empty string.
    public String getRelatedMarkerGenomicLocation() {
        Marker rm = this.getRelatedMarker();
	MarkerLocation ml = rm.getPreferredCoordinates();
        if (ml == null) return "";
        StringBuffer sb = new StringBuffer();
        sb.append("Chr");
        sb.append(ml.getChromosome());
        sb.append(":");
        sb.append(ml.getStartCoordinate().longValue());
        
        sb.append("-");
        sb.append(ml.getEndCoordinate().longValue());

        String strandDisplay = ml.getStrandDisplay();
        if (strandDisplay != null) {
            sb.append(" (");
            sb.append(strandDisplay);
            sb.append(")");
        }
        return sb.toString();
    }

    @Transient
    public String getRelatedMarkerLocation() {
	StringBuffer sb = new StringBuffer();
	Marker m = this.getRelatedMarker();

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

	    String strandDisplay = ml.getStrandDisplay();
	    if (strandDisplay != null) {
		    sb.append(" (");
		    sb.append(strandDisplay);
		    sb.append(")");
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
}
