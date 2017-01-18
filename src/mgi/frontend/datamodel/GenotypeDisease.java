package mgi.frontend.datamodel;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

/*
 * Represents a relationship between genotypes and diseases
 */
@Entity
@Table(name="genotype_disease")
public class GenotypeDisease {
	private int genotypeDiseaseKey;
	private String term;
	private String termID;
	private VocabTerm vocabTerm;
	private boolean isHeading;
	private boolean isNot;
	private boolean hasFootnote;
	private int sequenceNum;
	private List<GenotypeDiseaseReference> references;
	// TODO add list of secondary ids

	@Id
	@Column(name="genotype_disease_key")
	public int getGenotypeDiseaseKey() {
		return genotypeDiseaseKey;
	}

	public void setGenotypeDiseaseKey(int key) {
		this.genotypeDiseaseKey=key;
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
		return this.isHeading;
	}
	
	public void setIsHeading(boolean isHeading) {
		this.isHeading=isHeading;
	}

	@Column(name="is_not")
	public boolean getIsNot() {
		return this.isNot;
	}
	
	public void setIsNot(boolean isNot) {
		this.isNot=isNot;
	}

	@Column(name="has_footnote")
	public boolean getHasFootnote() {
		return hasFootnote;
	}
	public void setHasFootnote(boolean hasFootnote) {
		this.hasFootnote=hasFootnote;
	}

	@Column(name="sequence_num")
	public int getSequenceNum() {
		return sequenceNum;
	}

	public void setSequenceNum(int sequenceNum) {
		this.sequenceNum = sequenceNum;
	}

	@OneToMany(targetEntity=GenotypeDiseaseReference.class,fetch=FetchType.EAGER)
	@JoinColumn(name="genotype_disease_key")
	@OrderBy("sequenceNum")
	public List<GenotypeDiseaseReference> getReferences() {
		return references;
	}

	public void setReferences(List<GenotypeDiseaseReference> references) {
		this.references = references;
	}

	@OneToOne (targetEntity=VocabTerm.class)
	@JoinColumn (name="term_key")
	public VocabTerm getVocabTerm() {
		return this.vocabTerm;
	}

	public void setVocabTerm(VocabTerm vocabTerm) {
		this.vocabTerm = vocabTerm;
	}
	
	@Transient
	public List<String> getOmimIds() {
		ArrayList<String> ret = new ArrayList<String>();
		for(VocabTermID termId: getVocabTerm().getSecondaryIds()) {
			if(termId.getAccID().startsWith("OMIM:")) {
				ret.add(termId.getAccID().replace("OMIM:", ""));
			}
		}
		return ret;
	}

	//	@OneToMany(targetEntity=GenotypeDiseaseFootnote.class)
	//	@JoinColumn(name="genotype_disease_key")
	//	@OrderBy("number")
	//	public List<GenotypeDiseaseFootnote> getFootnotes()
	//	{
	//		return this.footnotes;
	//	}
	//	public void setFootnotes(List<GenotypeDiseaseFootnote> footnotes)
	//	{
	//		this.footnotes=footnotes;
	//	}

}
