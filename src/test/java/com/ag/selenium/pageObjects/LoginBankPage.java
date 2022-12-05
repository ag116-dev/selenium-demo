package com.ag.selenium.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginBankPage extends BasePage {
	
	public LoginBankPage(WebDriver webDriver) {
		super(webDriver);
		PageFactory.initElements(webDriver, this);
	}

	@FindBy(name="uid")
	@CacheLookup
	public WebElement inputUsername;
	
	@FindBy(name="password")
	@CacheLookup
	public WebElement inputPassword;
	
	@FindBy(name="btnLogin")
	@CacheLookup
	public WebElement btnLogin;
	
}
