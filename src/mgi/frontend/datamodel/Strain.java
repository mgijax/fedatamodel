package mgi.frontend.datamodel;

import java.util.ArrayList;
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
    private List<StrainImsrData> imsrData;
    private List<StrainMpdData> mpdData;
    private int isStandard;
    private int isSequenced;
    private List<StrainMutation> mutations;
    private List<StrainQTL> qtls;
	private List<StrainReferenceAssociation> referenceAssociations;
    private List<StrainSynonym> strainSynonyms;
    private List<StrainAttribute> strainAttributes;
	private List<StrainID> ids;
	private List<StrainDisease> diseases;
	
    // ================= Getters and Setters ===================== //

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

    // Return the first instance of StrainMpdData, if there is one.
	@Transient
	public StrainMpdData getFirstMpdData() {
		if (getMpdData().size() > 0) {
			return getMpdData().get(0);
		}
		return null;
	}

	@OneToMany (targetEntity=StrainID.class)
	@JoinColumn(name="strain_key")
	@BatchSize(size=100)
	@OrderBy("sequenceNum")
	public List<StrainID> getIds() {
		return ids;
	}

	@OneToMany (targetEntity=StrainImsrData.class)
	@JoinColumn(name="strain_key")
	@BatchSize(size=100)
	@OrderBy("imsrID")
    public List<StrainImsrData> getImsrData() {
		return imsrData;
	}

	@Column(name="is_sequenced")
    public int getIsSequenced() {
        return isSequenced;
    }

	@Column(name="is_standard")
    public int getIsStandard() {
        return isStandard;
    }

	@OneToMany (targetEntity=StrainMpdData.class)
	@JoinColumn(name="strain_key")
	@BatchSize(size=100)
	@OrderBy("mpdID")
    public List<StrainMpdData> getMpdData() {
		return mpdData;
	}

	@OneToMany (targetEntity=StrainMutation.class)
	@JoinColumn(name="strain_key")
	@BatchSize(size=100)
	@OrderBy("sequenceNum")
    public List<StrainMutation> getMutations() {
		return mutations;
	}
	
	@Column(name="name")
    public String getName() {
	return name;
    }

	@Column(name="primary_id")
    public String getPrimaryID() {
    	return primaryID;
    }

	@OneToMany (targetEntity=StrainQTL.class)
	@JoinColumn(name="strain_key")
	@BatchSize(size=100)
	@OrderBy("sequenceNum")
    public List<StrainQTL> getQtls() {
		return qtls;
	}

    @OneToMany (fetch=FetchType.LAZY)
	@JoinColumn(name="strain_key")
	@BatchSize(size=300)
	public List<StrainReferenceAssociation> getReferenceAssociations() {
		return referenceAssociations;
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
	
	@OneToMany (targetEntity=StrainAttribute.class)
	@JoinColumn(name="strain_key")
	@BatchSize(size=100)
	@OrderBy("sequenceNum")
    public List<StrainAttribute> getStrainAttributes() {
		return strainAttributes;
	}

	@OneToMany (targetEntity=StrainDisease.class)
	@JoinColumn(name="strain_key")
	@BatchSize(size=100)
	@OrderBy("sequenceNum")
    public List<StrainDisease> getDiseases() {
		return diseases;
	}

	@Id
    @Column(name="strain_key")
    public int getStrainKey() {
    	return strainKey;
    }

	@OneToMany (targetEntity=StrainSynonym.class)
	@JoinColumn(name="strain_key")
	@BatchSize(size=100)
	@OrderBy("sequenceNum")
    public List<StrainSynonym> getStrainSynonyms() {
		return strainSynonyms;
	}

	public void setIds(List<StrainID> ids) {
		this.ids = ids;
	}

	public void setImsrData(List<StrainImsrData> imsrData) {
		this.imsrData = imsrData;
	}

    public void setIsSequenced(int isSequenced) {
        this.isSequenced = isSequenced;
    }

    public void setIsStandard(int isStandard) {
        this.isStandard = isStandard;
    }

	public void setMpdData(List<StrainMpdData> mpdData) {
		this.mpdData = mpdData;
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
    
	public void setQtls(List<StrainQTL> qtls) {
		this.qtls = qtls;
	}

	public void setReferenceAssociations(List<StrainReferenceAssociation> referenceAssociations) {
		this.referenceAssociations = referenceAssociations;
	}

	public void setStrainAttributes(List<StrainAttribute> strainAttributes) {
		this.strainAttributes = strainAttributes;
	}

	public void setDiseases(List<StrainDisease> diseases) {
		this.diseases = diseases;
	}

	public void setStrainKey(int strainKey) {
    	this.strainKey = strainKey;
    }

	public void setStrainSynonyms(List<StrainSynonym> strainSynonyms) {
		this.strainSynonyms = strainSynonyms;
	}
	
	@Override
	public String toString() {
		return "Strain [strainKey=" + strainKey + ", name=" + name + ", primaryID=" + primaryID + "]";
	}
	
	@Transient
	public List<StrainID> getOtherIDs() {
		List<StrainID> otherIDs = new ArrayList<StrainID>();
		for (StrainID id : this.getIds()) {
			if (!id.getAccID().equals(this.primaryID)) {
				otherIDs.add(id);
			}
		}
		return otherIDs;
	}
}
