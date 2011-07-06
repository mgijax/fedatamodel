package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;
import javax.persistence.Transient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="batch_marker_terms")
public class BatchMarkerId {
	
	private Integer batchId;
	private String term;
	private String termType;
	private Marker marker;
	
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
	
	@OneToOne (targetEntity=Marker.class)
	@JoinColumn(name="marker_key")
	public Marker getMarker() {
		return marker;
	}
	
	public void setMarker(Marker marker) {
		this.marker = marker;
	}
	
}
