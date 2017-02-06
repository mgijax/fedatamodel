package mgi.frontend.datamodel;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Probe
 * @author mhall, jsb
 * Core Probe object.
 */
@Entity
@Table (name="Probe")
public class Probe {

    private String cloneID;
    private String logicalDB;
    private String name;
    private String primaryID;
    private int probeKey;
    private String segmentType;
    private String organism;
    private String age;
    private String sex;
    private String cellLine;
    private String vector;
    private String insertSite;
    private String insertSize;
    private String productSize;
    private String library;
    private String tissue;
    private String regionCovered;
    private String strain;
    private Integer expressionResultCount;
    private List<ProbeCloneCollection> probeCloneCollection;
    private List<ProbeID> ids;
    private List<ProbeNote> notes;
    
    /* need to figure out how to pull in ProbePrimerPair, which may or may not be null.
     */

    @Transient
    public List<String> getSynonyms() {
    	return null;
    }

    @Transient
    public List<ProbeID> getSecondaryIds() {
    	List<ProbeID> secIDs = new ArrayList<ProbeID>();
    	for (ProbeID p : this.getIds()) {
    		boolean preferred = p.isPreferred();
    		String logicalDB = p.getLogicalDB();

    		if ((!"MGI".equals(logicalDB)) && (!preferred)) {
    			secIDs.add(p);
    		}
    	}
    	return secIDs;
    }
    
    @Transient
    public String getProbeNote() {
    	return getNoteByType("probe");
    }

    @Transient
    public String getTissueNote() {
    	return getNoteByType("tissue");
    }

    @Transient
    private String getNoteByType(String noteType) {
    	if (noteType == null) { return null; }
    	
    	for (ProbeNote pn : this.getNotes()) {
    		if (noteType.equals(pn.getNoteType())) {
    			return pn.getNote();
    		}
    	}
    	return null;
    }

    // ================= Getters and Setters ===================== //

    @Column(name="age")
    public String getAge() {
		return age;
	}

    @Column(name="cell_line")
    public String getCellLine() {
		return cellLine;
	}

	@Column(name="clone_id")
    public String getCloneID() {
        return cloneID;
    }

	@Column(name="expression_result_count")
    public Integer getExpressionResultCount() {
		return expressionResultCount;
	}

	@OneToMany (targetEntity=ProbeID.class)
    @JoinColumn (name="probe_key")
    @OrderBy("accID")
    public List<ProbeID> getIds() {
		return ids;
	}

	@Column(name="insert_site")
    public String getInsertSite() {
		return insertSite;
	}

    @Column(name="insert_size")
    public String getInsertSize() {
		return insertSize;
	}

    @Column(name="library")
    public String getLibrary() {
		return library;
	}

    @Column(name="logical_db")
    public String getLogicalDB() {
        return logicalDB;
    }

    @Column(name="name")
    public String getName() {
        return name;
    }

    @OneToMany (targetEntity=ProbeNote.class)
    @JoinColumn(name="probe_key")
    public List<ProbeNote> getNotes() {
		return notes;
	}

    @Column(name="organism")
    public String getOrganism() {
		return organism;
	}

    @Column(name="primary_id")
    public String getPrimaryID() {
        return primaryID;
    }

    @OneToMany (targetEntity=ProbeCloneCollection.class)
    @JoinColumn(name="probe_key")
    @OrderBy("collection")
    public List<ProbeCloneCollection> getProbeCloneCollection() {
        return probeCloneCollection;
    }

    @Id
    @Column(name="probe_key")
    public int getProbeKey() {
        return probeKey;
    }

    @Column(name="product_size")
    public String getProductSize() {
		return productSize;
	}

    @Column(name="region_covered")
	public String getRegionCovered() {
		return regionCovered;
	}

    @Column(name="segment_type")
    public String getSegmentType() {
        return segmentType;
    }

    @Column(name="sex")
	public String getSex() {
		return sex;
	}

	@Column(name="strain")
	public String getStrain() {
		return strain;
	}

	@Column(name="tissue")
	public String getTissue() {
		return tissue;
	}

	@Column(name="vector")
	public String getVector() {
		return vector;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public void setCellLine(String cellLine) {
		this.cellLine = cellLine;
	}

	public void setCloneID(String cloneid) {
        this.cloneID = cloneid;
    }

	public void setExpressionResultCount(Integer expressionResultCount) {
		this.expressionResultCount = expressionResultCount;
	}

	public void setIds(List<ProbeID> ids) {
		this.ids = ids;
	}

	public void setInsertSite(String insertSite) {
		this.insertSite = insertSite;
	}

	public void setInsertSize(String insertSize) {
		this.insertSize = insertSize;
	}

	public void setLibrary(String library) {
		this.library = library;
	}

	public void setLogicalDB(String logicaldb) {
        this.logicalDB = logicaldb;
    }

	public void setName(String name) {
        this.name = name;
    }

	public void setNotes(List<ProbeNote> notes) {
		this.notes = notes;
	}

	public void setOrganism(String organism) {
		this.organism = organism;
	}

	public void setPrimaryID(String primaryid) {
        this.primaryID = primaryid;
    }

	public void setProbeCloneCollection(List<ProbeCloneCollection> probeCloneCollection) {
        this.probeCloneCollection = probeCloneCollection;
    }

	public void setProbeKey(int probeKey) {
        this.probeKey = probeKey;
    }

	public void setProductSize(String productSize) {
		this.productSize = productSize;
	}

	public void setRegionCovered(String regionCovered) {
		this.regionCovered = regionCovered;
	}

	public void setSegmentType(String segmenttype) {
        this.segmentType = segmenttype;
    }

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setStrain(String strain) {
		this.strain = strain;
	}

	public void setTissue(String tissue) {
		this.tissue = tissue;
	}

	public void setVector(String vector) {
		this.vector = vector;
	}
}
