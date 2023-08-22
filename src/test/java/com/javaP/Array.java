package com.javaP;
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

import com.api.request.DoPostRequest;
import com.api.requestDTO.EmpoyeeDetailsDTO;
import com.api.responseDTO.GetAPIResponseDTO;
import com.api.responseDTO.GetAPIResponseDTO.Employee;
import groovyjarjarantlr4.v4.parse.ANTLRParser.sync_return;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import com.utils.fileReader.FileManager;
import com.utils.fileReader.HostFileReader;
import com.utils.fileReader.PropertiesReader;

import java.io.File;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
public class Array {
	   private static final Logger logger = LogManager.getLogger(Array.class);

	    public static void main(String [] args) {
	    	Comparator<EmpoyeeDetailsDTO> ageComparator =new EmpoyeeDetailsDTO();
	    	ArrayList li=new ArrayList();
	    	li.add(new EmpoyeeDetailsDTO(3,"sumit",30));
	        li.add(new EmpoyeeDetailsDTO(1,"shuvashree",10));
	        li.add(new EmpoyeeDetailsDTO(60,"smita",600));
	        li.add(new EmpoyeeDetailsDTO(2,"trisha",600));

	        Collections.sort(li,ageComparator);
	        logger.info(li);

	    }
  
}





