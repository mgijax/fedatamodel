package org.jax.mgi.fe.datamodel;

import java.util.Comparator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.jax.mgi.fe.datamodel.sort.SmartAlphaComparator;

/**
 * ProbeReferenceAssociation
 * Associates a Reference with a Probe, including various data that's related to the probe
 * through a particular reference.
 */

@Entity
@Table (name="probe_to_reference")
public class ProbeReferenceAssociation {

	protected Probe probe;
    protected int probeReferenceKey;
    protected Reference reference;
    protected int hasRestrictionMap;
    protected int hasSequences;
    protected int hasAliases;
    protected int hasPolymorphisms;
    protected String note;
    protected String qualifier;
    protected List<ProbeAlias> aliases;
    protected List<ProbeSequence> probeSequences;
    protected List<ProbePolymorphism> probePolymorphisms;

    // ================= Transient methods ===================== //

	@Transient
	public Comparator<ProbeReferenceAssociation> getComparator() {
		return new ProbeReferenceAssociationComparator();
	}

    // ================= Getters and Setters ===================== //

	@OneToMany (targetEntity=ProbeAlias.class)
	@BatchSize(size=20)
	@JoinColumn(name="probe_reference_key")
    @OrderBy("sequenceNum")
	public List<ProbeAlias> getAliases() {
		return aliases;
	}

	@Column(name="has_aliases")
	public int getHasAliases() {
		return hasAliases;
	}

	@Column(name="has_polymorphisms")
    public int getHasPolymorphisms() {
		return hasPolymorphisms;
	}

	@Column(name="has_restriction_map")
	public int getHasRestrictionMap() {
		return hasRestrictionMap;
	}

	@Column(name="has_sequences")
    public int getHasSequences() {
		return hasSequences;
	}

	@Column(name="note")
	public String getNote() {
		return note;
	}

	/** Return the actual probe object for this relationship
     */
    @ManyToOne (targetEntity=Probe.class, fetch=FetchType.LAZY)
    @JoinColumn (name="probe_key")
    public Probe getProbe() {
    	return probe;
    }

	@OneToMany (targetEntity=ProbePolymorphism.class)
	@BatchSize(size=20)
	@JoinColumn(name="probe_reference_key")
    @OrderBy("sequenceNum")
	public List<ProbePolymorphism> getProbePolymorphisms() {
		return probePolymorphisms;
	}

	@Id
	@Column(name="probe_reference_key")
	public int getProbeReferenceKey() {
		return probeReferenceKey;
	}

	@OneToMany (targetEntity=ProbeSequence.class)
	@BatchSize(size=20)
	@JoinColumn(name="probe_reference_key")
    @OrderBy("sequenceID")
	public List<ProbeSequence> getProbeSequences() {
		return probeSequences;
	}

	@Column(name="qualifier")
	public String getQualifier() {
		return qualifier;
	}

    /** Return the actual reference object for this relationship
     */
	@ManyToOne (targetEntity=Reference.class, fetch=FetchType.LAZY)
    @JoinColumn (name="reference_key")
	public Reference getReference() {
		return reference;
	}

	public void setAliases(List<ProbeAlias> aliases) {
		this.aliases = aliases;
	}

	public void setHasAliases(int hasAliases) {
		this.hasAliases = hasAliases;
	}

	public void setHasPolymorphisms(int hasPolymorphisms) {
		this.hasPolymorphisms = hasPolymorphisms;
	}

	public void setHasRestrictionMap(int hasRestrictionMap) {
		this.hasRestrictionMap = hasRestrictionMap;
	}

	public void setHasSequences(int hasSequences) {
		this.hasSequences = hasSequences;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public void setProbe(Probe probe) {
    	this.probe = probe;
    }

	public void setProbePolymorphisms(List<ProbePolymorphism> probePolymorphisms) {
		this.probePolymorphisms = probePolymorphisms;
	}

	public void setProbeReferenceKey(int probeReferenceKey) {
		this.probeReferenceKey = probeReferenceKey;
	}

	public void setProbeSequences(List<ProbeSequence> probeSequences) {
		this.probeSequences = probeSequences;
	}

	public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}

	public void setReference(Reference reference) {
		this.reference = reference;
	}

	@Override
	public String toString() {
		return "ProbeReferenceAssociation [probe=" + probe + ", probeReferenceKey=" + probeReferenceKey + ", reference="
				+ reference + ", hasRestrictionMap=" + hasRestrictionMap + ", hasSequences=" + hasSequences
				+ ", hasAliases=" + hasAliases + ", hasPolymorphisms=" + hasPolymorphisms + ", note=" + note
				+ ", qualifier=" + qualifier + "]";
	}

	private class ProbeReferenceAssociationComparator extends SmartAlphaComparator<ProbeReferenceAssociation>
	{
		public int compare(ProbeReferenceAssociation m1, ProbeReferenceAssociation m2) {
			int c = super.compare(m1.getReference().getYear(), m2.getReference().getYear());
			if (c == 0) {
				c = super.compare(m1.getReference().getJnumID(), m2.getReference().getJnumID());
			}
			return c;
		}
	}
}
