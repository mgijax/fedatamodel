package org.jax.mgi.fe.datamodel;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;

/**
 * Is: a polymorphism for a marker
 */
@Entity
@Table(name="marker_polymorphism")
public class MarkerPolymorphism {
	private int polymorphismKey;
	private int markerKey;
	private String type;
	private String jnumID;
	private String probeName;
	private String probeID;
	private String endonuclease;
	private int sequenceNum;
	List<MarkerPolymorphismAllele> alleles;
	
    // ================= Getters and Setters ===================== //
	
	@Column(name="endonuclease")
	public String getEndonuclease() {
		return endonuclease;
	}
	
	@OneToMany (targetEntity=MarkerPolymorphismAllele.class)
	@JoinColumn(name="polymorphism_key")
	@BatchSize(size=100)
	@OrderBy("sequenceNum")
	public List<MarkerPolymorphismAllele> getAlleles() {
		return alleles;
	}

	@Column(name="jnum_id")
	public String getJnumID() {
		return jnumID;
	}
	
	@Column(name="marker_key")
	public int getMarkerKey() {
		return markerKey;
	}
	
	@Id
	@Column(name="polymorphism_key")
	public int getPolymorphismKey() {
		return polymorphismKey;
	}
	
	@Column(name="probe_id")
	public String getProbeID() {
		return probeID;
	}
	
	@Column(name="probe_name")
	public String getProbeName() {
		return probeName;
	}
	
	@Column(name="sequence_num")
	public int getSequenceNum() {
		return sequenceNum;
	}
	
	@Column(name="polymorphism_type")
	public String getType() {
		return type;
	}
	
	public void setAlleles(List<MarkerPolymorphismAllele> alleles) {
		this.alleles = alleles;
	}
	public void setEndonuclease(String endonuclease) {
		this.endonuclease = endonuclease;
	}
	public void setJnumID(String jnumID) {
		this.jnumID = jnumID;
	}
	public void setMarkerKey(int markerKey) {
		this.markerKey = markerKey;
	}
	public void setPolymorphismKey(int polymorphismKey) {
		this.polymorphismKey = polymorphismKey;
	}
	public void setProbeID(String probeID) {
		this.probeID = probeID;
	}
	public void setProbeName(String probeName) {
		this.probeName = probeName;
	}
	public void setSequenceNum(int sequenceNum) {
		this.sequenceNum = sequenceNum;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "MarkerPolymorphism [polymorphismKey=" + polymorphismKey + ", markerKey=" + markerKey + ", type=" + type
				+ ", jnumID=" + jnumID + ", probeName=" + probeName + ", probeID=" + probeID + ", endonuclease="
				+ endonuclease + ", sequenceNum=" + sequenceNum + "]";
	}
}
