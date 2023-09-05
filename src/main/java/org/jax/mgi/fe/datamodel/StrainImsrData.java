package org.jax.mgi.fe.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * StrainImsrData - represents one record in IMSR corresponding to this strain.
 * (Most often a strain will have one of these, but some have up to four.)
 */
@Entity
@Table(name="strain_imsr_data")
public class StrainImsrData {
	private int uniqueKey;
	private int strainKey;
	private String imsrID;
	private String repository;
	private String sourceURL;
	private String matchType;
	private String imsrStrain;
	
    // ================= Getters and Setters ===================== //

	@Column(name="imsr_id")
	public String getImsrID() {
		return imsrID;
	}

	@Column(name="imsr_strain")
	public String getImsrStrain() {
		return imsrStrain;
	}

	@Column(name="match_type")
	public String getMatchType() {
		return matchType;
	}

	@Column(name="repository")
	public String getRepository() {
		return repository;
	}

	@Column(name="source_url")
	public String getSourceURL() {
		return sourceURL;
	}

	@Column(name="strain_key")
	public int getStrainKey() {
		return strainKey;
	}

	@Id
	@Column(name="unique_key")
	public int getUniqueKey() {
		return uniqueKey;
	}

	public void setImsrID(String imsrID) {
		this.imsrID = imsrID;
	}

	public void setImsrStrain(String imsrStrain) {
		this.imsrStrain = imsrStrain;
	}

	public void setMatchType(String matchType) {
		this.matchType = matchType;
	}

	public void setRepository(String repository) {
		this.repository = repository;
	}

	public void setSourceURL(String sourceURL) {
		this.sourceURL = sourceURL;
	}

	public void setStrainKey(int strainKey) {
		this.strainKey = strainKey;
	}

	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}
}
