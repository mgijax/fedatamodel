package mgi.frontend.datamodel;

import java.util.HashMap;
import java.util.Map;

/**
 * GxdLitGenRecord
 * @author mhall
 * This datamodel class is NOT built from the database.  It is instead a class that is built up 
 * from other Datamodel classes.
 * 
 * This class represents two parts of a tuple relationship, a marker, and all the references related to that 
 * marker.  When used in conjunction with the HxdLitReferenceRecord object, the full tuple relationship is
 * realized.
 */

public class GxdLitGeneRecord {

	private String markerSymbol;
	private String markerName;
	
	//  This datamodel class, contains a mapping of another class.  This allows us
	// to effectively represent the tuple information needed for these objects.
	
	private Map <String, GxdLitReferenceRecord> referenceRecords = new HashMap<String, GxdLitReferenceRecord> ();
	public String getMarkerName() {
		return markerName;
	}
	public String getMarkerSymbol() {
		return markerSymbol;
	}
	public Map<String, GxdLitReferenceRecord> getReferenceRecords() {
		return referenceRecords;
	}
	public void setMarkerName(String markerName) {
		this.markerName = markerName;
	}
	public void setMarkerSymbol(String markerSymbol) {
		this.markerSymbol = markerSymbol;
	}
	public void setReferenceRecords(
			Map<String, GxdLitReferenceRecord> referenceRecords) {
		this.referenceRecords = referenceRecords;
	}
	
}
