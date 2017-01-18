package mgi.frontend.datamodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
@Entity
@FilterDef(name="termDiseaseModelExcludeNots")
@Table(name="term")
public class VocabTerm implements Serializable{
	
	// something about joining on non-primary keys is making it require serializable interface...
	private static final long serialVersionUID = 1L;
	
	private int termKey;
	private String term;
	private String vocabName;
	private String displayVocabName;
	private String primaryId;
	private int isObsolete;
	private int isRoot;
	private int isLeaf;
	private Integer sequenceNum;
	private String definition;
	private List<Marker> associatedMarkers;
	private List<VocabTermSynonym> synonyms;
	private List<VocabTermID> secondaryIds;
	private List<VocabTermAncestor> ancestors;
	private VocabTermEmapInfo emapInfo;
	private List<VocabTerm> emapsChildren;
	private List<VocabTerm> children;
	private List<VocabChild> parentEdges;
	
	/* Vocab specific optional associations */
	// For vocabName=OMIM
	private List<DiseaseModel> diseaseModels;
	
	/* Convenience methods */

	// wrapper to standardize capitalization with other objects' methods
	@Transient
	public String getPrimaryID() {
		return this.getPrimaryId();
	}

	@Transient
	public String getStageRange() {
		VocabTermEmapInfo emapInfo = this.getEmapInfo();
		if (emapInfo == null) { return ""; }

		StringBuffer sb = new StringBuffer();
		sb.append("Theiler Stage");

		Integer startStage = emapInfo.getStartStage();
		Integer endStage = emapInfo.getEndStage();

		if (startStage == null) {
		    // is an EMAPS term, so no start & end stage
		    startStage = emapInfo.getStage();
		    sb.append(" ");
		    sb.append(startStage.toString());
		} else if (startStage.equals(endStage)) {
		    // is an EMAPA term for only one stage
		    sb.append(" ");
		    sb.append(startStage.toString());
		} else {
		    // is an EMAPA term covering more than one stage
		    sb.append("s ");
		    sb.append(startStage.toString());
		    sb.append("-");
		    sb.append(endStage.toString());
		}
		return sb.toString();
	}

	@Transient
	public VocabTerm getDefaultParent() {
		VocabTermEmapInfo emapInfo = this.getEmapInfo();
		if (emapInfo != null) {
			return emapInfo.getDefaultParent();
		}
		return null;
	}

	@Transient
	private boolean isFromVocabulary (String vocab) {
		if (vocabName != null) {
			if (vocabName.equals(vocab)) {
				return true;
			}
		} else if (vocab == null) {
			return true;
		}
		return false;
	}

	@Transient
	public boolean getIsEmapa() {
		return this.isFromVocabulary("EMAPA");
	}

	@Transient
	public boolean getIsEmaps() {
		return this.isFromVocabulary("EMAPS");
	}

	/* Getters */
	@Id
	@Column(name="term_key")
	public int getTermKey() {
		return termKey;
	}
	
	@Column(name="term")
	public String getTerm() {
		return term;
	}
	
	@Column(name="definition")
	public String getDefinition() {
		return definition;
	}
	
	@Column(name="vocab_name")
	public String getVocabName() {
		return vocabName;
	}
	
	@Column(name="display_vocab_name")
	public String getDisplayVocabName() {
		return displayVocabName;
	}
	
	@Column(name="primary_id")
	public String getPrimaryId() {
		return primaryId;
	}
	
	@Column(name="sequence_num")
	public Integer getSequenceNum() {
		return sequenceNum;
	}
	
	@Column(name="is_obsolete")
	public int getIsObsolete() {
		return isObsolete;
	}
	
	@Column(name="is_root")
	public int getIsRoot() {
		return isRoot;
	}
	
	@Column(name="is_leaf")
	public int getIsLeaf() {
		return isLeaf;
	}
	
	@OneToMany (targetEntity=DiseaseModel.class)
    @JoinColumn(name="disease_id", referencedColumnName="primary_id")
    @BatchSize(size=500)
	@Filter(
			name = "termDiseaseModelExcludeNots",
			condition = "is_not_model = 0"
	)
    public List<DiseaseModel> getDiseaseModels() {
	return diseaseModels;
    }
	
	@OneToMany
	@JoinTable (name="marker_to_term",
		joinColumns=@JoinColumn(name="term_key"),
		inverseJoinColumns=@JoinColumn(name="marker_key")
		)
	@BatchSize(size=300)
	public List<Marker> getAssociatedMarkers() {
		if (associatedMarkers.size() > 0) {
		    Collections.sort(associatedMarkers,
			associatedMarkers.get(0).getComparator());
		}
		return associatedMarkers;
	}

	@OneToMany (targetEntity=VocabTermSynonym.class)
	@JoinColumn (name="term_key")
	@OrderBy ("synonym")
	public List<VocabTermSynonym> getSynonyms() {
		return synonyms;
	}

	@OneToMany (targetEntity=VocabTermID.class)
	@JoinColumn (name="term_key")
	@OrderBy ("accID")
	public List<VocabTermID> getSecondaryIds() {
		return secondaryIds;
	}

	@OneToOne (targetEntity=VocabTermEmapInfo.class)
	@JoinColumn (name="term_key")
	public VocabTermEmapInfo getEmapInfo() {
		return this.emapInfo;
	}

	@OneToMany (targetEntity=VocabTermAncestor.class)
	@JoinColumn (name="term_key")
	@OrderBy ("edgeLabel, ancestorTerm")
	public List<VocabTermAncestor> getAncestors() {
		return this.ancestors;
	}

	@OneToMany
	@JoinTable (name="term_emaps_child",
		joinColumns=@JoinColumn(name="emapa_term_key"),
		inverseJoinColumns=@JoinColumn(name="emaps_child_term_key")
		)
	@BatchSize(size=100)
	@OrderBy("sequenceNum")
	public List<VocabTerm> getEmapsChildren() {
		return this.emapsChildren;
	}

	@OneToMany
	@JoinTable (name="term_child",
		joinColumns=@JoinColumn(name="term_key"),
		inverseJoinColumns=@JoinColumn(name="child_term_key")
		)
	@BatchSize(size=100)
	@OrderBy("sequenceNum")
	public List<VocabTerm> getChildren() {
		return this.children;
	}
	
	/*
	 * Reverse join to voc_child to get to parents
	 */
	@OneToMany(targetEntity=VocabChild.class)
	@JoinColumn(name="child_term_key", referencedColumnName="term_key")
	@BatchSize(size=100)
	public List<VocabChild> getParentEdges() {
		return parentEdges;
	}
	
	@Transient
	public List<VocabChild> getEmapParentEdges() {
		List<VocabChild> emapParentEdges = new ArrayList<VocabChild>(this.getParentEdges());
		Collections.sort(emapParentEdges, new EmapParentEdgeComparator());
		return emapParentEdges;
	}

	/* Setters */

	public void setChildren (List<VocabTerm> children) {
		this.children = children;
	}

	public void setEmapsChildren (List<VocabTerm> emapsChildren) {
		this.emapsChildren = emapsChildren;
	}

	public void setAncestors (List<VocabTermAncestor> ancestors) {
		this.ancestors = ancestors;
	}

	public void setEmapInfo (VocabTermEmapInfo emapInfo) {
		this.emapInfo = emapInfo;
	}

	public void setSynonyms (List<VocabTermSynonym> synonyms) {
		this.synonyms = synonyms;
	}

	public void setSecondaryIds (List<VocabTermID> secondaryIds) {
		this.secondaryIds = secondaryIds;
	}

	public void setAssociatedMarkers(List<Marker> associatedMarkers) {
		this.associatedMarkers = associatedMarkers;
	}

	public void setTermKey(int termKey) {
		this.termKey = termKey;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public void setDefinition(String definition) {
		this.definition = definition;
	}
	public void setVocabName(String vocabName) {
		this.vocabName = vocabName;
	}
	public void setDisplayVocabName(String displayVocabName) {
		this.displayVocabName = displayVocabName;
	}
	public void setPrimaryId(String primaryId) {
		this.primaryId = primaryId;
	}
	public void setSequenceNum(Integer sequenceNum) {
		this.sequenceNum = sequenceNum;
	}
	public void setIsObsolete(int isObsolete) {
		this.isObsolete = isObsolete;
	}
	public void setIsRoot(int isRoot) {
		this.isRoot = isRoot;
	}
	public void setIsLeaf(int isLeaf) {
		this.isLeaf = isLeaf;
	}
	
	public void setDiseaseModels(List<DiseaseModel> diseaseModels) {
		this.diseaseModels = diseaseModels;
	}
	public void setParentEdges(List<VocabChild> parentEdges) {
		this.parentEdges = parentEdges;
	}
	
	/* Transient methods */
	@Transient
	public int getDiseaseModelCount()
	{
		return getDiseaseModels().size();
	}
	
	@Transient
	public Comparator<VocabTerm> getEmapsComparator() {
		return new EmapsComparator();
	}

	@Transient
	public List<VocabTerm> getEmapsChildrenSorted() {
	    if (this.emapsChildren != null) {
	        ArrayList<VocabTerm> sortedChildren =
		    new ArrayList<VocabTerm>(this.emapsChildren);
	        Collections.sort(sortedChildren,
		    sortedChildren.get(0).getEmapsComparator());
		return sortedChildren;
	    }
	    return this.emapsChildren;
	}


	// compares two VocabTerm objects based on their depth-first ordering,
	// when available.
	private class EmapsComparator implements Comparator<VocabTerm> {
	    public int compare (VocabTerm o1, VocabTerm o2) {

		VocabTermEmapInfo s1 = o1.getEmapInfo();
		VocabTermEmapInfo s2 = o2.getEmapInfo();

		// if either of the terms are missing their additional EMAP
		// data, then just fall back on a String comparison

		if ((s1 == null) || (s2 == null)) {
			return o1.getTerm().compareTo(o2.getTerm());
		}

		Integer stage1 = s1.getStage();
		Integer stage2 = s2.getStage();

		if ((stage1 != null) && (stage2 != null)) {
		    return stage1.compareTo(stage2);
		}
		return o1.getTerm().compareTo(o2.getTerm());
	    }
	}
	
	/*
	 * Compare parent endges by edgeLabel then by term name
	 */
	private class EmapParentEdgeComparator implements Comparator<VocabChild> {
		public int compare (VocabChild o1, VocabChild o2) {

			
			if ((o1.getEdgeLabel() != null) 
					&& (o2.getEdgeLabel()!=null) 
					&& !(o1.getEdgeLabel().equals(o2.getEdgeLabel()))
			) {
				return o1.getEdgeLabel().compareTo(o2.getEdgeLabel());
			}
			
			
			VocabTerm parent1 = o1.getParent();
			VocabTerm parent2 = o2.getParent();

			return parent1.getTerm().compareTo(parent2.getTerm());
		    }
	}
}
