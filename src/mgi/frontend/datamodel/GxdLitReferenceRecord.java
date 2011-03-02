package mgi.frontend.datamodel;

/**
 * GxdLitReferenceRecord
 * @author mhall
 * This datamodel class is not populated from the database.  Instead it will be constructed from
 * other datamodel classes when its being used.
 * 
 * This class in particular represents two values in a tuple, that of a reference, and the count of 
 * "record" for that reference.  When used in conjunction with the GxdLitGeneRecord object, we then
 * have a full representation of our tuple relationship.
 * 
 */

public class GxdLitReferenceRecord {
	private String jnum;
	private String longCitation;
	private Integer count = new Integer(1);
	
	public Integer getCount() {
		return count;
	}
	public String getJnum() {
		return jnum;
	}
	public String getLongCitation() {
		return longCitation;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public void setJnum(String jnum) {
		this.jnum = jnum;
	}
	public void setLongCitation(String longCitation) {
		this.longCitation = longCitation;
	}

}
