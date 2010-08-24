package mgi.frontend.datamodel;

import java.util.Set;
import java.util.List;
import javax.persistence.*;

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
public class Reference implements SortableObject {
	private int referenceKey;
	private String referenceType;
	private String primaryAuthor;
	private String authors;
	private String title;
	private String journal;
	private String vol;
	private String issue;
	private String pubDate;
	private String year;
	private String pages;
	private String jnumID;
	private String pubmedid;
	private int jnumNumeric;
	private String citation;
	private String shortCitation;
	private String refAbstract;
	private Set<ReferenceID> ids;
	private Set<Marker> markers;
	private String bookEditor;
	private String bookTitle;
	private String bookEdition;
	private String bookPlace;
	private String bookPublisher;
//	private List<MarkerReferenceAssociation> markerAssociations;
	private Integer countOfMarkers;
	private Integer countOfProbes;
	private Integer countOfGXDAssays;
	private Integer countOfGXDResults;
	private Integer countOfMappingResults;
	private Integer countOfSequences;
	private Integer countOfAlleles;
	private Integer countOfGXDIndex;
	
	public static String JNUM = "Reference.Jnum";
	public static String YEAR = "Reference.Year";
	public static String AUTHORS = "Reference.Authors";
	public static String TITLE = "Reference.Title";
	
	public Reference() {}
	
	@Id 
	@Column(name = "reference_key")
	public int getReferenceKey() {
		return referenceKey;
	}
	public void setReferenceKey(int referenceKey) {
		this.referenceKey = referenceKey;
	}
	@Column(name="reference_type")
	public String getReferenceType() {
		return referenceType;
	}
	public void setReferenceType(String referenceType) {
		this.referenceType = referenceType;
	}

	@Transient
	public Boolean isBook() {
	    return this.referenceType.equals("BOOK");
	}
	@Column(name="primary_author")
	public String getPrimaryAuthor() {
		return primaryAuthor;
	}
	public void setPrimaryAuthor(String primaryAuthor) {
		this.primaryAuthor = primaryAuthor;
	}
	public String getAuthors() {
		return authors;
	}
	public void setAuthors(String authors) {
		this.authors = authors;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getJournal() {
		return journal;
	}
	public void setJournal(String journal) {
		this.journal = journal;
	}
	public String getVol() {
		return vol;
	}
	public void setVol(String vol) {
		this.vol = vol;
	}
	public String getIssue() {
		return issue;
	}
	public void setIssue(String issue) {
		this.issue = issue;
	}
	@Column(name="pub_date")
	public String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getPages() {
		return pages;
	}
	public void setPages(String pages) {
		this.pages = pages;
	}
	@Column(name="jnum_id")
	public String getJnumID() {
		return jnumID;
	}
	public void setJnumID(String jnumID) {
		this.jnumID = jnumID;
	}
	@Column(name="pubmed_id")
    public String getPubMedID() {
        return pubmedid;
    }
    public void setPubMedID(String pubmedid) {
        this.pubmedid = pubmedid;
    }	
    @Column(name="jnum_numeric")
	public int getJnumNumeric() {
		return jnumNumeric;
	}
	public void setJnumNumeric(int jnumNumeric) {
		this.jnumNumeric = jnumNumeric;
	}
	public String getCitation() {
		return citation;
	}
	public void setCitation(String citation) {
		this.citation = citation;
	}
	
	@Column(name="short_citation")
	public String getShortCitation() {
		return shortCitation;
	}
	public void setShortCitation(String shortCitation) {
		this.shortCitation = shortCitation;
	}

	@Column(table="reference_abstract")
	@JoinColumn(name="reference_key")
	public String getAbstract() {
		return refAbstract;
	}

	public void setAbstract(String refAbstract) {
		this.refAbstract = refAbstract;
	}

	@OneToMany (targetEntity=Marker.class)
	@JoinTable (name="marker_to_reference",
			joinColumns=@JoinColumn(name="reference_key"),
			inverseJoinColumns=@JoinColumn(name="marker_key")
			)
	public Set<Marker> getMarkers() {
		return markers;
	}

	public void setMarkers(Set<Marker> markers) {
		this.markers = markers;
	}

	@OneToMany (targetEntity=ReferenceID.class)
	@JoinColumn (name="reference_key")
	public Set<ReferenceID> getIds() {
		return ids;
	}

	public void setIds(Set<ReferenceID> ids) {
		this.ids = ids;
	}

	@Column(table="reference_book", name="editor")
	@JoinColumn(name="reference_key")
	public String getBookEditor() {
		return bookEditor;
	}

	public void setBookEditor(String bookEditor) {
		this.bookEditor = bookEditor;
	}

	@Column(table="reference_book", name="book_title")
	@JoinColumn(name="reference_key")
	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	@Column(table="reference_book", name="edition")
	@JoinColumn(name="reference_key")
	public String getBookEdition() {
		return bookEdition;
	}

	public void setBookEdition(String bookEdition) {
		this.bookEdition = bookEdition;
	}

	@Column(table="reference_book", name="place")
	@JoinColumn(name="reference_key")
	public String getBookPlace() {
		return bookPlace;
	}

	public void setBookPlace(String bookPlace) {
		this.bookPlace = bookPlace;
	}

	@Column(table="reference_book", name="publisher")
	@JoinColumn(name="reference_key")
	public String getBookPublisher() {
		return bookPublisher;
	}

	public void setBookPublisher(String bookPublisher) {
		this.bookPublisher = bookPublisher;
	}


/*	@OneToMany (fetch=FetchType.LAZY)
	@JoinColumn(name="reference_key")
	@OrderBy ("sequenceNumRev")
	public List<MarkerReferenceAssociation> getMarkerAssociations() {
		return markerAssociations;
	}

	public void setMarkerAssociations(
			List<MarkerReferenceAssociation> markerAssociations) {
		this.markerAssociations = markerAssociations;
	}*/

	@Override
	public Comparable getComparableValue(String fieldname) throws NoSuchFieldException {
		Comparable value;
		if (fieldname.equals(JNUM)) {
			value = new Integer (this.getJnumNumeric());
		} else if (fieldname.equals(AUTHORS)) {
			value = this.getAuthors();
		} else if (fieldname.equals(YEAR)) {
			value = this.getYear();
		} else if (fieldname.equals(TITLE)) {
			value = this.getTitle();
		} else {
			throw new NoSuchFieldException ("Unknown field: " + fieldname);
		}
		return value;
	}

	@Column(table="reference_counts", name="marker_count")
	@JoinColumn(name="reference_key")
	public Integer getCountOfMarkers() {
		return countOfMarkers;
	}

	public void setCountOfMarkers(Integer countOfMarkers) {
		this.countOfMarkers = countOfMarkers;
	}

    @Column(table="reference_counts", name="probe_count")
    @JoinColumn(name="reference_key")
    public Integer getCountOfProbes() {
        return countOfProbes;
    }

    public void setCountOfProbes(Integer countOfProbes) {
        this.countOfProbes = countOfProbes;
    }
    
    @Column(table="reference_counts", name="gxd_assay_count")
    @JoinColumn(name="reference_key")
    public Integer getCountOfGXDAssays() {
        return countOfGXDAssays;
    }

    public void setCountOfGXDAssays(Integer countOfGXDAssays) {
        this.countOfGXDAssays = countOfGXDAssays;
    }
    
    @Column(table="reference_counts", name="gxd_result_count")
    @JoinColumn(name="reference_key")
    public Integer getCountOfGXDResults() {
        return countOfGXDResults;
    }

    public void setCountOfGXDResults(Integer countOfGXDResults) {
        this.countOfGXDResults = countOfGXDResults;
    }    
    
    @Column(table="reference_counts", name="mapping_expt_count")
    @JoinColumn(name="reference_key")
    public Integer getCountOfMappingResults() {
        return countOfMappingResults;
    }

    public void setCountOfMappingResults(Integer countOfMappingResults) {
        this.countOfMappingResults = countOfMappingResults;
    }      
    
    @Column(table="reference_counts", name="sequence_count")
    @JoinColumn(name="reference_key")
    public Integer getCountOfSequenceResults() {
        return countOfSequences;
    }

    public void setCountOfSequenceResults(Integer countOfSequences) {
        this.countOfSequences = countOfSequences;
    }     
    
    @Column(table="reference_counts", name="allele_count")
    @JoinColumn(name="reference_key")
    public Integer getCountOfAlleles() {
        return countOfAlleles;
    }

    public void setCountOfAlleles(Integer countOfAlleles) {
        this.countOfAlleles = countOfAlleles;
    }   
    
    @Column(table="reference_counts", name="gxd_index_count")
    @JoinColumn(name="reference_key")
    public Integer getCountOfGXDIndex() {
        return countOfGXDIndex;
    }

    public void setCountOfGXDIndex(Integer countOfGXDIndex) {
        this.countOfGXDIndex = countOfGXDIndex;
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