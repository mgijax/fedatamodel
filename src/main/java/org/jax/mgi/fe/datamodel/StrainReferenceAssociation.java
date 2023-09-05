package org.jax.mgi.fe.datamodel;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Implements the StrainReferenceAssociation object -- a join between a strain and a reference, with a qualifier
 */

@Entity
@Table (name="strain_to_reference")
public class StrainReferenceAssociation extends Association {

    protected Strain strain;

    // ================= Getters and Setters ===================== //

	/** Return the strain for this association.
	 */
	@ManyToOne (targetEntity=Strain.class, fetch=FetchType.LAZY)
	@JoinColumn (name="strain_key")
	public Strain getStrain() {
		return strain;
	}

	public void setStrain(Strain strain) {
		this.strain = strain;
	}

	@Override
	public String toString() {
		return "StrainReferenceAssociation [strain=" + strain + ", qualifier=" + qualifier + ", reference=" + reference
				+ ", uniqueKey=" + uniqueKey + "]";
	}
}
