package org.jax.mgi.fe.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Is: a summary of RI/RC data for a mapping experiment
 */
@Entity
@Table(name="mapping_rirc")
public class MappingRIRC {
	private int rircKey;
	private int experimentKey;
	private String designation;
	private String abbreviation1;
	private String abbreviation2;
	private String strain1;
	private String strain2;
	
    // ================= Getters and Setters ===================== //
	
	@Column(name="abbreviation_1")
	public String getAbbreviation1() {
		return abbreviation1;
	}

	@Column(name="abbreviation_2")
	public String getAbbreviation2() {
		return abbreviation2;
	}

	@Column(name="designation")
	public String getDesignation() {
		return designation;
	}

	@Column(name="experiment_key")
	public int getExperimentKey() {
		return experimentKey;
	}
	
	@Id
	@Column(name="rirc_key")
	public int getRircKey() {
		return rircKey;
	}
	
	@Column(name="strain_1")
	public String getStrain1() {
		return strain1;
	}
	
	@Column(name="strain_2")
	public String getStrain2() {
		return strain2;
	}

	public void setAbbreviation1(String abbreviation1) {
		this.abbreviation1 = abbreviation1;
	}
	public void setAbbreviation2(String abbreviation2) {
		this.abbreviation2 = abbreviation2;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public void setExperimentKey(int experimentKey) {
		this.experimentKey = experimentKey;
	}
	public void setRircKey(int rircKey) {
		this.rircKey = rircKey;
	}
	public void setStrain1(String strain1) {
		this.strain1 = strain1;
	}
	public void setStrain2(String strain2) {
		this.strain2 = strain2;
	}
	@Override
	public String toString() {
		return "MappingRIRC [rircKey=" + rircKey + ", experimentKey=" + experimentKey + ", designation=" + designation
				+ ", abbreviation1=" + abbreviation1 + ", abbreviation2=" + abbreviation2 + ", strain1=" + strain1
				+ ", strain2=" + strain2 + "]";
	}
}
