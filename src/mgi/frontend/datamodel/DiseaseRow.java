package mgi.frontend.datamodel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;

/**
 * DiseaseRow
 * @author jsb
 * This object represents one row of a disease group on a disease detail page.
 */
@Entity
@Table(name="disease_row")
public class DiseaseRow {

	private int diseaseRowKey;
	private int diseaseGroupKey;
	private int sequenceNum;
	private HomologyCluster homologyCluster;
	private List<DiseaseRowToMarker> diseaseRowToMarkers;
	private List<DiseaseRowToModel> diseaseRowToModels;
	private List<DiseaseGroupRow> diseaseGroupRows;

    // ================= Convenience Methods ===================== //

	
	@Transient
	private List<DiseaseRowToMarker> filterDiseaseRowToMarkers(String organism) {

		ArrayList<DiseaseRowToMarker> sublist = new ArrayList<DiseaseRowToMarker>();

		if (diseaseRowToMarkers == null) {
			return sublist;
		}

		Iterator<DiseaseRowToMarker> it = diseaseRowToMarkers.iterator();
		DiseaseRowToMarker drtm;

		while (it.hasNext()) {
			drtm = it.next();
			if (organism.equals(drtm.getOrganism()) ) {
				sublist.add(drtm);
			}
		}
		return sublist;
	}

	@Transient
	public List<Marker> getCausativeMouseMarkers() {
		// this is the list we will compose to return
		ArrayList<Marker> sublist = new ArrayList<Marker>();

		// walks through all mouse markers
		Iterator<DiseaseRowToMarker> drtmIt = getMouseMarkers().iterator();

		// one item from 'drtmIt'
		DiseaseRowToMarker drtm;

		while (drtmIt.hasNext()) {
			drtm = drtmIt.next();

			if (drtm.getIsCausative() != 0) {
				sublist.add (drtm.getMarker());
			}
		}
		return sublist;
	}

	@Transient
	public List<DiseaseRowToMarker> getMouseMarkers() {
		return filterDiseaseRowToMarkers("mouse");
	}

	@Transient
	public List<DiseaseRowToMarker> getHumanMarkers() {
		return filterDiseaseRowToMarkers("human");
	}
 
	@Transient
	public List<DiseaseModel> getAllMouseModels() {
		ArrayList<DiseaseModel> models =
			new ArrayList<DiseaseModel>();

		if (this.diseaseRowToModels == null) {
			return models;
		}

		Iterator<DiseaseRowToModel> it = diseaseRowToModels.iterator();

		while (it.hasNext()) {
			models.add(it.next().getDiseaseModel());
		}
		return models;
	}

	@Transient
	private List<DiseaseModel> filterMouseModels(int isNotModel) {
		ArrayList<DiseaseModel> models = new ArrayList<DiseaseModel>();

		if (diseaseRowToModels == null) {
			return models;
		}

		DiseaseModel dm;
		Iterator<DiseaseRowToModel> it = diseaseRowToModels.iterator();

		while (it.hasNext()) {
			dm = it.next().getDiseaseModel();
			if (dm.getIsNotModel() == isNotModel) {
				models.add(dm);
			}
		}
		return models;
	}

	@Transient
	public List<DiseaseModel> getMouseModels() {
		return filterMouseModels(0);
	}
 
	@Transient
	public List<DiseaseModel> getNotModels() {
		return filterMouseModels(1);
	}
 
    // ================= Getters and Setters ===================== //

	@OneToMany (targetEntity=DiseaseGroupRow.class )
	public List<DiseaseGroupRow> getDiseaseGroupRows() {
		return diseaseGroupRows;
	}

	public void setDiseaseGroupRows(List<DiseaseGroupRow> diseaseGroupRows) {
		this.diseaseGroupRows = diseaseGroupRows;
	}
	
	@ManyToOne (targetEntity=HomologyCluster.class, fetch=FetchType.LAZY)
	@BatchSize(size=100)
	@JoinColumn (name="cluster_key")
	public HomologyCluster getHomologyCluster() {
		return homologyCluster;
	}

	@Column(name="disease_group_key")
	public int getDiseaseGroupKey() {
		return diseaseGroupKey;
	}

	@Id
	@Column(name="disease_row_key")
	public int getDiseaseRowKey() {
		return diseaseRowKey;
	}

	@OneToMany (targetEntity=DiseaseRowToMarker.class)
	@JoinColumn (name="disease_row_key")
	@BatchSize(size=100)
	@OrderBy ("sequenceNum")
	public List<DiseaseRowToMarker> getDiseaseRowToMarkers() {
		return diseaseRowToMarkers;
	}

	@OneToMany (targetEntity=DiseaseRowToModel.class)
	@JoinColumn (name="disease_row_key")
	@BatchSize(size=100)
	@OrderBy ("sequenceNum")
	public List<DiseaseRowToModel> getDiseaseRowToModels() {
		return diseaseRowToModels;
	}

	@Column(name="sequence_num")
	public int getSequenceNum() {
		return sequenceNum;
	}

	public void setHomologyCluster(HomologyCluster homologyCluster) {
		this.homologyCluster = homologyCluster;
	}

	public void setDiseaseGroupKey(int diseaseGroupKey) {
		this.diseaseGroupKey = diseaseGroupKey;
	}

	public void setDiseaseRowKey(int diseaseRowKey) {
		this.diseaseRowKey = diseaseRowKey;
	}

	public void setDiseaseRowToMarkers(
			List<DiseaseRowToMarker> diseaseRowToMarkers) {
		this.diseaseRowToMarkers = diseaseRowToMarkers;
	}

	public void setDiseaseRowToModels(
			List<DiseaseRowToModel> diseaseRowToModels) {
		this.diseaseRowToModels = diseaseRowToModels;
	}

	public void setSequenceNum(int sequenceNum) {
		this.sequenceNum = sequenceNum;
	}

	@Override
	public String toString() {
		return "DiseaseRow [diseaseGroupKey="
				+ diseaseGroupKey
				+ ", diseaseRowKey="
				+ diseaseRowKey
				+ ", "
				+ sequenceNum + "]";
	}
}
