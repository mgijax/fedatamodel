package mgi.frontend.datamodel;

import javax.persistence.*;

/**
 * MarkerOrthology
 * @author mhall
 * This object encapsulates mouse marker orthologs. 
 */
@Entity
@Table(name="marker_orthology")
public class MarkerOrthology {
    
	private int mouseMarkerKey;
	private int otherMarkerKey;
	private String otherOrganism;
	private String otherSymbol;
	private int uniqueKey;
	
    // ================= Getters and Setters ===================== //

	@Column(name="mouse_marker_key")
	public int getMouseMarkerKey() {
		return mouseMarkerKey;
	}

	@Column(name="other_marker_key")
	public int getOtherMarkerKey() {
		return otherMarkerKey;
	}

	@Column(name="other_organism")
	public String getOtherOrganism() {
		return otherOrganism;
	}

	@Column(name="other_symbol")
	public String getOtherSymbol() {
		return otherSymbol;
	}

	@Id
	@Column(name="unique_key")
	public int getUniqueKey() {
		return uniqueKey;
	}

	public void setMouseMarkerKey(int mouseMarkerKey) {
		this.mouseMarkerKey = mouseMarkerKey;
	}

	public void setOtherMarkerKey(int otherMarkerKey) {
		this.otherMarkerKey = otherMarkerKey;
	}

	public void setOtherOrganism(String otherOrganism) {
		this.otherOrganism = otherOrganism;
	}

	public void setOtherSymbol(String otherSymbol) {
		this.otherSymbol = otherSymbol;
	}

	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	@Override
	public String toString() {
		return "MarkerOrthology [mouseMarkerKey="
				+ mouseMarkerKey
				+ ", otherMarkerKey="
				+ otherMarkerKey
				+ ", "
				+ (otherOrganism != null ? "otherOrganism=" + otherOrganism
						+ ", " : "")
				+ (otherSymbol != null ? "otherSymbol=" + otherSymbol + ", "
						: "") + "uniqueKey=" + uniqueKey + "]";
	}
}
