package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SequenceSequenceNum
 * @author kstone
 * Captures sorting information for rows in the sequence table
 */

@Entity
@Table (name="sequence_sequence_num")
public class SequenceSequenceNum
{
	protected int sequenceKey;
	
	protected Integer bySequenceType;
	protected Integer byProvider;
	//protected Sequence sequence;

    // ================= Getters and Setters ===================== //

	@Id
	@Column(name="sequence_key")
	public int getSequenceKey()
	{
		return sequenceKey;
	}
	public void setSequenceKey(int sequenceKey)
	{
		this.sequenceKey=sequenceKey;
	}

	@Column(name="by_sequence_type")
	public Integer getBySequenceType() {
		return bySequenceType;
	}
	@Column(name="by_provider")
	public Integer getByProvider() {
		return byProvider;
	}
	public void setBySequenceType(Integer bySequenceType) {
		this.bySequenceType = bySequenceType;
	}
	public void setByProvider(Integer byProvider) {
		this.byProvider = byProvider;
	}
//	/**
//	 * Return the actual sequence object for this relationship
//	 * @return
//	 */
//
//	@ManyToOne (targetEntity=Sequence.class, fetch=FetchType.LAZY)
//	@JoinColumn (name="sequence_key")
//	public Sequence getSequence() {
//		return sequence;
//	}
//
//	public void setSequence(Sequence sequence) {
//		this.sequence = sequence;
//	}
}
