package mgi.frontend.datamodel;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Id;

import org.hibernate.annotations.BatchSize;

/* MPGenotype - is a genotype which:
 * 1. has MP annotations, and
 * 2. is associated with a marker
 */
@Entity
@Table (name="marker_mp_genotype")
public class MPGenotype {
	private int mpGenotypeKey;
	private int markerKey;
	private int isMultigenic;
	private String allelePairs;
	private String strain;
	private String primaryID;
	private int sequenceNum;
	private List<MPAnnotation> mpAnnotations;

	//--- getters ---//

	@Id
	@Column(name="mp_genotype_key")
	public int getMpGenotypeKey() {
		return mpGenotypeKey;
	}

	@Column(name="marker_key")
	public int getMarkerKey() {
		return markerKey;
	}

	@Column(name="is_multigenic")
	public int getIsMultigenic() {
		return isMultigenic;
	}

	@Column(name="allele_pairs")
	public String getAllelePairs() {
		return allelePairs;
	}

	@Column(name="strain")
	public String getStrain() {
		return strain;
	}

	@Column(name="sequence_num")
	public int getSequenceNum() {
		return sequenceNum;
	}

	@OneToMany(targetEntity=MPAnnotation.class)
	@JoinColumn(name="mp_genotype_key")
	@BatchSize(size=40)
	@OrderBy("sequence_num")
	public List<MPAnnotation> getMpAnnotations() {
		return mpAnnotations;
	}

	@Column(name="genotype_id")
	public String getPrimaryID() {
		return primaryID;
	}

	//--- setters ---//

	public void setMpGenotypeKey(int mpGenotypeKey) {
		this.mpGenotypeKey = mpGenotypeKey;
	}

	public void setMarkerKey(int markerKey) {
		this.markerKey = markerKey;
	}
	
	public void setIsMultigenic(int isMultigenic) {
		this.isMultigenic = isMultigenic;
	}

	public void setAllelePairs(String allelePairs) {
		this.allelePairs = allelePairs;
	}

	public void setStrain(String strain) {
		this.strain = strain;
	}

	public void setSequenceNum(int sequenceNum) {
		this.sequenceNum = sequenceNum;
	}
	
	public void setMpAnnotations (List<MPAnnotation> mpAnnotations) {
		this.mpAnnotations = mpAnnotations;
	}

	public void setPrimaryID (String primaryID) {
		this.primaryID = primaryID;
	}

	//--- convenience methods ---//
	
	public String toString() {
		return "MPGenotype [mpGenotypeKey=" + mpGenotypeKey
			+ (allelePairs != null ? ", allelePairs=" + allelePairs : "")
			+ (strain != null ? ", strain=" + strain : "")
			+ "]";
	}
}
