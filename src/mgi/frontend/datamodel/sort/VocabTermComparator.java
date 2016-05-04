package mgi.frontend.datamodel.sort;

import mgi.frontend.datamodel.VocabTerm;


/*
 * Compare VocabTerm objects by term column
 * Smart Alpha
 */
public class VocabTermComparator extends SmartAlphaComparator<VocabTerm>{

	public int compare(VocabTerm o1, VocabTerm o2)
	{
		return compare(o1.getTerm(), o2.getTerm());
	}
}
