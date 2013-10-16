package mgi.frontend.datamodel;

import java.util.*;
import javax.persistence.*;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Where;
import org.hibernate.annotations.SortType;

/**
 * Base object for geno clusters in the HDP grid drill down.
 */

@Entity
@Table(name="hdp_genocluster")
public class HdpGenoCluster {

    private int genoClusterKey;
    private Marker marker;
    private List<Genotype> genotypes;
    
    private List<HdpGenoClusterAnnotation> mpTerms;
    private List<HdpGenoClusterAnnotation> diseases;

    // ================= Getters and Setters ===================== //

    @Id
    @Column(name="hdp_genocluster_key")
    public int getGenoClusterKey() {
		return genoClusterKey;
	}
	public void setGenoClusterKey(int genoClusterKey) {
		this.genoClusterKey = genoClusterKey;
	}

	@ManyToOne (targetEntity=Marker.class, fetch=FetchType.LAZY)
	@JoinColumn (name="marker_key")
	public Marker getMarker() {
		return marker;
	}
	public void setMarker(Marker marker)
	{
		this.marker=marker;
	}
	
	@OneToMany(fetch=FetchType.LAZY)
    @JoinTable (name="hdp_genocluster_genotype",
            joinColumns=@JoinColumn(name="hdp_genocluster_key"),
            inverseJoinColumns=@JoinColumn(name="genotype_key")
            )
	@BatchSize (size=250)
	public List<Genotype> getGenotypes()
	{
		return genotypes;
	}
	public void setGenotypes(List<Genotype> genotypes)
	{
		this.genotypes = genotypes;
	}
	
	/*
	 * return just the first genotype, since we only need one to build a display string.
	 * 		No need to query for all of them.
	 * 		We don't care which one it is
	 */
	@Transient
	public Genotype getGenotype()
	{
		for(Genotype g : this.getGenotypes())
		{
			return g; // only return the first one
		}
		return null;
	}

	/** get a list of HdpGenoClusterAnnotation objects for diseases
	 */
	@OneToMany (targetEntity=HdpGenoClusterAnnotation.class)
	@JoinColumn(name="hdp_genocluster_key")
    @Where(clause = "annotation_type IN (1005,1006,1012,1013) AND term_type='term'" )
	@BatchSize (size=200)
	public List<HdpGenoClusterAnnotation> getDiseases() {
		return diseases;
	}
    public void setDiseases(List<HdpGenoClusterAnnotation> diseases) {
		this.diseases = diseases;
	}
    
	/** get a list of HdpGenoClusterAnnotation objects for phenotypes (MP)
	 */
	@OneToMany (targetEntity=HdpGenoClusterAnnotation.class)
	@JoinColumn(name="hdp_genocluster_key")
    @Where(clause = "annotation_type = 1002 AND term_type='term'" )
	@BatchSize (size=200)
	public List<HdpGenoClusterAnnotation> getMpTerms() {
		return mpTerms;
	}
    public void setMpTerms(List<HdpGenoClusterAnnotation> mpTerms) {
		this.mpTerms = mpTerms;
	}



    // ================= Convenience ================= //

    @Override
    public String toString() {
	StringBuffer sb = new StringBuffer();
	sb.append ("genoClusterKey (");
	sb.append (this.genoClusterKey);
	sb.append (")");
	return sb.toString();
    }
}
