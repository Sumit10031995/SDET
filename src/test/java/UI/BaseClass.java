package UI;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import gherkin.deps.net.iharder.Base64.InputStream;

public class BaseClass {
	private static final Logger logger = Logger.getLogger(BaseClass.class.getName());
	protected static WebDriver driver = null;
	public static ExtentReports extentReport;
	public static ExtentTest extentTest;
	final String reportsFilePath = "." + File.separator + "src" + File.separator + "test-output"+ File.separator + "UIHTMLReports" + File.separator
			+ "Report" + System.currentTimeMillis() + ".html";
	final String screenshotsFilePath = "." + File.separator + "src" + File.separator + "test-output"+ File.separator + File.separator + "screenshot" + File.separator
			+ "Report" + System.currentTimeMillis() + ".PNG";

	@BeforeTest
	@Parameters("browser")
	protected static void lunchBrowser(ITestContext context, @Optional("chrome") String browser) {
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
		extentTest = extentReport.createTest(context.getName());
		Capabilities capabilities=((RemoteWebDriver) driver).getCapabilities();
		String device=capabilities.getBrowserName()+","+capabilities.getBrowserVersion();
		logger.info("Browser launched successfully");
	}

	@AfterTest
	protected void quitQuit() {
		driver.quit();
		logger.info("Browser closed successfully");

	}

	@BeforeSuite
	protected void initialiseExtentReport() {
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportsFilePath);
		sparkReporter.config().setDocumentTitle("My Test Report");
		sparkReporter.config().setReportName("Test Execution Report");
		sparkReporter.config().setCss(".r-img { width: 100%; height: auto; } .test { font-family: Arial, sans-serif; }");
		sparkReporter.config().setEncoding("UTF-8");
		sparkReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
		extentReport = new ExtentReports();
		extentReport.attachReporter(sparkReporter);
	}

	@AfterSuite
	protected void generateReport() {
		extentReport.flush();
		Desktop.getDesktop().browseFileDirectory(new File(reportsFilePath));
	}

	@AfterMethod
	protected void checkStatus(ITestResult result) {
		File srcFile = ((TakesScreenshot) this.driver).getScreenshotAs(OutputType.FILE);
		try {
			if (result.getStatus() == ITestResult.FAILURE) {
				File destFile = new File(screenshotsFilePath);
				FileUtils.copyFile(srcFile, destFile);
				extentTest.addScreenCaptureFromPath(destFile.getAbsolutePath());
				extentTest.fail(result.getThrowable());
				extentTest.fail(result.getMethod().getMethodName() + " IS FAIL");

			} else {
				extentTest.pass(result.getMethod().getMethodName() + " IS PASSED");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private InputStream FileInputStream(File srcFile) {
		// TODO Auto-generated method stub
		return null;
	}

}
