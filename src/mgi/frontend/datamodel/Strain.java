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
    private List<StrainGenotype> genotypes;
	private List<StrainID> ids;
	private List<StrainDisease> diseases;
	private List<StrainGridCell> gridCells;
    private String description;
    private List<StrainCollection> collections;
	
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

	@OneToMany (targetEntity=StrainCollection.class)
	@JoinColumn(name="strain_key")
	@BatchSize(size=100)
	@OrderBy("collection")
	public List<StrainCollection> getCollections() {
		return collections;
	}

    @Transient
	public List<String> getCollectionStrings() {
		List<String> out = new ArrayList<String>();
		if (this.getCollections() != null) {
			for (StrainCollection collection : this.getCollections()) {
				out.add(collection.getCollection());
			}
		}
		return out;
	}

    @Column(name="description")
    public String getDescription() {
		return description;
	}
    
	// return genotypes that have disease associations for this strain
	@Transient
	public List<StrainGenotype> getDiseaseGenotypes() {
		List<StrainGenotype> withDiseases = new ArrayList<StrainGenotype>();
		for (StrainGenotype gt : this.getGenotypes()) {
			if (gt.getHasDisease() != 0) {
				withDiseases.add(gt);
			}
		}
		return withDiseases;
	}

	@OneToMany (targetEntity=StrainDisease.class)
	@JoinColumn(name="strain_key")
	@BatchSize(size=100)
	@OrderBy("sequenceNum")
    public List<StrainDisease> getDiseases() {
		return diseases;
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

	@OneToMany (targetEntity=StrainGenotype.class)
	@JoinColumn(name="strain_key")
	@BatchSize(size=100)
	@OrderBy("sequenceNum")
    public List<StrainGenotype> getGenotypes() {
		return genotypes;
	}

	/** get the StrainGridCell for the given MP header term, or None if no match
	 */
	@Transient
	public StrainGridCell getGridCell(String header) {
		List<StrainGridCell> sgCells = this.getGridCells();
		if ((header != null) && (sgCells != null)) {
			for (StrainGridCell cell : sgCells) {
				if (header.equals(cell.getHeading())) {
					return cell;
				}
			}
		}
		return null;
	}
	
	/** returns a list of slimgrid cells for the strain
	 */
	@OneToMany (targetEntity=StrainGridCell.class)
	@JoinColumn(name="strain_key")
	@BatchSize(size=300)
	@OrderBy("sequenceNum")
	public List<StrainGridCell> getGridCells() {
		return gridCells;
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

	@Transient
    public boolean getIsFounder() {
    	return this.getCollectionStrings().contains("DOCCFounders");
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
	
	// return accession IDs that are not the primary ID for the strain
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

	public void setCollections(List<StrainCollection> collections) {
		this.collections = collections;
	}

	public void setDescription(String description) {
		this.description = description;
	}

    public void setDiseases(List<StrainDisease> diseases) {
		this.diseases = diseases;
	}

    public void setGenotypes(List<StrainGenotype> genotypes) {
		this.genotypes = genotypes;
	}

	public void setGridCells(List<StrainGridCell> gridCells) {
		this.gridCells = gridCells;
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
}
