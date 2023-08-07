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

			File requestFolder = Utility.createFolder(config.targetFolder, config.folderToCreateRequestDTO);
			File responseFolder = Utility.createFolder(config.targetFolder, config.folderToCreateResponseDTO);
			File testFolder = Utility.createFolder(config.targetFolder, config.apiTtests);

			File requestDTO = new File(requestFolder, config.requestFileName);
			String requestDetails = "package "
					+ Utility.captureStringAfterSpecificString(requestFolder, "java/").replace("/", ".") + ";\n" + body;
			Utility.writeTextsToFile(requestDTO, requestDetails);

			File responseDTO = new File(responseFolder, config.responseFileName);
			String responseDetails = "package "
					+ Utility.captureStringAfterSpecificString(responseFolder, "java/").replace("/", ".") + ";\n"
					+ response;
			Utility.writeTextsToFile(responseDTO, responseDetails);

			File tests = new File(testFolder, config.testFileName);
			String testDetails = "package "
					+ Utility.captureStringAfterSpecificString(testFolder, "java/").replace("/", ".") + ";\n"
					+ "import lombok.Builder;\n" + "import lombok.Data;\n" + "import lombok.Getter;\n" + "@Getter\n"
					+ "@Data\n" + "@Builder(toBuilder = true)\n" + "public class " + config.responseFileName + "{\n"
					+ config.responseFileName + " " + firstCharToLowerCase(config.responseFileName) + " =new "
					+ config.responseFileName + "();\n"
					+ getRequestMethod(getCurlDetails(curl).get("method").toString()) + ";\n"
					+ "		String formattedResponse = response.asString();\n"
					+ "		if (response.statusCode() == 200) {\n"
					+ "			firstCharToLowerCase(responseFileName) = gson.fromJson(formattedResponse, responseFileName.class);\n}";

			Utility.writeTextsToFile(tests, testDetails);

			System.out.println("Files Successfully Created");
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
		String cookieHeader = "_gid=GA1.2.485604522.1691280896; __ppIdCC=xoseuaibfhwcw_xon210.16898.7097; _pbjs_userid_consent_data=3524755945110770; sharedid=c4dc49be-34f6-4e41-bf7b-7de10916a618; cto_bundle=86XZ9V9RU05ENWZvJTJGSXByaXgxYlZpcyUyQnRKQjR3JTJCU0VLMUlWWTVQeUVUQSUyRlBmUGJyVUhscGdNWXdGdDBsVHk1VDlqUEw3TlUzdm9GemVLYTVUQWE0R1FNM2REdmczR0k4V0JKSXZwRHY5ZjBnWmFYWGJrcDlBNkk0TmJYeExXdk95cGRGVzhHTUpkV1JiRXBFeXIxZmZoR1MxVVk2cXNTRlA5Z2ZkWGxMenNSd2VTTHA1YWg4amxpdU45MEV1bzdvSDdoJTJGR1RaMERXUEw2MURXMDBWbzNZMVBydyUzRCUzRA; cto_bidid=qN-GWF9zdmtnVVlXanhqQ1JLS0VhNTNNTVVZNWxwWlRRcVJ4dnJUT3RoY3RzT0hyZnloWDNjWUFLWk9qNHhFMHFuSGY0eEVaTXpDZndmZUN5SW9iRXoyQ3dJS2NmSjdJeiUyRlpxZlJ6R1BzWFUxZXFmJTJCWFI5bFdGc0tDJTJGRDlqZDgzcG0waVBFQUUyc0dFd1FySE9mUGJnMkZZMUElM0QlM0Q; JSESSIONID=1AEAFF8A6EC450C8BA284779410F3053; _gat_gtag_UA_158277904_1=1; _ga_34Y2XZBFHV=GS1.1.1691359052.5.1.1691359053.0.0.0; _ga=GA1.1.743020840.1691280896; __viCookieActive=true; __gads=ID=2316400f668918d1-22b25fd2558000fd:T=1691280898:RT=1691359053:S=ALNI_MYkG92hBWm0Kmcdx8xhuuRAp074rg; __gpi=UID=00000c277847858f:T=1691280898:RT=1691359053:S=ALNI_MbyOPrKMxH3JkH1jts6bMtPZHhkew; amp_6e403e=i-rkk1eOVw9lvcQey9y5t2...1h76dm26o.1h76dm26o.0.0.0";
		try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
			HttpPost request = new HttpPost("https://www.codeusingjava.com/createpojo");
			request.addHeader("Content-Type", "application/json;charset=UTF-8");
			request.addHeader("Cookie", cookieHeader);
			request.addHeader("X-CSRF-TOKEN", "1413d189-4e2c-4dfd-88c1-c53f0e711cf4");
			StringEntity entity = new StringEntity(
					objectMapper.writeValueAsString(expRequestBody));
			request.setEntity(entity);

			try (CloseableHttpResponse response = httpClient.execute(request)) {
				HttpEntity responseEntity = response.getEntity();
				String responseBody = EntityUtils.toString(responseEntity);
				int statusCode = response.getStatusLine().getStatusCode();
				if (statusCode == 200)
					return responseBody.replace("Root", className);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String getRequestMethod(String httpMethod) {
		// BaseAPI
		switch (httpMethod.toLowerCase()) {
		case "get": {
			return "String response=BaseAPI.performGet(httpMethod,header);";
		}
		case "post": {
			return "String response=BaseAPI.performPost(httpMethod, requestPayload, header);";
		}
		case "put": {
			return "String response= =BaseAPI.performPut(httpMethod, requestPayload, header);";
		}
		case "delete": {
			return "String response= BaseAPI.performDelete(httpMethod, requestPayload, header);";
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
