package mgi.frontend.datamodel;

import javax.persistence.*;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * MarkerIDOtherMarker
 * @author jsb
 * Gene model IDs are sometimes shared across multiple markers.  An object
 * of this class represents another marker for an existing GM ID/marker
 * relationship.
 */
@Entity
@Table(name="marker_id_other_marker")
public class MarkerIDOtherMarker {

	private int uniqueKey;
	private int markerIdKey;
	private int markerKey;
	private String symbol;
	private String primaryID;

    // ================= Getters and Setters ===================== //

	@Column(name="marker_id_key")
	public int getMarkerIdKey() {
		return markerIdKey;
	}

	@Column(name="marker_key")
	public int getMarkerKey() {
		return markerKey;
	}

	@Column(name="primary_id")
	public String getPrimaryID() {
		return primaryID;
	}

	@Column(name="symbol")
	public String getSymbol() {
		return symbol;
	}

	@Id
	@Column(name="unique_key")
	public int getUniqueKey() {
		return uniqueKey;
	}

	public void setMarkerIdKey(int markerIdKey) {
		this.markerIdKey = markerIdKey;
	}

	public void setMarkerKey(int markerKey) {
		this.markerKey = markerKey;
	}

	public void setPrimaryID(String primaryID) {
		this.primaryID = primaryID;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	@Override
	public String toString() {
		return "MarkerIDOtherMarker [uniqueKey=" + uniqueKey + ", markerIdKey="
				+ markerIdKey + ", markerKey=" + markerKey + ", symbol="
				+ symbol + ", primaryID=" + primaryID + "]";
	}
}
