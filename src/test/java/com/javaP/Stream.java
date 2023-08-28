package com.javaP;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import com.api.requestDTO.EmpoyeeDetailsDTO;

public class Stream {
//public static void main(String[] args) {
////	Comparator<EmpoyeeDetailsDTO> ageComparator =(i,j)-> i.getEmployeeSalary()-j.getEmployeeSalary();
////	ArrayList li=new ArrayList();
////	li.add(new EmpoyeeDetailsDTO(3,"sumit",30));
////    li.add(new EmpoyeeDetailsDTO(1,"shuvashree",10));
////    li.add(new EmpoyeeDetailsDTO(60,"smita",600));
////    li.add(new EmpoyeeDetailsDTO(2,"trisha",600));
////    Collections.sort(li,ageComparator);
////    System.out.println(li);
//	
//	String name="suummmmmmmmmitttttttttttttm";
//	name=name+"hari";
//	StringBuffer buf=new StringBuffer("sumit");
//	StringBuilder
//	buf.append("naik");
//	System.out.println(buf);
//	StringBuffer buff=new StringBuffer("sumit");
//	System.out.println(buff.hashCode());
//	System.out.println(buf.hashCode());
//
//
////	System.out.println(name);
//////	String newString="";
//////	StringBuffer buf=new StringBuffer();
//////	name.chars().distinct().forEach(o->buf.append((char)o));
//////	System.out.println(buf);
////	
////	Map<Character,Integer> mp=new HashMap();
////	
////	
////	for(int i=0;i<name.length();i++) {
////		char c=name.charAt(i);
////		
////		if((mp.containsKey(c))) {
////			mp.put(c, (mp.get(c)+1));
////		}else {
////			mp.put(c, 1);
////		}
////		int e=name.indexOf(c, i+1);
////		if(e==-1)
////			buf.append(c);
////	}
////	System.out.println(buf);
////	System.out.println(mp);
//
//
//	
//}
	
	public enum Field {
	    NAME("name"),
	    ID("id"),
	    SALARY("salary"),
	    VARIANT_NAME("variant_name(as_shown_on_user_app)");

	    private final String value;

	    Field(String value) {
	        this.value = value;
	    }

	    public String getValue() {
	        return value;
	    }
	}

	    public static void main(String[] args) {
	        Field field = Field.VARIANT_NAME;
	        System.out.println("Field: " + Field.NAME.getValue());
	    }
	    
}
