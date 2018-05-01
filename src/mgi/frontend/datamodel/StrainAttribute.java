package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * StrainAttribute
 */
@Entity
@Table(name="strain_attribute")
public class StrainAttribute {
    
	private int uniqueKey;
	private int strainKey;
	private String attribute;
	private int sequenceNum;

    // ================= Getters and Setters ===================== //
	

	@Column(name="strain_key")
	public int getStrainKey() {
		return strainKey;
	}

	@Column(name="attribute")
	public String getAttribute() {
		return attribute;
	}

	@Id
	@Column(name="unique_key")
	public int getUniqueKey() {
		return uniqueKey;
	}

    @Column(name="sequence_num")
    public int getSequenceNum() {
	return sequenceNum;
    }

	public void setStrainKey(int strainKey) {
		this.strainKey = strainKey;
	}

	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	public void setSequenceNum(int sequenceNum) {
    	this.sequenceNum = sequenceNum;
    }

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
    
	@Override
	public String toString() {
		return "StrainAttribute ["
				+ "strainKey="
				+ strainKey
				+ ", "
				+ (attribute != null ? "attribute=" + attribute + ", " : "");
	}


}
