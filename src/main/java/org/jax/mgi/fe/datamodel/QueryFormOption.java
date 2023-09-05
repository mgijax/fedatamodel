package org.jax.mgi.fe.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * One option for a field on a query form.
 * @author jsb
 */

@Entity
@Table(name="queryform_option")
public class QueryFormOption {

    private int optionKey;
    private String formName;
    private String fieldName;
    private String displayValue;
    private String submitValue;
    private int sequenceNum;
    private Integer indentLevel;
    private Integer objectCount;
    private String objectType;
    private String helpText;
    private int showExpanded;

    // ================= Getters and Setters ===================== //

    @Id
    @Column(name="option_key")
    public int getOptionKey() {
	return optionKey;
    }

    public void setOptionKey(int optionKey) {
	this.optionKey = optionKey;
    }

    @Column(name="sequence_num")
    public int getSequenceNum() {
	return sequenceNum;
    }

    public void setSequenceNum(int sequenceNum) {
	this.sequenceNum = sequenceNum;
    }

    @Column(name="show_expanded")
    public int getShowExpanded() {
	return showExpanded;
    }

    public void setShowExpanded(int showExpanded) {
	this.showExpanded = showExpanded;
    }

    @Column(name="indent_level")
    public Integer getIndentLevel() {
	return indentLevel;
    }

    public void setIndentLevel(Integer indentLevel) {
	this.indentLevel = indentLevel;
    }

    @Column(name="object_count")
    public Integer getObjectCount() {
	return objectCount;
    }

    public void setObjectCount(Integer objectCount) {
	this.objectCount = objectCount;
    }

    @Column(name="help_text")
    public String getHelpText() {
	return helpText;
    }

    public void setHelpText(String helpText) {
	this.helpText = helpText;
    }

    @Column(name="form_name")
    public String getFormName() {
	return formName;
    }

    public void setFormName(String formName) {
	this.formName = formName;
    }

    @Column(name="field_name")
    public String getFieldName() {
	return fieldName;
    }

    public void setFieldName(String fieldName) {
	this.fieldName = fieldName;
    }

    @Column(name="display_value")
    public String getDisplayValue() {
	return displayValue;
    }

    public void setDisplayValue(String displayValue) {
	this.displayValue = displayValue;
    }

    @Column(name="submit_value")
    public String getSubmitValue() {
	return submitValue;
    }

    public void setSubmitValue(String submitValue) {
	this.submitValue = submitValue;
    }

    @Column(name="object_type")
    public String getObjectType() {
	return objectType;
    }

    public void setObjectType(String objectType) {
	this.objectType = objectType;
    }
}
