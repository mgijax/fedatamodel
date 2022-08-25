package mgi.frontend.datamodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Comparator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;

import mgi.frontend.datamodel.sort.SmartAlphaComparator;

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
	private int diseaseReferenceCount;
	private int hpoTermCount;
	private int genesTabCount;
	private int modelsTabCount;
	private List<DiseaseSynonym> diseaseSynonyms;
	private List<DiseaseGroup> diseaseGroups;
	private VocabTerm vocabTerm; 

	// =========== Convenience Methods =============== //

	// ----- method to order IDs on disease browser -----
	@Transient
	public List<VocabTermID> getOrderedSecondaryIDs() {

		// Disease browser requires secondary IDs ordered first by 
		// OMIM ID, and then other IDs sorted by alpha.  This depends on
		// alpha sort ordering in vocabTerm secondary ID retrieval

		List<VocabTermID> omimIDs  = new ArrayList<VocabTermID>();
		List<VocabTermID> otherIDs = new ArrayList<VocabTermID>();
		VocabTermID vocabTermID;

		Iterator<VocabTermID> iter = vocabTerm.getSecondaryIds().iterator();

		while (iter.hasNext()) {
			vocabTermID = iter.next();
			//remove preferred and private IDs
			if(!vocabTermID.isPreferred() && !vocabTermID.isPrivate()){
				if(vocabTermID.getLogicalDB().equals("OMIM")){
					omimIDs.add(vocabTermID);
				}
				else {
					otherIDs.add(vocabTermID);
				}
			}
		}
		// omim IDs first, than all other IDs
		omimIDs.addAll(otherIDs);
		return omimIDs;
	}


	// ----- methods to pull out groups for the disease browser page -----

	@Transient
	private DiseaseGroup getDiseaseGroup (String groupType) {
		DiseaseGroup dg;
		Iterator<DiseaseGroup> it = diseaseGroups.iterator();

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
		return getDiseaseGroup ("mouse and human");
	}

	@Transient
	public DiseaseGroup getMouseOnlyGroup() {
		return getDiseaseGroup ("mouse only");
	}

	@Transient
	public DiseaseGroup getHumanOnlyGroup() {
		return getDiseaseGroup ("human only");
	}

	@Transient
	public DiseaseGroup getOtherGroup() {
		return getDiseaseGroup ("non-genes");
	}

	@Transient
	public DiseaseGroup getNotObservedGroup() {
		return getDiseaseGroup ("not observed");
	}

	@Transient
	public DiseaseGroup getAdditionalGroup() {
		return getDiseaseGroup ("additional");
	}

	@Transient
	public Comparator<DiseaseModel> getDiseaseModelComparator()
	{
		return new DiseaseModelComparator();
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

	    // sort the models
		if (unique != null) {
	        Collections.sort(unique,
	        		this.getDiseaseModelComparator());
	    }
		
		return unique;
	}

	/* given DiseaseGroup 'dg', return a list of unique DiseaseModels in it,
	 * both positive models and NOT models
	 */
	@Transient
	private List<DiseaseModel> extractAllModelsFromGroup (DiseaseGroup dg) {
		return extractModelsFromGroup (dg, -1);
	}

	/* given DiseaseGroup 'dg', return a list of unique positive DiseaseModels
	 * in it
	 */
	@Transient
	private List<DiseaseModel> extractModelsFromGroup (DiseaseGroup dg) {
		return extractModelsFromGroup (dg, 0);
	}

	/* given DiseaseGroup 'dg', return a list of unique NOT DiseaseModels in
	 * it
	 */
	//    @Transient
	//    private List<DiseaseModel> extractNotModelsFromGroup (DiseaseGroup dg) {
	//	return extractModelsFromGroup (dg, 1);
	//    }

	@Transient
	private List<DiseaseModel> extractModelsFromGroup (DiseaseGroup dg, int notModels) {
		ArrayList<DiseaseModel> models = new ArrayList<DiseaseModel>();
		if (dg == null) { return models; }

		Iterator<DiseaseGroupRow> dgrIt = dg.getDiseaseGroupRows().iterator();
		DiseaseGroupRow dgr;

		while (dgrIt.hasNext()) {
			dgr = dgrIt.next();
			if (notModels == 0) {
				models.addAll (dgr.getDiseaseRow().getMouseModels());
			} else if (notModels == -1) {
				models.addAll (dgr.getDiseaseRow().getMouseModels());
				models.addAll (dgr.getDiseaseRow().getNotModels());
			} else {
				models.addAll (dgr.getDiseaseRow().getNotModels());
			}
		}
		return uniqueModels(models);
	}

	/* return all models for this disease, both positive and negative
	 */
	@Transient
	private List<DiseaseModel> getAllModels() {
		Iterator<DiseaseGroup> it = diseaseGroups.iterator();
		ArrayList<DiseaseModel> models = new ArrayList<DiseaseModel>();

		while (it.hasNext()) {
			models.addAll (extractAllModelsFromGroup(it.next()));
		}
		return uniqueModels(models);
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
		return filterModels(getAllModels(), 0).size();
	}

	/* get a count of all "NOT models"
	 */
	@Transient
	public int getCountOfNotModels() {
		return filterModels(getAllModels(), 1).size();
	}

	/* get a list of DiseaseModels where the disease occurs in mouse and human
	 * genes
	 */
	@Transient
	public List<DiseaseModel> getMouseAndHumanModels() {
		return uniqueModels( extractModelsFromGroup( getMouseHumanGroup() ) );
	}

	/* get a list of DiseaseModels where the disease only occurs in mouse,
	 * where those models have not already occurred in the mouse/human section
	 */
	@Transient
	public List<DiseaseModel> getMouseOnlyModels() {
		return uniqueModels( extractModelsFromGroup(getMouseOnlyGroup()) );
	}

	/* get a list of DiseaseModels where the disease occurs only in human,
	 * where those models have not already occurred in the mouse/human or the
	 * mouse-only sections
	 */
	@Transient
	public List<DiseaseModel> getHumanOnlyModels() {
		return uniqueModels( extractModelsFromGroup(getHumanOnlyGroup()) );
	}

	/* get a list of DiseaseModels for transgenes and other genome feature
	 * types (not genes).  These models must not have already occurred in the
	 * mouse-only, human-only, or mouse/human sections
	 */
	@Transient
	public List<DiseaseModel> getOtherModels() {
		return uniqueModels( extractModelsFromGroup(getOtherGroup()) );
	}

	/* get a list of NOT DiseaseModels -- cases where the researcher expected
	 * to see the disease phenotype, but did not.
	 */
	@Transient
	public List<DiseaseModel> getNotModels() {
		return uniqueModels( filterModels(getAllModels(), 1) );
	}

	/* get a list of leftover DiseaseModels -- those which do not appear in
	 * any other group
	 */
	@Transient
	public List<DiseaseModel> getAdditionalModels() {
		return uniqueModels( extractModelsFromGroup(getAdditionalGroup()) );
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

	@Column(name="disease_reference_count")
	public int getDiseaseReferenceCount() {
		return diseaseReferenceCount;
	}

	/** get an ordered list of disease groups for the disease
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

	@Column(name="hpo_term_count")
	public int getHpoTermCount() {
		return hpoTermCount;
	}

	@Column(name="genes_tab_count",insertable=false,updatable=false)
	public int getGenesTabCount() {
		return genesTabCount;
	}

	@Column(name="models_tab_count",insertable=false,updatable=false)
	public int getModelsTabCount() {
		return modelsTabCount;
	}

	
	/** get a vocab term object representing this disease
	 */
	@OneToOne (targetEntity=VocabTerm.class)
	@JoinColumn (name="disease_key")
	public VocabTerm getVocabTerm() {
		return vocabTerm;
	}


	// ================= Setters ===================== //

	public void setHpoTermCount(int hpoTermCount) {
		this.hpoTermCount = hpoTermCount;
	}

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

	public void setDiseaseReferenceCount(int diseaseReferenceCount) {
		this.diseaseReferenceCount = diseaseReferenceCount;
	}

	public void setVocabTerm(VocabTerm vocabTerm) {
		this.vocabTerm = vocabTerm;
	}

	public void setGenesTabCount(int genesTabCount) {
		this.genesTabCount = genesTabCount;
	}
	
	public void setModelsTabCount(int modelsTabCount) {
		this.modelsTabCount = modelsTabCount;
	}	

	// ================= Convenience ================= //

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append ("Disease Term (");
		sb.append (disease);
		sb.append ("), key ");
		sb.append (diseaseKey);
		sb.append (", ");
		sb.append (logicalDB);
		sb.append (" ID ");
		sb.append (primaryID);
		return sb.toString();
	}

	// ================= Private Classes ================= //

	private class DiseaseModelComparator extends SmartAlphaComparator<DiseaseModel>
	{
 		public int compare(DiseaseModel o1, DiseaseModel o2)
		{
			DiseaseModel i1 = (DiseaseModel) o1;
			DiseaseModel i2 = (DiseaseModel) o2;
			int stateCompare = super.compare(i1.getDisease(),i2.getDisease());
			if (stateCompare == 0) {
				stateCompare = super.compare(i1.getAllelePairsPlain(),i2.getAllelePairsPlain());
			}
			if (stateCompare == 0) {
				stateCompare = super.compare(i1.getBackgroundStrain(),i2.getBackgroundStrain());
			}
			return stateCompare;
		}
	}

}
