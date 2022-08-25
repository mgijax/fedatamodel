package mgi.frontend.datamodel;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.BatchSize;

import mgi.frontend.datamodel.phenotype.MPSystem;

/**
 * Genotype
 * @author jsb
 * This object represents a genotype -- a combination of a background strain
 * and zero or more pairs of alleles that have been introduced onto that
 * strain.  Genotypes may be ordered using data from genotype_sequence_num.
 */
@Entity
@Table(name="genotype")
@SecondaryTables (
    {
      @SecondaryTable (name="genotype_sequence_num", pkJoinColumns= {
        @PrimaryKeyJoinColumn(name="genotype_key", referencedColumnName="genotype_key") } )
    }
)
public class Genotype implements Comparable<Genotype>
{
	private Integer byAlleles;				// for sorting genotypes
	private int genotypeKey;
	private String backgroundStrain;
	private String strainID;
	private String primaryID;
	private List<Image> images;
	private List<GenotypeImageAssociation> imageAssociations;
	private int isConditional;
	private String note;
	private String combination1;
	private String combination2;
	private String combination3;
	private String genotypeType;
	private List<Annotation> annotations;
	private List<MPSystem> systems;
	private List<AlleleGenotypeAssociation> alleleAssociations;
	private List<GenotypeDisease> diseases;
	private Image primaryImage=null;
	private Image primaryImageThumbnail=null;
	private String cellLines;
	private String existsAs;
	private List<Allele> alleles;


	// ================= Getters and Setters ===================== //

    @OneToMany (fetch=FetchType.LAZY)
    @JoinColumn(name="genotype_key")
    public List<Annotation> getAnnotations() {
    	return annotations;
    }

    @Column(name="background_strain")
	public String getBackgroundStrain() {
		return backgroundStrain;
	}

    @Column(name="strain_id")
	public String getStrainID() {
		return strainID;
	}

	@Column(table="genotype_sequence_num", name="by_alleles")
	public int getByAlleles() {
		return byAlleles;
	}

	@Column(name="combination_1")
	public String getCombination1() {
		return combination1;
	}

	@Column(name="combination_2")
	public String getCombination2() {
		return combination2;
	}

	@Column(name="combination_3")
	public String getCombination3() {
		return combination3;
	}

	@Id
	@Column(name="genotype_key")
	public int getGenotypeKey() {
		return genotypeKey;
	}

    /** get an ordered list of images featuring this genotype
     */
	@OneToMany (fetch=FetchType.LAZY)
	@JoinTable (name="genotype_to_image",
			joinColumns=@JoinColumn(name="genotype_key"),
			inverseJoinColumns=@JoinColumn(name="image_key")
			)
	@OrderBy("byDefault")
	public List<Image> getImages() {
		return images;
	}

	@Column(name="is_conditional")
	public int getIsConditional() {
		return isConditional;
	}

	@Column(name="note")
	public String getNote() {
		return note;
	}

	@Column(name="genotype_type")
	public String getGenotypeType()
	{
		return genotypeType;
	}

	@Column(name="primary_id")
	public String getPrimaryID() {
		return primaryID;
	}

	@Column(name="cell_lines")
	public String getCellLines() {
		return cellLines;
	}

	@Column(name="exists_as")
	public String getExistsAs() {
		return existsAs;
	}

	public void setAnnotations(List<Annotation> annotations) {
		this.annotations = annotations;
	}

	public void setBackgroundStrain(String backgroundStrain) {
		this.backgroundStrain = backgroundStrain;
	}

	public void setStrainID(String strainID) {
		this.strainID = strainID;
	}

	public void setByAlleles(int byAlleles) {
		this.byAlleles = byAlleles;
	}

	public void setCombination1(String combination1) {
		this.combination1 = combination1;
	}

	public void setCombination2(String combination2) {
		this.combination2 = combination2;
	}

	public void setCombination3(String combination3) {
		this.combination3 = combination3;
	}

	public void setGenotypeKey(int genotypeKey) {
		this.genotypeKey = genotypeKey;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public void setIsConditional(int isConditional) {
		this.isConditional = isConditional;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public void setGenotypeType(String genotypeType)
	{
		this.genotypeType=genotypeType;
	}

	public void setPrimaryID(String primaryID) {
		this.primaryID = primaryID;
	}

	public void setCellLines(String cellLines) {
		this.cellLines = cellLines;
	}

	public void setExistsAs(String existsAs) {
		this.existsAs = existsAs;
	}

	@OneToMany (targetEntity=MPSystem.class)
	@JoinColumn(name="genotype_key")
	@OrderBy("systemSeq")
	public List<MPSystem> getMPSystems() {
		return systems;
	}

	public void setMPSystems(List<MPSystem> systems)
	{
		this.systems=systems;
	}


	@OneToMany
	@JoinTable (name="allele_to_genotype",
		joinColumns=@JoinColumn(name="genotype_key"),
		inverseJoinColumns=@JoinColumn(name="allele_key")
		)
	@BatchSize(size=15)
	@OrderBy("symbol")
	public List<Allele> getAlleles() {
		return alleles;
	}

	public void setAlleles(List<Allele> alleles) {
		this.alleles = alleles;
	}

	// get the list of Allele objects which have IMSR strain data
	@Transient
	public List<Allele> getImsrAlleles() {
		List<Allele> sublist = new ArrayList<Allele>();

		for (Allele a : this.getAlleles()) {
			if ((a.getImsrStrainCount() > 0) || (a.getImsrCountForMarker() > 0)) {
				if (a.getIsWildType() == 0) {
					sublist.add(a);
				}
			}
		}
		return sublist;
	}

	// get the list of non-wild-type Allele objects 
	@Transient
	public List<Allele> getMutantAlleles() {
		List<Allele> sublist = new ArrayList<Allele>();

		for (Allele a : this.getAlleles()) {
			if (a.getIsWildType() == 0) {
				sublist.add(a);
			}
		}
		return sublist;
	}

	// get the list of markers with mutations (distinct list of markers,
	// even if multiple alleles per marker) -- ordered by symbol
	@Transient
	public List<Marker> getMutatedMarkers() {
		List<Marker> markers = new ArrayList<Marker>();

		for (Allele a : getMutantAlleles()) {
			if (!markers.contains(a.getMarker())) {
				markers.add(a.getMarker());
			}
		}

		if (markers.size() > 0) {
			Collections.sort(markers,
				markers.get(0).getComparator());
		}
		return markers; 
	}

	@OneToMany (targetEntity=AlleleGenotypeAssociation.class)
	@JoinColumn(name="genotype_key")
	public List<AlleleGenotypeAssociation> getAlleleAssociations() {
		return alleleAssociations;
	}

	public void setImageAssociations(
			List<GenotypeImageAssociation> imageAssociations) {
		this.imageAssociations = imageAssociations;
	}

	@OneToMany (targetEntity=GenotypeImageAssociation.class)
	@JoinColumn(name="genotype_key")
	public List<GenotypeImageAssociation> getImageAssociations() {
		return imageAssociations;
	}

	public void setAlleleAssociations(
			List<AlleleGenotypeAssociation> alleleAssociations) {
		this.alleleAssociations = alleleAssociations;
	}

	@OneToMany (targetEntity=GenotypeDisease.class)
	@JoinColumn(name="genotype_key")
	public List<GenotypeDisease> getDiseases()
	{
		return diseases;
	}

	public void setDiseases(List<GenotypeDisease> diseases)
	{
		this.diseases = diseases;
	}

	@Transient
	public boolean hasDiseases() {
		for(GenotypeDisease disease : this.getDiseases())
		{
			// return true once we find a single diseae model that is NOT a "not". (whatever that means)
			if(!disease.getIsNot()) return true;
		}
		return false;
	}

	@Transient
	public boolean hasPrimaryImage()
	{
		this.getImageAssociations().size();
		for(GenotypeImageAssociation ia : this.getImageAssociations())
		{
			if(ia!=null && ia.getQualifier()!=null && ia.getImage()!=null
					&& ia.getQualifier().equalsIgnoreCase("primary")
					&& ia.getImage().getIsThumbnail()==0)
			{
				primaryImage=ia.getImage();
				return true;
			}
		}
		return false;
	}
	@Transient
	public boolean getHasPrimaryImage()
	{ return hasPrimaryImage(); }

	@Transient
	public Image getPrimaryImage()
	{
		// return cached object if we have it
		if(primaryImage!=null) return primaryImage;

		// find the primary image thumbnail for this genotype.
		for(GenotypeImageAssociation ia : this.getImageAssociations())
		{
			if(ia!=null && ia.getQualifier()!=null && ia.getImage()!=null
					&& ia.getQualifier().equalsIgnoreCase("primary")
					&& ia.getImage().getIsThumbnail()==0)
			{
				primaryImage=ia.getImage();
				return ia.getImage();
			}
		}
		return null;
	}

	@Transient
	public Image getThumbnail()
	{
		// return cached object if we have it
		if(this.primaryImageThumbnail!=null) return primaryImageThumbnail;

		// find the primary image thumbnail for this genotype.
		for(GenotypeImageAssociation ia : this.getImageAssociations())
		{
			if(ia!=null && ia.getQualifier()!=null && ia.getImage()!=null
					&& ia.getQualifier().equalsIgnoreCase("primary")
					&& ia.getImage().getIsThumbnail()==1)
			{
				primaryImageThumbnail=ia.getImage();
				return ia.getImage();
			}
		}
		return null;
	}

//	/*
//	 * Aggregates all the disease footnotes for this genotype
//	 */
//	@Transient
//	public Set<GenotypeDiseaseFootnote> getFootnotes()
//	{
//		Set<GenotypeDiseaseFootnote> footnotes = new LinkedHashSet<GenotypeDiseaseFootnote>();
//		for(GenotypeDisease gd : this.getDiseases())
//		{
//			footnotes.addAll(gd.getFootnotes());
//		}
//		return footnotes;
//	}

	@Override
	public String toString() {
		return "Genotype ["
				+ (backgroundStrain != null ? "backgroundStrain="
						+ backgroundStrain + ", " : "")
				+ (combination1 != null ? "combination1=" + combination1 + ", "
						: "")
				+ (combination2 != null ? "combination2=" + combination2 + ", "
						: "") + "genotypeKey=" + genotypeKey
				+ (combination3 != null ? "combination3=" + combination3 + ", "
						: "") + "genotypeKey=" + genotypeKey
				+ ", isConditional=" + isConditional + ", "
				+ (note != null ? "note=" + note + ", " : "")
				+ (primaryID != null ? "primaryID=" + primaryID : "") + "]";
	}

	/** standard comparison method for Comparable interface
	 */
	@Override
	public int compareTo(Genotype b) 
	{
		if (this.byAlleles < b.byAlleles) { return -1; }
		else if (this.byAlleles > b.byAlleles) { return 1; }
		return 0;
	}
}
