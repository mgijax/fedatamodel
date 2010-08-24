package mgi.frontend.datamodel;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name="Probe")
public class Probe implements SortableObject {

    private int probeKey;
    private String name;
    private String segmenttype;
    private String primaryid;
    private String logicaldb;
    private String cloneid;
    private Set<ProbeCloneCollection> probeCloneCollection;
    
    @Id
    public int getProbeKey() {
        return probeKey;
    }

    public void setProbeKey(int probeKey) {
        this.probeKey = probeKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSegmenttype() {
        return segmenttype;
    }

    public void setSegmenttype(String segmenttype) {
        this.segmenttype = segmenttype;
    }

    public String getPrimaryid() {
        return primaryid;
    }

    public void setPrimaryid(String primaryid) {
        this.primaryid = primaryid;
    }

    public String getLogicaldb() {
        return logicaldb;
    }

    public void setLogicaldb(String logicaldb) {
        this.logicaldb = logicaldb;
    }

    public String getCloneid() {
        return cloneid;
    }

    public void setCloneid(String cloneid) {
        this.cloneid = cloneid;
    }

    @OneToMany (targetEntity=ProbeCloneCollection.class)
    @JoinColumn(name="probeKey")
    public Set<ProbeCloneCollection> getProbeCloneCollection() {
        return probeCloneCollection;
    }

    public void setProbeCloneCollection(Set<ProbeCloneCollection> probeCloneCollection) {
        this.probeCloneCollection = probeCloneCollection;
    }    
    
    @Override
    public Comparable getComparableValue(String fieldname)
            throws NoSuchFieldException {
        // TODO Auto-generated method stub
        return null;
    }

}
