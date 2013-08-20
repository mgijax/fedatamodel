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
 * AlleleMutation - one mutation for an allele (can have more than one of
 * these per Allele object.
 */
@Entity
@Table(name="allele_mutation")
public class AlleleMutation {

	private int uniqueKey;
	private int alleleKey;
	private String mutation;

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

	@Column(name="mutation")
	public String getMutation() {
		return mutation;
	}

    // ================= Setters ======================================== //

	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	public void setAlleleKey(int alleleKey) {
		this.alleleKey = alleleKey;
	}

	public void setMutation(String mutation) {
		this.mutation = mutation;
	}
}
