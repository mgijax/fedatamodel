package mgi.frontend.datamodel;

import javax.persistence.*;

/**
 * MarkerAnnotation
 * @author mhall, jsb
 * This class extends the annotation class, so most of its methods are 
 * inherited.
 */
@Entity
@Table (name="marker_annotation")
public class MarkerAnnotation extends Annotation {
	protected Integer markerKey;
	protected Integer sequenceNum;

    // ================= Getters and Setters ===================== //
	
	@Column(name="marker_key")
	public Integer getMarkerKey() {
		return markerKey;
	}

	public void setMarkerKey(Integer markerKey) {
		this.markerKey = markerKey;
	}

	@Column(name="sequence_num")
	public Integer getSequenceNum() {
		return sequenceNum;
	}

	public void setSequenceNum(Integer sequenceNum) {
		this.sequenceNum = sequenceNum;
	}

	@Override
	public String toString() {
		return "MarkerAnnotation ["
				+ (markerKey != null ? "markerKey=" + markerKey + ", " : "")
				+ (annotationKey != null ? "annotationKey=" + annotationKey
						+ ", " : "")
				+ (annotationType != null ? "annotationType=" + annotationType
						+ ", " : "")
				+ (evidenceTerm != null ? "evidenceTerm=" + evidenceTerm + ", "
						: "")
				+ (jnumID != null ? "jnumID=" + jnumID + ", " : "")
				+ (qualifier != null ? "qualifier=" + qualifier + ", " : "")
				+ (referenceKey != null ? "referenceKey=" + referenceKey + ", "
						: "") + (term != null ? "term=" + term + ", " : "")
				+ (termID != null ? "termID=" + termID + ", " : "")
				+ "uniqueKey=" + uniqueKey + ", "
				+ (vocabName != null ? "vocabName=" + vocabName : "") + "]";
	}
}
