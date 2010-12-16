package mgi.frontend.datamodel;

import javax.persistence.*;

/**
 * AlleleAnnotation
 * @author jsb
 * An object of this class represents one annotation for an allele.
 */
@Entity
@Table (name="allele_annotation")
public class AlleleAnnotation extends Annotation {
	protected Integer alleleKey;
	protected String headerTerm;

    // ================= Getters and Setters ===================== //
	
	@Column(name="allele_key")
	public Integer getAlleleKey() {
		return alleleKey;
	}
	
	@Column(name="header_term")
	public String getHeaderTerm() {
		return headerTerm;
	}

	public void setAlleleKey(Integer alleleKey) {
		this.alleleKey = alleleKey;
	}
	
	public void setHeaderTerm(String headerTerm) {
		this.headerTerm = headerTerm;
	}

	@Override
	public String toString() {
		return "AlleleAnnotation ["
				+ (alleleKey != null ? "alleleKey=" + alleleKey + ", " : "")
				+ (headerTerm != null ? "headerTerm=" + headerTerm + ", " : "")
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
