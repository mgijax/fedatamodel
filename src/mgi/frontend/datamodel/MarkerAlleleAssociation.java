package mgi.frontend.datamodel;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * MarkerAlleleAssociation
 * @author jsb
 * Associates a Marker with an Allele
 */

@Entity
@Table (name="marker_to_allele")
public class MarkerAlleleAssociation {

    protected Marker marker;
    protected Allele allele;

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
    @ManyToOne (targetEntity=Allele.class, fetch=FetchType.LAZY)
    @JoinColumn (name="allele_key")
    public Allele getAllele() {
    	return allele;
    }

    public void setMarker(Marker marker) {
    	this.marker = marker;
    }

    public void setAllele(Allele allele) {
    	this.allele = allele;
    }

    @Override
    public String toString() {
    	return "MarkerAlleleAssociation ["
    		+ (marker != null ? "marker=" + marker + ", " : "")
    		+ (allele != null ? "allele=" + allele : "") + "]";
    }
}
