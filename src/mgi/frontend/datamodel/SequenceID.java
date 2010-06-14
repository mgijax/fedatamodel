package mgi.frontend.datamodel;

import javax.persistence.*;

@Entity
@Table (name="sequenceid")
public class SequenceID extends AccessionID {
	private int sequenceKey;

	public int getSequenceKey() {
		return sequenceKey;
	}

	public void setSequenceKey(int sequenceKey) {
		this.sequenceKey = sequenceKey;
	}

	@Override
	public String toString() {
		return "SequenceID [sequenceKey=" + sequenceKey + ", "
				+ (accID != null ? "accID=" + accID + ", " : "")
				+ "isPreferred=" + isPreferred + ", isPrivate=" + isPrivate
				+ ", "
				+ (logicalDB != null ? "logicalDB=" + logicalDB + ", " : "")
				+ "uniqueKey=" + uniqueKey + "]";
	}
}
