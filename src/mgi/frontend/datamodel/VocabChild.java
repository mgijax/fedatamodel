package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="term_child")
public class VocabChild {

	private int uniqueKey;
	private int termKey;
	private int childTermKey;
	private String childTerm;
	private String childPrimaryId;
	private int sequencenum;
	private int isLeaf;
	private String edgeLabel;
	private VocabTerm parent;
	
	
	@Id
	@Column(name="unique_key")
	public int getUniqueKey() {
		return uniqueKey;
	}
	
	@Column(name="term_key")
	public int getTermKey() {
		return termKey;
	}
	
	@Column(name="child_term_key")
	public int getChildTermKey() {
		return childTermKey;
	}
	
	@Column(name="child_term")
	public String getChildTerm() {
		return childTerm;
	}
	
	@Column(name="child_primary_id")
	public String getChildPrimaryId() {
		return childPrimaryId;
	}
	
	@Column(name="sequence_num")
	public int getSequencenum() {
		return sequencenum;
	}
	
	@Column(name="is_leaf")
	public int getIsLeaf() {
		return isLeaf;
	}
	
	@Column(name="edge_label")
	public String getEdgeLabel() {
		return edgeLabel;
	}
	
	@ManyToOne(targetEntity=VocabTerm.class)
	@JoinColumn(name="term_key", insertable=false, updatable=false)
	public VocabTerm getParent() {
		return parent;
	}
	
	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}
	public void setTermKey(int termKey) {
		this.termKey = termKey;
	}
	public void setChildTermKey(int childTermKey) {
		this.childTermKey = childTermKey;
	}
	public void setChildTerm(String childTerm) {
		this.childTerm = childTerm;
	}
	public void setChildPrimaryId(String childPrimaryId) {
		this.childPrimaryId = childPrimaryId;
	}
	public void setSequencenum(int sequencenum) {
		this.sequencenum = sequencenum;
	}
	public void setIsLeaf(int isLeaf) {
		this.isLeaf = isLeaf;
	}
	public void setEdgeLabel(String edgeLabel) {
		this.edgeLabel = edgeLabel;
	}
	
	public void setParent(VocabTerm parent) {
		this.parent = parent;
	}
	
	
	
	
}
