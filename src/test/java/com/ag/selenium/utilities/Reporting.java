package com.ag.selenium.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.ag.selenium.testCases.BaseClass;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

@SuppressWarnings("unused")
public class Reporting extends BaseClass implements ITestListener {

	public ExtentSparkReporter extentSparkReporter;
	public ExtentReports reports;
	public ExtentTest test;

	public void onStart(ITestContext context) {
		String repName = "Test-Report.html";
//		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
//		String repName = "Test-Report-" + timeStamp + ".html";
		extentSparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/" + repName);
		try {
			extentSparkReporter.loadXMLConfig(System.getProperty("user.dir") + "/extent-config.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			logger.error(e);
		}
		reports = new ExtentReports();
		reports.attachReporter(extentSparkReporter);
		reports.setSystemInfo("Host name", "localhost");
		reports.setSystemInfo("Environment", "QA");
		reports.setSystemInfo("Author", "AG");
	}

	public void onTestSuccess(ITestResult result) {
		logger.info("onTestSuccess");
		test = reports.createTest(result.getName());
		test.log(Status.PASS, MarkupHelper.createLabel(result.getName(), ExtentColor.GREEN));
	}

	public void onTestFailure(ITestResult result) {
		logger.info("onTestFailure");
		test = reports.createTest(result.getName());
		test.log(Status.FAIL, MarkupHelper.createLabel(result.getName(), ExtentColor.RED));

		try {
			captureScreen(webDriver, result.getName());
			String screenshotPath = (System.getProperty("user.dir") + "\\Screenshots\\" + result.getName() + ".png");
			File f = new File(screenshotPath);
			if (f.exists()) {
				test.fail("Screen shot is below: " + test.addScreenCaptureFromPath(screenshotPath));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void onTestSkipped(ITestResult result) {
		logger.info("onTestSkipped");
		test = reports.createTest(result.getName());
		test.log(Status.SKIP, MarkupHelper.createLabel(result.getName(), ExtentColor.ORANGE));
	}

	public void onFinish(ITestContext context) {
		logger.info("onFinish");
		reports.flush();
	}

}
