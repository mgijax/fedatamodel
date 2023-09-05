package org.jax.mgi.fe.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="glossary_term")
public class GlossaryTerm {
	
	private int uniqueKey;
	private String glossaryKey;
	private String displayName;
	private String definition;
	
	/* Getters */
	@Id
	@Column(name="unique_key")
	public int getUniqueKey() {
		return uniqueKey;
	}
	@Column(name="glossary_key")
	public String getGlossaryKey() {
		return glossaryKey;
	}
	@Column(name="display_name")
	public String getDisplayName() {
		return displayName;
	}
	@Column(name="definition")
	public String getDefinition() {
		return definition;
	}
	
	/* Setters */
	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}
	public void setGlossaryKey(String glossaryKey) {
		this.glossaryKey = glossaryKey;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public void setDefinition(String definition) {
		this.definition = definition;
	}
	
	/* Transient methods */
	@Transient
	public String getFirstLetter()
	{
		char fl = displayName.charAt(0);
		if(Character.isLetter(fl))
		{
			return Character.toString(Character.toUpperCase(fl));
		}
		return "";
	}
}
