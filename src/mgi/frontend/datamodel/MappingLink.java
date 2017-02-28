package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Is: an extra link for a mapping experiment
 */
@Entity
@Table(name="mapping_link")
public class MappingLink {
	private int uniqueKey;
	private int experimentKey;
	private String type;
	private String url;
	
    // ================= Getters and Setters ===================== //
	
	@Column(name="experiment_key")
	public int getExperimentKey() {
		return experimentKey;
	}

	@Column(name="link_type")
	public String getType() {
		return type;
	}

	@Id
	@Column(name="unique_key")
	public int getUniqueKey() {
		return uniqueKey;
	}

	@Column(name="url")
	public String getUrl() {
		return url;
	}

	public void setExperimentKey(int experimentKey) {
		this.experimentKey = experimentKey;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "MappingLink [uniqueKey=" + uniqueKey + ", experimentKey=" + experimentKey + ", type=" + type + ", url="
				+ url + "]";
	}
}
