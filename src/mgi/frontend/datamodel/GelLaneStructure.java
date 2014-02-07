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
	private Integer structureSeq;
	private String printname;
	private VocabTerm structure;

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

	@ManyToOne (targetEntity=VocabTerm.class, fetch=FetchType.LAZY)
	@JoinColumn(name="mgd_structure_key")
	public VocabTerm getStructure() {
		return structure;
	}

	@Column(name="printname")
	public String getPrintname() {
		return printname;
	}

	@Column(name="structure_seq")
	public Integer getStructureSeq() {
		return structureSeq;
	}

	@Transient
	public String getPrimaryId() {
		return structure.getPrimaryId();
	}

    // ===================== Setters ===================== //
	
	public void setUniqueKey(Integer uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	public void setGelLaneKey(Integer gelLaneKey) {
		this.gelLaneKey = gelLaneKey;
	}

	public void setStructure(VocabTerm structure) {
		this.structure = structure;
	}

	public void setStructureSeq(Integer structureSeq) {
		this.structureSeq = structureSeq;
	}

	public void setPrintname(String printname) {
		this.printname = printname;
	}
}
