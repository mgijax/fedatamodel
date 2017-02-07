package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Is: an alias (synonym) for a probe, associated via a particular reference
 */
@Entity
@Table(name="probe_alias")
public class ProbeAlias {
	private int uniqueKey;
	private int probeReferenceKey;
	private int sequenceNum;
	private String alias;
	
    // ================= Getters and Setters ===================== //
	
	@Column(name="alias")
	public String getAlias() {
		return alias;
	}

	@Column(name="probe_reference_key")
	public int getProbeReferenceKey() {
		return probeReferenceKey;
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

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public void setProbeReferenceKey(int probeReferenceKey) {
		this.probeReferenceKey = probeReferenceKey;
	}

	public void setSequenceNum(int sequenceNum) {
		this.sequenceNum = sequenceNum;
	}

	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	@Override
	public String toString() {
		return "ProbeAlias [uniqueKey=" + uniqueKey + ", probeReferenceKey=" + probeReferenceKey + ", sequenceNum="
				+ sequenceNum + ", alias=" + alias + "]";
	}
}
