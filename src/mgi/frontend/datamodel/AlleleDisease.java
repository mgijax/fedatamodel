package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="allele_disease")
public class AlleleDisease 
{
	private int alleleDiseaseKey;
	private String term;
	private String termID;
	private boolean isHeading;
	private boolean isNot;
	private boolean hasFootnote;
	private int sequenceNum;
	private int byAlpha;
	
	@Id
	@Column(name="allele_disease_key")
	public int getAlleleDiseaseKey() {
		return alleleDiseaseKey;
	}
	public void setAlleleDiseaseKey(int alleleDiseaseKey) {
		this.alleleDiseaseKey = alleleDiseaseKey;
	}
	
	@Column(name="term")
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	
	@Column(name="term_id")
	public String getTermID() {
		return termID;
	}
	public void setTermID(String termID) {
		this.termID = termID;
	}
	
	@Column(name="is_heading")
	public boolean getIsHeading() {
		return isHeading;
	}
	public void setIsHeading(boolean isHeading) {
		this.isHeading = isHeading;
	}
	
	@Column(name="is_not")
	public boolean getIsNot() {
		return isNot;
	}
	public void setIsNot(boolean isNot) {
		this.isNot = isNot;
	}
	
	@Column(name="has_footnote")
	public boolean getHasFootnote() {
		return hasFootnote;
	}
	public void setHasFootnote(boolean hasFootnote) {
		this.hasFootnote = hasFootnote;
	}
	
	@Column(name="sequence_num")
	public int getSequenceNum() {
		return sequenceNum;
	}
	public void setSequenceNum(int sequenceNum) {
		this.sequenceNum = sequenceNum;
	}
	
	@Column(name="by_alpha")
	public int getByAlpha() {
		return byAlpha;
	}
	public void setByAlpha(int byAlpha) {
		this.byAlpha = byAlpha;
	}
}
