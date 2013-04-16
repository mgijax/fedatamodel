package mgi.frontend.datamodel;

import javax.persistence.*;

import java.util.List;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

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
	@OrderBy ("sequenceNum")
	public List<DiseaseRow> getDiseaseRows() {
		return diseaseRows;
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
