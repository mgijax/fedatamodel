package mgi.frontend.datamodel;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;
import javax.persistence.FetchType;
import org.hibernate.annotations.BatchSize;

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
    private List<MarkerGridHeading> gridHeadings;

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

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="marker_grid_heading_to_term",
	joinColumns=@JoinColumn(name="term_key"),
	inverseJoinColumns=@JoinColumn(name="heading_key")
	)
    @BatchSize(size=100)
    @OrderBy("heading_abbreviation")
    public List<MarkerGridHeading> getGridHeadings() {
	return gridHeadings;
    }

    public void setGridHeadings(List<MarkerGridHeading> gridHeadings) {
	this.gridHeadings = gridHeadings;
    }

    // get the first slimgrid heading associated with this term (normally only
    // one anyway, but it's theoretically possible to have multiples in the
    // database schema.
    @Transient
    public String getSlimGridHeading() {
	List<MarkerGridHeading> headings = this.getGridHeadings();
	if ((headings != null) && (headings.size() > 0)) {
	    return headings.get(0).getHeadingAbbreviation();
	}
	return null;
    }

    public String toString() {
	return "Term [" + vocabName + ", " + primaryID + ", " + term + "]";
    }
}
