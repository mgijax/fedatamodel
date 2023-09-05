package org.jax.mgi.fe.datamodel;

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

/** represents one (marker, reference) pair, noting that a given reference
 * includes expression data for a given marker.  Exactly how (by what assay
 * type) and when (age of specimen) expression was studied are included in
 * related GxdLitAssayTypeAgePair objects.
 */
@Entity
@Table(name="expression_index")
@SecondaryTables (
    { 
      @SecondaryTable (name="expression_index_counts", pkJoinColumns= {
        @PrimaryKeyJoinColumn(name="index_key", referencedColumnName="index_key") } )
    	}
    )
public class GxdLitIndexRecord {
	
    String jnumId;		// J: number ID for the reference
    Marker marker;		// the Marker in which expression was studied
    String markerName;		// name of the marker, cached here
    Reference reference;	// reference containing expression data
    String markerSymbol;	// symbol of the marker, cached here
    String comments;		// note for this (marker, reference) pair
    int indexKey;		// unique key for this (marker, ref) pair

    // number of assays for this marker/reference pair which have been
    // fully-coded (with detailed expression results)
    Integer fullCodedAssayCount;
	
    // number of assay results for this marker/reference pair which have been
    // fully-coded (with detailed expression results)
    Integer fullCodedResultCount;
	
    // list of (age, assay type) pairs showing when and how expression was
    // studied for this marker in this reference
    List<GxdLitAssayTypeAgePair> pairs;
	
    // getters

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

	/** convenience method to return the reference's long citation
	 */
	@Transient
	public String getLongCitation() {
		return this.getReference().getLongCitation();
	}

	/** only retrieves the full Marker object when it is requested
	 */
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

	/** only retrieves the full Reference object when it is requested
	 */
	@ManyToOne(targetEntity=Reference.class, fetch=FetchType.LAZY)
	@JoinColumn (name="reference_key")
    public Reference getReference() {
		return reference;
	}

	/** convenience method - has this reference's data been fully coded
	 * for this marker (1) or not (0)?
	 */
	@Transient
    public Boolean isFullyCoded() {
    	if (this.fullCodedAssayCount > 0) {
    		return true;
    	}
    	return false;
    }
	
    // setters

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
