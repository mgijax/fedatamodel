package mgi.frontend.datamodel;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * Base object alleles.  This is represented by a core in our flower schema.
 * @author mhall, jsb
 * Base Allele object.  Tricky parts are commented inline.
 */


@Entity
@Table(name="allele")
@FilterDef(name="genotypeHasPhenotypeData2")
public class AllelePhenoSummary {
    private int alleleKey;
    private String alleleSubType;
    private String alleleType;
    private String driver;
    private String geneName;
    private int isRecombinase;
    private int isWildType;
    private String name;
    private String onlyAlleleSymbol;
    private String primaryID;
    private String symbol;
    private Set<AllelePhenoGenotypeAssociation> genotypeAssociations;

    // ================= Getters and Setters ===================== //

	@Id
    @Column(name="allele_key")
    public int getAlleleKey() {
        return alleleKey;
    }

	@Column(name="allele_subtype")
    public String getAlleleSubType() {
        return alleleSubType;
    }

    @Column(name="allele_type")
    public String getAlleleType() {
        return alleleType;
    }

    public String getDriver() {
        return driver;
    }

    /**
     * Return the gene name for the gene this allele is part of.
     * @return
     */

    @Column(name="gene_name")
    public String getGeneName() {
        return geneName;
    }

    @OneToMany (fetch=FetchType.EAGER)
    @LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name="allele_key")
    @Filter(
    		// This filter can be turned on to only select genotypes that have phenotype data
    		name = "genotypeHasPhenotypeData2",
    		condition="has_phenotype_data=1"
    	)
    public Set<AllelePhenoGenotypeAssociation> getGenotypeAssociations() {
		return genotypeAssociations;
	}

    /**
     * Is this a cre allele?
     * @return
     */
    @Column(name="is_recombinase")
    public int getIsRecombinase() {
        return isRecombinase;
    }

	/** is this a wild-type allele?  (1 if yes, 0 if no)
     */
    @Column(name="is_wild_type")
    public int getIsWildType() {
		return isWildType;
	}


    public String getName() {
        return name;
    }

    
    /**
     * String representation of the allele only part of the allele symbol
     * @return
     */

    @Column(name="only_allele_symbol")
    public String getOnlyAlleleSymbol() {
        return onlyAlleleSymbol;
    }
    @Column(name="primary_id")
    public String getPrimaryID() {
        return primaryID;
    }



    public String getSymbol() {
        return symbol;
    }
   
    public void setAlleleKey(int alleleKey) {
        this.alleleKey = alleleKey;
    }
    public void setAlleleSubType(String alleleSubType) {
        this.alleleSubType = alleleSubType;
    }

    public void setAlleleType(String alleleType) {
        this.alleleType = alleleType;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public void setGeneName(String geneName) {
        this.geneName = geneName;
    }

    public void setGenotypeAssociations(
			Set<AllelePhenoGenotypeAssociation> genotypeAssociations) {
		this.genotypeAssociations = genotypeAssociations;
	}

   
    public void setIsRecombinase(int isRecombinase) {
        this.isRecombinase = isRecombinase;
    }

    public void setIsWildType(int isWildType) {
		this.isWildType = isWildType;
	}

    /* The three IMSR counts exist in a separate table (allele_imsr_counts)
     * which we join in to make the attributes appear as if they were
     * sitting in the allele table itself.
     */

    public void setName(String name) {
        this.name = name;
    }


    public void setOnlyAlleleSymbol(String onlyAlleleSymbol) {
        this.onlyAlleleSymbol = onlyAlleleSymbol;
    }

	public void setPrimaryID(String primaryID) {
        this.primaryID = primaryID;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

}
