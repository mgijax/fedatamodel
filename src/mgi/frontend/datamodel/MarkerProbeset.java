package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * MarkerProbeset
 * @author jsb
 * One of these objects represents a microarray probeset association to a
 * marker.
 */
@Entity
@Table(name="marker_microarray")
public class MarkerProbeset {
    
	private int uniqueKey;
	private int markerKey;
	private String probesetID;
	private String platform;
	private String reportName;
	
    // ================= Getters and Setters ===================== //

	@Column(name="probeset_id")
	public String getProbesetID() {
		return probesetID;
	}
	
	@Column(name="report_name")
	public String getReportName() {
		return reportName;
	}
	
	@Column(name="platform")
	public String getPlatform() {
		return platform;
	}
	
	@Column(name="marker_key")
	public int getMarkerKey() {
		return markerKey;
	}
	
	@Id
	@Column(name="unique_key")
	public int getUniqueKey() {
		return uniqueKey;
	}
	
	public void setProbesetID(String probesetID) {
		this.probesetID = probesetID;
	}
	
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	
	public void setMarkerKey(int markerKey) {
		this.markerKey = markerKey;
	}
	
	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}
	
	@Override
	public String toString() {
		return "MarkerProbeset [uniqueKey=" + uniqueKey
			+ ", markerKey=" + markerKey
			+ ", probesetID=" + probesetID + "]";
	}
}
