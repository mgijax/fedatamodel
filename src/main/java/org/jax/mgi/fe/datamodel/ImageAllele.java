package org.jax.mgi.fe.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * A very simple representation of an allele for associating with images.  
 * It only captures the most basic data for the allele, and
 * a sequence number for ordering an image's alleles.  (useful for
 * efficiency, as instantiating a full Allele would be more expensive)
 * @author jsb
 */
        
@Entity
@Table(name="image_alleles")
public class ImageAllele {
    
    private int uniqueKey;
    private int imageKey;
    private int alleleKey;
    private int sequenceNum;
    private String alleleSymbol;
    private String alleleName;
    private String alleleID;
    
    // ================= Getters and Setters ===================== //

    @Column(name="allele_id")
    public String getAlleleID() {
		return alleleID;
	}

    @Column(name="allele_key")
	public int getAlleleKey() {
		return alleleKey;
	}

    @Column(name="allele_name")
	public String getAlleleName() {
		return alleleName;
	}

    @Column(name="allele_symbol")
	public String getAlleleSymbol() {
		return alleleSymbol;
	}

	@Column(name="image_key")
	public int getImageKey() {
		return imageKey;
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

	public void setAlleleID(String alleleID) {
		this.alleleID = alleleID;
	}

	public void setAlleleKey(int alleleKey) {
		this.alleleKey = alleleKey;
	}

	public void setAlleleName(String alleleName) {
		this.alleleName = alleleName;
	}

	public void setAlleleSymbol(String alleleSymbol) {
		this.alleleSymbol = alleleSymbol;
	}

	public void setImageKey(int imageKey) {
		this.imageKey = imageKey;
	}

	public void setSequenceNum(int sequenceNum) {
		this.sequenceNum = sequenceNum;
	}

	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	@Override
	public String toString() {
		return "ImageAllele [uniqueKey=" + uniqueKey + ", imageKey=" + imageKey
				+ ", alleleKey=" + alleleKey + ", sequenceNum=" + sequenceNum
				+ ", alleleSymbol=" + alleleSymbol + ", alleleName="
				+ alleleName + ", alleleID=" + alleleID + "]";
	}
}
