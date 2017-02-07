package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Is: a relative of a Probe object.  That is, it is a subset of data for one probe that was
 *		either derived from or was the source from which another probe was derived.  The is_child
 *		field indicates	whether the related probe is derived from (1) the probe_key, or if the
 *		probe identified by probe_key is derived from (0) the related probe.
 */
@Entity
@Table(name="probe_relative")
public class ProbeRelative {
	private int uniqueKey;
	private int probeKey;
	private int relatedProbeKey;
	private String relatedProbeID;
	private String relatedProbeName;
	private int isChild;
	private int sequenceNum;
	
    // ================= Getters and Setters ===================== //
	
	@Column(name="is_child")
	public int getIsChild() {
		return isChild;
	}

	@Column(name="probe_key")
	public int getProbeKey() {
		return probeKey;
	}

	@Column(name="related_probe_id")
	public String getRelatedProbeID() {
		return relatedProbeID;
	}

	@Column(name="related_probe_key")
	public int getRelatedProbeKey() {
		return relatedProbeKey;
	}

	@Column(name="related_probe_name")
	public String getRelatedProbeName() {
		return relatedProbeName;
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

	public void setIsChild(int isChild) {
		this.isChild = isChild;
	}

	public void setProbeKey(int probeKey) {
		this.probeKey = probeKey;
	}

	public void setRelatedProbeID(String relatedProbeID) {
		this.relatedProbeID = relatedProbeID;
	}

	public void setRelatedProbeKey(int relatedProbeKey) {
		this.relatedProbeKey = relatedProbeKey;
	}

	public void setRelatedProbeName(String relatedProbeName) {
		this.relatedProbeName = relatedProbeName;
	}

	public void setSequenceNum(int sequenceNum) {
		this.sequenceNum = sequenceNum;
	}

	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}
}
