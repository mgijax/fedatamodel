package mgi.frontend.datamodel;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="expression_result_summary")
public class GxdAssayResult 
{
	private Integer resultKey;
	private Integer assayKey;
	private String assayType;
	private String assayId;
	private String markerSymbol;
	private String anatomicalSystem;
	private String theilerStage;
	private String age;
	private String ageAbbreviation;
	private String structure;
	private String structurePrintname;
	private Integer structureKey;
	private String detectionLevel;
	private String isExpressed;
	private Integer referenceKey;
	private String jnumId;
	private Integer hasImage;
	private Integer genotypeKey;
	private Marker marker;
	private Reference reference;
	private Genotype genotype;
	private List<ImagePane> imagePanes;
	private String pattern;

	@OneToOne(targetEntity=Marker.class, fetch=FetchType.LAZY)
	@JoinColumn (name="marker_key", insertable=false, updatable=false)
    public Marker getMarker() {
		return marker;
	}
	
	@OneToOne(targetEntity=Reference.class, fetch=FetchType.LAZY)
	@JoinColumn (name="reference_key", insertable=false, updatable=false)
    public Reference getReference() {
		return reference;
	}
	
	@OneToOne(targetEntity=Genotype.class, fetch=FetchType.LAZY)
	@JoinColumn (name="genotype_key", insertable=false, updatable=false)
    public Genotype getGenotype() {
		return genotype;
	}
	
	@ManyToMany()
	@JoinTable (name = "expression_result_to_imagepane", 
		joinColumns= { @JoinColumn(name="result_key")},
		inverseJoinColumns = { @JoinColumn(name="imagepane_key") }	
	)
    public List<ImagePane> getImagePanes() {
		return imagePanes;
	}

	@Id
	@Column(name="result_key")
	public Integer getResultKey() {
		return resultKey;
	}

	@Column(name="assay_key")
	public Integer getAssayKey() {
		return assayKey;
	}

	@Column(name="assay_type")
	public String getAssayType() {
		return assayType;
	}

	@Column(name="assay_id")
	public String getAssayId() {
		return assayId;
	}

//	@Column(name="marker_key")
//	public Integer getMarkerKey() {
//		return markerKey;
//	}

	@Column(name="marker_symbol")
	public String getMarkerSymbol() {
		return markerSymbol;
	}

	@Column(name="anatomical_system")
	public String getAnatomicalSystem() {
		return anatomicalSystem;
	}

	@Column(name="theiler_stage")
	public String getTheilerStage() {
		return theilerStage;
	}

	@Column(name="age")
	public String getAge() {
		return age;
	}
	
	@Column(name="age_abbreviation")
	public String getAgeAbbreviation() {
		return ageAbbreviation;
	}

	@Column(name="structure")
	public String getStructure() {
		return structure;
	}

	@Column(name="structure_printname")
	public String getStructurePrintname() {
		return structurePrintname;
	}

	@Column(name="structure_key")
	public Integer getStructureKey() {
		return structureKey;
	}

	@Column(name="detection_level")
	public String getDetectionLevel() {
		return detectionLevel;
	}

	@Column(name="is_expressed")
	public String getIsExpressed() {
		return isExpressed;
	}

	@Column(name="reference_key")
	public Integer getReferenceKey() {
		return referenceKey;
	}

	@Column(name="jnum_id")
	public String getJnumId() {
		return jnumId;
	}

	@Column(name="has_image")
	public Integer getHasImage() {
		return hasImage;
	}

	@Column(name="genotype_key")
	public Integer getGenotypeKey() {
		return genotypeKey;
	}
	
	@Column(name="pattern")
	public String getPattern()
	{
		return pattern;
	}

	public void setResultKey(Integer resultKey) {
		this.resultKey = resultKey;
	}

	public void setAssayKey(Integer assayKey) {
		this.assayKey = assayKey;
	}

	public void setAssayType(String assayType) {
		this.assayType = assayType;
	}

	public void setAssayId(String assayId) {
		this.assayId = assayId;
	}

//	public void setMarkerKey(Integer markerKey) {
//		this.markerKey = markerKey;
//	}

	public void setMarkerSymbol(String markerSymbol) {
		this.markerSymbol = markerSymbol;
	}

	public void setAnatomicalSystem(String anatomicalSystem) {
		this.anatomicalSystem = anatomicalSystem;
	}

	public void setTheilerStage(String theilerStage) {
		this.theilerStage = theilerStage;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
	public void setAgeAbbreviation(String ageAbbreviation) {
		this.ageAbbreviation = ageAbbreviation;
	}

	public void setStructure(String structure) {
		this.structure = structure;
	}

	public void setStructurePrintname(String structurePrintname) {
		this.structurePrintname = structurePrintname;
	}

	public void setStructureKey(Integer structureKey) {
		this.structureKey = structureKey;
	}

	public void setDetectionLevel(String detectionLevel) {
		this.detectionLevel = detectionLevel;
	}

	public void setIsExpressed(String isExpressed) {
		this.isExpressed = isExpressed;
	}

	public void setReferenceKey(Integer referenceKey) {
		this.referenceKey = referenceKey;
	}

	public void setJnumId(String jnumId) {
		this.jnumId = jnumId;
	}

	public void setHasImage(Integer hasImage) {
		this.hasImage = hasImage;
	}

	public void setGenotypeKey(Integer genotypeKey) {
		this.genotypeKey = genotypeKey;
	}

	public void setMarker(Marker marker) {
		this.marker = marker;
	}
	
	public void setReference(Reference reference){
		this.reference = reference;
	}
	
	public void setGenotype(Genotype genotype){
		this.genotype = genotype;
	}

	public void setImagePanes(List<ImagePane> imagePanes){
		this.imagePanes = imagePanes;
	}
	public void setPattern(String pattern)
	{
		this.pattern = pattern;
	}
}
