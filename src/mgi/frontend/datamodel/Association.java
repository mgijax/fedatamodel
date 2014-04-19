package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 * Base class for an association.
 * @author mhall
 * We have updated this class so it reflects the more current database schema.
 *
 * This class however isn't currently in use, so there could still be bugs in it.
 *
 */


@MappedSuperclass
public class Association  {

	protected String qualifier;
	protected Reference reference;
	// subclasses will need to add their particular objects and getter/setter methods
	protected int uniqueKey;

	public String getQualifier() {
		return qualifier;
	}


	/**
	 * Return the reference for this association.  All objects with this type
	 * will have at least 1 reference associated with them.
	 * @return
	 */
	@ManyToOne (targetEntity=Reference.class, fetch=FetchType.LAZY)
	@JoinColumn (name="reference_key")
	public Reference getReference() {
		return reference;
	}

	@Id
	@Column(name="unique_key")
	public int getUniqueKey() {
		return uniqueKey;
	}

	public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}

	public void setReference(Reference reference) {
		this.reference = reference;
	}

	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	@Override
	public String toString() {
		return "Association ["
				+ (qualifier != null ? "qualifier=" + qualifier + ", " : "")
				+ ", uniqueKey=" + uniqueKey + "]";
	}
}
