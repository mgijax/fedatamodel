package org.jax.mgi.fe.datamodel.phenotype;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="mp_reference")
public class MPReference {

	//--- instance variables ---//

	private Integer mpReferenceKey;
	private String jnumID;
	private PhenoTableCenter phenotypingCenter;
	private PhenoTableCenter interpretationCenter;
	private List<MPAnnotationNote> notes;
	
	//--- setters & getters ---//

	@Id
	@Column(name="mp_reference_key")
	public Integer getMpReferenceKey() {
		return mpReferenceKey;
	}
	public void setMpReferenceKey(Integer mpReferenceKey) {
		this.mpReferenceKey = mpReferenceKey;
	}
	
	@OneToOne (targetEntity=PhenoTableCenter.class)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name="phenotyping_center_key")
	public PhenoTableCenter getPhenotypingCenter() {
		return phenotypingCenter;
	}

	public void setPhenotypingCenter(PhenoTableCenter phenotypingCenter) {
		this.phenotypingCenter = phenotypingCenter;
	}

	@OneToOne (targetEntity=PhenoTableCenter.class)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name="interpretation_center_key")
	public PhenoTableCenter getInterpretationCenter() {
		return interpretationCenter;
	}

	public void setInterpretationCenter(PhenoTableCenter interpretationCenter) {
		this.interpretationCenter = interpretationCenter;
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
	
	@Transient
	public String getPhenotypingCenterName() {
		if (this.phenotypingCenter == null) { return null; }
		return this.phenotypingCenter.getName();
	}

	@Transient
	public String getPhenotypingCenterAbbreviation() {
		if (this.phenotypingCenter == null) { return null; }
		return this.phenotypingCenter.getAbbreviation();
	}

	@Transient
	public String getInterpretationCenterName() {
		if (this.interpretationCenter == null) { return null; }
		return this.interpretationCenter.getName();
	}

	@Transient
	public String getInterpretationCenterAbbreviation() {
		if (this.interpretationCenter == null) { return null; }
		return this.interpretationCenter.getAbbreviation();
	}

	/* 
	 * This is how the source is going to be displayed. We decide to hide it if it is 'MGI' 
	 */
	@Transient 
	public String getSourceDisplay()
	{
		StringBuffer sb = new StringBuffer();
		String pc = this.getPhenotypingCenterAbbreviation();
		String ic = this.getInterpretationCenterAbbreviation();

		if (ic != null) {
			sb.append(ic);
		}

		if (pc != null) {
		    if (!pc.equals(ic)) {
			if (sb.length() > 0) {
				sb.append(" - ");
			}
			sb.append(pc);
		    }
		}
		return sb.toString();
	}
	
	@Transient
	public String getSourceDescription()
	{
		StringBuffer sb = new StringBuffer();
		String pc = this.getPhenotypingCenterName();
		String ic = this.getInterpretationCenterName();

		if (ic != null) {
			if (ic.equals("MGI")) {
				return "MGI Curated Data";
			} else if (ic.equals("EuroPhenome")) {
				return "Phenotype annotations from <B>EuroPhenome</B>";
			}
			sb.append("Data Interpretation Center: <B>");
			sb.append(ic);
			sb.append("</B>");
		}

		if (pc != null) {
		    if (sb.length() > 0) {
			sb.append("<BR>");
		    }
		    sb.append("Phenotyping Center: <B>");
		    sb.append(pc);
		    sb.append("</B>");
		}
		return sb.toString();
	}

	@Transient
	public boolean getHasNonMgiSource()
	{
		if ("MGI".equals(this.getInterpretationCenterName()))
		{
			return false;
		}
		return true;
	}
}
