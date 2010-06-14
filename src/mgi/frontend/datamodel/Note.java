package mgi.frontend.datamodel;

import javax.persistence.*;

@MappedSuperclass
public class Note implements SortableObject {

	public static String NOTE_TYPE = "Note.NoteType";
	public static String NOTE = "Note.Note";
	
	// subclasses will need to add their particular objects and getter/setter methods
	protected int uniqueKey;
	protected String noteType;
	protected String note;
	
	public Note() {}

	@Id
	public int getUniqueKey() {
		return uniqueKey;
	}

	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	public String getNoteType() {
		return noteType;
	}

	public void setNoteType(String noteType) {
		this.noteType = noteType;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public Comparable getComparableValue(String fieldname) throws NoSuchFieldException {
		Comparable out;
		if (fieldname.equals (NOTE_TYPE)) {
			out = this.getNoteType();
		}
		else if (fieldname.equals(NOTE)) {
			out = this.getNote();
		} else {
			throw new NoSuchFieldException ("Unknown field: " + fieldname);
		}
		return out;
	}

	@Override
	public String toString() {
		return "Note [" + (note != null ? "note=" + note + ", " : "")
				+ (noteType != null ? "noteType=" + noteType + ", " : "")
				+ "uniqueKey=" + uniqueKey + "]";
	}
}
