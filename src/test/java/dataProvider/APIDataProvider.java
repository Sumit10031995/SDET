package dataProvider;

import org.testng.annotations.DataProvider;

public class APIDataProvider {

	@DataProvider(name="API-POSTRequest-Body")
	public Object[][] requestDetails(){
		return new Object[][] {
			{"abcd"}
		};
	}
}
