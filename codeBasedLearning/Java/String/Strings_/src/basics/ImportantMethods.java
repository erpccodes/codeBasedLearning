package basics;

import java.util.Arrays;
import java.util.List;

public class ImportantMethods {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Given below String
		
		List<String> phrases = Arrays.asList(
			    "  the quick brown fox  ",
			    " jumps over ",
			    " the lazy dog ",
			    "  in the park "
			);
		// Now code the below tasks
// ----------------------------------------------------------------------------------------------------------		
// 1.Combine all the phrases into a single string, separating each phrase with a single space.
		String str="";
		for(int i=0;i<phrases.size();i++) {
			str=str.concat(phrases.get(i).trim())+" ";
			//System.out.println(str);
		}
		System.out.println("Using String concat: "+str.trim());
	
	//  Using StringBuilder it reduces the overhead of creating new String objects with each concatenation.
	StringBuilder str2 = new StringBuilder();
	for(int i = 0; i < phrases.size(); i++) {
	    if (i > 0) {
	        str2.append(" "); // Add space before the next phrase, except before the first phrase
	    }
	    str2.append(phrases.get(i).trim());
	}
	System.out.println("Using StringBuilder append: "+str2.toString());
	
	System.out.println("-------------------------------------------------------------");
	
// -----------------------------------------------------------------------------------------------------
	
// 2.Extract the first word from the string (before the first space).
	
	String str3=phrases.get(0).trim();
	
	System.out.println(str3.substring(0, str3.indexOf(" ")));
	
	
	//Extract the last word from the string (after the last space). 
	
	String str4=phrases.get(phrases.size()-1).trim();
	
	System.out.println(str4.substring(str4.lastIndexOf(" ")+1,str4.length()));
	
	System.out.println("Substring function start index is inclusive and end index is exclusive");
	
	System.out.println("-------------------------------------------------------------");
	
// ----------------------------------------------------------------------------------------------------
	
//3.  Replace all occurrences of the first character in the string with an asterisk '*'
	
	String str5= phrases.toString().trim();
	//str5=str5.replace(str.charAt(0),'*');
	System.out.println(str5.replace(str.charAt(0),'*'));
	System.out.println("-------------------------------------------------------------------");
	
// ------------------------------------------------------------------------------------------------------
	String str6="Hellow";
	System.out.println(str6+'!');
	

	}
}
