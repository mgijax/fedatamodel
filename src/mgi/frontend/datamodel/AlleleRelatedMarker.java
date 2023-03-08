package mgi.frontend.datamodel;

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
@Table(name="allele_related_marker")
public class AlleleRelatedMarker {
    private int armKey;
    private int alleleKey;
    private Marker relatedMarker;
    private int referenceKey;
    private int sequenceNum;
    private String relatedMarkerSymbol;
    private String relatedMarkerID;
    private String relationshipCategory;
    private String relationshipTerm;
    private String qualifier;
    private String evidenceCode;
    private String jnumID;
    private List<AlleleRelatedMarkerProperty> properties;
    private int inTeaser;

    private String NOTE = "note";
    /*---- Getters and Setters ----*/

    @Id
    @Column(name="arm_key")
    public int getArmKey() {
	return armKey;
    }

    public void setArmKey(int armKey) {
	this.armKey = armKey;
    }

    @Column(name="allele_key")
    public int getAlleleKey() {
	return alleleKey;
    }

    public void setAlleleKey(int alleleKey) {
	this.alleleKey = alleleKey;
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

    @OneToMany(targetEntity=AlleleRelatedMarkerProperty.class)
    @BatchSize(size=250)
    @JoinColumn(name="arm_key")
    @OrderBy("sequenceNum")
    public List<AlleleRelatedMarkerProperty> getProperties() {
	return this.properties;
    }

    public void setProperties(List<AlleleRelatedMarkerProperty> properties) {
	this.properties = properties;
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
    public String getProperty(String name) {
	List<AlleleRelatedMarkerProperty> props = this.getProperties();

	for (AlleleRelatedMarkerProperty p : props) {
	    if (p.getName().equals(name)) {
		return p.getValue();
	    }
	}
	return null;
    }

    @Transient
    public String getNote() {
	return this.getProperty(NOTE);
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
}
