package org.jax.mgi.fe.datamodel;

import java.util.ArrayList;
import java.util.List;

import org.jax.mgi.fe.datamodel.Annotation;
import org.jax.mgi.fe.datamodel.AnnotationProperty;
import org.junit.Assert;
import org.junit.Test;

public class AnnotationTest {

	/*
	 * Test annotation extension text output
	 */
	@Test
	public void testAnnotationExtensionTextOuputBlankCase() {
		
		// Initialize the test
		Annotation annot = new Annotation();
		
		// no extension properties
		List<AnnotationProperty> props = new ArrayList<AnnotationProperty>();
		annot.setPropertyList(props);
		
		String output = annot.getAnnotationExtensionTextOutput();
		
		Assert.assertEquals("", output);
	}
	
	@Test
	public void testAnnotationExtensionTextOuputOneStanza() {
		
		// Initialize the test
		Annotation annot = new Annotation();
		
		// add some extension properties
		List<AnnotationProperty> props = new ArrayList<AnnotationProperty>();
		props.add(mockAnnotationExtension("happens in","brain",1,1));
		props.add(mockAnnotationExtension("happens in","liver",1,2));
		props.add(mockAnnotationExtension("happens in","tail",1,3));
		
		annot.setPropertyList(props);
		
		String output = annot.getAnnotationExtensionTextOutput();
		
		Assert.assertEquals("happens in brain,happens in liver,happens in tail", output);
	}
	
	@Test
	public void testAnnotationExtensionTextOuputManyStanzas() {
		
		// Initialize the test
		Annotation annot = new Annotation();
		
		// add some extension properties
		List<AnnotationProperty> props = new ArrayList<AnnotationProperty>();
		props.add(mockAnnotationExtension("happens in","brain",1,1));
		props.add(mockAnnotationExtension("regulates","liver",1,2));
		
		props.add(mockAnnotationExtension("happens in","cardiovascular",2,1));
		props.add(mockAnnotationExtension("regulates","heart",2,2));
		
		props.add(mockAnnotationExtension("occurs at","tail",3,1));
		
		annot.setPropertyList(props);
		
		String output = annot.getAnnotationExtensionTextOutput();
		
		Assert.assertEquals("happens in brain,regulates liver|happens in cardiovascular,regulates heart|occurs at tail", output);
	}
	
	
	
	/*
	 * helpers
	 */
	private AnnotationProperty mockAnnotationExtension(String term, String value, int stanza, int sequencenum) {
		AnnotationProperty property = new AnnotationProperty();
		property.setProperty(term);
		property.setValue(value);
		property.setStanza(stanza);
		property.setType("Annotation Extension");
		property.setSequenceNum(sequencenum);
		
		return property;
	}

}
