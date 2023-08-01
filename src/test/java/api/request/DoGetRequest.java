package api.request;

import java.io.File;
import java.util.List;

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

public class DoGetRequest extends BaseAPI {
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
			List<Employee> name = getAPIResponseDTO.getData().stream()
					.filter(n -> n.getEmployee_name().equals("Tiger Nixon")).toList();
			if (isNotNullAndNotEmpty(name)) {
				System.out.println(name.get(0).getEmployee_name());
			} else
				AssertJUnit.assertTrue(false);

		} else
			AssertJUnit.assertTrue(false);

	}

}
