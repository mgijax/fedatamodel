package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * MarkerIncidentalMutation
 *
 * This object represents an incidental mutation link
 */
@Entity
@Table(name="marker_incidental_mut")
public class MarkerIncidentalMutation
{
	private int uniqueKey;
	private int markerKey;
	private String provider;


    // ================= Getters ======================================== //
	@Id
	@Column(name="unique_key")
	public int getUniqueKey() {
		return uniqueKey;
	}

	@Column(name="marker_key")
	public int getMarkerKey() {
		return markerKey;
	}

	@Column(name="provider")
	public String getProvider() {
		return provider;
	}

    // ================= Setters ======================================== //
	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	public void setMarkerKey(int markerKey) {
		this.markerKey = markerKey;
	}

	public void setProvider(String provider) {
		this.provider= provider;
	}

	@Override
	public String toString() {
		return provider;
	}
}
