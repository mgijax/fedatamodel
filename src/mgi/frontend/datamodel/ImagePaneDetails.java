package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Details of an image pane, including an assay ID and basic marker data.
 * More than one of these can exist for an image pane (for expression data).
 * @author jsb
 */
        
@Entity
@Table(name="expression_imagepane_details")
public class ImagePaneDetails {
    
    private int uniqueKey;
    private int imagePaneKey;
    private int assayKey;
    private String assayID;
    private int markerKey;
    private String markerID;
    private String markerSymbol;
    private int sequenceNum;
    
    // ================= Getters and Setters ===================== //
    
	@Column(name="assay_id")
	public String getAssayID() {
		return assayID;
	}

	@Column(name="assay_key")
	public int getAssayKey() {
		return assayKey;
	}

	@Column(name="imagepane_key")
	public int getImagePaneKey() {
		return imagePaneKey;
	}

	@Column(name="marker_id")
	public String getMarkerID() {
		return markerID;
	}

	@Column(name="marker_key")
	public int getMarkerKey() {
		return markerKey;
	}

	@Column(name="marker_symbol")
	public String getMarkerSymbol() {
		return markerSymbol;
	}

	@Column(name="sequence_num")
	public int getSequenceNum() {
		return sequenceNum;
	}

	@Id
    @Column(name="unique_key")
    public int getUniqueKey() {
		return uniqueKey;
	}

	public void setAssayID(String assayID) {
		this.assayID = assayID;
	}

	public void setAssayKey(int assayKey) {
		this.assayKey = assayKey;
	}

	public void setImagePaneKey(int imagePaneKey) {
		this.imagePaneKey = imagePaneKey;
	}

	public void setMarkerID(String markerID) {
		this.markerID = markerID;
	}

	public void setMarkerKey(int markerKey) {
		this.markerKey = markerKey;
	}

	public void setMarkerSymbol(String markerSymbol) {
		this.markerSymbol = markerSymbol;
	}

	public void setSequenceNum(int sequenceNum) {
		this.sequenceNum = sequenceNum;
	}

	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	@Override
	public String toString() {
		return "ImagePaneDetails [uniqueKey=" + uniqueKey + ", imagePaneKey="
				+ imagePaneKey + ", assayKey=" + assayKey + ", assayID="
				+ assayID + ", markerKey=" + markerKey + ", markerID="
				+ markerID + ", markerSymbol=" + markerSymbol
				+ ", sequenceNum=" + sequenceNum + "]";
	}
}
