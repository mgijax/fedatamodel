package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Is: a single gene model ID for a strain-specific marker (aka- strain marker)
 */
@Entity
@Table(name="strain_snp_cell")
public class StrainSnpCell {
	private int cellKey;
	private int rowKey;
	private String chromosome;
	private int allCount;
	private int sameCount;
	private int differentCount;
	private int sequenceNum;
	
	@Transient
	public String getCountComma(int count) {
		return String.format("%,d", count);
	}

	@Transient
	public String getAllCountComma() {
		return this.getCountComma(this.allCount);
	}
	
	@Transient
	public String getSameCountComma() {
		return this.getCountComma(this.sameCount);
	}
	
	@Transient
	public String getDiffCountComma() {
		return this.getCountComma(this.differentCount);
	}
	
	@Transient
	public int getColorBin() {
		if (allCount == 0) return 0;
		if (allCount < 10) return 1;
		if (allCount < 100) return 2;
		if (allCount < 350) return 3;
		if (allCount < 500) return 4;
		if (allCount < 1000) return 5;
		return 6;
	}
	
	@Column(name="all_count")
	public int getAllCount() {
		return allCount;
	}
	
	@Id
	@Column(name="cell_key")
	public int getCellKey() {
		return cellKey;
	}
	
	@Column(name="chromosome")
	public String getChromosome() {
		return chromosome;
	}
	
	@Column(name="different_count")
	public int getDifferentCount() {
		return differentCount;
	}
	
	@Column(name="row_key")
	public int getRowKey() {
		return rowKey;
	}
	
	@Column(name="same_count")
	public int getSameCount() {
		return sameCount;
	}
	
	@Column(name="sequence_num")
	public int getSequenceNum() {
		return sequenceNum;
	}
	
	public void setAllCount(int allCount) {
		this.allCount = allCount;
	}
	
	public void setCellKey(int cellKey) {
		this.cellKey = cellKey;
	}
	
	public void setChromosome(String chromosome) {
		this.chromosome = chromosome;
	}
	
	public void setDifferentCount(int differentCount) {
		this.differentCount = differentCount;
	}
	
	public void setRowKey(int rowKey) {
		this.rowKey = rowKey;
	}
	
	public void setSameCount(int sameCount) {
		this.sameCount = sameCount;
	}
	
	public void setSequenceNum(int sequenceNum) {
		this.sequenceNum = sequenceNum;
	}
	
	@Override
	public String toString() {
		return "StrainSnpCell [cellKey=" + cellKey + ", rowKey=" + rowKey + ", chromosome=" + chromosome + ", allCount="
				+ allCount + ", sameCount=" + sameCount + ", differentCount=" + differentCount + ", sequenceNum="
				+ sequenceNum + "]";
	}
}
