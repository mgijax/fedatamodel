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
 * Is: one cell in one row in a table of data for a mapping experiment
 */
@Entity
@Table(name="mapping_table_cell")
public class MappingTableCell {
	private int cellKey;
	private int rowKey;
	private String markerID;
	private String label;
	private int sequenceNum;
	
    // ================= Getters and Setters ===================== //

	@Id
	@Column(name="cell_key")
	public int getCellKey() {
		return cellKey;
	}

	@Column(name="label")
	public String getLabel() {
		return label;
	}

	@Column(name="marker_id")
	public String getMarkerID() {
		return markerID;
	}

	@Column(name="row_key")
	public int getRowKey() {
		return rowKey;
	}

	@Column(name="sequence_num")
	public int getSequenceNum() {
		return sequenceNum;
	}

	public void setCellKey(int cellKey) {
		this.cellKey = cellKey;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public void setMarkerID(String markerID) {
		this.markerID = markerID;
	}
	public void setRowKey(int rowKey) {
		this.rowKey = rowKey;
	}
	public void setSequenceNum(int sequenceNum) {
		this.sequenceNum = sequenceNum;
	}
	@Override
	public String toString() {
		return "MappingTableCell [cellKey=" + cellKey + ", rowKey=" + rowKey + ", markerID=" + markerID + ", label="
				+ label + ", sequenceNum=" + sequenceNum + "]";
	}
}
