package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/** A group of pane labels for an image where those pane labels are
 * associated with a single marker. 
 * @author jsb
 */
        
@Entity
@Table(name="expression_imagepane_set")
public class ImagePaneSet {
    private int panesetKey;
    private Image fullSizeImage;
    private Image thumbnailImage;
    private String assayType;
    private String paneLabels;
    private int markerKey;
    private int inPixelDB;
    private int sequenceNum;
	
    // ================= Getters and Setters ===================== //
    
    @Column(name="assay_type")
    public String getAssayType() {
		return assayType;
	}
    
	@ManyToOne (targetEntity=Image.class, fetch=FetchType.LAZY)
	@JoinColumn (name="image_key")
	public Image getFullSizeImage() {
		return fullSizeImage;
	}
	
	@Column(name="in_pixeldb")
	public int getInPixelDB() {
		return inPixelDB;
	}
	
	@Column(name="marker_key")
	public int getMarkerKey() {
		return markerKey;
	}
	
	@Column(name="pane_labels")
	public String getPaneLabels() {
		return paneLabels;
	}
	
	@Id
    @Column(name="paneset_key")
    public int getPanesetKey() {
		return panesetKey;
	}
	
	@Column(name="sequence_num")
	public int getSequenceNum() {
		return sequenceNum;
	}
	
	@ManyToOne (targetEntity=Image.class, fetch=FetchType.LAZY)
	@JoinColumn (name="thumbnail_key")	
	public Image getThumbnailImage() {
		return thumbnailImage;
	}
	
	public void setAssayType(String assayType) {
		this.assayType = assayType;
	}
	
	public void setFullSizeImage(Image fullSizeImage) {
		this.fullSizeImage = fullSizeImage;
	}

	public void setInPixelDB(int inPixelDB) {
		this.inPixelDB = inPixelDB;
	}
	
	public void setMarkerKey(int markerKey) {
		this.markerKey = markerKey;
	}
	
	public void setPaneLabels(String paneLabels) {
		this.paneLabels = paneLabels;
	}
	
	public void setPanesetKey(int panesetKey) {
		this.panesetKey = panesetKey;
	}
	
	public void setSequenceNum(int sequenceNum) {
		this.sequenceNum = sequenceNum;
	}
	
	public void setThumbnailImage(Image thumbnailImage) {
		this.thumbnailImage = thumbnailImage;
	}
	
	@Override
	public String toString() {
		return "ImagePaneSet [panesetKey=" + panesetKey + ", fullSizeImage="
				+ fullSizeImage + ", thumbnailImage=" + thumbnailImage
				+ ", assayType=" + assayType + ", paneLabels=" + paneLabels
				+ ", markerKey=" + markerKey + ", inPixelDB=" + inPixelDB
				+ ", sequenceNum=" + sequenceNum + "]";
	}    
}
