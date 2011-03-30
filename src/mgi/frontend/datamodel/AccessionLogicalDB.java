package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="accession_logical_db")
public class AccessionLogicalDB {

	String logicalDB;
	int logicalDBKey;
	
	@Column(name="logical_db")
	public String getLogicalDB() {
		return logicalDB;
	}
	@Id
	@Column(name="logical_db_key")
	public int getLogicalDBKey() {
		return logicalDBKey;
	}
	public void setLogicalDB(String logicalDB) {
		this.logicalDB = logicalDB;
	}
	public void setLogicalDBKey(int logicalDBKey) {
		this.logicalDBKey = logicalDBKey;
	}
	
	

}
