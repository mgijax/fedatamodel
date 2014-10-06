package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ReferenceSequenceAssociation
 * @author kstone
 * Captures Markers -> Sequences, with metadata about the assocation
 * available for review.
 *
 * This object implements the association object, so most of its methods have
 * been implemented there.
 */

@Entity
@Table (name="reference_to_sequence")
public class ReferenceSequenceAssociation extends Association {

	protected int referenceKey;
	protected Sequence sequence;

    // ================= Getters and Setters ===================== //

	@Column(name="reference_key",insertable=false,updatable=false)
	public int getReferenceKey()
	{
		return referenceKey;
	}
	public void setReferenceKey(int referenceKey)
	{
		this.referenceKey=referenceKey;
	}

	/**
	 * Return the actual sequence object for this relationship
	 * @return
	 */

	@ManyToOne (targetEntity=Sequence.class, fetch=FetchType.LAZY)
	@JoinColumn (name="sequence_key")
	public Sequence getSequence() {
		return sequence;
	}

	public void setSequence(Sequence sequence) {
		this.sequence = sequence;
	}
}
