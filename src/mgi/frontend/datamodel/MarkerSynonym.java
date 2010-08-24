package mgi.frontend.datamodel;

import javax.persistence.*;

@Entity
@Table(name="marker_synonym")
public class MarkerSynonym {
	private int uniqueKey;
	private int markerKey;
	private String synonym;
	private String synonymType;
	private String jnumID;
	
	public MarkerSynonym() {}

	@Id
	@Column(name="unique_key")
	public int getUniqueKey() {
		return uniqueKey;
	}

	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	@Column(name="marker_key")
	public int getMarkerKey() {
		return markerKey;
	}

	public void setMarkerKey(int markerKey) {
		this.markerKey = markerKey;
	}

	public String getSynonym() {
		return synonym;
	}

	public void setSynonym(String synonym) {
		this.synonym = synonym;
	}

	@Column(name="synonym_type")
	public String getSynonymType() {
		return synonymType;
	}

	public void setSynonymType(String synonymType) {
		this.synonymType = synonymType;
	}

	@Column(name="jnum_id")
	public String getJnumID() {
		return jnumID;
	}

	public void setJnumID(String jnumID) {
		this.jnumID = jnumID;
	}

	@Override
	public String toString() {
		return "MarkerSynonym ["
				+ (jnumID != null ? "jnumID=" + jnumID + ", " : "")
				+ "markerKey="
				+ markerKey
				+ ", "
				+ (synonym != null ? "synonym=" + synonym + ", " : "")
				+ (synonymType != null ? "synonymType=" + synonymType + ", "
						: "") + "uniqueKey=" + uniqueKey + "]";
	}
}
