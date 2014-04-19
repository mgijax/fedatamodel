package mgi.frontend.datamodel;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import mgi.frontend.datamodel.util.DatamodelUtils;

import org.hibernate.annotations.BatchSize;

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

	private String reporterGene;
	private String note;
	private int hasImage;
	private int isDirectDetection;

	private Integer probeKey;
	private String probeName;
	private String probePreparation;
	private String visualizedWith;

	private String antibody;
	private String detectionSystem;
	private Integer antibodyKey;

 	//private int referenceKey;
	private int markerKey;
	private String markerID;
	private String markerSymbol;
	private String markerName;
	private String modificationDate; //modification_date
	
	//private Integer gelImagePaneKey;
	private Reference reference;
	private ImagePane gelImagePane;

	private List<AssaySpecimen> assaySpecimens;
	private List<GelLane> gelLanes;
	
	/* fields not managed by hibernate */
	/* calculated + cached fields */
	// the row information is duplicated in the gel bands, se can cache the bands for one lane
	private List<GelBand> gelRowsCache=null;
	// the note information for blot assays
	private List<BlotFootnote> blotNotesCache=null;
	// this is not visible, but just used to keep track of adding footnote letters to the various blot assay notes
	private List<String> uniqueBlotNotes=new ArrayList<String>();
	private Boolean hasLaneNotes=null;

    // ================= Getters ===================== //

    @Column(name="antibody")
	public String getAntibody() {
		return antibody;
	}

	@Column(name="antibody_key")
	public Integer getAntibodyKey() {
		return antibodyKey;
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

	@Column(name="marker_name")
	public String getMarkerName() {
		return markerName;
	}

	@Column(name="modification_date")
	public String getModificationDate() {
		return modificationDate;
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
	public Integer getProbeKey() {
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

//	@Column(name="reference_key")
//	public int getReferenceKey() {
//		return referenceKey;
//	}

	@Column(name="reporter_gene")
	public String getReporterGene() {
		return reporterGene;
	}

	@Column(name="visualized_with")
	public String getVisualizedWith() {
		return visualizedWith;
	}

	@OneToMany(targetEntity=AssaySpecimen.class)
	@BatchSize(size=100)
	@JoinColumn(name="assay_key")
    @OrderBy("specimenSeq")
    public List<AssaySpecimen> getAssaySpecimens() {
        return this.assaySpecimens;
    }
	
	@OneToMany(targetEntity=GelLane.class)
	@BatchSize(size=250)
	@JoinColumn(name="assay_key")
    @OrderBy("laneSeq")
    public List<GelLane> getGelLanes() {
        return this.gelLanes;
    }
	
//	@Column(name="gel_imagepane_key")
//	public Integer getGelImagePaneKey() {
//		return gelImagePaneKey;
//	}
	@OneToOne(targetEntity=ImagePane.class)
	@JoinColumn(name="gel_imagepane_key")
	public ImagePane getGelImagePane() {
		return gelImagePane;
	}

	@OneToOne(targetEntity=Reference.class)
	@JoinColumn(name="reference_key")
	public Reference getReference() {
		return reference;
	}
	
	// -----------------Transient Methods --------------//
	@Transient
	public boolean getHasGelImage()
	{ return this.getGelImagePane()!=null; }

	@Transient
	public boolean getHasLaneNotes()
	{
		if(this.hasLaneNotes==null) initGelRowsCache();
		return this.hasLaneNotes;
	}
	@Transient
	public boolean getHasSampleAmount()
	{ return !"Western blot".equals(this.assayType); }
	/* We have numerous methods here to control the column spans for the blot assays */
	@Transient
	public int getBandsColSpan()
	{
		int numGelRows = getGelRowCache().size();
		return numGelRows==0 ? 1 : numGelRows;
	}
	
	@Transient
	public int getSampleAmountColSpan()
	{
		// 1 or 0 depending on assay type
		return getHasSampleAmount() ? 1 : 0;
	}
	@Transient 
	public int getNoteColSpan()
	{
		// 1 or 0 depending on if notes
		return getHasLaneNotes() ? 1 : 0;
	}
	
	@Transient
	public int getControlLaneColSpan()
	{
		// first two columns are age and structure
		return 2+this.getBandsColSpan()+this.getOtherInfoColSpan();
	}
	@Transient 
	public int getOtherInfoColSpan()
	{
		return getSampleAmountColSpan()+3+getNoteColSpan();
	}
	@Transient
	public List<GelBand> getGelRowCache()
	{
		if(gelRowsCache==null) initGelRowsCache();
		return gelRowsCache;
	}
	
	/* handle the gel footnotes, which are Age,Row, and Band */
	@Transient
	public List<BlotFootnote> getBlotNotes()
	{
		if(blotNotesCache==null) initGelRowsCache();
		return blotNotesCache;
	}
	/* Loop through all the gel lanes once to cache the information necessary for the above methods */
	private void initGelRowsCache()
	{
		gelRowsCache = new ArrayList<GelBand>();
		boolean foundGelRows=false;
		this.hasLaneNotes=false;
		blotNotesCache = new ArrayList<BlotFootnote>();
		for(GelLane lane : this.getGelLanes())
		{
			// if this is not a control lane
			if(!foundGelRows && !lane.getIsControl())
			{
				for(GelBand band : lane.getBands())
				{
					// caching one lane worth of bands to store the gel row information is sufficient
					gelRowsCache.add(band);
				}
				// we only want to add the bands for one lane
				foundGelRows=true;
			}
			// cache the different footnotes and calculate what "letters" they get (i.e. a,b,c,..z)
			//Lane notes now appear in their own column, but need to keep track of when they occur
			String laneNote = lane.getLaneNote();
			if(!this.hasLaneNotes && laneNote!=null && !laneNote.equals(""))
				this.hasLaneNotes=true;
			// then age note
			String ageNote = lane.getAgeNote();
			if(ageNote!=null && !ageNote.equals(""))
			{
				lane.setAgeNoteLetter(getBlotFootnoteLetter(ageNote));
			}
			for(GelBand band : lane.getBands())
			{
				// then row note
				String rowNote = band.getRowNote();
				if(rowNote!=null && !rowNote.equals(""))
				{
					band.setRowNoteLetter(getBlotFootnoteLetter(rowNote));
				}
				// then band note
				String bandNote = band.getBandNote();
				if(bandNote!=null && !bandNote.equals(""))
				{
					band.setBandNoteLetter(getBlotFootnoteLetter(bandNote));
				}
			}
		}
	}

	/* 
	 * method to keep track of assigning letters to each footnotes 
	 *	duplicates should reuse the same letter.
	 * NOTE: this function has the side-effect of adding the footnote display text to the blotNotesCache
	 * */
	@Transient
	private String getBlotFootnoteLetter(String footnote)
	{
		String footnoteLetter = null;
		if(this.uniqueBlotNotes.contains(footnote))
		{
			footnoteLetter = DatamodelUtils.getCharacterForFootnoteList(this.uniqueBlotNotes.indexOf(footnote));
		}
		else
		{
			footnoteLetter = DatamodelUtils.getCharacterForFootnoteList(this.uniqueBlotNotes.size());
			// track this note in the unique note list
			this.uniqueBlotNotes.add(footnote); 
			// Add the formatted display string to the blot notes caches (HINT: this is displayed at the bottom of blot assay detail pages)
			this.blotNotesCache.add(new BlotFootnote(footnoteLetter,footnote));
		}
		return footnoteLetter;
	}
    // ================= Setters ===================== //

	public void setAssaySpecimens(List<AssaySpecimen> assaySpecimens) {
        this.assaySpecimens=assaySpecimens;
	}

	public void setGelLanes(List<GelLane> gelLanes) {
        this.gelLanes=gelLanes;
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

	public void setModificationDate(String modificationDate) {
		this.modificationDate = modificationDate;
	}

	public void setMarkerName(String markerName) {
		this.markerName = markerName;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public void setPrimaryID(String primaryID) {
		this.primaryID = primaryID;
	}

	public void setProbeKey(Integer probeKey) {
		this.probeKey = probeKey;
	}

	public void setAntibodyKey(Integer antibodyKey) {
		this.antibodyKey = antibodyKey;
	}

	public void setProbeName(String probeName) {
		this.probeName = probeName;
	}

	public void setProbePreparation(String probePreparation) {
		this.probePreparation = probePreparation;
	}

//	public void setReferenceKey(int referenceKey) {
//		this.referenceKey = referenceKey;
//	}
	public void setReference(Reference reference)
	{
		this.reference = reference;
	}

	public void setReporterGene(String reporterGene) {
		this.reporterGene = reporterGene;
	}

	public void setVisualizedWith(String visualizedWith) {
		this.visualizedWith = visualizedWith;
	}
//
//	public void setGelImagePaneKey(Integer gelImagePaneKey) {
//		this.gelImagePaneKey = gelImagePaneKey;
//	}

	public void setGelImagePane(ImagePane gelImagePane) {
		this.gelImagePane = gelImagePane;
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
//				+ "referenceKey="
//				+ referenceKey
//				+ ", "
				+ (reporterGene != null ? "reporterGene=" + reporterGene + ", "
						: "")
				+ (visualizedWith != null ? "visualizedWith=" + visualizedWith
						: "") + "]";
	}
	
	// Small private helper class for making it easier to deal with footnotes JSP side
	public class BlotFootnote
	{
		private String footnoteLetter;
		private String note;
		public BlotFootnote(String footnoteLetter,String note)
		{
			this.footnoteLetter=footnoteLetter;
			this.note=note;
		}
		public String getFootnoteLetter()
		{
			return this.footnoteLetter;
		}
		public String getNote()
		{
			return this.note;
		}
	}
}