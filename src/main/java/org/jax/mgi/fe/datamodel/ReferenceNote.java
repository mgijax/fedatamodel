package org.jax.mgi.fe.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * ReferenceNote
 * @author jsb
 * a note for a reference
 */
@Entity
@Table (name="reference_note")
public class ReferenceNote extends Note {
	
    protected Integer referenceKey;

    // ================= Getters and Setters ===================== //
    
	@Column(name="reference_key")
	public Integer getReferenceKey() {
		return referenceKey;
	}

	public void setReferenceKey(Integer referenceKey) {
		this.referenceKey = referenceKey;
	}

	@Override
	public String toString() {
		return "ReferenceNote [referenceKey=" + referenceKey + ", note=" + note
				+ ", noteType=" + noteType + ", uniqueKey=" + uniqueKey + "]";
	}
}
