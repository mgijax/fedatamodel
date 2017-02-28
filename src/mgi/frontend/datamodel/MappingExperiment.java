package mgi.frontend.datamodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;

import mgi.frontend.datamodel.sort.SmartAlphaComparator;

/**
 * (Genetic) Mapping Experiment
 * Core mapping object.
 */
@Entity
@Table (name="mapping_experiment")
public class MappingExperiment {

    private int experimentKey;
    private String type;
    private String note;
    private Reference reference;
    private String referenceNote;
    private String chromosome;
    private String primaryID;
    private List<MappingMarker> markers;
    private List<MappingRIRC> rircData;
    private List<MappingCross> crossData;
    private List<MappingTable> tables;
    private List<MappingLink> links;
    private List<MappingHybrid> hybridData;

    /*--- transient methods ---*/
    
    // get the link with the specified type
    @Transient
    private MappingLink getLink(String linkType) {
    	for (MappingLink t : getLinks()) {
    		if (linkType.equals(t.getType())) {
    			return t;
    		}
    	}
    	return null;
    }
    
    // get the link for the Jac RH Panel (aka- from the "Lucy Load")
    @Transient
    public MappingLink getRhPanelLink() {
    	return getLink("RH Panel");
    }
    
    // get the data table with the specified name
    @Transient
    private MappingTable getTable(String tableType) {
    	for (MappingTable t : getTables()) {
    		if (tableType.equals(t.getTableType())) {
    			return t;
    		}
    	}
    	return null;
    }
    
    // get the table of matrix data for a HYBRID experiment
    @Transient
    public MappingTable getHybridMatrix() {
    	return getTable("HYBRID MATRIX");
    }

    // get the table of 2x2 data for an RI/RC experiment
    @Transient
    public MappingTable getRirc2x2() {
    	return getTable("RI 2x2");
    }

    // get the table of matrix data for an RI/RC experiment
    @Transient
    public MappingTable getRircMatrix() {
    	return getTable("RI MATRIX");
    }

    // get the table of statistics for an RI/RC experiment
    @Transient
    public MappingTable getRircStatistics() {
    	return getTable("RI STATISTICS");
    }

    // get the table of 2x2 data for an CROSS experiment
    @Transient
    public MappingTable getCross2x2() {
    	return getTable("CROSS 2x2");
    }

    // get the table of matrix data for an CROSS experiment
    @Transient
    public MappingTable getCrossMatrix() {
    	return getTable("CROSS MATRIX");
    }

    // get the table of statistics for an CROSS experiment
    @Transient
    public MappingTable getCrossStatistics() {
    	return getTable("CROSS STATISTICS");
    }

    // get reference note with selected tweaks made for HTML formatting
    @Transient
    public String getNoteHtml() {
    	return tweakHTML(getNote());
    }
    
    // get reference note with selected tweaks made for HTML formatting
    @Transient
    public String getReferenceNoteHtml() {
    	return tweakHTML(getReferenceNote());
    }
    
    // 1. convert any stray < or > into &lt; or &gt; notation
    // 2. convert <..> notation into superscripts
    // 3. convert newlines (\n) into HTML line breaks
    @Transient
    private String tweakHTML(String s) {
    	String t = s.replaceAll("<([^ <]+)>", "@@@sup:::$1@@@/sup:::");
    	t = t.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
    	t = t.replaceAll("@@@", "<").replaceAll(":::", ">");
    	return t.replaceAll("\n", "<br/>");
    }
    
    // return the extra CROSS data for this experiment
    @Transient
    public MappingCross getCross() {
    	List<MappingCross> r = getCrossData();
    	if ((r != null) && (r.size() > 0)) {
    		return r.get(0);
    	}
    	return null;
    }

    // return the extra HYBRID data for this experiment
    @Transient
    public MappingHybrid getHybrid() {
    	List<MappingHybrid> r = getHybridData();
    	if ((r != null) && (r.size() > 0)) {
    		return r.get(0);
    	}
    	return null;
    }

    // return the extra RI/RC data for this experiment
    @Transient
    public MappingRIRC getRirc() {
    	List<MappingRIRC> r = getRircData();
    	if ((r != null) && (r.size() > 0)) {
    		return r.get(0);
    	}
    	return null;
    }

    // ================= Getters and Setters ===================== //

    @Column(name="chromosome")
    public String getChromosome() {
		return chromosome;
	}

	@Id
    @Column(name="experiment_key")
	public int getExperimentKey() {
		return experimentKey;
	}

	@OneToMany (targetEntity=MappingLink.class)
    @JoinColumn(name="experiment_key")
    public List<MappingLink> getLinks() {
		return links;
	}

	@OneToMany (targetEntity=MappingMarker.class)
    @JoinColumn(name="experiment_key")
    @OrderBy("sequenceNum")
	public List<MappingMarker> getMarkers() {
		return markers;
	}

	@Column(name="experiment_note")
	public String getNote() {
		return note;
	}

    @Column(name="primary_id")
	public String getPrimaryID() {
		return primaryID;
	}

    @ManyToOne (targetEntity=Reference.class, fetch=FetchType.EAGER)
    @JoinColumn (name="reference_key")
	public Reference getReference() {
		return reference;
	}

	@Column(name="reference_note")
	public String getReferenceNote() {
		return referenceNote;
	}

	@OneToMany (targetEntity=MappingCross.class)
	@BatchSize(size=300)
	@JoinColumn(name="experiment_key")
    public List<MappingCross> getCrossData() {
		return crossData;
	}

	@OneToMany (targetEntity=MappingHybrid.class)
	@BatchSize(size=300)
	@JoinColumn(name="experiment_key")
    public List<MappingHybrid> getHybridData() {
		return hybridData;
	}

	@OneToMany (targetEntity=MappingRIRC.class)
	@BatchSize(size=300)
	@JoinColumn(name="experiment_key")
    public List<MappingRIRC> getRircData() {
		return rircData;
	}

    @OneToMany (targetEntity=MappingTable.class)
    @JoinColumn(name="experiment_key")
    @OrderBy("sequenceNum")
	public List<MappingTable> getTables() {
		return tables;
	}

    @Column(name="experiment_type")
	public String getType() {
		return type;
	}

	public void setChromosome(String chromosome) {
		this.chromosome = chromosome;
	}

	public void setExperimentKey(int experimentKey) {
		this.experimentKey = experimentKey;
	}
	public void setLinks(List<MappingLink> links) {
		this.links = links;
	}
	public void setMarkers(List<MappingMarker> markers) {
		this.markers = markers;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public void setPrimaryID(String primaryID) {
		this.primaryID = primaryID;
	}
	public void setReference(Reference reference) {
		this.reference = reference;
	}
	public void setReferenceNote(String referenceNote) {
		this.referenceNote = referenceNote;
	}
	public void setCrossData(List<MappingCross> crossData) {
		this.crossData = crossData;
	}
	public void setHybridData(List<MappingHybrid> hybridData) {
		this.hybridData = hybridData;
	}
	public void setRircData(List<MappingRIRC> rircData) {
		this.rircData = rircData;
	}
	public void setTables(List<MappingTable> tables) {
		this.tables = tables;
	}
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "MappingExperiment [experimentKey=" + experimentKey + ", type=" + type + ", note=" + note
				+ ", reference=" + reference + ", referenceNote=" + referenceNote + ", chromosome=" + chromosome
				+ ", primaryID=" + primaryID + "]";
	}
}
