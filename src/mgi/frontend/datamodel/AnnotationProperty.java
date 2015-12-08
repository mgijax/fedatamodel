package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="annotation_property")
public class AnnotationProperty {

    private int uniqueKey;
	private int annotationKey;
    private int sequenceNum;
    private int stanza;

    private String property;
    private String value;
    private String type;

    @Id
    @Column(name="unique_key")
    public int getUniqueKey() {
        return uniqueKey;
    }

    @Column(name="annotation_key")
    public int getAnnotationKey() {
        return annotationKey;
    }

    @Column(name="sequence_num")
    public int getSequenceNum() {
        return sequenceNum;
    }

    @Column(name="stanza")
    public int getStanza() {
        return stanza;
    }

    @Column(name="property")
    public String getProperty() {
        return property;
    }

    @Column(name="value")
    public String getValue() {
        return value;
    }

    @Column(name="type")
    public String getType() {
        return type;
    }



    public void setUniqueKey(int uniqueKey) {
        this.uniqueKey = uniqueKey;
    }
    public void setAnnotationKey(int annotationKey) {
        this.annotationKey = annotationKey;
    }
    public void setSequenceNum(int sequenceNum) {
        this.sequenceNum = sequenceNum;
    }
    public void setStanza(int stanza) {
        this.stanza = stanza;
    }


    public void setProperty(String property) {
        this.property = property;
    }
    public void setValue(String value) {
        this.value = value;
    }

    public void setType(String type) {
    	this.type = type;
    }

}
