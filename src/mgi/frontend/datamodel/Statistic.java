package mgi.frontend.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="statistic")
public class Statistic {
	
	private int uniqueKey;
	private int statisticKey;
	private String name;
	private String abbreviation;
	private int value;
	private int sequenceNum;
	private String groupName;
	private int groupSequenceNum;
	
	@Id
	@Column(name="unique_key")
	public int getUniqueKey() {
		return uniqueKey;
	}	
	
	@Column(name="statistic_key")
	public int getStatisticKey() {
		return statisticKey;
	}

	@Column(name="name")
	public String getName() {
		return name;
	}

	@Column(name="abbreviation")
	public String getAbbreviation() {
		return abbreviation;
	}

	@Column(name="value")
	public int getValue() {
		return value;
	}

	@Column(name="sequencenum")
	public int getSequenceNum() {
		return sequenceNum;
	}

	@Column(name="group_name")
	public String getGroupName() {
		return groupName;
	}

	@Column(name="group_sequencenum")
	public int getGroupSequenceNum() {
		return groupSequenceNum;
	}

	
	/* Setters */
	public void setStatisticKey(int statisticKey) {
		this.statisticKey = statisticKey;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public void setSequenceNum(int sequenceNum) {
		this.sequenceNum = sequenceNum;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public void setGroupSequenceNum(int groupSequenceNum) {
		this.groupSequenceNum = groupSequenceNum;
	}
	public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}
}
