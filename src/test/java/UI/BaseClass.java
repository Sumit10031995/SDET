package UI;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import report.ExtentReportManager;
import report.Setup;
import utils.fileReader.PropertiesReader;
@Listeners(Setup.class)
public class BaseClass extends ExtentReportManager{
	private  final Logger logger = Logger.getLogger(BaseClass.class.getName());
	protected static WebDriver driver = null;
	public ExtentTest extentTest;
	final String screenshotsFilePath = PropertiesReader.getPropertyDetails("extent.reporter.screenshot.out")+ "Screenshot" + System.currentTimeMillis() + ".PNG";

	@BeforeClass(alwaysRun = true)
	@Parameters("browser")
	protected  void lunchBrowser(ITestContext context, @Optional("chrome") String browser) {
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

	@AfterClass
	protected void quitQuit() {
		driver.quit();
		logger.info("Browser closed successfully");

	}


	@AfterMethod(alwaysRun = true)
	protected void  checkStatus(ITestResult result) {
		File srcFile = ((TakesScreenshot) this.driver).getScreenshotAs(OutputType.FILE);
		try {
			if (result.getStatus() == ITestResult.FAILURE) {
				File destFile = new File(screenshotsFilePath);
				FileUtils.copyFile(srcFile, destFile);
				Setup.extentTest.get().addScreenCaptureFromPath(destFile.getAbsolutePath());
				extentTest.fail(result.getThrowable());
				extentTest.fail(result.getMethod().getMethodName() + " IS FAIL");

			} else {
				extentTest.pass(result.getMethod().getMethodName() + " IS PASSED");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


}
