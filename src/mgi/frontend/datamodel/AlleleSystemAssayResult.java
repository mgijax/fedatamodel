package mgi.frontend.datamodel;

import javax.persistence.*;

@Entity
@Table (name="recombinase_assay_result")
public class AlleleSystemAssayResult {

    private int alleleSystemKey;
    private Integer resultKey;
    private String structure;
    private String age;
    private String level;
    private String pattern;
    private String jnumID;
    private String assayType;
    private String reporterGene;
    private String detectionMethod;
    private String sex;
    private String allelicComposition;
    private String backgroundStrain;
    private String assayNote;
    private String resultNote;
    private String specimenNote;
    private String probeID;
    private String probeName;
    private String antibodyID;
    private String antibodyName;
    

    @Column(name="allele_system_key")
    public int getAlleleSystemKey() {
        return alleleSystemKey;
    }
    public void setAlleleSystemKey(int alleleSystemKey) {
        this.alleleSystemKey = alleleSystemKey;
    }
    
    @Id
    @Column(name="result_key")
    public Integer getResultKey() {
        return resultKey;
    }
    public void setResultKey(Integer resultKey) {
        this.resultKey = resultKey;
    }
    public String getStructure() {
        return structure;
    }
    public void setStructure(String structure) {
        this.structure = structure;
    }
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getLevel() {
        return level;
    }
    public void setLevel(String level) {
        this.level = level;
    }
    public String getPattern() {
        return pattern;
    }
    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
    
    @Column(name="jnum_id")
    public String getJnumID() {
        return jnumID;
    }
    public void setJnumID(String jnumID) {
        this.jnumID = jnumID;
    }
    
    @Column(name="assay_type")
    public String getAssayType() {
        return assayType;
    }
    public void setAssayType(String assayType) {
        this.assayType = assayType;
    }
    
    @Column(name="reporter_gene")
    public String getReporterGene() {
        return reporterGene;
    }
    public void setReporterGene(String reporterGene) {
        this.reporterGene = reporterGene;
    }
    
    @Column(name="detection_method")
    public String getDetectionMethod() {
        return detectionMethod;
    }
    public void setDetectionMethod(String detectionMethod) {
        this.detectionMethod = detectionMethod;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    
    @Column(name="allelic_composition")
    public String getAllelicComposition() {
        return allelicComposition;
    }
    public void setAllelicComposition(String allelicComposition) {
        this.allelicComposition = allelicComposition;
    }
    
    @Column(name="background_strain")
    public String getBackgroundStrain() {
        return backgroundStrain;
    }
    public void setBackgroundStrain(String backgroundStrain) {
        this.backgroundStrain = backgroundStrain;
    }
    
    @Column(name="assay_note")
    public String getAssayNote() {
        return assayNote;
    }
    public void setAssayNote(String assayNote) {
        this.assayNote = assayNote;
    }
    
    @Column(name="result_note")
    public String getResultNote() {
        return resultNote;
    }
    public void setResultNote(String resultNote) {
        this.resultNote = resultNote;
    }
    
    @Column(name="specimen_note")
    public String getSpecimenNote() {
        return specimenNote;
    }
    public void setSpecimenNote(String specimenNote) {
        this.specimenNote = specimenNote;
    }
    
    @Column(name="probe_id")
    public String getProbeID() {
        return probeID;
    }
    public void setProbeID(String probeID) {
        this.probeID = probeID;
    }
    
    @Column(name="probe_name")
    public String getProbeName() {
        return probeName;
    }
    public void setProbeName(String probeName) {
        this.probeName = probeName;
    }
    
    @Column(name="antibody_id")
    public String getAntibodyID() {
        return antibodyID;
    }
    public void setAntibodyID(String antibodyID) {
        this.antibodyID = antibodyID;
    }
    
    @Column(name="antibody_name")
    public String getAntibodyName() {
        return antibodyName;
    }
    public void setAntibodyName(String antibodyName) {
        this.antibodyName = antibodyName;
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
                + "]";
    }


}
