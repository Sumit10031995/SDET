package api.request;

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
import utils.utility.Assertions;

public class DoGetRequest extends BaseAPI {

	@Test(priority = 0)
	public void doGetRequest() {
        String employeeName="Tiger Nixon1";
		GetAPIResponseDTO getAPIResponseDTO = new GetAPIResponseDTO();
		PropertiesReader prop = new PropertiesReader();
		String endPoint = prop.getPropertyDetails("getAPIEndPoint");
		RestAssured.baseURI = new HostFileReader().hosts().getName();

		Response response = performGet(endPoint, new HashMap());
		String formattedResponse = response.asString();
		if (response.statusCode() == 200) {
			Assert.assertEquals(response.statusCode(), 200);
			getAPIResponseDTO = gson.fromJson(formattedResponse, GetAPIResponseDTO.class);
			List<Employee> name = getAPIResponseDTO.getData().stream()
					.filter(n -> n.getEmployee_name().equals(employeeName)).toList();
			Assertions.assertEquals("Name Not Matching", name.get(0).getEmployee_name(), employeeName);
		} 
		else if (response.statusCode() == 404)
			Assertions.assertTrue("API End Point Is Not Correct", false);
		else if (response.statusCode() == 429)
			Assertions.assertTrue("Too Many Request Error", false);
		else if (response.statusCode() == 500)
			logFail("Internal Server Error");

	}

}
