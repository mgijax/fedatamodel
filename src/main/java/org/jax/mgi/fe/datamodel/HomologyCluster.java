package org.jax.mgi.fe.datamodel;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;

@Entity
@Table(name="homology_cluster")
@SecondaryTables (
	{ @SecondaryTable (name="homology_cluster_counts", pkJoinColumns = {
		@PrimaryKeyJoinColumn (name="cluster_key", referencedColumnName = "cluster_key") } )
	} )
public class HomologyCluster {
	
	private int clusterKey;
	private String primaryID;
	private String version;
	private String source;
	private String secondarySource;
	private Date date;	
	private List<OrganismOrtholog> orthologs;
	private Integer mouseMarkerCount;
	private Integer humanMarkerCount;
	private Integer ratMarkerCount;
	private Integer cattleMarkerCount;
	private Integer chimpMarkerCount;
	private Integer dogMarkerCount;
	private Integer monkeyMarkerCount;
	private Integer chickenMarkerCount;
	private Integer xenopusMarkerCount;
	private Integer zebrafishMarkerCount;
	private int hasComparativeGOGraph;
	
	@Id
	@Column(name="cluster_key")
	public int getClusterKey() {
		return clusterKey;
	}
	public void setClusterKey(int clusterKey) {
		this.clusterKey = clusterKey;
	}
	@Column(name="has_comparative_go_graph")
	public int getHasComparativeGOGraph() {
		return hasComparativeGOGraph;
	}
	public void setHasComparativeGOGraph(int hasComparativeGOGraph) {
		this.hasComparativeGOGraph = hasComparativeGOGraph;
	}
	@Column(table="homology_cluster_counts", name="mouse_marker_count")
	@JoinColumn(name="cluster_key")
	public Integer getMouseMarkerCount() {
		return mouseMarkerCount;
	}
	public void setMouseMarkerCount(Integer mouseMarkerCount) {
		this.mouseMarkerCount = mouseMarkerCount;
	}
	@Column(table="homology_cluster_counts", name="human_marker_count")
	@JoinColumn(name="cluster_key")
	public Integer getHumanMarkerCount() {
		return humanMarkerCount;
	}
	public void setHumanMarkerCount(Integer humanMarkerCount) {
		this.humanMarkerCount = humanMarkerCount;
	}
	@Column(table="homology_cluster_counts", name="rat_marker_count")
	@JoinColumn(name="cluster_key")
	public Integer getRatMarkerCount() {
		return ratMarkerCount;
	}
	public void setRatMarkerCount(Integer ratMarkerCount) {
		this.ratMarkerCount = ratMarkerCount;
	}
	@Column(table="homology_cluster_counts", name="cattle_marker_count")
	@JoinColumn(name="cluster_key")
	public Integer getCattleMarkerCount() {
		return cattleMarkerCount;
	}
	public void setCattleMarkerCount(Integer cattleMarkerCount) {
		this.cattleMarkerCount = cattleMarkerCount;
	}
	@Column(table="homology_cluster_counts", name="chimp_marker_count")
	@JoinColumn(name="cluster_key")
	public Integer getChimpMarkerCount() {
		return chimpMarkerCount;
	}
	public void setChimpMarkerCount(Integer chimpMarkerCount) {
		this.chimpMarkerCount = chimpMarkerCount;
	}
	@Column(table="homology_cluster_counts", name="dog_marker_count")
	@JoinColumn(name="cluster_key")
	public Integer getDogMarkerCount() {
		return dogMarkerCount;
	}
	public void setDogMarkerCount(Integer dogMarkerCount) {
		this.dogMarkerCount = dogMarkerCount;
	}
	@Column(table="homology_cluster_counts", name="monkey_marker_count")
	@JoinColumn(name="cluster_key")
	public Integer getMonkeyMarkerCount() {
		return monkeyMarkerCount;
	}
	public void setMonkeyMarkerCount(Integer monkeyMarkerCount) {
		this.monkeyMarkerCount = monkeyMarkerCount;
	}
	@Column(table="homology_cluster_counts", name="chicken_marker_count")
	@JoinColumn(name="cluster_key")
	public Integer getChickenMarkerCount() {
		return chickenMarkerCount;
	}
	public void setChickenMarkerCount(Integer chickenMarkerCount) {
		this.chickenMarkerCount = chickenMarkerCount;
	}
	@Column(table="homology_cluster_counts", name="xenopus_marker_count")
	@JoinColumn(name="cluster_key")
	public Integer getXenopusMarkerCount() {
		return xenopusMarkerCount;
	}
	public void setXenopusMarkerCount(Integer xenopusMarkerCount) {
		this.xenopusMarkerCount = xenopusMarkerCount;
	}
	@Column(table="homology_cluster_counts", name="zebrafish_marker_count")
	@JoinColumn(name="cluster_key")
	public Integer getZebrafishMarkerCount() {
		return zebrafishMarkerCount;
	}
	public void setZebrafishMarkerCount(Integer zebrafishMarkerCount) {
		this.zebrafishMarkerCount = zebrafishMarkerCount;
	}
	@Column(name="primary_id")
	public String getPrimaryID() {
		return primaryID;
	}
	public void setPrimaryID(String primaryID) {
		this.primaryID = primaryID;
	}
	@Column(name="version")
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	@Column(name="source")
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	@Column(name="secondary_source")
	public String getSecondarySource() {
		return secondarySource;
	}
	public void setSecondarySource(String secondarySource) {
		this.secondarySource = secondarySource;
	}
	@Column(name="cluster_date")
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@OneToMany (targetEntity=OrganismOrtholog.class)
	@JoinColumn(name="cluster_key")
	@BatchSize(size=100)
	@OrderBy("sequenceNum")
	public List<OrganismOrtholog> getOrthologs() {
		return orthologs;
	}
	public void setOrthologs(List<OrganismOrtholog> orthologs) {
		this.orthologs = orthologs;
	}

	/** get the OrganismOrtholog object specified by the given organism
	* name
	*/
	@Transient
	public OrganismOrtholog getOrganismOrtholog (String organism) {
		if (organism == null) { return null; }

		for (OrganismOrtholog oo: this.getOrthologs()) {
			if (organism.equals(oo.getOrganism())) {
				return oo;
			}
		}
		return null;
	}

	/* retrieves the representative protein sequence for each mouse,
	 * human, and rat marker in this HomologyCluster.
	 */
	@Transient
	public List<Sequence> getRepresentativeProteinSequences() {
	    ArrayList<Sequence> seqs = new ArrayList<Sequence>();
	    OrganismOrtholog oo;

	    ArrayList<String> organisms = new ArrayList<String>();
	    organisms.add("mouse");
	    organisms.add("human");
	    organisms.add("rat");

	    Iterator<String> orgIt = organisms.iterator();
	    String organism;

	    Iterator<Marker> mrkIt;
	    Marker mrk;
	    Sequence seq;

	    while (orgIt.hasNext()) { 
		organism = orgIt.next();
	    	oo = this.getOrganismOrtholog(organism);

		if (oo != null) {
		    mrkIt = oo.getMarkers().iterator();
		    while (mrkIt.hasNext()) {
			mrk = mrkIt.next();
			seq = mrk.getRepresentativePolypeptideSequence();
			if (seq != null) {
			    seqs.add(seq);
			}
		    }
		}
	    }

	    return seqs;
	}
}
