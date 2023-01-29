package com.ag.selenium.utilities;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.ag.selenium.testCases.BaseClass;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Reporting extends BaseClass implements ITestListener {

	public ExtentSparkReporter extentSparkReporter;
	public ExtentReports reports;
	public ExtentTest test;

	public String testName;
	public String testDetail;

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
			logger.error(e);
		}
		reports = new ExtentReports();
		reports.attachReporter(extentSparkReporter);
		reports.setSystemInfo("Host name", "localhost");
		reports.setSystemInfo("Environment", "QA");
		reports.setSystemInfo("Author", "AG");
	}

	@Override
	public void onTestStart(ITestResult result) {
		logger.debug("onTestStart");
		testName = result.getInstance().getClass().getSimpleName() + "-" + result.getName();
		testDetail = result.getMethod().getDescription();
		if (testDetail.isEmpty()) {
			testDetail = result.getName();
		}
	}

	public void onTestSuccess(ITestResult result) {
		logger.debug("onTestSuccess");

		test = reports.createTest(testName);
		test.log(Status.PASS, MarkupHelper.createLabel(testDetail, ExtentColor.GREEN));
	}

	public void onTestFailure(ITestResult result) {
		logger.debug("onTestFailure");
//		test.log(Status.FAIL, MarkupHelper.createLabel(result.getName(), ExtentColor.RED));

		try {
			captureScreen(webDriver, result.getName());
			String screenshotPath = (System.getProperty("user.dir") + "\\Screenshots\\" + result.getName() + ".png");
			test = reports.createTest(testName);
			test.log(Status.FAIL, testDetail, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e);
		}

	}

	public void onTestSkipped(ITestResult result) {
		logger.debug("onTestSkipped");
		test = reports.createTest(testName);
		test.log(Status.SKIP, MarkupHelper.createLabel(testDetail, ExtentColor.ORANGE));
	}

	public void onFinish(ITestContext context) {
		logger.debug("onFinish");
		reports.flush();
	}

}
