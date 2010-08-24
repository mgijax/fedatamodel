package mgi.frontend.datamodel;

import javax.persistence.*;

@Entity
@Table (name="referenceToSequence")
public class ReferenceToSeq {

    @Id
    protected String uniqueKey;
    protected String sequenceKey;
    protected String referenceKey;


    public String getReferenceKey() {
        return referenceKey;
    }



    public void setReferenceKey(String referenceKey) {
        this.referenceKey = referenceKey;
    }



    public String getSequenceKey() {
        return sequenceKey;
    }



    public void setSequenceKey(String sequenceKey) {
        this.sequenceKey = sequenceKey;
    }

	
}
