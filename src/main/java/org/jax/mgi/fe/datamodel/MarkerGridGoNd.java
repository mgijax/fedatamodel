
package org.jax.mgi.fe.datamodel;

import javax.persistence.Transient;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;

/**
 * MarkerGridGoNd
 * @author jer
 * Each object indicates whether the marker does or does not have a ND annotation and no other annotations for a given GO dag.
 * Note that this object only exists if the marker has at least one annotation to the dag. If the marker has no annotations
 * to the dag at all, there is no MarkerGridGoNd object.
 */
@Entity
@Table (name="marker_grid_go_nd")
public class MarkerGridGoNd {
    private int goNdKey;
    private int markerKey;
    private String goDag;
    private boolean isNd;

    // ================= Getters and Setters ===================== //
	
    @Id
    @Column(name="go_nd_key")
    public int getGoNdKey() {
	return goNdKey;
    }

    public void setGoNdKey(int goNdKey) {
	this.goNdKey = goNdKey;
    }

    @Column(name="marker_key")
    public int getMarkerKey() {
	return markerKey;
    }

    public void setMarkerKey(int markerKey) {
	this.markerKey = markerKey;
    }

    @Column(name="go_dag")
    public String getGoDag() {
	return goDag;
    }

    public void setGoDag(String goDag) {
	this.goDag = goDag;
    }

    @Column(name="is_nd")
    public boolean getIsNd() {
	return isNd;
    }

    public void setIsNd(boolean isNd) {
	this.isNd = isNd;
    }

    @Override
    public String toString() {
	return "MarkerGridGoNd [goNdKey=" + goNdKey + ", "
		+ "markerKey=" + markerKey + ", "
		+ "goDag=" + goDag + ", "
		+ "isNd=" + isNd + " ]";
    }
}
