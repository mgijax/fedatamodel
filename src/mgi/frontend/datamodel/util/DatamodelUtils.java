package mgi.frontend.datamodel.util;

/*
 * Static helper methods for use in datamodel transient methods
 * 
 */
public class DatamodelUtils {
	/*
	 * Returns the appropriate letter(s) associated with a footnote 
	 * in a list of footnotes. I.e. 0:a, 1:b, 2:c ... 26:A,27:B, ... 52:aa,53:bb, etc
	 * NOTE: this is zero based, so just treat it as if you are pulling these values out of an array
	 */
	public static String getCharacterForFootnoteList(int index)
	{
		int lowerAsciiBase = 97;
		int upperAsciiBase = 65;
		// calculate the number of times past 52 (i.e. one round of all lower+upper characters)
		int duplicates = (int) Math.floor((index)/52);
		// get relative letter (a,b,c,d,...)
		int modIndex = index%52;
		// is it lower or upper?
		Character footnoteChar = (char) (lowerAsciiBase+modIndex);
		if(modIndex > 25) footnoteChar = (char) (upperAsciiBase+(modIndex-26));
		// duplicate the character if needed (as many times as needed)
		String footnoteLetter="";
		for(int i=0;i<=duplicates;i++)
			footnoteLetter += footnoteChar.toString();
		return footnoteLetter;
	}
	
	// Create a safe string for use in a css class or id based on the given input string
	public static String makeCssSafe(String input)
	{
		if (input == null) return "";
		String cssSafe = new String(input);
		// replace any non-alphanumeric character with underscore
		cssSafe = cssSafe.replaceAll("[^a-zA-Z0-9]+", "_");
        return cssSafe;
	}
}
