package mgi.frontend.datamodel;

import javax.persistence.*;

@Entity
@Table(name="allele_synonym")
public class AlleleSynonym {
	private int uniqueKey;
	private int alleleKey;
	private String synonym;
	private String synonymType;
	private String jnumID;
	
	public AlleleSynonym() {}

	@Id
	@Column(name="unique_key")
	public int getUniqueKey() {
		return uniqueKey;
	}

	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	@Column(name="allele_key")
	public int getAlleleKey() {
		return alleleKey;
	}

	public void setAlleleKey(int alleleKey) {
		this.alleleKey = alleleKey;
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

	@Override
    public String toString() {
        return "AlleleSynonym [alleleKey=" + alleleKey + ", jnumID=" + jnumID
                + ", synonym=" + synonym + ", synonymType=" + synonymType
                + ", uniqueKey=" + uniqueKey + "]";
    }
}
