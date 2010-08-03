package mgi.frontend.datamodel;

import javax.persistence.*;

@MappedSuperclass
public class Source implements SortableObject {
	
	// subclasses will need to add their particular objects and getter/setter methods
	protected int uniqueKey;
	protected String strain;
	protected String tissue;
	protected String age;
    protected String sex;
    protected String cellline;
	
	public Source() {}

	@Id
	public int getUniqueKey() {
		return uniqueKey;
	}

	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	public String getStrain() {
        return strain;
    }

    public void setStrain(String strain) {
        this.strain = strain;
    }

    public String getTissue() {
        return tissue;
    }

    public void setTissue(String tissue) {
        this.tissue = tissue;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCellLine() {
        return cellline;
    }

    public void setCellLine(String celline) {
        this.cellline = celline;
    }

    @Override
	public Comparable getComparableValue(String fieldname) throws NoSuchFieldException {
		Comparable out;
		return null;
	}
	/*
	@Override
	public String toString() {
		return "Note [" + (note != null ? "note=" + note + ", " : "")
				+ (noteType != null ? "noteType=" + noteType + ", " : "")
				+ "uniqueKey=" + uniqueKey + "]";
	}*/
	}
