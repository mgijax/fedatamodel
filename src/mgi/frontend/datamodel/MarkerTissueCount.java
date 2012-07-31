package mgi.frontend.datamodel;

import javax.persistence.*;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * MarkerTissueCount
 * @author jsb
 * This object represents the counts of expression results for one
 * marker/tissue pair.  There are three counts:  detected, not detected,
 * and all.
 */
@Entity
@Table(name="marker_tissue_expression_counts")
public class MarkerTissueCount {

	private int uniqueKey;
	private int structureKey;
	private String structure;			// Theiler stage plus printname
	private int allResultCount;			// detected + not detected counts
	private int detectedResultCount;
	private int notDetectedResultCount;
	private int sequenceNum;			// for ordering
	private Marker marker;

	// ================= Getters and Setters ===================== //

	@Column(name="all_result_count")
	public int getAllResultCount() {
		return allResultCount;
	}

	@Column(name="detected_count")
	public int getDetectedResultCount() {
		return detectedResultCount;
	}

	@Transient
	public int getMarkerKey() {
		return marker.getMarkerKey();
	}

	@Column(name="not_detected_count")
	public int getNotDetectedResultCount() {
		return notDetectedResultCount;
	}

	@Column(name="sequence_num")
	public int getSequenceNum() {
		return sequenceNum;
	}

	@Column(name="structure")
	public String getStructure() {
		return structure;
	}

	@Column(name="structure_key")
	public int getStructureKey() {
		return structureKey;
	}
	
	// look up the primary mgi id for building links
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="marker_key")
	public Marker getMarker()
	{
		return marker;
	}

	@Transient
	public String getMarkerId()
	{
		return marker.getPrimaryID();
	}
	@Id
	@Column(name="unique_key")
	public int getUniqueKey() {
		return uniqueKey;
	}

	public void setAllResultCount(int allResultCount) {
		this.allResultCount = allResultCount;
	}

	public void setDetectedResultCount(int detectedResultCount) {
		this.detectedResultCount = detectedResultCount;
	}

	public void setNotDetectedResultCount(int notDetectedResultCount) {
		this.notDetectedResultCount = notDetectedResultCount;
	}

	public void setSequenceNum(int sequenceNum) {
		this.sequenceNum = sequenceNum;
	}

	public void setStructure(String structure) {
		this.structure = structure;
	}

	public void setStructureKey(int structureKey) {
		this.structureKey = structureKey;
	}
	
	public void setMarker(Marker marker)
	{
		this.marker = marker;
	}

	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	@Override
	public String toString() {
		return "MarkerTissueCount [allResultCount=" + allResultCount
				+ ", detectedResultCount=" + detectedResultCount
				+ ",  notDetectedResultCount="
				+ notDetectedResultCount + ", sequenceNum=" + sequenceNum
				+ ", "
				+ (structure != null ? "structure=" + structure + ", " : "")
				+ "structureKey=" + structureKey + ", uniqueKey=" + uniqueKey
				+ "]";
	}
}
