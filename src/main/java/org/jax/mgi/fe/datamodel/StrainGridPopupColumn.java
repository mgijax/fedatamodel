package org.jax.mgi.fe.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="strain_grid_popup_column")
public class StrainGridPopupColumn {
	private int columnKey;
	private String term;
	private int gridCellKey;
	private int sequenceNum;

	@Id
	@Column(name="column_key")
	public int getColumnKey() {
		return columnKey;
	}

	@Column(name="grid_cell_key")
	public int getGridCellKey() {
		return gridCellKey;
	}

	@Column(name="sequence_num")
	public int getSequenceNum() {
		return sequenceNum;
	}

	@Column(name="term")
	public String getTerm() {
		return term;
	}

	public void setColumnKey(int columnKey) {
		this.columnKey = columnKey;
	}

	public void setGridCellKey(int gridCellKey) {
		this.gridCellKey = gridCellKey;
	}

	public void setSequenceNum(int sequenceNum) {
		this.sequenceNum = sequenceNum;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	@Override
	public String toString() {
		return "StrainGridPopupColumn [columnKey=" + columnKey + ", term=" + term + ", gridCellKey=" + gridCellKey
				+ ", sequenceNum=" + sequenceNum + "]";
	}
}
