package mgi.frontend.datamodel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="probeCloneCollection")
public class ProbeCloneCollection implements SortableObject {

    private int probeKey;
    private String collection;
    
    @Id
    public int getProbeKey() {
        return probeKey;
    }

    public void setProbeKey(int probeKey) {
        this.probeKey = probeKey;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    @Override
    public Comparable getComparableValue(String fieldname)
            throws NoSuchFieldException {
        // TODO Auto-generated method stub
        return null;
    }

}
