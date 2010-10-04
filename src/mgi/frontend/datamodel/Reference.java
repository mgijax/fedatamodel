package mgi.frontend.datamodel;

import java.util.Set;
import java.util.List;
import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

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
@JsonIgnoreProperties({"markers"})
public class Reference {

	private String authors;
	private String bookEdition;
	private String bookEditor;
	private String bookPlace;
	private String bookPublisher;
	private String bookTitle;
	private String citation;
	private Integer countOfAlleles;
	private Integer countOfGXDAssays;
	private Integer countOfGXDIndex;
	private Integer countOfGXDResults;
	private Integer countOfMappingResults;
	private Integer countOfMarkers;
	private Integer countOfProbes;
	private Integer countOfSequences;
	private Set<ReferenceID> ids;
	private String issue;
	private String jnumID;
	private int jnumNumeric;
	private String journal;
	private Set<Marker> markers;
	private String pages;
	private String primaryAuthor;
	private String pubDate;
	private String pubmedid;
	private String refAbstract;
	private int referenceKey;
	private String referenceType;
	private String shortCitation;
	private String title;
	private String vol;
	private String year;
	
    // ================= Getters and Setters ===================== //
	
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
	
	public String getCitation() {
		return citation;
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
     * Return the count of associated gxd index items, 
     * joined by reference_key
     * @return
     */	
	@Column(table="reference_counts", name="gxd_index_count")
    @JoinColumn(name="reference_key")
    public Integer getCountOfGXDIndex() {
        return countOfGXDIndex;
    }

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
     * Return the count of associated sequences, 
     * joined by reference_key
     * @return
     */	
	@Column(table="reference_counts", name="sequence_count")
    @JoinColumn(name="reference_key")
    public Integer getCountOfSequenceResults() {
        return countOfSequences;
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

	public void setCitation(String citation) {
		this.citation = citation;
	}

	public void setCountOfAlleles(Integer countOfAlleles) {
        this.countOfAlleles = countOfAlleles;
    }

	public void setCountOfGXDAssays(Integer countOfGXDAssays) {
        this.countOfGXDAssays = countOfGXDAssays;
    }

	public void setCountOfGXDIndex(Integer countOfGXDIndex) {
        this.countOfGXDIndex = countOfGXDIndex;
    }

	public void setCountOfGXDResults(Integer countOfGXDResults) {
        this.countOfGXDResults = countOfGXDResults;
    }

	public void setCountOfMappingResults(Integer countOfMappingResults) {
        this.countOfMappingResults = countOfMappingResults;
    }

	public void setCountOfMarkers(Integer countOfMarkers) {
		this.countOfMarkers = countOfMarkers;
	}

	public void setCountOfProbes(Integer countOfProbes) {
        this.countOfProbes = countOfProbes;
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

    public void setMarkers(Set<Marker> markers) {
		this.markers = markers;
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
		return "Reference ["
				+ (authors != null ? "authors=" + authors + ", " : "")
				+ (bookEdition != null ? "bookEdition=" + bookEdition + ", "
						: "")
				+ (bookEditor != null ? "bookEditor=" + bookEditor + ", " : "")
				+ (bookPlace != null ? "bookPlace=" + bookPlace + ", " : "")
				+ (bookPublisher != null ? "bookPublisher=" + bookPublisher
						+ ", " : "")
				+ (bookTitle != null ? "bookTitle=" + bookTitle + ", " : "")
				+ (citation != null ? "citation=" + citation + ", " : "")
				+ (countOfMarkers != null ? "countOfMarkers=" + countOfMarkers
						+ ", " : "")
				+ (ids != null ? "ids=" + ids + ", " : "")
				+ (issue != null ? "issue=" + issue + ", " : "")
				+ (jnumID != null ? "jnumID=" + jnumID + ", " : "")
				+ "jnumNumeric="
				+ jnumNumeric
				+ ", "
				+ (journal != null ? "journal=" + journal + ", " : "")
				+ (pages != null ? "pages=" + pages + ", " : "")
				+ (primaryAuthor != null ? "primaryAuthor=" + primaryAuthor
						+ ", " : "")
				+ (pubDate != null ? "pubDate=" + pubDate + ", " : "")
				+ (refAbstract != null ? "refAbstract=" + refAbstract + ", "
						: "")
				+ "referenceKey="
				+ referenceKey
				+ ", "
				+ (referenceType != null ? "referenceType=" + referenceType
						+ ", " : "")
				+ (shortCitation != null ? "shortCitation=" + shortCitation
						+ ", " : "")
				+ (title != null ? "title=" + title + ", " : "")
				+ (vol != null ? "vol=" + vol + ", " : "")
				+ (year != null ? "year=" + year : "") + "]";
	}
}