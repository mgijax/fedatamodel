package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/** one name:value property for a relationship where one allele is related
 * to a marker.  A RelatedMarker object can have a list of these.
 */
@Entity
@Table(name="allele_arm_property")
public class AlleleRelatedMarkerProperty {
    private int uniqueKey;
    private int armKey;
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

    @Column(name="arm_key")
    public int getArmKey() {
	return armKey;
    }

    public void setArmKey(int armKey) {
	this.armKey = armKey;
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

