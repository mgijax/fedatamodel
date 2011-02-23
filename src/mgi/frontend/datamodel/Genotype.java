package mgi.frontend.datamodel;

import javax.persistence.*;

import java.util.List;

/**
 * Genotype
 * @author jsb
 * This object represents a genotype -- a combination of a background strain
 * and zero or more pairs of alleles that have been introduced onto that
 * strain.  Genotypes may be ordered using data from genotype_sequence_num.
 */
@Entity
@Table(name="genotype")
@SecondaryTables (
    { 
      @SecondaryTable (name="genotype_sequence_num", pkJoinColumns= {
        @PrimaryKeyJoinColumn(name="genotype_key", referencedColumnName="genotype_key") } )
    }  
)
public class Genotype {
    
	private int byAlleles;				// for sorting genotypes
	private int genotypeKey;
	private String backgroundStrain;
	private String primaryID;
	private List<Image> images;
	private int isConditional;
	private String note;
	private String combination1;
	private String combination2;
	private List<Annotation> annotations;
	
	// ================= Getters and Setters ===================== //

    @OneToMany (fetch=FetchType.LAZY)
    @JoinColumn(name="genotype_key")
    public List<Annotation> getAnnotations() {
    	return annotations;
    }

    @Column(name="background_strain")
	public String getBackgroundStrain() {
		return backgroundStrain;
	}

	@Column(table="genotype_sequence_num", name="by_alleles")
	public int getByAlleles() {
		return byAlleles;
	}

	@Column(name="combination_1")
	public String getCombination1() {
		return combination1;
	}

	@Column(name="combination_2")
	public String getCombination2() {
		return combination2;
	}

	@Id
	@Column(name="genotype_key")
	public int getGenotypeKey() {
		return genotypeKey;
	}
	
    /** get an ordered list of images featuring this genotype
     */
	@OneToMany (fetch=FetchType.LAZY)
	@JoinTable (name="genotype_to_image",
			joinColumns=@JoinColumn(name="genotype_key"),
			inverseJoinColumns=@JoinColumn(name="image_key")
			)
	@OrderBy("byDefault")
	public List<Image> getImages() {
		return images;
	} 
    
	@Column(name="is_conditional")
	public int getIsConditional() {
		return isConditional;
	}
	
	@Column(name="note")
	public String getNote() {
		return note;
	}
	
	@Column(name="primary_id")
	public String getPrimaryID() {
		return primaryID;
	}
	
	public void setAnnotations(List<Annotation> annotations) {
		this.annotations = annotations;
	}
	
	public void setBackgroundStrain(String backgroundStrain) {
		this.backgroundStrain = backgroundStrain;
	}
	
	public void setByAlleles(int byAlleles) {
		this.byAlleles = byAlleles;
	}
	
	public void setCombination1(String combination1) {
		this.combination1 = combination1;
	}
	
	public void setCombination2(String combination2) {
		this.combination2 = combination2;
	}
	
	public void setGenotypeKey(int genotypeKey) {
		this.genotypeKey = genotypeKey;
	}
	
	public void setImages(List<Image> images) {
		this.images = images;
	}
	
	public void setIsConditional(int isConditional) {
		this.isConditional = isConditional;
	}
	
	public void setNote(String note) {
		this.note = note;
	}
	
	public void setPrimaryID(String primaryID) {
		this.primaryID = primaryID;
	}

	@Override
	public String toString() {
		return "Genotype ["
				+ (backgroundStrain != null ? "backgroundStrain="
						+ backgroundStrain + ", " : "")
				+ (combination1 != null ? "combination1=" + combination1 + ", "
						: "")
				+ (combination2 != null ? "combination2=" + combination2 + ", "
						: "") + "genotypeKey=" + genotypeKey
				+ ", isConditional=" + isConditional + ", "
				+ (note != null ? "note=" + note + ", " : "")
				+ (primaryID != null ? "primaryID=" + primaryID : "") + "]";
	}
}