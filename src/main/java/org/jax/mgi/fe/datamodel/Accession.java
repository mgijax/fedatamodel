package org.jax.mgi.fe.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="Accession")
public class Accession {
	
	private int uniqueKey;
	private int objectKey;
	private String searchID;
	private String displayID;
	private int sequenceNum;
	private String description;
	private AccessionLogicalDB logicalDBObject;
	private AccessionDisplayType displayTypeObject;
	private AccessionObjectType objectTypeObject;
	
	@Column(name="display_id")
	public String getDisplayID() {
		return displayID;
	}
	public void setDisplayID(String id) {
		this.displayID = id;
	}
	
	@Column(name="search_id")
	public String getSearchID() {
		return searchID;
	}
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	@OneToOne (targetEntity=AccessionDisplayType.class, fetch=FetchType.LAZY)
	@JoinColumn (name="display_type_key")
	private AccessionDisplayType getDisplayTypeObject() {
		return displayTypeObject;
	}

	@Transient
	public String getDisplayType() {
		return getDisplayTypeObject().getDisplayType();
	}
	
	@OneToOne (targetEntity=AccessionLogicalDB.class, fetch=FetchType.LAZY)
	@JoinColumn (name="logical_db_key")
	private AccessionLogicalDB getLogicalDBObject() {
		return logicalDBObject;
	}
	
	@Transient
	public String getLogicalDB() {
		return getLogicalDBObject().getLogicalDB();
	}
	
	@Column(name="object_key")
	public int getObjectKey() {
		return objectKey;
	}
	@OneToOne (targetEntity=AccessionObjectType.class, fetch=FetchType.LAZY)
	@JoinColumn (name="object_type_key")
	private AccessionObjectType getObjectTypeObject() {
		return objectTypeObject;
	}

	@Transient
	public String getObjectType () {
		return getObjectTypeObject().getObjectType();
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
    public void setSearchID(String searchID) {
		this.searchID = searchID;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setDisplayTypeObject(AccessionDisplayType displayType) {
		this.displayTypeObject = displayType;
	}
	public void setLogicalDBObject(AccessionLogicalDB logicalDB) {
		this.logicalDBObject = logicalDB;
	}
	public void setObjectKey(int objectKey) {
		this.objectKey = objectKey;
	}
	public void setObjectTypeObject(AccessionObjectType objectType) {
		this.objectTypeObject = objectType;
	}
	public void setSequenceNum(int sequenceNum) {
		this.sequenceNum = sequenceNum;
	}
	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

}
