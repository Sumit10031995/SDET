package api.request;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Listeners;

import com.github.dzieciou.testing.curl.CurlLoggingRestAssuredConfigFactory;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import report.ExtentReportManager;
import report.Setup;
@Listeners(Setup.class)
public class BaseAPI extends ExtentReportManager {
	public static boolean isNotNullAndNotEmpty(String str) {
		return str != null && !str.isEmpty();
	}

	public static <T> boolean isNotNullAndNotEmpty(List<T> list) {
		return list != null && list.size() != 0;
	}

	public static <T> boolean isNotNullAndNotEmpty(Map<T, T> map) {
		return map != null && map.size() != 0;
	}

	private static void printRequestLogInReport(RequestSpecification requestSpecification) {
		QueryableRequestSpecification specification = SpecificationQuerier.query(requestSpecification);
		logInfo(specification.getBasePath());
		logInfo(specification.getBaseUri());
		logInfo(specification.getMethod());
		logInfo(specification.getBody());
		logInfo(specification.getHeaders().asList().toString());
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
		printRequestLogInReport(requestSpecification);
		printResponseLogInReport(response);
		return response;
	}

	public static Response performPost(String endPoint, String requestPayLoad, Map<String, String> headers) {
		RequestSpecification requestSpecification = getRequestSpecefication(endPoint, requestPayLoad, headers);
		Response response = requestSpecification.when().post(endPoint).then().extract().response();
		printRequestLogInReport(requestSpecification);
		printResponseLogInReport(response);
		return response;
	}

	public static Response performPut(String endPoint, String requestPayLoad, Map<String, String> headers) {
		RequestSpecification requestSpecification = getRequestSpecefication(endPoint, requestPayLoad, headers);
		Response response = requestSpecification.when().put(endPoint).then().extract().response();
		printRequestLogInReport(requestSpecification);
		printResponseLogInReport(response);
		return response;
	}

	public static Response performDelete(String endPoint, String requestPayLoad, Map<String, String> headers) {
		RequestSpecification requestSpecification = getRequestSpecefication(endPoint, requestPayLoad, headers);
		Response response = requestSpecification.when().delete(endPoint).then().extract().response();
		printRequestLogInReport(requestSpecification);
		printResponseLogInReport(response);
		return response;
	}
}
