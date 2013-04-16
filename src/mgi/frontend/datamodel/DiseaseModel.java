package mgi.frontend.datamodel;

import javax.persistence.*;
import java.util.List;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * DiseaseModel
 * @author jsb
 * This object represents a mouse model for a human disease.
 */
@Entity
@Table(name="disease_model")
public class DiseaseModel {

	private int diseaseModelKey;
	private Genotype genotype;
	private String disease;
	private String diseaseID;
	private int isNotModel;
	private List<Reference> references;

    // ================= Convenience Methods ===================== //

	@Transient
	public String getBackgroundStrain() {
		return this.genotype.getBackgroundStrain();
	}
    
	@Transient
	public String getAllelePairs() {
		return this.genotype.getCombination1();
	}

	@Transient
	public String getGenotypeID() {
		return this.genotype.getPrimaryID();
	}

	@Transient
	public int getReferenceCount() {
		if (this.references == null) {
			return 0;
		}
		return this.references.size();
	}

    // ================= Getters and Setters ===================== //

	@ManyToOne (targetEntity=Genotype.class, fetch=FetchType.LAZY)
	@JoinColumn (name="genotype_key")
	public Genotype getGenotype() {
		return genotype;
	}

	@Id
	@Column(name="disease_model_key")
	public int getDiseaseModelKey() {
		return diseaseModelKey;
	}

	@Column(name="disease")
	public String getDisease() {
		return disease;
	}

	@Column(name="disease_id")
	public String getDiseaseID() {
		return diseaseID;
	}

	@Column(name="is_not_model")
	public int getIsNotModel() {
		return isNotModel;
	}

	@OneToMany
	@JoinTable (name="disease_model_to_reference",
		joinColumns=@JoinColumn(name="disease_model_key"),
		inverseJoinColumns=@JoinColumn(name="reference_key")
		)
	@OrderBy("year, jnumNumeric")
	public List<Reference> getReferences() {
		return references;
	}

	public void setGenotype(Genotype genotype) {
		this.genotype = genotype;
	}

	public void setDiseaseModelKey(int diseaseModelKey) {
		this.diseaseModelKey = diseaseModelKey;
	}

	public void setIsNotModel(int isNotModel) {
		this.isNotModel = isNotModel;
	}

	public void setDisease(String disease) {
		this.disease = disease;
	}

	public void setDiseaseID(String diseaseID) {
		this.diseaseID = diseaseID;
	}

	public void setReferences(List<Reference> references) {
		this.references = references;
	}

	@Override
	public String toString() {
		return "DiseaseModel [diseaseModelKey="
				+ diseaseModelKey
				+ ", diseaseID="
				+ diseaseID + "]";
	}
}
