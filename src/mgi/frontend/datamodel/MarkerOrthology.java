package mgi.frontend.datamodel;

import javax.persistence.*;

@Entity
@Table(name="marker_orthology")
public class MarkerOrthology {
	private int uniqueKey;
	private int mouseMarkerKey;
	private int otherMarkerKey;
	private String otherSymbol;
	private String otherOrganism;
	
	public MarkerOrthology() {}

	@Id
	@Column(name="unique_key")
	public int getUniqueKey() {
		return uniqueKey;
	}

	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	@Column(name="mouse_marker_key")
	public int getMouseMarkerKey() {
		return mouseMarkerKey;
	}

	public void setMouseMarkerKey(int mouseMarkerKey) {
		this.mouseMarkerKey = mouseMarkerKey;
	}

	@Column(name="other_marker_key")
	public int getOtherMarkerKey() {
		return otherMarkerKey;
	}

	public void setOtherMarkerKey(int otherMarkerKey) {
		this.otherMarkerKey = otherMarkerKey;
	}

	@Column(name="other_symbol")
	public String getOtherSymbol() {
		return otherSymbol;
	}

	public void setOtherSymbol(String otherSymbol) {
		this.otherSymbol = otherSymbol;
	}

	@Column(name="other_organism")
	public String getOtherOrganism() {
		return otherOrganism;
	}

	public void setOtherOrganism(String otherOrganism) {
		this.otherOrganism = otherOrganism;
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
