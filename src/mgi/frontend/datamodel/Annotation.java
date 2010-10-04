package mgi.frontend.datamodel;

import javax.persistence.*;

/**
 * Annotation
 * @author mhall, jsb
 * The base annotation class, shared across all vocabularies.
 */
@MappedSuperclass
public class Annotation {
	
	protected Integer annotationKey;
	protected String annotationType;
	protected String evidenceTerm;
	protected String jnumID;
	protected String qualifier;
	protected Integer referenceKey;
	protected String term;
	protected String termID;
	// subclasses will need to add their particular objects and getter/setter methods
	protected int uniqueKey;
	protected String vocabName;
	
	
    // ================= Getters and Setters ===================== //

	public Integer getAnnotationKey() {
		return annotationKey;
	}

	public String getAnnotationType() {
		return annotationType;
	}

	public String getEvidenceTerm() {
		return evidenceTerm;
	}

	public String getJnumID() {
		return jnumID;
	}

	public String getQualifier() {
		return qualifier;
	}

	public Integer getReferenceKey() {
		return referenceKey;
	}

	public String getTerm() {
		return term;
	}

	public String getTermID() {
		return termID;
	}

	@Id
	public int getUniqueKey() {
		return uniqueKey;
	}

	public String getVocabName() {
		return vocabName;
	}

	public void setAnnotationKey(Integer annotationKey) {
		this.annotationKey = annotationKey;
	}

	public void setAnnotationType(String annotationType) {
		this.annotationType = annotationType;
	}

	public void setEvidenceTerm(String evidenceTerm) {
		this.evidenceTerm = evidenceTerm;
	}

	public void setJnumID(String jnumID) {
		this.jnumID = jnumID;
	}

	public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}

	public void setReferenceKey(Integer referenceKey) {
		this.referenceKey = referenceKey;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public void setTermID(String termID) {
		this.termID = termID;
	}

	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	public void setVocabName(String vocabName) {
		this.vocabName = vocabName;
	}

	@Override
	public String toString() {
		return "Annotation ["
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
