package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/* MPAnnotationReference - is one reference for an MPAnnotation object
 */
@Entity
@Table (name="marker_mp_annotation_reference")
public class MPAnnotationReference {
	private int mpAnnotationKey;
	private int mpReferenceKey;
	private int referenceKey;
	private String jnumID;
	private int sequenceNum;

	//--- getters ---//

	@Id
	@Column(name="mp_reference_key")
	public int getMpReferenceKey() {
		return mpReferenceKey;
	}

	@Column(name="mp_annotation_key")
	public int getMpAnnotationKey() {
		return mpAnnotationKey;
	}

	@Column(name="reference_key")
	public int getReferenceKey() {
		return referenceKey;
	}

	@Column(name="jnum_id")
	public String getJnumID() {
		return jnumID;
	}

	@Column(name="sequence_num")
	public int getSequenceNum() {
		return sequenceNum;
	}

	//--- setters ---//

	public void setMpReferenceKey(int mpReferenceKey) {
		this.mpReferenceKey = mpReferenceKey;
	}

	public void setMpAnnotationKey(int mpAnnotationKey) {
		this.mpAnnotationKey = mpAnnotationKey;
	}
	
	public void setReferenceKey(int referenceKey) {
		this.referenceKey = referenceKey;
	}

	public void setJnumID(String jnumID) {
		this.jnumID = jnumID;
	}

	public void setSequenceNum(int sequenceNum) {
		this.sequenceNum = sequenceNum;
	}
	
	//--- convenience methods ---//
	
	public String toString() {
		return "MPAnnotationReference [mpReferenceKey=" + mpReferenceKey
			+ ", mpAnnotationKey=" + mpAnnotationKey
			+ ", referenceKey=" + referenceKey
			+ (jnumID != null ? ", jnumID=" + jnumID : "")
			+ "]";
	}
}
