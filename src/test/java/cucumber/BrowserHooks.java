package cucumber;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.AfterStep;
import report.Setup;
import utils.fileReader.PropertiesReader;

public class BrowserHooks extends QueryFunctions{
	private  final Logger logger = Logger.getLogger(BrowserHooks.class.getName());
	protected static  WebDriver driver = null;
	public  ExtentTest extentTest;
	final String screenshotsFilePath = PropertiesReader.getPropertyDetails("extent.reporter.screenshot.out")+ "Screenshot" + System.currentTimeMillis() + ".PNG";

    @Before
    public void setUp() {
		System.setProperty("webdriver.http.factory", "jdk-http-client");
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        this.driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        System.out.println("Browser launched");
    }

    @After
    public void tearDown() {
        if (this.driver != null) {
        	this.driver.quit();
            System.out.println("Browser closed");
        }
    }

    public WebDriver getDriver() {
        return this.driver;
    }


	@AfterStep
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
