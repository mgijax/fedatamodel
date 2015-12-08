package mgi.frontend.datamodel;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;

/* MPAnnotation - is an MP annotation tied to an MPGenotype object
 */
@Entity
@Table (name="marker_mp_annotation")
public class MPAnnotation {
	private int mpGenotypeKey;
	private int mpAnnotationKey;
	private String qualifier;
	private int termKey;
	private String term;
	private String termID;
	private int sequenceNum;
	private List<MPAnnotationReference> mpReferences;
	private List<Term> headers;

	//--- getters ---//

	@Id
	@Column(name="mp_annotation_key")
	public int getMpAnnotationKey() {
		return mpAnnotationKey;
	}

	@Column(name="mp_genotype_key")
	public int getMpGenotypeKey() {
		return mpGenotypeKey;
	}

	@Column(name="qualifier")
	public String getQualifier() {
		return qualifier;
	}

	@Column(name="term_key")
	public int getTermKey() {
		return termKey;
	}

	@Column(name="term")
	public String getTerm() {
		return term;
	}

	@Column(name="term_id")
	public String getTermID() {
		return termID;
	}

	@Column(name="sequence_num")
	public int getSequenceNum() {
		return sequenceNum;
	}

	@OneToMany(targetEntity=MPAnnotationReference.class)
	@JoinColumn(name="mp_annotation_key")
	@BatchSize(size=20)
	@OrderBy("sequence_num")
	public List<MPAnnotationReference> getMpReferences() {
		return mpReferences;
	}

	@OneToMany(targetEntity=Term.class)
	@JoinTable (name="marker_mp_annotation_to_header",
		joinColumns=@JoinColumn(name="mp_annotation_key"),
		inverseJoinColumns=@JoinColumn(name="term_key")
		)
	@BatchSize(size=10)
	@OrderBy("sequence_num")
	public List<Term> getHeaders() {
		return this.headers;
	}

	/* hacked to generate header abbreviations, rathern than pulling them
	* from the now-null abbreviations in the database
	*/
	@Transient
	public List<String> getMPHeaders() {
		List<String> abbrevs = new ArrayList<String>();

		for (Term t : this.getHeaders()) {
			//abbrevs.add(t.getAbbreviation());
			abbrevs.add(t.getTerm().replace(" phenotype", ""));
		}
		return abbrevs;
	}

	//--- setters ---//

	public void setMpAnnotationKey(int mpAnnotationKey) {
		this.mpAnnotationKey = mpAnnotationKey;
	}
	
	public void setMpGenotypeKey(int mpGenotypeKey) {
		this.mpGenotypeKey = mpGenotypeKey;
	}

	public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}

	public void setTermKey(int termKey) {
		this.termKey = termKey;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public void setTermID(String termID) {
		this.termID = termID;
	}

	public void setSequenceNum(int sequenceNum) {
		this.sequenceNum = sequenceNum;
	}
	
	public void setMpReferences (List<MPAnnotationReference> mpReferences) {
		this.mpReferences = mpReferences;
	}

	public void setHeaders(List<Term> headers) {
		this.headers = headers;
	}

	//--- convenience methods ---//
	
	public String toString() {
		return "MPAnnotation [mpAnnotationKey=" + mpAnnotationKey
			+ ", mpGenotypeKey=" + mpGenotypeKey
			+ (qualifier != null ? ", qualifier=" + qualifier : "")
			+ (term != null ? ", term=" + term : "")
			+ (termID != null ? ", termID=" + termID : "")
			+ "]";
	}
}
