package mgi.frontend.datamodel;

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
 * DiseaseGroup
 * @author jsb
 * This object represents one group of rows for a disease detail page.
 */
@Entity
@Table(name="disease_group")
public class DiseaseGroup {

	private int diseaseGroupKey;
	private int diseaseKey;
	private String groupType;
	private int sequenceNum;
	private List<DiseaseGroupRow> diseaseGroupRows;
	private List<DiseaseRow> diseaseRows;

    // ================= Getters and Setters ===================== //

	@Id
	@Column(name="disease_group_key")
	public int getDiseaseGroupKey() {
		return diseaseGroupKey;
	}

	@Column(name="disease_key")
	public int getDiseaseKey() {
		return diseaseKey;
	}

	@OneToMany (targetEntity=DiseaseRow.class)
	@JoinColumn (name="disease_group_key")
	@BatchSize(size=100)
	@OrderBy ("sequenceNum")
	public List<DiseaseRow> getDiseaseRows() {
		return diseaseRows;
	}

	@OneToMany (targetEntity=DiseaseGroupRow.class)
	@JoinColumn (name="disease_group_key")
	@BatchSize(size=100)
	@OrderBy ("annotatedDisease")
	public List<DiseaseGroupRow> getDiseaseGroupRows() {
		return diseaseGroupRows;
	}

	@Column(name="group_type")
	public String getGroupType() {
		return groupType;
	}

	@Column(name="sequence_num")
	public int getSequenceNum() {
		return sequenceNum;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}

	public void setDiseaseGroupKey(int diseaseGroupKey) {
		this.diseaseGroupKey = diseaseGroupKey;
	}

	public void setDiseaseKey(int diseaseKey) {
		this.diseaseKey = diseaseKey;
	}

	public void setDiseaseRows(List<DiseaseRow> diseaseRows) {
		this.diseaseRows = diseaseRows;
	}

	public void setDiseaseGroupRows(List<DiseaseGroupRow> diseaseGroupRows) {
		this.diseaseGroupRows = diseaseGroupRows;
	}

	public void setSequenceNum(int sequenceNum) {
		this.sequenceNum = sequenceNum;
	}

	@Override
	public String toString() {
		return "DiseaseGroup [diseaseGroupKey="
				+ diseaseGroupKey
				+ ", diseaseKey="
				+ diseaseKey
				+ ", "
				+ groupType
				+ ", "
				+ sequenceNum + "]";
	}
}
