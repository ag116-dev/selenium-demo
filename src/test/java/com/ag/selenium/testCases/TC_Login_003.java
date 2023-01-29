package com.ag.selenium.testCases;

import com.ag.selenium.pageObjects.DemoqaPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_Login_003 extends BaseClass {
	@Test(description = "should login successfully")
	public void login_003() throws InterruptedException {
		DemoqaPage loginPage = new DemoqaPage(webDriver);
		loginPage.open();

		Assert.assertTrue(loginPage.loginInValidUser("tomsmith", "SuperSecretPassword!"));
	}

}
