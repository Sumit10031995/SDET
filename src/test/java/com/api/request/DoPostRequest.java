package com.api.request;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.dzieciou.testing.curl.CurlLoggingRestAssuredConfigFactory;
import com.google.gson.Gson;

import com.api.requestDTO.PostRequestBodyDTO;
import com.api.responseDTO.GetAPIResponseDTO;
import com.api.responseDTO.GetAPIResponseDTO.Employee;
import com.api.responseDTO.PostAPIResponseDTO;
import com.dataProvider.APIDataProvider;
import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import com.utils.fileReader.PropertiesReader;
import com.utils.utility.Assertions;
import com.utils.utility.Utility;

public class DoPostRequest extends BaseAPI{
	private  Logger logger = LogManager.getLogger(DoPostRequest.class);

	@Test(priority = 0, dataProvider = "API-POSTRequest-Body", dataProviderClass = APIDataProvider.class)
	public void doGetRequest(PostRequestBodyDTO request) {
		String salary="123";
		PostAPIResponseDTO postAPIResponseDTO = new PostAPIResponseDTO();
		PropertiesReader prop = new PropertiesReader();
		String baseURL = prop.getPropertyDetails("baseurl");
		String endPoint = prop.getPropertyDetails("postAPIEndPoint");
		logger.info("my name is sumit");

		RestAssured.baseURI = baseURL;
		Response response = RestAssured.given().config(CurlLoggingRestAssuredConfigFactory.createConfig()).given().header("Content-Type", "application/json").and()
				.body(new Gson().toJson(request)).when().post(endPoint).then().extract().response();
		String formattedResponse = response.asString();
		logger.info(response);
		if (response.statusCode() == 200) {
			AssertJUnit.assertEquals(response.statusCode(), 200);
			postAPIResponseDTO = gson.fromJson(formattedResponse, PostAPIResponseDTO.class);
			System.out.println(postAPIResponseDTO.getData().getSalary().equals("124"));
			Assertions.assertEquals("Name Not Matching", postAPIResponseDTO.getData().getSalary(), salary);
			} 
			else if (response.statusCode() == 404)
				Assertions.assertTrue("API End Point Is Not Correct", false);
			else if (response.statusCode() == 429)
				Assertions.assertTrue("Too Many Request Error", false);
			else if (response.statusCode() == 500)
				Assertions.assertTrue("Internal Server Error", false);

		}
}
