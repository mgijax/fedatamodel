package mgi.frontend.datamodel;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

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
}
