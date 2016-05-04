package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="marker_minimap_marker")
public class MinimapMarker 
{
	private int uniqueKey;

	private int markerKey;
	private int anchorMarkerKey;
	private String anchorSymbol;
	private float cmOffset;
	private float maxCmOffset;
	
	@Id
	@Column(name="unique_key")
	public int getUniqueKey() {
		return uniqueKey;
	}
	
	@Column(name="marker_key")
	public int getMarkerKey() {
		return markerKey;
	}
	
	@Column(name="anchor_marker_key")
	public int getAnchorMarkerKey() {
		return anchorMarkerKey;
	}
	
	@Column(name="anchor_symbol")
	public String getAnchorSymbol() {
		return anchorSymbol;
	}
	
	@Column(name="cm_offset")
	public float getCmOffset() {
		return cmOffset;
	}
	
	@Column(name="max_cm_offset")
	public float getMaxCmOffset() {
		return maxCmOffset;
	}
	
	@Transient
	public boolean isPrimary() {
		return markerKey == anchorMarkerKey;
	}

	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	public void setAnchorSymbol(String anchorSymbol) {
		this.anchorSymbol = anchorSymbol;
	}

	public void setCmOffset(float cmOffset) {
		this.cmOffset = cmOffset;
	}

	public void setMaxCmOffset(float maxCmOffset) {
		this.maxCmOffset = maxCmOffset;
	}

	public void setMarkerKey(int markerKey) {
		this.markerKey = markerKey;
	}

	public void setAnchorMarkerKey(int anchorMarkerKey) {
		this.anchorMarkerKey = anchorMarkerKey;
	}
}