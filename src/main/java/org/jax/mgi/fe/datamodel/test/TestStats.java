package org.jax.mgi.fe.datamodel.test;

/**
 * This entity represents a row in the test_stats table.
 * Used to store dynamicly generated test data.
 * Can be reference in concordion by using cc:dynamicData="testId"
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="test_stats")
public class TestStats {
	private Integer testStatsKey;
	private String id;
	private String testData;

	/* Getters */
	@Id
    @Column(name="test_stats_key")
	public Integer getTestStatsKey() {
		return testStatsKey;
	}
	@Column(name="id")
	public String getId() {
		return id;
	}
	@Column(name="test_data")
	public String getTestData() {
		return testData;
	}

	/* Setters */
	public void setTestStatsKey(Integer testStatsKey) {
		this.testStatsKey = testStatsKey;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setTestData(String testData) {
		this.testData = testData;
	}
}
