package mgi.frontend.datamodel.phenotype;

import java.util.*;
import javax.persistence.*;

import mgi.frontend.datamodel.Genotype;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * This object defines the relationship between allele and genotypes that display on the phenotable grid
 */
@Entity
@Table(name="phenotable_to_genotype")
public class PhenoTableGenotype {

	private int phenoTableGenotypeKey;
	private Genotype genotype;
	private List<PhenoTableProvider> phenoTableProviders;
	private int genotypeSeq;
	private int splitSex;
	private String sexDisplay;

    // ================= Getters ======================================== //

	@Id
	@Column(name="phenotable_genotype_key")
	public int getPhenoTableGenotypeKey() {
		return phenoTableGenotypeKey;
	}

	/*
	 * This is the predefined order for display in the phenotype summary table
	 */
	@Column(name="genotype_seq")
	public int getGenotypeSeq() {
		return genotypeSeq;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="genotype_key")
	public Genotype getGenotype() {
		return genotype;
	}

	@OneToMany(targetEntity=PhenoTableProvider.class, fetch=FetchType.EAGER)
	@BatchSize(size=250)
	@JoinColumn(name="phenotable_genotype_key")
    @OrderBy("providerSeq")
	public List<PhenoTableProvider> getPhenoTableProviders() {
		return phenoTableProviders;
	}

	@Column(name="split_sex")
	public int getSplitSex() {
		return splitSex;
	}

	@Column(name="sex_display")
	public String getSexDisplay() {
		return sexDisplay;
	}

	@Transient
	public String getHtmlSex()
	{
		String htmlMale = "&#9794;";
		String htmlFemale = "&#9792;";
		htmlMale = "M";
		htmlFemale = "F";
		if(this.sexDisplay!=null)
		{
			if(this.sexDisplay.equalsIgnoreCase("M"))
			{
				return htmlMale;
			}
			if(this.sexDisplay.equalsIgnoreCase("F"))
			{
				return htmlFemale;
			}
		}
		return "";
	}
	
    @Transient 
    public int getColumnSpan() {
        int columnSpan = this.phenoTableProviders.size()>0 ? this.phenoTableProviders.size() : 1;
        if (this.splitSex==1) {
       	columnSpan = columnSpan * 2;
        }
        return columnSpan;
    }
    
    
	// ================= Setters ======================================== //

	public void setPhenoTableGenotypeKey(int phenoTableGenotypeKey) {
		this.phenoTableGenotypeKey = phenoTableGenotypeKey;
	}

	public void setGenotype(Genotype genotype) {
		this.genotype = genotype;
	}

	public void setGenotypeSeq(int genotypeSeq) {
		this.genotypeSeq = genotypeSeq;
	}

	public void setSplitSex(int splitSex) {
		this.splitSex = splitSex;
	}
	public void setSexDisplay(String sexDisplay) {
		this.sexDisplay = sexDisplay;
	}
	public void setPhenoTableProviders(List<PhenoTableProvider> phenoTableProviders) {
        this.phenoTableProviders = phenoTableProviders;
	}

}