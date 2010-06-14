package mgi.frontend.datamodel;

import javax.persistence.*;

@MappedSuperclass
public class Annotation implements SortableObject {

	public static String TYPE = "Annotation.Type";
	public static String VOCAB_NAME = "Annotation.Vocab";
	public static String TERM = "Annotation.Term";
	public static String TERM_ID = "Annotation.TermID";
	public static String QUALIFIER = "Annotation.Qualifier";
	public static String EVIDENCE = "Annotation.Evidence";
	public static String JNUM = "Annotation.Jnum";
	
	// subclasses will need to add their particular objects and getter/setter methods
	protected int uniqueKey;
	protected String annotationType;
	protected String vocabName;
	protected String term;
	protected String termID;
	protected Integer annotationKey;
	protected String qualifier;
	protected String evidenceTerm;
	protected Integer referenceKey;
	protected String jnumID;
	
	public Annotation() {}

	@Id
	public int getUniqueKey() {
		return uniqueKey;
	}

	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	public String getQualifier() {
		return qualifier;
	}

	public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}

	public String getAnnotationType() {
		return annotationType;
	}

	public void setAnnotationType(String annotationType) {
		this.annotationType = annotationType;
	}

	public String getVocabName() {
		return vocabName;
	}

	public void setVocabName(String vocabName) {
		this.vocabName = vocabName;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getTermID() {
		return termID;
	}

	public void setTermID(String termID) {
		this.termID = termID;
	}

	public Integer getAnnotationKey() {
		return annotationKey;
	}

	public void setAnnotationKey(Integer annotationKey) {
		this.annotationKey = annotationKey;
	}

	public String getEvidenceTerm() {
		return evidenceTerm;
	}

	public void setEvidenceTerm(String evidenceTerm) {
		this.evidenceTerm = evidenceTerm;
	}

	public Integer getReferenceKey() {
		return referenceKey;
	}

	public void setReferenceKey(Integer referenceKey) {
		this.referenceKey = referenceKey;
	}

	public String getJnumID() {
		return jnumID;
	}

	public void setJnumID(String jnumID) {
		this.jnumID = jnumID;
	}

	@Override
	public Comparable getComparableValue(String fieldname) 
			throws NoSuchFieldException {
		Comparable out;
		if (fieldname.equals (QUALIFIER)) {
			out = this.getQualifier();
		} else if (fieldname.equals (TYPE)) {
			out = this.getAnnotationType();
		} else if (fieldname.equals (VOCAB_NAME)) {
			out = this.getVocabName();
		} else if (fieldname.equals (TERM)) {
			out = this.getTerm();
		} else if (fieldname.equals (TERM_ID)) {
			out = this.getTermID();
		} else if (fieldname.equals (JNUM)) {
			out = this.getJnumID();
		} else if (fieldname.equals (EVIDENCE)) {
			out = this.getEvidenceTerm();
		} else {
			throw new NoSuchFieldException("Unknown field: " + fieldname);
		}
		return out;
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
