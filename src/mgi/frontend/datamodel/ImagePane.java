package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.util.List;

/**
 * One pane of an expression image, allowing multiple assays and markers via
 * ImagePaneDetails objects.
 * @author jsb
 */
        
@Entity
@Table(name="expression_imagepane")
public class ImagePane {
    
    private int imagePaneKey;
    private int imageKey;
    private String paneLabel;
    private int sequenceNum;
    private List<ImagePaneDetails> details;
    
    // ================= Getters and Setters ===================== //

	/** get a list of ImagePaneDetails objects ordered by marker symbol
	 */
	@OneToMany (targetEntity=ImagePaneDetails.class)
	@JoinColumn(name="imagepane_key")
	@OrderBy("sequenceNum")
	public List<ImagePaneDetails> getDetails() {
		return details;
	}

	@Column(name="image_key")
	public int getImageKey() {
		return imageKey;
	}

	@Id
	@Column(name="imagepane_key")
	public int getImagePaneKey() {
		return imagePaneKey;
	}

	@Column(name="pane_label")
	public String getPaneLabel() {
		return paneLabel;
	}

	@Column(name="sequence_num")
	public int getSequenceNum() {
		return sequenceNum;
	}

	public void setDetails(List<ImagePaneDetails> details) {
		this.details = details;
	}

	public void setImageKey(int imageKey) {
		this.imageKey = imageKey;
	}

	public void setImagePaneKey(int imagePaneKey) {
		this.imagePaneKey = imagePaneKey;
	}

	public void setPaneLabel(String paneLabel) {
		this.paneLabel = paneLabel;
	}

	public void setSequenceNum(int sequenceNum) {
		this.sequenceNum = sequenceNum;
	}

	@Override
	public String toString() {
		return "ImagePane [imagePaneKey=" + imagePaneKey + ", imageKey="
				+ imageKey + ", paneLabel=" + paneLabel + ", sequenceNum="
				+ sequenceNum + "]";
	}
}
