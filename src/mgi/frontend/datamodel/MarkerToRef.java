package mgi.frontend.datamodel;

import javax.persistence.*;

@Entity
@Table (name="markerToReference")
public class MarkerToRef {

    @Id
    protected String uniqueKey;
    protected String markerKey;
    protected String referenceKey;

	public String getMarkerKey() {
        return markerKey;
    }



    public void setMarkerKey(String markerKey) {
        this.markerKey = markerKey;
    }



    public String getReferenceKey() {
        return referenceKey;
    }



    public void setReferenceKey(String referenceKey) {
        this.referenceKey = referenceKey;
    }

	
}
