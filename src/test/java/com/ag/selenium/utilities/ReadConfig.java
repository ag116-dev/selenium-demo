package com.ag.selenium.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	Properties pro;

	public ReadConfig() {
		File file = new File("./Configuration/config.properties");

		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			pro = new Properties();
			pro.load(fileInputStream);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public String getApplicationUrl() {
		String url = pro.getProperty("baseURL");
		return url;
	}
	
	public String getChromePath() {
		String chromepath = pro.getProperty("chromepath");
		return chromepath;
	}

}
