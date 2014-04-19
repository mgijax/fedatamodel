package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Recombinase System Structure
 * -child of Recombinase System Structure
 */
@Entity
@Table (name="recombinase_system_structure")
public class RecombinaseSystemStructure {

    private int systemStructureKey;
    private int alleleSystemKey;
    private String structure;
    private Integer ageE1;
    private Integer ageE2;
    private Integer ageE3;
    private Integer ageP1;
    private Integer ageP2;
    private Integer ageP3;
    private Integer hasImage;


    // ================= Getters and Setters ===================== //

    @Id
    @Column(name="system_structure_key")
    public int getSystemStructureKey() {
		return systemStructureKey;
	}
	public void setSystemStructureKey(int systemStructureKey) {
		this.systemStructureKey = systemStructureKey;
	}


    @Column(name="allele_system_key")
    public int getAlleleSystemKey() {
		return alleleSystemKey;
	}
	public void setAlleleSystemKey(int alleleSystemKey) {
		this.alleleSystemKey = alleleSystemKey;
	}


    /**
     * Return just the system
     * @return
     */
    @Column(name="structure")
    public String getStructure() {
        return structure;
    }
    public void setStructure(String structure) {
        this.structure = structure;
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


//    @Override
//    public String toString() {
//        return "AlleleSystem [allele=" + allele + ", alleleID=" + alleleID
//                + ", alleleSystemAssayResults=" + alleleSystemAssayResults
//                + ", alleleSystemKey=" + alleleSystemKey + ", images=" + images
//                + ", otherAlleles=" + otherAlleles + ", otherSystems="
//                + otherSystems + ", system=" + system + ", systemKey="
//                + systemKey + "]";
//    }

}
