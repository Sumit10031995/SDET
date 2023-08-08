package utils.utility;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import utils.fileReader.FileManager;
import utils.fileReader.PropertiesReader;

public class AutomationStrectureCreation {

	static AutomationStrectureConfig config = new AutomationStrectureConfig("APIServiceQA");

	
	public static void main(String[] args) {
		/**
		 * NOTE:-curl --location 'http[url]' --request POST --header 'key:value' --data
		 * {}
		 **/
		String curl = "curl --location  'http://catalog-service-ink10art.dev.dunzo.com/api/v2/product-grouping-details' \\\n"
				+ "--request POST \n" + "--header 'Content-Type: file' \\\n"
				+ "--data '{\"dzid\":\"f7c37860-8024-4298-8c62-74f8c066b6b6\",\"show_age_restricted\":true,\"filter_oos\":false,\"product_grouping_ids\":[\"64c21727e024c2683c0626fa\"]}'";
		String responseDto = "{\n" + "  \"categories\": [\"Qa-cat1\"],\n" + "  \"show_age_restricted\": true\n" + "}";
		createAPIRequestStrecture(curl, responseDto);
	}

	public static void createAPIRequestStrecture(String curl, String responseDto) {
		try {
			Map<String, Object> curlDetails = getCurlDetails(curl);
			String body = doPOJOCreation(curlDetails.get("body").toString(), config.requestFileName);
			String response = doPOJOCreation(responseDto, config.responseFileName);

            //Request DTO
			File requestFolder = FileManager.searchFile(config.targetFolder, config.folderToCreateRequestDTO);
			File requestDTO = new File(requestFolder, config.requestFileName);
			String requestDetails = "package "
					+ Utility.captureStringAfterSpecificString(requestFolder, "java/").replace("/", ".") + ";\n" + body;
			FileManager.writeTextsToFile(requestDTO, requestDetails);
			
            //Response DTO
			File responseFolder = FileManager.searchFile(config.targetFolder, config.folderToCreateResponseDTO);
			File responseDTO = new File(responseFolder, config.responseFileName);
			String responseDetails = "package "
					+ Utility.captureStringAfterSpecificString(responseFolder, "java/").replace("/", ".") + ";\n"
					+ response;
			FileManager.writeTextsToFile(responseDTO, responseDetails);
			
            //Test
			File testFolder = FileManager.searchFile(config.targetFolder, config.apiTtests);
			File tests = new File(testFolder, config.testFileName);
			String testDetails = "package "
					+ Utility.captureStringAfterSpecificString(testFolder, "java/").replace("/", ".") +";\n"
					+"import "+Utility.captureStringAfterSpecificString(responseDTO, "java/").replace("/", ".") +";\n"
					+ "import java.util.HashMap;\n"
	                + "import org.testng.annotations.Test;\n"
	                + "import com.google.gson.Gson;\n"
	                + "import api.request.BaseAPI;\n"
	                + "import io.restassured.response.Response;\n"
	                + "\n"
	                + "public class "+config.testFileName.replace(".java", "")+" {\n"
	                + "\t@Test\n"
	                + "\tpublic void test() {\n"
	                + "\t\tGson gson = new Gson();\n"
	                + "\t\t"+config.responseFileName.replace(".java", "") +" "+ firstCharToLowerCase(config.responseFileName).replace(".java", "") + " =new "+config.responseFileName.replace(".java", "") +"();\n"
	                + "\t\tResponse response = BaseAPI.performPost(\"url\", \"header\", new HashMap());\n"
	                + "\t\tString formattedResponse = response.asString();\n"
	                + "\t\tif (response.statusCode() == 200) {\n"
	                + "\t\t\t"+firstCharToLowerCase(config.responseFileName).replace(".java", "")+" = gson.fromJson(formattedResponse, "+config.responseFileName+".class);\n"
	                + "\t\t}\n"
	                + "\t}\n"
	                + "}";
			

			FileManager.writeTextsToFile(tests, testDetails);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static Map<String, Object> getCurlDetails(String curlCommand) throws Exception {
		Map<String, Object> map = new HashMap();
//		if (!Utility.isValidCurlFormat(curlCommand)) {
//			throw new Exception(
//					"curl format should be =\"curl --location 'http[url]' --request POST --header 'key:value' --data {}");
//		}
		Pattern urlPattern = Pattern.compile("--location '(.*?)'");
		Pattern methodPattern = Pattern.compile("--request (\\w+)");
		Pattern headersPattern = Pattern.compile("--header '(.*?)'");
		Pattern dataPattern = Pattern.compile("--data '(.*?)'", Pattern.DOTALL);

		Matcher urlMatcher = urlPattern.matcher(curlCommand);
		Matcher methodMatcher = methodPattern.matcher(curlCommand);
		Matcher headersMatcher = headersPattern.matcher(curlCommand);
		Matcher dataMatcher = dataPattern.matcher(curlCommand);

		while (urlMatcher.find()) {
			map.put("url", urlMatcher.group(1));
		}

		while (methodMatcher.find()) {
			map.put("method", methodMatcher.group(1));
		}

		Map<String, Object> header = new HashMap();
		while (headersMatcher.find()) {
			String hdr = headersMatcher.group(1);
			String[] split = hdr.split(": ");
			header.put(split[0].trim(), split[1].trim());
		}
		map.put("header", header);

		while (dataMatcher.find()) {
			map.put("body", dataMatcher.group(1));
		}
		return map;
	}

	// https://www.codeusingjava.com/tools/jsontopojo
	private static String doPOJOCreation(String requestBody, String className) {
		Map expRequestBody = new HashMap();
		expRequestBody.put("jsonText", requestBody);
		ObjectMapper objectMapper = new ObjectMapper();
		String url = PropertiesReader.getPropertyDetails("api.convertTo.POJO");
		String cookieHeader = "_gid=GA1.2.485604522.1691280896; __ppIdCC=xoseuaibfhwcw_xon210.16898.7097; _pbjs_userid_consent_data=3524755945110770; sharedid=c4dc49be-34f6-4e41-bf7b-7de10916a618; JSESSIONID=94AFD0D1E26B4B20F3321AC3240863DE; _gat_gtag_UA_158277904_1=1; _ga_34Y2XZBFHV=GS1.1.1691396770.7.1.1691396770.0.0.0; _ga=GA1.1.743020840.1691280896; __viCookieActive=true; amp_6e403e=i-rkk1eOVw9lvcQey9y5t2...1h77hl44n.1h77hl44n.0.0.0; __gads=ID=2316400f668918d1-22b25fd2558000fd:T=1691280898:RT=1691396771:S=ALNI_MYkG92hBWm0Kmcdx8xhuuRAp074rg; __gpi=UID=00000c277847858f:T=1691280898:RT=1691396771:S=ALNI_MbyOPrKMxH3JkH1jts6bMtPZHhkew; cto_bundle=3oU13V9RU05ENWZvJTJGSXByaXgxYlZpcyUyQnRKTFN2bldqVXNoUUhUVCUyRk1pS25hSVo3Q1hWcDdvbXJmMFpCZFV1eWVkbUFsY0JTUkZzaXVuMmxVY01RVDMzNXZmTkk4bXM0R3EyeUVpa3JhbEwlMkI3d0lBbVBBJTJCTDRkTUElMkY0SmE1VFFNUzJ1cmtJUmFQM090Z1VUN1dKYlBmclJ0MW0zUFpZOWQ5T2VvSmRDeGNKUGtTd2VBRzZnazVIelYxOERZaUJ4dCUyQk41c0NhTnRoSTljOUc0V0dYTTJFeWZDYUElM0QlM0Q; cto_bidid=6vPHKV9zdmtnVVlXanhqQ1JLS0VhNTNNTVVZNWxwWlRRcVJ4dnJUT3RoY3RzT0hyZnloWDNjWUFLWk9qNHhFMHFuSGY0eEVaTXpDZndmZUN5SW9iRXoyQ3dJRmhaYjZuc3RwdzN2ZlZXRGxXSjY0MFNtRmVGdW42V3pocG45VmhKbzQlMkJWRnRDZjM0TzNKRjJFTmZ0Rjl3WHNIUSUzRCUzRA";
		try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
			HttpPost request = new HttpPost("https://www.codeusingjava.com/createpojo");
			request.addHeader("Content-Type", "application/json;charset=UTF-8");
			request.addHeader("Cookie", cookieHeader);
			request.addHeader("X-CSRF-TOKEN", "fc6c67ca-36de-4f56-8f82-4b0d222b7700");
			StringEntity entity = new StringEntity(
					objectMapper.writeValueAsString(expRequestBody));
			request.setEntity(entity);

			try (CloseableHttpResponse response = httpClient.execute(request)) {
				HttpEntity responseEntity = response.getEntity();
				String responseBody = EntityUtils.toString(responseEntity);
				int statusCode = response.getStatusLine().getStatusCode();
				if (statusCode == 200)
					return objectMapper.readTree(responseBody.replace("Root", className).replace("package codeusingjava;\\n", "")).get(0).asText();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String getRequestMethod(String httpMethod) {
		switch (httpMethod.toLowerCase()) {
		case "get": {
			return "String response=BaseAPI.performGet(url,header);";
		}
		case "post": {
			return "String response=BaseAPI.performPost(url, requestPayload, header);";
		}
		case "put": {
			return "String response= =BaseAPI.performPut(url, requestPayload, header);";
		}
		case "delete": {
			return "String response= BaseAPI.performDelete(url, requestPayload, header);";
		}
		}
		return httpMethod;
	}

	private static String firstCharToLowerCase(String str) {

		if (str == null || str.length() == 0)
			return "";

		if (str.length() == 1)
			return str.toLowerCase();

		return str.substring(0, 1).toLowerCase() + str.substring(1, str.length());
	}

}
