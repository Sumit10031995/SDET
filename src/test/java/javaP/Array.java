package javaP;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

public class Array {

	public static void main(String[] args) {
		System.out.println(readObject("sumit"));

	}
	
	public static File getFileLocation(File src,File target) {
		if(src.isDirectory()) {
			File[] dirs=src.listFiles();
			for(File folder:dirs) {
				if(folder.isDirectory()) {
					File expFile=getFileLocation(folder,target) ;
					if(expFile!=null)
						return expFile;
				}else {
					if(folder.getName().equals(target.toString()))
						return folder;
				}

				
			}
		}
		return null;
	}
	
	
	public static File getFolderLocation(File src,File target) {
		if(src.isDirectory()) {
			File[] dirs=src.listFiles();
			for(File folder:dirs) {
				if(folder.isDirectory()) {
					if(folder.getName().equals(target.toString()))
						return folder;
				}else {
					File expFile=getFileLocation(folder,target) ;
					if(expFile!=null)
						return expFile;
				}

				
			}
		}
		return null;
	}
	
	public static List<List<String>> getCSVDetails() {
		List<List<String>> listOfData = new ArrayList();
		try (CSVReader reader = new CSVReader(new FileReader(FileManager.searchFile("userDetails.csv")))) {
			List<String[]> data = reader.readAll();
			for (String[] row : data) {
				List<String> details = new ArrayList();
				for (String cell : row) {
					details.add(cell);
				}
				listOfData.add(details);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listOfData;
	}
	
	public static Map readObject(String name) {
		Map obj = new HashMap();
		List<List<String>> data = getCSVDetails();
		int index = 0;
		for (int i = 0; i < data.get(0).size(); i++) {
			if (data.get(0).get(i).equalsIgnoreCase("Name")) {
				index = i;
				break;
			}

		}
		for (int i = 1; i < data.size(); i++) {
			if (data.get(i).get(index).equals(name)) {
				for (int j = 0; j < data.get(i).size(); j++) {
					obj.put(data.get(0).get(j), data.get(i).get(j));
				}
			}
		}

		return obj;
	}
}
