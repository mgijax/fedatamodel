package mgi.frontend.datamodel;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;

/**
 * Is: a strain-specific version of a marker
 */
@Entity
@Table(name="strain_marker")
public class StrainMarker {
	private int strainMarkerKey;
	private Integer canonicalMarkerKey;
	private String canonicalMarkerID;
	private String canonicalMarkerSymbol;
	private String canonicalMarkerName;
	private int strainKey;
	private String strainName;
	private String strainID;
	private String featureType;
	private String chromosome;
	private Double startCoordinate;
	private Double endCoordinate;
	private String strand;
	private Integer length;
	private int sequenceNum;
	List<StrainMarkerGeneModel> geneModels;
	
    // ================= Getters and Setters ===================== //
	
	@Transient
	public String getFirstGeneModelID() {
		List<StrainMarkerGeneModel> models = this.getGeneModels();
		if (models != null) {
			return models.get(0).getGeneModelID();
		}
		return null;
	}
	
	/* If this strain marker has an MGI-generated ID, then we want to prefer just
	 * that one and skip any others.  Otherwise, just return them all.
	 */
	@Transient
	public List<StrainMarkerGeneModel> getPreferredGeneModels() {
		for (StrainMarkerGeneModel gm : this.getGeneModels()) {
			if ("MGI Strain Gene".equals(gm.getLogicalDB())) {
				List<StrainMarkerGeneModel> mgiOnly = new ArrayList<StrainMarkerGeneModel>();
				mgiOnly.add(gm);
				return mgiOnly;
			}
		}
		return this.getGeneModels();
	}
	
	@Column(name="canonical_marker_id")
	public String getCanonicalMarkerID() {
		return canonicalMarkerID;
	}

	@Column(name="canonical_marker_key")
	public Integer getCanonicalMarkerKey() {
		return canonicalMarkerKey;
	}

	@Column(name="canonical_marker_symbol")
	public String getCanonicalMarkerSymbol() {
		return canonicalMarkerSymbol;
	}

	@Column(name="canonical_marker_name")
	public String getCanonicalMarkerName() {
		return canonicalMarkerName;
	}

	@Column(name="chromosome")
	public String getChromosome() {
		return chromosome;
	}

	@Column(name="end_coordinate")
	public Double getEndCoordinate() {
		return endCoordinate;
	}

	@Column(name="feature_type")
	public String getFeatureType() {
		return featureType;
	}

	@OneToMany (targetEntity=StrainMarkerGeneModel.class)
	@JoinColumn(name="strain_marker_key")
	@BatchSize(size=100)
	@OrderBy("sequence_num")
	public List<StrainMarkerGeneModel> getGeneModels() {
		return geneModels;
	}

	@Column(name="length")
	public Integer getLength() {
		return length;
	}

	@Transient
	public String getLocation() {
		if (this.chromosome == null) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		sb.append("Chr");
		sb.append(this.chromosome);

		NumberFormat nf = new DecimalFormat("#0");

		sb.append (":");
		sb.append (nf.format(this.startCoordinate));
		sb.append ("-");
		sb.append (nf.format(this.endCoordinate));

		if (this.strand != null) {
			sb.append (" (");
			sb.append (strand);
			sb.append (")");
		}
		return sb.toString();
	}

	@Transient
	public boolean getNoAnnotation() {
		return (this.featureType == null) || (this.featureType.equals(""));
	}

	@Column(name="sequence_num")
	public int getSequenceNum() {
		return sequenceNum;
	}

	@Column(name="start_coordinate")
	public Double getStartCoordinate() {
		return startCoordinate;
	}

	@Column(name="strain_id")
	public String getStrainID() {
		return strainID;
	}

	@Column(name="strain_key")
	public int getStrainKey() {
		return strainKey;
	}

	@Id
	@Column(name="strain_marker_key")
	public int getStrainMarkerKey() {
		return strainMarkerKey;
	}

	@Column(name="strain_name")
	public String getStrainName() {
		return strainName;
	}

	@Column(name="strand")
	public String getStrand() {
		return strand;
	}

	public void setCanonicalMarkerID(String canonicalMarkerID) {
		this.canonicalMarkerID = canonicalMarkerID;
	}

	public void setCanonicalMarkerKey(Integer canonicalMarkerKey) {
		this.canonicalMarkerKey = canonicalMarkerKey;
	}

	public void setCanonicalMarkerSymbol(String canonicalMarkerSymbol) {
		this.canonicalMarkerSymbol = canonicalMarkerSymbol;
	}

	public void setCanonicalMarkerName(String canonicalMarkerName) {
		this.canonicalMarkerName = canonicalMarkerName;
	}

	public void setChromosome(String chromosome) {
		this.chromosome = chromosome;
	}

	public void setEndCoordinate(Double endCoordinate) {
		this.endCoordinate = endCoordinate;
	}

	public void setFeatureType(String featureType) {
		this.featureType = featureType;
	}

	public void setGeneModels(List<StrainMarkerGeneModel> geneModels) {
		this.geneModels = geneModels;
	}

	public void setLength(Integer length) {
		this.length = length;
	}
	
	public void setSequenceNum(int sequenceNum) {
		this.sequenceNum = sequenceNum;
	}
	
	public void setStartCoordinate(Double startCoordinate) {
		this.startCoordinate = startCoordinate;
	}

	public void setStrainID(String strainID) {
		this.strainID = strainID;
	}

	public void setStrainKey(int strainKey) {
		this.strainKey = strainKey;
	}

	public void setStrainMarkerKey(int strainMarkerKey) {
		this.strainMarkerKey = strainMarkerKey;
	}

	public void setStrainName(String strainName) {
		this.strainName = strainName;
	}

	public void setStrand(String strand) {
		this.strand = strand;
	}

	@Override
	public String toString() {
		return "StrainMarker [strainMarkerKey=" + strainMarkerKey + ", canonicalMarkerKey=" + canonicalMarkerKey
				+ ", strainKey=" + strainKey + ", strainName=" + strainName + ", strainID=" + strainID
				+ ", featureType=" + featureType + ", chromosome=" + chromosome + ", startCoordinate=" + startCoordinate
				+ ", endCoordinate=" + endCoordinate + ", strand=" + strand + ", length=" + length + ", sequenceNum="
				+ sequenceNum + "]";
	}
}
