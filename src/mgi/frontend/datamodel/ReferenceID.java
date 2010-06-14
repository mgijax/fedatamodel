package mgi.frontend.datamodel;

import javax.persistence.*;

@Entity
@Table (name="referenceid")
public class ReferenceID extends AccessionID {
	private int referenceKey;

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
