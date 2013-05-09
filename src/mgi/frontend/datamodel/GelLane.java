package mgi.frontend.datamodel;

import java.util.*;

import javax.persistence.*;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * GelLane
 * This object represents the data for one gel lane.
 */
@Entity
@Table(name="gellane")
public class GelLane {

	// attributes in core table
	private Integer gelLaneKey;
	private Integer assayKey;
	private Integer laneSeq;
	private Integer genotypeKey;
	private String sampleAmount;
    private String laneLabel;
	private String laneNote;
	private String age;
	private String ageNote;
	private String sex;
	private boolean isControl;
	private String controlText;
	private String rnaType;

	// attributes joined in
	private Genotype genotype;
	private List<GelLaneStructure> structures;
	private List<GelBand> bands;
	
	/* properties not managed by hibernate */
	private String laneNoteLetter="";
	private String ageNoteLetter="";

    // ===================== Getters ===================== //

	@Id
	@Column(name="gellane_key")
	public Integer getGelLaneKey() {
		return gelLaneKey;
	}

	@Column(name="assay_key")
	public Integer getAssayKey() {
		return assayKey;
	}

	@Column(name="lane_seq")
	public Integer getLaneSeq() {
		return laneSeq;
	}

	@Column(name="sample_amount")
	public String getSampleAmount() {
		return sampleAmount;
	}

	@Column(name="lane_label")
	public String getLaneLabel() {
		return laneLabel;
	}

	@Column(name="age")
	public String getAge() {
		return age;
	}

	@Column(name="age_note")
	public String getAgeNote() {
		return ageNote;
	}
	
	@Column(name="lane_note")
	public String getLaneNote() {
		return laneNote;
	}

	@Column(name="sex")
	public String getSex() {
		return sex;
	}

	@Column(name="control_text")
	public String getControlText() {
		return controlText;
	}

	@Column(name="is_control")
	public boolean getIsControl() {
		return isControl;
	}

	@Column(name="rna_type")
	public String getRnaType() {
		return rnaType;
	}

	@Column(name="genotype_key")
	public Integer getGenotypeKey() {
		return genotypeKey;
	}

	@OneToOne(targetEntity=Genotype.class, fetch=FetchType.LAZY)
	@JoinColumn (name="genotype_key", insertable=false, updatable=false)
    public Genotype getGenotype() {
		return genotype;
	}

	@OneToMany(targetEntity=GelLaneStructure.class)
	@BatchSize(size=50)
	@JoinColumn(name="gellane_key")
    @OrderBy("structureSeq")
    public List<GelLaneStructure> getStructures() {
        return this.structures;
    }
	@OneToMany(targetEntity=GelBand.class)
	@BatchSize(size=50)
	@JoinColumn(name="gellane_key")
    @OrderBy("rowSeq")
    public List<GelBand> getBands() {
        return this.bands;
    }
	
	@Transient 
	public String getAgeNoteLetter()
	{
		return ageNoteLetter;
	}
	@Transient 
	public String getLaneNoteLetter()
	{
		return laneNoteLetter;
	}
	
    // ===================== Setters ===================== //
	
	
	public void setGelLaneKey(Integer gelLaneKey) {
		this.gelLaneKey = gelLaneKey;
	}

	public void setStructures(List<GelLaneStructure> structures) {
		this.structures = structures;
	}
	
	public void setBands(List<GelBand> bands) {
		this.bands = bands;
	}

	public void setAssayKey(Integer assayKey) {
		this.assayKey = assayKey;
	}

	public void setLaneSeq(Integer laneSeq) {
		this.laneSeq = laneSeq;
	}

	public void setGenotypeKey(Integer genotypeKey) {
		this.genotypeKey = genotypeKey;
	}

	public void setSampleAmount(String sampleAmount) {
		this.sampleAmount = sampleAmount;
	}

	public void setLaneLabel(String laneLabel) {
		this.laneLabel = laneLabel;
	}

	public void setLaneNote(String laneNote) {
		this.laneNote = laneNote;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public void setAgeNote(String ageNote) {
		this.ageNote = ageNote;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setIsControl(boolean isControl) {
		this.isControl = isControl;
	}

	public void setControlText(String controlText) {
		this.controlText = controlText;
	}

	public void setRnaType(String rnaType) {
		this.rnaType = rnaType;
	}

	public void setGenotype(Genotype genotype) {
		this.genotype = genotype;
	}
	

	public void setLaneNoteLetter(String laneNoteLetter) {
		this.laneNoteLetter = laneNoteLetter;
	}

	public void setAgeNoteLetter(String ageNoteLetter) {
		this.ageNoteLetter = ageNoteLetter;
	}

	@Override
	public String toString() {
		return "GelLane ["
				+ "assayKey=" + assayKey + ", "
				+ "gelLaneKey=" + gelLaneKey + ", "
				+ "laneLabel=" + laneLabel + ", "
				+ "]";
	}
}