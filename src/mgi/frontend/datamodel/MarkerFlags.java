package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * MarkerFlags
 * @author jsb
 * One of these objects represents a set of flags associated with a marker (for data where we don't need counts).
 */
@Entity
@Table(name="marker_flags")
public class MarkerFlags {
    
	/***--- instance variables ---***/
	
	private int markerKey;
	private int hasWildTypeExpressionData;
	private int hasPhenotypesRelatedToAnatomy;
	
	/***--- getters and setters ---***/
	
	@Column(name="has_phenotypes_related_to_anatomy")
	public int getHasPhenotypesRelatedToAnatomy() {
		return hasPhenotypesRelatedToAnatomy;
	}
	
	@Column(name="has_wildtype_expression_data")
	public int getHasWildTypeExpressionData() {
		return hasWildTypeExpressionData;
	}

	@Id
	@Column(name="marker_key")
	public int getMarkerKey() {
		return markerKey;
	}

	public void setHasPhenotypesRelatedToAnatomy(int hasPhenotypesRelatedToAnatomy) {
		this.hasPhenotypesRelatedToAnatomy = hasPhenotypesRelatedToAnatomy;
	}

	public void setHasWildTypeExpressionData(int hasWildTypeExpressionData) {
		this.hasWildTypeExpressionData = hasWildTypeExpressionData;
	}

	public void setMarkerKey(int markerKey) {
		this.markerKey = markerKey;
	}
}
