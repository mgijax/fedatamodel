package mgi.frontend.datamodel;

import java.util.Comparator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import mgi.frontend.datamodel.sort.SmartAlphaComparator;

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
    private Image image;
    private String paneLabel;
    private int sequenceNum;
    private Integer x;
    private Integer y;
    private Integer width;
    private Integer height;
    private List<ImagePaneDetails> details;
    private List<ImagePaneID> imagePaneIds;

    // ================= Getters and Setters ===================== //

	/** get a list of ImagePaneDetails objects
	 */
	@OneToMany (targetEntity=ImagePaneDetails.class)
	@JoinColumn(name="imagepane_key")
	@OrderBy("sequenceNum")
	public List<ImagePaneDetails> getDetails() {
		return details;
	}
	
	@OneToOne(targetEntity=Image.class, fetch=FetchType.LAZY)
	@JoinColumn (name="image_key", insertable=false, updatable=false)
    public Image getImage() {
		return image;
	}

	/** get a list of ImagePaneID objects
	 */
	@OneToMany (targetEntity=ImagePaneID.class)
	@JoinColumn(name="imagepane_key")
	public List<ImagePaneID> getImagePaneIds() {
		return imagePaneIds;
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
	@Column(name="x")
	public int getX() {
		return x;
	}
	@Column(name="y")
	public int getY() {
		return y;
	}
	@Column(name="width")
	public int getWidth() {
		return width;
	}
	@Column(name="height")
	public int getHeight() {
		return height;
	}
	
	@Column(name="sequence_num")
	public int getSequenceNum() {
		return sequenceNum;
	}
	
	/* Transient Methods */
	@Transient
	/* return the combined pane + image label */
	public String getCombinedLabel()
	{
		String figLabel = this.getImage().getFigureLabel()==null ? "" : this.getImage().getFigureLabel();
		String pLabel = getPaneLabel()==null ? "" : getPaneLabel();
		return figLabel+pLabel;
	}

	@Transient
	public Comparator<ImagePane> getComparator()
	{
		return new ImagePaneComparator();
	}
	public void setDetails(List<ImagePaneDetails> details) {
		this.details = details;
	}

	public void setImagePaneIds(List<ImagePaneID> imagePaneIds) {
		this.imagePaneIds = imagePaneIds;
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
	
	public void setImage(Image image)
	{
		this.image = image;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	@Override
	public String toString() {
		return "ImagePane [imagePaneKey=" + imagePaneKey + ", imageKey="
				+ imageKey + ", paneLabel=" + paneLabel + ", sequenceNum="
				+ sequenceNum + "]";
	}
	
	private class ImagePaneComparator extends SmartAlphaComparator<ImagePane>
	{
		public int compare(ImagePane o1, ImagePane o2)
		{
			ImagePane i1 = (ImagePane) o1;
			ImagePane i2 = (ImagePane) o2;
			return super.compare(i1.getCombinedLabel(),i2.getCombinedLabel());
		}
	}
}
