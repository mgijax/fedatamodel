package org.jax.mgi.fe.datamodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.jax.mgi.fe.datamodel.util.DatamodelUtils;

/**
 * AssaySpecimen
 * This object represents the data for one assay specimen.
 */
@Entity
@Table(name="assay_specimen")
public class AssaySpecimen {

	// attributes in core table
	private Integer specimenKey;
	private Integer assayKey;
	private Integer specimenSeq;
	private Integer genotypeKey;
    private String specimenLabel;
	private String specimenNote;
	private String age;
	private String ageNote;
	private String sex;
	private String fixation;
	private String embeddingMethod;
	private String hybridization;

	// attributes joined in
	private Genotype genotype;
	private List<SpecimenResult> specimenResults;
	private List<ImagePane> cachedImagePanes=null;


    // ===================== Getters ===================== //

	@Id
	@Column(name="specimen_key")
	public Integer getSpecimenKey() {
		return specimenKey;
	}

	@Column(name="assay_key")
	public Integer getAssayKey() {
		return assayKey;
	}

	@Column(name="specimen_seq")
	public Integer getSpecimenSeq() {
		return specimenSeq;
	}

	@Column(name="specimen_label")
	public String getSpecimenLabel() {
		return specimenLabel;
	}

	@Column(name="specimen_note")
	public String getSpecimenNote() {
		return specimenNote;
	}

	@Column(name="age")
	public String getAge() {
		return age;
	}

	@Column(name="age_note")
	public String getAgeNote() {
		return ageNote;
	}

	@Column(name="sex")
	public String getSex() {
		return sex;
	}

	@Column(name="fixation")
	public String getFixation() {
		return fixation;
	}

	@Column(name="embedding_method")
	public String getEmbeddingMethod() {
		return embeddingMethod;
	}

	@Column(name="hybridization")
	public String getHybridization() {
		return hybridization;
	}

	@Column(name="genotype_key")
	public Integer getGenotypeKey() {
		return genotypeKey;
	}

	@OneToOne(targetEntity=Genotype.class, fetch=FetchType.LAZY)
	@JoinColumn (name="genotype_key", insertable=false, updatable=false)
    public Genotype getGenotype() {
		return genotype;
	}

	@OneToMany(targetEntity=SpecimenResult.class)
	@BatchSize(size=100)
	@JoinColumn(name="specimen_key")
    @OrderBy("specimenResultSeq")
    public List<SpecimenResult> getSpecimenResults() {
        return this.specimenResults;
    }


    // ===================== Setters ===================== //

	public void setSpecimenKey(int specimenKey) {
		this.specimenKey = specimenKey;
	}

	public void setSpecimenSeq(int specimenSeq) {
		this.specimenSeq = specimenSeq;
	}

	public void setAssayKey(int assayKey) {
		this.assayKey = assayKey;
	}

	public void setGenotypeKey(int genotypeKey) {
		this.genotypeKey = genotypeKey;
	}

	public void setSpecimenLabel(String specimenLabel) {
		this.specimenLabel = specimenLabel;
	}

	public void setSpecimenNote(String specimenNote) {
		this.specimenNote = specimenNote;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public void setAgeNote(String ageNote) {
		this.ageNote = ageNote;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setFixation(String fixation) {
		this.fixation = fixation;
	}

	public void setEmbeddingMethod(String embeddingMethod) {
		this.embeddingMethod = embeddingMethod;
	}

	public void setHybridization(String hybridization) {
		this.hybridization = hybridization;
	}

	public void setGenotype(Genotype genotype){
		this.genotype = genotype;
	}

	public void setSpecimenResults(List<SpecimenResult> specimenResults) {
		this.specimenResults = specimenResults;
	}


    // ===================== Transient ===================== //

	// Identify whether (true) or not (false) this specimen has any cell types identified for any of its results.
	@Transient
	public boolean getHasCellTypeData() {
		if (specimenResults != null) {
			for (SpecimenResult result : specimenResults) {
				if (result.cellTypes != null) {
					for (SpecimenResultCellType cellType : result.cellTypes) {
						if ((cellType != null) && (cellType.getCellType() != null) && (cellType.getCellType().trim().length() > 0)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	@Transient
	public List<ImagePane> getImagePanes()
	{
		if(cachedImagePanes==null)
		{
			Set<Integer> uniquePaneKeys = new HashSet<Integer>();
			List<ImagePane> imagePanes = new ArrayList<ImagePane>();
			this.getSpecimenResults().size();
			for(SpecimenResult sr : this.getSpecimenResults())
			{
				for(ImagePane ip : sr.getImagepanes())
				{
					if(!uniquePaneKeys.contains(ip.getImagePaneKey()))
					{
						uniquePaneKeys.add(ip.getImagePaneKey());
						imagePanes.add(ip);
					}
				}
			}
			if(imagePanes.size()>0)
			{
				// sort the image panes
				Collections.sort(imagePanes,imagePanes.get(0).getComparator());
			}
			this.cachedImagePanes = imagePanes;
		}
		return cachedImagePanes;
	}

	@Transient 
	public String getCssId()
	{
		return DatamodelUtils.makeCssSafe(specimenLabel)+"_id";
	}
	@Override
	public String toString() {
		return "AssaySpecimen ["
				+ "assayKey=" + assayKey + ", "
				+ "specimenKey=" + specimenKey + ", "
				+ "specimenLabel=" + specimenLabel + ", "
				+ "]";
	}
}