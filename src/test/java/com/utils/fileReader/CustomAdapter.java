package com.utils.fileReader;

import com.google.gson.*;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Arrays;

import com.google.gson.*;
import java.lang.reflect.Type;
public class CustomAdapter {
	 public static void main(String[] args) {
	        String bytes = "{\"name\":\"John\",\"age\":\"ghtr\",\"price\":\"30.67\"}";
	        

	        Gson gson = new Gson();
	        Person person = gson.fromJson(new String(bytes), Person.class);
System.out.println(gson.toJson(bytes));
	        System.out.println("Deserialized Person: " + person.getName() + ", " + (person.getAge()+1) +","+person.getPrice());
	    }


	
}

class Person {
    private String name;
    private int age;
    private double price;
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
    

}