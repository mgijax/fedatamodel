package mgi.frontend.datamodel;

import javax.persistence.*;

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
