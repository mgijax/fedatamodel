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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;

import mgi.frontend.datamodel.sort.SmartAlphaComparator;

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
	private List<ProbeMarkerAssociation> markerAssociations;
    private List<ProbePrimerPair> primerPairs;
    private List<ProbeRelative> relatives;
    private List<ProbeReferenceAssociation> referenceAssociations;
    private List<Reference> references;
    
    /*--- transient methods ---*/
    
    /* get the distinct list of sequences across all references, sorted smart-alpha by published ID
     * then by primary ID (for cases that they differ)
     */
    @Transient
    public List<ProbeSequence> getSequences() {
    	/* References may mention sequences by their primary ID or by a secondary ID.  We want to show
    	 * the unique set of sequence IDs across all references.  The sequenceID in the ProbeSequence
    	 * is the ID cited in the paper, while the primaryID in the sequence object is the actual primary
    	 * ID for the sequence.  So, we keep a list of the ProbeSequence objects for display, and we
    	 * track which ID/primaryID pairs we've already seen and added to the list.
    	 */
    	List<ProbeSequence> sequences = new ArrayList<ProbeSequence>();
    	Map<String,Set<String>> sequencesSeen = new HashMap<String,Set<String>>();
    	
    	for (ProbeReferenceAssociation ref : getReferenceAssociations()) {
    		if (ref.getProbeSequences() != null) {
    			for (ProbeSequence seq : ref.getProbeSequences()) {
    				String publishedID = seq.getSequenceID();
    				String primaryID = seq.getPrimaryID();
    				
    				if (!sequencesSeen.containsKey(publishedID)) {
    					sequences.add(seq);
    					sequencesSeen.put(publishedID, new HashSet<String>());
    					sequencesSeen.get(publishedID).add(primaryID);
    				} else if (!sequencesSeen.get(publishedID).contains(primaryID)) {
    					sequences.add(seq);
    					sequencesSeen.get(publishedID).add(primaryID);
    				}
    			}
    		}
    	}

    	if (sequences.size() == 0) {
    		return null;
    	}
    	
    	Collections.sort(sequences, sequences.get(0).getComparator());
    	return sequences;
    }
    
    /* get the distinct list of aliases (aka- synonyms) across all references, smart-alpha sorted
     */
    @Transient
    public List<String> getSynonyms() {
    	List<String> aliases = new ArrayList<String>();
    	for (ProbeReferenceAssociation pra : getReferenceAssociations()) {
    		if (pra.getHasAliases() != 0) {
    			for (ProbeAlias pa : pra.getAliases()) {
    				if (!aliases.contains(pa.getAlias())) {
    					aliases.add(pa.getAlias());
    				}
    			}
    		}
    	}
    	if (aliases.size() == 0) {
    		return null;
    	}
    	Collections.sort(aliases, new SmartAlphaComparator<String>());
    	return aliases;
    }
    
    /* get relatives of this probe that are either derived from it (1) or probes from which
     * it was derived (0).
     */
    @Transient
    private List<ProbeRelative> filterRelatives(int isChild) {
    	List<ProbeRelative> subset = new ArrayList<ProbeRelative>();
    	for (ProbeRelative pr : getRelatives()) {
    		if (pr.getIsChild() == isChild) {
    			subset.add(pr);
    		}
    	}
    	return subset;
    }
    
    /* a probe can only have one parent from which it was derived; find and return it
     * (or null, if not available)
     */
    @Transient
    public ProbeRelative getParentProbe() {
    	List<ProbeRelative> parents = filterRelatives(0);
    	if ((parents != null) && (parents.size() == 1)) {
    		return parents.get(0);
    	}
    	return null;
    }

    /* a probe can have many children (probes that are derived from it); find and return them
     */
    @Transient
    public List<ProbeRelative> getChildProbes() {
    	return filterRelatives(1);
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

    /* get the first set of primer pairs (or null, if none), as we assume only one per probe
     */
    @Transient
    private ProbePrimerPair getPrimerPair() {
    	// short-cut for performance; if the segment type isn't "primer", don't bother to look
    	// for primer pairs in the database.
    	if (!"primer".equals(segmentType)) {
    		return null;
    	}
    	List<ProbePrimerPair> pairs = this.getPrimerPairs();
    	if ((pairs != null) && (pairs.size() > 0)) {
    		return pairs.get(0);
    	}
    	return null;
    }

    @Transient
    public String getPrimerSequence1() {
    	ProbePrimerPair ppp = getPrimerPair();
    	if (ppp != null) {
    		return ppp.getPrimerSequence1();
    	}
    	return null;
    }

    @Transient
    public String getPrimerSequence2() {
    	ProbePrimerPair ppp = getPrimerPair();
    	if (ppp != null) {
    		return ppp.getPrimerSequence2();
    	}
    	return null;
    }

    // ================= Getters and Setters ===================== //

    @Column(name="age")
    public String getAge() {
		return age;
	}

	@OneToMany (targetEntity=ProbeRelative.class)
	@BatchSize(size=20)
	@JoinColumn(name="probe_key")
    @OrderBy("sequenceNum")
	public List<ProbeRelative> getRelatives() {
		return relatives;
	}

	@OneToMany (targetEntity=ProbeReferenceAssociation.class)
	@BatchSize(size=300)
	@JoinColumn(name="probe_key")
	public List<ProbeReferenceAssociation> getReferenceAssociations() {
		return referenceAssociations;
	}

	@OneToMany
	@JoinTable (name="probe_to_reference",
		joinColumns=@JoinColumn(name="probe_key"),
		inverseJoinColumns=@JoinColumn(name="reference_key") )
	@BatchSize(size=300)
	@OrderBy("year, jnumNumeric")
	public List<Reference> getReferences() {
		return references;
	}

	@OneToMany (targetEntity=ProbeMarkerAssociation.class)
	@BatchSize(size=200)
	@JoinColumn(name="probe_key")
    public List<ProbeMarkerAssociation> getMarkerAssociations() {
		return markerAssociations;
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

	@OneToMany (targetEntity=ProbePrimerPair.class)
    @JoinColumn (name="probe_key")
    public List<ProbePrimerPair> getPrimerPairs() {
		return primerPairs;
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

	public void setPrimerPairs(List<ProbePrimerPair> primerPairs) {
		this.primerPairs = primerPairs;
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

	public void setRelatives(List<ProbeRelative> relatives) {
		this.relatives = relatives;
	}

	public void setMarkerAssociations(List<ProbeMarkerAssociation> markerAssociations) {
		this.markerAssociations = markerAssociations;
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

	public void setReferenceAssociations(List<ProbeReferenceAssociation> referenceAssociations) {
		this.referenceAssociations = referenceAssociations;
	}

	public void setReferences(List<Reference> references) {
		this.references = references;
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
