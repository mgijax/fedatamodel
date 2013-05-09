package mgi.frontend.datamodel;

import java.util.*;

import javax.persistence.*;


/**
 * GelLane
 * This object represents the structures in a given gel lane
 */
@Entity
@Table(name="gellane_to_structure")
public class GelLaneStructure {

	// attributes in core table
	private Integer uniqueKey;
	private Integer gelLaneKey;
	private Integer mgdStructureKey;
	private Integer structureSeq;
	private String printname;

    // ===================== Getters ===================== //

	@Id
	@Column(name="unique_key")
	public Integer getUniqueKey() {
		return uniqueKey;
	}

	@Column(name="gellane_key")
	public Integer getGelLaneKey() {
		return gelLaneKey;
	}

	@Column(name="mgd_structure_key")
	public Integer getMgdStructureKey() {
		return mgdStructureKey;
	}

	@Column(name="printname")
	public String getPrintname() {
		return printname;
	}

	@Column(name="structure_seq")
	public Integer getStructureSeq() {
		return structureSeq;
	}


    // ===================== Setters ===================== //
	
	public void setUniqueKey(Integer uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	public void setGelLaneKey(Integer gelLaneKey) {
		this.gelLaneKey = gelLaneKey;
	}

	public void setMgdStructureKey(Integer mgdStructureKey) {
		this.mgdStructureKey = mgdStructureKey;
	}

	public void setStructureSeq(Integer structureSeq) {
		this.structureSeq = structureSeq;
	}

	public void setPrintname(String printname) {
		this.printname = printname;
	}
}