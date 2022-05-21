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

import mgi.frontend.datamodel.group.RecombinaseEntity;
import mgi.frontend.datamodel.util.DatamodelUtils;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.BatchSize;

/**
 * Recombinase Allele System
 */
@Entity
@Table (name="recombinase_allele_system")
@JsonIgnoreProperties({"images"})
public class AlleleSystem implements RecombinaseEntity {

    private int alleleSystemKey;
    private String alleleID;
    private String system;
    private Allele allele;
    private String cellData;
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
     * Cell data - JSON string encoding multiple counts per cell in this row
     * @return String
     */
    @Column(name="cell_data")
    public String getCellData() {
		return cellData;
	}
	public void setCellData(String cellData) {
		this.cellData = cellData;
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
                + "]";
    }

}
