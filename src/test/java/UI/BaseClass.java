package UI;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import java.util.logging.Logger;

public class BaseClass {
    private static final Logger logger = Logger.getLogger(BaseClass.class.getName());
	protected static WebDriver driver = null;

	@BeforeSuite
	@Parameters("browser")
	public static  void lunchBrowser(String browser) {
		System.out.println("before test");
		System.setProperty("webdriver.http.factory", "jdk-http-client");
		switch (browser) {
		case "chrome": {
			driver = new ChromeDriver();
			break;
		}
		case "firefox": {
			driver = new FirefoxDriver();
			break;
		}
		case "internetExplorer": {
			driver = new InternetExplorerDriver();
			break;
		}
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		logger.info("Browser launched successfully");
	}

	
	@AfterSuite
	public void quitQuit() {
		System.out.println("after suite");
		driver.quit();
		logger.info("Browser closed successfully");

	}

}
