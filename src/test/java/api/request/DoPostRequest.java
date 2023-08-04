package api.request;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.dzieciou.testing.curl.CurlLoggingRestAssuredConfigFactory;
import com.google.gson.Gson;

import api.requestDTO.PostRequestBodyDTO;
import api.responseDTO.GetAPIResponseDTO;
import api.responseDTO.GetAPIResponseDTO.Employee;
import api.responseDTO.PostAPIResponseDTO;
import dataProvider.APIDataProvider;
import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import utils.fileReader.PropertiesReader;
import utils.utility.Utility;

public class DoPostRequest {
	Gson gson = new Gson();

	@Test(priority = 0, dataProvider = "API-POSTRequest-Body", dataProviderClass = APIDataProvider.class)
	public void doGetRequest(PostRequestBodyDTO request) {
		PostAPIResponseDTO postAPIResponseDTO = new PostAPIResponseDTO();
		PropertiesReader prop = new PropertiesReader();
		String baseURL = prop.getPropertyDetails("baseurl");
		String endPoint = prop.getPropertyDetails("postAPIEndPoint");

		RestAssured.baseURI = baseURL;
		Response response = RestAssured.given().config(CurlLoggingRestAssuredConfigFactory.createConfig()).given().header("Content-Type", "application/json").and()
				.body(new Gson().toJson(request)).when().post(endPoint).then().extract().response();
		String formattedResponse = response.asString();
		if (response.statusCode() == 200) {
			AssertJUnit.assertEquals(response.statusCode(), 200);
			postAPIResponseDTO = gson.fromJson(formattedResponse, PostAPIResponseDTO.class);
			System.out.println(postAPIResponseDTO.getData().getSalary().equals("124"));
		} else
			AssertJUnit.assertTrue(false);

	}
}
