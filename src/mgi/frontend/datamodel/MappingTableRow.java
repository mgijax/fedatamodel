package mgi.frontend.datamodel;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 * Is: one row in a table of data for a mapping experiment
 */
@Entity
@Table(name="mapping_table_row")
public class MappingTableRow {
	private int tableKey;
	private int rowKey;
	private int isHeader;
	private int sequenceNum;
	private List<MappingTableCell> cells;
	
    // ================= Getters and Setters ===================== //

	@Id
	@Column(name="row_key")
	public int getRowKey() {
		return rowKey;
	}

    @OneToMany (targetEntity=MappingTableCell.class)
    @JoinColumn(name="row_key")
    @OrderBy("sequenceNum")
	public List<MappingTableCell> getCells() {
		return cells;
	}

	@Column(name="sequence_num")
	public int getSequenceNum() {
		return sequenceNum;
	}

	@Column(name="mapping_table_key")
	public int getTableKey() {
		return tableKey;
	}

	@Column(name="is_header")
	public int getIsHeader() {
		return isHeader;
	}

	public void setRowKey(int rowKey) {
		this.rowKey = rowKey;
	}
	public void setCells(List<MappingTableCell> cells) {
		this.cells = cells;
	}
	public void setSequenceNum(int sequenceNum) {
		this.sequenceNum = sequenceNum;
	}
	public void setTableKey(int tableKey) {
		this.tableKey = tableKey;
	}
	public void setIsHeader(int isHeader) {
		this.isHeader = isHeader;
	}
	@Override
	public String toString() {
		return "MappingTableRow [tableKey=" + tableKey + ", rowKey=" + rowKey + ", isHeader=" + isHeader
				+ ", sequenceNum=" + sequenceNum + "]";
	}
}
