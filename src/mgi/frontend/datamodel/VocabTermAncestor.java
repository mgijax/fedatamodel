package mgi.frontend.datamodel;

import javax.persistence.*;

/**
 * records information for an ancestor term of a given vocabulary term.
 */
@Entity
@Table(name="term_ancestor")
public class VocabTermAncestor {
	private int uniqueKey;		// uniquely identifies this record
	private int termKey;		// identifies the term
	private int ancestorTermKey;	// identifies the ancestor term
	private String ancestorTerm;	// text of the ancestor term itself
	private String ancestorID;	// accession ID of the ancestor term
	private int pathNumber;		// identifies which path to the
					// ...root contains this ancestor
	private int depth;		// how deep in the DAG (from the root)
					// ...is this ancestor?
	private String edgeLabel;	// type of edge going up to this
					// ...ancestor
	
    // ================= Getters and Setters ===================== //
	
	@Id
	@Column(name="unique_key")
	public int getUniqueKey() {
		return uniqueKey;
	}

	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	@Column(name="term_key")
	public int getTermKey() {
		return termKey;
	}

	public void setTermKey(int termKey) {
		this.termKey = termKey;
	}

	@Column(name="ancestor_term_key")
	public int getAncestorTermKey() {
		return ancestorTermKey;
	}

	public void setAncestorTermKey(int ancestorTermKey) {
		this.ancestorTermKey = ancestorTermKey;
	}

	@Column(name="ancestor_term")
	public String getAncestorTerm() {
		return ancestorTerm;
	}

	public void setAncestorTerm(String ancestorTerm) {
		this.ancestorTerm = ancestorTerm;
	}

	@Column(name="ancestor_primary_id")
	public String getAncestorID() {
		return ancestorID;
	}

	public void setAncestorID(String ancestorID) {
		this.ancestorID = ancestorID;
	}

	@Column(name="path_number")
	public int getPathNumber() {
		return pathNumber;
	}

	public void setPathNumber(int pathNumber) {
		this.pathNumber = pathNumber;
	}

	@Column(name="depth")
	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	@Column(name="edge_label")
	public String getEdgeLabel() {
		return edgeLabel;
	}

	public void setEdgeLabel(String edgeLabel) {
		this.edgeLabel = edgeLabel;
	}

	@Override
	public String toString() {
		return "VocabTermAncestor [termKey=" + termKey + "]";
	}
}
