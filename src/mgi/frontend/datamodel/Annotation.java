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

	@Column(name="annotation_key")
	public Integer getAnnotationKey() {
		return annotationKey;
	}

	@Column(name="annotation_type")
	public String getAnnotationType() {
		return annotationType;
	}

	@Column(name="evidence_term")
	public String getEvidenceTerm() {
		return evidenceTerm;
	}

	@Column(name="jnum_id")
	public String getJnumID() {
		return jnumID;
	}

	@Column(name="qualifier")
	public String getQualifier() {
		return qualifier;
	}

	@Column(name="reference_key")
	public Integer getReferenceKey() {
		return referenceKey;
	}

	@Column(name="term")
	public String getTerm() {
		return term;
	}

	@Column(name="term_id")
	public String getTermID() {
		return termID;
	}

	@Id
	@Column(name="unique_key")
	public int getUniqueKey() {
		return uniqueKey;
	}

	@Column(name="vocab_name")
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
