package org.jax.mgi.fe.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

/**
 * WKSilversMarker - one of these objects represents a marker discussed in W.K. Silvers' book,
 * along with its corresponding MGI marker data.  If needed in the future, we could join from
 * the markerKey to a Marker object, but we'll stay simple for now.
 */
@Entity
@Table (name="marker_wksilvers")
public class WKSilversMarker {
	
	protected int uniqueKey;
	protected int sequenceNum;
	protected String silversSymbol;
	protected String silversUrlFragment;
	protected int markerKey;
	protected String markerSymbol;
	protected String markerID;
	
    // ================= Getters and Setters ===================== //
	
	@Column(name="marker_id")
	public String getMarkerID() {
		return markerID;
	}
	
	@Column(name="marker_key")
	public int getMarkerKey() {
		return markerKey;
	}
	
	@Column(name="marker_symbol")
	public String getMarkerSymbol() {
		return markerSymbol;
	}
	
	@Column(name="sequence_num")
	public int getSequenceNum() {
		return sequenceNum;
	}
	
	@Column(name="silvers_symbol")
	public String getSilversSymbol() {
		return silversSymbol;
	}

	@Column(name="silvers_url_fragment")
	public String getSilversUrlFragment() {
		return silversUrlFragment;
	}

	@Id
	@Column(name="unique_key")
	public int getUniqueKey() {
		return uniqueKey;
	}

	public void setMarkerID(String markerID) {
		this.markerID = markerID;
	}
	public void setMarkerKey(int markerKey) {
		this.markerKey = markerKey;
	}
	public void setMarkerSymbol(String markerSymbol) {
		this.markerSymbol = markerSymbol;
	}
	public void setSequenceNum(int sequenceNum) {
		this.sequenceNum = sequenceNum;
	}
	public void setSilversSymbol(String silversSymbol) {
		this.silversSymbol = silversSymbol;
	}
	public void setSilversUrlFragment(String silversUrlFragment) {
		this.silversUrlFragment = silversUrlFragment;
	}
	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	@Override
	public String toString() {
		return "WKSilversMarker [uniqueKey=" + uniqueKey + ", sequenceNum=" + sequenceNum + ", silversSymbol="
				+ silversSymbol + ", silversUrlFragment=" + silversUrlFragment + ", markerKey=" + markerKey
				+ ", markerSymbol=" + markerSymbol + ", markerID=" + markerID + "]";
	}
}
