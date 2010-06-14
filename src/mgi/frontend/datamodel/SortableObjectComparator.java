package mgi.frontend.datamodel;

import java.util.Comparator;
import java.util.List;
import java.util.Iterator;

/** Is: a Comparator for SortableObjects.  Multi-level sorting is allowed,
 * 		as long as the specified fields are recognized by each SortableObject's
 * 		getComparableValue() method.  Each field can also be sorted in an
 * 		ascending or descending manner.
 *  Has: a list of fieldnames to sort by and a list of boolean flags to
 *  	indicate whether each should be sorting in ascending or descending
 *  	manner.  (default for every field is ascending, if not specified)
 *  Does: compares SortableObjects
 *  
 * @author jsb
 *
 */
public class SortableObjectComparator implements Comparator {
	
	//--- instance variables ---//
	
	// fields to sort by (1st is primary, 2nd is secondary, etc.)
	private String[] sortByFields = null;
	
	// length of this.sortByFields (to avoid recomputing it repeatedly)
	private int sortByFieldsLength = 0;
	
	// parallel array to this.sortByFields.  A true value for item i means
	// that the i-th field in this.sortByFields should be sorted in
	// ascending order.
	private boolean[] isAscending = null;
	
	//--- constructors ---//
	
	/** hide the default constructor by making it private
	 */
	private SortableObjectComparator() {}

	/** constructor.  All fields will be sorted in ascending order.
	 * @param sortByFields List of strings, each of which is a fieldname to
	 * 		sort by.  Precedence is given to earlier fields in the List.
	 */
	public SortableObjectComparator(List sortByFields) {
		String[] temp = (String[]) sortByFields.toArray(new String[sortByFields.size()]);
		this.initialize(temp, null);
		return;
	}

	/** constructor
	 * @param sortByFields List of strings, each of which is a fieldname to
	 * 		sort by.  Precedence is given to earlier fields in the List.
	 * @param isAscending array of booleans, parallel to sortByFields.  For
	 * 		each i, if isAscending[i] == true, then that field should be
	 * 		sorted in ascending manner.  (false means descending)  If
	 * 		isAscending is longer than sortByFields, then we only use the
	 * 		first sortByFields.length values from isAscending.  If
	 * 		isAscending is shorter than sortByFields, then we default to true
	 * 		for any i where i >= isAscending.length.
	 */
	public SortableObjectComparator(List sortByFields, boolean[] isAscending) {
		String[] temp = (String[]) sortByFields.toArray(
			new String[sortByFields.size()]);
		this.initialize(temp, isAscending);
		return;
	}

	/** constructor.  All fields will be sorted in ascending order.
	 * @param sortByFields array of strings, each of which is a fieldname to
	 * 		sort by.  Precedence is given to earlier fields in the array.
	 */
	public SortableObjectComparator(String[] sortByFields) {
		this.initialize(sortByFields, null);
		return;
	}
	
	/** constructor
	 * @param sortByFields array of strings, each of which is a fieldname to
	 * 		sort by.  Precedence is given to earlier fields in the array.
	 * @param isAscending array of booleans, parallel to sortByFields.  For
	 * 		each i, if isAscending[i] == true, then that field should be
	 * 		sorted in ascending manner.  (false means descending)  If
	 * 		isAscending is longer than sortByFields, then we only use the
	 * 		first sortByFields.length values from isAscending.  If
	 * 		isAscending is shorter than sortByFields, then we default to true
	 * 		for any i where i >= isAscending.length.
	 */
	public SortableObjectComparator(String[] sortByFields, boolean[] isAscending) {
		this.initialize(sortByFields, isAscending);
		return;
	}
	
	/** private method; called by various constructors to initialize the
	 * 		object in a standard manner.  If isAscending is shorter than
	 * 		sortByFields, then extra fields will be ascending.  If
	 * 		isAscending is larger than sortByFields, then the extra
	 * 		isAscending values will be ignored.
	 * @param sortByFields as from constructors
	 * @param isAscending as from constructors
	 */
	private void initialize (String[] sortByFields, boolean[] isAscending) {
		this.sortByFields = sortByFields; 
		this.sortByFieldsLength = this.sortByFields.length;

		// if we were not given an isAscending array, or we were given one with
		// an incorrect length, then initially assume that all fields are to be
		// sorted in ascending order
		
		if ((isAscending == null) || (isAscending.length != sortByFields.length)) {
			this.isAscending = new boolean[this.sortByFieldsLength];
			for (int i=0; i < this.isAscending.length; i++) {
				this.isAscending[i] = true;
			}						
		} else {
			// we were given an isAscending array of the proper length, so use it
			// as-is
			this.isAscending = isAscending;
		}
		
		// if we were given an isAscending array and it was the wrong length,
		// then update this.isAscending with all the values we can use.  For any
		// others, we keep the 'true' default value.
		
		if (isAscending != null) {
			if (isAscending.length < sortByFields.length) {
				for (int i=0; i < this.isAscending.length; i++) {
					this.isAscending[i] = isAscending[i];
				}
			} else if (isAscending.length > sortByFields.length) {
				for (int i=0; i < this.sortByFieldsLength; i++) {
					this.isAscending[i] = isAscending[i];
				}
			}
		}
		return;		
	}
	
	/** iterates through objects to ensure that the fields specified in
	 * the constructor are recognized by them.  bails out with an exception
	 * when the first faulty fieldname is detected.
	 * @param objects the List of objects to be checked
	 * @throws NoSuchFieldException when one of the given objects has no
	 * 		knowledge of one of the fieldnames specified in the constructor
	 * 		of this Comparator.
	 */
	public void checkFields (List<SortableObject> objects) throws NoSuchFieldException {
		Iterator<SortableObject> objectIterator = objects.iterator();
		SortableObject obj;
		Comparable val;
		
		while (objectIterator.hasNext()) {
			obj = objectIterator.next();
			for (int i = 0; i < this.sortByFieldsLength; i++) {
				val = obj.getComparableValue(this.sortByFields[i]);
			}
		}
		return;
	}
	
	/** compare method for two SortableObjects, with multi-level sorting
	 * 		based on the values given to the constructor of this Comparator.
	 * 		If a fieldname passed in to the constructor is unknown to either
	 * 		a or b, then the object's toString() method is used for the sake 
	 * 		of comparison for that field for particular object.  (No 
	 * 		exceptions are raised since the Comparator interface does not 
	 * 		allow them.  If you want to check that fields are valid, you can
	 * 		use the checkFields() method before doing your sorting.)
	 * @param a the first object to be compared
	 * @param b the second object to be compared
	 */
	public int compare (Object a, Object b) {
	    SortableObject a1 = (SortableObject) a;		// cast a
	    SortableObject b1 = (SortableObject) b;		// cast b
	    Comparable aVal;							// a's value to be compared
	    Comparable bVal;							// b's value to be compared
	    String fieldname;							// name of current field
	    int cmpVal = 0;								// aVal compared to bVal
	    
	    // Iterate through the fields to sort by.  As soon as we find a
	    // field with a difference for a and b, then we can bail out with
	    // the proper sort value.
	    
	    for (int i = 0; i < this.sortByFieldsLength; i++) {

	    	// get a's value for this field
	    	try {
	    		aVal = a1.getComparableValue(this.sortByFields[i]);
	    	} catch (NoSuchFieldException e) {
	    		aVal = a.toString();
	    	}
	    	
	    	// get b's value for this field
	    	try {
	    		bVal = b1.getComparableValue(this.sortByFields[i]);
	    	} catch (NoSuchFieldException e) {
	    		bVal = b.toString();
	    	}
	    	
	    	// compare them.  If different, then we can handle the ascending/
	    	// descending flag and return the appropriate value.
	    	
	    	cmpVal = aVal.compareTo(bVal);
	    	if (cmpVal != 0) {
	    		if (this.isAscending[i]) {
	    			return cmpVal;
	    		} else {
	    			return -cmpVal;
	    		}
	    	}
	    }
	    
	    // We finished the list of fields without finding a difference, so
	    // a and b are considered equivalent for the purposes of sorting.
	    
	    return 0;	    
	}
}
