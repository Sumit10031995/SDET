package utils.propertiesReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
	public synchronized String getPropertyDetails(String filePath,String key) {
		Properties prop=new Properties();
		try {
			prop.load(new FileReader(filePath));
			return prop.getProperty(key).toString();
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
