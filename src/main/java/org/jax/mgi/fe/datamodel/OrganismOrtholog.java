package org.jax.mgi.fe.datamodel;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;

@Entity
@Table(name="homology_cluster_organism")
public class OrganismOrtholog {
	
	private int clusterOrganismKey;
	private String organism;	
	private List<Marker> markers;
	private int sequenceNum;
	private HomologyCluster homologyCluster;
	
	@Id
	@Column(name="cluster_organism_key")
	public int getClusterOrganismKey() {
		return clusterOrganismKey;
	}
	public void setClusterOrganismKey(int clusterOrganismKey) {
		this.clusterOrganismKey = clusterOrganismKey;
	}	
	@Column(name="organism")
	public String getOrganism() {
		return organism;
	}
	public void setOrganism(String organism) {
		this.organism = organism;
	}
	
	@ManyToMany
	@JoinTable (name="homology_cluster_organism_to_marker",
			joinColumns=@JoinColumn(name="cluster_organism_key"),
			inverseJoinColumns=@JoinColumn(name="marker_key")
			)
	@BatchSize(size=100)
	public List<Marker> getMarkers() {
		return markers;
	}
	public void setMarkers(List<Marker> markers) {
		this.markers = markers;
	}
	@Column(name="sequence_num")
	public int getSequenceNum() {
		return sequenceNum;
	}
	public void setSequenceNum(int sequenceNum) {
		this.sequenceNum = sequenceNum;
	}
	
	@OneToOne (targetEntity=HomologyCluster.class, fetch=FetchType.LAZY)
	@JoinColumn (name="cluster_key")
	public HomologyCluster getHomologyCluster() {
		return this.homologyCluster;
	}

	public void setHomologyCluster (HomologyCluster homologyCluster) {
		this.homologyCluster = homologyCluster;
	}

	@Transient
	public int getMarkerCount() {
		List<Marker> markers = this.getMarkers();
		if (markers == null) { return 0; }
		return markers.size();
	}
}
