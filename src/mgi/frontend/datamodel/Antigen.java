package mgi.frontend.datamodel;

import java.util.Set;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Antigen
 * @author jsb
 * petal object in Antigen flower, storing data about the antigen that gave
 * rise to the antibody
 */
@Entity
@Table (name="antigen")
public class Antigen {

    private int antigenKey;
    private String name;
    private String species;
    private String strain;
    private String sex;
    private String age;
    private String tissue;
    private String tissueDescription;
    private String cellLine;
    private String regionCovered;
    private String note;

    // ================= Getters and Setters ===================== //

    @Id
    @Column(name="antigen_key")
    public int getAntigenKey() {
	return antigenKey;
    }
    public void setAntigenKey(int antigenKey) {
	this.antigenKey = antigenKey;
    }

    @Column(name="Name")
    public String getName() {
	return name;
    }
    public void setName(String name) {
	this.name = name;
    }

    @Column(name="species")
    public String getSpecies() {
	return species;
    }
    public void setSpecies(String species) {
	this.species = species;
    }

    @Column(name="strain")
    public String getStrain() {
	return strain;
    }
    public void setStrain(String strain) {
	this.strain = strain;
    }

    @Column(name="sex")
    public String getSex() {
	return sex;
    }
    public void setSex(String sex) {
	this.sex = sex;
    }

    @Column(name="age")
    public String getAge() {
	return age;
    }
    public void setAge(String age) {
	this.age = age;
    }

    @Column(name="tissue")
    public String getTissue() {
	return tissue;
    }
    public void setTissue(String tissue) {
	this.tissue = tissue;
    }

    @Column(name="tissue_description")
    public String getTissueDescription() {
	return tissueDescription;
    }
    public void setTissueDescription(String tissueDescription) {
	this.tissueDescription = tissueDescription;
    }

    @Column(name="cell_line")
    public String getCellLine() {
	return cellLine;
    }
    public void setCellLine(String cellLine) {
	this.cellLine = cellLine;
    }

    @Column(name="region_covered")
    public String getRegionCovered() {
	return regionCovered;
    }
    public void setRegionCovered(String regionCovered) {
	this.regionCovered = regionCovered;
    }

    @Column(name="note")
    public String getNote() {
	return note;
    }
    public void setNote(String note) {
	this.note = note;
    }

    public String toString() {
	return "<Antigen " + this.name
	    + " [" + this.species + ", key " + this.antigenKey + "]>";
    }
}
