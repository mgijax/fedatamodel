package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Is: a summary of FISH data for a mapping experiment
 */
@Entity
@Table(name="mapping_fish")
public class MappingFish {
	private int fishKey;
	private int experimentKey;
	private String strain;
	private String band;
	private String cellOrigin;
	private String karyotypeMethod;
	private String robertsonians;
	private String metaphaseCount;
	private String singleSignalCount;
	private String doubleSignalCount;
	private String label;
	
    // ================= Getters and Setters ===================== //
	
	@Column(name="band")
	public String getBand() {
		return band;
	}
	
	@Column(name="cell_origin")
	public String getCellOrigin() {
		return cellOrigin;
	}
	
	@Column(name="double_signal_count")
	public String getDoubleSignalCount() {
		return doubleSignalCount;
	}
	
	@Column(name="experiment_key")
	public int getExperimentKey() {
		return experimentKey;
	}
	
	@Id
	@Column(name="fish_key")
	public int getFishKey() {
		return fishKey;
	}
	
	@Column(name="karyotype_method")
	public String getKaryotypeMethod() {
		return karyotypeMethod;
	}
	
	@Column(name="label")
	public String getLabel() {
		return label;
	}
	
	@Column(name="metaphase_count")
	public String getMetaphaseCount() {
		return metaphaseCount;
	}

	@Column(name="robertsonians")
	public String getRobertsonians() {
		return robertsonians;
	}

	@Column(name="single_signal_count")
	public String getSingleSignalCount() {
		return singleSignalCount;
	}

	@Column(name="strain")
	public String getStrain() {
		return strain;
	}

	public void setBand(String band) {
		this.band = band;
	}
	public void setCellOrigin(String cellOrigin) {
		this.cellOrigin = cellOrigin;
	}
	public void setDoubleSignalCount(String doubleSignalCount) {
		this.doubleSignalCount = doubleSignalCount;
	}
	public void setExperimentKey(int experimentKey) {
		this.experimentKey = experimentKey;
	}
	public void setFishKey(int fishKey) {
		this.fishKey = fishKey;
	}
	public void setKaryotypeMethod(String karyotypeMethod) {
		this.karyotypeMethod = karyotypeMethod;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public void setMetaphaseCount(String metaphaseCount) {
		this.metaphaseCount = metaphaseCount;
	}
	public void setRobertsonians(String robertsonians) {
		this.robertsonians = robertsonians;
	}
	public void setSingleSignalCount(String singleSignalCount) {
		this.singleSignalCount = singleSignalCount;
	}
	public void setStrain(String strain) {
		this.strain = strain;
	}

	@Override
	public String toString() {
		return "MappingFish [fishKey=" + fishKey + ", experimentKey=" + experimentKey + ", strain=" + strain + ", band="
				+ band + ", cellOrigin=" + cellOrigin + ", karyotypeMethod=" + karyotypeMethod + ", robertsonians="
				+ robertsonians + ", metaphaseCount=" + metaphaseCount + ", singleSignalCount=" + singleSignalCount
				+ ", doubleSignalCount=" + doubleSignalCount + ", label=" + label + "]";
	}
}
