package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Object representing probe notes
 * This inherits almost all of its methods from the note object.
 */
@Entity
@Table (name="probe_note")
public class ProbeNote extends Note {
	protected Integer probeKey;

    // ================= Getters and Setters ===================== //
	
	@Column(name="probe_key")
	public Integer getProbeKey() {
		return probeKey;
	}

	public void setProbeKey(Integer probeKey) {
		this.probeKey = probeKey;
	}

	@Override
    public String toString() {
        return "ProbeNote [probeKey=" + probeKey + ", note=" + note
                + ", noteType=" + noteType + ", uniqueKey=" + uniqueKey + "]";
    }
}
