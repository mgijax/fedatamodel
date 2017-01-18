package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="accession_display_type")
public class AccessionDisplayType {
	int displayTypeKey;
	String displayType;
	
	@Id
	@Column(name="display_type_key")
	public int getDisplayTypeKey() {
		return displayTypeKey;
	}
	public void setDisplayTypeKey(int displayTypeKey) {
		this.displayTypeKey = displayTypeKey;
	}
	@Column(name="display_type")
	public String getDisplayType() {
		return displayType;
	}
	public void setDisplayType(String displayType) {
		this.displayType = displayType;
	}
	
	

}
