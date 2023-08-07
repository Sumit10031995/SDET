package api.tests.APIServiceQA;
import api.clients.APIServiceQA.APIServiceQAResponseDTO;
import java.util.HashMap;
import org.testng.annotations.Test;
import com.google.gson.Gson;
import api.request.BaseAPI;
import io.restassured.response.Response;

public class APIServiceQATests {
	@Test
	public void test() {
		Gson gson = new Gson();
		APIServiceQAResponseDTO aPIServiceQAResponseDTO =new APIServiceQAResponseDTO();
		Response response = BaseAPI.performPost("url", "header", new HashMap());
		String formattedResponse = response.asString();
		if (response.statusCode() == 200) {
			aPIServiceQAResponseDTO = gson.fromJson(formattedResponse, APIServiceQAResponseDTO.class);
		}
	}
}