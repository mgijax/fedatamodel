package mgi.frontend.datamodel;

import javax.persistence.*;

/**
 * MarkerSynonym
 * @author mhall
 * An object representing a MarkerSynonym.
 */
@Entity
@Table(name="marker_synonym")
public class MarkerSynonym {
    
	private String jnumID;
	private int markerKey;
	private String synonym;
	private String synonymType;
	private int uniqueKey;

    // ================= Getters and Setters ===================== //
	
	@Column(name="jnum_id")
	public String getJnumID() {
		return jnumID;
	}

	@Column(name="marker_key")
	public int getMarkerKey() {
		return markerKey;
	}

	public String getSynonym() {
		return synonym;
	}

	@Column(name="synonym_type")
	public String getSynonymType() {
		return synonymType;
	}

	@Id
	@Column(name="unique_key")
	public int getUniqueKey() {
		return uniqueKey;
	}

	public void setJnumID(String jnumID) {
		this.jnumID = jnumID;
	}

	public void setMarkerKey(int markerKey) {
		this.markerKey = markerKey;
	}

	public void setSynonym(String synonym) {
		this.synonym = synonym;
	}

	public void setSynonymType(String synonymType) {
		this.synonymType = synonymType;
	}

	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
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
