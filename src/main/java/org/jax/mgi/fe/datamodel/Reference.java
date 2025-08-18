package org.jax.mgi.fe.datamodel;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table (name="reference")
@SecondaryTables (
	{ @SecondaryTable (name="reference_abstract", pkJoinColumns= {
		@PrimaryKeyJoinColumn(name="reference_key", referencedColumnName="reference_key") } ),
	  @SecondaryTable (name="reference_counts", pkJoinColumns= {
		@PrimaryKeyJoinColumn(name="reference_key", referencedColumnName="reference_key") } ),
	  @SecondaryTable (name="reference_book", pkJoinColumns= {
		@PrimaryKeyJoinColumn(name="reference_key", referencedColumnName="reference_key") } )
	} )
public class Reference {

	private String authors;
	private String bookEdition;
	private String bookEditor;
	private String bookPlace;
	private String bookPublisher;
	private String bookTitle;
	private Integer countOfAlleles;
	private Integer countOfGOAnnotations;
	private Integer countOfGXDAssays;
	private Integer countOfGXDHtExperiments;
	private Integer countOfGXDIndex;
    private Integer countOfGXDResults;
    private Integer countOfGXDStructures;
	private Integer countOfMappingResults;
	private Integer countOfMarkers;
	private Integer countOfDiseaseModels;
	private Integer countOfOrthologs;
	private Integer countOfProbes;
	private Integer countOfStrains;
	private Integer countOfSequences;
    private Set<ReferenceID> ids;
    private String issue;
    private String jnumID;
    private int jnumNumeric;
    private String journal;
	private String longCitation;
	private Set<Marker> markers;
        private List<DiseaseModel> diseaseModels;
	private String miniCitation;
	private String pages;
	private String primaryAuthor;
	private String pubDate;
	private String pubmedid;
	private String refAbstract;
	private int referenceKey;
	private List<ReferenceNote> notes;
	private String referenceType;
	private String shortCitation;
	private String title;
	private String vol;
	private String year;

	/** retrieve the text of the note for the given note type, or null
	 * if there is no note of the given type
	 */
	@Transient
	public String filterNotes(String noteType) {
		Iterator<ReferenceNote> it = this.getNotes().iterator();
		ReferenceNote note;

		while (it.hasNext()) {
			note = it.next();
			if (note.getNoteType().equals(noteType)) {
				return note.getNote();
			}
		}
		return null;
	}

	/**
	 * Return the abstract, via the reference_key
	 */
	@Column(table="reference_abstract")
	@JoinColumn(name="reference_key")
	public String getAbstract() {
		return refAbstract;
	}
	public String getAuthors() {
		return authors;
	}

	/**
	 * Returns the edition, joined via the reference_key
	 * @return
	 */
	@Column(table="reference_book", name="edition")
	@JoinColumn(name="reference_key")
	public String getBookEdition() {
		return bookEdition;
	}

	/**
	 * Returns the editor, joined via the reference_key
	 * @return
	 */
	@Column(table="reference_book", name="editor")
	@JoinColumn(name="reference_key")
	public String getBookEditor() {
		return bookEditor;
	}

	/**
	 * Returns the place the book was published, joined by
	 * reference key
	 * @return
	 */
	@Column(table="reference_book", name="place")
	@JoinColumn(name="reference_key")
	public String getBookPlace() {
		return bookPlace;
	}

	/**
	 * Returns the publisher, joined by the reference_key
	 * @return
	 */
	@Column(table="reference_book", name="publisher")
	@JoinColumn(name="reference_key")
	public String getBookPublisher() {
		return bookPublisher;
	}

	/**
	 * Returns the book title, joined by the reference_key
	 * @return
	 */
	@Column(table="reference_book", name="book_title")
	@JoinColumn(name="reference_key")
	public String getBookTitle() {
		return bookTitle;
	}

	/**
	 * Return the count of associated alleles, joined by reference_key
	 * @return
	 */
	@Column(table="reference_counts", name="allele_count")
    @JoinColumn(name="reference_key")
    public Integer getCountOfAlleles() {
        return countOfAlleles;
    }

    /**
     * Return the count of GO annotations for this reference.
     * joined by reference_key
     * @return
     */
	@Column(table="reference_counts", name="go_annotation_count")
    @JoinColumn(name="reference_key")
    public Integer getCountOfGOAnnotations() {
        return countOfGOAnnotations;
    }

	/**
     * Return the count of associated gxd assays,
     * joined by reference_key
     * @return
     */
	@Column(table="reference_counts", name="gxd_assay_count")
    @JoinColumn(name="reference_key")
    public Integer getCountOfGXDAssays() {
        return countOfGXDAssays;
    }

	/**
     * Return the count of associated gxd high-throughput experiments,
     * joined by reference_key
     * @return
     */
	@Column(table="reference_counts", name="gxd_htexp_count")
    @JoinColumn(name="reference_key")
    public Integer getCountOfGXDHtExperiments() {
        return countOfGXDHtExperiments;
    }

	/**
     * Return the count of associated gxd index items,
     * joined by reference_key
     * @return
     */
	@Column(table="reference_counts", name="gxd_index_count")
    @JoinColumn(name="reference_key")
    public Integer getCountOfGXDIndex() {
        return countOfGXDIndex;
    }

    // ================= Getters and Setters ===================== //

	/**
     * Return the count of associated gxd assay results,
     * joined by reference_key
     * @return
     */
	@Column(table="reference_counts", name="gxd_result_count")
    @JoinColumn(name="reference_key")
    public Integer getCountOfGXDResults() {
        return countOfGXDResults;
    }

	/**
     * Return the count of associated gxd structures(tissues?),
     * joined by reference_key
     * @return
     */
	@Column(table="reference_counts", name="gxd_structure_count")
	@JoinColumn(name="reference_key")
    public Integer getCountOfGXDStructures() {
        return countOfGXDStructures;
    }

	/**
     * Return the count of associated mapping experiments,
     * joined by reference_key
     * @return
     */
	@Column(table="reference_counts", name="mapping_expt_count")
    @JoinColumn(name="reference_key")
    public Integer getCountOfMappingResults() {
        return countOfMappingResults;
    }

	/**
     * Return the count of associated markers,
     * joined by reference_key
     * @return
     */
	@Column(table="reference_counts", name="marker_count")
	@JoinColumn(name="reference_key")
	public Integer getCountOfMarkers() {
		return countOfMarkers;
	}

	/**
     * Return the count of associated disease models,
     * joined by reference_key
     * @return
     */
	@Column(table="reference_counts", name="disease_model_count")
	@JoinColumn(name="reference_key")
	public Integer getCountOfDiseaseModels() {
		return countOfDiseaseModels;
	}

	/**
	 * Return the count of Orthologs, this currently isn't
	 * in the database, so will have to be overriden when it is.
	 * @return
	 */
	@Transient
    public Integer getCountOfOrthologs() {
        return 0;
    }

	/**
     * Return the count of associated probes,
     * joined by reference_key
     * @return
     */
	@Column(table="reference_counts", name="probe_count")
    @JoinColumn(name="reference_key")
    public Integer getCountOfProbes() {
        return countOfProbes;
    }

	/**
     * Return the count of associated probes,
     * joined by reference_key
     * @return
     */
	@Column(table="reference_counts", name="strain_count")
    @JoinColumn(name="reference_key")
    public Integer getCountOfStrains() {
        return countOfStrains;
    }

	/**
     * Return the count of associated sequences,
     * joined by reference_key
     * @return
     */
	@Column(table="reference_counts", name="sequence_count")
    @JoinColumn(name="reference_key")
    public Integer getCountOfSequenceResults() {
        return countOfSequences;
    }

	@Transient
	public String getDoiId() {
		for(ReferenceID id: this.ids){
			if (id.getLogicalDB().equals("Journal Link")) {
				return id.getAccID();
			}
		}
		return "";
	}

	@Transient
	public String getFullTextLink() {
		return this.filterNotes("Full Text");
	}

	/**
	 * Return a collection of all possible reference IDs.
	 * @return
	 */
	@OneToMany (targetEntity=ReferenceID.class)
	@JoinColumn (name="reference_key")
	public Set<ReferenceID> getIds() {
		return ids;
	}

	public String getIssue() {
		return issue;
	}

	 @Column(name="jnum_id")
	public String getJnumID() {
		return jnumID;
	}

	@Column(name="jnum_numeric")
	public int getJnumNumeric() {
		return jnumNumeric;
	}

    public String getJournal() {
		return journal;
	}

    @Column(name="long_citation")
	public String getLongCitation() {
        return longCitation;
    }

    /**
	 * Returns a collection of markers
	 * @return
	 */
	@OneToMany (targetEntity=Marker.class)
	@JoinTable (name="marker_to_reference",
			joinColumns=@JoinColumn(name="reference_key"),
			inverseJoinColumns=@JoinColumn(name="marker_key")
			)
	public Set<Marker> getMarkers() {
		return markers;
	}

    /**
	 * Returns the list of DiseaseModel objects for this reference.
         * There is one DiseaseModel object per genotype/disease for the ref.
	 * @return
	 */
	@OneToMany (targetEntity=DiseaseModel.class, fetch=FetchType.LAZY)
	@JoinTable (name="disease_model_to_reference",
			joinColumns=@JoinColumn(name="reference_key"),
			inverseJoinColumns=@JoinColumn(name="disease_model_key")
			)
	public List<DiseaseModel> getDiseaseModels() {
		return diseaseModels;
	}

    /**
     * Returns disease models for this reference, grouped by genotype. 
     */
    @Transient
    public List<List<DiseaseModel>> getDiseaseModelsGrouped() {
        List<List<DiseaseModel>> grouped = new ArrayList<List<DiseaseModel>> ();
        String prevID = null;
        List<DiseaseModel> current = null;
        for (DiseaseModel dm : getDiseaseModels()) {
            if (prevID != null && dm.getGenotype().getPrimaryID().equals(prevID)) {
                current.add(dm);
            } else {
                if (current != null) {
                    grouped.add(current);
                }
                current = new ArrayList<DiseaseModel>();
                current.add(dm);
            }
            prevID = dm.getGenotype().getPrimaryID();
        }
        if (current != null) {
            grouped.add(current);
        }
        return grouped;
    }
    
    @Column(name="mini_citation")
	public String getMiniCitation() {
		return miniCitation;
	}

	/** return a collection of notes for this reference
	 */
	@OneToMany (targetEntity=ReferenceNote.class)
	@JoinColumn(name="reference_key")
	public List<ReferenceNote> getNotes() {
		return notes;
	}

	public String getPages() {
		return pages;
	}

	@Column(name="primary_author")
	public String getPrimaryAuthor() {
		return primaryAuthor;
	}

	@Column(name="pub_date")
	public String getPubDate() {
		return pubDate;
	}

	@Column(name="pubmed_id")
    public String getPubMedID() {
        return pubmedid;
    }

	@Transient
	public String getReferenceDetailNote() {
		return this.filterNotes("Reference Detail");
	}

	@Id
	@Column(name = "reference_key")
	public int getReferenceKey() {
		return referenceKey;
	}

	@Column(name="reference_type")
	public String getReferenceType() {
		return referenceType;
	}

	@Column(name="short_citation")
	public String getShortCitation() {
		return shortCitation;
	}

	public String getTitle() {
		return title;
	}

    public String getVol() {
		return vol;
	}

    public String getYear() {
		return year;
	}

	@Transient
	public Boolean isBook() {
	    return this.referenceType.equals("BOOK");
	}

	public void setAbstract(String refAbstract) {
		this.refAbstract = refAbstract;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public void setBookEdition(String bookEdition) {
		this.bookEdition = bookEdition;
	}

	public void setBookEditor(String bookEditor) {
		this.bookEditor = bookEditor;
	}

	public void setBookPlace(String bookPlace) {
		this.bookPlace = bookPlace;
	}

	public void setBookPublisher(String bookPublisher) {
		this.bookPublisher = bookPublisher;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public void setCountOfAlleles(Integer countOfAlleles) {
        this.countOfAlleles = countOfAlleles;
    }

	public void setCountOfGOAnnotations (Integer countOfGOAnnotations) {
        this.countOfGOAnnotations = countOfGOAnnotations;
    }

	public void setCountOfGXDAssays(Integer countOfGXDAssays) {
        this.countOfGXDAssays = countOfGXDAssays;
    }

	public void setCountOfGXDHtExperiments(Integer countOfGXDHtExperiments) {
        this.countOfGXDHtExperiments = countOfGXDHtExperiments;
    }

	public void setCountOfGXDIndex(Integer countOfGXDIndex) {
        this.countOfGXDIndex = countOfGXDIndex;
    }

	public void setCountOfGXDResults(Integer countOfGXDResults) {
        this.countOfGXDResults = countOfGXDResults;
    }

	public void setCountOfGXDStructures(Integer countOfGXDStructures) {
        this.countOfGXDStructures = countOfGXDStructures;
    }

	public void setCountOfMappingResults(Integer countOfMappingResults) {
        this.countOfMappingResults = countOfMappingResults;
    }

	public void setCountOfMarkers(Integer countOfMarkers) {
		this.countOfMarkers = countOfMarkers;
	}

	public void setCountOfDiseaseModels(Integer countOfDiseaseModels) {
		this.countOfDiseaseModels = countOfDiseaseModels;
	}

	public void setCountOfOrthologs(Integer countOfOrthologs) {
        this.countOfOrthologs = countOfOrthologs;
    }

	public void setCountOfProbes(Integer countOfProbes) {
        this.countOfProbes = countOfProbes;
    }

	public void setCountOfStrains(Integer countOfStrains) {
        this.countOfStrains = countOfStrains;
    }

	public void setCountOfSequenceResults(Integer countOfSequences) {
        this.countOfSequences = countOfSequences;
    }

	public void setIds(Set<ReferenceID> ids) {
		this.ids = ids;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}

	public void setJnumID(String jnumID) {
		this.jnumID = jnumID;
	}

	public void setJnumNumeric(int jnumNumeric) {
		this.jnumNumeric = jnumNumeric;
	}

	public void setJournal(String journal) {
		this.journal = journal;
	}

    public void setLongCitation(String longCitation) {
        this.longCitation = longCitation;
    }

    public void setMarkers(Set<Marker> markers) {
		this.markers = markers;
	}

    public void setDiseaseModels(List<DiseaseModel> diseaseModels) {
		this.diseaseModels = diseaseModels;
	}

    public void setMiniCitation(String citation) {
		this.miniCitation = citation;
	}

    public void setNotes(List<ReferenceNote> notes) {
		this.notes = notes;
	}

    public void setPages(String pages) {
		this.pages = pages;
	}

    public void setPrimaryAuthor(String primaryAuthor) {
		this.primaryAuthor = primaryAuthor;
	}

    public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

    public void setPubMedID(String pubmedid) {
        this.pubmedid = pubmedid;
    }

    public void setReferenceKey(int referenceKey) {
		this.referenceKey = referenceKey;
	}

    public void setReferenceType(String referenceType) {
		this.referenceType = referenceType;
	}

    public void setShortCitation(String shortCitation) {
		this.shortCitation = shortCitation;
	}

    public void setTitle(String title) {
		this.title = title;
	}

    public void setVol(String vol) {
		this.vol = vol;
	}

    public void setYear(String year) {
		this.year = year;
	}

	@Override
    public String toString() {
        return "Reference [authors=" + authors + ", bookEdition=" + bookEdition
                + ", bookEditor=" + bookEditor + ", bookPlace=" + bookPlace
                + ", bookPublisher=" + bookPublisher + ", bookTitle="
                + bookTitle + ", countOfAlleles=" + countOfAlleles
                + ", countOfGXDAssays=" + countOfGXDAssays
                + ", countOfGXDIndex=" + countOfGXDIndex
                + ", countOfGXDResults=" + countOfGXDResults
                + ", countOfGXDStructures=" + countOfGXDStructures
                + ", countOfMappingResults=" + countOfMappingResults
                + ", countOfMarkers=" + countOfMarkers + ", countOfOrthologs="
                + countOfOrthologs + ", countOfProbes=" + countOfProbes
                + ", countOfSequences=" + countOfSequences + ", ids=" + ids
                + ", issue=" + issue + ", jnumID=" + jnumID + ", jnumNumeric="
                + jnumNumeric + ", journal=" + journal + ", longCitation="
                + longCitation + ", markers=" + markers + ", diseaseModels=" + diseaseModels
                + ", miniCitation=" + miniCitation + ", pages=" + pages + ", primaryAuthor="
                + primaryAuthor + ", pubDate=" + pubDate + ", pubmedid="
                + pubmedid + ", refAbstract=" + refAbstract + ", referenceKey="
                + referenceKey + ", referenceType=" + referenceType
                + ", shortCitation=" + shortCitation + ", title=" + title
                + ", vol=" + vol + ", year=" + year + "]";
    }
}
