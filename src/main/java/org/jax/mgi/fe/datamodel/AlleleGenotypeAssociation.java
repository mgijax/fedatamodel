package org.jax.mgi.fe.datamodel;

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

/**
 * AlleleGenotypeAssociation
 * @author jsb
 * This object represents the association of a genotype and an allele,
 * including an optional qualifier describing the association.
 */
@Entity
@Table(name="allele_to_genotype")
public class AlleleGenotypeAssociation {
    
	private int uniqueKey;
	private Allele allele;
	private Genotype genotype;
	private String qualifier;
	private List<GenotypeDisease> diseases;
	
	// ================= Getters and Setters ===================== //

	@ManyToOne (targetEntity=Allele.class, fetch=FetchType.LAZY)
	@JoinColumn (name="allele_key")
	public Allele getAllele() {
		return allele;
	}

	@ManyToOne (targetEntity=Genotype.class, fetch=FetchType.LAZY)
	@JoinColumn (name="genotype_key")
	public Genotype getGenotype() {
		return genotype;
	}

	@Column(name="qualifier")
	public String getQualifier() {
		return qualifier;
	}

	@Id
	@Column(name="allele_genotype_key")
	public int getUniqueKey() {
		return uniqueKey;
	}

	public void setAllele(Allele allele) {
		this.allele = allele;
	}

	public void setGenotype(Genotype genotype) {
		this.genotype = genotype;
	}

	public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}

	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}
	
	@OneToMany(targetEntity=GenotypeDisease.class)
	@JoinColumn(name="allele_genotype_key")
	@OrderBy("sequenceNum")
	public List<GenotypeDisease> getDiseases()
	{
		return diseases;
	}
	
	public void setDiseases(List<GenotypeDisease> diseases)
	{
		this.diseases=diseases;
	}
}
