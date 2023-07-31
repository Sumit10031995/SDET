package api.request;

import java.io.File;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.github.dzieciou.testing.curl.CurlLoggingRestAssuredConfigFactory;
import com.google.gson.Gson;

import api.responseDTO.GetAPIResponseDTO;
import api.responseDTO.GetAPIResponseDTO.Employee;
import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import utils.fileReader.HostFileReader;
import utils.fileReader.PropertiesReader;

public class DoGetRequest {
	private static final String proFfilePath = "." + File.separator + "src" + File.separator + "property"
			+ File.separator + "api.properties";
	Gson gson = new Gson();

	@Test(priority = 0)
	public void doGetRequest() {
		GetAPIResponseDTO getAPIResponseDTO = new GetAPIResponseDTO();
		PropertiesReader prop = new PropertiesReader();
		String endPoint = prop.getPropertyDetails(proFfilePath, "getAPIEndPoint");
		RestAssured.baseURI = new HostFileReader().hosts().getName();
		RestAssuredConfig config = CurlLoggingRestAssuredConfigFactory.createConfig(); 
		Response response = RestAssured.given().config(config).given().header("Content-Type", "application/json").when()
				.get(endPoint).then().extract().response();
		String formattedResponse = response.asString();
		
		if (response.statusCode() == 200) {
			AssertJUnit.assertEquals(response.statusCode(), 200);
			getAPIResponseDTO = gson.fromJson(formattedResponse, GetAPIResponseDTO.class);
			for (Employee emp : getAPIResponseDTO.getData()) {
				if (getAPIResponseDTO.getData().get(0).getEmployee_name().equals("Tiger Nixon"))
					System.out.println(getAPIResponseDTO.getData().get(0).getEmployee_name());
				break;
			}

		} else
			AssertJUnit.assertTrue(false);

	}

}
