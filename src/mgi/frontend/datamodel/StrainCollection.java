package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * StrainCollection
 */
@Entity
@Table(name="strain_collection")
public class StrainCollection {
    
	private int uniqueKey;
	private int strainKey;
	private String collection;

    // ================= Getters and Setters ===================== //
	

	@Column(name="strain_key")
	public int getStrainKey() {
		return strainKey;
	}

	@Column(name="collection")
	public String getCollection() {
		return collection;
	}

	@Id
	@Column(name="unique_key")
	public int getUniqueKey() {
		return uniqueKey;
	}

	public void setStrainKey(int strainKey) {
		this.strainKey = strainKey;
	}

	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	public void setCollection(String collection) {
		this.collection = collection;
	}
    
	@Override
	public String toString() {
		return "StrainCollection ["
				+ "strainKey="
				+ strainKey
				+ ", "
				+ (collection != null ? "collection=" + collection + ", " : "");
	}


}
