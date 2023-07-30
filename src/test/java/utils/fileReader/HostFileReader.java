package utils.fileReader;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;

public class HostFileReader {
	public static final String filePath = "." + File.separator + "src" + File.separator + "JSON" + File.separator
			+ "Hosts.json";

	public static Data hsotDetails() {
		Gson gson = new Gson();
		try (Reader reader = new FileReader(filePath)) {
			return gson.fromJson(reader, Data.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static class Data {
		private String name;

		public String getName() {
			this.name = getENVtDetails(name);
			return name;
		}

	}

	public static String getENVtDetails(String key) {
		String filePath = "." + File.separator + "src" + File.separator + "property" + File.separator
				+ "api.properties";
		PropertiesReader reader = new PropertiesReader();
		String env = reader.getPropertyDetails(filePath, "envName");
		String cls = reader.getPropertyDetails(filePath, "cluster");
		Data data = HostFileReader.hsotDetails();
		return key.replace("${env:env_name}", env).replace("${cluster:cls_name}", cls);
	}
}
