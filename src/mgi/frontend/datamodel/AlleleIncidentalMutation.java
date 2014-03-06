package mgi.frontend.datamodel;

import java.util.*;
import javax.persistence.*;

import mgi.frontend.datamodel.util.DatamodelUtils;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * AlleleIncidentalMutation
 *
 * This object represents an incidental mutation link
 */
@Entity
@Table(name="allele_incidental_mut")
public class AlleleIncidentalMutation 
{
	private int uniqueKey;
	private int alleleKey;
	private String filename;


    // ================= Getters ======================================== //
	@Id
	@Column(name="unique_key")
	public int getUniqueKey() {
		return uniqueKey;
	}

	@Column(name="allele_key")
	public int getAlleleKey() {
		return alleleKey;
	}

	@Column(name="filename")
	public String getFilename() {
		return filename;
	}
	
    // ================= Setters ======================================== //
	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	public void setAlleleKey(int alleleKey) {
		this.alleleKey = alleleKey;
	}

	public void setFilename(String filename) {
		this.filename= filename;
	}

	@Override
	public String toString() {
		return filename;
	}
}
