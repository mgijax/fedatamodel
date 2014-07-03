package mgi.frontend.datamodel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;
import javax.persistence.Transient;

import mgi.frontend.datamodel.phenotype.AlleleSummaryDisease;
import mgi.frontend.datamodel.phenotype.AlleleSummarySystem;
import mgi.frontend.datamodel.phenotype.DiseaseTableDisease;
import mgi.frontend.datamodel.phenotype.DiseaseTableGenotype;
import mgi.frontend.datamodel.phenotype.PhenoTableGenotype;
import mgi.frontend.datamodel.phenotype.PhenoTableSystem;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.FilterJoinTable;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

/**
 * Base object alleles.  This is represented by a core in our flower schema.
 *
 * Base Allele object.  Tricky parts are commented inline.
 */
@Entity
@Table(name="Allele")
@SecondaryTables (
  {
    @SecondaryTable (name="allele_counts", pkJoinColumns= {
    		@PrimaryKeyJoinColumn(name="allele_key", referencedColumnName="allele_key") } ),
    @SecondaryTable (name="allele_imsr_counts", pkJoinColumns= {
    		@PrimaryKeyJoinColumn(name="allele_key", referencedColumnName="allele_key") } )
  }
)
//IMPORTANT this annotation forces inner join on the secondary table and ASSUMES there is an entry for every allele
@org.hibernate.annotations.Tables({
	@org.hibernate.annotations.Table(appliesTo="allele_counts",optional=false),
	@org.hibernate.annotations.Table(appliesTo="allele_imsr_counts",optional=false)
})

@FilterDefs({
@FilterDef(name="noDiseaseHeaders"),
@FilterDef(name="teaserMarkers")
})
@JsonIgnoreProperties({"references", "notes", "molecularDescription", "ids"})
public class Allele {
    private int alleleKey;
    private String alleleSubType;
    private List<AlleleIncidentalMutation> incidentalMutations;
    private List<AlleleSummarySystem> summarySystems;
    private List<AlleleSummaryDisease> summaryDiseases;
    private List<AlleleSystem> alleleSystems;
    private String alleleType;
    private String chromosome;
    private String collection;
    private Integer countOfExpressionAssayResults;
    private Integer countOfMarkers;
    private Integer countOfMutationInvolvesMarkers;
    private Integer countOfReferences;
    private String driver;
    private String holder;
    private String companyID;
    private String geneName;
    private List<AlleleID> ids;
    private String inducibleNote;
    private int isRecombinase;
    private int isWildType;
    private int isMixed;
    private Integer primaryImageKey;
    private String strain;
    private String strainLabel;
    private String molecularDescription;
    private String name;
    private List<AlleleNote> notes;
    private String onlyAlleleSymbol;
    private String primaryID;
    private String inheritanceMode;
    private List<AlleleReferenceAssociation> referenceAssociations;
    private String symbol;
    private String transmissionType;
    private String transmissionPhrase;
    private List<AlleleSynonym> synonyms;
    private Integer imsrCellLineCount;
    private Integer imsrStrainCount;
    private Integer imsrCountForMarker;
    private RecombinaseInfo recombinaseInfo;
    private List<AlleleGenotypeAssociation> genotypeAssociations;
    private List<Annotation> annotations;
    private List<AlleleRelatedMarker> relatedMarkers;
	private List<PhenoTableSystem> phenoTableSystems;
	private List<PhenoTableGenotype> phenotableGenotypes;
	private boolean hasDiseaseModel;
	private List<DiseaseTableDisease> diseaseTableDiseases;
	private List<DiseaseTableGenotype> diseaseTableGenotypes;
	private List<AlleleImageAssociation> imageAssociations;
	private List<Marker> markers;
	private List<AlleleMutation> mutations;
	private List<AlleleCellLine> cellLines;
	private List<AlleleSequenceAssociation> sequenceAssociations;



    // ================= Getters and Setters ===================== //

	@Id
    @Column(name="allele_key")
    public int getAlleleKey() {
        return alleleKey;
    }

	@Column(name="allele_subtype")
    public String getAlleleSubType() {
        return alleleSubType;
    }

	@OneToMany (targetEntity=PhenoTableSystem.class)
	@BatchSize(size=100)
	@JoinColumn(name="allele_key")
	@OrderBy("systemSeq")
	public List<PhenoTableSystem> getPhenoTableSystems() {
		return phenoTableSystems;
	}

	@OneToMany (targetEntity=AlleleIncidentalMutation.class)
	@BatchSize(size=250)
	@JoinColumn(name="allele_key")
	public List<AlleleIncidentalMutation> getIncidentalMutations() {
		return incidentalMutations;
	}

	@Transient
	public AlleleIncidentalMutation getIncidentalMutation() {
		return getIncidentalMutations().get(0);
	}


	@OneToMany (targetEntity=AlleleSummarySystem.class)
	@BatchSize(size=250)
	@JoinColumn(name="allele_key")
	@OrderBy("system")
	public List<AlleleSummarySystem> getSummarySystems() {
		return summarySystems;
	}

	@OneToMany (targetEntity=AlleleSummaryDisease.class)
	@BatchSize(size=200)
	@JoinColumn(name="allele_key")
	@OrderBy("disease")
	public List<AlleleSummaryDisease> getSummaryDiseases() {
		return summaryDiseases;
	}

	/**
     * Return the allesystem objects.  This is only really
     * relevant if its a cre allele.
     * @return
     */

    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="allele_key")
    @BatchSize(size=50)
    public List<AlleleSystem> getAlleleSystems() {
        return alleleSystems;
    }

    @Column(name="allele_type")
    public String getAlleleType() {
        return alleleType;
    }

    @Column(name="chromosome")
    public String getChromosome() {
        return chromosome;
    }
    @Column(name="collection")
    public String getCollection() {
        return collection;
    }

    @Column(name="holder")
    public String getHolder() {
        return holder;
    }

    @Column(name="company_id")
    public String getCompanyID() {
        return companyID;
    }

    @Column(name="transmission_type")
    public String getTransmissionType() {
        return transmissionType;
    }

    @Column(name="transmission_phrase")
    public String getTransmissionPhrase() {
        return transmissionPhrase;
    }

    @Column(name="inheritance_mode")
    public String getInheritanceMode() {
        return inheritanceMode;
    }

    @OneToMany (fetch=FetchType.LAZY)
    @JoinColumn(name="allele_key")
    public List<Annotation> getAnnotations() {
    	return annotations;
    }

    @Column(table="allele_counts", name="expression_assay_result_count")
    @JoinColumn(name="allele_key")
    public Integer getCountOfExpressionAssayResults() {
        return countOfExpressionAssayResults;
    }

    @Column(table="allele_counts", name="marker_count")
    @JoinColumn(name="allele_key")
    public Integer getCountOfMarkers() {
        return countOfMarkers;
    }

    @Column(table="allele_counts", name="mutation_involves_marker_count")
    @JoinColumn(name="allele_key")
    public Integer getCountOfMutationInvolvesMarkers() {
        return countOfMutationInvolvesMarkers;
    }

    @Column(table="allele_counts", name="reference_count")
    @JoinColumn(name="allele_key")
    public Integer getCountOfReferences() {
        return countOfReferences;
    }

    public String getDriver() {
        return driver;
    }

    /**
     * Return the gene name for the gene this allele is part of.
     * @return
     */

    @Column(name="gene_name")
    public String getGeneName() {
        return geneName;
    }

    @OneToMany (fetch=FetchType.LAZY)
    @BatchSize(size=100)
	@JoinColumn(name="allele_key")
    public List<AlleleGenotypeAssociation> getGenotypeAssociations() {
		return genotypeAssociations;
	}

    /**
     * Return all of the possible allele id's.  This class is based
     * off of the accession id class.
     * @return
     */
    @OneToMany (targetEntity=AlleleID.class)
    @JoinColumn (name="allele_key")
    @OrderBy("accID")
    public List<AlleleID> getIds() {
        return ids;
    }

	/** count of cell lines for this allele in IMSR
     */
    @Column(table="allele_imsr_counts", name="cell_line_count")
    @JoinColumn(name="allele_key")
    public Integer getImsrCellLineCount() {
		return imsrCellLineCount;
	}

    /** count of cell lines and strains for the marker of this allele in IMSR
     */
    @Column(table="allele_imsr_counts", name="count_for_marker")
    @JoinColumn(name="allele_key")
	public Integer getImsrCountForMarker() {
		return imsrCountForMarker;
	}

    /** count of strains for this allele in IMSR
     */
    @Column(table="allele_imsr_counts", name="strain_count")
    @JoinColumn(name="allele_key")
	public Integer getImsrStrainCount() {
		return imsrStrainCount;
	}

    @Column(name="inducible_note")
    public String getInducibleNote() {
        return inducibleNote;
    }

    /**
     * Is this a cre allele?
     * @return
     */
    @Column(name="is_recombinase")
    public int getIsRecombinase() {
        return isRecombinase;
    }

    /** is this a wild-type allele?  (1 if yes, 0 if no)
     */
    @Column(name="is_wild_type")
    public int getIsWildType() {
	return isWildType;
    }

    /*
     * primary image key
     */
    @Column(name="primary_image_key")
    public Integer getPrimaryImageKey(){
    	return primaryImageKey;
    }

    /** is this a mixed allele?  (1 if yes, 0 if no)
     */
    @Column(name="is_mixed")
    public int getIsMixed() {
	return isMixed;
    }

    @Column(name="strain")
    public String getStrain() {
        return strain;
    }

    @Column(name="strain_type")
    public String getStrainLabel() {
        return strainLabel;
    }

    @Column(name="molecular_description")
    public String getMolecularDescription() {
        return molecularDescription;
    }

    public String getName() {
        return name;
    }

    // pull out the ID with the given logical db from the list of all IDs
    @Transient
    private AlleleID filterIDs (String logicalDB) {
	Iterator<AlleleID> it = this.getIds().iterator();
	AlleleID id;

	while (it.hasNext()) {
	    id = it.next();
	    if (logicalDB.equals(id.getLogicalDB())) {
		return id;
	    }
	}
	return null;
    }

    // return the KOMP ID associated with the allele, if there is one
    @Transient
    public AlleleID getKompID () {
	AlleleID id = null;

	id = this.filterIDs("KOMP-CSD-Project");
	if (id != null) { return id; }

	id = this.filterIDs("EUCOMM-projects");
	if (id != null) { return id; }

	id = this.filterIDs("NorCOMM-projects");
	return id;
    }

    // pull out the note with the given type from the set of all notes
    @Transient
    private String filterNotes(String noteType) {
	Iterator<AlleleNote> it = this.getNotes().iterator();
	AlleleNote note;

	while (it.hasNext()) {
	    note = it.next();
	    if (noteType.equals(note.getNoteType())) {
		return note.getNote();
	    }
	}
	return null;
    }

    @Transient
    public String getGeneralNote() {
	return this.filterNotes("General");
    }

    @Transient
    public String getMolecularNote() {
	return this.filterNotes("Molecular");
    }

    @Transient
    public String getRecombinaseUserNote() {
	return this.filterNotes("User (Cre)");
    }

    @Transient
    public String getDriverNote() {
	return this.filterNotes("Driver");
    }

    @Transient
    public String getDerivationNote() {
	return this.filterNotes("Derivation");
    }

    /**
     * return all of the allele notes.  This class is based off of the notes
     * class.
     * @return
     */

    @OneToMany (targetEntity=AlleleNote.class)
    @JoinColumn(name="allele_key")
    public List<AlleleNote> getNotes() {
        return notes;
    }

    /**
     * String representation of the allele only part of the allele symbol
     * @return
     */

    @Column(name="only_allele_symbol")
    public String getOnlyAlleleSymbol() {
        return onlyAlleleSymbol;
    }
    @Column(name="primary_id")
    public String getPrimaryID() {
        return primaryID;
    }

    /* go through list of AlleleRelatedMarker objects and return
     * only those with the specified relationship category.
     */
    @Transient
    private List<AlleleRelatedMarker> filterRelatedMarkers (String category) {

	ArrayList<AlleleRelatedMarker> sublist =
	    new ArrayList<AlleleRelatedMarker>();
	Iterator<AlleleRelatedMarker> it = this.getRelatedMarkers().iterator();
	AlleleRelatedMarker m;

	if (category == null) {		// nothing will match a null category
	    return sublist;
	}

	while (it.hasNext()) {
	    m = it.next();
	    if (category.equals(m.getRelationshipCategory())) {
		sublist.add(m);
	    }
	}
	return sublist;
    }

    /* get the set of 'mutation involves' related markers
     */
    @Transient
    public List<AlleleRelatedMarker> getMutationInvolvesMarkers() {
	return this.filterRelatedMarkers("mutation_involves");
    }

    /** return set of related markers, currently used for 'mutation involves'
     * relationships but can be expanded in the future.
     */
    @OneToMany (targetEntity=AlleleRelatedMarker.class, fetch=FetchType.LAZY)
    @JoinColumn(name="allele_key")
    @OrderBy("sequenceNum")
    @Filter(
	// enable this filter to only retrive markers flagged for usage in a
	// teaser on the allele detail page (big performance gain)
	name = "teaserMarkers",
	condition = "in_teaser = 1"
    )
    public List<AlleleRelatedMarker> getRelatedMarkers() {
	return relatedMarkers;
    }

    /** Return the RecombinaseInfo object associated with this allele.
     */
	@OneToOne (targetEntity=RecombinaseInfo.class,
			fetch=FetchType.LAZY,
			optional=false)
	@LazyToOne(value = LazyToOneOption.NO_PROXY)
	@JoinColumn (name="allele_key")
    public RecombinaseInfo getRecombinaseInfo() {
        return recombinaseInfo;
    }

    @Transient
    public Image getPrimaryImage() {
	Iterator<AlleleImageAssociation> it =
	    this.getImageAssociations().iterator();
	AlleleImageAssociation assoc;
	Image image;

	while (it.hasNext()) {
	    assoc = it.next();
	    if ("primary".equals(assoc.getQualifier())) {
	        image = assoc.getImage();
	        if ("Phenotypes".equals(image.getImageClass())) {
		    return image;
		}
	    }
	}
	return null;
    }

    @Transient
    public List<Image> getPhenotypeImages()
    {
		List<Image> phenoImages = new ArrayList<Image>();
    	for(AlleleImageAssociation assoc : getImageAssociations())
    	{
    		if ("Phenotypes".equals(assoc.getImage().getImageClass()))
    		{
    			phenoImages.add(assoc.getImage());
    		}
    	}
		return phenoImages;
    }

    @Transient
    public List<Image> getMolecularImages()
    {
		List<Image> molImages = new ArrayList<Image>();
    	for(AlleleImageAssociation assoc : getImageAssociations())
    	{
    		if ("Molecular".equals(assoc.getImage().getImageClass()))
    		{
    			molImages.add(assoc.getImage());
    		}
    	}
		return molImages;
    }

    /** return all the image associations for this allele.  No ordering has
     * yet been defined.
     */
    @OneToMany (targetEntity=AlleleImageAssociation.class)
    @JoinColumn (name="allele_key")
    public List<AlleleImageAssociation> getImageAssociations() {
	return imageAssociations;
    }

    @Transient
    public List<Reference> filterReferences (String qualifier) {
	Iterator<AlleleReferenceAssociation> associations =
	    this.getReferenceAssociations().iterator();
	AlleleReferenceAssociation assoc;
	Reference ref;
	List<Reference> refs = new ArrayList<Reference>();

	while (associations.hasNext()) {
	    assoc = associations.next();
	    ref = assoc.getReference();

	    // special case for all references
	    if (qualifier == null) {
			// desired qualifier is null, matches association qualifier
			if (assoc.getQualifier() == null) {
			    refs.add(ref);
			}
	    } 
	    else if (qualifier.equals("ALL")) {
	    	refs.add(ref);
	    } else if (qualifier.equals(assoc.getQualifier())) {
		// non-null desired qualifier, matches association qualifier
		refs.add(ref);
	    }
	}
	return refs;
    }

    @Transient
    public Reference getTransmissionReference() {
	List<Reference> refs = this.filterReferences("Transmission");
	if (refs.size() > 0) {
	    return refs.get(0);
	}
	return null;
    }

    @Transient
    public Reference getOriginalReference() {
	List<Reference> refs = this.filterReferences("Original");
	if (refs.size() > 0) {
	    return refs.get(0);
	}
	return null;
    }

    @Transient
    public List<Reference> getReferences() {
	List<Reference> refs = this.filterReferences("ALL");
	return refs;
    }

    @Transient
    public List<Reference> getMolecularReferences() {
	List<Reference> refs = this.filterReferences("Molecular");
	return refs;
    }

    @OneToMany (fetch=FetchType.LAZY)
    @JoinColumn(name="allele_key")
    @BatchSize(size=300)
    public List<AlleleReferenceAssociation> getReferenceAssociations() {
        return referenceAssociations;
    }

    /*
     * Diseases to be displayed on the allele phenotable summary (the grid)
     */
	@OneToMany (targetEntity=DiseaseTableDisease.class)
	@BatchSize(size=100)
	@JoinColumn(name="allele_key")
	@OrderBy("diseaseSeq")
    @Filter(
    		// This filter can be turned on to only select disease terms (no headers)
    		name = "noDiseaseHeaders",
    		condition="is_heading=0"
    	)
	public List<DiseaseTableDisease> getDiseaseTableDiseases()
	{
		return diseaseTableDiseases;
	}

	@Column(name="has_disease_model")
	public boolean getHasDiseaseModel() {
		return hasDiseaseModel;
	}

	@Transient
	public AlleleCellLine getParentCellLine() {
	    for (AlleleCellLine c : this.getAlleleCellLines()) {
		if (c.isParent()) {
		    return c;
		}
	    }
	    return null;
	}

	@Transient
	public List<AlleleCellLine> getMutantCellLines() {
	    ArrayList<AlleleCellLine> lines = new ArrayList<AlleleCellLine>();

	    if (this.getAlleleCellLines() != null) {
	        for (AlleleCellLine c : this.getAlleleCellLines()) {
	            if (c.isMutant()) {
	                lines.add(c);
	            }
	        }
	    }
	    return lines;
	}

	@OneToMany(targetEntity=AlleleCellLine.class)
	@BatchSize(size=250)
	@JoinColumn(name="allele_key")
	@OrderBy("sequenceNum")
	public List<AlleleCellLine> getAlleleCellLines() {
	    return this.cellLines;
	}

	@Transient
	public List<String> getMutations() {
	    ArrayList<String> m = new ArrayList<String>();
	    for (AlleleMutation am : this.getAlleleMutations()) {
		m.add(am.getMutation());
	    }
	    return m;
	}

	@OneToMany(targetEntity=AlleleMutation.class)
	@BatchSize(size=250)
	@JoinColumn(name="allele_key")
	@OrderBy("mutation")
	public List<AlleleMutation> getAlleleMutations() {
	    return this.mutations;
	}

	@OneToMany(targetEntity=PhenoTableGenotype.class)
	@BatchSize(size=250)
	@JoinColumn(name="allele_key")
    @OrderBy("genotypeSeq")
    public List<PhenoTableGenotype> getPhenoTableGenotypeAssociations() {
        return this.phenotableGenotypes;
    }

	@OneToMany(targetEntity=DiseaseTableGenotype.class)
	@BatchSize(size=100)
	@JoinColumn(name="allele_key")
    @OrderBy("genotypeSeq")
    public List<DiseaseTableGenotype> getDiseaseTableGenotypeAssociations() {
        return this.diseaseTableGenotypes;
    }

    @Transient
    public Marker getMarker() {
	List<Marker> markers = this.getMarkers();
	if ((markers != null) && (markers.size() > 0)) {
	    return markers.get(0);
	}
	return null;
    }

    @OneToMany(fetch=FetchType.LAZY)
    @JoinTable (name="marker_to_allele",
	joinColumns=@JoinColumn(name="allele_key"),
        inverseJoinColumns=@JoinColumn(name="marker_key")
	)
    @BatchSize(size=200)
    @OrderBy("symbol")
    public List<Marker> getMarkers() {
	return markers;
    }

    @OneToMany (fetch=FetchType.LAZY)
    @BatchSize(size=100)
    @JoinColumn(name="allele_key")
    public List<AlleleSequenceAssociation> getSequenceAssociations() {
	return sequenceAssociations;
    }

    // assumes 'qualifier' is not null
    @Transient
    public List<Sequence> filterSequences(String qualifier, boolean isNot) {
	List<AlleleSequenceAssociation> associations =
	    this.getSequenceAssociations();

	if (associations == null) { return null; }
	if (associations.size() == 0) { return null; }

	ArrayList<Sequence> seqs = new ArrayList<Sequence>();
	AlleleSequenceAssociation assoc;
	String assocQualifier;
	Iterator<AlleleSequenceAssociation> it = associations.iterator();

	while (it.hasNext()) {
	    assoc = it.next();
	    assocQualifier = assoc.getQualifier();

	    if (isNot) {
		if (!qualifier.equals(assocQualifier)) {
		    seqs.add(assoc.getSequence());
		}
	    } else {
		if (qualifier.equals(assocQualifier)) {
		    seqs.add(assoc.getSequence());
		}
	    }
	}
	return seqs;
    }

    @Transient
    public List<Sequence> getNonRepresentativeSequences() {
	return this.filterSequences("Representative", true);
    }

    @Transient
    public Sequence getRepresentativeSequence() {
	List<Sequence> repSeq = this.filterSequences("Representative", false);
	if (repSeq == null || repSeq.size()<1) { return null; }

	return repSeq.get(0);
    }

    public void setMarkers(List<Marker> markers) {
	this.markers = markers;
    }

    public void setSequenceAssociations(List<AlleleSequenceAssociation> sequenceAssociations) {
	this.sequenceAssociations = sequenceAssociations;
    }

//	@Transient
//	public List<Genotype> getPhenoTableGenotypes()
//	{
//		this.getPhenoTableGenotypeAssociations().size(); // Trick to have hibernate fetch entire list at once
//		List<Genotype> genotypes = new ArrayList<Genotype>();
//		for(PhenoTableGenotype ptg : this.getPhenoTableGenotypeAssociations())
//		{
//			genotypes.add(ptg.getGenotype());
//		}
//		return genotypes;
//	}

    public String getSymbol() {
        return symbol;
    }
    @OneToMany (targetEntity=AlleleSynonym.class)
    @JoinColumn(name="allele_key")
    @OrderBy("synonym")
    @BatchSize(size=300)
    public List<AlleleSynonym> getSynonyms() {
        return synonyms;
    }

    public void setAlleleKey(int alleleKey) {
        this.alleleKey = alleleKey;
    }
    public void setAlleleSubType(String alleleSubType) {
        this.alleleSubType = alleleSubType;
    }

    public void setAlleleSystems(List<AlleleSystem> alleleSystems) {
        this.alleleSystems = alleleSystems;
    }

    public void setAlleleType(String alleleType) {
        this.alleleType = alleleType;
    }

    public void setChromosome(String chromosome) {
        this.chromosome = chromosome;
    }
    public void setCollection(String collection) {
        this.collection = collection;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    public void setTransmissionPhrase(String transmissionPhrase) {
        this.transmissionPhrase = transmissionPhrase;
    }

    public void setInheritanceMode(String inheritanceMode) {
        this.inheritanceMode = inheritanceMode;
    }

    public void setAnnotations(List<Annotation> annotations) {
		this.annotations = annotations;
	}

    public void setCountOfExpressionAssayResults(Integer countOfExpressionAssayResults) {
        this.countOfExpressionAssayResults = countOfExpressionAssayResults;
    }

    public void setCountOfMarkers(Integer countOfMarkers) {
        this.countOfMarkers = countOfMarkers;
    }

    public void setCountOfMutationInvolvesMarkers(
	    Integer countOfMutationInvolvesMarkers) {
        this.countOfMutationInvolvesMarkers = countOfMutationInvolvesMarkers;
    }

    public void setCountOfReferences(Integer countOfReferences) {
        this.countOfReferences = countOfReferences;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public void setGeneName(String geneName) {
        this.geneName = geneName;
    }

    public void setGenotypeAssociations(
			List<AlleleGenotypeAssociation> genotypeAssociations) {
		this.genotypeAssociations = genotypeAssociations;
	}

    public void setIds(List<AlleleID> ids) {
        this.ids = ids;
    }

    public void setImsrCellLineCount(Integer imsrCellLineCount) {
		this.imsrCellLineCount = imsrCellLineCount;
	}

    public void setImsrCountForMarker(Integer imsrCountForMarker) {
		this.imsrCountForMarker = imsrCountForMarker;
	}

    public void setImsrStrainCount(Integer imsrStrainCount) {
		this.imsrStrainCount = imsrStrainCount;
	}

    public void setInducibleNote(String inducibleNote) {
        this.inducibleNote = inducibleNote;
    }

    public void setIsRecombinase(int isRecombinase) {
        this.isRecombinase = isRecombinase;
    }

    public void setIsWildType(int isWildType) {
	this.isWildType = isWildType;
    }

    public void setPrimaryImageKey(Integer primaryImageKey)
    {
    	this.primaryImageKey=primaryImageKey;
    }

    public void setIsMixed(int isMixed) {
	this.isMixed = isMixed;
    }

    public void setStrain(String strain) {
	this.strain = strain;
    }

    public void setStrainLabel(String strainLabel) {
	this.strainLabel = strainLabel;
    }

    public void setMolecularDescription(String molecularDescription) {
        this.molecularDescription = molecularDescription;
    }

    /* The three IMSR counts exist in a separate table (allele_imsr_counts)
     * which we join in to make the attributes appear as if they were
     * sitting in the allele table itself.
     */

    public void setName(String name) {
        this.name = name;
    }

	public void setNotes(List<AlleleNote> notes) {
        this.notes = notes;
    }

    public void setOnlyAlleleSymbol(String onlyAlleleSymbol) {
        this.onlyAlleleSymbol = onlyAlleleSymbol;
    }

	public void setPrimaryID(String primaryID) {
        this.primaryID = primaryID;
    }

    public void setRecombinaseInfo(RecombinaseInfo recombinaseInfo) {
		this.recombinaseInfo = recombinaseInfo;
	}

    public void setImageAssociations(List<AlleleImageAssociation> imageAssociations) {
        this.imageAssociations = imageAssociations;
    }

    public void setReferenceAssociations(
	    List<AlleleReferenceAssociation> referenceAssociations) {
        this.referenceAssociations = referenceAssociations;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

	public void setSynonyms(List<AlleleSynonym> synonyms) {
        this.synonyms = synonyms;
    }

	public void setAlleleCellLines(List<AlleleCellLine> cellLines) {
		this.cellLines = cellLines;
	}

	public void setAlleleMutations(List<AlleleMutation> mutations) {
		this.mutations = mutations;
	}

	public void setIncidentalMutations(List<AlleleIncidentalMutation> incidentalMutations) {
		this.incidentalMutations = incidentalMutations;
	}
	public void setSummarySystems(List<AlleleSummarySystem> summarySystems) {
		this.summarySystems = summarySystems;
	}
	public void setSummaryDiseases(List<AlleleSummaryDisease> summaryDiseases) {
		this.summaryDiseases = summaryDiseases;
	}

	public void setPhenoTableSystems(List<PhenoTableSystem> phenoTableSystems) {
		this.phenoTableSystems = phenoTableSystems;
	}

	public void setDiseaseTableDiseases(List<DiseaseTableDisease> diseaseTableDiseases)
	{
		this.diseaseTableDiseases=diseaseTableDiseases;
	}

	public void setHasDiseaseModel(boolean hasDiseaseModel) {
		this.hasDiseaseModel = hasDiseaseModel;
	}

	public void setDiseaseTableGenotypeAssociations(List<DiseaseTableGenotype> diseaseTableGenotypes) {
        this.diseaseTableGenotypes=diseaseTableGenotypes;
	}

	public void setRelatedMarkers (
	    List<AlleleRelatedMarker> relatedMarkers) {
		this.relatedMarkers = relatedMarkers;
	}

	public void setPhenoTableGenotypeAssociations(List<PhenoTableGenotype> phenotableGenotypes) {
        this.phenotableGenotypes=phenotableGenotypes;
	}

	/*
	 * Transient Methods
	 */
	@Transient
	public boolean hasPrimaryImage()
	{
		return this.getPrimaryImageKey() != null;
	}

	@Override
    public String toString() {
        return "Allele [alleleKey=" + alleleKey + ", alleleSubType="
                + alleleSubType + ", alleleType=" + alleleType
                + ", countOfMarkers=" + countOfMarkers + ", countOfReferences="
                + countOfReferences + ", driver=" + driver + ", geneName="
                + geneName + ", ids=" + ids + ", inducibleNote="
                + inducibleNote + ", isRecombinase=" + isRecombinase
                + ", molecularDescription=" + molecularDescription + ", name="
                + name + ", onlyAlleleSymbol=" + onlyAlleleSymbol
                + ", primaryID=" + primaryID + ", symbol=" + symbol + "]";
    }

}
