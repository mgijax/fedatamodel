package mgi.frontend.datamodel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;

@Entity
@Table(name="genotype_disease_reference")
public class GenotypeDiseaseReference 
{
	private int uniqueKey;
	private String jnumID;
	private int sequenceNum;
	
	@Id
	@Column(name="unique_key")
	public int getUniqueKey() {
		return uniqueKey;
	}
	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}
	@Column(name="jnum_id")
	public String getJnumID() {
		return jnumID;
	}
	public void setJnumID(String jnum) {
		this.jnumID = jnum;
	}
	@Column(name="sequence_num")
	public int getSequenceNum() {
		return sequenceNum;
	}
	public void setSequenceNum(int sequenceNum) {
		this.sequenceNum = sequenceNum;
	}
	
	
}
