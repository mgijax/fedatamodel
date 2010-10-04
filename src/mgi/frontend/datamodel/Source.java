package mgi.frontend.datamodel;

import javax.persistence.*;

/**
 * Source
 * @author mhall
 * This base object is used for a generic source, currently its 
 * only being used by sequences.
 */
@MappedSuperclass
public class Source {
	
	protected String age;
	protected String cellline;
	protected String sex;
	protected String strain;
    protected String tissue;
	protected int uniqueKey;
	
    // ================= Getters and Setters ===================== //
	
	public String getAge() {
        return age;
    }

	@Column(name="cell_line")
    public String getCellLine() {
        return cellline;
    }

	public String getSex() {
        return sex;
    }

    public String getStrain() {
        return strain;
    }

    public String getTissue() {
        return tissue;
    }

    @Id
	@Column(name="unique_key")
	public int getUniqueKey() {
		return uniqueKey;
	}

    public void setAge(String age) {
        this.age = age;
    }

    public void setCellLine(String celline) {
        this.cellline = celline;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setStrain(String strain) {
        this.strain = strain;
    }

    public void setTissue(String tissue) {
        this.tissue = tissue;
    }

    public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

}
