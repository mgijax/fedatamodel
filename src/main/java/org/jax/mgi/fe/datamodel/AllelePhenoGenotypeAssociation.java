package org.jax.mgi.fe.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * AlleleGenotypeAssociation
 * @author jsb
 * This object represents the association of a genotype and an allele,
 * including an optional qualifier describing the association.
 */
@Entity
@Table(name="allele_to_genotype")
public class AllelePhenoGenotypeAssociation {
    
	private int uniqueKey;
	private Genotype genotype;
	private String qualifier;
	
	// ================= Getters and Setters ===================== //
	@OneToOne (targetEntity=Genotype.class)
	@LazyCollection(LazyCollectionOption.FALSE)
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

	public void setGenotype(Genotype genotype) {
		this.genotype = genotype;
	}

	public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}

	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}
}
