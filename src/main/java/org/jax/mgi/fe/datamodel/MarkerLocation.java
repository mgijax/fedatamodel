package org.jax.mgi.fe.datamodel;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * MarkerLocation   
 * @author mhall
 * This class extends the Location class, so almost all of the location
 * methods come from it.
 */
@SuppressWarnings("serial")
@Entity
@Table (name="marker_location")
public class MarkerLocation extends Location implements Serializable
{
	
    protected Integer markerKey;
    protected Float cmOffset;
    private int uniqueKey;
    protected String cytogeneticOffset;
	
    // ================= Getters and Setters ===================== //
	
    @Column(name="cm_offset")
    public Float getCmOffset() {
        return cmOffset;
    }

    @Column(name = "cytogenetic_offset")
    public String getCytogeneticOffset() {
        return cytogeneticOffset;
    }

    @Column(name = "marker_key")
    public Integer getMarkerKey() {
        return markerKey;
    }

    @Id
    @Column(name="unique_key")
    public int getUniqueKey() {
        return uniqueKey;
    }
	
    public void setCmOffset(Float cmOffset) {
        this.cmOffset = cmOffset;
    }    
    
    public void setCytogeneticOffset(String cytogeneticOffset) {
        this.cytogeneticOffset = cytogeneticOffset;
    }

    public void setMarkerKey(Integer markerKey) {
        this.markerKey = markerKey;
    }

    public void setUniqueKey(int uniqueKey) {
        this.uniqueKey = uniqueKey;
    }	
	
    @Override
    public String toString() {
        return "MarkerLocation ["
                + (markerKey != null ? "markerKey=" + markerKey + ", " : "")
                + (buildIdentifier != null ? "buildIdentifier="
                + buildIdentifier + ", " : "")
                + (chromosome != null ? "chromosome=" + chromosome + ", " : "")
                + ", "
                + "endCoordinate="
                + endCoordinate
                + ", "
                + (locationType != null ? "locationType=" + locationType + ", "
                                : "")
                + (mapUnits != null ? "mapUnits=" + mapUnits + ", " : "")
                + (provider != null ? "provider=" + provider + ", " : "")
                + "sequenceNum=" + sequenceNum + ", startCoordinate="
                + startCoordinate + ", uniqueKey=" + uniqueKey + "]";
	}
}
