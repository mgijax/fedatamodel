package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="marker_searchable_nomenclature")
public class MarkerSearchableNomenclature 
{

	private int uniqueKey;
	private String term;
	private String termType;
	
	@Id
	@Column(name="unique_key")
	public int getUniqueKey() {
		return uniqueKey;
	}
	@Column(name="term")
	public String getTerm() {
		return term;
	}
	@Column(name="term_type")
	public String getTermType() {
		return termType;
	}
	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public void setTermType(String termType) {
		this.termType = termType;
	}
	

	@Override
	public String toString() {
		return "MarkerSearchableNomenclature ["+
				"uniqueKey="+uniqueKey+","+
				"term="+term+","+
				"termType="+termType+","+
				"]";
	}
	
}
