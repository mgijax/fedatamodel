package mgi.frontend.datamodel;

import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.SortType;

/**
 * Base object for diseases.
 * @author jsb
 *
 * Diseases are related to human and mouse markers through a series of join
 * tables.  Markers are grouped into disease rows (in table disease_row),
 * which correspond to rows on the disease detail page.  Those disease rows
 * are then grouped into disease groups (in table disease_group), which tells
 * what section of the page a given group's rows should be displayed in.
 */

@Entity
@Table(name="disease")
public class Disease {

    private int diseaseKey;
    private String disease;
    private String primaryID;
    private String logicalDB;
    private List<DiseaseSynonym> diseaseSynonyms;
    private List<DiseaseGroup> diseaseGroups;

    // =========== Convenience Methods =============== //

    // ----- methods to pull out groups for the disease detail page -----

    @Transient
    private DiseaseGroup getDiseaseGroup (String groupType) {
	DiseaseGroup dg;
	Iterator<DiseaseGroup> it = this.diseaseGroups.iterator();

	while (it.hasNext()) {
	    dg = it.next();
	    if (dg.getGroupType().equals(groupType)) {
		return dg;
	    }
	}
	return null;
    }

    @Transient
    public DiseaseGroup getMouseHumanGroup() {
	return this.getDiseaseGroup ("mouse and human");
    }
 
    @Transient
    public DiseaseGroup getMouseOnlyGroup() {
	return this.getDiseaseGroup ("mouse only");
    }
 
    @Transient
    public DiseaseGroup getHumanOnlyGroup() {
	return this.getDiseaseGroup ("human only");
    }
 
    @Transient
    public DiseaseGroup getOtherGroup() {
	return this.getDiseaseGroup ("non-genes");
    }
 
    @Transient
    public DiseaseGroup getNotObservedGroup() {
	return this.getDiseaseGroup ("not observed");
    }
 
    @Transient
    public DiseaseGroup getAdditionalGroup() {
	return this.getDiseaseGroup ("additional");
    }
 
    // ----- methods to pull out mouse models for the 'all models' page -----

    /* given a list of DiseaseModels, filter out duplicates and return a
     * list of unique DiseaseModels
     */
    @Transient
    private List<DiseaseModel> uniqueModels(List<DiseaseModel> dups) {
	ArrayList<DiseaseModel> unique = new ArrayList<DiseaseModel>();
	if (dups == null) { return unique; }

	HashMap<String,String> keysSeen = new HashMap<String,String>();

	Iterator<DiseaseModel> dmIt = dups.iterator();
	DiseaseModel dm;
	String key;

	while (dmIt.hasNext()) {
	    dm = dmIt.next();
	    key = "" + dm.getDiseaseModelKey();

	    if (!keysSeen.containsKey(key)) {
		keysSeen.put(key, key);
		unique.add(dm);
	    }
	}
	return unique;
    }

    /* given DiseaseGroup 'dg', return a list of unique DiseaseModels in it,
     * both positive models and NOT models
     */
    @Transient
    private List<DiseaseModel> extractAllModelsFromGroup (DiseaseGroup dg) {
	return this.extractModelsFromGroup (dg, -1);
    }

    /* given DiseaseGroup 'dg', return a list of unique positive DiseaseModels
     * in it
     */
    @Transient
    private List<DiseaseModel> extractModelsFromGroup (DiseaseGroup dg) {
	return this.extractModelsFromGroup (dg, 0);
    }

    /* given DiseaseGroup 'dg', return a list of unique NOT DiseaseModels in
     * it
     */
    @Transient
    private List<DiseaseModel> extractNotModelsFromGroup (DiseaseGroup dg) {
	return this.extractModelsFromGroup (dg, 1);
    }

    @Transient
    private List<DiseaseModel> extractModelsFromGroup (DiseaseGroup dg,
		    int notModels) {
	ArrayList<DiseaseModel> models = new ArrayList<DiseaseModel>();
	if (dg == null) { return models; }

	Iterator<DiseaseRow> drIt = dg.getDiseaseRows().iterator();
	DiseaseRow row;

	while (drIt.hasNext()) {
	    row = drIt.next();
	    if (notModels == 0) {
		    models.addAll (row.getMouseModels());
	    } else if (notModels == -1) {
		    models.addAll (row.getMouseModels());
		    models.addAll (row.getNotModels());
	    } else {
		    models.addAll (row.getNotModels());
	    }
	}
	return this.uniqueModels(models);
    }

    /* return all models for this disease, both positive and negative
     */
    @Transient
    private List<DiseaseModel> getAllModels() {
	Iterator<DiseaseGroup> it = this.diseaseGroups.iterator();
	ArrayList<DiseaseModel> models = new ArrayList<DiseaseModel>();

	while (it.hasNext()) {
	    models.addAll (this.extractAllModelsFromGroup(it.next()));
	}
	return this.uniqueModels(models);
    }

    /* return models from 'fullList' which are either positive models (0) or
     * NOT models (1)
     */
    @Transient
    private List<DiseaseModel> filterModels (List<DiseaseModel> fullList,
	int onlyNotModels) {

	ArrayList<DiseaseModel> sublist = new ArrayList<DiseaseModel>();
	if (fullList == null) { return sublist; }

	// go ahead and do the filtering
	
	Iterator<DiseaseModel> dmIt = fullList.iterator();
	DiseaseModel dm;

	dmIt = fullList.iterator();
	while (dmIt.hasNext()) {
	    dm = dmIt.next();

	    if (dm.getIsNotModel() == onlyNotModels) {
		sublist.add(dm);
	    }
	}
	return sublist;
    }

    /* get a count of all positive models (not "NOT models")
     */
    @Transient
    public int getCountOfModels() {
	return this.filterModels(this.getAllModels(), 0).size();
    }

    /* get a count of all "NOT models"
     */
    @Transient
    public int getCountOfNotModels() {
	return this.filterModels(this.getAllModels(), 1).size();
    }

    /* get a list of DiseaseModels where the disease occurs in mouse and human
     * genes
     */
    @Transient
    public List<DiseaseModel> getMouseAndHumanModels() {
	return this.uniqueModels(
		this.extractModelsFromGroup(
		this.getMouseHumanGroup() ) );
    }

    /* get a list of DiseaseModels where the disease only occurs in mouse,
     * where those models have not already occurred in the mouse/human section
     */
    @Transient
    public List<DiseaseModel> getMouseOnlyModels() {
	return this.uniqueModels(
		this.extractModelsFromGroup(this.getMouseOnlyGroup()) );
    }

    /* get a list of DiseaseModels where the disease occurs only in human,
     * where those models have not already occurred in the mouse/human or the
     * mouse-only sections
     */
    @Transient
    public List<DiseaseModel> getHumanOnlyModels() {
	return this.uniqueModels(
		this.extractModelsFromGroup(this.getHumanOnlyGroup()) );
    }

    /* get a list of DiseaseModels for transgenes and other genome feature
     * types (not genes).  These models must not have already occurred in the
     * mouse-only, human-only, or mouse/human sections
     */
    @Transient
    public List<DiseaseModel> getOtherModels() {
	return this.uniqueModels(
		this.extractModelsFromGroup(this.getOtherGroup()) );
    }

    /* get a list of NOT DiseaseModels -- cases where the researcher expected
     * to see the disease phenotype, but did not.
     */
    @Transient
    public List<DiseaseModel> getNotModels() {
	return this.uniqueModels(
		this.filterModels(this.getAllModels(), 1) );
    }

    /* get a list of leftover DiseaseModels -- those which do not appear in
     * any other group
     */
    @Transient
    public List<DiseaseModel> getAdditionalModels() {
	return this.uniqueModels(
		this.extractModelsFromGroup(this.getAdditionalGroup()) );
    }

    // ================= Getters ===================== //

    @Column(name="disease")
    public String getDisease() {
        return disease;
    }

    @Column(name="primary_id")
    public String getPrimaryID() {
        return primaryID;
    }

    @Column(name="logical_db")
    public String getLogicalDB() {
        return logicalDB;
    }

    /** get an ordered list of synonyms for the disease
     */
    @OneToMany (targetEntity=DiseaseGroup.class)
    @JoinColumn(name="disease_key")
    @BatchSize(size=50)
    @OrderBy("sequenceNum")
    public List<DiseaseGroup> getDiseaseGroups() {
	return diseaseGroups;
    }

    /** get an ordered list of synonyms for the disease
     */
    @OneToMany (targetEntity=DiseaseSynonym.class)
    @JoinColumn(name="disease_key")
    @BatchSize(size=50)
    @OrderBy("sequenceNum")
    public List<DiseaseSynonym> getDiseaseSynonyms() {
	return diseaseSynonyms;
    }

    @Id
    @Column(name="disease_key")
    public int getDiseaseKey() {
        return diseaseKey;
    }

    // ================= Setters ===================== //

/*    public void setMouseModelCount(Integer mouseModelCount) {
	this.mouseModelCount = mouseModelCount;
    }

    public void setReferenceCount(Integer referenceCount) {
	this.referenceCount = referenceCount;
    }
*/
    public void setDisease(String disease) {
        this.disease = disease;
    }

    public void setPrimaryID(String primaryID) {
        this.primaryID = primaryID;
    }

    public void setLogicalDB(String logicalDB) {
        this.logicalDB = logicalDB;
    }

    public void setDiseaseKey(int diseaseKey) {
        this.diseaseKey = diseaseKey;
    }

    public void setDiseaseGroups(List<DiseaseGroup> diseaseGroups) {
	this.diseaseGroups = diseaseGroups;
    }

    public void setDiseaseSynonyms(List<DiseaseSynonym> diseaseSynonyms) {
	this.diseaseSynonyms = diseaseSynonyms;
    }

    // ================= Convenience ================= //

    @Override
    public String toString() {
	StringBuffer sb = new StringBuffer();
	sb.append ("Disease Term (");
	sb.append (this.disease);
	sb.append ("), key ");
	sb.append (this.diseaseKey);
	sb.append (", ");
	sb.append (this.logicalDB);
	sb.append (" ID ");
	sb.append (this.primaryID);
	return sb.toString();
    }
}
