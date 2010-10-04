package mgi.frontend.datamodel;

import javax.persistence.*;

/**
 * The base class for all accession id's
 * @author mhall, jsb
 * This class provides the common fields for all accession ID's.
 */

@MappedSuperclass
public class AccessionID {
	protected String accID;
	protected boolean isPreferred;
	protected boolean isPrivate;
	protected String logicalDB;
	protected int uniqueKey;
	
    // ================= Getters and Setters ===================== //
	
	@Column(name="acc_id")
	public String getAccID() {
		return accID;
	}
	
	@Column(name="logical_db")
	public String getLogicalDB() {
		return logicalDB;
	}

	/**
	 * This key is auto generated during database creation time, its not
	 * really used by the frontend.
	 * @return The unique key from the datamodel
	 */
	@Id
	@Column(name="unique_key")
	public int getUniqueKey() {
		return uniqueKey;
	}

	public boolean isPreferred() {
		return isPreferred;
	}

	public boolean isPrivate() {
		return isPrivate;
	}

	public void setAccID(String accID) {
		this.accID = accID;
	}

	
	public void setLogicalDB(String logicalDB) {
		this.logicalDB = logicalDB;
	}

	public void setPreferred(boolean isPreferred) {
		this.isPreferred = isPreferred;
	}

	public void setPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}

	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	@Override
	public String toString() {
		return this.getClass().getName().replaceAll(".*\\.", "") + " [" 
				+ (accID != null ? "accID=" + accID + ", " : "")
				+ "isPreferred=" + isPreferred + ", isPrivate=" + isPrivate
				+ ", "
				+ (logicalDB != null ? "logicalDB=" + logicalDB + ", " : "")
				+ "uniqueKey=" + uniqueKey + "]";
	}
}
