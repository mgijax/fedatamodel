package mgi.frontend.datamodel;

import javax.persistence.*;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * MarkerLink
 * @author jsb
 * This object represents one pre-computed link for a marker.
 */
@Entity
@Table(name="marker_link")
public class MarkerLink {

	private int uniqueKey;
	private int markerKey;
	private String linkGroup;
	private int sequenceNum;
	private String associatedID;
	private String displayText;
	private String url;
	private int hasMarkups;
	private int useNewWindow;

    // ================= Getters and Setters ===================== //

	@Column(name="associated_id")
	public String getAssociatedID() {
		return associatedID;
	}

	@Column(name="display_text")
	public String getDisplayText() {
		return displayText;
	}

	@Column(name="has_markups")
	public int getHasMarkups() {
		return hasMarkups;
	}

	@Column(name="link_group")
	public String getLinkGroup() {
		return linkGroup;
	}

	@Column(name="marker_key")
	public int getMarkerKey() {
		return markerKey;
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

	@Column(name="url")
	public String getUrl() {
		return url;
	}

	@Column(name="use_new_window")
	public int getUseNewWindow() {
		return useNewWindow;
	}

	public void setAssociatedID(String associatedID) {
		this.associatedID = associatedID;
	}

	public void setDisplayText(String displayText) {
		this.displayText = displayText;
	}

	public void setHasMarkups(int hasMarkups) {
		this.hasMarkups = hasMarkups;
	}

	public void setLinkGroup(String linkGroup) {
		this.linkGroup = linkGroup;
	}

	public void setMarkerKey(int markerKey) {
		this.markerKey = markerKey;
	}

	public void setSequenceNum(int sequenceNum) {
		this.sequenceNum = sequenceNum;
	}

	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setUseNewWindow(int useNewWindow) {
		this.useNewWindow = useNewWindow;
	}

	@Override
	public String toString() {
		return "MarkerLink [markerKey="
				+ markerKey
				+ ", uniqueKey="
				+ uniqueKey
				+ ", "
				+ (linkGroup != null ? "linkGroup="
					+ linkGroup + ", " : "")
				+ (url != null ? "url="
					+ url + ", " : "")
				+ (displayText != null ? "displayText="
					+ displayText + ", " : "")
				+ "useNewWindow=" + useNewWindow + "]";
	}
}
