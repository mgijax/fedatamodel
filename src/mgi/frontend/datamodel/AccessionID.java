package mgi.frontend.datamodel;

import javax.persistence.*;

@MappedSuperclass
public class AccessionID {
	protected int uniqueKey;
	protected String logicalDB;
	protected String accID;
	protected boolean isPreferred;
	protected boolean isPrivate;
	
	public AccessionID() {}

	@Id
	public int getUniqueKey() {
		return uniqueKey;
	}

	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	public String getLogicalDB() {
		return logicalDB;
	}

	public void setLogicalDB(String logicalDB) {
		this.logicalDB = logicalDB;
	}

	public String getAccID() {
		return accID;
	}

	public void setAccID(String accID) {
		this.accID = accID;
	}

	public boolean isPreferred() {
		return isPreferred;
	}

	public void setPreferred(boolean isPreferred) {
		this.isPreferred = isPreferred;
	}

	public boolean isPrivate() {
		return isPrivate;
	}

	public void setPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
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
