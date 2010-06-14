package mgi.frontend.datamodel;

import javax.persistence.*;

@Entity
@Table(name="markerSynonym")
public class MarkerSynonym {
	private int uniqueKey;
	private int markerKey;
	private String synonym;
	private String synonymType;
	private String jnumID;
	
	public MarkerSynonym() {}

	@Id
	public int getUniqueKey() {
		return uniqueKey;
	}

	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

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

	public String getSynonymType() {
		return synonymType;
	}

	public void setSynonymType(String synonymType) {
		this.synonymType = synonymType;
	}

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
