package mgi.frontend.datamodel;

import javax.persistence.Transient;
import javax.persistence.Id;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
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
    private List<StrainGridPopupRow> popupRows;

    // ================= Getters and Setters ===================== //
	
    @Column(name="color_level")
    public int getColorLevel() {
    	return colorLevel;
    }

    @Id
    @Column(name="grid_cell_key")
    public int getGridCellKey() {
    	return gridCellKey;
    }

    @ManyToOne(targetEntity=StrainGridHeading.class)
    @JoinColumn(name="heading_key")
    @BatchSize(size=1)
    public StrainGridHeading getGridHeading() {
    	return gridHeading;
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
    public String getHeading() {
    	return this.gridHeading.getHeading();
    }

    @Transient
    public String getHeadingID() {
    	return this.gridHeading.getTermIDs().get(0);
    }
    
    @Transient
    public String getHeadingAbbreviation() {
    	return this.gridHeading.getHeadingAbbreviation();
    }

    @OneToMany(targetEntity=StrainGridPopupRow.class)
    @BatchSize(size=30)
    @JoinColumn(name="grid_cell_key")
    @OrderBy("sequenceNum")
    public List<StrainGridPopupRow> getPopupRows() {
		return popupRows;
	}

    @Transient
    public List<String> getPopupColumnHeaders() {
    	List<String> terms = new ArrayList<String>();
    	for (StrainGridPopupCell cell : this.getPopupRows().get(0).getCells()) {
    		terms.add(cell.getTerm());
    	}
   		return terms;
    }
    
    @Column(name="sequence_num")
    public int getSequenceNum() {
    	return sequenceNum;
    }

    @Column(name="strain_key")
    public int getStrainKey() {
    	return strainKey;
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

    @Column(name="value")
    public int getValue() {
    	return value;
    }

    public void setColorLevel(int colorLevel) {
    	this.colorLevel = colorLevel;
    }

    public void setGridCellKey(int gridCellKey) {
    	this.gridCellKey = gridCellKey;
    }

    public void setGridHeading(StrainGridHeading gridHeading) {
    	this.gridHeading = gridHeading;
    }

    public void setPopupRows(List<StrainGridPopupRow> popupRows) {
		this.popupRows = popupRows;
	}
 
    public void setSequenceNum(int sequenceNum) {
    	this.sequenceNum = sequenceNum;
    }
 
    public void setStrainKey(int strainKey) {
    	this.strainKey = strainKey;
    }
 
    public void setValue(int value) {
    	this.value = value;
    }

    @Override
    public String toString() {
	return "StrainGridCell [gridCellKey=" + gridCellKey
		+ "strainKey=" + strainKey + ", "
		+ "colorLevel=" + colorLevel + ", "
		+ "value=" + value + ", ]";
    }
}
