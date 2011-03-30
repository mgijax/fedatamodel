package mgi.frontend.datamodel;

import javax.persistence.*;

/**
 * MarkerAlias
 * @author jsb
 * Certain markers are aliases for other markers.  One of these objects
 * represents an alias for a marker, containing those fields we need to
 * display and generate a link. 
 */
@Entity
@Table(name="marker_alias")
public class MarkerAlias {
    
	private int uniqueKey;
	private int markerKey;
	private int aliasKey;
	private String aliasSymbol;
	private String aliasID;
	
    // ================= Getters and Setters ===================== //

	@Column(name="alias_id")
	public String getAliasID() {
		return aliasID;
	}
	
	@Column(name="alias_key")
	public int getAliasKey() {
		return aliasKey;
	}
	
	@Column(name="alias_symbol")
	public String getAliasSymbol() {
		return aliasSymbol;
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
	
	public void setAliasID(String aliasID) {
		this.aliasID = aliasID;
	}
	
	public void setAliasKey(int aliasKey) {
		this.aliasKey = aliasKey;
	}
	
	public void setAliasSymbol(String aliasSymbol) {
		this.aliasSymbol = aliasSymbol;
	}
	
	public void setMarkerKey(int markerKey) {
		this.markerKey = markerKey;
	}
	
	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}
	
	@Override
	public String toString() {
		return "MarkerAlias [uniqueKey=" + uniqueKey + ", markerKey="
				+ markerKey + ", aliasKey=" + aliasKey + ", aliasSymbol="
				+ aliasSymbol + ", aliasID=" + aliasID + "]";
	}
}
