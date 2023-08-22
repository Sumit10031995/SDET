package com.UI.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BrowseFacebookPage {
	public static final String url="https://www.facebook.com/";
	public static final String facebookAttributeText="Facebook";
	public static final String id="naiksumit7@gmail.com";
	public static final String password="9937798945";


	@FindBy(xpath="//img[@alt='Facebook']")
	WebElement FBText;
	
	@FindBy(xpath="//input[@id='email']")
	WebElement idTextbox;
	
	@FindBy(xpath="//input[@id='pass']")
	WebElement passTextbox;
	
	@FindBy(xpath="//button[@name='login']")
	WebElement loginButton;

	public WebElement getFBText() {
		return FBText;
	}

	public WebElement getIdTextbox() {
		return idTextbox;
	}

	public WebElement getPassTextbox() {
		return passTextbox;
	}

	public WebElement getLoginButton() {
		return loginButton;
	}


}
