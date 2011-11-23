package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="batch_marker_go_annotations")
public class BatchMarkerGoAnnotation {

    private int uniqueKey;
    private int markerKey;
    private String goId;
    private String goTerm;
    private String evidenceCode;
    private int sequenceNum;

	@Id
	@Column(name="unique_key")
	public int getUniqueKey() {
		return uniqueKey;
	}

	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	@Column(name="marker_key")
	public int getMarkerKey() {
		return markerKey;
	}

	public void setMarkerKey(int markerKey) {
		this.markerKey = markerKey;
	}

	@Column(name="go_id")
	public String getGoId() {
		return goId;
	}

	public void setGoId(String goId) {
		this.goId = goId;
	}

	@Column(name="go_term")
	public String getGoTerm() {
		return goTerm;
	}

	public void setGoTerm(String goTerm) {
		this.goTerm = goTerm;
	}

	@Column(name="evidence_code")
	public String getEvidenceCode() {
		return evidenceCode;
	}

	public void setEvidenceCode(String evidenceCode) {
		this.evidenceCode = evidenceCode;
	}

	@Column(name="sequence_num")
	public int getSequenceNum() {
		return sequenceNum;
	}

	public void setSequenceNum(int sequenceNum) {
		this.sequenceNum = sequenceNum;
	}

	@Override
	public String toString() {
		return "BatchMarkerGoAnnotation [evidenceCode=" + evidenceCode
				+ ", goId=" + goId + ", goTerm=" + goTerm + ", markerKey="
				+ markerKey + ", uniqueKey=" + uniqueKey + "]";
	}
}
