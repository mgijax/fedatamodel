package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * StrainMutation - represents one marker (or marker and allele) carried by a mouse strain
 */
@Entity
@Table(name="strain_mutation")
public class StrainMutation {
	private int uniqueKey;
	private int strainKey;
	private Integer markerKey;
	private String markerSymbol;
	private String markerID;
	private Integer alleleKey;
	private String alleleSymbol;
	private String alleleID;
	private int sequenceNum;
	
    // ================= Getters and Setters ===================== //

	@Column(name="allele_id")
	public String getAlleleID() {
		return alleleID;
	}
	
	@Column(name="allele_key")
	public Integer getAlleleKey() {
		return alleleKey;
	}

	@Column(name="allele_symbol")
	public String getAlleleSymbol() {
		return alleleSymbol;
	}

	@Column(name="marker_id")
	public String getMarkerID() {
		return markerID;
	}

	@Column(name="marker_key")
	public Integer getMarkerKey() {
		return markerKey;
	}

	@Column(name="marker_symbol")
	public String getMarkerSymbol() {
		return markerSymbol;
	}

	@Column(name="sequence_num")
	public int getSequenceNum() {
		return sequenceNum;
	}

	@Column(name="strain_key")
	public int getStrainKey() {
		return strainKey;
	}

	@Id
	@Column(name="unique_key")
	public int getUniqueKey() {
		return uniqueKey;
	}

	public void setAlleleID(String alleleID) {
		this.alleleID = alleleID;
	}

	public void setAlleleKey(Integer alleleKey) {
		this.alleleKey = alleleKey;
	}

	public void setAlleleSymbol(String alleleSymbol) {
		this.alleleSymbol = alleleSymbol;
	}

	public void setMarkerID(String markerID) {
		this.markerID = markerID;
	}

	public void setMarkerKey(Integer markerKey) {
		this.markerKey = markerKey;
	}

	public void setMarkerSymbol(String markerSymbol) {
		this.markerSymbol = markerSymbol;
	}

	public void setSequenceNum(int sequenceNum) {
		this.sequenceNum = sequenceNum;
	}

	public void setStrainKey(int strainKey) {
		this.strainKey = strainKey;
	}

	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}
}
