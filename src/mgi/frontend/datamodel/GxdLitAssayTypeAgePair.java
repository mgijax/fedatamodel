package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/** represents one (assay type, age) pair -- noting how and when expression
 * was studied for a certain (marker, reference) pair.  The (marker,
 * reference) pair is a GxdLitIndexRecord.
 */
@Entity
@Table(name="expression_index_stages")
public class GxdLitAssayTypeAgePair {

	String age;		// age at which expression was studied
	String assayType;	// type of assay used to study it
	String uniqueKey;	// uniquely identifies this record
	String indexKey;	// identifies the (marker, reference) pair
	
	// getters

	@Column(name="age_string")
	public String getAge() {
		return age;
	}
	
	@Column(name="assay_type")
	public String getAssayType() {
		return assayType;
	}
	
	@Column(name="index_key")
	public String getIndexKey() {
		return indexKey;
	}
	
	@Id
	@Column(name="unique_key")
	public String getUniqueKey() {
		return uniqueKey;
	}

	// setters

	public void setAge(String age) {
		this.age = age;
	}
	public void setAssayType(String assay_type) {
		this.assayType = assay_type;
	}
	public void setIndexKey(String indexKey) {
		this.indexKey = indexKey;
	}
	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}
}
