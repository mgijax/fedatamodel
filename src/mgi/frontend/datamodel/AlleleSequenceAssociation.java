package mgi.frontend.datamodel;

import javax.persistence.*;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * is an association between a sequence and an allele, including an optional
 * qualifier to describe the type of association.
 *
 * This object extends the association base object, so most of its methods
 * are inherited from this.
 */

@Entity
@Table (name="allele_to_sequence")
public class AlleleSequenceAssociation extends Association {

    protected Allele allele;
    protected Sequence sequence;

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

	@ManyToOne (targetEntity=Sequence.class, fetch=FetchType.LAZY)
	@JoinColumn (name="sequence_key")
	public Sequence getSequence() {
		return sequence;
	}

	public void setAllele(Allele allele) {
		this.allele = allele;
	}

	public void setSequence(Sequence sequence) {
		this.sequence = sequence;
	}

	@Override
	public String toString() {
		return "AlleleSequenceAssociation [allele=" + allele
				+ qualifier + ", sequence=" + sequence + ", uniqueKey="
				+ uniqueKey + "]";
	}
}
