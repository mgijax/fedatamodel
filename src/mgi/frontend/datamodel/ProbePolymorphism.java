package mgi.frontend.datamodel;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 * Is: a polymorphism associated with a probe via a specific reference
 */
@Entity
@Table(name="probe_polymorphism")
public class ProbePolymorphism {
	private int probePolymorphismKey;
	private int probeReferenceKey;
	private int markerKey;
	private String markerID;
	private String markerSymbol;
	private String endonuclease;
	private int sequenceNum;
	private List<ProbePolymorphismDetails> details;
	
    // ================= Getters and Setters ===================== //
	
	@OneToMany (targetEntity=ProbePolymorphismDetails.class)
    @JoinColumn (name="probe_polymorphism_key")
    @OrderBy("allele")
	public List<ProbePolymorphismDetails> getDetails() {
		return details;
	}

	@Column(name="endonuclease")
	public String getEndonuclease() {
		return endonuclease;
	}

	@Column(name="marker_id")
	public String getMarkerID() {
		return markerID;
	}

	@Column(name="marker_key")
	public int getMarkerKey() {
		return markerKey;
	}

	@Column(name="marker_symbol")
	public String getMarkerSymbol() {
		return markerSymbol;
	}

	@Id
	@Column(name="probe_polymorphism_key")
	public int getProbePolymorphismKey() {
		return probePolymorphismKey;
	}

	@Column(name="probe_reference_key")
	public int getProbeReferenceKey() {
		return probeReferenceKey;
	}

	@Column(name="sequence_num")
	public int getSequenceNum() {
		return sequenceNum;
	}

	public void setDetails(List<ProbePolymorphismDetails> details) {
		this.details = details;
	}

	public void setEndonuclease(String endonuclease) {
		this.endonuclease = endonuclease;
	}

	public void setMarkerID(String markerID) {
		this.markerID = markerID;
	}

	public void setMarkerKey(int markerKey) {
		this.markerKey = markerKey;
	}

	public void setMarkerSymbol(String markerSymbol) {
		this.markerSymbol = markerSymbol;
	}

	public void setProbePolymorphismKey(int probePolymorphismKey) {
		this.probePolymorphismKey = probePolymorphismKey;
	}

	public void setProbeReferenceKey(int probeReferenceKey) {
		this.probeReferenceKey = probeReferenceKey;
	}

	public void setSequenceNum(int sequenceNum) {
		this.sequenceNum = sequenceNum;
	}

	@Override
	public String toString() {
		return "ProbePolymorphism [probePolymorphismKey=" + probePolymorphismKey + ", probeReferenceKey="
				+ probeReferenceKey + ", markerKey=" + markerKey + ", markerID=" + markerID + ", markerSymbol="
				+ markerSymbol + ", endonuclease=" + endonuclease + ", sequenceNum=" + sequenceNum + "]";
	}
}
