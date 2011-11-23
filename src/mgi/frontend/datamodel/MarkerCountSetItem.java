package mgi.frontend.datamodel;

import javax.persistence.*;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * MarkerCountSetItem
 * @author jsb
 * This extends the CountSetItem class, so most of its methods come from
 * that class.  This particular class is for count sets related to markers.
 */
@Entity
@Table (name="marker_count_sets")
public class MarkerCountSetItem extends CountSetItem {

    protected Integer markerKey;

    // ================= Getters and Setters ===================== //

	@Column(name="marker_key")
	public Integer getMarkerKey() {
		return markerKey;
	}

	public void setMarkerKey(Integer markerKey) {
		this.markerKey = markerKey;
	}

	@Override
	public String toString() {
		return "MarkerCountSetItem ["
				+ (markerKey != null ? "markerKey=" + markerKey + ", " : "")
				+ "count=" + count + ", "
				+ (countType != null ? "countType=" + countType + ", " : "")
				+ "sequenceNum=" + sequenceNum + ", "
				+ (setType != null ? "setType=" + setType + ", " : "")
				+ "uniqueKey=" + uniqueKey + "]";
	}
}
