package org.jax.mgi.fe.datamodel.hdp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Base object for grid clusters in the HDP grid.
 */

@Entity
@Table(name="hdp_gridcluster_marker")
public class HdpGridClusterMarker {

    private int uniqueKey;
    private int gridClusterKey;
    private int markerKey;
    private String symbol;


    // ================= Getters and Setters ===================== //

    @Id
    @Column(name="unique_key")
    public int getUniqueKey() {
		return uniqueKey;
	}
	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

    @Column(name="hdp_gridcluster_key")
    public int getGridClusterKey() {
		return gridClusterKey;
	}
	public void setGridClusterKey(int gridClusterKey) {
		this.gridClusterKey = gridClusterKey;
	}

	@Column(name="marker_key")
    public int getMarkerKey() {
		return markerKey;
	}
	public void setMarkerKey(int markerKey) {
		this.markerKey = markerKey;
	}
	
	@Column(name="symbol")
    public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }




    // ================= Convenience ================= //

    @Override
    public String toString() {
	StringBuffer sb = new StringBuffer();
	sb.append ("gridClusterKey (");
	sb.append (this.gridClusterKey);
	sb.append (")");
	return sb.toString();
    }
}
