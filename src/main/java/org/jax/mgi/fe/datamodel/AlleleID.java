package org.jax.mgi.fe.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
/**
 * An object representing a single allele id
 * @author mhall
 * This extends the accession id object, and inherits almost all of its
 * methods from it.
 */
@Entity
@Table (name="allele_id")
public class AlleleID extends AccessionID {
	private int alleleKey;

    // ================= Getters and Setters ===================== //
	
	@Column(name="allele_key")
	public int getAlleleKey() {
		return alleleKey;
	}

	public void setAlleleKey(int alleleKey) {
		this.alleleKey = alleleKey;
	}

	@Override
    public String toString() {
        return "AlleleID [alleleKey=" + alleleKey + ", accID=" + accID
                + ", isPreferred=" + isPreferred + ", isPrivate=" + isPrivate
                + ", logicalDB=" + logicalDB + ", uniqueKey=" + uniqueKey + "]";
    }
}
