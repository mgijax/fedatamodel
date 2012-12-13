package mgi.frontend.datamodel.phenotype;

import java.util.*;
import javax.persistence.*;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * PhenoTableSystem
 *
 * This object represents a cell for a system row in the phenotable grid
 */
@Entity
@Table(name="phenotable_system_cell")
public class PhenoTableSystemCell {

	private int phenoTableSystemCellKey;
	private String call;
	private String sex;
	private String genotypeID;
	private int cellSeq;
	private int genotypeSeq;

    // ================= Getters ======================================== //

	@Id
	@Column(name="phenotable_system_cell_key")
	public int getPhenoTableSystemCellKey() {
		return phenoTableSystemCellKey;
	}

	@Column(name="call")
	public String getCall() {
		return call;
	}
	@Column(name="sex")
	public String getSex() {
		return sex;
	}

	@Column(name="cell_seq")
	public int getCellSeq() {
		return cellSeq;
	}
	
	@Column(name="genotype_id")
	public String getGenotypeID()
	{
		return genotypeID;
	}

	@Column(name="genotype_seq")
    public int getGenotypeSeq() {
        return genotypeSeq;
    }
    
	/* Transient Properties */
	
	/*
	 * This is the html escape version of an annotation call.
	 */
	@Transient 
	public String getCallString()
	{
		if(this.call.equals("NA"))
		{
			return " ";
		}
		if(this.call.equals("Y"))
		{
			return "&#8730;";
		}
		return this.call;
	}
	@Transient 
	public boolean getHasCall()
	{
		return call.equals("Y") || call.equals("N");
	}

    // ================= Setters ======================================== //

	public void setPhenoTableSystemCellKey(int phenoTableSystemCellKey) {
		this.phenoTableSystemCellKey = phenoTableSystemCellKey;
	}

	public void setCall(String call) {
		this.call = call;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setGenotypeID(String genotypeID) {
		this.genotypeID = genotypeID;
	}

	public void setCellSeq(int cellSeq) {
		this.cellSeq = cellSeq;
	}
	public void setGenotypeSeq(int genotypeSeq) {
		this.genotypeSeq=genotypeSeq;
	}
}
