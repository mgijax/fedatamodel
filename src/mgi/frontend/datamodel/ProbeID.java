package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
/**
 * Is: a single probe id
 * Notes: This extends the accession id object, and inherits almost all of its methods from it.
 *		Copied and customized from AlleleID class.
 */
@Entity
@Table (name="probe_id")
public class ProbeID extends AccessionID {
	private int probeKey;

    // ================= Getters and Setters ===================== //
	
	@Column(name="probe_key")
	public int getProbeKey() {
		return probeKey;
	}

	public void setProbeKey(int probeKey) {
		this.probeKey = probeKey;
	}

	@Override
    public String toString() {
        return "ProbeID [probeKey=" + probeKey + ", accID=" + accID
                + ", isPreferred=" + isPreferred + ", isPrivate=" + isPrivate
                + ", logicalDB=" + logicalDB + ", uniqueKey=" + uniqueKey + "]";
    }
}
