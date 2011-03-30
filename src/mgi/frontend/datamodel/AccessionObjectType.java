package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="accession_object_type")
public class AccessionObjectType {
	int objectTypeKey;
	String objectType;
	
	@Id
	@Column(name="object_type_key")
	public int getObjectTypeKey() {
		return objectTypeKey;
	}
	public void setObjectTypeKey(int objectTypeKey) {
		this.objectTypeKey = objectTypeKey;
	}
	@Column(name="object_type")
	public String getObjectType() {
		return objectType;
	}
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}
	
	

}
