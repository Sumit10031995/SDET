package api.request;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import com.google.gson.Gson;
import api.responseDTO.GetAPIResponseDTO;
import api.responseDTO.GetAPIResponseDTO.Employee;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.fileReader.HostFileReader;
import utils.fileReader.PropertiesReader;

public class DoGETRequest extends BaseAPI {
	private static final String proFfilePath = "." + File.separator + "src" + File.separator + "property"
			+ File.separator + "api.properties";
	Gson gson = new Gson();

	@Test(priority = 0)
	public void doGetRequest() {

		GetAPIResponseDTO getAPIResponseDTO = new GetAPIResponseDTO();
		PropertiesReader prop = new PropertiesReader();
		String endPoint = prop.getPropertyDetails(proFfilePath, "getAPIEndPoint");
		RestAssured.baseURI = new HostFileReader().hosts().getName();

		Response response = performGet(endPoint, new HashMap());
		String formattedResponse = response.asString();
		if (response.statusCode() == 200) {
			AssertJUnit.assertEquals(response.statusCode(), 200);
			getAPIResponseDTO = gson.fromJson(formattedResponse, GetAPIResponseDTO.class);
			List<Employee> name = getAPIResponseDTO.getData().stream()
					.filter(n -> n.getEmployee_name().equals("Tiger Nixon")).toList();
			if (isNotNullAndNotEmpty(name)) {
				logPass(name.get(0).getEmployee_name());
			} else
				Assert.assertTrue(false);
			logFail("TestCase Pass Fail");

		} else
			logFail("TestCase Pass Fail");
		Assert.assertTrue(false);

	}

}
