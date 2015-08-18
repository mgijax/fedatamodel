package mgi.frontend.datamodel;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;

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
		return genotype.getBackgroundStrain();
	}
    
	@Transient
	public String getAllelePairs() {
		return genotype.getCombination1();
	}

	@Transient
	public String getGenotypeID() {
		return genotype.getPrimaryID();
	}

	@Transient
	public int getReferenceCount() {
		if (references == null) {
			return 0;
		}
		return references.size();
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
	@BatchSize(size=100)
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
