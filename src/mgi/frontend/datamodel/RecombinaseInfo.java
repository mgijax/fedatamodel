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
				+ (notDetectedCount != null ? "notDetectedCount="
						+ notDetectedCount + ", " : "")
				+ (unaffectedSystems != null ? "unaffectedSystems="
						+ unaffectedSystems : "") + "]";
	}
}
