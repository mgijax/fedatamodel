package mgi.frontend.datamodel;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * ImagePaneID
 */
@Entity
@Table (name="expression_imagepane_id")
public class ImagePaneID extends AccessionID {

	private int imagepane_key;
	private int sequenceNum;

    // ================= Getters and Setters ===================== //


	@Override
	public String toString() {
		return "ImagePaneID [imagepane_key=" + imagepane_key + ", "
				+ (accID != null ? "accID=" + accID + ", " : "")
				+ "isPreferred=" + isPreferred + ", isPrivate=" + isPrivate
				+ ", "
				+ (logicalDB != null ? "logicalDB=" + logicalDB + ", " : "")
				+ "uniqueKey=" + uniqueKey + "]";
	}
}
