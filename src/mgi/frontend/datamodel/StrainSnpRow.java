package mgi.frontend.datamodel;

import java.text.DecimalFormat;
import java.text.NumberFormat;
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

import org.hibernate.annotations.BatchSize;

/**
 * Is: a row of SNP data for a strain detail page.  The row represents the data for a single
 * 	comparison strain and contains a cell for each chromosome.
 */
@Entity
@Table(name="strain_snp_row")
public class StrainSnpRow {
	private int rowKey;
	private int strainKey;
	private int comparisonStrainKey;
	private String comparisonStrainName;
	private String comparisonStrainID;
	private int sequenceNum;
	List<StrainSnpCell> cells;

	// get the total number of SNPs for this comparison strain across all chromosomes
	@Transient
	public int getSnpTotal() {
		int total = 0;
		for (StrainSnpCell cell : this.getCells()) {
			total = total + cell.getAllCount();
		}
		return total;
	}
	
	@OneToMany (targetEntity=StrainSnpCell.class)
	@JoinColumn(name="row_key")
	@BatchSize(size=25)
	@OrderBy("sequence_num")
	public List<StrainSnpCell> getCells() {
		return cells;
	}
	
	@Column(name="comparison_strain_id")
	public String getComparisonStrainID() {
		return comparisonStrainID;
	}
	
	@Column(name="comparison_strain_key")
	public int getComparisonStrainKey() {
		return comparisonStrainKey;
	}
	
	@Column(name="comparison_strain_name")
	public String getComparisonStrainName() {
		return comparisonStrainName;
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
	
	@Column(name="strain_key")
	public int getStrainKey() {
		return strainKey;
	}

	public void setCells(List<StrainSnpCell> cells) {
		this.cells = cells;
	}

	public void setComparisonStrainID(String comparisonStrainID) {
		this.comparisonStrainID = comparisonStrainID;
	}

	public void setComparisonStrainKey(int comparisonStrainKey) {
		this.comparisonStrainKey = comparisonStrainKey;
	}

	public void setComparisonStrainName(String comparisonStrainName) {
		this.comparisonStrainName = comparisonStrainName;
	}

	public void setRowKey(int rowKey) {
		this.rowKey = rowKey;
	}

	public void setSequenceNum(int sequenceNum) {
		this.sequenceNum = sequenceNum;
	}

	public void setStrainKey(int strainKey) {
		this.strainKey = strainKey;
	}

	@Override
	public String toString() {
		return "StrainSnpRow [rowKey=" + rowKey + ", strainKey=" + strainKey + ", comparisonStrainKey="
				+ comparisonStrainKey + ", comparisonStrainName=" + comparisonStrainName + ", comparisonStrainID="
				+ comparisonStrainID + ", sequenceNum=" + sequenceNum + ", cells=" + cells + "]";
	}
}
