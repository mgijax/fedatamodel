package mgi.frontend.datamodel;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;

@Entity
@Table(name="Allele")
@SecondaryTables (
    { 
      @SecondaryTable (name="allele_counts", pkJoinColumns= {
        @PrimaryKeyJoinColumn(name="allele_key", referencedColumnName="allele_key") } ) 
    } )
public class Allele {
    private int alleleKey;
    private String symbol;
    private String name;
    private String onlyAlleleSymbol;
    private String geneName;
    private String primaryID;
    private String alleleType;
    private String alleleSubType;
    private int isRecombinase;
    private String driver;
    private String inducableNote;
    private String molecularDescription;
    private Set<AlleleID> ids;
    private List<AlleleNote> notes;
    private Set<AlleleSynonym> synonyms;
    private Integer countOfMarkers;
    private Integer countOfReferences;
    private List<Reference> references;
    
    @Id
    @Column(name="allele_key")
    public int getAlleleKey() {
        return alleleKey;
    }
    public void setAlleleKey(int alleleKey) {
        this.alleleKey = alleleKey;
    }
    public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="only_allele_symbol")
    public String getOnlyAlleleSymbol() {
        return onlyAlleleSymbol;
    }
    public void setOnlyAlleleSymbol(String onlyAlleleSymbol) {
        this.onlyAlleleSymbol = onlyAlleleSymbol;
    }
    
    @Column(name="gene_name")
    public String getGeneName() {
        return geneName;
    }
    public void setGeneName(String geneName) {
        this.geneName = geneName;
    }
    
    @Column(name="primary_id")
    public String getPrimaryID() {
        return primaryID;
    }
    public void setPrimaryID(String primaryID) {
        this.primaryID = primaryID;
    }
    
    @Column(name="allele_type")
    public String getAlleleType() {
        return alleleType;
    }
    public void setAlleleType(String alleleType) {
        this.alleleType = alleleType;
    }
    
    @Column(name="allele_subtype")
    public String getAlleleSubType() {
        return alleleSubType;
    }
    public void setAlleleSubType(String alleleSubType) {
        this.alleleSubType = alleleSubType;
    }
    
    @Column(name="is_recombinase")
    public int getIsRecombinase() {
        return isRecombinase;
    }
    public void setIsRecombinase(int isRecombinase) {
        this.isRecombinase = isRecombinase;
    }
    
    
    public String getDriver() {
        return driver;
    }
    public void setDriver(String driver) {
        this.driver = driver;
    }
    
    @Column(name="inducible_note")
    public String getInducableNote() {
        return inducableNote;
    }
    public void setInducableNote(String inducableNote) {
        this.inducableNote = inducableNote;
    }
    
    @Column(name="molecular_description")
    public String getMolecularDescription() {
        return molecularDescription;
    }
    public void setMolecularDescription(String molecularDescription) {
        this.molecularDescription = molecularDescription;
    }
    
    @OneToMany (targetEntity=AlleleID.class)
    @JoinColumn (name="allele_key")
    public Set<AlleleID> getIds() {
        return ids;
    }
    public void setIds(Set<AlleleID> ids) {
        this.ids = ids;
    }
    
    @Column(table="allele_counts", name="marker_count")
    @JoinColumn(name="allele_key")
    public Integer getCountOfMarkers() {
        return countOfMarkers;
    }

    public void setCountOfMarkers(Integer countOfMarkers) {
        this.countOfMarkers = countOfMarkers;
    }
    
    @Column(table="allele_counts", name="reference_count")
    @JoinColumn(name="allele_key")
    public Integer getCountOfReferences() {
        return countOfReferences;
    }

    public void setCountOfReferences(Integer countOfReferences) {
        this.countOfReferences = countOfReferences;
    }
    
    @OneToMany (targetEntity=AlleleNote.class)
    @JoinColumn(name="allele_key")
    public List<AlleleNote> getNotes() {
        return notes;
    }

    public void setNotes(List<AlleleNote> notes) {
        this.notes = notes;
    }
    
    @OneToMany (targetEntity=AlleleSynonym.class)
    @JoinColumn(name="allele_key")
    public Set<AlleleSynonym> getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(Set<AlleleSynonym> synonyms) {
        this.synonyms = synonyms;
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
    
    public void setReferences(List<Reference> references) {
        this.references = references;
    }
    
    @Override
    public String toString() {
        return "Allele [alleleKey=" + alleleKey + ", alleleSubType="
                + alleleSubType + ", alleleType=" + alleleType
                + ", countOfMarkers=" + countOfMarkers + ", countOfReferences="
                + countOfReferences + ", driver=" + driver + ", geneName="
                + geneName + ", ids=" + ids + ", inducableNote="
                + inducableNote + ", isRecombinase=" + isRecombinase
                + ", molecularDescription=" + molecularDescription + ", name="
                + name + ", onlyAlleleSymbol=" + onlyAlleleSymbol
                + ", primaryID=" + primaryID + ", symbol=" + symbol + "]";
    }

}
