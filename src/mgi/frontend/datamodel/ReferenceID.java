package mgi.frontend.datamodel;

import javax.persistence.*;

/**
 * ReferenceID
 * @author mhall
 * An object representing a generic reference id.
 */
@Entity
@Table (name="reference_id")
public class ReferenceID extends AccessionID {
    
	private int referenceKey;

    // ================= Getters and Setters ===================== //
	
	@Column(name="reference_key")
	public int getReferenceKey() {
		return referenceKey;
	}

	public void setReferenceKey(int referenceKey) {
		this.referenceKey = referenceKey;
	}

	@Override
	public String toString() {
		return "ReferenceID [referenceKey=" + referenceKey + ", "
				+ (accID != null ? "accID=" + accID + ", " : "")
				+ "isPreferred=" + isPreferred + ", isPrivate=" + isPrivate
				+ ", "
				+ (logicalDB != null ? "logicalDB=" + logicalDB + ", " : "")
				+ "uniqueKey=" + uniqueKey + "]";
	}
}
