package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Is a synonym for a vocabulary term.
 *
 */
@Entity
@Table(name="term_synonym")
public class VocabTermSynonym {
	private int termKey;
	private String synonym;
	private String synonymType;
	private int uniqueKey;
	
    // ================= Getters and Setters ===================== //
	
	@Column(name="term_key")
	public int getTermKey() {
		return termKey;
	}

	public String getSynonym() {
		return synonym;
	}

	/**
	 * Returns the synonym type (name, symbol etc)
	 */
	@Column(name="synonym_type")
	public String getSynonymType() {
		return synonymType;
	}

	/**
	 * Internal database key for front end database. (needed for Hibernate)
	 */
	@Id
	@Column(name="unique_key")
	public int getUniqueKey() {
		return uniqueKey;
	}

	public void setTermKey(int termKey) {
		this.termKey = termKey;
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
        return "VocabTermSynonym [termKey=" + termKey 
                + ", synonym=" + synonym + ", synonymType=" + synonymType
                + ", uniqueKey=" + uniqueKey + "]";
    }
}
