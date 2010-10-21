package mgi.frontend.datamodel;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;

/**
 * Companion to an Allele object, storing data specific to recombinase alleles.
 * @author jsb
 * There will be zero or one of these objects for each allele.  It maintains a
 * set of flags indicating whether the recombinase allele was detected or not
 * detected (or not tested) in various systems.  It has a list of all
 * AlleleSystem objects involving the allele, a list of them which were detected,
 * and a list of them which were not detected.
 */
@Entity
@Table(name="allele_recombinase_systems")
public class RecombinaseInfo {	
	//-------------------
	// instance variables
	//-------------------
	
	// attributes from allele_recombinase_system
	private int alleleKey;
	private Integer inAdiposeTissue;
    private Integer inAlimentarySystem;
    private Integer inBranchialArches;
    private Integer inCardiovascularSystem;
    private Integer inCavitiesAndLinings;
    private Integer inEndocrineSystem;
    private Integer inHead;
    private Integer inHemolymphoidSystem;
    private Integer inIntegumentalSystem;
    private Integer inLimbs;
    private Integer inLiverAndBiliarySystem;
    private Integer inMesenchyme;
    private Integer inMuscle;
    private Integer inNervousSystem;
    private Integer inRenalAndUrinarySystem;
    private Integer inReproductiveSystem;
    private Integer inRespiratorySystem;
    private Integer inSensoryOrgans;
    private Integer inSkeletalSystem;
    private Integer inTail;
    private Integer inEarlyEmbryo;
    private Integer inExtraembryonicComponent;
    private Integer inEmbryoOther;
    private Integer inPostnatalOther;
    private Integer detectedCount;
	private Integer notDetectedCount;
    
	// attributes from recombinase_allele_system
	private List<AlleleSystem> alleleSystems;
	
	// attributes from allele_recombinase_unaffected_system
	private List<AlleleSystem> unaffectedSystems;
	
	// attributes from allele_recombinase_affected_system
	private List<AlleleSystem> affectedSystems;
    
	//--------------------
    // getters and setters
	//--------------------

    /** Return the AlleleSystem objects involving this allele which had a
	 * 'detected' status.
     */
    @OneToMany (targetEntity=AlleleSystem.class)
    @JoinTable (name="allele_recombinase_affected_system",
            joinColumns=@JoinColumn(name="allele_key"),
            inverseJoinColumns=@JoinColumn(name="allele_system_key")
            )
	public List<AlleleSystem> getAffectedSystems() {
		return affectedSystems;
	}
    
    @Id
    @Column(name="allele_key")
    public int getAlleleKey() {
        return alleleKey;
    }
    
    /** Return all the AlleleSystem objects involving this allele.
     */
    @OneToMany (targetEntity=AlleleSystem.class)
    @JoinTable (name="recombinase_allele_system",
            joinColumns=@JoinColumn(name="allele_key"),
            inverseJoinColumns=@JoinColumn(name="allele_system_key")
            )
    public List<AlleleSystem> getAlleleSystems() {
        return alleleSystems;
    }

	@Column(name="detected_count")
	public Integer getDetectedCount() {
		return detectedCount;
	}

    @Column(name="in_adipose_tissue")
    public Integer getInAdiposeTissue() {
		return inAdiposeTissue;
	}

	@Column(name="in_alimentary_system")
	public Integer getInAlimentarySystem() {
		return inAlimentarySystem;
	}

    @Column(name="in_branchial_arches")
	public Integer getInBranchialArches() {
		return inBranchialArches;
	}

	@Column(name="in_cardiovascular_system")
	public Integer getInCardiovascularSystem() {
		return inCardiovascularSystem;
	}

    @Column(name="in_cavities_and_linings")
	public Integer getInCavitiesAndLinings() {
		return inCavitiesAndLinings;
	}

	@Column(name="in_early_embryo")
	public Integer getInEarlyEmbryo() {
		return inEarlyEmbryo;
	}

    @Column(name="in_embryo_other")
	public Integer getInEmbryoOther() {
		return inEmbryoOther;
	}

	@Column(name="in_endocrine_system")
	public Integer getInEndocrineSystem() {
		return inEndocrineSystem;
	}

    @Column(name="in_extraembryonic_component")
	public Integer getInExtraembryonicComponent() {
		return inExtraembryonicComponent;
	}

	@Column(name="in_head")
	public Integer getInHead() {
		return inHead;
	}

    @Column(name="in_hemolymphoid_system")
	public Integer getInHemolymphoidSystem() {
		return inHemolymphoidSystem;
	}

	@Column(name="in_integumental_system")
	public Integer getInIntegumentalSystem() {
		return inIntegumentalSystem;
	}

    @Column(name="in_limbs")
	public Integer getInLimbs() {
		return inLimbs;
	}

	@Column(name="in_liver_and_biliary_system")
	public Integer getInLiverAndBiliarySystem() {
		return inLiverAndBiliarySystem;
	}

	@Column(name="in_mesenchyme")
	public Integer getInMesenchyme() {
		return inMesenchyme;
	}

	@Column(name="in_muscle")
	public Integer getInMuscle() {
		return inMuscle;
	}

    @Column(name="in_nervous_system")
	public Integer getInNervousSystem() {
		return inNervousSystem;
	}

	@Column(name="in_postnatal_other")
	public Integer getInPostnatalOther() {
		return inPostnatalOther;
	}

    @Column(name="in_renal_and_urinary_system")
	public Integer getInRenalAndUrinarySystem() {
		return inRenalAndUrinarySystem;
	}

	@Column(name="in_reproductive_system")
	public Integer getInReproductiveSystem() {
		return inReproductiveSystem;
	}

    @Column(name="in_respiratory_system")
	public Integer getInRespiratorySystem() {
		return inRespiratorySystem;
	}

	@Column(name="in_sensory_organs")
	public Integer getInSensoryOrgans() {
		return inSensoryOrgans;
	}

    @Column(name="in_skeletal_system")
	public Integer getInSkeletalSystem() {
		return inSkeletalSystem;
	}

	@Column(name="in_tail")
	public Integer getInTail() {
		return inTail;
	}

    @Column(name="not_detected_count")
	public Integer getNotDetectedCount() {
		return notDetectedCount;
	}

	/** Return the AlleleSystem objects involving this allele which had a
	 * 'not detected' status.
     */
    @OneToMany (targetEntity=AlleleSystem.class)
    @JoinTable (name="allele_recombinase_unaffected_system",
            joinColumns=@JoinColumn(name="allele_key"),
            inverseJoinColumns=@JoinColumn(name="allele_system_key")
            )
	public List<AlleleSystem> getUnaffectedSystems() {
		return unaffectedSystems;
	}

    public void setAffectedSystems(List<AlleleSystem> affectedSystems) {
		this.affectedSystems = affectedSystems;
	}

	public void setAlleleKey(int alleleKey) {
        this.alleleKey = alleleKey;
    }

    public void setAlleleSystems(List<AlleleSystem> alleleSystems) {
        this.alleleSystems = alleleSystems;
    }

	public void setDetectedCount(Integer detectedCount) {
		this.detectedCount = detectedCount;
	}

    public void setInAdiposeTissue(Integer inAdiposeTissue) {
		this.inAdiposeTissue = inAdiposeTissue;
	}

	public void setInAlimentarySystem(Integer inAlimentarySystem) {
		this.inAlimentarySystem = inAlimentarySystem;
	}

    public void setInBranchialArches(Integer inBranchialArches) {
		this.inBranchialArches = inBranchialArches;
	}

	public void setInCardiovascularSystem(Integer inCardiovascularSystem) {
		this.inCardiovascularSystem = inCardiovascularSystem;
	}

    public void setInCavitiesAndLinings(Integer inCavitiesAndLinings) {
		this.inCavitiesAndLinings = inCavitiesAndLinings;
	}

	public void setInEarlyEmbryo(Integer inEarlyEmbryo) {
		this.inEarlyEmbryo = inEarlyEmbryo;
	}

    public void setInEmbryoOther(Integer inEmbryoOther) {
		this.inEmbryoOther = inEmbryoOther;
	}

	public void setInEndocrineSystem(Integer inEndocrineSystem) {
		this.inEndocrineSystem = inEndocrineSystem;
	}

    public void setInExtraembryonicComponent(Integer inExtraembryonicComponent) {
		this.inExtraembryonicComponent = inExtraembryonicComponent;
	}

	public void setInHead(Integer inHead) {
		this.inHead = inHead;
	}

    public void setInHemolymphoidSystem(Integer inHemolymphoidSystem) {
		this.inHemolymphoidSystem = inHemolymphoidSystem;
	}

	public void setInIntegumentalSystem(Integer inIntegumentalSystem) {
		this.inIntegumentalSystem = inIntegumentalSystem;
	}

    public void setInLimbs(Integer inLimbs) {
		this.inLimbs = inLimbs;
	}

	public void setInLiverAndBiliarySystem(Integer inLiverAndBiliarySystem) {
		this.inLiverAndBiliarySystem = inLiverAndBiliarySystem;
	}

    public void setInMesenchyme(Integer inMesenchyme) {
		this.inMesenchyme = inMesenchyme;
	}

	public void setInMuscle(Integer inMuscle) {
		this.inMuscle = inMuscle;
	}

    public void setInNervousSystem(Integer inNervousSystem) {
		this.inNervousSystem = inNervousSystem;
	}

	public void setInPostnatalOther(Integer inPostnatalOther) {
		this.inPostnatalOther = inPostnatalOther;
	}

	public void setInRenalAndUrinarySystem(Integer inRenalAndUrinarySystem) {
		this.inRenalAndUrinarySystem = inRenalAndUrinarySystem;
	}

	public void setInReproductiveSystem(Integer inReproductiveSystem) {
		this.inReproductiveSystem = inReproductiveSystem;
	}

	public void setInRespiratorySystem(Integer inRespiratorySystem) {
		this.inRespiratorySystem = inRespiratorySystem;
	}

    public void setInSensoryOrgans(Integer inSensoryOrgans) {
		this.inSensoryOrgans = inSensoryOrgans;
	}

	public void setInSkeletalSystem(Integer inSkeletalSystem) {
		this.inSkeletalSystem = inSkeletalSystem;
	}

	public void setInTail(Integer inTail) {
		this.inTail = inTail;
	}

	public void setNotDetectedCount(Integer notDetectedCount) {
		this.notDetectedCount = notDetectedCount;
	}

	public void setUnaffectedSystems(List<AlleleSystem> unaffectedSystems) {
		this.unaffectedSystems = unaffectedSystems;
	}

	//----------------
	// toString method
	//----------------
	
	@Override
	public String toString() {
		return "RecombinaseInfo ["
				+ (affectedSystems != null ? "affectedSystems="
						+ affectedSystems + ", " : "")
				+ "alleleKey="
				+ alleleKey
				+ ", "
				+ (alleleSystems != null ? "alleleSystems=" + alleleSystems
						+ ", " : "")
				+ (detectedCount != null ? "detectedCount=" + detectedCount
						+ ", " : "")
				+ (inAdiposeTissue != null ? "inAdiposeTissue="
						+ inAdiposeTissue + ", " : "")
				+ (inAlimentarySystem != null ? "inAlimentarySystem="
						+ inAlimentarySystem + ", " : "")
				+ (inBranchialArches != null ? "inBranchialArches="
						+ inBranchialArches + ", " : "")
				+ (inCardiovascularSystem != null ? "inCardiovascularSystem="
						+ inCardiovascularSystem + ", " : "")
				+ (inCavitiesAndLinings != null ? "inCavitiesAndLinings="
						+ inCavitiesAndLinings + ", " : "")
				+ (inEarlyEmbryo != null ? "inEarlyEmbryo=" + inEarlyEmbryo
						+ ", " : "")
				+ (inEmbryoOther != null ? "inEmbryoOther=" + inEmbryoOther
						+ ", " : "")
				+ (inEndocrineSystem != null ? "inEndocrineSystem="
						+ inEndocrineSystem + ", " : "")
				+ (inExtraembryonicComponent != null ? "inExtraembryonicComponent="
						+ inExtraembryonicComponent + ", "
						: "")
				+ (inHead != null ? "inHead=" + inHead + ", " : "")
				+ (inHemolymphoidSystem != null ? "inHemolymphoidSystem="
						+ inHemolymphoidSystem + ", " : "")
				+ (inIntegumentalSystem != null ? "inIntegumentalSystem="
						+ inIntegumentalSystem + ", " : "")
				+ (inLimbs != null ? "inLimbs=" + inLimbs + ", " : "")
				+ (inLiverAndBiliarySystem != null ? "inLiverAndBiliarySystem="
						+ inLiverAndBiliarySystem + ", " : "")
				+ (inMesenchyme != null ? "inMesenchyme=" + inMesenchyme + ", "
						: "")
				+ (inMuscle != null ? "inMuscle=" + inMuscle + ", " : "")
				+ (inNervousSystem != null ? "inNervousSystem="
						+ inNervousSystem + ", " : "")
				+ (inPostnatalOther != null ? "inPostnatalOther="
						+ inPostnatalOther + ", " : "")
				+ (inRenalAndUrinarySystem != null ? "inRenalAndUrinarySystem="
						+ inRenalAndUrinarySystem + ", " : "")
				+ (inReproductiveSystem != null ? "inReproductiveSystem="
						+ inReproductiveSystem + ", " : "")
				+ (inRespiratorySystem != null ? "inRespiratorySystem="
						+ inRespiratorySystem + ", " : "")
				+ (inSensoryOrgans != null ? "inSensoryOrgans="
						+ inSensoryOrgans + ", " : "")
				+ (inSkeletalSystem != null ? "inSkeletalSystem="
						+ inSkeletalSystem + ", " : "")
				+ (inTail != null ? "inTail=" + inTail + ", " : "")
				+ (notDetectedCount != null ? "notDetectedCount="
						+ notDetectedCount + ", " : "")
				+ (unaffectedSystems != null ? "unaffectedSystems="
						+ unaffectedSystems : "") + "]";
	}
}
