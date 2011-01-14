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
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * A representation of a marker designed to work well with the batch query
 * interface.  There is nothing lazy-loaded, so everything is retrieved up
 * front.  (So there are no subsequent queries for associated objects when
 * walking through the display layer.)  This class only contains those items
 * necessary to the batch query interface.
 * 
 * By building this class separate from the full Marker class, we reserve the
 * right to restructure and optimize the schema supporting the batch query
 * interface, say by pulling some or all of the values from joined tables 
 * into specially-formatted single fields for fast retrieval.  (We could then 
 * parse those special values transparently in this class, with the caller 
 * having no knowledge of the optimization.)
 * 
 * @author jsb
 */
        
@Entity
@Table(name="marker")
public class BatchMarker {
    
    private int markerKey;
    private String symbol;
    private String name;
    private List<MarkerLocation> locations;
    private List<MarkerID> ids;
    private List<MarkerAnnotation> annotations;
    private List<BatchMarkerAllele> alleles;
    private List<MarkerTissueCount> expressionCountsByTissue;

    //--- Private Filters ---//
    
	/** used to make other convenience methods to extract only a certain type
	 * of annotations from the full List of annotations
	 */
	@Transient
	private List<MarkerAnnotation> filterAnnotations (String annotationType) {
		ArrayList<MarkerAnnotation> sublist = new ArrayList<MarkerAnnotation>();
		Iterator<MarkerAnnotation> it = this.getAnnotations().iterator();
		MarkerAnnotation annotation;
		
		while (it.hasNext()) {
			annotation = it.next();
			if (annotation.getAnnotationType().equals(annotationType)) {
				sublist.add(annotation);
			}
		}
		return sublist;
	}

	/** retrieve the IDs from the given logical database name
	 */
	@Transient
	private List<MarkerID> filterMarkerIDs (String logicalDatabase) {
		ArrayList<MarkerID> sublist = new ArrayList<MarkerID>();
		Iterator<MarkerID> it = this.getIds().iterator();
		MarkerID item;
		
		while (it.hasNext()) {
			item = it.next();
			if (item.getLogicalDB().equals(logicalDatabase)) {
				sublist.add(item);
			}
		}
		return sublist;
	}

	/** get the a single ID for the given logical database, or null if none
	 */
	@Transient
	private MarkerID getSingleID (String logicalDatabase) {
		List<MarkerID> ids = this.filterMarkerIDs(logicalDatabase);
		if (ids.size() > 0) {
			return ids.get(0);
		}
		return null;
	}

	//--- Public Helper Methods ---//
	
	@Transient
	public MarkerLocation getBestLocation() {
		if (this.locations.size() > 0) {
			return this.locations.get(0);
		}
		return null;
	}
	
	@Transient
	public List<MarkerAnnotation> getGOAnnotations () {
		return this.filterAnnotations("GO/Marker");
	}
	
	@Transient
	public List<MarkerAnnotation> getMPAnnotations () {
		return this.filterAnnotations("Mammalian Phenotype/Genotype");
	}

	@Transient
	public List<MarkerAnnotation> getOMIMAnnotations () {
		return this.filterAnnotations("OMIM/Genotype");
	}

	@Transient
	public List<MarkerID> getSequenceIDs() {
		List<MarkerID> sublist = this.filterMarkerIDs("Sequence DB");
		sublist.addAll(this.filterMarkerIDs("RefSeq"));
		return sublist;
	}

	@Transient
	public List<MarkerID> getUniProtIDs () {
		List<MarkerID> ids = this.filterMarkerIDs ("SWISS-PROT");
		ids.addAll(this.filterMarkerIDs("TrEMBL"));
		return ids;
	}  

	@Transient
	public MarkerID getEnsemblGeneModelID() {
		return this.getSingleID("Ensembl Gene Model");
	}

	@Transient
	public MarkerID getEntrezGeneID() {
		return this.getSingleID("Entrez Gene");
	}

	@Transient
	public MarkerID getVegaGeneModelID() {
		return this.getSingleID("VEGA Gene Model");
	}

    //--- Getters and Setters ---//
    @Transient
    public List<BatchMarkerAllele> getAlleles() {
		return alleles;
	}
	
	@OneToMany (targetEntity=MarkerAnnotation.class)
	@JoinColumn(name="marker_key")
	@OrderBy("sequenceNum")
    public List<MarkerAnnotation> getAnnotations() {
		return annotations;
	}
	
	@OneToMany (targetEntity=MarkerTissueCount.class)
	@JoinColumn(name="marker_key")
	@OrderBy("sequenceNum")
    public List<MarkerTissueCount> getExpressionCountsByTissue() {
		return expressionCountsByTissue;
	}
	
	@OneToMany (targetEntity=MarkerID.class)
	@JoinColumn(name="marker_key")
	public List<MarkerID> getIds() {
		return ids;
	}
	
	@OneToMany (targetEntity=MarkerLocation.class)
	@JoinColumn(name="marker_key")
	@OrderBy("sequenceNum")
    public List<MarkerLocation> getLocations() {
		return locations;
	}
	
    @Id
    @Column(name="marker_key")
    public int getMarkerKey() {
		return markerKey;
	}
	
    @Column(name="name")
    public String getName() {
		return name;
	}
	
    @Column(name="symbol")
    public String getSymbol() {
		return symbol;
	}
	
    public void setAlleles(List<BatchMarkerAllele> alleles) {
		this.alleles = alleles;
	}
	
    public void setAnnotations(List<MarkerAnnotation> annotations) {
		this.annotations = annotations;
	}
	
    public void setExpressionCountsByTissue(
			List<MarkerTissueCount> expressionCountsByTissue) {
		this.expressionCountsByTissue = expressionCountsByTissue;
    }

    public void setIds(List<MarkerID> ids) {
		this.ids = ids;
	}
	
	public void setLocations(List<MarkerLocation> locations) {
		this.locations = locations;
	}
	
	public void setMarkerKey(int markerKey) {
		this.markerKey = markerKey;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	@Override
	public String toString() {
		return "BatchMarker ["
				+ (alleles != null ? "alleles=" + alleles + ", " : "")
				+ (annotations != null ? "annotations=" + annotations + ", "
						: "")
				+ (expressionCountsByTissue != null ? "expressionCountsByTissue="
						+ expressionCountsByTissue + ", "
						: "") + (ids != null ? "ids=" + ids + ", " : "")
				+ (locations != null ? "locations=" + locations + ", " : "")
				+ "markerKey=" + markerKey + ", "
				+ (name != null ? "name=" + name + ", " : "")
				+ (symbol != null ? "symbol=" + symbol : "") + "]";
	}
}
