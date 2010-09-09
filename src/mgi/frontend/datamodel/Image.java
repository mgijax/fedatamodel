package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="image")
public class Image {
    
    private int imageKey;
    private int referenceKey;
    private int thumbnailImageKey;
    private Integer fullsizeImageKey;
    private int isThumbnail;
    private Integer width;
    private Integer height;
    private String figureLabel;
    private String imageType;
    private String pixeldbNumericID;
    private String copyright;
    private String caption;
    
    @Id
    @Column(name="image_key")
    public int getImageKey() {
        return imageKey;
    }
    public void setImageKey(int imageKey) {
        this.imageKey = imageKey;
    }
    
    @Column(name="reference_key")
    public int getReferenceKey() {
        return referenceKey;
    }
    public void setReferenceKey(int referenceKey) {
        this.referenceKey = referenceKey;
    }
    
    @Column(name="thumbnail_image_key")
    public int getThumbnailImageKey() {
        return thumbnailImageKey;
    }
    public void setThumbnailImageKey(int thumbnailImageKey) {
        this.thumbnailImageKey = thumbnailImageKey;
    }
    
    @Column(name="fullsize_image_key")
    public Integer getFullsizeImageKey() {
        return fullsizeImageKey;
    }
    public void setFullsizeImageKey(Integer fullsizeImageKey) {
        this.fullsizeImageKey = fullsizeImageKey;
    }
    
    @Column(name="is_thumbnail")
    public int getIsThumbnail() {
        return isThumbnail;
    }
    public void setIsThumbnail(int isThumbnail) {
        this.isThumbnail = isThumbnail;
    }
    
    public Integer getWidth() {
        return width;
    }
    public void setWidth(Integer width) {
        this.width = width;
    }
    
    public Integer getHeight() {
        return height;
    }
    public void setHeight(Integer height) {
        this.height = height;
    }
    
    @Column(name="figure_label")
    public String getFigureLabel() {
        return figureLabel;
    }
    public void setFigureLabel(String figureLabel) {
        this.figureLabel = figureLabel;
    }
    
    @Column(name="image_type")
    public String getImageType() {
        return imageType;
    }
    public void setImageType(String imageType) {
        this.imageType = imageType;
    }
    
    @Column(name="pixeldb_numeric_id")
    public String getPixeldbNumericID() {
        return pixeldbNumericID;
    }
    public void setPixeldbNumericID(String pixeldbNumericID) {
        this.pixeldbNumericID = pixeldbNumericID;
    }
    
    public String getCopyright() {
        return copyright;
    }
    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }
    
    public String getCaption() {
        return caption;
    }
    public void setCaption(String caption) {
        this.caption = caption;
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
