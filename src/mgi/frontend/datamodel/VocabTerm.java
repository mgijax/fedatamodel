package mgi.frontend.datamodel;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterJoinTable;

@Entity
@FilterDef(name="termDiseaseModelExcludeNots")
@Table(name="term")
public class VocabTerm implements Serializable{
	
	// something about joining on non-primary keys is making it require serializable interface...
	private static final long serialVersionUID = 1L;
	
	private int termKey;
	private String term;
	private String vocabName;
	private String primaryId;
	private int isObsolete;
	
	/* Vocab specific optional associations */
	// For vocabName=OMIM
	private List<DiseaseModel> diseaseModels;
	
	/* Getters */
	@Id
	@Column(name="term_key")
	public int getTermKey() {
		return termKey;
	}
	
	@Column(name="term")
	public String getTerm() {
		return term;
	}
	
	@Column(name="vocab_name")
	public String getVocabName() {
		return vocabName;
	}
	
	@Column(name="primary_id")
	public String getPrimaryId() {
		return primaryId;
	}
	
	@Column(name="is_obsolete")
	public int getIsObsolete() {
		return isObsolete;
	}
	
	@OneToMany (targetEntity=DiseaseModel.class)
    @JoinColumn(name="disease_id", referencedColumnName="primary_id")
    @BatchSize(size=500)
	@Filter(
			name = "termDiseaseModelExcludeNots",
			condition = "is_not_model = 0"
	)
    public List<DiseaseModel> getDiseaseModels() {
	return diseaseModels;
    }
	
	/* Setters */
	public void setTermKey(int termKey) {
		this.termKey = termKey;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public void setVocabName(String vocabName) {
		this.vocabName = vocabName;
	}
	public void setPrimaryId(String primaryId) {
		this.primaryId = primaryId;
	}
	public void setIsObsolete(int isObsolete) {
		this.isObsolete = isObsolete;
	}
	
	public void setDiseaseModels(List<DiseaseModel> diseaseModels) {
		this.diseaseModels = diseaseModels;
	}
	
	/* Transient methods */
	@Transient
	public int getDiseaseModelCount()
	{
		return getDiseaseModels().size();
	}
	
}
