package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Return an allele synonym object
 * @author mhall
 *
 */
@Entity
@Table(name="allele_synonym")
public class AlleleSynonym {
	private int alleleKey;
	private String jnumID;
	private String synonym;
	private String synonymType;
	private int uniqueKey;
	
    // ================= Getters and Setters ===================== //
	
	@Column(name="allele_key")
	public int getAlleleKey() {
		return alleleKey;
	}

	public String getSynonym() {
		return synonym;
	}

	/**
	 * Returns the synonym type (name, symbol etc)
	 * @return
	 */
	@Column(name="synonym_type")
	public String getSynonymType() {
		return synonymType;
	}

	/**
	 * Internal database key for front end database.  No
	 * use in the front end.
	 * @return
	 */
	@Id
	@Column(name="unique_key")
	public int getUniqueKey() {
		return uniqueKey;
	}

	public void setAlleleKey(int alleleKey) {
		this.alleleKey = alleleKey;
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
        return "AlleleSynonym [alleleKey=" + alleleKey + ", jnumID=" + jnumID
                + ", synonym=" + synonym + ", synonymType=" + synonymType
                + ", uniqueKey=" + uniqueKey + "]";
    }
}
