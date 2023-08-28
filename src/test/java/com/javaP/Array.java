package com.javaP;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opencsv.CSVReader;

import com.api.request.DoPostRequest;
import com.api.requestDTO.EmpoyeeDetailsDTO;
import com.api.requestDTO.Testtttt;
import com.api.responseDTO.GetAPIResponseDTO;
import com.api.responseDTO.GetAPIResponseDTO.Employee;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import groovyjarjarantlr4.v4.parse.ANTLRParser.sync_return;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import com.utils.fileReader.CustomAdapter;
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

	    public static int maxSubarraySum(int[] nums) {
	        int maxEndingHere = nums[0];
	        int maxSoFar = nums[0];
	        
	        for (int i = 1; i < nums.length; i++) {
	            maxEndingHere = maxEndingHere + nums[i];
	            System.out.println(nums[i]+  "          "+maxEndingHere);
	            if (nums[i] > maxEndingHere) {
	                maxEndingHere = nums[i];
	            }
	            if (maxEndingHere > maxSoFar) {
	                maxSoFar = maxEndingHere;
	                System.out.println("----------------------");

	            }
	        }

	    
	        return maxSoFar;
	    }
	    
	    public static void main(String[] args) {
	    	int [] a= {-2, 1,6, -3,8,-1,8,2, 4, -1, 2, 1, -5, 4};
	    	int max=a[0];
	    	int total=0;
	    	for(int i = 1; i < a.length; i++)  {
	    		 total=a[i]+total;
	    		if(total<max) {
	    			max=total;
	    		}
	    	}
	    	System.out.println(max);
	    }
	    
	    public static int max(int a, int b) {
	    	if(a<b) {
	    		return b;
	    	}else return a;
	    }
	}



