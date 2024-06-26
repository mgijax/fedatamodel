package org.jax.mgi.fe.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * SequenceSource
 * @author mhall
 * Returns a source for a sequence, this object extends the source 
 * base object.  
 */
@Entity
@Table (name="sequence_source")
public class SequenceSource extends Source {
	protected Integer sequenceKey;
	protected String strainID;
	
    // ================= Getters and Setters ===================== //
	
	@Column(name="sequence_key")
	public Integer getSequenceKey() {
		return sequenceKey;
	}

	public void setSequenceKey(Integer sequenceKey) {
		this.sequenceKey = sequenceKey;
	}

	@Column(name="strain_id")
	public String getStrainID() {
		return strainID;
	}

	public void setStrainID(String strainID) {
		this.strainID = strainID;
	}

	/** returns true if the source uses raw values.  (These values are 
	 * marked by an asterisk and indicate those that could not be mapped 
	 * to an MGI controlled vocabulary.)
	 */
	@Transient
	public boolean hasRawValues() {
		if ((this.age != null) && this.age.endsWith("*")) {
			return true;
		}
		if ((this.cellline != null) && this.cellline.endsWith("*")) {
			return true;
		}
		if ((this.sex != null) && this.sex.endsWith("*")) {
			return true;
		}
		if ((this.strain != null) && this.strain.endsWith("*")) {
			return true;
		}
		if ((this.tissue != null) && this.tissue.endsWith("*")) {
			return true;
		}
		return false;
	}
}
