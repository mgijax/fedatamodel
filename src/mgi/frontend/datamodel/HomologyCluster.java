package mgi.frontend.datamodel;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

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
	@Column(name="cluster_date")
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@OneToMany (targetEntity=OrganismOrtholog.class)
	@JoinColumn(name="cluster_key")
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
}
