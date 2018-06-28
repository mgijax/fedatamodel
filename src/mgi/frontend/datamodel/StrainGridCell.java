package mgi.frontend.datamodel;

import javax.persistence.Transient;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;

/**
 * StrainGridCell
 * @author jsb
 * This object represents one cell on a slimgrid on the strain detail page.
 */
@Entity
@Table (name="strain_grid_cell")
public class StrainGridCell {
    private int gridCellKey;
    private int strainKey;
    private int colorLevel;
    private int value;
    private int sequenceNum;
    private StrainGridHeading gridHeading;

    // ================= Getters and Setters ===================== //
	
    @Id
    @Column(name="grid_cell_key")
    public int getGridCellKey() {
    	return gridCellKey;
    }

    public void setGridCellKey(int gridCellKey) {
    	this.gridCellKey = gridCellKey;
    }

    @ManyToOne(targetEntity=StrainGridHeading.class)
    @JoinColumn(name="heading_key")
    @BatchSize(size=1)
    public StrainGridHeading getGridHeading() {
    	return gridHeading;
    }
	
    public void setGridHeading(StrainGridHeading gridHeading) {
    	this.gridHeading = gridHeading;
    }

    @Column(name="strain_key")
    public int getStrainKey() {
    	return strainKey;
    }

    public void setStrainKey(int strainKey) {
    	this.strainKey = strainKey;
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
	return "StrainGridCell [gridCellKey=" + gridCellKey
		+ "strainKey=" + strainKey + ", "
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
