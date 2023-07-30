package api.request;

import java.io.File;

import org.json.JSONObject;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import com.google.gson.Gson;
import UI.BaseClass;
import api.responseDTO.GetAPIResponseDTO;
import api.responseDTO.GetAPIResponseDTO.Employee;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.LogSpecification;
import utils.fileReader.PropertiesReader;

public class DoGetRequest extends BaseClass{
	private static final String proFfilePath = "." + File.separator + "src" + File.separator + "property"
			+ File.separator + "api.properties";
	Gson gson = new Gson();

	@Test(priority = 0)
	public void doGetRequest() {
		GetAPIResponseDTO getAPIResponseDTO = new GetAPIResponseDTO();
		PropertiesReader prop = new PropertiesReader();
		String baseURL = prop.getPropertyDetails(proFfilePath, "baseurl");
		String endPoint = prop.getPropertyDetails(proFfilePath, "getAPIEndPoint");

		RestAssured.baseURI = baseURL;
		Response response = RestAssured.given().log().all().given().header("Content-Type", "application/json").when()
				.get(endPoint).then().extract().response();
		String formattedResponse = response.asString();
		LogSpecification sp=(LogSpecification)RestAssured.given().log().all();
		JSONObject obj=new JSONObject(sp.everything(true));
	    System.out.print("------->>>"+obj);
		//CurlCaptureExample.constructCurlCommand();
		extentTest.createNode(new Gson().toJson(null));
		extentTest.createNode(formattedResponse);
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
