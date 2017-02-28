package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Is: a summary of IN SITU data for a mapping experiment
 */
@Entity
@Table(name="mapping_insitu")
public class MappingInSitu {
	private int insituKey;
	private int experimentKey;
	private String strain;
	private String band;
	private String cellOrigin;
	private String karyotypeMethod;
	private String robertsonians;
	private String metaphaseCount;
	private String grainsScored;
	private Integer grainsOnCorrectChromosome;
	private Integer grainsOnOtherChromosome;
	
    // ================= Getters and Setters ===================== //
	
	@Column(name="band")
	public String getBand() {
		return band;
	}
	
	@Column(name="cell_origin")
	public String getCellOrigin() {
		return cellOrigin;
	}
	
	@Column(name="experiment_key")
	public int getExperimentKey() {
		return experimentKey;
	}
	
	@Column(name="grains_on_correct_chromosome")
	public Integer getGrainsOnCorrectChromosome() {
		return grainsOnCorrectChromosome;
	}
	
	@Column(name="grains_on_other_chromosome")
	public Integer getGrainsOnOtherChromosome() {
		return grainsOnOtherChromosome;
	}
	
	@Column(name="grains_scored")
	public String getGrainsScored() {
		return grainsScored;
	}
	
	@Id
	@Column(name="insitu_key")
	public int getInsituKey() {
		return insituKey;
	}
	
	@Column(name="karyotype_method")
	public String getKaryotypeMethod() {
		return karyotypeMethod;
	}
	
	@Column(name="metaphase_count")
	public String getMetaphaseCount() {
		return metaphaseCount;
	}
	
	@Column(name="robertsonians")
	public String getRobertsonians() {
		return robertsonians;
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
	public void setExperimentKey(int experimentKey) {
		this.experimentKey = experimentKey;
	}
	public void setGrainsOnCorrectChromosome(Integer grainsOnCorrectChromosome) {
		this.grainsOnCorrectChromosome = grainsOnCorrectChromosome;
	}
	public void setGrainsOnOtherChromosome(Integer grainsOnOtherChromosome) {
		this.grainsOnOtherChromosome = grainsOnOtherChromosome;
	}
	public void setGrainsScored(String grainsScored) {
		this.grainsScored = grainsScored;
	}
	public void setInsituKey(int insituKey) {
		this.insituKey = insituKey;
	}
	public void setKaryotypeMethod(String karyotypeMethod) {
		this.karyotypeMethod = karyotypeMethod;
	}
	public void setMetaphaseCount(String metaphaseCount) {
		this.metaphaseCount = metaphaseCount;
	}
	public void setRobertsonians(String robertsonians) {
		this.robertsonians = robertsonians;
	}
	public void setStrain(String strain) {
		this.strain = strain;
	}
	@Override
	public String toString() {
		return "MappingInSitu [insituKey=" + insituKey + ", experimentKey=" + experimentKey + ", strain=" + strain
				+ ", band=" + band + ", cellOrigin=" + cellOrigin + ", karyotypeMethod=" + karyotypeMethod
				+ ", robertsonians=" + robertsonians + ", metaphaseCount=" + metaphaseCount + ", grainsScored="
				+ grainsScored + ", grainsOnCorrectChromosome=" + grainsOnCorrectChromosome
				+ ", grainsOnOtherChromosome=" + grainsOnOtherChromosome + "]";
	}
}
