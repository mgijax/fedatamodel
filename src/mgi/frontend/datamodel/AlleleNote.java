package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Object representing allele notes
 * @author mhall
 * This inherits almost all of its methods from the note object.
 */
@Entity
@Table (name="allele_note")
public class AlleleNote extends Note {
	protected Integer alleleKey;

    // ================= Getters and Setters ===================== //
	
	@Column(name="allele_key")
	public Integer getAlleleKey() {
		return alleleKey;
	}

	public void setAlleleKey(Integer alleleKey) {
		this.alleleKey = alleleKey;
	}

	@Override
    public String toString() {
        return "AlleleNote [alleleKey=" + alleleKey + ", note=" + note
                + ", noteType=" + noteType + ", uniqueKey=" + uniqueKey + "]";
    }
}
