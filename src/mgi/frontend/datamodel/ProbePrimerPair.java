package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Is: a primer pair for a probe object
 */
@Entity
@Table(name="probe_primers")
public class ProbePrimerPair {
	private int probeKey;
	private String primerSequence1;
	private String primerSequence2;
	
    // ================= Getters and Setters ===================== //
	
	@Id
	@Column(name="probe_key")
	public int getProbeKey() {
		return probeKey;
	}

	public void setProbeKey(int probeKey) {
		this.probeKey = probeKey;
	}

	@Column(name="primer_sequence_1")
	public String getPrimerSequence1() {
		return primerSequence1;
	}

	public void setPrimerSequence1(String primerSequence1) {
		this.primerSequence1 = primerSequence1;
	}

	@Column(name="primer_sequence_2")
	public String getPrimerSequence2() {
		return primerSequence2;
	}

	public void setPrimerSequence2(String primerSequence2) {
		this.primerSequence2 = primerSequence2;
	}

	@Override
	public String toString() {
		return "ProbePrimerPair [probeKey=" + probeKey + ", primerSequence1=" + primerSequence1 + ", primerSequence2="
				+ primerSequence2 + "]";
	}
}
