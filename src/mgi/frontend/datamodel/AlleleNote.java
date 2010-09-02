package mgi.frontend.datamodel;

import javax.persistence.*;

@Entity
@Table (name="allele_note")
public class AlleleNote extends Note {
	protected Integer alleleKey;

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
