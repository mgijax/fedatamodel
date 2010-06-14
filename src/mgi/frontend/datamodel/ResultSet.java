package mgi.frontend.datamodel;

import java.util.Collections;
import java.util.List;

/** Is: a wrapper over a List of SortableObjects, providing
 *    pagination, slicing by index, and sorting
 *  Has: a List of SortableObjects
 *  Does: can sort the list according to a caller-specified set of 
 *    input fields, can retrieve a slice of the list (from start
 *    index to stop index), and can retrieve a slice of the list
 *    (for a given page number and page size) 
 * @author jsb
 */
public class ResultSet {
	
	// the sortable List of SortableObjects
	private List<SortableObject> myItems = null; 
	
	// hide the default constructor
	private ResultSet() {}
	
	/** constructor; keeps a reference to the input 'items',  so
	 *    any sorts are applied to the original list (so there
	 *    are potential conflicts if the original list is
	 *    otherwise altered)
	 * @param items see comments above
	 */
	public ResultSet (List<SortableObject> items) {
		this.myItems = items;
		return;
	}
	
	/** sorts the list of SortableObjects according to the fields
	 *    named in 'fieldnames'
	 * @param fieldnames can specify any number of fields by which
	 *     to sort.  (first item is primary sort, second is 
	 *     secondary, third is tertiary, etc.)  The fieldnames should
	 *     be drawn from constants specified in the SortableObject
	 *     class whose instances were passed into the constructor.
	 * @throws NoSuchFieldException thrown if a specified fieldname
	 *     is unrecognized by at least one of the SortableObjects
	 *     passed into the constructor
	 */
	public void sortBy (List<String> fieldnames) throws NoSuchFieldException {
		this.sortBy(fieldnames.toArray(new String[0]));
		return;
	}
	
	/** see sortBy(List<String>) for comments
	 */
	public void sortBy (String[] fieldnames) throws NoSuchFieldException {
		// build the comparator using the specified fieldnames
		SortableObjectComparator comparator = 
			new SortableObjectComparator (fieldnames);
		
		// ensure that the specified fieldnames are valid for each of the items
		comparator.checkFields(this.myItems);
		
		// go ahead and do the sort
		Collections.sort(this.myItems, comparator);
		return;
	}
	
	/** determine how many items were in the list given to this ResultSet
	 * @return number of items in the original list
	 */
	public int size() {
		return this.myItems.size();
	}

	/** determine what the last page number would be for the given page size
	 * @param pageSize number of items that each page will hold
	 * @return number of pages (starting at 1)
	 */
	public int maxPage(int pageSize) {
		return 1 + (this.size() / pageSize); 
	}

	/** get the items for the n-th page (pageNumber) where each
	 *     page can hold pageSize items
	 * @param pageNumber which page to retrieve items for
	 * 	   (page numbers start at 1)
	 * @param pageSize how many items does each page hold?
	 * @return List of up to pageNumber SortableObjects (the last
	 *     page may return less than the requested number)
	 */
	public List<SortableObject> getPage (int pageNumber, int pageSize) {
		int startIndex = (pageNumber - 1) * pageSize;
		int stopIndex = pageNumber * pageSize;
		return this.subList(startIndex, stopIndex);
	}
	
	/** get the items in positions startIndex to stopIndex
	 * @param startIndex index of first item to retrieve
	 * @param stopIndex index after the last item to retrieve
	 * @return List of SortableObjects
	 */
	public List<SortableObject> subList (int startIndex, int stopIndex) {
		return this.myItems.subList(startIndex, stopIndex);
	}	
}
