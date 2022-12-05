package com.ag.selenium.testCases;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.ag.selenium.utilities.ReadConfig;

public class BaseClass {

	ReadConfig readConfig = new ReadConfig();

	public String baseURL = readConfig.getApplicationUrl();
	public String username = "mngr164225";
	public String password = "jahetAp";
	public static WebDriver webDriver;
	public static Logger logger;

	@Parameters("browser")
	@BeforeClass
	public void setup(@Optional("chrome") String br) {
		logger = Logger.getLogger("automation");
		PropertyConfigurator.configure("log4j.properties");

		if (br.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + readConfig.getChromePath());
			webDriver = new ChromeDriver();
		} else {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + readConfig.getChromePath());
			webDriver = new ChromeDriver();
		}

		webDriver.manage().timeouts().implicitlyWait(Duration.ofMinutes(3));
		webDriver.manage().window().maximize();
		
	}

	@AfterClass
	public void tearDown() {
		webDriver.quit();
	}

	public void captureScreen(WebDriver webDriver, String name) throws IOException {
		String filePath = System.getProperty("user.dir") + "/Screenshots/" + name + ".png";
		TakesScreenshot ts = (TakesScreenshot) webDriver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(filePath);
		FileUtils.copyFile(source, target);
		logger.info("Screenshot taken: " + filePath);
	}
}
