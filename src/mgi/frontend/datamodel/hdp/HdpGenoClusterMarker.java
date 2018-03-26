package mgi.frontend.datamodel.hdp;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import mgi.frontend.datamodel.Genotype;
import mgi.frontend.datamodel.Marker;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Where;

/**
 * Represents a marker associated with a genocluster in HMDC
 */

@Entity
@Table(name="hdp_genocluster_marker")
public class HdpGenoClusterMarker {

    private int uniqueKey;
    private int genoClusterKey;
    private String symbol;
    private String primaryID;
    private String markerType;
    private Marker marker;

    // ================= Getters and Setters ===================== //

    @Column(name="hdp_genocluster_key")
    public int getGenoClusterKey() {
		return genoClusterKey;
	}

    @ManyToOne (targetEntity=Marker.class, fetch=FetchType.LAZY)
	@JoinColumn (name="marker_key")
	public Marker getMarker() {
		return marker;
	}

    @Column(name="marker_type")
	public String getMarkerType() {
		return markerType;
	}

    @Column(name="primary_id")
	public String getPrimaryID() {
		return primaryID;
	}

    @Column(name="symbol")
	public String getSymbol() {
		return symbol;
	}

	@Id
    @Column(name="unique_key")
	public int getUniqueKey() {
		return uniqueKey;
	}

	public void setGenoClusterKey(int genoClusterKey) {
		this.genoClusterKey = genoClusterKey;
	}
	
	public void setMarker(Marker marker) {
		this.marker=marker;
	}
	
	public void setMarkerType(String markerType) {
		this.markerType = markerType;
	}

	public void setPrimaryID(String primaryID) {
		this.primaryID = primaryID;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	@Override
	public String toString() {
		return "HdpGenoClusterMarker [uniqueKey=" + uniqueKey + ", genoClusterKey=" + genoClusterKey + ", symbol="
				+ symbol + ", primaryID=" + primaryID + ", markerType=" + markerType + "]";
	}
}
