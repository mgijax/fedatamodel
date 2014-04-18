package mgi.frontend.datamodel;

import java.util.Comparator;

/** a GenotypeComparator is used to sort Genotype objects by their alleles.
 * @author jsb
 */
public class GenotypeComparator implements Comparator<Genotype> {

	/** no-argument constructor
	 */
	public GenotypeComparator() {}
	
	public int compare (Genotype a, Genotype b) {
		return a.compareTo(b);
	}
}
