package org.jax.mgi.fe.datamodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.WhereJoinTable;

/**
 * Antibody
 * @author jsb
 * central object in Antibody flower
 */
@Entity
@Table (name="antibody")
public class Antibody {

    private int antibodyKey;
    private String name;
    private String primaryID;
    private String host;
    private String antibodyType;
    private String antibodyClass;
    private int expressionResultCount;
    private String synonyms;
    private String note;

    // Antigen fields
    private String agRegionCovered;
    private String agNote;
    private String agSpecies;
    private String agStrain;
    private String agSex;
    private String agAge;
    private String agTissue;
    private String agTissueDescription;
    private String agCellLine;

    private List<Marker> markers;
    private List<Reference> relatedReferences;
    private List<Reference> primaryReferences;

    // ================= Getters and Setters ===================== //

    @Id
    @Column(name="antibody_key")
    public int getAntibodyKey() {
	return antibodyKey;
    }
    public void setAntibodyKey(int antibodyKey) {
	this.antibodyKey = antibodyKey;
    }

    @Column(name="Name")
    public String getName() {
	return name;
    }
    public void setName(String name) {
	this.name = name;
    }

    @Column(name="primary_id")
    public String getPrimaryID() {
	return primaryID;
    }
    public void setPrimaryID(String primaryID) {
	this.primaryID = primaryID;
    }

    @Column(name="host")
    public String getHost() {
	return host;
    }
    public void setHost(String host) {
	this.host = host;
    }

    @Column(name="antibody_type")
    public String getAntibodyType() {
	return antibodyType;
    }
    public void setAntibodyType(String antibodyType) {
	this.antibodyType = antibodyType;
    }

    @Column(name="antibody_class")
    public String getAntibodyClass() {
	return antibodyClass;
    }
    public void setAntibodyClass(String antibodyClass) {
	this.antibodyClass = antibodyClass;
    }

    @Column(name="note")
    public String getNote() {
	return note;
    }
    public void setNote(String note) {
	this.note = note;
    }

    @Column(name="synonyms")
    public String getSynonyms() {
	return synonyms;
    }
    public void setSynonyms(String synonyms) {
	this.synonyms = synonyms;
    }

    @Column(name="ag_region_covered")
    public String getAgRegionCovered() {
	return agRegionCovered;
    }
    public void setAgRegionCovered(String agRegionCovered) {
	this.agRegionCovered = agRegionCovered;
    }

    @Column(name="ag_note")
    public String getAgNote() {
	return agNote;
    }
    public void setAgNote(String agNote) {
	this.agNote = agNote;
    }

    @Column(name="ag_species")
    public String getAgSpecies() {
	return agSpecies;
    }
    public void setAgSpecies(String agSpecies) {
	this.agSpecies = agSpecies;
    }

    @Column(name="ag_strain")
    public String getAgStrain() {
	return agStrain;
    }
    public void setAgStrain(String agStrain) {
	this.agStrain = agStrain;
    }

    @Column(name="ag_sex")
    public String getAgSex() {
	return agSex;
    }
    public void setAgSex(String agSex) {
	this.agSex = agSex;
    }

    @Column(name="ag_age")
    public String getAgAge() {
	return agAge;
    }
    public void setAgAge(String agAge) {
	this.agAge = agAge;
    }

    @Column(name="ag_cell_line")
    public String getAgCellLine() {
	return agCellLine;
    }
    public void setAgCellLine(String agCellLine) {
	this.agCellLine = agCellLine;
    }

    @Column(name="ag_tissue")
    public String getAgTissue() {
	return agTissue;
    }
    public void setAgTissue(String agTissue) {
	this.agTissue = agTissue;
    }

    @Column(name="ag_tissue_description")
    public String getAgTissueDescription() {
	return agTissueDescription;
    }
    public void setAgTissueDescription(String agTissueDescription) {
	this.agTissueDescription = agTissueDescription;
    }

    

    @Column(name="expression_result_count")
    public int getExpressionResultCount() {
	return expressionResultCount;
    }
    public void setExpressionResultCount(int expressionResultCount) {
	this.expressionResultCount = expressionResultCount;
    }

    /* note the existence of the getSortedMarkers() method below if you'd like
     * the list with a smart-alpha sort
     */
    @OneToMany (targetEntity=Marker.class)
    @JoinTable (name="marker_to_antibody",
	joinColumns=@JoinColumn(name="antibody_key"),
	inverseJoinColumns=@JoinColumn(name="marker_key")
	)
    @BatchSize(size=200)
    @OrderBy("symbol")
    public List<Marker> getMarkers() {
	return markers;
    }
    public void setMarkers(List<Marker> markers) {
	this.markers = markers;
    }

    /* need a transient method to get the sorted list of markers, as the
     * base sort is not alphanumeric.  Will never return null.
     */
    @Transient
    public List<Marker> getSortedMarkers() {
	ArrayList<Marker> myMarkers = new ArrayList<Marker>();

	if (this.markers == null) {
	    return myMarkers;
	}

	for (Marker m : this.markers) {
	    myMarkers.add(m);
	}

	Collections.sort(myMarkers, myMarkers.get(0).getComparator());
	return myMarkers;
    }

    /* get the list of all references, sorted properly by year and J: number,
     * but with the primary reference moved to the top
     */
    @Transient
    public List<Reference> getReferences() {
	List<Reference> refs = new ArrayList<Reference>();
	List<Reference> primary = this.getPrimaryReferences();
	List<Reference> related = this.getRelatedReferences();

	if (primary != null) {
	    refs.addAll(primary);
	}
	if (related != null) {
	    refs.addAll(related);
	}

	return refs; 
    }

    @OneToMany (targetEntity=Reference.class)
    @JoinTable (name="antibody_to_reference",
	joinColumns=@JoinColumn(name="antibody_key"),
	inverseJoinColumns=@JoinColumn(name="reference_key")
	)
    @WhereJoinTable(clause = "qualifier = 'Related'")
    @BatchSize(size=200)
    @OrderBy("year, jnumNumeric")
    public List<Reference> getRelatedReferences() {
	return relatedReferences;
    }
    public void setRelatedReferences(List<Reference> relatedReferences) {
	this.relatedReferences = relatedReferences;
    }

    @OneToMany (targetEntity=Reference.class)
    @JoinTable (name="antibody_to_reference",
	joinColumns=@JoinColumn(name="antibody_key"),
	inverseJoinColumns=@JoinColumn(name="reference_key")
	)
    @WhereJoinTable(clause = "qualifier = 'Primary'")
    @BatchSize(size=10)
    @OrderBy("year, jnumNumeric")
    public List<Reference> getPrimaryReferences() {
	return primaryReferences;
    }
    public void setPrimaryReferences(List<Reference> primaryReferences) {
	this.primaryReferences = primaryReferences;
    }

    public String toString() {
	return "<Antibody " + this.name
	    + " [" + this.primaryID + ", key " + this.antibodyKey + "]>";
    }
}
