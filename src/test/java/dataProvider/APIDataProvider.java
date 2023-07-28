package dataProvider;

import org.testng.annotations.DataProvider;

import utils.fileReader.JSONFileReaderClient;

public class APIDataProvider {
	JSONFileReaderClient client=new JSONFileReaderClient();
	
	@DataProvider(name="API-POSTRequest-Body", parallel = true)
	public Object[][] requestDetails(){
		return new Object[][] {
			{client.getBody()}
		};
	}
}
