package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SequenceCloneCollection
 * @author jsb
 * is a clone collection associated with a sequence object
 */
@Entity
@Table (name="sequence_clone_collection")
public class SequenceCloneCollection {

    private String collection;
    private int sequenceKey;
	private int uniqueKey;
    
    // ================= Getters and Setters ===================== //
    
    public String getCollection() {
        return collection;
    }

    @Column(name="sequence_key")
    public int getSequenceKey() {
        return sequenceKey;
    }

	@Id
	@Column(name="unique_key")
	public int getUniqueKey() {
		return uniqueKey;
	}

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public void setSequenceKey(int sequenceKey) {
        this.sequenceKey = sequenceKey;
    }

    public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}
}
