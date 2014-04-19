package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * GelBand
 * This object represents the data for one gel band + information about its gel row
 */
@Entity
@Table(name="gelband")
public class GelBand {

	// attributes in core table
	private Integer gelBandKey;
	private Integer gelLaneKey;
	private String rowSize;
    private String rowNote;
	private String strength;
	private String bandNote;
	private Integer rowSeq;
	/* properties not managed by hibernate */
	private String rowNoteLetter="";
	private String bandNoteLetter="";
	

    // ===================== Getters ===================== //

	@Id
	@Column(name="gelband_key")
	public Integer getGelBandKey() {
		return gelBandKey;
	}
	
	@Column(name="gellane_key")
	public Integer getGelLaneKey() {
		return gelLaneKey;
	}

	@Column(name="rowsize")
	public String getRowSize() {
		return rowSize;
	}

	@Column(name="row_note")
	public String getRowNote() {
		return rowNote;
	}

	@Column(name="strength")
	public String getStrength() {
		return strength;
	}

	@Column(name="band_note")
	public String getBandNote() {
		return bandNote;
	}
	
	@Column(name="row_seq")
	public Integer getRowSeq() {
		return rowSeq;
	}
	
	@Transient
	public String getRowNoteLetter() {
		return rowNoteLetter;
	}
	@Transient
	public String getBandNoteLetter() {
		return bandNoteLetter;
	}
	

    // ===================== Setters ===================== //
	
	public void setGelBandKey(Integer gelBandKey) {
		this.gelBandKey = gelBandKey;
	}

	public void setGelLaneKey(Integer gelLaneKey) {
		this.gelLaneKey = gelLaneKey;
	}

	public void setRowSize(String rowSize) {
		this.rowSize = rowSize;
	}

	public void setRowNote(String rowNote) {
		this.rowNote = rowNote;
	}

	public void setStrength(String strength) {
		this.strength = strength;
	}

	public void setBandNote(String bandNote) {
		this.bandNote = bandNote;
	}

	public void setRowSeq(Integer rowSeq) {
		this.rowSeq = rowSeq;
	}

	public void setRowNoteLetter(String rowNoteLetter) {
		this.rowNoteLetter = rowNoteLetter;
	}

	public void setBandNoteLetter(String bandNoteLetter) {
		this.bandNoteLetter = bandNoteLetter;
	}
	
}