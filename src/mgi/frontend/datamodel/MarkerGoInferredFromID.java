package mgi.frontend.datamodel;

import javax.persistence.*;

/**
 * MarkerGoInferredFromID
 * @author jsb
 * One of these objects represents one accession ID used in drawing an
 * inference to make one of a marker's GO annotations.  
 */
@Entity
@Table (name="marker_go_inferred_from_id")
public class MarkerGoInferredFromID extends AccessionID {
	
	private int goAnnotationKey;
	private int sequenceNum;

	@Column(name="go_annotation_key")
	public int getGoAnnotationKey() {
		return goAnnotationKey;
	}
	
	@Column(name="sequence_num")
	public int getSequenceNum() {
		return sequenceNum;
	}
	
	public void setGoAnnotationKey(int goAnnotationKey) {
		this.goAnnotationKey = goAnnotationKey;
	}
	
	public void setSequenceNum(int sequenceNum) {
		this.sequenceNum = sequenceNum;
	}

	@Override
	public String toString() {
		return "MarkerGoInferredFromID [goAnnotationKey=" + goAnnotationKey
				+ ", sequenceNum=" + sequenceNum + ", "
				+ (accID != null ? "accID=" + accID + ", " : "")
				+ "isPreferred=" + isPreferred + ", isPrivate=" + isPrivate
				+ ", "
				+ (logicalDB != null ? "logicalDB=" + logicalDB + ", " : "")
				+ "uniqueKey=" + uniqueKey + "]";
	}
}
