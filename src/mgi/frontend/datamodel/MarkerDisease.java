package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="marker_disease")
public class MarkerDisease {
	
	private int id;
	private int marker_key;
	private String disease;
	private String diseaseID;
	private String logicalDB;
	private int sequenceNum;

	@Id
	@Column(name="unique_key")
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="marker_key")
	public int getMarker_key() {
		return marker_key;
	}
	
	public void setMarker_key(int marker_key) {
		this.marker_key = marker_key;
	}
	
	public String getDisease() {
		return disease;
	}
	
	public void setDisease(String disease) {
		this.disease = disease;
	}
	
	@Column(name="disease_id")
	public String getDiseaseID() {
		return diseaseID;
	}
	
	public void setDiseaseID(String diseaseID) {
		this.diseaseID = diseaseID;
	}
	
	@Column(name="logical_db")
	public String getLogicalDB() {
		return logicalDB;
	}
	
	public void setLogicalDB(String logicalDB) {
		this.logicalDB = logicalDB;
	}
	
	@Column(name="sequence_num")
	public int getSequenceNum() {
		return sequenceNum;
	}
	
	public void setSequenceNum(int sequenceNum) {
		this.sequenceNum = sequenceNum;
	}

	@Override
	public String toString() {
		return "MarkerDisease [id=" + id + ", marker_key=" + marker_key
				+ ", disease=" + disease + ", diseaseID=" + diseaseID
				+ ", logicalDB=" + logicalDB + ", sequenceNum=" + sequenceNum
				+ "]";
	}
}
