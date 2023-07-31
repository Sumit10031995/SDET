package utils.fileReader;

import java.io.File;

public class HostFileReader extends JSONFileReader {
	private static final String hostFilePath = "." + File.separator + "src" + File.separator + "JSON" + File.separator
			+ "Hosts.json";
	private static final String filePathProperties = "." + File.separator + "src" + File.separator + "property" + File.separator
			+ "api.properties";
	
	public String getDataSetPath() {
		return hostFilePath;
	}

	public static String getENVtDetails(String key) {
		PropertiesReader reader = new PropertiesReader();
		String env = reader.getPropertyDetails(filePathProperties, "envName");
		String cls = reader.getPropertyDetails(filePathProperties, "cluster");
		return key.replace("${env:env_name}", env).replace("${cluster:cls_name}", cls);
	}

	public Name hosts() {
		return getValue(Name.class);
	}
    
	public static class Name {
		private String baseURL;

		public String getName() {
			this.baseURL = getENVtDetails(baseURL);
			return baseURL;
		}
	}

}
