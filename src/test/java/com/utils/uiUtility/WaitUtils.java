package com.utils.uiUtility;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.utils.fileReader.PropertiesReader;

public class WaitUtils {
	private static int time;
	 WaitUtils() {
		time=Integer.parseInt(PropertiesReader.getPropertyDetails("duration.mili"));
	}

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
