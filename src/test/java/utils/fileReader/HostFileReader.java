package utils.fileReader;
import utils.utility.Utility;

public class HostFileReader extends JSONFileReader {
	private static final String hostFilePath =FileManager.searchFile("Hosts.json").getAbsolutePath();
	
	public String getDataSetPath() {
		return hostFilePath;
	}

	public static String getENVtDetails(String key) {
		PropertiesReader reader = new PropertiesReader();
		String env = reader.getPropertyDetails("envName");
		String cls = reader.getPropertyDetails("cluster");
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
