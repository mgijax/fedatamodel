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
    private Boolean isPreferred;
    private Boolean isPrivate;
    private String sequenceNum;
    private String organism;
    
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
    
    @Column(name="organism")
    public String getOrganism() {
        return organism;
    }
    
    @Column(name="acc_id")
    public String getAccID() {
        return accID;
    }
    
    @Column(name="preferred")
    public Boolean getIsPreferred() {
        return isPreferred;
    }
    
    @Column(name="private")
    public Boolean getIsPrivate() {
        return isPrivate;
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
    public void setOrganism(String organism) {
        this.organism = organism;
    }
    public void setAccID(String accID) {
        this.accID = accID;
    }
    public void setIsPreferred(Boolean isPreferred) {
        this.isPreferred = isPreferred;
    }
    public void setIsPrivate(Boolean isPrivate) {
        this.isPrivate = isPrivate;
    }
    public void setSequenceNum(String sequenceNum) {
        this.sequenceNum = sequenceNum;
    }
    
    

}
