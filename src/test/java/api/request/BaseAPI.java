package api.request;

import java.util.List;
import java.util.Map;
import org.testng.annotations.Listeners;
import com.github.dzieciou.testing.curl.CurlLoggingRestAssuredConfigFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.google.gson.Gson;

import java.net.URL;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import report.ExtentReportManager;
import report.Setup;
import utils.fileReader.FileManager;

@Listeners(Setup.class)
public class BaseAPI extends ExtentReportManager {
	Gson gson = new Gson();
	private final static String apiSchema=FileManager.searchFile("apiSchema.json").getAbsolutePath();
	public static boolean isNotNullAndNotEmpty(String str) {
		return str != null && !str.isEmpty();
	}

	public static <T> boolean isNotNullAndNotEmpty(List<T> list) {
		return list != null && list.size() != 0;
	}

	public static <T> boolean isNotNullAndNotEmpty(Map<T, T> map) {
		return map != null && map.size() != 0;
	}

	private static void printRequestLogInReport(RequestSpecification requestSpecification,String endPoint) {
		QueryableRequestSpecification specification = SpecificationQuerier.query(requestSpecification);
		logInfo(getCurlCommandFromRequestSpec(specification,endPoint));

	}

	private static void printResponseLogInReport(Response response) {
		logInfo(response.statusCode() + "");
		logInfo(response.headers().asList().toString());
		logInfo(response.body().asString());
	}

	private static RequestSpecification getRequestSpecefication(String endPoint, String requestPayLoad,
			Map<String, String> headers) {
		return RestAssured.given().config(CurlLoggingRestAssuredConfigFactory.createConfig())
				.contentType(ContentType.JSON).headers(headers).body(requestPayLoad);
	}

	public static Response performGet(String endPoint, Map<String, String> headers) {
		RequestSpecification requestSpecification = getRequestSpecefication(endPoint, "{}", headers);
		Response response = requestSpecification.when().get(endPoint).then().extract().response();
		printRequestLogInReport(requestSpecification,endPoint);
		printResponseLogInReport(response);
		return response;
	}

	public static Response performPost(String endPoint, String requestPayLoad, Map<String, String> headers) {
		RequestSpecification requestSpecification = getRequestSpecefication(endPoint, requestPayLoad, headers);
		Response response = requestSpecification.when().post(endPoint).then().extract().response();
		printRequestLogInReport(requestSpecification,endPoint);
		printResponseLogInReport(response);
		return response;
	}

	public static Response performPut(String endPoint, String requestPayLoad, Map<String, String> headers) {
		RequestSpecification requestSpecification = getRequestSpecefication(endPoint, requestPayLoad, headers);
		Response response = requestSpecification.when().put(endPoint).then().extract().response();
		printRequestLogInReport(requestSpecification,endPoint);
		printResponseLogInReport(response);
		return response;
	}

	public static Response performDelete(String endPoint, String requestPayLoad, Map<String, String> headers) {
		RequestSpecification requestSpecification = getRequestSpecefication(endPoint, requestPayLoad, headers);
		Response response = requestSpecification.when().delete(endPoint).then().extract().response();
		printRequestLogInReport(requestSpecification,endPoint);
		printResponseLogInReport(response);
		return response;
	}
	

	private static String getCurlCommandFromRequestSpec(QueryableRequestSpecification requestSpec, String endPoint) {
		StringBuilder curlCommand = new StringBuilder("curl ");
		curlCommand.append("'").append(requestSpec.getBaseUri() + endPoint).append("' ");
		curlCommand.append(" -X " + requestSpec.getMethod()+" ");
		requestSpec.getHeaders().asList().forEach(header -> curlCommand.append("-H '").append(header.getName())
				.append(": ").append(header.getValue()).append("' "));
		String requestBody = requestSpec.getBody();
		if (requestBody != null && !requestBody.isEmpty()) {
			curlCommand.append("-d '").append(requestBody.replace("'", "\\'")).append("' ");
		}
		return curlCommand.toString();
	}
	
	private static void validateAPISchema(String apiResponse, String schemaPath) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode responseNode = objectMapper.readTree(apiResponse);
			JsonNode schemaNode = objectMapper.readTree(new URL("file://" + apiSchema));
			JsonSchemaFactory schemaFactory = JsonSchemaFactory.byDefault();
			JsonSchema schema = schemaFactory.getJsonSchema(schemaNode);
			schema.validate(responseNode);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
