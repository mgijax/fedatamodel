package org.jax.mgi.fe.datamodel.hdp;


/*
 * A common interface for the different grid-based annotation tables.
 */
public interface HdpGridAnnotation 
{
	public String getTerm();
	public void setTerm(String term);
	
	public String getTermId();
	public void setTermId(String term);
	
	public String getTermType();
	public void setTermType(String termType);
	
	public String getQualifier();
	public void setQualifier(String qualifier);
	
	public String getTermIdentifier(); // always returns a value, whereas termId is null for some termTypes
	
	public void setAnnotCount(Integer annotCount);
	public Integer getAnnotCount();
	
	public void setHumanAnnotCount(Integer annotCount);
	public Integer getHumanAnnotCount();
}
