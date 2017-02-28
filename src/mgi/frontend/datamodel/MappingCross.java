package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Is: a summary of CROSS data for a mapping experiment
 */
@Entity
@Table(name="mapping_cross")
public class MappingCross {
	private int crossKey;
	private int experimentKey;
	private String crossType;
	private String femaleParent;
	private String maleParent;
	private String femaleStrain;
	private String maleStrain;
	private String panelName;
	private String panelFilename;
	private String homozygousAllele;
	private String homozygousStrain;
	private String heterozygousAllele;
	private String heterozygousStrain;
	
    // ================= Getters and Setters ===================== //
	
	@Id
	@Column(name="cross_key")
	public int getCrossKey() {
		return crossKey;
	}

	@Column(name="cross_type")
	public String getCrossType() {
		return crossType;
	}

	@Column(name="experiment_key")
	public int getExperimentKey() {
		return experimentKey;
	}

	@Column(name="female_parent")
	public String getFemaleParent() {
		return femaleParent;
	}

	@Column(name="female_strain")
	public String getFemaleStrain() {
		return femaleStrain;
	}

	@Column(name="heterozygous_allele")
	public String getHeterozygousAllele() {
		return heterozygousAllele;
	}

	@Column(name="heterozygous_strain")
	public String getHeterozygousStrain() {
		return heterozygousStrain;
	}

	@Column(name="homozygous_allele")
	public String getHomozygousAllele() {
		return homozygousAllele;
	}

	@Column(name="homozygous_strain")
	public String getHomozygousStrain() {
		return homozygousStrain;
	}

	@Column(name="male_parent")
	public String getMaleParent() {
		return maleParent;
	}

	@Column(name="male_strain")
	public String getMaleStrain() {
		return maleStrain;
	}

	@Column(name="panel_filename")
	public String getPanelFilename() {
		return panelFilename;
	}

	@Column(name="panel_name")
	public String getPanelName() {
		return panelName;
	}

	public void setCrossKey(int crossKey) {
		this.crossKey = crossKey;
	}
	public void setCrossType(String crossType) {
		this.crossType = crossType;
	}
	public void setExperimentKey(int experimentKey) {
		this.experimentKey = experimentKey;
	}
	public void setFemaleParent(String femaleParent) {
		this.femaleParent = femaleParent;
	}
	public void setFemaleStrain(String femaleStrain) {
		this.femaleStrain = femaleStrain;
	}
	public void setHeterozygousAllele(String heterozygousAllele) {
		this.heterozygousAllele = heterozygousAllele;
	}
	public void setHeterozygousStrain(String heterozygousStrain) {
		this.heterozygousStrain = heterozygousStrain;
	}
	public void setHomozygousAllele(String homozygousAllele) {
		this.homozygousAllele = homozygousAllele;
	}
	public void setHomozygousStrain(String homozygousStrain) {
		this.homozygousStrain = homozygousStrain;
	}
	public void setMaleParent(String maleParent) {
		this.maleParent = maleParent;
	}
	public void setMaleStrain(String maleStrain) {
		this.maleStrain = maleStrain;
	}
	public void setPanelFilename(String panelFilename) {
		this.panelFilename = panelFilename;
	}
	public void setPanelName(String panelName) {
		this.panelName = panelName;
	}
	@Override
	public String toString() {
		return "MappingCross [crossKey=" + crossKey + ", experimentKey=" + experimentKey + ", crossType=" + crossType
				+ ", femaleParent=" + femaleParent + ", maleParent=" + maleParent + ", femaleStrain=" + femaleStrain
				+ ", maleStrain=" + maleStrain + ", panelName=" + panelName + ", panelFilename=" + panelFilename
				+ ", homozygousAllele=" + homozygousAllele + ", homozygousStrain=" + homozygousStrain
				+ ", heterozygousAllele=" + heterozygousAllele + ", heterozygousStrain=" + heterozygousStrain + "]";
	}
}
