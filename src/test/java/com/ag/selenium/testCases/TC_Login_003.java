package com.ag.selenium.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.ag.selenium.pageObjects.DemoqaPage;

public class TC_Login_003 extends BaseClass {
	@Test(description = "should login successfully")
	public void login_003() throws IOException, InterruptedException {
		DemoqaPage loginPage = new DemoqaPage(webDriver);
		loginPage.open();
		
		if(loginPage.loginInValidUser("tomsmith", "SuperSecretPassword!"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			Assert.assertTrue(false);
		}
	}

}
