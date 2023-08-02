package UI.step;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import UI.BaseClass;
import UI.page.BrowseFacebookPage;
import utils.uiUtility.WaitUtils;

public class BrowseFacebookStep extends BaseClass {
	@Test(groups = { "sanity" })
	public void testFBLoginFeature() {
		driver.get(BrowseFacebookPage.url);
		logInfo("Navigated To FB URL");
		BrowseFacebookPage page = PageFactory.initElements(driver, BrowseFacebookPage.class);
		WaitUtils.visibilityOf(driver, page.getFBText());
		page.getIdTextbox().sendKeys(page.id);
		Assert.assertEquals("ram", "sumit");
		page.getPassTextbox().sendKeys(page.password);
		Assert.assertTrue(false);
		logFail("TestExecutationFail");
		page.getLoginButton().click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	}

}
