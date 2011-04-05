package mgi.frontend.datamodel;

import java.util.Comparator;

/** a GenotypeComparator is used to sort Genotype objects by their alleles.
 * @author jsb
 */
public class GenotypeComparator implements Comparator {

	/** no-argument constructor
	 */
	public GenotypeComparator() {}
	
	public int compare (Object a, Object b) {
		return ((Genotype) a).compareTo(b);
	}
}
