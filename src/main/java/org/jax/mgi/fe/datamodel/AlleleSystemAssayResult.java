package org.jax.mgi.fe.datamodel;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.jax.mgi.fe.datamodel.group.RecombinaseEntity;

@Entity
@Table (name="recombinase_assay_result")
public class AlleleSystemAssayResult implements RecombinaseEntity, Serializable {

	private static final long serialVersionUID = 1L;
	 
    private String age;
    private int alleleSystemKey;
    private String allelicComposition;
    private String antibodyID;
    private String antibodyName;
    private String assayNote;
    private String assayType;
    private String backgroundStrain;
    private String detectionMethod;
    private String jnumID;
    private String level;
    private List<AlleleSystemAssayResultImagePane> panes;
    private String pattern;
    private String probeID;
    private String probeName;
    private String reporterGene;
    private Integer resultKey;
    private String resultNote;
    private String sex;
    private String specimenNote;
    private String structure;
    private String cellType;   
    private Term term;
    private List<Term> cellTypeTerms;    

    // ================= Getters and Setters ===================== //
    
    public String getAge() {
        return age;
    }
    
    /**
     * Return the allele system key for this assay result
     * @return
     */
    @Column(name="allele_system_key")
    public int getAlleleSystemKey() {
        return alleleSystemKey;
    }
    
    /**
     * Returns the allele pairs
     * @return
     */
    @Column(name="allelic_composition")
    public String getAllelicComposition() {
        return allelicComposition;
    }
    
    @Column(name="antibody_id")
    public String getAntibodyID() {
        return antibodyID;
    }
    
    @Column(name="antibody_name")
    public String getAntibodyName() {
        return antibodyName;
    }
    @Column(name="assay_note")
    public String getAssayNote() {
        return assayNote;
    }
    @Column(name="assay_type")
    public String getAssayType() {
        return assayType;
    }
    @Column(name="background_strain")
    public String getBackgroundStrain() {
        return backgroundStrain;
    }
    @Column(name="detection_method")
    public String getDetectionMethod() {
        return detectionMethod;
    }
    @Column(name="jnum_id")
    public String getJnumID() {
        return jnumID;
    }
    public String getLevel() {
        return level;
    }
    @OneToMany (targetEntity=AlleleSystemAssayResultImagePane.class)
    @JoinColumn(name="result_key")
    public List<AlleleSystemAssayResultImagePane> getPanes() {
        return panes;
    }
    
    public String getPattern() {
        return pattern;
    }
    @Column(name="probe_id")
    public String getProbeID() {
        return probeID;
    }
    
    @Column(name="probe_name")
    public String getProbeName() {
        return probeName;
    }
    @Column(name="reporter_gene")
    public String getReporterGene() {
        return reporterGene;
    }
    
    @Id
    @Column(name="result_key")
    public Integer getResultKey() {
        return resultKey;
    }
    @Column(name="result_note")
    public String getResultNote() {
        return resultNote;
    }
    
    public String getSex() {
        return sex;
    }
    @Column(name="specimen_note")
    public String getSpecimenNote() {
        return specimenNote;
    }
    public String getStructure() {
        return structure;
    }

    @Column(name="cell_type")
    public String getCellType() {
		return cellType;
	}

	public void setCellType(String cellType) {
		this.cellType = cellType;
	}

    public void setAge(String age) {
        this.age = age;
    }
    
    public void setAlleleSystemKey(int alleleSystemKey) {
        this.alleleSystemKey = alleleSystemKey;
    }
    public void setAllelicComposition(String allelicComposition) {
        this.allelicComposition = allelicComposition;
    }
    
    public void setAntibodyID(String antibodyID) {
        this.antibodyID = antibodyID;
    }
    public void setAntibodyName(String antibodyName) {
        this.antibodyName = antibodyName;
    }
    
    public void setAssayNote(String assayNote) {
        this.assayNote = assayNote;
    }
    public void setAssayType(String assayType) {
        this.assayType = assayType;
    }
    
    public void setBackgroundStrain(String backgroundStrain) {
        this.backgroundStrain = backgroundStrain;
    }
    public void setDetectionMethod(String detectionMethod) {
        this.detectionMethod = detectionMethod;
    }
    
    public void setJnumID(String jnumID) {
        this.jnumID = jnumID;
    }
    public void setLevel(String level) {
        this.level = level;
    }
    
    public void setPanes(List<AlleleSystemAssayResultImagePane> panes) {
        this.panes = panes;
    }
    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
    
    public void setProbeID(String probeID) {
        this.probeID = probeID;
    }
    public void setProbeName(String probeName) {
        this.probeName = probeName;
    }
    
    public void setReporterGene(String reporterGene) {
        this.reporterGene = reporterGene;
    }
    public void setResultKey(Integer resultKey) {
        this.resultKey = resultKey;
    }
    
    public void setResultNote(String resultNote) {
        this.resultNote = resultNote;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public void setSpecimenNote(String specimenNote) {
        this.specimenNote = specimenNote;
    }
    public void setStructure(String structure) {
        this.structure = structure;
    }   
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="structure_key", referencedColumnName="term_key", nullable=true, insertable=false, updatable=false)
    public Term getTerm() {
        return this.term;
    }
    public void setTerm(Term term) {
        this.term = term;
    }

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="term", referencedColumnName="cell_type", insertable=false, updatable=false)    
	public List<Term> getCellTypeTerms() {
		return cellTypeTerms;
	}
	public void setCellTypeTerms(List<Term> cellTypeTerms) {
		this.cellTypeTerms = cellTypeTerms;
	}
	
    @Override
    public String toString() {
        return "AlleleSystemAssayResult [age=" + age + ", allele_system_key="
                + alleleSystemKey + ", allelicComposition="
                + allelicComposition + ", antibodyID=" + antibodyID
                + ", antibodyName=" + antibodyName + ", assayNote=" + assayNote
                + ", assayType=" + assayType + ", backgroundStrain="
                + backgroundStrain + ", detectionMethod=" + detectionMethod
                + ", jnumID=" + jnumID + ", level=" + level + ", pattern="
                + pattern + ", probeID=" + probeID + ", probeName=" + probeName
                + ", reporterGene=" + reporterGene + ", resultKey=" + resultKey
                + ", resultNote=" + resultNote + ", sex=" + sex
                + ", specimenNote=" + specimenNote + ", structure=" + structure
		+ ", cellType=" + cellType
                + "]";
    }

}
