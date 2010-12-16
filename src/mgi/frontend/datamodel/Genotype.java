package mgi.frontend.datamodel;

import javax.persistence.*;

import java.util.List;

/**
 * Genotype
 * @author jsb
 * This object represents a genotype -- a combination of a background strain
 * and zero or more pairs of alleles that have been introduced onto that
 * strain.
 */
@Entity
@Table(name="genotype")
public class Genotype {
    
	private int genotypeKey;
	private String backgroundStrain;
	private String primaryID;
	private int isConditional;
	private String note;
	private String combination1;
	private String combination2;
	private List<GenotypeAnnotation> annotations;
	
	// ================= Getters and Setters ===================== //

    @OneToMany (fetch=FetchType.LAZY)
    @JoinColumn(name="genotype_key")
    public List<GenotypeAnnotation> getAnnotations() {
    	return annotations;
    }
    
	@Column(name="background_strain")
	public String getBackgroundStrain() {
		return backgroundStrain;
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
	
	public void setAnnotations(List<GenotypeAnnotation> annotations) {
		this.annotations = annotations;
	}
	
	public void setBackgroundStrain(String backgroundStrain) {
		this.backgroundStrain = backgroundStrain;
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