package cucumber;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import UI.page.BrowseFacebookPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.uiUtility.WaitUtils;

public class CucumberFacebookTest {
    private BrowserHooks browserHooks;
    BrowseFacebookPage page;
        
    public CucumberFacebookTest() {
        this.browserHooks = new BrowserHooks();
        page=PageFactory.initElements(browserHooks.driver, BrowseFacebookPage.class);
    }
	
	
	@Given("user should be on login page$")
	public void user_should_be_on_login_page() {
		browserHooks.driver.get("https://www.facebook.com/");
		WaitUtils.visibilityOf(browserHooks.driver,page.getIdTextbox());
	}

	@When("user provide valid userID and password(.+),(.+)$")
	public void user_provide_valid_user_id_and_password(String id, String password) {
		page.getIdTextbox().sendKeys(id);
		page.getPassTextbox().sendKeys(password);
	}

	@And("click on login button$")
	public void click_on_login_button() {
	    page.getLoginButton().click();
	}

	@Then("user should be landed on FB home page$")
	public void user_should_be_landed_on_fb_home_page() {
	    Assert.assertTrue(true);
	}

}
