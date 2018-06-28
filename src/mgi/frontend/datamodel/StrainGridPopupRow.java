package mgi.frontend.datamodel;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;

@Entity
@Table(name="strain_grid_popup_row")
public class StrainGridPopupRow {
	private int rowKey;
	private int gridCellKey;
	private Genotype genotype;
	private int sequenceNum;
	private List<StrainGridPopupCell> cells;

    @OneToMany(targetEntity=StrainGridPopupCell.class)
    @BatchSize(size=30)
    @JoinColumn(name="row_key")
    @OrderBy("sequenceNum")
	public List<StrainGridPopupCell> getCells() {
		return cells;
	}

    @ManyToOne(targetEntity=Genotype.class)
    @JoinColumn(name="genotype_key")
    @BatchSize(size=1)
	public Genotype getGenotype() {
		return genotype;
	}

	@Column(name="grid_cell_key")
	public int getGridCellKey() {
		return gridCellKey;
	}

	@Id
	@Column(name="row_key")
	public int getRowKey() {
		return rowKey;
	}

	@Column(name="sequence_num")
	public int getSequenceNum() {
		return sequenceNum;
	}

	public void setCells(List<StrainGridPopupCell> cells) {
		this.cells = cells;
	}

	public void setGenotype(Genotype genotype) {
		this.genotype = genotype;
	}

	public void setGridCellKey(int gridCellKey) {
		this.gridCellKey = gridCellKey;
	}

	public void setRowKey(int rowKey) {
		this.rowKey = rowKey;
	}

	public void setSequenceNum(int sequenceNum) {
		this.sequenceNum = sequenceNum;
	}

	@Override
	public String toString() {
		return "StrainGridPopupRow [rowKey=" + rowKey + ", gridCellKey=" + gridCellKey + ", genotype=" + genotype
				+ ", sequenceNum=" + sequenceNum + ", cells=" + cells + "]";
	}
}
