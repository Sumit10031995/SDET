package utils.uiUtility;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class WaitUtils {
	private static final int time=20000;

	public static void visibilityOf(WebDriver driver, WebElement wb) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(time));
		wait.until(ExpectedConditions.visibilityOf(wb));
	}
	
	public static void elementToBeClickable(WebDriver driver, WebElement wb) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(time));
		wait.until(ExpectedConditions.elementToBeClickable(wb));
	}
	
	public static void elementSelectionStateToBe(WebDriver driver, WebElement wb,boolean state) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(time));
		wait.until(ExpectedConditions.elementSelectionStateToBe(wb,state));
	}
}
