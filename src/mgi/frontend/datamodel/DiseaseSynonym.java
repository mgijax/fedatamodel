package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DiseaseSynonym
 * @author jsb
 * is a synonym for a Disease.
 */
@Entity
@Table (name="disease_synonym")
public class DiseaseSynonym {
	private int uniqueKey;
	private int diseaseKey;
	private int sequenceNum;
	private String synonym;

    // ================= Getters ===================== //
	
    @Id
    @Column(name="unique_key")
    public int getUniqueKey() {
	return uniqueKey;
    }

    @Column(name="disease_key")
    public int getDiseaseKey() {
	return diseaseKey;
    }

    @Column(name="sequence_num")
    public int getSequenceNum() {
	return sequenceNum;
    }

    @Column(name="synonym")
    public String getSynonym() {
	return synonym;
    }

    // ================= Setters ===================== //

    public void setUniqueKey(int uniqueKey) {
	this.uniqueKey = uniqueKey;
    }

    public void setDiseaseKey(int diseaseKey) {
	this.diseaseKey = diseaseKey;
    }

    public void setSequenceNum(int sequenceNum) {
	this.sequenceNum = sequenceNum;
    }

    public void setSynonym(String synonym) {
	this.synonym = synonym;
    }

    // ================= Convenience ================= //

    @Override
    public String toString() {
	StringBuffer sb = new StringBuffer();
	sb.append ("DiseaseSynonym (");
	sb.append (this.synonym);
	sb.append ("), uniqueKey ");
	sb.append (this.uniqueKey);
	sb.append (", sequenceNum ");
	sb.append (this.sequenceNum);
	sb.append (", diseaseKey ");
	sb.append (this.diseaseKey);
	return sb.toString();
    }
}
