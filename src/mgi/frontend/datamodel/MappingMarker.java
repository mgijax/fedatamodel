package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Is: primarily a marker associated with a (genetic) mapping experiment, but also includes (optionally)
 * 	an allele, an assay type, and a description
 */
@Entity
@Table(name="mapping_to_marker")
public class MappingMarker {
	private int uniqueKey;
	private int experimentKey;
	private Marker marker;
	private Allele allele;
	private String assayType;
	private String description;
	private int sequenceNum;
	
    // ================= Getters and Setters ===================== //
	
	@ManyToOne (targetEntity=Allele.class, fetch=FetchType.EAGER)
    @JoinColumn (name="allele_key")
	public Allele getAllele() {
		return allele;
	}

	@Column(name="assay_type")
	public String getAssayType() {
		return assayType;
	}

	@Column(name="description")
	public String getDescription() {
		return description;
	}

	@Column(name="experiment_key")
	public int getExperimentKey() {
		return experimentKey;
	}

	@ManyToOne (targetEntity=Marker.class, fetch=FetchType.EAGER)
    @JoinColumn (name="marker_key")
	public Marker getMarker() {
		return marker;
	}

	@Column(name="sequence_num")
	public int getSequenceNum() {
		return sequenceNum;
	}

	@Id
	@Column(name="unique_key")
	public int getUniqueKey() {
		return uniqueKey;
	}

	public void setAllele(Allele allele) {
		this.allele = allele;
	}

	public void setAssayType(String assayType) {
		this.assayType = assayType;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setExperimentKey(int experimentKey) {
		this.experimentKey = experimentKey;
	}

	public void setMarker(Marker marker) {
		this.marker = marker;
	}

	public void setSequenceNum(int sequenceNum) {
		this.sequenceNum = sequenceNum;
	}

	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	@Override
	public String toString() {
		return "MappingMarker [uniqueKey=" + uniqueKey + ", experimentKey=" + experimentKey + ", marker=" + marker
				+ ", allele=" + allele + ", assayType=" + assayType + ", description=" + description + "]";
	}
}
