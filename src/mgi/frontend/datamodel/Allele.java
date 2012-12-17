package mgi.frontend.datamodel;

import java.util.*;

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

import mgi.frontend.datamodel.phenotype.DiseaseTableDisease;
import mgi.frontend.datamodel.phenotype.DiseaseTableGenotype;
import mgi.frontend.datamodel.phenotype.PhenoTableGenotype;
import mgi.frontend.datamodel.phenotype.PhenoTableDisease;
import mgi.frontend.datamodel.phenotype.PhenoTableSystem;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;

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
@FilterDef(name="noDiseaseHeaders")
@JsonIgnoreProperties({"references", "notes", "molecularDescription", "ids"})
public class Allele {
    private int alleleKey;
    private String alleleSubType;
    private List<AlleleSystem> alleleSystems;
    private String alleleType;
    private Integer countOfMarkers;
    private Integer countOfReferences;
    private String driver;
    private String geneName;
    private Set<AlleleID> ids;
    private String inducibleNote;
    private int isRecombinase;
    private int isWildType;
    private String molecularDescription;
    private String name;
    private List<AlleleNote> notes;
    private String onlyAlleleSymbol;
    private String primaryID;
    private List<Reference> references;
    private String symbol;
    private Set<AlleleSynonym> synonyms;
    private Integer imsrCellLineCount;
    private Integer imsrStrainCount;
    private Integer imsrCountForMarker;
    private RecombinaseInfo recombinaseInfo;
    private List<AlleleGenotypeAssociation> genotypeAssociations;
    private List<Annotation> annotations;
	private List<PhenoTableSystem> phenoTableSystems;
	private List<PhenoTableGenotype> phenotableGenotypes;
	private boolean hasDiseaseModel;
	private List<DiseaseTableDisease> diseaseTableDiseases;
	private List<DiseaseTableGenotype> diseaseTableGenotypes;
	


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

	/**
     * Return the allesystem objects.  This is only really
     * relevant if its a cre allele.
     * @return
     */

    @OneToMany
    @JoinTable (name="recombinase_allele_system",
            joinColumns=@JoinColumn(name="allele_key"),
            inverseJoinColumns=@JoinColumn(name="allele_system_key")
            )
    public List<AlleleSystem> getAlleleSystems() {
        return alleleSystems;
    }

    @Column(name="allele_type")
    public String getAlleleType() {
        return alleleType;
    }

    @OneToMany (fetch=FetchType.LAZY)
    @JoinColumn(name="allele_key")
    public List<Annotation> getAnnotations() {
    	return annotations;
    }

    @Column(table="allele_counts", name="marker_count")
    @JoinColumn(name="allele_key")
    public Integer getCountOfMarkers() {
        return countOfMarkers;
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
    public Set<AlleleID> getIds() {
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

	@Column(name="molecular_description")
    public String getMolecularDescription() {
        return molecularDescription;
    }

    public String getName() {
        return name;
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

    /** Return the RecombinaseInfo object associated with this allele.
     */
	@OneToOne (targetEntity=RecombinaseInfo.class, fetch=FetchType.LAZY)
	@JoinColumn (name="allele_key")
    public RecombinaseInfo getRecombinaseInfo() {
        return recombinaseInfo;
    }
    @OneToMany
    @JoinTable (name="allele_to_reference",
            joinColumns=@JoinColumn(name="allele_key"),
            inverseJoinColumns=@JoinColumn(name="reference_key")
            )
    @OrderBy("year, jnumNumeric")
    public List<Reference> getReferences() {
        return references;
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
    public Set<AlleleSynonym> getSynonyms() {
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

    public void setAnnotations(List<Annotation> annotations) {
		this.annotations = annotations;
	}
    public void setCountOfMarkers(Integer countOfMarkers) {
        this.countOfMarkers = countOfMarkers;
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

    public void setIds(Set<AlleleID> ids) {
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

	public void setReferences(List<Reference> references) {
        this.references = references;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

	public void setSynonyms(Set<AlleleSynonym> synonyms) {
        this.synonyms = synonyms;
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
	
	public void setPhenoTableGenotypeAssociations(List<PhenoTableGenotype> phenotableGenotypes) {
        this.phenotableGenotypes=phenotableGenotypes;
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
