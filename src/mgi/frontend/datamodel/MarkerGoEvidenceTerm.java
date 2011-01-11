package mgi.frontend.datamodel;

import javax.persistence.*;

/**
 * MarkerGoEvidenceTerm
 * @author jsb
 * One of these objects represents one evidence code used for a marker's
 * GO annotations.  These are useful to know which evidence codes were
 * used, so we could print a table of only the ones used for a marker,
 * for instance.
 */
@Entity
@Table (name="marker_go_evidence_terms")
public class MarkerGoEvidenceTerm  {
	
    private int uniqueKey;
	private int markerKey;
	private String abbreviation;
	private String term;
	private int sequenceNum;
	
	@Column(name="abbreviation")
	public String getAbbreviation() {
		return abbreviation;
	}
	
	@Column(name="marker_key")
	public int getMarkerKey() {
		return markerKey;
	}
	
	@Column(name="sequence_num")
	public int getSequenceNum() {
		return sequenceNum;
	}
	
	@Column(name="term")
	public String getTerm() {
		return term;
	}
	
	@Id
	@Column(name="unique_key")
	public int getUniqueKey() {
		return uniqueKey;
	}
	
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	
	public void setMarkerKey(int markerKey) {
		this.markerKey = markerKey;
	}
	
	public void setSequenceNum(int sequenceNum) {
		this.sequenceNum = sequenceNum;
	}
	
	public void setTerm(String term) {
		this.term = term;
	}
	
	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}
	
	@Override
	public String toString() {
		return "MarkerGoEvidenceTerm ["
				+ (abbreviation != null ? "abbreviation=" + abbreviation + ", "
						: "") + "markerKey=" + markerKey + ", sequenceNum="
				+ sequenceNum + ", "
				+ (term != null ? "term=" + term + ", " : "") + "uniqueKey="
				+ uniqueKey + "]";
	}    
}
