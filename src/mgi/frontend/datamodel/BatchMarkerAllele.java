package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A very simple representation of an allele for associating with the batch
 * query's markers.  It only captures the symbol and ID of the allele, and
 * a sequence number for ordering a marker's alleles.
 * @author jsb
 */
        
@Entity
@Table(name="batch_marker_alleles")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class BatchMarkerAllele {
    
    private int uniqueKey;
    private int markerKey;
    private int sequenceNum;
    private String alleleSymbol;
    private String alleleID;
    
    // ================= Getters and Setters ===================== //

    @Column(name="allele_id")
    public String getAlleleID() {
		return alleleID;
	}

    @Column(name="allele_symbol")
	public String getAlleleSymbol() {
		return alleleSymbol;
	}

    @Column(name="marker_key")
	public int getMarkerKey() {
		return markerKey;
	}

    @Column(name="sequence_num")
	public int getSequenceNum() {
		return sequenceNum;
	}

	@Id
    @Column(name="unique_key")
    public int getUniqueKey() {
		return uniqueKey;
	}

	public void setAlleleID(String alleleID) {
		this.alleleID = alleleID;
	}

	public void setAlleleSymbol(String alleleSymbol) {
		this.alleleSymbol = alleleSymbol;
	}

	public void setMarkerKey(int markerKey) {
		this.markerKey = markerKey;
	}

	public void setSequenceNum(int sequenceNum) {
		this.sequenceNum = sequenceNum;
	}

	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	@Override
	public String toString() {
		return "BatchMarkerAllele ["
				+ (alleleID != null ? "alleleID=" + alleleID + ", " : "")
				+ (alleleSymbol != null ? "alleleSymbol=" + alleleSymbol + ", "
						: "") + "markerKey=" + markerKey + ", sequenceNum="
				+ sequenceNum + ", uniqueKey=" + uniqueKey + "]";
	}
}
