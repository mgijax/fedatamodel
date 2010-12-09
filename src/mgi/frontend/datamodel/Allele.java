package mgi.frontend.datamodel;

import java.util.List;
import java.util.Set;

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

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Base object alleles.  This is represented by a core in our flower schema.
 * @author mhall, jsb
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
    }  )
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
