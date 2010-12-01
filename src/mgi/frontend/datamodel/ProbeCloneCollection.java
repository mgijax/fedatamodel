package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ProbeCloneCollection
 * @author mhall
 * Returns a collection of probes and clones.
 */
@Entity
@Table (name="probe_clone_collection")
public class ProbeCloneCollection {

    private String collection;
    private int probeKey;
	private int uniqueKey;
    
    // ================= Getters and Setters ===================== //
    
    public String getCollection() {
        return collection;
    }

    @Column(name="probe_key")
    public int getProbeKey() {
        return probeKey;
    }

	@Id
	@Column(name="unique_key")
	public int getUniqueKey() {
		return uniqueKey;
	}

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public void setProbeKey(int probeKey) {
        this.probeKey = probeKey;
    }

    public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}
}
