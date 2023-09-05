package org.jax.mgi.fe.datamodel;

import java.util.Comparator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.jax.mgi.fe.datamodel.sort.SmartAlphaComparator;

/**
 * Is: a sequence (related by a certain reference) for a probe object
 */
@Entity
@Table(name="probe_reference_sequence")
public class ProbeSequence {
	private int uniqueKey;
	private int probeReferenceKey;
	private Sequence sequence;
	private String sequenceID;
	private String qualifier;
	
    // ================= Transient methods ===================== //
	
	@Transient
	public String getPrimaryID() {
		return sequence.getPrimaryID();
	}
	
	@Transient
	public String getLogicalDB() {
		return sequence.getLogicalDB();
	}

	@Transient
	public Comparator<ProbeSequence> getComparator() {
		return new ProbeSequenceComparator();
	}

    // ================= Getters and Setters ===================== //
	
	@Column(name="probe_reference_key")
	public int getProbeReferenceKey() {
		return probeReferenceKey;
	}

	@Column(name="qualifier")
	public String getQualifier() {
		return qualifier;
	}

    @ManyToOne (targetEntity=Sequence.class, fetch=FetchType.LAZY)
    @JoinColumn (name="sequence_key")
	public Sequence getSequence() {
		return sequence;
	}

	@Column(name="sequence_id")
	public String getSequenceID() {
		return sequenceID;
	}

	@Id
	@Column(name="unique_key")
	public int getUniqueKey() {
		return uniqueKey;
	}

	public void setProbeReferenceKey(int probeReferenceKey) {
		this.probeReferenceKey = probeReferenceKey;
	}

	public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}

	public void setSequence(Sequence sequence) {
		this.sequence = sequence;
	}

	public void setSequenceID(String sequenceID) {
		this.sequenceID = sequenceID;
	}

	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	@Override
	public String toString() {
		return "ProbeSequence [uniqueKey=" + uniqueKey + ", probeReferenceKey=" + probeReferenceKey + ", sequenceID="
				+ sequenceID + ", qualifier=" + qualifier + "]";
	}

	private class ProbeSequenceComparator extends SmartAlphaComparator<ProbeSequence>
	{
		public int compare(ProbeSequence m1, ProbeSequence m2) {
			int c = super.compare(m1.getSequenceID(), m2.getSequenceID());
			if (c == 0) {
				c = super.compare(m1.getPrimaryID(), m2.getPrimaryID());
			}
			return c;
		}
	}
}
