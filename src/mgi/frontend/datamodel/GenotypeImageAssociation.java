package mgi.frontend.datamodel;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="genotype_to_image")
public class GenotypeImageAssociation 
{
	private int uniqueKey;
	private Image image;
	private Genotype genotype;
	private String qualifier;
	
	@Id
	@Column(name="unique_key")
	public int getUniqueKey() {
		return uniqueKey;
	}
	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}
	@ManyToOne (targetEntity=Image.class, fetch=FetchType.LAZY)
	@JoinColumn (name="image_key")
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	@ManyToOne (targetEntity=Genotype.class, fetch=FetchType.LAZY)
	@JoinColumn (name="genotype_key")
	public Genotype getGenotype() {
		return genotype;
	}
	public void setGenotype(Genotype genotype) {
		this.genotype = genotype;
	}
	@Column(name="qualifier")
	public String getQualifier() {
		return qualifier;
	}
	public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}

	
}
