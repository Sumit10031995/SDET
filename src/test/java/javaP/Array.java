package javaP;

import java.io.FileReader;
import java.util.Arrays;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Array {

	public static String StringChallenge(String str1, String str2) {
	    char[] charArray1 = str1.toCharArray();
	    char[] charArray2 = str2.toCharArray();

	    Arrays.sort(charArray1);
	    Arrays.sort(charArray2);

	    int i = 0;
	    int j = 0;

	    while (i < charArray1.length && j < charArray2.length) {
	        if (charArray1[i] == charArray2[j]) {
	            i++;
	            j++;
	        } else {
	            i++;
	        }
	    }

	    return j == charArray2.length ? "true" : "false";
	}

	public static void main(String[] args) {
	    System.out.println(StringChallenge("rkqodlw", "world")); // Output: true
	    System.out.println(StringChallenge("cdore", "coder"));   // Output: true
	    System.out.println(StringChallenge("h3llko", "hello"));  // Output: false
	}
}
