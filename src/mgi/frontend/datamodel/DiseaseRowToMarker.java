package mgi.frontend.datamodel;

import javax.persistence.*;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * DiseaseRowToMarker
 * @author jsb
 * This object is a join table between a disease row (on a disease detail
 * page) and a marker in that disease row.
 */
@Entity
@Table(name="disease_row_to_marker")
public class DiseaseRowToMarker {

	private int uniqueKey;
	private int diseaseRowKey;
	private Marker marker;
	private int sequenceNum;
	private int isCausative;
	private String organism;

    // ================= Convenience Methods ===================== //

	@Transient
	public String getSymbol() {
		return this.marker.getSymbol();
	}
    
	@Transient
	public String getPrimaryID() {
		return this.marker.getPrimaryID();
	}

    // ================= Getters and Setters ===================== //

	@ManyToOne (targetEntity=Marker.class, fetch=FetchType.LAZY)
	@JoinColumn (name="marker_key")
	public Marker getMarker() {
		return marker;
	}

	@Id
	@Column(name="unique_key")
	public int getUniqueKey() {
		return uniqueKey;
	}

	@Column(name="disease_row_key")
	public int getDiseaseRowKey() {
		return diseaseRowKey;
	}

	@Column(name="is_causative")
	public int getIsCausative() {
		return isCausative;
	}

	@Column(name="organism")
	public String getOrganism() {
		return organism;
	}

	@Column(name="sequence_num")
	public int getSequenceNum() {
		return sequenceNum;
	}

	public void setMarker(Marker marker) {
		this.marker = marker;
	}

	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	public void setDiseaseRowKey(int diseaseRowKey) {
		this.diseaseRowKey = diseaseRowKey;
	}

	public void setIsCausative(int isCausative) {
		this.isCausative = isCausative;
	}

	public void setOrganism(String organism) {
		this.organism = organism;
	}

	public void setSequenceNum(int sequenceNum) {
		this.sequenceNum = sequenceNum;
	}

	@Override
	public String toString() {
		return "DiseaseRowToMarker [uniqueKey="
				+ uniqueKey
				+ ", organism="
				+ organism
				+ ", "
				+ isCausative
				+ ", "
				+ sequenceNum + "]";
	}
}
