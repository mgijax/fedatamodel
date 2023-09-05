package org.jax.mgi.fe.datamodel.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FootnoteLetterTest {

	@Test
	public void test() {
		assertEquals("a",DatamodelUtils.getCharacterForFootnoteList(0));
		assertEquals("b",DatamodelUtils.getCharacterForFootnoteList(1));
		assertEquals("e",DatamodelUtils.getCharacterForFootnoteList(4));
		assertEquals("z",DatamodelUtils.getCharacterForFootnoteList(25));
		assertEquals("A",DatamodelUtils.getCharacterForFootnoteList(26));
		assertEquals("B",DatamodelUtils.getCharacterForFootnoteList(27));
		assertEquals("Z",DatamodelUtils.getCharacterForFootnoteList(51));
		//System.out.println(DatamodelUtils.getCharacterForFootnoteList(52));
		assertEquals("aa",DatamodelUtils.getCharacterForFootnoteList(52));
		assertEquals("bb",DatamodelUtils.getCharacterForFootnoteList(53));
		assertEquals("AA",DatamodelUtils.getCharacterForFootnoteList(78));
		assertEquals("aaa",DatamodelUtils.getCharacterForFootnoteList(104));
	}

}
