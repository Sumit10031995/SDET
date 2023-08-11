package javaP;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import com.google.gson.Gson;
import com.opencsv.CSVReader;

import api.responseDTO.GetAPIResponseDTO;
import api.responseDTO.GetAPIResponseDTO.Employee;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.fileReader.FileManager;
import utils.fileReader.HostFileReader;
import utils.fileReader.PropertiesReader;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
public class Array {
	   public static void main(String[] args) {
	        String[] values = {"Hello!123", "abcxyz@", "12345", "Special!", "alpha_beta", "123_456", "Hello World!"};

	        String pattern = "^(?=.*[a-zA-Z])(?=.*[-!@#$%^&*()_+{}\\[\\]:;<>,.?~\\/\\s])[a-zA-Z\\d!@#$%^&*()_+{}\\[\\]:;<>,.?~\\/\\s]+$";
	        Pattern regex = Pattern.compile(pattern);

	        for (String value : values) {
	            Matcher matcher = regex.matcher(value);
	            if (matcher.matches()) {
	                System.out.println(value + " truye");
	            } else {
	                System.out.println(value + " false");
	            }
	        }
	    }     

public static boolean isValidMobileNumber(String mobileNumber) {
	Pattern ptn = Pattern.compile("[0-9]{10}");
	return ptn.matcher(mobileNumber).find();
}

public static boolean isValidEmailId(String email) {
	Pattern ptn = Pattern.compile("^[a-zA-Z0-9_.]+@[a-zA-Z]+(?:\\.[a-zA-Z]+)*$");
	return ptn.matcher(email).find();
}

public static boolean isInteger(String integer) {
	Pattern ptn = Pattern.compile("^-?\\d+$");
	return ptn.matcher(String.valueOf(integer)).find();
}

public static boolean isNumber(String number) {
	Pattern ptn = Pattern.compile("^-?\\d+(\\.\\d*)?$");
	return ptn.matcher(String.valueOf(number)).find();
}

public static boolean isDouble(String doubleNumber) {
	Pattern ptn = Pattern.compile("^-?\\d+\\.\\d+$");
	return ptn.matcher(String.valueOf(doubleNumber)).find();
}

public static boolean isNonZeroInteger(String integer) {
	Pattern ptn = Pattern.compile("^[1-9]\\d*$");
	return ptn.matcher(String.valueOf(integer)).find();
}

public static boolean isNonZeroNumber(String doubleNumber) {
	Pattern ptn = Pattern.compile("^[+]?\\d+(\\.\\d+)?$");
	return ptn.matcher(String.valueOf(doubleNumber)).find();
}

public static boolean isNonZeroDoubleNumber(String doubleNumber) {
	Pattern ptn = Pattern.compile("^(?!0\\.0$)(?!\\d+$)\\d*\\.\\d+$");
	return ptn.matcher(String.valueOf(doubleNumber)).find();
}

public static boolean isContainsSpecialCharacter(String doubleNumber) {
	Pattern ptn = Pattern.compile("[!\"#$%&'()*+,-./:;<=>?@[\\\\]^_`{|}~\\s]");
	return ptn.matcher(String.valueOf(doubleNumber)).find();
}

public static boolean isAlphabet(String charSeq) {
	Pattern ptn = Pattern.compile("^[a-zA-Z]+$");
	return ptn.matcher(String.valueOf(charSeq)).find();
}

public static boolean isAlphaNumeric(String charSeq) {
	Pattern ptn = Pattern.compile("^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]+$");
	return ptn.matcher(String.valueOf(charSeq)).find();
}

public static boolean isAlphaSymbolic(String charSeq) {
	Pattern ptn = Pattern.compile("^(?=.*[a-zA-Z])(?=.*[!@#$%^&*()_+{}\\[\\]:;<>,.?~\\/-\\s])[a-zA-Z!@#$%^&*()_+{}\\[\\]:;<>,.?~\\/-\\s]+$");
	return ptn.matcher(String.valueOf(charSeq)).find();
}


}
