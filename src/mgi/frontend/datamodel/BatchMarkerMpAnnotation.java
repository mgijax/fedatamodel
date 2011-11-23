package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="batch_marker_mp_annotations")
public class BatchMarkerMpAnnotation {

    private int uniqueKey;
    private int markerKey;
    private String mpId;
    private String mpTerm;
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

	@Column(name="mp_id")
	public String getMpId() {
		return mpId;
	}

	public void setMpId(String mpId) {
		this.mpId = mpId;
	}

	@Column(name="mp_term")
	public String getMpTerm() {
		return mpTerm;
	}

	public void setMpTerm(String mpTerm) {
		this.mpTerm = mpTerm;
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
		return "BatchMarkerMpAnnotation [markerKey=" + markerKey + ", mpId="
				+ mpId + ", mpTerm=" + mpTerm + ", sequenceNum=" + sequenceNum
				+ ", uniqueKey=" + uniqueKey + "]";
	}

}
