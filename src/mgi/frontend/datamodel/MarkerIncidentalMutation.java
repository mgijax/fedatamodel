package mgi.frontend.datamodel;

import java.util.*;
import javax.persistence.*;

import mgi.frontend.datamodel.util.DatamodelUtils;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
	private String filename;


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

	@Column(name="filename")
	public String getFilename() {
		return filename;
	}
	
    // ================= Setters ======================================== //
	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	public void setMarkerKey(int markerKey) {
		this.markerKey = markerKey;
	}

	public void setFilename(String filename) {
		this.filename= filename;
	}

	@Override
	public String toString() {
		return filename;
	}
}
