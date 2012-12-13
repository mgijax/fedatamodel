package mgi.frontend.datamodel.phenotype;

import java.util.*;
import javax.persistence.*;

import mgi.frontend.datamodel.Genotype;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * This object defines the a genotype's provider in the phenotable grid
 */
@Entity
@Table(name="phenotable_provider")
public class PhenoTableProvider {

	private int phenoTableProviderKey;
	private String provider;
	private int providerSeq;

    // ================= Getters ======================================== //

	@Id
	@Column(name="phenotable_provider_key")
	public int getPhenoTableProviderKey() {
		return phenoTableProviderKey;
	}

	/*
	 * This is the predefined order for display in the phenotype summary table
	 */
	@Column(name="provider_seq")
	public int getProviderSeq() {
		return providerSeq;
	}

	@Column(name="provider")
	public String getProvider() {
		return provider;
	}
	
	/*
	 * Return the image file name for this provider's icon in the phenotable grid
	 */
	@Transient
	public String getProviderIcon()
	{
		if(provider.equalsIgnoreCase("MGI")) return "mgi_col.png";
		if(provider.equalsIgnoreCase("WTSI")) return "wtsi_col.png";
		if(provider.equalsIgnoreCase("EuPh")) return "euro_col.png";
		return "";
	}

	// ================= Setters ======================================== //

	public void setPhenoTableProviderKey(int phenoTableProviderKey) {
		this.phenoTableProviderKey = phenoTableProviderKey;
	}


	public void setProviderSeq(int providerSeq) {
		this.providerSeq = providerSeq;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

}