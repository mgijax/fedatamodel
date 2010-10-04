package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Base object for images. 
 * @author mhall
 * This object is very straight forward, with no complex sub object relationships.
 * 
 */
        
@Entity
@Table(name="image")
public class Image {
    
    private String caption;
    private String copyright;
    private String figureLabel;
    private Integer fullsizeImageKey;
    private Integer height;
    private int imageKey;
    private String imageType;
    private int isThumbnail;
    private String pixeldbNumericID;
    private int referenceKey;
    private int thumbnailImageKey;
    private Integer width;
    
    // ================= Getters and Setters ===================== //
    
    public String getCaption() {
        return caption;
    }
    
    public String getCopyright() {
        return copyright;
    }
    
    @Column(name="figure_label")
    public String getFigureLabel() {
        return figureLabel;
    }
    
    @Column(name="fullsize_image_key")
    public Integer getFullsizeImageKey() {
        return fullsizeImageKey;
    }
    
    public Integer getHeight() {
        return height;
    }
    
    @Id
    @Column(name="image_key")
    public int getImageKey() {
        return imageKey;
    }
    
    @Column(name="image_type")
    public String getImageType() {
        return imageType;
    }
    
    @Column(name="is_thumbnail")
    public int getIsThumbnail() {
        return isThumbnail;
    }
    
    @Column(name="pixeldb_numeric_id")
    public String getPixeldbNumericID() {
        return pixeldbNumericID;
    }
    
    @Column(name="reference_key")
    public int getReferenceKey() {
        return referenceKey;
    }
    
    @Column(name="thumbnail_image_key")
    public int getThumbnailImageKey() {
        return thumbnailImageKey;
    }
    
    public Integer getWidth() {
        return width;
    }
    
    public void setCaption(String caption) {
        this.caption = caption;
    }
    
    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }
    
    public void setFigureLabel(String figureLabel) {
        this.figureLabel = figureLabel;
    }
    
    public void setFullsizeImageKey(Integer fullsizeImageKey) {
        this.fullsizeImageKey = fullsizeImageKey;
    }
    
    public void setHeight(Integer height) {
        this.height = height;
    }
    
    public void setImageKey(int imageKey) {
        this.imageKey = imageKey;
    }
    
    public void setImageType(String imageType) {
        this.imageType = imageType;
    }
    
    public void setIsThumbnail(int isThumbnail) {
        this.isThumbnail = isThumbnail;
    }
    
    public void setPixeldbNumericID(String pixeldbNumericID) {
        this.pixeldbNumericID = pixeldbNumericID;
    }
    
    public void setReferenceKey(int referenceKey) {
        this.referenceKey = referenceKey;
    }
    
    public void setThumbnailImageKey(int thumbnailImageKey) {
        this.thumbnailImageKey = thumbnailImageKey;
    }
    
    public void setWidth(Integer width) {
        this.width = width;
    }
    
    @Override
    public String toString() {
        return "Image [caption=" + caption + ", copyright=" + copyright
                + ", figureLabel=" + figureLabel + ", fullsizeImageKey="
                + fullsizeImageKey + ", height=" + height + ", imageKey="
                + imageKey + ", imageType=" + imageType + ", isThumbnail="
                + isThumbnail + ", pixeldbNumericID=" + pixeldbNumericID
                + ", referenceKey=" + referenceKey + ", thumbnailImageKey="
                + thumbnailImageKey + ", width=" + width + "]";
    }
    
    

}
