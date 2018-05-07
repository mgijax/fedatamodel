package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * StrainMpdData - represents one record in MPD corresponding to this strain.
 * (So far, all strains have only one, but there may be more at some point.)
 */
@Entity
@Table(name="strain_mpd_data")
public class StrainMpdData {
	private int uniqueKey;
	private int strainKey;
	private String mpdID;
	private String repository;
	private String sourceURL;
	private String matchType;
	private String mpdStrain;
	
    // ================= Getters and Setters ===================== //

	@Column(name="mpd_id")
	public String getMpdID() {
		return mpdID;
	}

	@Column(name="mpd_strain")
	public String getMpdStrain() {
		return mpdStrain;
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

	public void setMpdID(String mpdID) {
		this.mpdID = mpdID;
	}

	public void setMpdStrain(String mpdStrain) {
		this.mpdStrain = mpdStrain;
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
