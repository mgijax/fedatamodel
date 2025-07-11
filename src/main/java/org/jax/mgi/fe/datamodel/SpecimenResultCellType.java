package org.jax.mgi.fe.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * One cell type for an expression result/specimen pair.
 * @author jsb
 */

@Entity
@Table(name="specimen_result_cell_type")
public class SpecimenResultCellType {

    private int uniqueKey;
    private int specimenResultKey;
    private String cellType;
    private String cellTypeId;
    private int sequenceNum;

    // ================= Getters and Setters ===================== //

    @Id
	@Column(name="unique_key")
	public int getUniqueKey() {
		return uniqueKey;
	}

	@Column(name="specimen_result_key")
	public int getSpecimenResultKey() {
		return specimenResultKey;
	}

	@Column(name="cell_type")
	public String getCellType() {
		return cellType;
	}

	@Column(name="cell_type_id")
	public String getCellTypeId() {
		return cellTypeId;
	}

	@Column(name="sequence_num")
	public int getSequenceNum() {
		return sequenceNum;
	}
	
	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	public void setSpecimenResultKey(int specimenResultKey) {
		this.specimenResultKey = specimenResultKey;
	}

	public void setCellType(String cellType) {
		this.cellType = cellType;
	}
    
	public void setCellTypeId(String cellTypeId) {
		this.cellTypeId = cellTypeId;
	}	

	public void setSequenceNum(int sequenceNum) {
		this.sequenceNum = sequenceNum;
	}

	@Override
	public String toString() {
		return "SpecimenResultCellType [uniqueKey=" + uniqueKey + ", specimenResultKey=" + specimenResultKey
				+ ", cellType=" + cellType + ", sequenceNum=" + sequenceNum + "]";
	}
}
