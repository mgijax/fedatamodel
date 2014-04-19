package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Note
 * @author mhall
 * Base Note class.  All tables that carry note information carry at least
 * these fields.
 */
@MappedSuperclass
public class Note {
	
	protected String note;
	protected String noteType;
	protected int uniqueKey;

    // ================= Getters and Setters ===================== //
	
	public String getNote() {
		return note;
	}

	@Column(name="note_type")
	public String getNoteType() {
		return noteType;
	}

	@Id
	@Column(name="unique_key")
	public int getUniqueKey() {
		return uniqueKey;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public void setNoteType(String noteType) {
		this.noteType = noteType;
	}

	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	@Override
	public String toString() {
		return "Note [" + (note != null ? "note=" + note + ", " : "")
				+ (noteType != null ? "noteType=" + noteType + ", " : "")
				+ "uniqueKey=" + uniqueKey + "]";
	}
}
