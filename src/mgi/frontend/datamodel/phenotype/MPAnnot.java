package mgi.frontend.datamodel.phenotype;

import java.util.*;
import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="mp_annot")
public class MPAnnot {
	private Integer annotation_key;
	private String sex;
	private boolean call;
	private List<MPReference> references;
	private Integer annotSeq;

	@Column(name="annotation_seq")
	public Integer getAnnotSeq() {
		return annotSeq;
	}

	public void setAnnotSeq(Integer annotSeq) {
		this.annotSeq = annotSeq;
	}

	/* Getters */
	@Column(name="sex")
	public String getSex() {
		return sex;
	}

	@Column(name="call")
	public boolean getIsCall() {
		return call;
	}
	
	@Id
    @Column(name="mp_annotation_key")
	public Integer getAnnotation_key() {
		return annotation_key;
	}

	@OneToMany (targetEntity=MPReference.class,fetch=FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	@JoinColumn(name="mp_annotation_key")
	public List<MPReference> getReferences()
	{
		return references;
	}
	
	/* Transient Properties */
	/*
	 * This is basically a check if the sex is NA
	 */
	@Transient 
	public boolean getIsBoth()
	{
		if(this.sex.equalsIgnoreCase("na"))
		{
			return true;
		}
		return false;
	}
	
	
	/*
	 * The html escape version of a call.
	 */
	@Transient
	public String getHtmlCall()
	{
		if (this.call)
		{
			return "";
		}
		return "N";
	}
	
	@Transient
	public boolean getHasNotes()
	{
		if(this.getReferences().size()>0)
		{
			for(MPReference ref : getReferences())
			{
				if(ref.getNotes().size()>0)
				{
					return true;
				}
			}
		}
		return false;
	}

	@Transient
	public String getHtmlSex()
	{
		String htmlMale = "&#9794;";
		String htmlFemale = "&#9792;";
		htmlMale = "M";
		htmlFemale = "F";
		if(this.sex.equalsIgnoreCase("M"))
		{
			return htmlMale;
		}
		if(this.sex.equalsIgnoreCase("F"))
		{
			return htmlFemale;
		}
		return "";
	}
	/* Returns the html sex symbol (plus 'N' if applicable) */
	@Transient
	public String getCombinedSymbol()
	{
		return getHtmlSex()+this.getHtmlCall();
	}
	/* Setters */
	public void setSex(String sex) {
		this.sex=sex;
	}
	public void setIsCall(boolean call) {
		this.call = call;
	}
	public void setAnnotation_key(Integer annotation_key) {
		this.annotation_key = annotation_key;
	}
	public void setReferences(List<MPReference> references)
	{
		this.references=references;
	}
}
