package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Is a bucket of extra info for an EMAPA/EMAPS vocabulary term.
 *
 */
@Entity
@Table(name="term_emap")
public class VocabTermEmapInfo {
	private int termKey;			// identifies the term
	private VocabTerm defaultParent;	// parent to open by default

	// only for EMAPA terms:
	private Integer startStage;		// when structure appears
	private Integer endStage;		// when structure goes away

	// only for EMAPS terms:
	private Integer stage;			// Theiler stage
	private VocabTerm emapaTerm;		// EMAPA term from which...
						// ...this one was generated
	
    // ================= Getters and Setters ===================== //
	
	@Id
	@Column(name="term_key")
	public int getTermKey() {
		return termKey;
	}

	public void setTermKey(int termKey) {
		this.termKey = termKey;
	}

	@Column(name="start_stage")
	public Integer getStartStage() {
		return startStage;
	}

	public void setStartStage(Integer startStage) {
		this.startStage = startStage;
	}

	@Column(name="end_stage")
	public Integer getEndStage() {
		return endStage;
	}

	public void setEndStage(Integer endStage) {
		this.endStage = endStage;
	}

	@Column(name="stage")
	public Integer getStage() {
		return stage;
	}

	public void setStage(Integer stage) {
		this.stage = stage;
	}

	@ManyToOne (targetEntity=VocabTerm.class, fetch=FetchType.LAZY)
	@JoinColumn (name="default_parent_key")
	public VocabTerm getDefaultParent() {
		return defaultParent;
	}

	public void setDefaultParent (VocabTerm defaultParent) {
		this.defaultParent = defaultParent;
	}

	@ManyToOne (targetEntity=VocabTerm.class, fetch=FetchType.LAZY)
	@JoinColumn (name="emapa_term_key")
	public VocabTerm getEmapaTerm() {
		return emapaTerm;
	}

	public void setEmapaTerm (VocabTerm emapaTerm) {
		this.emapaTerm = emapaTerm;
	}

	@Override
	public String toString() {
		return "VocabTermEmapInfo [termKey=" + termKey + "]";
	}
}
