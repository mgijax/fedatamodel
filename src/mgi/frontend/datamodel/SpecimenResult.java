package mgi.frontend.datamodel;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;

/**
 * SpecimenResult
 * This object represents the data for one specimen result.
 */
@Entity
@Table(name="specimen_result")
public class SpecimenResult {

	private Integer specimenResultKey;
	private Integer specimenKey;
	private Integer specimenResultSeq;
	private VocabTerm structureTerm;
    private String  structure;
	private String  level;
	private String  pattern;
	private String  note;
    List<ImagePane> imagepanes;

	@Id
	@Column(name="specimen_result_key")
	public Integer getSpecimenResultKey() {
		return specimenResultKey;
	}

	@Column(name="specimen_key")
	public Integer getSpecimenKey() {
		return specimenKey;
	}

	@Column(name="specimen_result_seq")
	public Integer getSpecimenResultSeq() {
		return specimenResultSeq;
	}

	@ManyToOne (targetEntity=VocabTerm.class, fetch=FetchType.LAZY)
	@JoinColumn(name="structure_mgd_key")
	public VocabTerm getStructureTerm() {
		return structureTerm;
	}

	@Column(name="structure")
	public String getStructure() {
		return structure;
	}

	@Column(name="level")
	public String getLevel() {
		return level;
	}

	@Column(name="pattern")
	public String getPattern() {
		return pattern;
	}

	@Column(name="note")
	public String getNote() {
		return note;
	}

    @OneToMany
    @JoinTable (name="specimen_result_to_imagepane",
            joinColumns=@JoinColumn(name="specimen_result_key"),
            inverseJoinColumns=@JoinColumn(name="imagepane_key")
            )
    @BatchSize(size=50)
//    @OrderBy ("jnumID,figureLabel")
    public List<ImagePane> getImagepanes() {
        return imagepanes;
    }

	public void setSpecimenResultKey(int specimenResultKey) {
		this.specimenResultKey = specimenResultKey;
	}

	public void setSpecimenKey(int specimenKey) {
		this.specimenKey = specimenKey;
	}

	public void setSpecimenResultSeq(int specimenResultSeq) {
		this.specimenResultSeq = specimenResultSeq;
	}

	public void setStructureTerm(VocabTerm structureTerm) {
		this.structureTerm = structureTerm;
	}

	public void setStructure(String structure) {
		this.structure = structure;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public void setNote(String note) {
		this.note = note;
	}

    public void setImagepanes(List<ImagePane> imagepanes) {
      this.imagepanes = imagepanes;
    }


//	@Override
//	public String toString() {
//		return "AssaySpecimen ["
//				+ "assayKey=" + assayKey + ", "
//				+ "specimenKey=" + specimenKey + ", "
//				+ "specimenLable=" + specimenLable + ", "
//				+ "]";
//	}
}
