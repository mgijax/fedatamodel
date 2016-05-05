package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * MarkerGridHeadingTerm
 * @author jsb
 * This object represents a vocabulary term (at least its key and ID) that is
 * associated with a heading for a cell on a slimgrid on the marker
 * detail page.
 */
@Entity
@Table (name="marker_grid_heading_to_term")
public class MarkerGridHeadingTerm{
    private int uniqueKey;
    private int headingKey;
    private int termKey;
    private String termID;

    // ================= Getters and Setters ===================== //
	
    @Id
    @Column(name="unique_key")
    public int getUniqueKey() {
	return this.uniqueKey;
    }

    public void setUniqueKey(int uniqueKey) {
	this.uniqueKey = uniqueKey;
    }

    @Column(name="heading_key")
    public int getHeadingKey() {
	return this.headingKey;
    }

    public void setHeadingKey(int headingKey) {
	this.headingKey = headingKey;
    }

    @Column(name="term_key")
    public int getTermKey() {
	return this.termKey;
    }

    public void setTermKey(int termKey) {
	this.termKey = termKey;
    }

    @Column(name="term_id")
    public String getTermID() {
	return termID;
    }

    public void setTermID(String termID) {
	this.termID = termID;
    }

    @Override
    public String toString() {
	return "MarkerGridHeadingTerm [headingKey=" + headingKey + ", "
	    + "termID=" + termID + ", "
	    + "uniqueKey=" + uniqueKey + "]";
    }
}
