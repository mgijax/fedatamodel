package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
