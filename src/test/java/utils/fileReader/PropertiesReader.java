package utils.fileReader;

import java.io.FileReader;
import java.util.Properties;
import utils.utility.Utility;

public class PropertiesReader {
	private static final String filePath=Utility.searchFile("config.properties").getAbsolutePath();
	
	public static String getPropertyDetails(String key) {
		Properties prop=new Properties();
		try {
			prop.load(new FileReader(filePath));
			return prop.getProperty(key).toString();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}