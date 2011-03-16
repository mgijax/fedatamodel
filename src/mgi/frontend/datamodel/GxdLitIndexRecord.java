package mgi.frontend.datamodel;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="expression_index")
@SecondaryTables (
    { 
      @SecondaryTable (name="expression_index_counts", pkJoinColumns= {
        @PrimaryKeyJoinColumn(name="index_key", referencedColumnName="index_key") } )
    	}
    )
public class GxdLitIndexRecord {
	
	String jnumId;
	
	Marker marker;
	
	String markerName;
	
	Reference reference;

	Integer fullCodedAssayCount;
	
	Integer fullCodedResultCount;
	
    String markerSymbol;

    String comments;
    
    int indexKey;

	List<GxdLitAssayTypeAgePair> pairs;
	
	@Column(name="comments")
    public String getComments() {
		return comments;
	}
    
	@Column(table="expression_index_counts", name="fully_coded_assay_count")
    @JoinColumn(name="index_key")
	public Integer getFullCodedAssayCount() {
		return fullCodedAssayCount;
	}
	@Column(table="expression_index_counts", name="fully_coded_result_count")
    @JoinColumn(name="index_key")
	public Integer getFullCodedResultCount() {
		return fullCodedResultCount;
	}

	@Id
	@Column(name="index_key")
	public int getIndexKey() {
		return indexKey;
	}
	
	@Column(name="jnum_id")
	public String getJnumId() {
		return jnumId;
	}

	@Transient
	public String getLongCitation() {
		return this.getReference().getLongCitation();
	}

	@ManyToOne(targetEntity=Marker.class, fetch=FetchType.LAZY)
	@JoinColumn (name="marker_key")
    public Marker getMarker() {
		return marker;
	}

	@Column(name="marker_name")
	public String getMarkerName() {
		return markerName;
	}

	@Column(name="marker_symbol")
	public String getMarkerSymbol() {
		return markerSymbol;
	}

	@OneToMany
    @JoinTable (name="expression_index_stages",
            joinColumns=@JoinColumn(name="index_key"),
            inverseJoinColumns=@JoinColumn(name="unique_key")
            )	
	public List<GxdLitAssayTypeAgePair> getPairs() {
		return pairs;
	}

	@ManyToOne(targetEntity=Reference.class, fetch=FetchType.LAZY)
	@JoinColumn (name="reference_key")
    public Reference getReference() {
		return reference;
	}

	@Transient
    public Boolean isFullyCoded() {
    	if (this.fullCodedAssayCount > 0) {
    		return true;
    	}
    	return false;
    }
	
	public void setComments(String comments) {
		this.comments = comments;
	}

	public void setFullCodedAssayCount(Integer fullCodedAssayCount) {
		this.fullCodedAssayCount = fullCodedAssayCount;
	}
	
	public void setFullCodedResultCount(Integer fullCodedResultCount) {
		this.fullCodedResultCount = fullCodedResultCount;
	}

	public void setIndexKey(int indexKey) {
		this.indexKey = indexKey;
	}

	public void setJnumId(String jnumId) {
		this.jnumId = jnumId;
	}

	public void setMarker(Marker marker) {
		this.marker = marker;
	}
	
	public void setMarkerName(String markerName) {
		this.markerName = markerName;
	}
	public void setMarkerSymbol(String markerSymbol) {
		this.markerSymbol = markerSymbol;
	}
	public void setPairs(List<GxdLitAssayTypeAgePair> pairs) {
		this.pairs = pairs;
	}
	public void setReference(Reference reference) {
		this.reference = reference;
	}
	
}
