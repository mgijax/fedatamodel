package mgi.frontend.datamodel;

import java.util.*;

import javax.persistence.*;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

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
