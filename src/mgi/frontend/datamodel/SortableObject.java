package mgi.frontend.datamodel;

/** provides a single method which can be implemented by any object we
 * 		want to be sortable by the universal SortableObjectComparator.
 * 		The universal ResultSet can handle sorting and pagination for
 * 		Lists of these SortableObjects.
 * @author jsb
 */
public interface SortableObject {
	/** single method used to look up field values by name; implementing 
	 * 		object can return values within itself or within any objects 
	 * 		it contains.  If it knows nothing about the requested field, 
	 * 		it should throw NoSuchFieldException.
	 * @param name of the field whose value we are requesting.  Note that 
	 * 		this is a String, not an object's property.  The object can map
	 * 		this String	to any value it wants to, as long as it returns a 
	 * 		Comparable object.
	 */
	public Comparable getComparableValue (String fieldname) 
		throws NoSuchFieldException;
}
