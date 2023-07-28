package utils.fileReader;
import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;

public class JSONFileReader {

	public String getDataSetPath() {
		return "";
	}

	public <T> T getValue(final String key, final Class<T> tClass) {
		T obj = null;
		String currKey = key;
		String file = getDataSetPath();
		obj = getValue(currKey, file, tClass);
		return obj;
	}

	private <T> T getValue(String key, String file, Class<T> tClass) {
		JSONParser jsonParser = new JSONParser();
		try (FileReader reader = new FileReader(file)) {
			JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
			T t=new Gson().fromJson(jsonObject.get(key).toString(), tClass);
			return t;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
