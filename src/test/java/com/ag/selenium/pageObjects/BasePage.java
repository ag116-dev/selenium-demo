package com.ag.selenium.pageObjects;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

	WebDriver webDriver;
	public static Logger logger;

	public BasePage(WebDriver webDriver) {
		logger = Logger.getLogger("automation");
		PropertyConfigurator.configure("log4j.properties");
		this.webDriver = webDriver;
		PageFactory.initElements(webDriver, this);
	}
	
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
	 * input url
	 */
	public void open(String url) {
		webDriver.get(url);
		logger.info(url);
	}

}
