package mgi.frontend.datamodel;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;
import javax.persistence.Transient;

import mgi.frontend.datamodel.sort.SmartAlphaComparator;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.FilterJoinTable;

/**
 * Marker
 * @author mhall, jsb
 * This is the core Marker object.
 */

@Entity
@FilterDefs({
  @FilterDef(name="markerDetailRefs"),
  @FilterDef(name="markerDetailMarkerInteractions"),
  @FilterDef(name="onlyProteinSequences")
})
@Table(name="marker")
@SecondaryTables ( {
  @SecondaryTable (name="marker_counts", pkJoinColumns= {
    @PrimaryKeyJoinColumn(name="marker_key", referencedColumnName="marker_key") } )
} )
//IMPORTANT this annotation forces inner join on the secondary table and ASSUMES there is an entry for every marker
@org.hibernate.annotations.Tables({
	@org.hibernate.annotations.Table(appliesTo="marker_counts",optional=false)
})
//@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Marker {

	private List<MarkerAlias> aliases;
	private List<MarkerQtlExperiment> qtlExperiments;
	private List<MarkerProbeset> probesets;
	private List<MarkerAlleleAssociation> alleleAssociations;
	List<MarkerIncidentalMutation> incidentalMutations;
	private List<BatchMarkerAllele> batchMarkerAlleles;
	private List<Annotation> annotations;
	private List<BatchMarkerGoAnnotation> batchMarkerGoAnnotations;
	private List<BatchMarkerMpAnnotation> batchMarkerMpAnnotations;
	private List<MarkerBiotypeConflict> biotypeConflicts;
	private List<String> batchMarkerSnps;
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
	private Integer countForImsr;
	private Integer countOfMutationInvolves;
	private int isInReferenceGenome;
	private int hasGOGraph;
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
	private List<MarkerLink> links;
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
	private Integer countOfGeneTraps;
	private List<ExpressionAssay> expressionAssays;
	private OrganismOrtholog organismOrtholog;
       private List<MarkerDisease> markerDiseases;
       private List<RelatedMarker> relatedMarkers;
       private List<MarkerInteraction> markerInteractions;

    //=== methods for related markers ===//

       /* retrieve related markers for the specified category and relationship
	* term
	*/
       @Transient
       private List<RelatedMarker> filterRelatedMarkers (String category,
	       String relationshipTerm) {

	   ArrayList<RelatedMarker> sublist = new ArrayList<RelatedMarker>();
	   Iterator<RelatedMarker> it = this.getRelatedMarkers().iterator();
	   RelatedMarker m;

	   while (it.hasNext()) {
	       m = it.next();
	       if (m.getRelationshipCategory().equals(category)) {
		   if (m.getRelationshipTerm().equals(relationshipTerm)) {
		       sublist.add(m);
		   }
	       }
	   }
	   return sublist;
       }

       /* retrieve related markers, where this marker is a cluster and the
	* related markers are members of this cluster
	*/
       @Transient
       public List<RelatedMarker> getClusterMembers() {
	   return this.filterRelatedMarkers("cluster_has_member", "contains");
       }

       /* retrieve related markers, where this marker is a member of one or
	* more clusters and the related markers are those cluster markers
	*/
       @Transient
       public List<RelatedMarker> getClusters() {
	   return this.filterRelatedMarkers("cluster_has_member", "is member of");
       }

       @OneToMany (targetEntity=RelatedMarker.class, fetch=FetchType.LAZY)
       @JoinColumn(name="marker_key")
       @OrderBy("sequenceNum")
       public List<RelatedMarker> getRelatedMarkers() {
	       return relatedMarkers;
       }

       public void setRelatedMarkers(List<RelatedMarker> relatedMarkers) {
               this.relatedMarkers = relatedMarkers;
       }

    //=== methods for marker interactions ===//

       /** getter
	*/
       @OneToMany (targetEntity=MarkerInteraction.class, fetch=FetchType.LAZY)
       @JoinColumn(name="marker_key")
       @OrderBy("sequenceNum")
       @Filter(
		// enable this filter to only retrieve markers for use as
		// teasers on the marker detail page (big performance gain)
		name = "markerDetailMarkerInteractions",
		condition = "in_teaser = 1"
       )
       public List<MarkerInteraction> getMarkerInteractions() {
	       return markerInteractions;
       }

       /** setter
	*/
       public void setMarkerInteractions(List<MarkerInteraction> markerInteractions) {
               this.markerInteractions = markerInteractions;
       }

       /** sort the given list of MarkerInteraction objects by symbol, using a
	* smart-alpha sort mechanism
	*/
       @Transient
       private void sortMarkerInteractionsBySymbol (List<MarkerInteraction> rmList) {
	   if (rmList.size() > 1) {
	       Collections.sort(rmList,
		   rmList.get(0).getComparator());
	   }
       }

       /** get the up to three unique markers which interact with this one,
	* not including this one (if it interacts with itself).
	*/
       @Transient
       public List<Marker> getInteractionTeaserMarkers() {
	   List<MarkerInteraction> mi = this.getMarkerInteractions();
	   this.sortMarkerInteractionsBySymbol(mi);

	   ArrayList<Marker> markers = new ArrayList<Marker>();

	   for (MarkerInteraction i : mi) {
	       if (markers.size() < 3) {
		   Marker m = i.getInteractingMarker();
		   int markerKey = m.getMarkerKey();

		   boolean foundIt = false;

		   if (markerKey == this.getMarkerKey()) {
			foundIt = true;
		   } else {
			for (Marker n : markers) {
			    if (markerKey == n.getMarkerKey()) {
				foundIt = true;
			    }
			}
		   }

		   if (!foundIt) {
		       markers.add(m);
		   }
	       }
	   }
	   return markers;
       }

       /** return the list of counts of marker interactions by type of
	* interaction
	*/
       @Transient
       public List<MarkerCountSetItem> getInteractionCountsByType() {
	   return this.filterCountSetItems("Interaction");
       }

    // ================= Instance Methods ===================== //

       @OneToMany (targetEntity=MarkerDisease.class)
       @JoinColumn(name="marker_key")
       @OrderBy("sequenceNum")
       public List<MarkerDisease> getMarkerDiseases() {
               return markerDiseases;
       }

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

	/** retrieve the marker links for the given link group
	 */
	@Transient
	private List<MarkerLink> filterLinks (String linkGroup) {
		MarkerLink link;
		ArrayList<MarkerLink> sublist = new ArrayList<MarkerLink>();
		Iterator<MarkerLink> it = this.getLinks().iterator();

		while (it.hasNext()) {
			link = it.next();
			if (linkGroup.equals(link.getLinkGroup())) {
				sublist.add(link);
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
	@BatchSize(size=50)
	@OrderBy("aliasSymbol")
	public List<MarkerAlias> getAliases() {
		return aliases;
	}

	/** need to be able to filter the list of QTL experiments to be only
	 * those of a particular type
	 */
	@Transient
	private List<MarkerQtlExperiment> filterQtlExperiments (
		String noteType) {

	    List<MarkerQtlExperiment> sublist =
		new ArrayList<MarkerQtlExperiment>();

	    MarkerQtlExperiment qtlExp;
	    Iterator<MarkerQtlExperiment> it =
		this.getQtlExperiments().iterator();

	    while (it.hasNext()) {
		qtlExp = it.next();

		if (noteType.equals(qtlExp.getNoteType())) {
		    sublist.add(qtlExp);
		}
	    }
	    return sublist;
	}

	/** get the list of QTL mapping notes
	 */
	@Transient
	public List<MarkerQtlExperiment> getQtlMappingNotes() {
	    return this.filterQtlExperiments("TEXT-QTL");
	}

	/** get the list of QTL candidate gene notes
	 */
	@Transient
	public List<MarkerQtlExperiment> getQtlCandidateGeneNotes() {
	    return this.filterQtlExperiments("TEXT-QTL-Candidate Genes");
	}

	/** returns a collection of QTL mapping experiment notes for the mkr
	 */
	@OneToMany (targetEntity=MarkerQtlExperiment.class)
	@JoinColumn(name="marker_key")
	@BatchSize(size=70)
	@OrderBy("sequenceNum")
	public List<MarkerQtlExperiment> getQtlExperiments() {
		return qtlExperiments;
	}

	/** returns a collection of microarray probesets for the marker
	 */
	@OneToMany (targetEntity=MarkerProbeset.class)
	@JoinColumn(name="marker_key")
	@BatchSize(size=50)
	@OrderBy("probesetID")
	public List<MarkerProbeset> getProbesets() {
		return probesets;
	}

	/**
	 * Returns a collection of marker/allele association objects, which
	 * extend the base association class.
	 */
	@OneToMany (targetEntity=MarkerAlleleAssociation.class)
	@BatchSize(size=200)
	@JoinColumn(name="marker_key")
	public List<MarkerAlleleAssociation> getAlleleAssociations() {
		return alleleAssociations;
	}

	@OneToMany (targetEntity=MarkerIncidentalMutation.class)
	@BatchSize(size=250)
	@JoinColumn(name="marker_key")
	public List<MarkerIncidentalMutation> getIncidentalMutations() {
		return incidentalMutations;
	}
	public void setIncidentalMutations(List<MarkerIncidentalMutation> incidentalMutations) {
		this.incidentalMutations = incidentalMutations;
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
    @OneToMany(fetch=FetchType.LAZY)
    @JoinTable (name="marker_to_annotation",
            joinColumns=@JoinColumn(name="marker_key"),
            inverseJoinColumns=@JoinColumn(name="annotation_key")
            )
    @BatchSize(size=200)
    @OrderBy("dagName, term")
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
	@ElementCollection
	@CollectionTable(name="batch_marker_snps", joinColumns=@JoinColumn(name="marker_key"))
	@Column(name="snp_id")
	@OrderBy("snp_id")
	public List<String> getBatchMarkerSnps() {
		return batchMarkerSnps;
	}

	/** Returns a list of the biotype conflicts for this marker
	 * (for cases where different data sources disagree about what type of
	 * marker this is)
	 */
	@OneToMany (targetEntity=MarkerBiotypeConflict.class)
	@JoinColumn(name="marker_key")
	@BatchSize(size=20)
	@OrderBy("sequenceNum")
	public List<MarkerBiotypeConflict> getBiotypeConflicts() {
		return biotypeConflicts;
	}

	@Column(table="marker_counts", name="mutation_involves_count")
	@JoinColumn(name="marker_key")
	public Integer getCountOfMutationInvolves() {
		return countOfMutationInvolves;
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

    @Column(table="marker_counts", name="gene_trap_count")
    @JoinColumn(name="marker_key")
    public Integer getCountOfGeneTraps() {
	return countOfGeneTraps;
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

	@Column(table="marker_counts", name="imsr_count")
	@JoinColumn(name="marker_key")
	public Integer getCountForImsr() {
		return countForImsr;
	}

	/** returns a collection of marker count set items, which extend the
	 * base count set item class.
	 */
	@OneToMany (targetEntity=MarkerCountSetItem.class)
	@BatchSize(size=100)
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

	/** determine if the marker has exactly one Ensembl Gene Model ID
	 */
	@Transient
	public boolean getHasOneEnsemblGeneModelID() {
		List<MarkerID> ids = this.filterMarkerIDs("Ensembl Gene Model");
		if (ids.size() != 1) {
			return false;
		}
		return true;
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
	
	@Transient
	public MarkerID getHgncID() {
		return this.getSingleID("HGNC");
	}

	@OneToMany (fetch=FetchType.LAZY)
	@JoinColumn(name="marker_key")
	@BatchSize(size=300)
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

	/** returns 1 if this marker is in the GO Consortium reference genome
	 * project, 0 otherwise
	 */
	@Column(name="is_in_reference_genome")
	public int getIsInReferenceGenome() {
		return isInReferenceGenome;
	}

	/** returns 1 if this marker has a GO graph, or 0 if not
	 */
	@Column(name="has_go_graph")
	public int getHasGOGraph() {
		return hasGOGraph;
	}

    /**
	 * Returns a collection representing all possible ID's for
	 * this marker.
	 * @return
	 */
	@OneToMany (targetEntity=MarkerID.class)
	@JoinColumn(name="marker_key")
	@BatchSize(size=100)
	@OrderBy("sequenceNum")
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

	@OneToMany (targetEntity=MarkerLocation.class,fetch=FetchType.LAZY)
	@JoinColumn(name="marker_key")
	@OrderBy("sequenceNum")
	@BatchSize(size=100)
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
	@BatchSize(size=200)
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
	@BatchSize(size=50)
	@JoinColumn(name="marker_key")
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

	/** returns the organism/ortholog object for this marker.  From there,
	 * you can trace back to the HomologyClass object and get all of the
	 * organisms (and their markers) which are part of the homology class.
	 */
	@ManyToOne (targetEntity=OrganismOrtholog.class,fetch=FetchType.LAZY)
	@JoinTable (name="homology_cluster_organism_to_marker",
			joinColumns=@JoinColumn(name="marker_key"),
			inverseJoinColumns=@JoinColumn(name="cluster_organism_key")
			)
	public OrganismOrtholog getOrganismOrtholog() {
		return organismOrtholog;
	}

	/**
	 * Return a collection of marker orthologs.
	 * @return
	 */
	@OneToMany
	@JoinColumn(name="mouse_marker_key")
	@BatchSize(size=50)
	@OrderBy("sequenceNum")
	public List<MarkerOrthology> getOrthologousMarkers() {
		return orthologousMarkers;
	}

	/**
	 * Return a collection of marker links.
	 * @return
	 */
	@OneToMany
	@JoinColumn(name="marker_key")
	@OrderBy("sequenceNum")
	public List<MarkerLink> getLinks() {
		return links;
	}

	/**
	 * Return a collection of chicken expression links
	 */
	@Transient
	public List<MarkerLink> getExpressedInChickenLinks() {
		return this.filterLinks("gxd chicken expression links");
	}

	/**
	 * Return a collection of zfin expression links
	 */
	@Transient
	public List<MarkerLink> getExpressedInZfinLinks() {
		return this.filterLinks("gxd zfin expression links");
	}

	/**
	 * Return a collection of marker links for the homologene class page.
	 */
	@Transient
	public List<MarkerLink> getHomologyLinks() {
		return this.filterLinks("homology gene links");
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

	@Transient
	public MarkerLocation getPreferredLocation()
    {
        MarkerLocation bestLoc = this.getPreferredCoordinates();
        if(bestLoc == null)
        {
        	bestLoc = this.getPreferredCytoband();
        	if(bestLoc == null)
        	{
        		bestLoc = this.getPreferredCentimorgans();
        	}
        }
        return bestLoc;
    }

    /* get the chromosome for this marker, preferring to take it from the
     * coordinates first, cM second, and cytoband third.
     */
    @Transient
    public String getChromosome() {
	MarkerLocation loc = this.getPreferredCoordinates();

	if (loc != null) {
	    return loc.getChromosome();
	}

	loc = this.getPreferredCentimorgans();
	if (loc != null) {
	    return loc.getChromosome();
	}

	loc = this.getPreferredCytoband();
	if (loc != null) {
	    return loc.getChromosome();
	}
	return "UN";		// default to Unknown chromosome
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

    /** get a nicely formatted string with the marker's location, preferring:
     * coordinates over centimorgans over cytogenetic band
     */
    @Transient
    public String getLocation() {
	StringBuffer sb = new StringBuffer();
	sb.append("Chr");
	sb.append(this.getChromosome());

	MarkerLocation loc = this.getPreferredCoordinates();
	if (loc == null) {
	    loc = this.getPreferredCentimorgans();
	    if (loc == null) {
		loc = this.getPreferredCytoband();
		if (loc != null) {
		    // format using cytoband location
		    sb.append (" ");
		    sb.append (loc.getCytogeneticOffset());
		}
	    } else {
		// format using cM location
	        NumberFormat nf = new DecimalFormat("#0.00");

		sb.append (" ");
		sb.append (nf.format(loc.getCmOffset()));
		sb.append (" cM");
	    }
	} else {
	    // format using coordinates
	    NumberFormat nf = new DecimalFormat("#0");

	    sb.append (":");
	    sb.append (nf.format(loc.getStartCoordinate()));
	    sb.append ("-");
	    sb.append (nf.format(loc.getEndCoordinate()));

	    if (loc.getStrand() != null) {
		sb.append (" (");
		sb.append (loc.getStrand());
		sb.append (")");
	    }
	}
	return sb.toString();
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
	@BatchSize(size=300)
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
	@BatchSize(size=300)
	@OrderBy("year, jnumNumeric")
	@FilterJoinTable(
			name = "markerDetailRefs",
			condition = "qualifier in ('earliest','latest')"
	)
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
	@BatchSize(size=200)
	@Filter(
	  // enable this filter to only bring back protein sequences
	  name = "onlyProteinSequences",
	  condition = "qualifier='polypeptide'"
	)
	public List<MarkerSequenceAssociation> getSequenceAssociations() {
		return sequenceAssociations;
	}

    /** get the neXtProt IDs for this (human) marker; mouse markers don't have
     * these IDs
     */
	@Transient
	public List<MarkerID> getNeXtProtIDs() {
		return this.filterMarkerIDs("neXtProt");
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

    /** get the HomoloGene id for this marker
	 */
	@Transient
	public MarkerID getHomoloGeneID() {
		List<MarkerID> ids = this.filterMarkerIDs("HomoloGene");
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
	@BatchSize(size=50)
	@OrderBy("synonym")
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

	@Transient
	public boolean getIsSTS(){
		return this.getMarkerType().equals("DNA Segment");
	}

	@Transient
	public Comparator<Marker> getComparator() {
		return new MarkerComparator();
	}

	public void setAliases(List<MarkerAlias> aliases) {
		this.aliases = aliases;
	}

	public void setQtlExperiments(List<MarkerQtlExperiment> qtlExperiments) {
		this.qtlExperiments = qtlExperiments;
	}

	public void setProbesets(List<MarkerProbeset> probesets) {
		this.probesets = probesets;
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

	public void setBatchMarkerSnps(List<String> batchMarkerSnps) {
		this.batchMarkerSnps=batchMarkerSnps;
	}

	public void setBiotypeConflicts(List<MarkerBiotypeConflict> biotypeConflicts) {
		this.biotypeConflicts = biotypeConflicts;
	}

	public void setCountOfMutationInvolves (Integer countOfMutationInvolves) {
		this.countOfMutationInvolves = countOfMutationInvolves;
	}

	public void setCountOfAlleles(Integer countOfAlleles) {
		this.countOfAlleles = countOfAlleles;
	}

	public void setCountOfAllelesWithHumanDiseases(
			Integer countOfAllelesWithHumanDiseases) {
		this.countOfAllelesWithHumanDiseases = countOfAllelesWithHumanDiseases;
	}

	public void setCountOfGeneTraps(Integer countOfGeneTraps) {
		this.countOfGeneTraps = countOfGeneTraps;
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

	public void setCountForImsr(Integer countForImsr) {
		this.countForImsr = countForImsr;
	}

	public void setCountSetItems(List<MarkerCountSetItem> countSetItems) {
		this.countSetItems = countSetItems;
	}

	public void setExpressionAssays(List<ExpressionAssay> expressionAssays) {
		this.expressionAssays = expressionAssays;
	}

	public void setIsInReferenceGenome(int isInReferenceGenome) {
		this.isInReferenceGenome = isInReferenceGenome;
	}

	public void setHasGOGraph(int hasGOGraph) {
		this.hasGOGraph = hasGOGraph;
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

	public void setOrganismOrtholog(OrganismOrtholog organismOrtholog) {
		this.organismOrtholog = organismOrtholog;
	}

	public void setLinks(List<MarkerLink> links) {
		this.links = links;
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

       public void setMarkerDiseases(List<MarkerDisease> markerDiseases) {
               this.markerDiseases = markerDiseases;
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

    private class MarkerComparator extends SmartAlphaComparator<Marker>
    {
		public int compare(Marker m1, Marker m2) {
		    return super.compare(m1.getSymbol(), m2.getSymbol());
		}
    }
}
