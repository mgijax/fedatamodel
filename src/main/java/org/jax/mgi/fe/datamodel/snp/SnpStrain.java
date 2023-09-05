package org.jax.mgi.fe.datamodel.snp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="snp_strain")
public class SnpStrain {
	
	private int snpStrainKey;
	private int mgdStrainKey;
	private String strain;
	private int sequenceNum;
	
	/* Getters */
	@Id
	@Column(name="_snpstrain_key")
	public int getSnpStrainKey() {
		return snpStrainKey;
	}
	@Column(name="_mgdstrain_key")
	public int getMgdStrainKey() {
		return mgdStrainKey;
	}
	
	@Column(name="strain")
	public String getStrain() {
		return strain;
	}
	
	@Column(name="sequencenum")
	public int getSequenceNum() {
		return sequenceNum;
	}

	/* Setters */
	public void setSnpStrainKey(int snpStrainKey) {
		this.snpStrainKey = snpStrainKey;
	}
	public void setStrain(String strain) {
		this.strain = strain;
	}
	public void setMgdStrainKey(int mgdStrainKey) {
		this.mgdStrainKey = mgdStrainKey;
	}
	public void setSequenceNum(int sequenceNum) {
		this.sequenceNum = sequenceNum;
	}
	
	@Override
	public String toString() {
		return "SnpStrain [snpStrainKey=" + snpStrainKey + ", mgdStrainKey="
				+ mgdStrainKey + ", strain=" + strain + ", sequenceNum="
				+ sequenceNum + "]";
	}
}
