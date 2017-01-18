package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * MarkerBiotypeConflict
 * @author jsb
 * 
 */
@Entity
@Table(name="marker_biotype_conflict")
public class MarkerBiotypeConflict {
    
	private int uniqueKey;
	private int markerKey;
	private String accID;
	private String logicalDB;
	private String biotype;
	private int sequenceNum;

    // ================= Getters and Setters ===================== //
	
	@Column(name="acc_id")
	public String getAccID() {
		return accID;
	}

	@Column(name="biotype")
	public String getBiotype() {
		return biotype;
	}

	@Column(name="logical_db")
	public String getLogicalDB() {
		return logicalDB;
	}

	@Column(name="marker_key")
	public int getMarkerKey() {
		return markerKey;
	}

	@Column(name="sequence_num")
	public int getSequenceNum() {
		return sequenceNum;
	}

	@Id
	@Column(name="unique_key")
	public int getUniqueKey() {
		return uniqueKey;
	}

	public void setAccID(String accID) {
		this.accID = accID;
	}

	public void setBiotype(String biotype) {
		this.biotype = biotype;
	}

	public void setLogicalDB(String logicalDB) {
		this.logicalDB = logicalDB;
	}

	public void setMarkerKey(int markerKey) {
		this.markerKey = markerKey;
	}

	public void setSequenceNum(int sequenceNum) {
		this.sequenceNum = sequenceNum;
	}

	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	@Override
	public String toString() {
		return "MarkerBiotypeConflict [uniqueKey=" + uniqueKey + ", markerKey="
				+ markerKey + ", accID=" + accID + ", logicalDB=" + logicalDB
				+ ", biotype=" + biotype + ", sequenceNum=" + sequenceNum + "]";
	}
}
