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
    
    // ================= Getters and Setters ===================== //
    
    public String getCollection() {
        return collection;
    }

    @Id
    @Column(name="probe_key")
    public int getProbeKey() {
        return probeKey;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public void setProbeKey(int probeKey) {
        this.probeKey = probeKey;
    }

}
