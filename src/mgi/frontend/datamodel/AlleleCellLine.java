package mgi.frontend.datamodel;

import java.util.*;
import javax.persistence.*;

import mgi.frontend.datamodel.util.DatamodelUtils;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * AlleleCellLine - one cell line for an allele (can have more than one of
 * these per Allele object)
 */
@Entity
@Table(name="allele_cell_line")
public class AlleleCellLine {

	private int uniqueKey;
	private int alleleKey;
	private boolean isParent;
	private boolean isMutant;
	private int mgdCellLineKey;
	private String cellLineType;
	private String name;
	private String primaryID;
	private String logicalDB;
	private String backgroundStrain;
	private String vector;
	private String vectorType;
	private String creator;
	private int sequenceNum;

    // ================= Getters ======================================== //

	@Id
	@Column(name="unique_key")
	public int getUniqueKey() {
		return uniqueKey;
	}

	@Column(name="allele_key")
	public int getAlleleKey() {
		return alleleKey;
	}

	@Column(name="is_parent")
	public boolean isParent() {
		return isParent;
	}

	@Column(name="is_mutant")
	public boolean isMutant() {
		return isMutant;
	}

	@Column(name="mgd_cellline_key")
	public int getMgdCellLineKey() {
		return mgdCellLineKey;
	}

	@Column(name="cellline_type")
	public String getCellLineType() {
		return cellLineType;
	}

	@Column(name="cellline_name")
	public String getName() {
		return name;
	}

	@Column(name="primary_id")
	public String getPrimaryID() {
		return primaryID;
	}

	@Column(name="logical_db")
	public String getLogicalDB() {
		return logicalDB;
	}

	@Column(name="background_strain")
	public String getBackgroundStrain() {
		return backgroundStrain;
	}

	@Column(name="vector")
	public String getVector() {
		return vector;
	}

	@Column(name="vector_type")
	public String getVectorType() {
		return vectorType;
	}

	@Column(name="creator")
	public String getCreator() {
		return creator;
	}

	@Column(name="sequence_num")
	public int getSequenceNum() {
		return sequenceNum;
	}

    // ================= Setters ======================================== //

	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	public void setAlleleKey(int alleleKey) {
		this.alleleKey = alleleKey;
	}

	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}

	public void setMutant(boolean isMutant) {
		this.isMutant = isMutant;
	}

	public void setMgdCellLineKey(int mgdCellLineKey) {
		this.mgdCellLineKey = mgdCellLineKey;
	}

	public void setCellLineType(String cellLineType) {
		this.cellLineType = cellLineType;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrimaryID(String primaryID) {
		this.primaryID = primaryID;
	}

	public void setLogicalDB(String logicalDB) {
		this.logicalDB = logicalDB;
	}

	public void setBackgroundStrain(String backgroundStrain) {
		this.backgroundStrain = backgroundStrain;
	}

	public void setVector(String vector) {
		this.vector = vector;
	}

	public void setVectorType(String vectorType) {
		this.vectorType = vectorType;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public void setSequenceNum(int sequenceNum) {
		this.sequenceNum = sequenceNum;
	}
}
