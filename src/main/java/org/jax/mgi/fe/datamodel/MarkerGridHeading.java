package org.jax.mgi.fe.datamodel;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;

/**
 * MarkerGridHeading
 * @author jsb
 * This object represents a heading for a cell on a slimgrid on the marker
 * detail page.
 */
@Entity
@Table (name="marker_grid_heading")
public class MarkerGridHeading{
    private int headingKey;
    private String heading;
    private String headingAbbreviation;
    private String gridName;
    private String gridNameAbbreviation;
    private int sequenceNum;
    private List<MarkerGridHeadingTerm> headingTerms;

    // ================= Getters and Setters ===================== //
	
    @Id
    @Column(name="heading_key")
    public int getHeadingKey() {
	return this.headingKey;
    }

    public void setHeadingKey(int headingKey) {
	this.headingKey = headingKey;
    }

    @Column(name="heading")
    public String getHeading() {
	return heading;
    }

    public void setHeading(String heading) {
	this.heading = heading;
    }

    @Column(name="heading_abbreviation")
    public String getHeadingAbbreviation() {
	return headingAbbreviation;
    }

    public void setHeadingAbbreviation(String headingAbbreviation) {
	this.headingAbbreviation = headingAbbreviation;
    }

    @Column(name="grid_name")
    public String getGridName() {
	return gridName;
    }

    public void setGridName(String gridName) {
	this.gridName = gridName;
    }

    @Column(name="grid_name_abbreviation")
    public String getGridNameAbbreviation() {
	return gridNameAbbreviation;
    }

    public void setGridNameAbbreviation(String gridNameAbbreviation) {
	this.gridNameAbbreviation = gridNameAbbreviation;
    }

    @Column(name="sequence_num")
    public int getSequenceNum() {
	return sequenceNum;
    }

    public void setSequenceNum(int sequenceNum) {
	this.sequenceNum = sequenceNum;
    }

    @OneToMany(targetEntity=MarkerGridHeadingTerm.class)
    @BatchSize(size=10)
    @JoinColumn(name="heading_key")
    public List<MarkerGridHeadingTerm> getHeadingTerms() {
	return headingTerms;
    }

    public void setHeadingTerms(List<MarkerGridHeadingTerm> headingTerms) {
	this.headingTerms = headingTerms;
    }

    @Transient
    public List<String> getTermIDs() {
	List<String> ids = new ArrayList<String>();

	for (MarkerGridHeadingTerm term : this.headingTerms) {
	    ids.add(term.getTermID());
	}
	return ids;
    }

    @Override
    public String toString() {
	return "MarkerGridHeading [headingKey=" + headingKey + ", "
	    + "heading=" + heading + ", "
	    + "gridName=" + gridName + "]";
    }
}
