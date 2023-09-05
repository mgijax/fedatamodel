package org.jax.mgi.fe.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Is: a summary of HYBRID data for a mapping experiment
 */
@Entity
@Table(name="mapping_hybrid")
public class MappingHybrid {
	private int hybridKey;
	private int experimentKey;
	private String band;
	private String concordanceType;
	
    // ================= Getters and Setters ===================== //
	
	@Column(name="band")
	public String getBand() {
		return band;
	}
	
	@Column(name="concordance_type")
	public String getConcordanceType() {
		return concordanceType;
	}
	
	@Column(name="experiment_key")
	public int getExperimentKey() {
		return experimentKey;
	}

	@Id
	@Column(name="hybrid_key")
	public int getHybridKey() {
		return hybridKey;
	}

	public void setBand(String band) {
		this.band = band;
	}

	public void setConcordanceType(String concordanceType) {
		this.concordanceType = concordanceType;
	}

	public void setExperimentKey(int experimentKey) {
		this.experimentKey = experimentKey;
	}

	public void setHybridKey(int hybridKey) {
		this.hybridKey = hybridKey;
	}

	@Override
	public String toString() {
		return "MappingHybrid [hybridKey=" + hybridKey + ", experimentKey=" + experimentKey + ", band=" + band
				+ ", concordanceType=" + concordanceType + "]";
	}
}
