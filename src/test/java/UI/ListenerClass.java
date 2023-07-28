package UI;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerClass extends BaseClass implements ITestListener{
	@Override
	public void onTestFailure(ITestResult result) {
		File srcFile = ((TakesScreenshot) this.driver).getScreenshotAs(OutputType.FILE);
		final String filePath = "." + File.separator + "src" + File.separator + "screenshot" + File.separator
				+ result.getMethod().getMethodName() + System.currentTimeMillis() + ".png";
		try {
			FileUtils.copyFile(srcFile, new File(filePath));
			extentTest.addScreenCaptureFromPath(filePath);
			extentTest.fail(result.getThrowable());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
