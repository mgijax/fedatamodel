package mgi.frontend.datamodel;

import javax.persistence.*;

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
	
    // ================= Getters and Setters ===================== //
	
	@Column(name="sequence_key")
	public Integer getSequenceKey() {
		return sequenceKey;
	}

	public void setSequenceKey(Integer sequenceKey) {
		this.sequenceKey = sequenceKey;
	}

}
