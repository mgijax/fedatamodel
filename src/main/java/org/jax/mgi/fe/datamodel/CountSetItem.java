package org.jax.mgi.fe.datamodel;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Note
 * @author jsb
 * Base CountSetItem class.  All tables that carry count_set information carry at least
 * these fields.  (A count set is a set of counts for an object, where we might want to
 * iterate over those counts rather than accessing each one directly.)
 */
@MappedSuperclass
public class CountSetItem {

	protected String setType;
	protected String countType;
	protected int uniqueKey;
	protected int count;
	protected int sequenceNum;

    // ================= Getters and Setters ===================== //

	@Column(name="count")
	public int getCount() {
		return count;
	}

	@Column(name="count_type")
	public String getCountType() {
		return countType;
	}

	@Column(name="sequence_num")
	public int getSequenceNum() {
		return sequenceNum;
	}

	@Column(name="set_type")
	public String getSetType() {
		return setType;
	}

	@Id
	@Column(name="unique_key")
	public int getUniqueKey() {
		return uniqueKey;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setCountType(String countType) {
		this.countType = countType;
	}

	public void setSequenceNum(int sequenceNum) {
		this.sequenceNum = sequenceNum;
	}

	public void setSetType(String setType) {
		this.setType = setType;
	}

	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	@Override
	public String toString() {
		return "CountSetItem [count=" + count + ", "
				+ (countType != null ? "countType=" + countType + ", " : "")
				+ "sequenceNum=" + sequenceNum + ", "
				+ (setType != null ? "setType=" + setType + ", " : "")
				+ "uniqueKey=" + uniqueKey + "]";
	}
}
