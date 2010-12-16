package mgi.frontend.datamodel;

import javax.persistence.*;

import java.util.List;

/**
 * ExpressionAssay
 * @author jsb
 * This object represents the data for one expression assay.
 */
@Entity
@Table(name="expression_assay")
public class ExpressionAssay {
	private int assayKey;
	private String assayType;
	private String primaryID;
	private int probeKey;
	private String probeName;
	private String antibody;
	private String detectionSystem;
	private int isDirectDetection;
	private String probePreparation;
	private String visualizedWith;
	private String reporterGene;
	private String note;
	private int hasImage;
	private int referenceKey;
	private int markerKey;
	private String markerID;
	private String markerSymbol;
	
	@Column(name="antibody")
	public String getAntibody() {
		return antibody;
	}
	
	@Id
	@Column(name="assay_key")
	public int getAssayKey() {
		return assayKey;
	}
	
	@Column(name="assay_type")
	public String getAssayType() {
		return assayType;
	}
	
	@Column(name="detection_system")
	public String getDetectionSystem() {
		return detectionSystem;
	}
	
	@Column(name="has_image")
	public int getHasImage() {
		return hasImage;
	}
	
	@Column(name="is_direct_detection")
	public int getIsDirectDetection() {
		return isDirectDetection;
	}
	
	@Column(name="marker_id")
	public String getMarkerID() {
		return markerID;
	}
	
	@Column(name="marker_key")
	public int getMarkerKey() {
		return markerKey;
	}
	
	@Column(name="marker_symbol")
	public String getMarkerSymbol() {
		return markerSymbol;
	}
	
	@Column(name="note")
	public String getNote() {
		return note;
	}
	
	@Column(name="primary_id")
	public String getPrimaryID() {
		return primaryID;
	}
	
	@Column(name="probe_key")
	public int getProbeKey() {
		return probeKey;
	}
	
	@Column(name="probe_name")
	public String getProbeName() {
		return probeName;
	}
	
	@Column(name="probe_preparation")
	public String getProbePreparation() {
		return probePreparation;
	}
	
	@Column(name="reference_key")
	public int getReferenceKey() {
		return referenceKey;
	}
	
	@Column(name="reporter_gene")
	public String getReporterGene() {
		return reporterGene;
	}
	
	@Column(name="visualized_with")
	public String getVisualizedWith() {
		return visualizedWith;
	}
	
	public void setAntibody(String antibody) {
		this.antibody = antibody;
	}
	
	public void setAssayKey(int assayKey) {
		this.assayKey = assayKey;
	}
	
	public void setAssayType(String assayType) {
		this.assayType = assayType;
	}
	
	public void setDetectionSystem(String detectionSystem) {
		this.detectionSystem = detectionSystem;
	}
	
	public void setHasImage(int hasImage) {
		this.hasImage = hasImage;
	}
	
	public void setIsDirectDetection(int isDirectDetection) {
		this.isDirectDetection = isDirectDetection;
	}
	
	public void setMarkerID(String markerID) {
		this.markerID = markerID;
	}
	
	public void setMarkerKey(int markerKey) {
		this.markerKey = markerKey;
	}
	
	public void setMarkerSymbol(String markerSymbol) {
		this.markerSymbol = markerSymbol;
	}
	
	public void setNote(String note) {
		this.note = note;
	}
	
	public void setPrimaryID(String primaryID) {
		this.primaryID = primaryID;
	}
	
	public void setProbeKey(int probeKey) {
		this.probeKey = probeKey;
	}
	
	public void setProbeName(String probeName) {
		this.probeName = probeName;
	}
	
	public void setProbePreparation(String probePreparation) {
		this.probePreparation = probePreparation;
	}
	
	public void setReferenceKey(int referenceKey) {
		this.referenceKey = referenceKey;
	}
	
	public void setReporterGene(String reporterGene) {
		this.reporterGene = reporterGene;
	}
	
	public void setVisualizedWith(String visualizedWith) {
		this.visualizedWith = visualizedWith;
	}
	
	@Override
	public String toString() {
		return "ExpressionAssay ["
				+ (antibody != null ? "antibody=" + antibody + ", " : "")
				+ "assayKey="
				+ assayKey
				+ ", "
				+ (assayType != null ? "assayType=" + assayType + ", " : "")
				+ (detectionSystem != null ? "detectionSystem="
						+ detectionSystem + ", " : "")
				+ "hasImage="
				+ hasImage
				+ ", isDirectDetection="
				+ isDirectDetection
				+ ", "
				+ (markerID != null ? "markerID=" + markerID + ", " : "")
				+ "markerKey="
				+ markerKey
				+ ", "
				+ (markerSymbol != null ? "markerSymbol=" + markerSymbol + ", "
						: "")
				+ (note != null ? "note=" + note + ", " : "")
				+ (primaryID != null ? "primaryID=" + primaryID + ", " : "")
				+ "probeKey="
				+ probeKey
				+ ", "
				+ (probeName != null ? "probeName=" + probeName + ", " : "")
				+ (probePreparation != null ? "probePreparation="
						+ probePreparation + ", " : "")
				+ "referenceKey="
				+ referenceKey
				+ ", "
				+ (reporterGene != null ? "reporterGene=" + reporterGene + ", "
						: "")
				+ (visualizedWith != null ? "visualizedWith=" + visualizedWith
						: "") + "]";
	}
}