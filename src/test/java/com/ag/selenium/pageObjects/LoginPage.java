package com.ag.selenium.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver webDriver;
	public LoginPage(WebDriver webDriver) {
		this.webDriver = webDriver;
		PageFactory.initElements(webDriver, this);
	}

	@FindBy(id="username")
	@CacheLookup
	public WebElement inputUsername;
	
	@FindBy(id="password")
	@CacheLookup
	public WebElement inputPassword;
	
	@FindBy(id="log-in")
	@CacheLookup
	public WebElement btnLogin;
	
}
