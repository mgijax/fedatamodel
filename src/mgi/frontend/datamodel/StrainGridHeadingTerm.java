package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * StrainGridHeadingTerm
 * @author jsb
 * This object represents a vocabulary term (at least its key and ID) that is
 * associated with a heading for a cell on a slimgrid on the strain
 * detail page.
 */
@Entity
@Table (name="strain_grid_heading_to_term")
public class StrainGridHeadingTerm{
    private int headingKey;
    private int termKey;
    private String termID;

    // ================= Getters and Setters ===================== //
	
    @Id
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
    	return "StrainGridHeadingTerm [headingKey=" + headingKey + ", "
    		+ "termID=" + termID + "]";
    }
}
