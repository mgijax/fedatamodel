package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * MarkerGoReference
 * @author jsb
 * One of these objects represents one reference used as a citation for
 * for one of a marker's GO annotations.  
 */
@Entity
@Table (name="marker_go_reference")
public class MarkerGoReference  {
	
    private int uniqueKey;
	private int goAnnotationKey;
	private int referenceKey;
	private String jnumID;
	private int sequenceNum;

	@Column(name="go_annotation_key")
	public int getGoAnnotationKey() {
		return goAnnotationKey;
	}
	
	@Column(name="jnum_id")
	public String getJnumID() {
		return jnumID;
	}
	
	@Column(name="reference_key")
	public int getReferenceKey() {
		return referenceKey;
	}
	
	@Column(name="sequence_num")
	public int getSequenceNum() {
		return sequenceNum;
	}
	
	@Id
	@Column(name="unique_key")
	public int getUniqueKey() {
		return uniqueKey;
	}
	
	public void setGoAnnotationKey(int goAnnotationKey) {
		this.goAnnotationKey = goAnnotationKey;
	}
	
	public void setJnumID(String jnumID) {
		this.jnumID = jnumID;
	}
	
	public void setReferenceKey(int referenceKey) {
		this.referenceKey = referenceKey;
	}
	
	public void setSequenceNum(int sequenceNum) {
		this.sequenceNum = sequenceNum;
	}
	
	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	@Override
	public String toString() {
		return "MarkerGoReference [goAnnotationKey=" + goAnnotationKey + ", "
				+ (jnumID != null ? "jnumID=" + jnumID + ", " : "")
				+ "referenceKey=" + referenceKey + ", sequenceNum="
				+ sequenceNum + ", uniqueKey=" + uniqueKey + "]";
	}
}
