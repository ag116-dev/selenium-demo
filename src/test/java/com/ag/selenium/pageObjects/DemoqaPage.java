package com.ag.selenium.pageObjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DemoqaPage {

	WebDriver webDriver;

	public DemoqaPage(WebDriver webDriver) {
		this.webDriver = webDriver;
		PageFactory.initElements(webDriver, this);
	}

	@FindBy(id = "userName")
	@CacheLookup
	public WebElement inputUsername;

	@FindBy(id = "password")
	@CacheLookup
	public WebElement inputPassword;

	@FindBy(css = "button#login")
	@CacheLookup
	public WebElement btnLogin;

	@FindBy(id = "name")
	@CacheLookup
	public WebElement errorMessage;

	/**
	 * Navigator to url
	 *
	 */
	public void open() {
		String baseURL = "https://demoqa.com/login";
		this.open(baseURL);
	}
	
	/**
	 * Navigator to url
	 *
	 * @param url
	 */
	public void open(String url) {
		webDriver.get(url);
	}

	/**
	 * Login as valid user
	 *
	 * @param userName
	 * @param password
	 * @return boolean
	 * @throws InterruptedException 
	 */
	public boolean loginInValidUser(String userName, String password) throws InterruptedException {
		this.inputUsername.sendKeys(userName);
		this.inputPassword.sendKeys(password);
		this.btnLogin.click();
		if (this.errorMessage.getText().equals("Invalid username or password!")) {
			return true;
		}
		return false;
	}

}
