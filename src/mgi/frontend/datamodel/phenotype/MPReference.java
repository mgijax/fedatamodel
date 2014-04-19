package mgi.frontend.datamodel.phenotype;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="mp_reference")
public class MPReference {

	private Integer mpReferenceKey;
	private String jnumID;
	private String source;
	
	@Column(name="source")
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	@Column(name="source_seq")
	public Integer getSoureSeq() {
		return soureSeq;
	}
	public void setSoureSeq(Integer soureSeq) {
		this.soureSeq = soureSeq;
	}

	private Integer soureSeq;
	private List<MPAnnotationNote> notes;
	
	@Id
    @Column(name="mp_reference_key")
	public Integer getMpReferenceKey() {
		return mpReferenceKey;
	}
	public void setMpReferenceKey(Integer mpReferenceKey) {
		this.mpReferenceKey = mpReferenceKey;
	}
	
	@Column(name="jnum_id")
	public String getJnumID() {
		return jnumID;
	}
	public void setJnumID(String jnumID) {
		this.jnumID = jnumID;
	}
	
	@OneToMany (targetEntity=MPAnnotationNote.class,fetch=FetchType.EAGER)
	@JoinColumn(name="mp_reference_key")
	public List<MPAnnotationNote> getNotes()
	{
		return notes;
	}
	
	public void setNotes(List<MPAnnotationNote> notes)
	{
		this.notes=notes;
	}
	
	/* Transient Properties */
	@Transient
	public boolean getHasNotes()
	{
		return this.getNotes().size()>0;
	}
	
	
	/* 
	 * This is how the source is going to be displayed. We decide to hide it if it is 'MGI' 
	 */
	@Transient 
	public String getSourceDisplay()
	{
		if (this.source.equalsIgnoreCase("MGI"))
		{
			return "";
		}
		return source;
	}
	
	@Transient
	public boolean getHasNonMgiSource()
	{
		if (this.source.equalsIgnoreCase("MGI"))
		{
			return false;
		}
		return true;
	}
}
