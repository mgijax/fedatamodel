package org.jax.mgi.fe.datamodel;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class VocabTermTest {

	/*
	 * Sort order is
	 * edgeLabel then term alpha
	 */
	@Test
	public void testSortEmapParentEdges() {
		// setup test case
		VocabTerm t = term("test");
		
		VocabTerm p1 = term("mouse");
		VocabTerm p2 = term("conceptus");
		VocabTerm p3 = term("egg cylinder");
		
		addParentEdge(t, p1, "part-of");
		addParentEdge(t, p2, "part-of");
		addParentEdge(t, p3, "is-a");
	
		
		// verift edge order
		List<String> edgeStrings = new ArrayList<String>();
		for (VocabChild edge : t.getEmapParentEdges()) {
			edgeStrings.add(edge.getEdgeLabel() + " " + edge.getParent().getTerm());
		}
		
		Assert.assertEquals("is-a egg cylinder", edgeStrings.get(0));
		Assert.assertEquals("part-of conceptus", edgeStrings.get(1));
		Assert.assertEquals("part-of mouse", edgeStrings.get(2));
	}
	
	
	
	/*
	 * Helpers
	 */
	private VocabTerm term(String term) {
		VocabTerm t = new VocabTerm();
		t.setParentEdges(new ArrayList<VocabChild>());
		t.setTerm(term);
		return t;
	}
	
	private void addParentEdge(VocabTerm term, VocabTerm parent, String edgeLabel) {
		VocabChild vc = new VocabChild();
		vc.setParent(parent);
		vc.setEdgeLabel(edgeLabel);
		term.getParentEdges().add(vc);
	}

}
