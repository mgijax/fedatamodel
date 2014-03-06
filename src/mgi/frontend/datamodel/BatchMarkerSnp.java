package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * A very simple representation of a SNP for associating with the batch
 * query's markers.  It only captures the ID of the SNP, and
 * a sequence number for ordering a marker's SNP IDs.
 * @author jsb
 */
        
@Entity
@Table(name="batch_marker_snps")
public class BatchMarkerSnp {
    
    private int uniqueKey;
    private int markerKey;
    private String snpID;
    
    // ================= Getters and Setters ===================== //

    @Column(name="snp_id")
    public String getSnpID() {
		return snpID;
	}

    @Column(name="marker_key")
	public int getMarkerKey() {
		return markerKey;
	}

	@Id
    @Column(name="unique_key")
    public int getUniqueKey() {
		return uniqueKey;
	}

	public void setSnpID(String snpID) {
		this.snpID = snpID;
	}

	public void setMarkerKey(int markerKey) {
		this.markerKey = markerKey;
	}

	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	@Override
	public String toString() {
		return "BatchMarkerSnp [uniqueKey=" + uniqueKey + ", markerKey="
				+ markerKey + ", snpID="
				+ snpID + "]";
	}
}
