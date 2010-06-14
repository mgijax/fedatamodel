package mgi.frontend.datamodel;

import java.util.Set;
import java.util.List;
import javax.persistence.*;

@Entity
@Table (name="reference")
@SecondaryTables (
	{ @SecondaryTable (name="referenceAbstract", pkJoinColumns= {
		@PrimaryKeyJoinColumn(name="referenceKey", referencedColumnName="referenceKey") } ),
	  @SecondaryTable (name="referenceCounts", pkJoinColumns= {
		@PrimaryKeyJoinColumn(name="referenceKey", referencedColumnName="referenceKey") } ),
	  @SecondaryTable (name="referenceBook", pkJoinColumns= {
		@PrimaryKeyJoinColumn(name="referenceKey", referencedColumnName="referenceKey") } ) 
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
	private int jnumNumeric;
	private String citation;
	private String shortCitation;
	private String refAbstract;
	private Set<ReferenceID> ids;
	private Set<Marker> markerKeys;
	private String bookEditor;
	private String bookTitle;
	private String bookEdition;
	private String bookPlace;
	private String bookPublisher;
	private List<MarkerReferenceAssociation> markerAssociations;
	private Integer countOfMarkers;
	
	public static String JNUM = "Reference.Jnum";
	public static String YEAR = "Reference.Year";
	public static String AUTHORS = "Reference.Authors";
	public static String TITLE = "Reference.Title";
	
	public Reference() {}
	
	@Id
	public int getReferenceKey() {
		return referenceKey;
	}
	public void setReferenceKey(int referenceKey) {
		this.referenceKey = referenceKey;
	}
	public String getReferenceType() {
		return referenceType;
	}
	public void setReferenceType(String referenceType) {
		this.referenceType = referenceType;
	}
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
	public String getJnumID() {
		return jnumID;
	}
	public void setJnumID(String jnumID) {
		this.jnumID = jnumID;
	}
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
	
	@Column(name="shortCitation")
	public String getShortCitation() {
		return shortCitation;
	}
	public void setShortCitation(String shortCitation) {
		this.shortCitation = shortCitation;
	}

	@Column(table="referenceAbstract")
	@JoinColumn(name="referenceKey")
	public String getAbstract() {
		return refAbstract;
	}

	public void setAbstract(String refAbstract) {
		this.refAbstract = refAbstract;
	}

	@OneToMany (targetEntity=Marker.class)
	@JoinTable (name="markerToReference",
			joinColumns=@JoinColumn(name="referenceKey"),
			inverseJoinColumns=@JoinColumn(name="markerKey")
			)
	public Set<Marker> getMarkerKeys() {
		return markerKeys;
	}

	public void setMarkerKeys(Set<Marker> markerKeys) {
		this.markerKeys = markerKeys;
	}

	@OneToMany (targetEntity=ReferenceID.class)
	@JoinColumn (name="referenceKey")
	public Set<ReferenceID> getIds() {
		return ids;
	}

	public void setIds(Set<ReferenceID> ids) {
		this.ids = ids;
	}

	@Column(table="referenceBook", name="editor")
	@JoinColumn(name="referenceKey")
	public String getBookEditor() {
		return bookEditor;
	}

	public void setBookEditor(String bookEditor) {
		this.bookEditor = bookEditor;
	}

	@Column(table="referenceBook")
	@JoinColumn(name="referenceKey")
	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	@Column(table="referenceBook", name="edition")
	@JoinColumn(name="referenceKey")
	public String getBookEdition() {
		return bookEdition;
	}

	public void setBookEdition(String bookEdition) {
		this.bookEdition = bookEdition;
	}

	@Column(table="referenceBook", name="place")
	@JoinColumn(name="referenceKey")
	public String getBookPlace() {
		return bookPlace;
	}

	public void setBookPlace(String bookPlace) {
		this.bookPlace = bookPlace;
	}

	@Column(table="referenceBook", name="publisher")
	@JoinColumn(name="referenceKey")
	public String getBookPublisher() {
		return bookPublisher;
	}

	public void setBookPublisher(String bookPublisher) {
		this.bookPublisher = bookPublisher;
	}


	@OneToMany (fetch=FetchType.LAZY)
	@JoinColumn(name="referenceKey")
	@OrderBy ("sequenceNumRev")
	public List<MarkerReferenceAssociation> getMarkerAssociations() {
		return markerAssociations;
	}

	public void setMarkerAssociations(
			List<MarkerReferenceAssociation> markerAssociations) {
		this.markerAssociations = markerAssociations;
	}

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

	@Column(table="referenceCounts", name="markerCount")
	@JoinColumn(name="referenceKey")
	public Integer getCountOfMarkers() {
		return countOfMarkers;
	}

	public void setCountOfMarkers(Integer countOfMarkers) {
		this.countOfMarkers = countOfMarkers;
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
//				+ (markerAssociations != null ? "markerAssociations="
	//					+ markerAssociations + ", " : "")
		//		+ (markerKeys != null ? "markerKeys=" + markerKeys + ", " : "")
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