package mgi.frontend.datamodel;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import mgi.frontend.datamodel.util.DatamodelUtils;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.BatchSize;

/**
 * Recombinase Allele System
 */
@Entity
@Table (name="recombinase_allele_system")
@JsonIgnoreProperties({"images"})
public class AlleleSystem {

    private int alleleSystemKey;
    private Integer systemKey;
    private String alleleID;
    private String system;
    private Allele allele;
    private Integer ageE1;
    private Integer ageE2;
    private Integer ageE3;
    private Integer ageP1;
    private Integer ageP2;
    private Integer ageP3;
    private Integer hasImage;
    private List<AlleleSystemOtherAllele> otherAlleles;
    private List<AlleleSystemOtherSystem> otherSystems;
    private List<AlleleSystemAssayResult> alleleSystemAssayResults;
    private List<RecombinaseSystemStructure> recombinaseSystemStructures;
    private List<Image> images;


    // ================= Getters and Setters ===================== //


    /**
     * allele system key
     * @return int
     */
    @Id
    @Column(name="allele_system_key")
    public int getAlleleSystemKey() {
        return alleleSystemKey;
    }
    public void setAlleleSystemKey(int alleleSystemKey) {
        this.alleleSystemKey = alleleSystemKey;
    }


    /**
     * system key
     * @return Integer
     */
    @Column(name="system_key")
    public Integer getSystemKey() {
		return systemKey;
	}
	public void setSystemKey(Integer systemKey) {
		this.systemKey = systemKey;
	}


    /**
     * allele ID
     * @return String
     */
    @Column(name="allele_id")
    public String getAlleleID() {
		return alleleID;
	}
	public void setAlleleID(String alleleID) {
		this.alleleID = alleleID;
	}


    /**
     * system
     * @return String
     */
    public String getSystem() {
        return system;
    }
    public void setSystem(String system) {
        this.system = system;
    }


    /**
     * Age range - e1
     * @return Integer
     */
    @Column(name="age_e1")
    public Integer getAgeE1() {
		return ageE1;
	}
	public void setAgeE1(Integer ageE1) {
		this.ageE1 = ageE1;
	}

    /**
     * Age range - e2
     * @return Integer
     */
    @Column(name="age_e2")
    public Integer getAgeE2() {
		return ageE2;
	}
	public void setAgeE2(Integer ageE2) {
		this.ageE2 = ageE2;
	}

    /**
     * Age range - e3
     * @return Integer
     */
    @Column(name="age_e3")
    public Integer getAgeE3() {
		return ageE3;
	}
	public void setAgeE3(Integer ageE3) {
		this.ageE3 = ageE3;
	}


    /**
     * Age range - p1
     * @return Integer
     */
    @Column(name="age_p1")
    public Integer getAgeP1() {
		return ageP1;
	}
	public void setAgeP1(Integer ageP1) {
		this.ageP1 = ageP1;
	}

    /**
     * Age range - p2
     * @return Integer
     */
    @Column(name="age_p2")
    public Integer getAgeP2() {
		return ageP2;
	}
	public void setAgeP2(Integer ageP2) {
		this.ageP2 = ageP2;
	}

    /**
     * Age range - p3
     * @return Integer
     */
    @Column(name="age_p3")
    public Integer getAgeP3() {
		return ageP3;
	}
	public void setAgeP3(Integer ageP3) {
		this.ageP3 = ageP3;
	}


    /**
     * Does this have an image?
     * @return Integer
     */
    @Column(name="has_image")
    public Integer getHasImage() {
		return hasImage;
	}
	public void setHasImage(Integer hasImage) {
		this.hasImage = hasImage;
	}

    /**
     * gather the parent allele
     * @return Allele
     */
    @ManyToOne (targetEntity=Allele.class, fetch=FetchType.LAZY)
    @JoinColumn (name="allele_key")
    public Allele getAllele() {
        return allele;
    }
    public void setAllele(Allele allele) {
        this.allele = allele;
    }


    /**
     * Other alleles that are associated with this system
     * @return List<AlleleSystemOtherAllele>
     */
    @OneToMany (targetEntity=AlleleSystemOtherAllele.class)
    @JoinColumn(name="allele_system_key")
    @OrderBy ("otherAlleleSymbol")
    public List<AlleleSystemOtherAllele> getOtherAlleles() {
        return otherAlleles;
    }
    public void setOtherAlleles(List<AlleleSystemOtherAllele> alleles) {
        this.otherAlleles = alleles;
    }


    /**
     * Other systems that are associated to this allele
     * @return List<AlleleSystemOtherSystem>
     */
    @OneToMany (targetEntity=AlleleSystemOtherSystem.class)
    @JoinColumn(name="allele_system_key")
    public List<AlleleSystemOtherSystem> getOtherSystems() {
        return otherSystems;
    }
    public void setOtherSystems(List<AlleleSystemOtherSystem> systems) {
        this.otherSystems = systems;
    }


    /**
     * systems assay results
     * @return List<AlleleSystemAssayResult>
     */
	@OneToMany (targetEntity=AlleleSystemAssayResult.class)
    @JoinColumn(name="allele_system_key")
    public List<AlleleSystemAssayResult> getAlleleSystemAssayResults() {
        return alleleSystemAssayResults;
    }
    public void setAlleleSystemAssayResults(List<AlleleSystemAssayResult> a) {
        this.alleleSystemAssayResults = a;
    }


    /**
     * Recombinase System Structures
     * @return List<RecombinaseSystemStructure>
     */
	@OneToMany (targetEntity=RecombinaseSystemStructure.class)
    @JoinColumn(name="allele_system_key")
	@BatchSize(size=100)
    public List<RecombinaseSystemStructure> getRecombinaseSystemStructures() {
        return recombinaseSystemStructures;
    }
    public void setRecombinaseSystemStructures(List<RecombinaseSystemStructure> a) {
        this.recombinaseSystemStructures = a;
    }


    /**
     * images
     * @return List<Image>
     */
    @OneToMany
    @JoinTable (name="recombinase_allele_system_to_image",
            joinColumns=@JoinColumn(name="allele_system_key"),
            inverseJoinColumns=@JoinColumn(name="image_key")
            )
    @OrderBy ("jnumID,figureLabel")
    public List<Image> getImages() {
        return images;
    }
    public void setImages(List<Image> images) {
        this.images = images;
    }


	/* Transient Properties */
    @Transient
    public String getCssClass() {
        return DatamodelUtils.makeCssSafe(this.system) + "_class";
    }
    @Transient
    public String getCssId() {
        return DatamodelUtils.makeCssSafe(this.system) + "_id";
    }


    @Override
    public String toString() {
        return "AlleleSystem [allele=" + allele + ", alleleID=" + alleleID
                + ", alleleSystemAssayResults=" + alleleSystemAssayResults
                + ", alleleSystemKey=" + alleleSystemKey + ", images=" + images
                + ", otherAlleles=" + otherAlleles + ", otherSystems="
                + otherSystems + ", system=" + system + ", systemKey="
                + systemKey + "]";
    }

}
