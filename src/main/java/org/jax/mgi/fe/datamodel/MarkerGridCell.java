package org.jax.mgi.fe.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;

/**
 * MarkerGridCell
 * @author jsb
 * This object represents on cell on a slimgrid on the marker detail page.
 */
@Entity
@Table (name="marker_grid_cell")
public class MarkerGridCell {
    private int gridCellKey;
    private int markerKey;
    private int colorLevel;
    private int value;
    private int sequenceNum;
    private MarkerGridHeading gridHeading;

    // ================= Getters and Setters ===================== //
	
    @Id
    @Column(name="grid_cell_key")
    public int getGridCellKey() {
	return gridCellKey;
    }

    public void setGridCellKey(int gridCellKey) {
	this.gridCellKey = gridCellKey;
    }

    @ManyToOne(targetEntity=MarkerGridHeading.class)
    @JoinColumn(name="heading_key")
    @BatchSize(size=1)
    public MarkerGridHeading getGridHeading() {
	return gridHeading;
    }
	
    public void setGridHeading(MarkerGridHeading gridHeading) {
	this.gridHeading = gridHeading;
    }

    @Column(name="marker_key")
    public int getMarkerKey() {
	return markerKey;
    }

    public void setMarkerKey(int markerKey) {
	this.markerKey = markerKey;
    }

    @Column(name="color_level")
    public int getColorLevel() {
	return colorLevel;
    }

    public void setColorLevel(int colorLevel) {
	this.colorLevel = colorLevel;
    }

    @Column(name="value")
    public int getValue() {
	return value;
    }

    public void setValue(int value) {
	this.value = value;
    }

    @Column(name="sequence_num")
    public int getSequenceNum() {
	return sequenceNum;
    }

    public void setSequenceNum(int sequenceNum) {
	this.sequenceNum = sequenceNum;
    }

    @Override
    public String toString() {
	return "MarkerGridCell [gridCelLKey=" + gridCellKey
		+ "markerKey=" + markerKey + ", "
		+ "colorLevel=" + colorLevel + ", "
		+ "value=" + value + ", ]";
    }

    //=============== transient (convenience) methods ===============//
 
    @Transient
    public String getHeading() {
	return this.gridHeading.getHeading();
    }
 
    @Transient
    public String getHeadingAbbreviation() {
	return this.gridHeading.getHeadingAbbreviation();
    }
 
    @Transient
    public String getGridName() {
	return this.gridHeading.getGridName();
    }
 
    @Transient
    public String getGridNameAbbreviation() {
	return this.gridHeading.getGridNameAbbreviation();
    }

    @Transient
    public String getTermIDs() {
	StringBuffer sb = new StringBuffer();
        for (String id : this.gridHeading.getTermIDs()) {
	    if (sb.length() > 0) { sb.append(","); }
	    sb.append(id);
	}
	return sb.toString();
    }
}
