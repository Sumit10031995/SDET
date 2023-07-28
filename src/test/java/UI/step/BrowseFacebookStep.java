package UI.step;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import UI.BaseClass;
import UI.ListenerClass;
import UI.page.BrowseFacebookPage;
import utils.uiUtility.WaitUtils;

@Listeners(ListenerClass.class)
public class BrowseFacebookStep extends BaseClass {
	@Test(groups = { "sanity" })
	public void testFBLoginFeature() {
		driver.get(BrowseFacebookPage.url);
		BrowseFacebookPage page = PageFactory.initElements(driver, BrowseFacebookPage.class);
		WaitUtils.visibilityOf(driver, page.getFBText());
		page.getIdTextbox().sendKeys(page.id);
		page.getPassTextbox().sendKeys(page.password);
		Assert.assertTrue(false);
		page.getLoginButton().click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	}

}
