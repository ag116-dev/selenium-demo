package com.ag.selenium.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ag.selenium.pageObjects.LoginPage;
import com.ag.selenium.utilities.XLUtils;

public class TC_Login_001 extends BaseClass {
	@Test(dataProvider = "LoginData")
	public void login_001(String username, String password) throws IOException {
		webDriver.get(baseURL);
		logger.info(baseURL);
		LoginPage loginPage = new LoginPage(webDriver);
		loginPage.inputUsername.sendKeys(username);
		loginPage.inputPassword.sendKeys(password);
		loginPage.btnLogin.click();
		if(webDriver.getTitle().equals("ACME demo app"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			Assert.assertTrue(false);
		}
	}
	
	@DataProvider(name = "LoginData")
	String[][] getData() throws IOException {
		String path = System.getProperty("user.dir") + "/src/test/java/com/ag/selenium/testData/LoginData.xlsx";
		int rowNum = XLUtils.getRowCount(path, "Sheet1");
		int colNum = XLUtils.getCellCount(path, "Sheet1", rowNum);
		String loginData[][] = new String[rowNum][colNum];
		for (int i = 1; i <= rowNum; i++) {
			for (int j = 0; j < colNum; j++) {
				loginData[i - 1][j] = XLUtils.getCellData(path, "Sheet1", i, j);
			}
		}
		return loginData;
	}
}
