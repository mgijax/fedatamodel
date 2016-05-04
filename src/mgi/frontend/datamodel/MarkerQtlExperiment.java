package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * MarkerQtlExperiment
 * @author jsb
 * Notes for QTL mapping experiments are tied to markers.  Each of these
 * objects is one of those mapping experiment notes.
 */
@Entity
@Table(name="marker_qtl_experiments")
public class MarkerQtlExperiment {
    
	private int uniqueKey;
	private int markerKey;
	private int mgdExptKey;
	private String jnumID;
	private String note;
	private String refNote;
	private String noteType;
	private int sequenceNum;
	
    // ================= Getters and Setters ===================== //

	@Column(name="jnum_id")
	public String getJnumID() {
		return jnumID;
	}
	
	@Id
	@Column(name="unique_key")
	public int getUniqueKey() {
		return uniqueKey;
	}
	
	@Transient
	public String getHtmlNote() {
		if (this.getNote() != null) {
			return this.getNote().replaceAll("[\n]+", "<p/>");
		}
		return "";
	}

	@Column(name="note")
	public String getNote() {
		return note;
	}
	
	@Column(name="ref_note")
	public String getRefNote() {
		return refNote;
	}
	
	@Column(name="note_type")
	public String getNoteType() {
		return noteType;
	}
	
	@Column(name="marker_key")
	public int getMarkerKey() {
		return markerKey;
	}
	
	@Column(name="mgd_expt_key")
	public int getMgdExptKey() {
		return mgdExptKey;
	}
	
	@Column(name="sequence_num")
	public int getSequenceNum() {
		return sequenceNum;
	}
	
	public void setJnumID(String jnumID) {
		this.jnumID = jnumID;
	}
	
	public void setMgdExptKey(int mgdExptKey) {
		this.mgdExptKey = mgdExptKey;
	}
	
	public void setSequenceNum(int sequenceNum) {
		this.sequenceNum = sequenceNum;
	}
	
	public void setNote(String note) {
		this.note = note;
	}
	
	public void setRefNote(String refNote) {
		this.refNote = refNote;
	}
	
	public void setNoteType(String noteType) {
		this.noteType = noteType;
	}
	
	public void setMarkerKey(int markerKey) {
		this.markerKey = markerKey;
	}
	
	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}
	
	@Override
	public String toString() {
		return "MarkerQtlExperiment [uniqueKey=" + uniqueKey
			+ ", markerKey=" + markerKey + ", jnumID=" + jnumID
			+ ", mgdExptKey=" + mgdExptKey + "]";
	}
}
