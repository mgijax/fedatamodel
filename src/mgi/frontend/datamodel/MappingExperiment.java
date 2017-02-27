package mgi.frontend.datamodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

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

import mgi.frontend.datamodel.sort.SmartAlphaComparator;

/**
 * (Genetic) Mapping Experiment
 * Core mapping object.
 */
@Entity
@Table (name="mapping_experiment")
public class MappingExperiment {

    private int experimentKey;
    private String type;
    private String note;
    private Reference reference;
    private String referenceNote;
    private String chromosome;
    private String primaryID;
    private List<MappingMarker> markers;

    /*--- transient methods ---*/
    
    @Transient
    public String getNoteHtml() {
    	return tweakHTML(getNote());
    }
    
    @Transient
    public String getReferenceNoteHtml() {
    	return tweakHTML(getReferenceNote());
    }
    
    // 1. convert <..> notation into superscripts
    // 2. convert newlines (\n) into HTML line breaks
    private String tweakHTML(String s) {
    	String t = s.replaceAll("<", "@@@").replaceAll(">", "</sup>").replaceAll("@@@", "<sup>");
    	return t.replaceAll("\n", "<br/>");
    }
    
    // ================= Getters and Setters ===================== //

    @Column(name="chromosome")
    public String getChromosome() {
		return chromosome;
	}

	@Id
    @Column(name="experiment_key")
	public int getExperimentKey() {
		return experimentKey;
	}

    @OneToMany (targetEntity=MappingMarker.class)
    @JoinColumn(name="experiment_key")
    @OrderBy("sequenceNum")
	public List<MappingMarker> getMarkers() {
		return markers;
	}

	@Column(name="experiment_note")
	public String getNote() {
		return note;
	}

    @Column(name="primary_id")
	public String getPrimaryID() {
		return primaryID;
	}

    @ManyToOne (targetEntity=Reference.class, fetch=FetchType.EAGER)
    @JoinColumn (name="reference_key")
	public Reference getReference() {
		return reference;
	}

	@Column(name="reference_note")
	public String getReferenceNote() {
		return referenceNote;
	}

    @Column(name="experiment_type")
	public String getType() {
		return type;
	}

    public void setChromosome(String chromosome) {
		this.chromosome = chromosome;
	}

	public void setExperimentKey(int experimentKey) {
		this.experimentKey = experimentKey;
	}
	public void setMarkers(List<MappingMarker> markers) {
		this.markers = markers;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public void setPrimaryID(String primaryID) {
		this.primaryID = primaryID;
	}
	public void setReference(Reference reference) {
		this.reference = reference;
	}
	public void setReferenceNote(String referenceNote) {
		this.referenceNote = referenceNote;
	}
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "MappingExperiment [experimentKey=" + experimentKey + ", type=" + type + ", note=" + note
				+ ", reference=" + reference + ", referenceNote=" + referenceNote + ", chromosome=" + chromosome
				+ ", primaryID=" + primaryID + "]";
	}
}
