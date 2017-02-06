package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ProbeMarkerAssociation
 * Associates a Marker with a Probe, including a qualifier (eg- putative)
 */

@Entity
@Table (name="marker_to_probe")
public class ProbeMarkerAssociation {

    protected Marker marker;
    protected Probe probe;
    protected int uniqueKey;
    protected String qualifier;

    // ================= Getters and Setters ===================== //

	/** Return the actual marker for this relationship.
     */
    @ManyToOne (targetEntity=Marker.class, fetch=FetchType.LAZY)
    @JoinColumn (name="marker_key")
    public Marker getMarker() {
    	return marker;
    }
	
	/** Return the actual allele object for this relationship
     */
    @ManyToOne (targetEntity=Probe.class, fetch=FetchType.LAZY)
    @JoinColumn (name="probe_key")
    public Probe getProbe() {
    	return probe;
    }

	public String getQualifier() {
		return qualifier;
	}

	@Id
	@Column(name="unique_key")
	public int getUniqueKey() {
		return uniqueKey;
	}

    public void setMarker(Marker marker) {
    	this.marker = marker;
    }

    public void setProbe(Probe probe) {
    	this.probe = probe;
    }

    public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}

    public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

    @Override
    public String toString() {
    	return "ProbeMarkerAssociation ["
    		+ (marker != null ? "marker=" + marker + ", " : "")
    		+ (probe != null ? "probe=" + probe : "") + "]";
    }
}
