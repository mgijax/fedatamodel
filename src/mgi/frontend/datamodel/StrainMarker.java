package mgi.frontend.datamodel;

import java.text.DecimalFormat;
import java.text.NumberFormat;
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
	private int canonicalMarkerKey;
	private int strainKey;
	private String strainName;
	private String strainID;
	private String featureType;
	private String chromosome;
	private Double startCoordinate;
	private Double endCoordinate;
	private String strand;
	private int length;
	private int sequenceNum;
	List<StrainMarkerGeneModel> geneModels;
	
    // ================= Getters and Setters ===================== //
	
	@Column(name="canonical_marker_key")
	public int getCanonicalMarkerKey() {
		return canonicalMarkerKey;
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
	public int getLength() {
		return length;
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

	public void setCanonicalMarkerKey(int canonicalMarkerKey) {
		this.canonicalMarkerKey = canonicalMarkerKey;
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

	public void setLength(int length) {
		this.length = length;
	}

	@Transient
	public String getLocation() {
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
