package javaP;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class StrTest 
{
	public static void main(String[] args) {
		List li=Arrays.asList("a","b","c","d");
		List<String> lii=new ArrayList(li);
		String temp=lii.get(0);
		lii.indexOf(temp);
		lii.set(0,"d");
		System.out.println(lii);
	}
}
	
