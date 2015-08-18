package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Base object for a vocabulary term.
 * @author jsb
 * This is very rudimentary, as it doesn't yet link to any of the other
 * term tables.  This is all we need at the moment, however, so this is
 * as far as we built.
 */

@Entity
@Table(name="term")
public class Term {

    private int termKey;
    private int isLeaf;
    private int isRoot;
    private int sequenceNum;
    private String definition;
    private String displayVocabName;
    private String primaryID;
    private String term;
    private String vocabName;
    private String abbreviation;

    // ================= Getters and Setters ===================== //

    @Id
    @Column(name="term_key")
    public int getTermKey() {
	return termKey;
    }

    public void setTermKey(int termKey) {
	this.termKey = termKey;
    }

    @Column(name="is_leaf")
    public int getIsLeaf() {
	return isLeaf;
    }

    public void setIsLeaf(int isLeaf) {
	this.isLeaf = isLeaf;
    }

    @Column(name="is_root")
    public int getIsRoot() {
	return isRoot;
    }

    public void setIsRoot(int isRoot) {
	this.isRoot = isRoot;
    }

    @Column(name="sequence_num")
    public int getSequenceNum() {
	return sequenceNum;
    }

    public void setSequenceNum(int sequenceNum) {
	this.sequenceNum = sequenceNum;
    }

    @Column(name="definition")
    public String getDefinition() {
	return definition;
    }

    public void setDefinition(String definition) {
	this.definition = definition;
    }

    @Column(name="display_vocab_name")
    public String getDisplayVocabName() {
	return displayVocabName;
    }

    public void setDisplayVocabName(String displayVocabName) {
	this.displayVocabName = displayVocabName;
    }

    @Column(name="primary_id")
    public String getPrimaryID() {
	return primaryID;
    }

    public void setPrimaryID(String primaryID) {
	this.primaryID = primaryID;
    }

    @Column(name="term")
    public String getTerm() {
	return term;
    }

    @Column(name="abbreviation")
    public String getAbbreviation() {
	return abbreviation;
    }

    public void setTerm(String term) {
	this.term = term;
    }

    public void setAbbreviation(String abbreviation) {
	this.abbreviation = abbreviation;
    }

    @Column(name="vocab_name")
    public String getVocabName() {
	return vocabName;
    }

    public void setVocabName(String vocabName) {
	this.vocabName = vocabName;
    }

    public String toString() {
	return "Term [" + vocabName + ", " + primaryID + ", " + term + "]";
    }
}
