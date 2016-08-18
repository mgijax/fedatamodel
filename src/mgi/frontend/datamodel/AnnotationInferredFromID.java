package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="annotation_inferred_from_id")
public class AnnotationInferredFromID {
    
    private String uniqueKey;
    private String annotationKey;
    private String logicalDB;
    private String accID;
    private String sequenceNum;
    private String tooltip;
    
    @Id
    @Column(name="unique_key")
    public String getUniqueKey() {
        return uniqueKey;
    }
    
    @Column(name="annotation_key")
    public String getAnnotationKey() {
        return annotationKey;
    }
    
    @Column(name="logical_db")
    public String getLogicalDB() {
        return logicalDB;
    }
    
    @Column(name="tooltip")
    public String getTooltip() {
        return tooltip;
    }
    
    @Column(name="acc_id")
    public String getAccID() {
        return accID;
    }
    
    @Column(name="sequence_num")
    public String getSequenceNum() {
        return sequenceNum;
    }
    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }
    public void setAnnotationKey(String annotationKey) {
        this.annotationKey = annotationKey;
    }
    public void setLogicalDB(String logicalDB) {
        this.logicalDB = logicalDB;
    }
    public void setTooltip(String tooltip) {
        this.tooltip = tooltip;
    }
    public void setAccID(String accID) {
        this.accID = accID;
    }
    public void setSequenceNum(String sequenceNum) {
        this.sequenceNum = sequenceNum;
    }
    
    

}
