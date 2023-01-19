package com.ag.selenium.testCases;

import com.ag.selenium.pageObjects.LoginBankPage;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_Login_002 extends BaseClass {
	@Test
	public void login_002() throws InterruptedException {
		String baseURL = "https://demo.guru99.com/v4/";
		LoginBankPage loginPage = new LoginBankPage(webDriver);
		loginPage.open(baseURL);
		loginPage.inputUsername.sendKeys("username1");
		loginPage.inputPassword.sendKeys("password1");
		loginPage.btnLogin.click();

		Thread.sleep(2000);
		if (isAlertPresent()) {
			webDriver.switchTo().alert().accept();
			webDriver.switchTo().defaultContent();
			Assert.assertTrue(true);
		} else {
			Assert.fail();
		}
	}

	public boolean isAlertPresent() {
		try {
			webDriver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			logger.error(e);
			return false;
		}
	}
}
