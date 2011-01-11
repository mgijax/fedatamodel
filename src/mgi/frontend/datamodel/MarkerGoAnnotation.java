package mgi.frontend.datamodel;

import javax.persistence.*;
import java.util.List;

/**
 * MarkerGoAnnotation
 * @author jsb
 * One of these objects represents one row on the GO/marker association
 * page.  It is the annotation from a GO term to a marker with a given
 * qualifier, evidence code, set of inferred-from IDs, and set of 
 * references.
 */
@Entity
@Table (name="marker_go_annotation")
public class MarkerGoAnnotation  {
	
    private int goAnnotationKey;
	private int markerKey;
	private String dagName;
	private String qualifier;
	private String term;
	private String termID;
	private String evidenceCode;
	private int referenceCount;
	private int inferredIdCount;
	private int sequenceNum;
	private List<MarkerGoReference> references;
	private List<MarkerGoInferredFromID> inferredFromIDs;
	
	@Column(name="dag_name")
	public String getDagName() {
		return dagName;
	}

	@Column(name="evidence_code")
	public String getEvidenceCode() {
		return evidenceCode;
	}

	@Id
	@Column(name="go_annotation_key")
	public int getGoAnnotationKey() {
		return goAnnotationKey;
	}

	@OneToMany (targetEntity=MarkerGoInferredFromID.class)
	@JoinColumn(name="go_annotation_key")
	@OrderBy("sequenceNum")
	public List<MarkerGoInferredFromID> getInferredFromIDs() {
		return inferredFromIDs;
	}

	@Column(name="inferred_id_count")
	public int getInferredIdCount() {
		return inferredIdCount;
	}
	
	@Column(name="marker_key")
	public int getMarkerKey() {
		return markerKey;
	}
	
	@Column(name="qualifier")
	public String getQualifier() {
		return qualifier;
	}
	
	@Column(name="reference_count")
	public int getReferenceCount() {
		return referenceCount;
	}
	
	@OneToMany (targetEntity=MarkerGoReference.class)
	@JoinColumn(name="go_annotation_key")
	@OrderBy("sequenceNum")
	public List<MarkerGoReference> getReferences() {
		return references;
	}
	
	@Column(name="sequence_num")
	public int getSequenceNum() {
		return sequenceNum;
	}
	
	@Column(name="term")
	public String getTerm() {
		return term;
	}
	
	@Column(name="term_id")
	public String getTermID() {
		return termID;
	}
	
	public void setDagName(String dagName) {
		this.dagName = dagName;
	}
	
	public void setEvidenceCode(String evidenceCode) {
		this.evidenceCode = evidenceCode;
	}
	
	public void setGoAnnotationKey(int goAnnotationKey) {
		this.goAnnotationKey = goAnnotationKey;
	}
	
	public void setInferredFromIDs(List<MarkerGoInferredFromID> inferredFromIDs) {
		this.inferredFromIDs = inferredFromIDs;
	}
	
	public void setInferredIdCount(int inferredIdCount) {
		this.inferredIdCount = inferredIdCount;
	}
	
	public void setMarkerKey(int markerKey) {
		this.markerKey = markerKey;
	}
	
	public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}
	
	public void setReferenceCount(int referenceCount) {
		this.referenceCount = referenceCount;
	}
	
	public void setReferences(List<MarkerGoReference> references) {
		this.references = references;
	}
	
	public void setSequenceNum(int sequenceNum) {
		this.sequenceNum = sequenceNum;
	}
	
	public void setTerm(String term) {
		this.term = term;
	}
	
	public void setTermID(String termID) {
		this.termID = termID;
	}

	@Override
	public String toString() {
		return "MarkerGoAnnotation ["
				+ (dagName != null ? "dagName=" + dagName + ", " : "")
				+ (evidenceCode != null ? "evidenceCode=" + evidenceCode + ", "
						: "") + "goAnnotationKey=" + goAnnotationKey
				+ ", inferredIdCount=" + inferredIdCount + ", markerKey="
				+ markerKey + ", "
				+ (qualifier != null ? "qualifier=" + qualifier + ", " : "")
				+ "referenceCount=" + referenceCount + ", sequenceNum="
				+ sequenceNum + ", "
				+ (term != null ? "term=" + term + ", " : "")
				+ (termID != null ? "termID=" + termID : "") + "]";
	}
}
