package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;

@Entity
@Table(name="strain_grid_popup_cell")
public class StrainGridPopupCell {
	private int cellKey;
	private StrainGridPopupRow row;
	private StrainGridPopupColumn column;
	private int color;
	private int value;
	private int sequenceNum;

	@Id
	@Column(name="cell_key")
	public int getCellKey() {
		return cellKey;
	}

	@Column(name="color_level")
	public int getColor() {
		return color;
	}

    @ManyToOne(targetEntity=StrainGridPopupColumn.class)
    @JoinColumn(name="column_key")
    @BatchSize(size=1)
	public StrainGridPopupColumn getColumn() {
		return column;
	}

    @Transient
	public Genotype getGenotype() {
		return this.getRow().getGenotype();
	}

	@ManyToOne(targetEntity=StrainGridPopupRow.class)
    @JoinColumn(name="row_key")
    @BatchSize(size=1)
	public StrainGridPopupRow getRow() {
		return row;
	}

	@Column(name="sequence_num")
	public int getSequenceNum() {
		return sequenceNum;
	}

	@Transient
	public String getTerm() {
		return this.getColumn().getTerm();
	}
	
	@Column(name="value")
	public int getValue() {
		return value;
	}

	public void setCellKey(int cellKey) {
		this.cellKey = cellKey;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public void setColumn(StrainGridPopupColumn column) {
		this.column = column;
	}

	public void setRow(StrainGridPopupRow row) {
		this.row = row;
	}

	public void setSequenceNum(int sequenceNum) {
		this.sequenceNum = sequenceNum;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "StrainGridPopupCell [cellKey=" + cellKey + ", row=" + row + ", column=" + column + ", color=" + color
				+ ", value=" + value + ", sequenceNum=" + sequenceNum + "]";
	}
}
