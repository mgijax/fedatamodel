package mgi.frontend.datamodel;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Annotation
 * @author mhall, jsb
 * The base annotation class, shared across all annotation types.
 */
@Entity
@Table(name="annotation")
@SecondaryTables (
    {
      @SecondaryTable (name="annotation_sequence_num", pkJoinColumns= {
        @PrimaryKeyJoinColumn(name="annotation_key", referencedColumnName="annotation_key") } )
    }
    )
public class Annotation {

	private int annotationKey;
	private String annotationType;
	private String byAnnotationType;
	private String byDagStructure;
	private String byTermAlpha;
	private String byVocab;
	private String dagName;
	private String evidenceCode;
	private List <AnnotationInferredFromID> inferredFromList;
	private String inferredIDCount;
	private String objectType;
	private String qualifier;
	private String referenceCount;
	private List <Reference> references;
	private String term;
	private String termID;
	private String vocabName;
	private List<Marker> markers;
	private List<Genotype> genotypes;

	@Id
	@Column(name="annotation_key")
    public int getAnnotationKey() {
        return annotationKey;
    }

	@Column(name="annotation_type")
    public String getAnnotationType() {
        return annotationType;
    }

    @Column(table="annotation_sequence_num", name="by_annotation_type")
    public String getByAnnotationType() {
        return byAnnotationType;
    }

    @Column(table="annotation_sequence_num", name="by_dag_structure")
    public String getByDagStructure() {
        return byDagStructure;
    }

    @Column(table="annotation_sequence_num", name="by_term_alpha")
    public String getByTermAlpha() {
        return byTermAlpha;
    }

    @Column(table="annotation_sequence_num", name="by_vocab")
    public String getByVocab() {
        return byVocab;
    }

    @Column(name="dag_name")
    public String getDagName() {
        return dagName;
    }

    @Column(name="evidence_code")
    public String getEvidenceCode() {
        return evidenceCode;
    }

    @Transient
    public String getInferredFrom() {
	StringBuffer sb = new StringBuffer();
	for (AnnotationInferredFromID id : this.getInferredFromList()) {
	    if (sb.length() > 0) {
		sb.append (", ");
	    }
	    sb.append (id.getAccID());
	}
	return sb.toString();
    }

    @OneToMany (fetch=FetchType.LAZY)
    @JoinColumn(name="annotation_key")
    public List<AnnotationInferredFromID> getInferredFromList() {
        return inferredFromList;
    }

    @Column(name="inferred_id_count")
    public String getInferredIDCount() {
        return inferredIDCount;
    }

    /** returns a collection of genotypes for this annotation
     */
    @OneToMany (targetEntity=Genotype.class)
    @JoinTable (name="genotype_to_annotation",
	joinColumns=@JoinColumn(name="annotation_key"),
	inverseJoinColumns=@JoinColumn(name="genotype_key")
	)
    public List<Genotype> getGenotypes() {
        return genotypes;
    }

    /** returns a collection of markers for this annotation
     */
    @OneToMany (targetEntity=Marker.class)
    @JoinTable (name="marker_to_annotation",
	joinColumns=@JoinColumn(name="annotation_key"),
	inverseJoinColumns=@JoinColumn(name="marker_key")
	)
    public List<Marker> getMarkers() {
        return markers;
    }

    @Column(name="object_type")
    public String getObjectType() {
        return objectType;
    }

    public String getQualifier() {
        return qualifier;
    }

    @Column(name="reference_count")
    public String getReferenceCount() {
        return referenceCount;
    }

    @OneToMany
    @JoinTable (name="annotation_reference",
            joinColumns=@JoinColumn(name="annotation_key"),
            inverseJoinColumns=@JoinColumn(name="reference_key")
            )

    public List<Reference> getReferences() {
        return references;
    }

    public String getTerm() {
        return term;
    }

    @Column(name="term_id")
    public String getTermID() {
        return termID;
    }

    @Column(name="vocab_name")
    public String getVocabName() {
        return vocabName;
    }

    public void setAnnotationKey(int annotationKey) {
        this.annotationKey = annotationKey;
    }

    public void setAnnotationType(String annotationType) {
        this.annotationType = annotationType;
    }
    public void setByAnnotationType(String byAnnotationType) {
        this.byAnnotationType = byAnnotationType;
    }
    public void setByDagStructure(String byDagStructure) {
        this.byDagStructure = byDagStructure;
    }
    public void setByTermAlpha(String byTermAlpha) {
        this.byTermAlpha = byTermAlpha;
    }
    public void setByVocab(String byVocab) {
        this.byVocab = byVocab;
    }
    public void setDagName(String dagName) {
        this.dagName = dagName;
    }
    public void setEvidenceCode(String evidenceCode) {
        this.evidenceCode = evidenceCode;
    }
    public void setInferredFromList(List<AnnotationInferredFromID> inferredFromList) {
        this.inferredFromList = inferredFromList;
    }
    public void setInferredIDCount(String inferredIDCount) {
        this.inferredIDCount = inferredIDCount;
    }
    public void setGenotypes(List<Genotype> genotypes) {
	this.genotypes = genotypes;
    }
    public void setMarkers(List<Marker> markers) {
	this.markers = markers;
    }
    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }
    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }
    public void setReferenceCount(String referenceCount) {
        this.referenceCount = referenceCount;
    }
    public void setReferences(List<Reference> references) {
        this.references = references;
    }
    public void setTerm(String term) {
        this.term = term;
    }
    public void setTermID(String termID) {
        this.termID = termID;
    }

    public void setVocabName(String vocabName) {
        this.vocabName = vocabName;
    }

}
