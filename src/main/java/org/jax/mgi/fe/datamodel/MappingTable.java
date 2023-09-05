package org.jax.mgi.fe.datamodel;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Is: a table of data for a mapping experiment
 */
@Entity
@Table(name="mapping_table")
public class MappingTable {
	private int tableKey;
	private int experimentKey;
	private String tableType;
	private int sequenceNum;
	private List<MappingTableRow> rows;
	
    // ================= Transient Methods ===================== //
	
	@Transient
	public MappingTableRow getHeaderRow() {
		for (MappingTableRow r : getRows()) {
			if (r.getIsHeader() == 1) {
				return r;
			}
		}
		return null;
	}

	@Transient
	public List<MappingTableRow> getDataRows() {
		List<MappingTableRow> dataRows = new ArrayList<MappingTableRow>();
		for (MappingTableRow r : getRows()) {
			if (r.getIsHeader() == 0) {
				dataRows.add(r);
			}
		}
		return dataRows;
	}
	
    // ================= Getters and Setters ===================== //

	@Column(name="experiment_key")
	public int getExperimentKey() {
		return experimentKey;
	}

    @OneToMany (targetEntity=MappingTableRow.class)
    @JoinColumn(name="mapping_table_key")
    @OrderBy("sequenceNum")
	public List<MappingTableRow> getRows() {
		return rows;
	}

	@Column(name="sequence_num")
	public int getSequenceNum() {
		return sequenceNum;
	}

	@Id
	@Column(name="mapping_table_key")
	public int getTableKey() {
		return tableKey;
	}

	@Column(name="table_type")
	public String getTableType() {
		return tableType;
	}

	public void setExperimentKey(int experimentKey) {
		this.experimentKey = experimentKey;
	}
	public void setRows(List<MappingTableRow> rows) {
		this.rows = rows;
	}
	public void setSequenceNum(int sequenceNum) {
		this.sequenceNum = sequenceNum;
	}
	public void setTableKey(int tableKey) {
		this.tableKey = tableKey;
	}
	public void setTableType(String tableType) {
		this.tableType = tableType;
	}
	@Override
	public String toString() {
		return "MappingTable [tableKey=" + tableKey + ", experimentKey=" + experimentKey + ", tableType=" + tableType
				+ ", sequenceNum=" + sequenceNum + "]";
	}
}
