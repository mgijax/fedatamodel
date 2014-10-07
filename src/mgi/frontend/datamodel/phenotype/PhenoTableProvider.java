package mgi.frontend.datamodel.phenotype;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * This object defines the a genotype's provider in the phenotable grid
 */
@Entity
@Table(name="phenotable_provider")
public class PhenoTableProvider {

	private int uniqueKey;
	private int phenotableGenotypeKey;
	private PhenoTableCenter phenotypingCenter;
	private PhenoTableCenter interpretationCenter;

	/*--- fields from the phenotable_provider table ---*/

	@Id
	@Column(name="unique_key")
	public int getUniqueKey() {
		return uniqueKey;
	}

	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	@Column(name="phenotable_genotype_key")
	public int getPhenotableGenotypeKey() {
		return phenotableGenotypeKey;
	}

	public void setPhenotableGenotypeKey (int phenotableGenotypeKey) {
		this.phenotableGenotypeKey = phenotableGenotypeKey;
	}

	@OneToOne (targetEntity=PhenoTableCenter.class)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name="phenotyping_center_key")
	public PhenoTableCenter getPhenotypingCenter() {
		return phenotypingCenter;
	}

	public void setPhenotypingCenter(PhenoTableCenter phenotypingCenter) {
		this.phenotypingCenter = phenotypingCenter;
	}

	@OneToOne (targetEntity=PhenoTableCenter.class)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name="interpretation_center_key")
	public PhenoTableCenter getInterpretationCenter() {
		return interpretationCenter;
	}

	public void setInterpretationCenter(PhenoTableCenter interpretationCenter) {
		this.interpretationCenter = interpretationCenter;
	}

	/*--- convenience methods related to phenotyping centers ---*/

	@Transient
	public int getPhenotypingCenterKey() {
		if (this.phenotypingCenter == null) { return -1; }
		return this.phenotypingCenter.getCenterKey();
	}

	@Transient
	public String getPhenotypingCenterName() {
		if (this.phenotypingCenter == null) { return null; }
		return this.phenotypingCenter.getName();
	}

	@Transient
	public String getPhenotypingCenterAbbreviation() {
		if (this.phenotypingCenter == null) { return null; }
		return this.phenotypingCenter.getAbbreviation();
	}

	@Transient
	public int getPhenotypingCenterSequenceNum() {
		if (this.phenotypingCenter == null) { return -1; }
		return this.phenotypingCenter.getSequenceNum();
	}

	/*--- convenience methods related to interpretation centers ---*/

	@Transient
	public int getInterpretationCenterKey() {
		if (this.interpretationCenter == null) { return -1; }
		return this.interpretationCenter.getCenterKey();
	}

	@Transient
	public String getInterpretationCenterName() {
		if (this.interpretationCenter == null) { return null; }
		return this.interpretationCenter.getName();
	}

	@Transient
	public String getInterpretationCenterAbbreviation() {
		if (this.interpretationCenter == null) { return null; }
		return this.interpretationCenter.getAbbreviation();
	}

	@Transient
	public int getInterpretationCenterSequenceNum() {
		if (this.interpretationCenter == null) { return -1; }
		return this.interpretationCenter.getSequenceNum();
	}

	/*--- transient methods for convenience ---*/

	/* get the provider string, considering both phenotyping and
	 * interpretation centers
	 */
	@Transient
	public String getProviderString() {
		StringBuffer sb = new StringBuffer();
		String pc = this.getPhenotypingCenterAbbreviation();
		String ic = this.getInterpretationCenterAbbreviation();

		if (ic != null) {
			sb.append(ic);
		}

		if (pc != null) {
		    if (!pc.equals(ic)) {
			if (sb.length() > 0) {
				sb.append(" - ");
			}
			sb.append(pc);
		    }
		}
		return sb.toString();
	}

	/* get the provider description, considering both phenotyping and
	 * interpretation centers
	 */
	@Transient
	public String getProviderDescription() {
		StringBuffer sb = new StringBuffer();
		String pc = this.getPhenotypingCenterName();
		String ic = this.getInterpretationCenterName();

		if (ic != null) {
			if (ic.equals("MGI")) {
				return "MGI Curated Data";
			} else if (ic.equals("EuroPhenome")) {
				return "Phenotype annotations from <B>EuroPhenome</B>";
			}
			sb.append("Data Interpretation Center: <B>");
			sb.append(ic);
			sb.append("</B>");
		}

		if (pc != null) {
		    if (sb.length() > 0) {
			sb.append("<BR>");
		    }
		    sb.append("Phenotyping Center: <B>");
		    sb.append(pc);
		    sb.append("</B>");
		}
		return sb.toString();
	}
}
