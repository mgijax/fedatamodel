package mgi.frontend.datamodel;

import javax.persistence.*;

@MappedSuperclass
public class Association implements SortableObject {

	public static String QUALIFIER = "Association.Qualifier";
	public static String JNUM = "Association.Jnum";
	
	// subclasses will need to add their particular objects and getter/setter methods
	protected int uniqueKey;
	protected String qualifier;
	protected int sequenceNumFwd;
	protected int sequenceNumRev;
	protected Reference reference;
	
	public Association() {}

	@Id
	public int getUniqueKey() {
		return uniqueKey;
	}

	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	public String getQualifier() {
		return qualifier;
	}

	public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}

	public int getSequenceNumFwd() {
		return sequenceNumFwd;
	}

	public void setSequenceNumFwd(int sequenceNumFwd) {
		this.sequenceNumFwd = sequenceNumFwd;
	}

	public int getSequenceNumRev() {
		return sequenceNumRev;
	}

	public void setSequenceNumRev(int sequenceNumRev) {
		this.sequenceNumRev = sequenceNumRev;
	}

	@ManyToOne (targetEntity=Reference.class, fetch=FetchType.LAZY)
	@JoinColumn (name="referenceKey")
	public Reference getReference() {
		return reference;
	}

	public void setReference(Reference reference) {
		this.reference = reference;
	}

	@Override
	public Comparable getComparableValue(String fieldname) throws NoSuchFieldException {
		Comparable out;
		if (fieldname.equals (QUALIFIER)) {
			out = this.getQualifier();
		} else if (fieldname.equals (JNUM)) {
			out = this.reference.getComparableValue(Reference.JNUM);
		} else {
			throw new NoSuchFieldException ("Unknown field: " + fieldname);
		}
		return out;
	}

	@Override
	public String toString() {
		return "Association ["
				+ (qualifier != null ? "qualifier=" + qualifier + ", " : "")
				+ "sequenceNumFwd="
				+ sequenceNumFwd + ", sequenceNumRev=" + sequenceNumRev
				+ ", uniqueKey=" + uniqueKey + "]";
	}
}
