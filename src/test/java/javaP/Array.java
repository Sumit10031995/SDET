package javaP;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Array {

	public static void main(String[] args) {
		JSONParser jsonParser = new JSONParser();
		try(FileReader file=new FileReader("./src/JSON/PostRequestRequestBody.json")){
			JSONObject obj=(JSONObject) jsonParser.parse(file);
			System.out.println(obj.get("postRequestBody1"));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
