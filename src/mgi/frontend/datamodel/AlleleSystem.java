package mgi.frontend.datamodel;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Recombinase Allele System Objects
 * @author mhall
 * This is a new object concept, which is the pairing of recombinase systems
 * and the alleles that they are expressed in.
 */
@Entity
@Table (name="recombinase_allele_system")
@JsonIgnoreProperties({"images"})
public class AlleleSystem {

    private int alleleKey;
    private List<AlleleSystemAssayResult> alleleSystemAssayResults;
    private int alleleSystemKey;
    private Integer systemKey;
    private String alleleID;
    private List<Image> images;
    private List<AlleleSystemOtherAllele> otherAlleles;
    private List<AlleleSystemOtherSystem> otherSystems;
    private String system;
    private Allele allele;
    
    // ================= Getters and Setters ===================== //
    
    @OneToOne (targetEntity=Allele.class)
    @JoinColumn(name="allele_key")  
    public Allele getAllele() {
        return allele;
    }

    /**
     * Return the allele key from the allele system pairing
     * @return int
     */
    @Column(name="allele_key")
    public int getAlleleKey() {
        return alleleKey;
    }
    
    @Column(name="allele_id")
    public String getAlleleID() {
		return alleleID;
	}

	public void setAlleleID(String alleleID) {
		this.alleleID = alleleID;
	}

	@OneToMany (targetEntity=AlleleSystemAssayResult.class)
    @JoinColumn(name="allele_system_key")
    public List<AlleleSystemAssayResult> getAlleleSystemAssayResults() {
        return alleleSystemAssayResults;
    }
    
    /**
     * Returns the compound allele system key
     * @return int
     */
    @Id
    @Column(name="allele_system_key")
    public int getAlleleSystemKey() {
        return alleleSystemKey;
    }
    
    @OneToMany
    @JoinTable (name="recombinase_allele_system_to_image",
            joinColumns=@JoinColumn(name="allele_system_key"),
            inverseJoinColumns=@JoinColumn(name="image_key")
            )
    public List<Image> getImages() {
        return images;
    }
    
    /**
     * Returns a list of the other alleles that are associated with this 
     * allele system.  So these alleles would have the system itself in 
     * common.
     * @return
     */
    
    @OneToMany (targetEntity=AlleleSystemOtherAllele.class)
    @JoinColumn(name="allele_system_key")
    public List<AlleleSystemOtherAllele> getOtherAlleles() {
        return otherAlleles;
    }
    
    /**
     * Returns a list of the other systems that are associated to
     * this allele system.  So these systems would have the allele
     * itself in common.
     * @return
     */
    @OneToMany (targetEntity=AlleleSystemOtherSystem.class)
    @JoinColumn(name="allele_system_key")
    public List<AlleleSystemOtherSystem> getOtherSystems() {
        return otherSystems;
    }
    
    /**
     * Return just the system
     * @return
     */
    public String getSystem() {
        return system;
    }

    public void setAllele(Allele allele) {
        this.allele = allele;
    }
    
    /**
     * Return the system's term key from the allele system pairing
     * @return int
     */
    @Column(name="system_key")
    public Integer getSystemKey() {
		return systemKey;
	}

	public void setSystemKey(Integer systemKey) {
		this.systemKey = systemKey;
	}

	public void setAlleleKey(int alleleKey) {
        this.alleleKey = alleleKey;
    }    
    
    public void setAlleleSystemAssayResults(List<AlleleSystemAssayResult> a) {
        this.alleleSystemAssayResults = a;
    }

    public void setAlleleSystemKey(int alleleSystemKey) {
        this.alleleSystemKey = alleleSystemKey;
    }
    
    // This stuff currently brings back duplicate entries.. for whatever reason.
    
    public void setImages(List<Image> images) {
        this.images = images;
    }
    public void setOtherAlleles(List<AlleleSystemOtherAllele> alleles) {
        this.otherAlleles = alleles;
    }
    
    public void setOtherSystems(List<AlleleSystemOtherSystem> systems) {
        this.otherSystems = systems;
    }
    public void setSystem(String system) {
        this.system = system;
    }
    @Override
    public String toString() {
        return "AlleleSystem [alleleKey=" + alleleKey
                + ", alleleSystemKey=" + alleleSystemKey + ", system=" + system
                + "]";
    }


}
