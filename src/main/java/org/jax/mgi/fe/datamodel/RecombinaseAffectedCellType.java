package org.jax.mgi.fe.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * RecombinaseAffectedCellType - cell types (headers) where recomb activity has been detected
 */
@Entity
@Table(name="recombinase_affected_cell_types")
public class RecombinaseAffectedCellType {

	private int uniqueKey;
	private int alleleKey;
	private int cellTypeHeaderKey;
	private String cellTypeHeader;

    // ================= Getters ======================================== //

	@Id
	@Column(name="unique_key")
	public int getUniqueKey() {
		return uniqueKey;
	}

	@Column(name="allele_key")
	public int getAlleleKey() {
		return alleleKey;
	}

	@Column(name="cell_type_header_key")
	public int getCellTypeHeaderKey() {
		return cellTypeHeaderKey;
	}

	@Column(name="cell_type_header")
	public String getCellTypeHeader() {
		return cellTypeHeader;
	}

    // ================= Setters ======================================== //

	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	public void setAlleleKey(int alleleKey) {
		this.alleleKey = alleleKey;
	}

	public void setCellTypeHeaderKey(int cellTypeHeaderKey) {
		this.cellTypeHeaderKey = cellTypeHeaderKey;
	}

	public void setCellTypeHeader(String cellTypeHeader) {
		this.cellTypeHeader = cellTypeHeader;
	}
}
