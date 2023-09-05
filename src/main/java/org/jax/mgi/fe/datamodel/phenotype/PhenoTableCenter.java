package org.jax.mgi.fe.datamodel.phenotype;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * This object defines the core data for a phenotyping or data interpretation
 * center.
 */
@Entity
@Table(name="phenotable_center")
public class PhenoTableCenter {

	private int centerKey;
	private String name;
	private String abbreviation;
	private int sequenceNum;

	/*--- fields related to phenotyping centers ---*/

	@Id
	@Column(name="center_key")
	public int getCenterKey() {
		return centerKey;
	}

	public void setCenterKey(int centerKey) {
		this.centerKey = centerKey;
	}

	@Column(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="abbreviation")
	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	@Column(name="sequence_num")
	public int getSequenceNum() {
		return sequenceNum;
	}

	public void setSequenceNum(int sequenceNum) {
		this.sequenceNum = sequenceNum;
	}
}
