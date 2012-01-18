package mgi.frontend.datamodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Marker
 * @author mhall, jsb
 * This is the core Marker object.
 */

@Entity
@Table(name="marker")
@SecondaryTables (
		{ @SecondaryTable (name="marker_counts", pkJoinColumns= {
			@PrimaryKeyJoinColumn(name="marker_key", referencedColumnName="marker_key") } )
		} )
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Marker {

	private List<MarkerAlias> aliases;
	private List<MarkerAlleleAssociation> alleleAssociations;
	private List<BatchMarkerAllele> batchMarkerAlleles;
	private List<BatchMarkerSnp> batchMarkerSnps;
	private List<Annotation> annotations;
	private List<BatchMarkerGoAnnotation> batchMarkerGoAnnotations;
	private List<BatchMarkerMpAnnotation> batchMarkerMpAnnotations;
	private List<MarkerBiotypeConflict> biotypeConflicts;
	private Integer countOfAlleles;
	private Integer countOfGOTerms;
	private Integer countOfGxdAssays;
	private Integer countOfOrthologs;
	private Integer countOfReferences;
	private Integer countOfSequences;
	private Integer countOfGxdResults;
	private Integer countOfGxdLiterature;
	private Integer countOfGxdTissues;
	private Integer countOfGxdImages;
	private Integer countOfMappingExperiments;
	private Integer countOfRefSeqSequences;
	private Integer countOfUniProtSequences;
	private Integer countOfCdnaSources;
	private Integer countOfMicroarrayProbesets;
	private int hasGOGraph;
	private int hasGOOrthologyGraph;
	private List<MarkerID> ids;
	private List<MarkerLocation> locations;
	private List<MarkerCountSetItem> countSetItems;
	private int markerKey;
	private String markerSubtype;
	private String markerType;
	private String name;
	private List<MarkerNote> notes;
	private String organism;
	private List<MarkerOrthology> orthologousMarkers;
    private String primaryID;
    private List<MarkerReferenceAssociation> referenceAssociations;
    private List<MarkerSequenceAssociation> sequenceAssociations;
    private List<Reference> references;
	private String status;
	private String symbol;
	private List<MarkerSynonym> synonyms;
	private List<MarkerTissueCount> markerTissueCounts;
	private Integer countOfPhenotypeImages;
	private Integer countOfHumanDiseases;
	private Integer countOfAllelesWithHumanDiseases;
	private Integer countOfAntibodies;
	private List<ExpressionAssay> expressionAssays;

    // ================= Instance Methods ===================== //

	/** used to make other convenience methods to extract only a certain types
	 * of annotations from the full List of annotations
	 */
	@Transient
	private List<Annotation> filterAnnotations (String annotationType) {
		ArrayList<Annotation> sublist = new ArrayList<Annotation>();
		Iterator<Annotation> it = this.getAnnotations().iterator();
		Annotation annotation;

		while (it.hasNext()) {
			annotation = it.next();
			if (annotation.getAnnotationType().equals(annotationType)) {
				sublist.add(annotation);
			}
		}
		return sublist;
	}

	/** used to make other convenience methods to extract only annotations
	 * made to terms from a certain DAG.  Assumes dagName is not null.
	 */
	@Transient
	private List<Annotation> filterAnnotationsByDAG (String dagName) {
		ArrayList<Annotation> sublist = new ArrayList<Annotation>();
		Iterator<Annotation> it = this.getAnnotations().iterator();
		Annotation annotation;

		while (it.hasNext()) {
			annotation = it.next();
			if (dagName.equals(annotation.getDagName())) {
				sublist.add(annotation);
			}
		}
		return sublist;
	}

	/** used to make other convenience methods to pull out certain sets
	 * of counts
	 */
	@Transient
	private List<MarkerCountSetItem> filterCountSetItems (String setType) {
		ArrayList<MarkerCountSetItem> sublist = new ArrayList<MarkerCountSetItem>();
		Iterator<MarkerCountSetItem> it = this.getCountSetItems().iterator();
		MarkerCountSetItem item;

		while (it.hasNext()) {
			item = it.next();
			if (item.getSetType().equals(setType)) {
				sublist.add(item);
			}
		}
		return sublist;
	}

	/** retrieve the first location (the highest priority one) with the given
	 * location type
	 */
	@Transient
	private MarkerLocation filterLocations(String locationType) {
		MarkerLocation loc = null;
		Iterator<MarkerLocation> it = this.getLocations().iterator();
		while (it.hasNext()) {
			loc = it.next();
			if (loc.getLocationType().equals(locationType)) {
				return loc;
			}
		}
		return null;
	}

	/** retrieve the IDs from the given logical database name
	 */
	@Transient
	private List<MarkerID> filterMarkerIDs (String logicalDatabase) {
		ArrayList<MarkerID> sublist = new ArrayList<MarkerID>();
		Iterator<MarkerID> it = this.getIds().iterator();
		MarkerID item;

		while (it.hasNext()) {
			item = it.next();
			if (item.getLogicalDB().equals(logicalDatabase)) {
				sublist.add(item);
			}
		}
		return sublist;
	}

	/** retrieve the reference with the given qualifier
	 */
	@Transient
	private Reference filterReferences (String qualifier) {
		MarkerReferenceAssociation association;
		Iterator<MarkerReferenceAssociation> it = this.getReferenceAssociations().iterator();

		while (it.hasNext()) {
			association = it.next();
			if (qualifier.equals(association.getQualifier())) {
				return association.getReference();
			}
		}
		return null;
	}

	/** retrieve the sequence with the given qualifier
	 */
	@Transient
	private Sequence filterSequences (String qualifier) {
		MarkerSequenceAssociation association;
		Iterator<MarkerSequenceAssociation> it = this.getSequenceAssociations().iterator();

		while (it.hasNext()) {
			association = it.next();
			if (qualifier.equals(association.getQualifier())) {
				return association.getSequence();
			}
		}
		return null;
	}

	/** returns a collection of aliases for the marker
	 */
	@OneToMany (targetEntity=MarkerAlias.class)
	@JoinColumn(name="marker_key")
	@OrderBy("aliasSymbol")
	@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
	public List<MarkerAlias> getAliases() {
		return aliases;
	}

	/**
	 * Returns a collection of marker/allele association objects, which
	 * extend the base association class.
	 */
	@OneToMany (targetEntity=MarkerAlleleAssociation.class)
	@JoinColumn(name="marker_key")
	public List<MarkerAlleleAssociation> getAlleleAssociations() {
		return alleleAssociations;
	}

	/** return the list of counts of alleles by type
	 */
	@Transient
	public List<MarkerCountSetItem> getAlleleCountsByType() {
		return this.filterCountSetItems("Alleles");
	}

	/** get the Allen Brain Atlas ID for this marker, or null if none
	 */
	@Transient
	public MarkerID getAllenBrainAtlasID() {
		return this.getSingleID("ABA");
	}

	/**
	 * Returns a collection of marker annotation objects, which
	 * extend the base annotation class.
	 */
    @OneToMany
    @JoinTable (name="marker_to_annotation",
            joinColumns=@JoinColumn(name="marker_key"),
            inverseJoinColumns=@JoinColumn(name="annotation_key")
            )
    @OrderBy("dagName, term")
    @Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
    public List<Annotation> getAnnotations() {
		return annotations;
	}

	/** get the ArrayExpress ID for this marker, or null if none
	 */
	@Transient
	public MarkerID getArrayExpressID() {
		return this.getSingleID("ArrayExpress");
	}

	/** Returns a list of the simple allele representations for a marker in
	 * the batch query interface.  (a very lightweight allele representation)
	 */
	@OneToMany (targetEntity=BatchMarkerAllele.class)
	@JoinColumn(name="marker_key")
	@OrderBy("sequenceNum")
	public List<BatchMarkerAllele> getBatchMarkerAlleles() {
		return batchMarkerAlleles;
	}

	@OneToMany (targetEntity=BatchMarkerGoAnnotation.class)
	@JoinColumn(name="marker_key")
	@OrderBy("sequenceNum")
	public List<BatchMarkerGoAnnotation> getBatchMarkerGoAnnotations() {
	        return batchMarkerGoAnnotations;
	}

	@OneToMany (targetEntity=BatchMarkerMpAnnotation.class)
	@JoinColumn(name="marker_key")
	@OrderBy("sequenceNum")
	public List<BatchMarkerMpAnnotation> getBatchMarkerMpAnnotations() {
        return batchMarkerMpAnnotations;
	}

	/** Returns a list of the simple SNP representations for a marker in
	 * the batch query interface.  (a very lightweight SNP representation)
	 */
	@OneToMany (targetEntity=BatchMarkerSnp.class)
	@JoinColumn(name="marker_key")
	@OrderBy("sequenceNum")
	public List<BatchMarkerSnp> getBatchMarkerSnps() {
		return batchMarkerSnps;
	}

	/** Returns a list of the biotype conflicts for this marker
	 * (for cases where different data sources disagree about what type of
	 * marker this is)
	 */
	@OneToMany (targetEntity=MarkerBiotypeConflict.class)
	@JoinColumn(name="marker_key")
	@OrderBy("sequenceNum")
	public List<MarkerBiotypeConflict> getBiotypeConflicts() {
		return biotypeConflicts;
	}

	@Column(table="marker_counts", name="allele_count")
    @JoinColumn(name="marker_key")
    public Integer getCountOfAlleles() {
        return countOfAlleles;
    }

	@Column(table="marker_counts", name="alleles_with_human_disease_count")
    @JoinColumn(name="marker_key")
	public Integer getCountOfAllelesWithHumanDiseases() {
		return countOfAllelesWithHumanDiseases;
	}

	@Column(table="marker_counts", name="antibody_count")
    @JoinColumn(name="marker_key")
	public Integer getCountOfAntibodies() {
		return countOfAntibodies;
	}

	@Column(table="marker_counts", name="cdna_source_count")
    @JoinColumn(name="marker_key")
	public Integer getCountOfCdnaSources() {
		return countOfCdnaSources;
	}

	@Column(table="marker_counts", name="go_term_count")
    @JoinColumn(name="marker_key")
    public Integer getCountOfGOTerms() {
        return countOfGOTerms;
    }

	@Column(table="marker_counts", name="gxd_assay_count")
    @JoinColumn(name="marker_key")
    public Integer getCountOfGxdAssays() {
        return countOfGxdAssays;
    }

	@Column(table="marker_counts", name="gxd_image_count")
    @JoinColumn(name="marker_key")
	public Integer getCountOfGxdImages() {
		return countOfGxdImages;
	}

	@Column(table="marker_counts", name="gxd_literature_count")
    @JoinColumn(name="marker_key")
	public Integer getCountOfGxdLiterature() {
		return countOfGxdLiterature;
	}

	@Column(table="marker_counts", name="gxd_result_count")
    @JoinColumn(name="marker_key")
	public Integer getCountOfGxdResults() {
		return countOfGxdResults;
	}

	@Column(table="marker_counts", name="gxd_tissue_count")
    @JoinColumn(name="marker_key")
	public Integer getCountOfGxdTissues() {
		return countOfGxdTissues;
	}

	@Column(table="marker_counts", name="human_disease_count")
    @JoinColumn(name="marker_key")
	public Integer getCountOfHumanDiseases() {
		return countOfHumanDiseases;
	}

	@Column(table="marker_counts", name="mapping_expt_count")
    @JoinColumn(name="marker_key")
	public Integer getCountOfMappingExperiments() {
		return countOfMappingExperiments;
	}

	@Column(table="marker_counts", name="microarray_probeset_count")
    @JoinColumn(name="marker_key")
	public Integer getCountOfMicroarrayProbesets() {
		return countOfMicroarrayProbesets;
	}

	@Column(table="marker_counts", name="ortholog_count")
    @JoinColumn(name="marker_key")
    public Integer getCountOfOrthologs() {
        return countOfOrthologs;
    }

	@Column(table="marker_counts", name="phenotype_image_count")
    @JoinColumn(name="marker_key")
	public Integer getCountOfPhenotypeImages() {
		return countOfPhenotypeImages;
	}

	@Column(table="marker_counts", name="reference_count")
	@JoinColumn(name="marker_key")
	public Integer getCountOfReferences() {
		return countOfReferences;
	}

	@Column(table="marker_counts", name="sequence_refseq_count")
	@JoinColumn(name="marker_key")
	public Integer getCountOfRefSeqSequences() {
		return countOfRefSeqSequences;
	}

	@Column(table="marker_counts", name="sequence_count")
	@JoinColumn(name="marker_key")
	public Integer getCountOfSequences() {
		return countOfSequences;
	}

	@Column(table="marker_counts", name="sequence_uniprot_count")
	@JoinColumn(name="marker_key")
	public Integer getCountOfUniProtSequences() {
		return countOfUniProtSequences;
	}

	/** returns a collection of marker count set items, which extend the
	 * base count set item class.
	 */
	@OneToMany (targetEntity=MarkerCountSetItem.class)
	@JoinColumn(name="marker_key")
	@OrderBy("sequenceNum")
	public List<MarkerCountSetItem> getCountSetItems() {
		return countSetItems;
	}

	/** get the earliest reference for this marker
	*/
	@Transient
	public Reference getEarliestReference () {
		return this.filterReferences("earliest");
	}

	/** get the Ensembl Gene Model ID for this marker, or null if none
	 */
	@Transient
	public MarkerID getEnsemblGeneModelID() {
		return this.getSingleID("Ensembl Gene Model");
	}

	/** get the Entrez Gene ID for this marker, or null if none
	 */
	@Transient
	public MarkerID getEntrezGeneID() {
		return this.getSingleID("Entrez Gene");
	}

	@OneToMany (fetch=FetchType.LAZY)
	@JoinColumn(name="marker_key")
	public List<ExpressionAssay> getExpressionAssays() {
		return expressionAssays;
	}

	/** get the FuncBase ID for this marker, or null if none
	 */
	@Transient
	public MarkerID getFuncBaseID() {
		return this.getSingleID("FuncBase");
	}

	/** get the GENSAT ID for this marker, or null if none
	 */
	@Transient
	public MarkerID getGensatID() {
		return this.getSingleID("GENSAT");
	}

	/** get the GEO ID for this marker, or null if none
	 */
	@Transient
	public MarkerID getGeoID() {
		return this.getSingleID("GEO");
	}

	/** returns an ordered list of GO annotations for the marker
	 */
	@Transient
	public List<Annotation> getGoAnnotations () {
		return this.filterAnnotations("GO/Marker");
	}

    /** returns an ordered list of GO annotations from the component ontology
	 */
	@Transient
	public List<Annotation> getGoComponentAnnotations() {
		return this.filterAnnotationsByDAG("Cellular Component");
	}

    /** returns an ordered list of GO annotations from the function ontology
	 */
	@Transient
	public List<Annotation> getGoFunctionAnnotations() {
		return this.filterAnnotationsByDAG("Molecular Function");
	}

	/** returns an ordered list of GO annotations from the process ontology
	 */
	@Transient
	public List<Annotation> getGoProcessAnnotations() {
		return this.filterAnnotationsByDAG("Biological Process");
	}

	@Transient
	public String getGOText() {
	    for (MarkerNote mn: this.getNotes()) {
	        if (mn.getNoteType().equals("GO Text")) {
	            return mn.getNote();
	        }
	    }
	    return "";
	}

	/** return the list of counts of Gxd assays by assay type
	 */
	@Transient
	public List<MarkerCountSetItem> getGxdAssayCountsByType() {
		return this.filterCountSetItems("Expression Assays by Assay Type");
	}

    /** return the list of counts of Gxd results by Theiler Stage
	 */
	@Transient
	public List<MarkerCountSetItem> getGxdResultCountsByStage() {
		return this.filterCountSetItems("Expression Results by Theiler Stage");
	}

    /** return the list of counts of Gxd results by assay type
	 */
	@Transient
	public List<MarkerCountSetItem> getGxdResultCountsByType() {
		return this.filterCountSetItems("Expression Results by Assay Type");
	}

	/** returns 1 if this marker has a GO graph, or 0 if not
	 */
	@Column(name="has_go_graph")
	public int getHasGOGraph() {
		return hasGOGraph;
	}

	/** returns 1 if this marker has a GO orthology graph, or 0 if not
	 */
	@Column(name="has_go_orthology_graph")
	public int getHasGOOrthologyGraph() {
		return hasGOOrthologyGraph;
	}

    /**
	 * Returns a collection representing all possible ID's for
	 * this marker.
	 * @return
	 */
	@OneToMany (targetEntity=MarkerID.class)
	@JoinColumn(name="marker_key")
	@OrderBy("sequenceNum")
	@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
	public List<MarkerID> getIds() {
		return ids;
	}

    /** get the latest reference for this marker
	*/
	@Transient
	public Reference getLatestReference () {
		return this.filterReferences("latest");
	}

    /**
	 * Returns a collection representing all possible locations for
	 * a given marker.
	 * @return
	 *
	 * This collection is ordered by sequence number, with the location
	 * that is most likely needed for a display page first.
	 *
	 */

	@OneToMany (targetEntity=MarkerLocation.class)
	@JoinColumn(name="marker_key")
	@OrderBy("sequenceNum")
	@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
	public List<MarkerLocation> getLocations() {
		return locations;
	}

    /** convenience method to pull the marker clip out of the list of notes
	 */
	@Transient
	public String getMarkerClip() {
		Iterator<MarkerNote> it = this.getNotes().iterator();
		MarkerNote note;

		while (it.hasNext()) {
			note = it.next();
			if (note.getNoteType().equals("marker clip")) {
				return note.getNote();
			}
		}
		return null;
	}

    @Id
	@Column(name="marker_key")
	public int getMarkerKey() {
		return markerKey;
	}

    @Column(name="marker_subtype")
	public String getMarkerSubtype() {
		return markerSubtype;
	}

    /** returns a list of MarkerTissueCount objects, which give the counts
	 * of expression results by tissue for the marker
	 */
	@OneToMany (targetEntity=MarkerTissueCount.class)
	@JoinColumn(name="marker_key")
	@OrderBy("sequenceNum")
	public List<MarkerTissueCount> getMarkerTissueCounts() {
		return markerTissueCounts;
	}

    @Column(name="marker_type")
	public String getMarkerType() {
		return markerType;
	}

    /** get the MCV annotations for this marker
     */
    @Transient
    public List<Annotation> getMCVAnnotations () {
        return this.filterAnnotations("MCV/Marker");
    }

	/** return the list of counts of molecular reagents by type
	 */
	@Transient
	public List<MarkerCountSetItem> getMolecularReagentCountsByType() {
		return this.filterCountSetItems("Molecular reagents");
	}

	/** get the Mouse Cyc annotations for this marker
     */
    @Transient
    public List<Annotation> getMouseCycAnnotations () {
        return this.filterAnnotations("MouseCyc/Marker");
    }

	/** get the Mammalian Phenotype (MP) annotations for this marker's
	 *  alleles
	 */
	@Transient
	public List<Annotation> getMPAnnotations () {
		return this.filterAnnotations("Mammalian Phenotype/Marker");
	}

	public String getName() {
		return name;
	}

	/** get the NCBI Gene Model ID for this marker, or null if none
	 */
	@Transient
	public MarkerID getNcbiGeneModelID() {
		return this.getSingleID("NCBI Gene Model");
	}

	/**
	 * A collection of possible marker notes for a marker.
	 * @return
	 * This object extends the note class.
	 */
	@OneToMany (targetEntity=MarkerNote.class)
	@JoinColumn(name="marker_key")
	@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
	public List<MarkerNote> getNotes() {
		return notes;
	}

	/** get the OMIM annotations for this marker's alleles
	 */
	@Transient
	public List<Annotation> getOMIMAnnotations () {
		return this.filterAnnotations("OMIM/Marker");
	}

	/** get the OMIM annotations for this marker's alleles
	 */
	@Transient
	public List<Annotation> getOMIMHumanAnnotations () {
		return this.filterAnnotations("OMIM/Human Marker");
	}

	public String getOrganism() {
		return organism;
	}

	/**
	 * Return a collection of marker orthologs.
	 * @return
	 */
	@OneToMany
	@JoinColumn(name="mouse_marker_key")
	@OrderBy("otherOrganism")
	public List<MarkerOrthology> getOrthologousMarkers() {
		return orthologousMarkers;
	}

	/** retrieve the IDs from which are flagged for being part of
	 * the "other database links" section
	 */
	@Transient
	public List<MarkerID> getOtherIDs () {
		ArrayList<MarkerID> sublist = new ArrayList<MarkerID>();
		Iterator<MarkerID> it = this.getIds().iterator();
		MarkerID item;

		while (it.hasNext()) {
			item = it.next();
			if (item.getIsForOtherDbSection() == 1) {
				sublist.add(item);
			}
		}
		return sublist;
	}

	/** get a list of non-preferred (old) MGI IDs for the marker
	 */
	@Transient
	public List<MarkerID> getOtherMgiIDs() {
		ArrayList<MarkerID> ids = new ArrayList<MarkerID>();
		Iterator<MarkerID> it = this.filterMarkerIDs("MGI").iterator();
		MarkerID item;

		while (it.hasNext()) {
			item = it.next();
			if (!item.isPreferred()) {
				ids.add(item);
			}
		}
		return ids;
	}

	/** get the PIRSF annotations for this marker
     */
    @Transient
    public Annotation getPirsfAnnotation () {
        List<Annotation> pirsf = this.filterAnnotations("PIRSF/Marker");
        if ((pirsf == null) || (pirsf.size() ==0)) {
        	return null;
        }
        return pirsf.get(0);
    }

	/** return the list of counts of polymorphisms by type
	 */
	@Transient
	public List<MarkerCountSetItem> getPolymorphismCountsByType() {
		return this.filterCountSetItems("Polymorphisms");
	}

	/** get the preferred centimorgan location for the marker, or null if none
	 */
	@Transient
	public MarkerLocation getPreferredCentimorgans() {
		return this.filterLocations("centimorgans");
	}

	/** get the preferred genome coordinates for the marker, or null if none
	 */
	@Transient
	public MarkerLocation getPreferredCoordinates() {
		return this.filterLocations("coordinates");
	}

    /** get the preferred cytogenetic band for the marker, or null if none
	 */
	@Transient
	public MarkerLocation getPreferredCytoband() {
		return this.filterLocations("cytogenetic");
	}

	@Column(name="primary_id")
	public String getPrimaryID() {
		return primaryID;
	}

	/** get the Interpro annotations for this marker
     */
    @Transient
    public List<Annotation> getProteinAnnotations () {
        List<Annotation> interpro = this.filterAnnotations("InterPro/Marker");
        List<Annotation> proteinOntology = this.filterAnnotations("Protein Ontology/Marker");
        interpro.addAll(proteinOntology);
        return interpro;
    }

	/** convenience method to pull the QTL coordinate note out of the list
     *  of notes
	 */
	@Transient
	public String getQtlNote() {
		Iterator<MarkerNote> it = this.getNotes().iterator();
		MarkerNote note;

		while (it.hasNext()) {
			note = it.next();
			if (note.getNoteType().equals("QTL Coordinate")) {
				return note.getNote();
			}
		}
		return null;
	}

	/**
	 * Returns reference associations.
	 * @return
	 * This might be no longer needed.
	 */
	@OneToMany (fetch=FetchType.LAZY)
	@JoinColumn(name="marker_key")
	public List<MarkerReferenceAssociation> getReferenceAssociations() {
		return referenceAssociations;
	}

	/**
	 * Return a collection of references
	 * @return
	 * This is order by year and then jnum
	 */
	@OneToMany
	@JoinTable (name="marker_to_reference",
			joinColumns=@JoinColumn(name="marker_key"),
			inverseJoinColumns=@JoinColumn(name="reference_key")
			)
	@OrderBy("year, jnumNumeric")
	@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
	public List<Reference> getReferences() {
		return references;
	}

    /** get the RefSeq ID for this marker, or null if none
	 */
	@Transient
	public MarkerID getRefSeqID() {
		MarkerID id = null;
		List<MarkerID> allIDs = this.getIds();
		Iterator<MarkerID> it = allIDs.iterator();
		while (it.hasNext()) {
			id = it.next();
			if (id.getLogicalDB().equals("RefSeq")) {
				return id;
			}
		}
		return null;
	}

	/** get the representative genomic sequence for this marker
	 */
	@Transient
	public Sequence getRepresentativeGenomicSequence () {
		return this.filterSequences("genomic");
	}

    /** get the representative polypeptide sequence for this marker
	 */
	@Transient
	public Sequence getRepresentativePolypeptideSequence () {
		return this.filterSequences("polypeptide");
	}

	/** get the representative transcript sequence for this marker
	 */
	@Transient
	public Sequence getRepresentativeTranscriptSequence () {
		return this.filterSequences("transcript");
	}

    /** returns a List of Marker/Sequence association objects
	 */
	@OneToMany (targetEntity=MarkerSequenceAssociation.class)
	@JoinColumn(name="marker_key")
	public List<MarkerSequenceAssociation> getSequenceAssociations() {
		return sequenceAssociations;
	}

    /** get the Genbank/RefSeq IDs for this marker
	 */
	@Transient
	public List<MarkerID> getSequenceIDs() {
		List<MarkerID> sublist = this.filterMarkerIDs("Sequence DB");
		sublist.addAll(this.filterMarkerIDs("RefSeq"));
		return sublist;
	}

    /** get the a single ID for the given logical database, or null if none
	 */
	@Transient
	public MarkerID getSingleID (String logicalDatabase) {
		List<MarkerID> ids = this.filterMarkerIDs(logicalDatabase);
		if (ids.size() > 0) {
			return ids.get(0);
		}
		return null;
	}

	public String getStatus() {
		return status;
	}

	/** convenience method to pull the strain-specific note out of the list
     * of notes
	 */
	@Transient
	public String getStrainSpecificNote() {
		Iterator<MarkerNote> it = this.getNotes().iterator();
		MarkerNote note;

		while (it.hasNext()) {
			note = it.next();
			if (note.getNoteType().equals("Strain-Specific Marker")) {
				return note.getNote();
			}
		}
		return null;
	}

	/** retrieve the references with strain-specific marker data
	 */
	@Transient
	public List<Reference> getStrainSpecificReferences () {
		MarkerReferenceAssociation association;
		Iterator<MarkerReferenceAssociation> it = this.getReferenceAssociations().iterator();
		List<Reference> ssRefs = new ArrayList<Reference>();

		while (it.hasNext()) {
			association = it.next();
			if (association.isStrainSpecific()) {
				ssRefs.add(association.getReference());
			}
		}
		return ssRefs;
	}

	public String getSymbol() {
		return symbol;
	}

	@OneToMany (targetEntity=MarkerSynonym.class)
	@JoinColumn(name="marker_key")
	@OrderBy("synonym")
	@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
	public List<MarkerSynonym> getSynonyms() {
		return synonyms;
	}

	/** retrieve the UniProt IDs for the marker
	 */
	@Transient
	public List<MarkerID> getUniProtIDs () {
		List<MarkerID> ids = this.filterMarkerIDs ("SWISS-PROT");
		ids.addAll(this.filterMarkerIDs("TrEMBL"));
		return ids;
	}

    /** get the VEGA Gene Model ID for this marker, or null if none
	 */
	@Transient
	public MarkerID getVegaGeneModelID() {
		return this.getSingleID("VEGA Gene Model");
	}

	public void setAliases(List<MarkerAlias> aliases) {
		this.aliases = aliases;
	}

    public void setAlleleAssociations(
			List<MarkerAlleleAssociation> alleleAssociations) {
		this.alleleAssociations = alleleAssociations;
	}

    public void setAnnotations(List<Annotation> annotations) {
		this.annotations = annotations;
	}

	public void setBatchMarkerAlleles(List<BatchMarkerAllele> batchMarkerAlleles) {
		this.batchMarkerAlleles = batchMarkerAlleles;
	}

	public void setBatchMarkerGoAnnotations(
	                List<BatchMarkerGoAnnotation> batchMarkerGoAnnotations) {
	        this.batchMarkerGoAnnotations = batchMarkerGoAnnotations;
	}

	public void setBatchMarkerMpAnnotations(
                List<BatchMarkerMpAnnotation> batchMarkerMpAnnotations) {
        this.batchMarkerMpAnnotations = batchMarkerMpAnnotations;
	}

	public void setBatchMarkerSnps(List<BatchMarkerSnp> batchMarkerSnps) {
		this.batchMarkerSnps = batchMarkerSnps;
	}

	public void setBiotypeConflicts(List<MarkerBiotypeConflict> biotypeConflicts) {
		this.biotypeConflicts = biotypeConflicts;
	}

	public void setCountOfAlleles(Integer countOfAlleles) {
		this.countOfAlleles = countOfAlleles;
	}

	public void setCountOfAllelesWithHumanDiseases(
			Integer countOfAllelesWithHumanDiseases) {
		this.countOfAllelesWithHumanDiseases = countOfAllelesWithHumanDiseases;
	}

	public void setCountOfAntibodies(Integer countOfAntibodies) {
		this.countOfAntibodies = countOfAntibodies;
	}

	public void setCountOfCdnaSources(Integer countOfCdnaSources) {
		this.countOfCdnaSources = countOfCdnaSources;
	}

	public void setCountOfGOTerms(Integer countOfGOTerms) {
        this.countOfGOTerms = countOfGOTerms;
    }

	public void setCountOfGxdAssays(Integer countOfGxdAssays) {
        this.countOfGxdAssays = countOfGxdAssays;
    }

	public void setCountOfGxdImages(Integer countOfGxdImages) {
		this.countOfGxdImages = countOfGxdImages;
	}

	public void setCountOfGxdLiterature(Integer countOfGxdLiterature) {
		this.countOfGxdLiterature = countOfGxdLiterature;
	}

	public void setCountOfGxdResults(Integer countOfGxdResults) {
		this.countOfGxdResults = countOfGxdResults;
	}

	public void setCountOfGxdTissues(Integer countOfGxdTissues) {
		this.countOfGxdTissues = countOfGxdTissues;
	}

	public void setCountOfHumanDiseases(Integer countOfHumanDiseases) {
		this.countOfHumanDiseases = countOfHumanDiseases;
	}

	public void setCountOfMappingExperiments(Integer countOfMappingExperiments) {
		this.countOfMappingExperiments = countOfMappingExperiments;
	}

	public void setCountOfMicroarrayProbesets(Integer countOfMicroarrayProbesets) {
		this.countOfMicroarrayProbesets = countOfMicroarrayProbesets;
	}

	public void setCountOfOrthologs(Integer countOfOrthologs) {
        this.countOfOrthologs = countOfOrthologs;
    }

	public void setCountOfPhenotypeImages(Integer countOfPhenotypeImages) {
		this.countOfPhenotypeImages = countOfPhenotypeImages;
	}

	public void setCountOfReferences(Integer countOfReferences) {
		this.countOfReferences = countOfReferences;
	}

	public void setCountOfRefSeqSequences(Integer countOfRefSeqSequences) {
		this.countOfRefSeqSequences = countOfRefSeqSequences;
	}

	public void setCountOfSequences(Integer countOfSequences) {
        this.countOfSequences = countOfSequences;
    }

	public void setCountOfUniProtSequences(Integer countOfUniProtSequences) {
		this.countOfUniProtSequences = countOfUniProtSequences;
	}

	public void setCountSetItems(List<MarkerCountSetItem> countSetItems) {
		this.countSetItems = countSetItems;
	}

	public void setExpressionAssays(List<ExpressionAssay> expressionAssays) {
		this.expressionAssays = expressionAssays;
	}

	public void setHasGOGraph(int hasGOGraph) {
		this.hasGOGraph = hasGOGraph;
	}

	public void setHasGOOrthologyGraph(int hasGOOrthologyGraph) {
		this.hasGOOrthologyGraph = hasGOOrthologyGraph;
	}

	public void setIds(List<MarkerID> ids) {
		this.ids = ids;
	}

	public void setLocations(List<MarkerLocation> locations) {
		this.locations = locations;
	}

	public void setMarkerKey(int markerKey) {
		this.markerKey = markerKey;
	}

	public void setMarkerSubtype(String markerSubtype) {
		this.markerSubtype = markerSubtype;
	}

	public void setMarkerTissueCounts(List<MarkerTissueCount> markerTissueCounts) {
		this.markerTissueCounts = markerTissueCounts;
	}

	public void setMarkerType(String markerType) {
		this.markerType = markerType;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNotes(List<MarkerNote> notes) {
		this.notes = notes;
	}

	public void setOrganism(String organism) {
		this.organism = organism;
	}

	public void setOrthologousMarkers(List<MarkerOrthology> orthologousMarkers) {
		this.orthologousMarkers = orthologousMarkers;
	}

	public void setPrimaryID(String primaryID) {
		this.primaryID = primaryID;
	}

	public void setReferenceAssociations(
			List<MarkerReferenceAssociation> referenceAssociations) {
		this.referenceAssociations = referenceAssociations;
	}

	public void setReferences(List<Reference> references) {
		this.references = references;
	}

	public void setSequenceAssociations(
			List<MarkerSequenceAssociation> sequenceAssociations) {
		this.sequenceAssociations = sequenceAssociations;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public void setSynonyms(List<MarkerSynonym> synonyms) {
		this.synonyms = synonyms;
	}

	@Override
	public String toString() {
		return "Marker ["
				+ (countOfReferences != null ? "countOfReferences="
						+ countOfReferences + ", " : "")
				+ (ids != null ? "ids=" + ids + ", " : "")
				+ "markerKey="
				+ markerKey
				+ ", "
				+ (markerSubtype != null ? "markerSubtype=" + markerSubtype
						+ ", " : "")
				+ (markerType != null ? "markerType=" + markerType + ", " : "")
				+ (name != null ? "name=" + name + ", " : "")
				+ (organism != null ? "organism=" + organism + ", " : "")
				+ (orthologousMarkers != null ? "orthologousMarkers="
						+ orthologousMarkers + ", " : "")
				+ (primaryID != null ? "primaryID=" + primaryID + ", " : "")
				+ (status != null ? "status=" + status + ", " : "")
				+ (symbol != null ? "symbol=" + symbol + ", " : "")
				+ (synonyms != null ? "synonyms=" + synonyms : "") + "]";
	}
}
