package mgi.frontend.datamodel;

import javax.persistence.*;

/**
 * GenotypeAnnotation
 * @author jsb
 * An object of this class represents one annotation for a genotype.
 */
@Entity
@Table (name="genotype_annotation")
public class GenotypeAnnotation extends Annotation {
	protected Integer genotypeKey;
	protected String headerTerm;

    // ================= Getters and Setters ===================== //
	
	@Column(name="genotype_key")
	public Integer getGenotypeKey() {
		return genotypeKey;
	}
	
	@Column(name="header_term")
	public String getHeaderTerm() {
		return headerTerm;
	}

	public void setGenotypeKey(Integer genotypeKey) {
		this.genotypeKey = genotypeKey;
	}
	
	public void setHeaderTerm(String headerTerm) {
		this.headerTerm = headerTerm;
	}

	@Override
	public String toString() {
		return "GenotypeAnnotation ["
				+ (genotypeKey != null ? "genotypeKey=" + genotypeKey + ", "
						: "")
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
