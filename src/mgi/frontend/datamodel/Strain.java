package mgi.frontend.datamodel;

import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;

/**
 * Strain
 * @author jsb
 * central object in Strain flower, storing data about a mouse strain
 */
@Entity
@Table (name="strain")
public class Strain {

    private int strainKey;
    private String name;
    private String primaryID;
    private List<StrainMutation> mutations;
    private List<StrainQTL> qtls;
	private List<StrainReferenceAssociation> referenceAssociations;

    // ================= Getters and Setters ===================== //

    @Column(name="name")
    public String getName() {
	return name;
    }

    @Column(name="primary_id")
    public String getPrimaryID() {
    	return primaryID;
    }

    @Id
    @Column(name="strain_key")
    public int getStrainKey() {
    	return strainKey;
    }

	@OneToMany (fetch=FetchType.LAZY)
	@JoinColumn(name="strain_key")
	@BatchSize(size=300)
	public List<StrainReferenceAssociation> getReferenceAssociations() {
		return referenceAssociations;
	}

	@OneToMany (targetEntity=StrainQTL.class)
	@JoinColumn(name="strain_key")
	@BatchSize(size=100)
	@OrderBy("sequenceNum")
    public List<StrainQTL> getQtls() {
		return qtls;
	}

	public void setQtls(List<StrainQTL> qtls) {
		this.qtls = qtls;
	}

	@OneToMany (targetEntity=StrainMutation.class)
	@JoinColumn(name="strain_key")
	@BatchSize(size=100)
	@OrderBy("sequenceNum")
    public List<StrainMutation> getMutations() {
		return mutations;
	}

	public void setReferenceAssociations(List<StrainReferenceAssociation> referenceAssociations) {
		this.referenceAssociations = referenceAssociations;
	}

	public void setMutations(List<StrainMutation> mutations) {
		this.mutations = mutations;
	}

	public void setName(String name) {
		this.name = name;
    }

    public void setPrimaryID(String primaryID) {
    	this.primaryID = primaryID;
    }

    public void setStrainKey(int strainKey) {
    	this.strainKey = strainKey;
    }

	@Override
	public String toString() {
		return "Strain [strainKey=" + strainKey + ", name=" + name + ", primaryID=" + primaryID + "]";
	}

    // ================= Transient Methods ===================== //

	/** retrieve the reference with the given qualifier
	 */
	@Transient
	private Reference filterReferences (String qualifier) {
		StrainReferenceAssociation association;
		Iterator<StrainReferenceAssociation> it = getReferenceAssociations().iterator();

		while (it.hasNext()) {
			association = it.next();
			if (qualifier.equals(association.getQualifier())) {
				return association.getReference();
			}
		}
		return null;
	}

	/** get the earliest reference for this strain
	 */
	@Transient
	public Reference getEarliestReference () {
		return filterReferences("earliest");
	}
	
	/** get the count of references
	 */
	@Transient
	public int getReferenceCount() {
		if (getReferenceAssociations() != null) {
			return getReferenceAssociations().size();
		}
		return 0;
	}
}
