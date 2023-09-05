package org.jax.mgi.fe.datamodel.phenotype;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="mp_annotation_note")
public class MPAnnotationNote {
	private Integer mpNoteKey;
	private String note;
	
	@Column(name="note")
	public String getNote()
	{
		return note;
	}
	public void setNote(String note)
	{
		this.note=note;
	}

	@Id
    @Column(name="mp_note_key")
	public Integer getMpNoteKey()
	{
		return this.mpNoteKey;
	}

	public void setMpNoteKey(Integer key)
	{
		this.mpNoteKey=key;
	}
}
