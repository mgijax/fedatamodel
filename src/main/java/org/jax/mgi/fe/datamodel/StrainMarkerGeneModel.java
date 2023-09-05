package org.jax.mgi.fe.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Is: a single gene model ID for a strain-specific marker (aka- strain marker)
 */
@Entity
@Table(name="strain_marker_gene_model")
public class StrainMarkerGeneModel {
	private int uniqueKey;
	private int strainMarkerKey;
	private String geneModelID;
	private String logicalDB;
	private int sequenceNum;
	
    // ================= Getters and Setters ===================== //
	
	@Column(name="gene_model_id")
	public String getGeneModelID() {
		return geneModelID;
	}

	@Column(name="logical_db")
	public String getLogicalDB() {
		return logicalDB;
	}

	@Column(name="sequence_num")
	public int getSequenceNum() {
		return sequenceNum;
	}

	@Column(name="strain_marker_key")
	public int getStrainMarkerKey() {
		return strainMarkerKey;
	}

	@Id
	@Column(name="unique_key")
	public int getUniqueKey() {
		return uniqueKey;
	}

	public void setGeneModelID(String geneModelID) {
		this.geneModelID = geneModelID;
	}

	public void setLogicalDB(String logicalDB) {
		this.logicalDB = logicalDB;
	}
	
	public void setSequenceNum(int sequenceNum) {
		this.sequenceNum = sequenceNum;
	}

	public void setStrainMarkerKey(int strainMarkerKey) {
		this.strainMarkerKey = strainMarkerKey;
	}
	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	@Override
	public String toString() {
		return "StrainMarkerGeneModel [uniqueKey=" + uniqueKey + ", strainMarkerKey=" + strainMarkerKey
				+ ", geneModelID=" + geneModelID + ", logicalDB=" + logicalDB + ", sequenceNum=" + sequenceNum + "]";
	}
}
