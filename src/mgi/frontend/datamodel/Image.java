package mgi.frontend.datamodel;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;

/**
 * Base object for images. 
 * @author mhall, jsb
 * An image may be ordered using data from image_sequence_num.
 * 
 * Phenotype images are related to genotypes through genotype_to_image, and
 * to markers through marker_to_phenotype_image, and have allele data 
 * cached for efficiency in image_alleles.
 * 
 * Expression images for a marker are best found through the
 * expression_imagepane_set table.  Details for a particular expression
 * image and its panes follow through expression_imagepane to
 * expression_imagepane_details.
 */
        
@Entity
@Table(name="image")
@SecondaryTables (
    { 
      @SecondaryTable (name="image_sequence_num", pkJoinColumns= {
        @PrimaryKeyJoinColumn(name="image_key", referencedColumnName="image_key") } )
    }  
    )
public class Image {
    
	private int byDefault;		// used for sorting images
    private String caption;
    private String copyright;
    private String figureLabel;
    private Integer fullsizeImageKey;
    private List<Genotype> genotypes;
    private Integer height;
    private List<ImageAllele> imageAlleles;
    private List<ImagePane> imagePanes;
    private int imageKey;
    private String imageType;
    private int isThumbnail;
    private String pixeldbNumericID;
    private Reference reference;
    private int thumbnailImageKey;
    private Integer width;
    private String jnumID;
    private String imageClass;
    
    
    // ================= Getters and Setters ===================== //
    
    @Column(table="image_sequence_num", name="by_default")
    public int getByDefault() {
		return byDefault;
	}
    
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

	/** get an ordered list of genotypes featured in the image
     */
    @OneToMany
	@JoinTable (name="genotype_to_image",
			joinColumns=@JoinColumn(name="image_key"),
			inverseJoinColumns=@JoinColumn(name="genotype_key")
			)
	@OrderBy("byAlleles")
	public List<Genotype> getGenotypes() {
		return genotypes;
	}

	public Integer getHeight() {
        return height;
    }

	/** get a list of ImageAllele objects ordered by allele symbol
	 */
	@OneToMany (targetEntity=ImageAllele.class)
	@JoinColumn(name="image_key")
	@OrderBy("sequenceNum")
	public List<ImageAllele> getImageAlleles() {
		return imageAlleles;
	}

	@Column(name="image_class")
    public String getImageClass() {
		return imageClass;
	}

	@Id
    @Column(name="image_key")
    public int getImageKey() {
        return imageKey;
    }

	/** get a list of ImageAllele objects ordered by allele symbol
	 */
	@OneToMany (targetEntity=ImagePane.class)
	@JoinColumn(name="image_key")
	@OrderBy("sequenceNum")
	public List<ImagePane> getImagePanes() {
		return imagePanes;
	}
    
    @Column(name="image_type")
    public String getImageType() {
        return imageType;
    }
    
    @Column(name="is_thumbnail")
    public int getIsThumbnail() {
        return isThumbnail;
    }
    
    @Column(name="jnum_id")
    public String getJnumID() {
		return jnumID;
	}
    
    @Column(name="pixeldb_numeric_id")
    public String getPixeldbNumericID() {
        return pixeldbNumericID;
    }
    
    @ManyToOne (targetEntity=Reference.class, fetch=FetchType.LAZY)
	@JoinColumn (name="reference_key")
	public Reference getReference() {
		return reference;
	}
    
    @Column(name="thumbnail_image_key")
    public int getThumbnailImageKey() {
        return thumbnailImageKey;
    }
    
    public Integer getWidth() {
        return width;
    } 
    
    public void setByDefault(int byDefault) {
		this.byDefault = byDefault;
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
    
    public void setGenotypes(List<Genotype> genotypes) {
		this.genotypes = genotypes;
	}
    
    public void setHeight(Integer height) {
        this.height = height;
    }
    
    public void setImageAlleles(List<ImageAllele> imageAlleles) {
		this.imageAlleles = imageAlleles;
	}
    
    public void setImageClass(String imageClass) {
		this.imageClass = imageClass;
	}
    
    public void setImageKey(int imageKey) {
        this.imageKey = imageKey;
    }
    
    public void setImagePanes(List<ImagePane> imagePanes) {
		this.imagePanes = imagePanes;
	}
    
    public void setImageType(String imageType) {
        this.imageType = imageType;
    }
    
    public void setIsThumbnail(int isThumbnail) {
        this.isThumbnail = isThumbnail;
    }
    
    public void setJnumID(String jnumID) {
		this.jnumID = jnumID;
	}
    
    public void setPixeldbNumericID(String pixeldbNumericID) {
        this.pixeldbNumericID = pixeldbNumericID;
    }

	public void setReference(Reference reference) {
		this.reference = reference;
	}

	public void setThumbnailImageKey(int thumbnailImageKey) {
        this.thumbnailImageKey = thumbnailImageKey;
    }

	public void setWidth(Integer width) {
        this.width = width;
    }

}
