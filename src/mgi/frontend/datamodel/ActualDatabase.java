package mgi.frontend.datamodel;

import javax.persistence.*;

/**
 * ActualDatabase
 * @author jsb
 * This object represents MGI's notion of an "actual database", a concrete
 * isntance of a "logical database".  These correspond to the ACC_LogicalDB
 * and ACC_ActualDB tables in the traditional 'mgd' database.
 */
@Entity
@Table(name="actual_database")
public class ActualDatabase implements Comparable {
    
	private int actualDbKey;
	private String logicalDb;
	private String actualDb;
	private String url;
	private int sequenceNum;
	
	// ================= Getters ===================== //

	@Column(name="logical_db")
	public String getLogicalDb() {
		return logicalDb;
	}

	@Id
	@Column(name="actualdb_key")
	public int getActualDbKey() {
		return actualDbKey;
	}

	@Column(name="actual_db")
	public String getActualDb() {
		return actualDb;
	}

	@Column(name="url")
	public String getUrl() {
		return url;
	}

	@Column(name="sequence_num")
	public int getSequenceNum() {
		return sequenceNum;
	}
	
	// ================= Setters ===================== //

	public void setLogicalDb(String logicalDb) {
		this.logicalDb = logicalDb;
	}
	
	public void setActualDbKey(int actualDbKey) {
		this.actualDbKey = actualDbKey;
	}
	
	public void setActualDb(String actualDb) {
		this.actualDb = actualDb;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public void setSequenceNum(int sequenceNum) {
		this.sequenceNum = sequenceNum;
	}
	
	@Override
	public String toString() {
		return "ActualDatabase ["
				+ (logicalDb != null ? "logicalDb="
						+ logicalDb + ", " : "")
				+ (actualDb != null ? "actualDb=" 
						+ actualDb + ", " : "")
				+ (url != null ? "url=" + url + ", " : "")
				+ "actualDbKey=" + actualDbKey
				+ ", sequenceNum=" + sequenceNum + "]";
	}

	/** standard comparison method for Comparable interface
	 */
	@Override
	public int compareTo(Object arg0) {
		ActualDatabase b = (ActualDatabase) arg0;
		
		if (this.sequenceNum < b.sequenceNum) { return -1; }
		else if (this.sequenceNum > b.sequenceNum) { return 1; }
		return 0;
	}
}
