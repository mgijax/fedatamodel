package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="batch_marker_terms")
public class BatchMarkerId {

	private Integer batchId;
	private String term;
	private String termType;
	private Marker marker;
	private StrainMarker strainMarker;

	@Id
	@Column(name="unique_key")
	public Integer getBatchId() {
		return batchId;
	}

	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}

	@Column(name="term")
	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	@Column(name="term_type")
	public String getTermType() {
		return termType;
	}

	public void setTermType(String termType) {
		this.termType = termType;
	}

	@OneToOne (targetEntity=Marker.class,fetch=FetchType.LAZY)
	@JoinColumn(name="marker_key", referencedColumnName="marker_key", nullable=true)
	public Marker getMarker() {
		return marker;
	}

	public void setMarker(Marker marker) {
		this.marker = marker;
	}

	@OneToOne (targetEntity=StrainMarker.class,fetch=FetchType.LAZY)
	@JoinColumn(name="strain_marker_key", referencedColumnName="strain_marker_key", nullable=true)
	public StrainMarker getStrainMarker() {
		return strainMarker;
	}

	public void setStrainMarker(StrainMarker strainMarker) {
		this.strainMarker = strainMarker;
	}
	
	@Transient
	public String getUniqueKey() {
		if (marker != null) {
			return "m" + marker.getMarkerKey();
		}
		if (strainMarker != null) {
			return "c" + strainMarker.getStrainMarkerKey();
		}
		return "missing";
	}
}
