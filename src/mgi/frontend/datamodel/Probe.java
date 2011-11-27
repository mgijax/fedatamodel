package mgi.frontend.datamodel;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Probe
 * @author mhall, jsb
 * Core Probe object.
 */
@Entity
@Table (name="Probe")
public class Probe {

    private String cloneid;
    private String logicaldb;
    private String name;
    private String primaryid;
    private Set<ProbeCloneCollection> probeCloneCollection;
    private int probeKey;
    private String segmenttype;

    // ================= Getters and Setters ===================== //

    @Column(name="clone_id")
    public String getCloneid() {
        return cloneid;
    }

    @Column(name="logical_db")
    public String getLogicaldb() {
        return logicaldb;
    }

    public String getName() {
        return name;
    }

    @Column(name="primary_id")
    public String getPrimaryid() {
        return primaryid;
    }

    @OneToMany (targetEntity=ProbeCloneCollection.class)
    @JoinColumn(name="probe_key")
    public Set<ProbeCloneCollection> getProbeCloneCollection() {
        return probeCloneCollection;
    }

    @Id
    @Column(name="probe_key")
    public int getProbeKey() {
        return probeKey;
    }

    @Column(name="segment_type")
    public String getSegmenttype() {
        return segmenttype;
    }

    public void setCloneid(String cloneid) {
        this.cloneid = cloneid;
    }

    public void setLogicaldb(String logicaldb) {
        this.logicaldb = logicaldb;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrimaryid(String primaryid) {
        this.primaryid = primaryid;
    }

    public void setProbeCloneCollection(Set<ProbeCloneCollection> probeCloneCollection) {
        this.probeCloneCollection = probeCloneCollection;
    }

    public void setProbeKey(int probeKey) {
        this.probeKey = probeKey;
    }

    public void setSegmenttype(String segmenttype) {
        this.segmenttype = segmenttype;
    }

}
