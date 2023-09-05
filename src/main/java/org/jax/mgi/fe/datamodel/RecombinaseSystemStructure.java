package org.jax.mgi.fe.datamodel;

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
    private String cellData;


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
