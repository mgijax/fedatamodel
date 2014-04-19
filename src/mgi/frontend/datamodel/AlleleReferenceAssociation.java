package mgi.frontend.datamodel;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Implements the AlleleReferenecAssociation object.
 * This object mapped an early idea of a allele->Reference association.
 *
 * This object extends the association base object, so most of its methods
 * are inherited from this.
 *
 * This may be depreciated now.
 */

@Entity
@Table (name="allele_to_reference")
public class AlleleReferenceAssociation extends Association {

    protected Allele allele;

    // ================= Getters and Setters ===================== //

	/**
	 * Return the allele for this association.
	 * @return
	 */
	@ManyToOne (targetEntity=Allele.class, fetch=FetchType.LAZY)
	@JoinColumn (name="allele_key")
	public Allele getAllele() {
		return allele;
	}

	public void setAllele(Allele allele) {
		this.allele = allele;
	}

	@Override
	public String toString() {
		return "AlleleReferenceAssociation [allele=" + allele
				+ qualifier + ", reference=" + reference + ", uniqueKey="
				+ uniqueKey + "]";
	}
}
