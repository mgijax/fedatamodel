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
	
	String markerName;
	
	Reference reference;

	Integer fullCodedCount;
	
    @Column(table="expression_index_counts", name="fully_coded_assay_count")
    @JoinColumn(name="index_key")
	public Integer getFullCodedCount() {
		return fullCodedCount;
	}

    @Transient
    public Boolean isFullyCoded() {
    	if (this.fullCodedCount > 0) {
    		return true;
    	}
    	return false;
    }
    
	public void setFullCodedCount(Integer fullCodedCount) {
		this.fullCodedCount = fullCodedCount;
	}
	String markerSymbol;

	int indexKey;

	List<GxdLitAssayTypeAgePair> pairs;

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

	public void setIndexKey(int indexKey) {
		this.indexKey = indexKey;
	}

	public void setJnumId(String jnumId) {
		this.jnumId = jnumId;
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
