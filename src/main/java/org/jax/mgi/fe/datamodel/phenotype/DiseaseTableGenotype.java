package org.jax.mgi.fe.datamodel.phenotype;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.jax.mgi.fe.datamodel.Genotype;

/**
 *
 * This object defines the relationship between allele and genotypes that display on the phenotable grid
 */
@Entity
@Table(name="diseasetable_to_genotype")
public class DiseaseTableGenotype {

	private int diseaseTableGenotypeKey;
	private Genotype genotype;
	private int genotypeSeq;

    // ================= Getters ======================================== //

	@Id
	@Column(name="diseasetable_genotype_key")
	public int getDiseaseTableGenotypeKey() {
		return diseaseTableGenotypeKey;
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
    
	// ================= Setters ======================================== //

	public void setDiseaseTableGenotypeKey(int diseaseTableGenotypeKey) {
		this.diseaseTableGenotypeKey = diseaseTableGenotypeKey;
	}

	public void setGenotype(Genotype genotype) {
		this.genotype = genotype;
	}

	public void setGenotypeSeq(int genotypeSeq) {
		this.genotypeSeq = genotypeSeq;
	}
}