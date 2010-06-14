package mgi.frontend.datamodel;

import javax.persistence.*;
import java.util.Comparator;


// proof of concept -- now defunct

public class MarkerReferenceAssociationComparator implements Comparator {
	public static final int BY_MARKER = 0;
	public static final int BY_REFERENCE = 1;
	
	private int mode = BY_MARKER;
	
	private MarkerReferenceAssociationComparator() {}

	public MarkerReferenceAssociationComparator(int sortBy) {
		if ((sortBy != BY_MARKER) && (sortBy != BY_REFERENCE)) {
			throw new IllegalArgumentException ("Illegal value for sortBy: " + sortBy);
		}
		this.mode = sortBy;
		return;
	}

	public int compare (Object a, Object b) {
	    MarkerReferenceAssociation a1 = (MarkerReferenceAssociation) a;
	    MarkerReferenceAssociation b1 = (MarkerReferenceAssociation) b;
	    int aVal = 0;
	    int bVal = 0;
	    
	    switch (mode) {
	    	case BY_MARKER:
	    		aVal = a1.getSequenceNumRev();
	    		bVal = b1.getSequenceNumRev();
	    		break;
	    	case BY_REFERENCE:
	    		aVal = a1.getSequenceNumFwd();
	    		bVal = b1.getSequenceNumFwd();
	    		break;
	    }
	    if (aVal < bVal) { return -1; }
	    else if (aVal > bVal) { return 1; }

	    return 0;	    
	}
}
