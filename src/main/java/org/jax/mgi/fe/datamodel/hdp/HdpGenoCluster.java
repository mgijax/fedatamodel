package org.jax.mgi.fe.datamodel.hdp;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Where;
import org.jax.mgi.fe.datamodel.Genotype;
import org.jax.mgi.fe.datamodel.Marker;

/**
 * Base object for geno clusters in the HDP grid drill down.
 */

@Entity
@Table(name="hdp_genocluster")
public class HdpGenoCluster {

    private int genoClusterKey;
    private List<HdpGenoClusterMarker> markers;
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

	@OneToMany (targetEntity=HdpGenoClusterMarker.class, fetch=FetchType.LAZY)
	@JoinColumn (name="hdp_genocluster_key")
	public List<HdpGenoClusterMarker> getMarkers() {
		return markers;
	}
	public void setMarkers(List<HdpGenoClusterMarker> markers)
	{
		this.markers = markers;
	}
	
	// Return the first marker for the genocluster that is not a Transgene, if there is one.  Null otherwise.
	@Transient
	public Marker getNonTransgeneMarker() {
		for (HdpGenoClusterMarker m : this.getMarkers()) {
			if (!"Transgene".equals(m.getMarkerType())) {
				return m.getMarker();
			}
		}
		return null;
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
	/*
	 * annot key 1005 is genotype -> disease
	 * annot key 1006 is allele -> disease
	 * annot key 1012 is human marker to disease
	 *  annot key 1013 is phenotypic human marker to disease
	 */
    @Where(clause = "annotation_type IN (1005) AND term_type='term'" )
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
	// annot key 1002 is MP -> genotype
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
