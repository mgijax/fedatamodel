package mgi.frontend.datamodel;

import java.util.*;
import javax.persistence.*;

import mgi.frontend.datamodel.util.DatamodelUtils;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/** one name:value property for a relationship where one marker is related
 * to another marker.  A RelatedMarker object can have a list of these.
 */
@Entity
@Table(name="marker_mrm_property")
public class RelatedMarkerProperty {
    private int uniqueKey;
    private int mrmKey;
    private String name;
    private String value;
    private int sequenceNum;

    /*--- Getters and Setters ---*/

    @Id
    @Column(name="unique_key")
    public int getUniqueKey() {
	return uniqueKey;
    }

    public void setUniqueKey(int uniqueKey) {
	this.uniqueKey = uniqueKey;
    }

    @Column(name="mrm_key")
    public int getMrmKey() {
	return mrmKey;
    }

    public void setMrmKey(int mrmKey) {
	this.mrmKey = mrmKey;
    }

    @Column(name="name")
    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    @Column(name="value")
    public String getValue() {
	return value;
    }

    public void setValue(String value) {
	this.value = value;
    }

    @Column(name="sequence_num")
    public int getSequenceNum() {
	return sequenceNum;
    }

    public void setSequenceNum(int sequenceNum) {
	this.sequenceNum = sequenceNum;
    }

}

