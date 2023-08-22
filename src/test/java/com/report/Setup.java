package com.report;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class Setup implements ITestListener{
	private static ExtentReports extentReports;
	public static ThreadLocal<ExtentTest> extentTest=new ThreadLocal();
	
	@Override
	public void onStart(ITestContext context) {
		extentReports=ExtentReportManager.setExtentReportConfig();
	}
	
	@Override
	public void onFinish(ITestContext context) {
     if(extentReports!=null)
    	 extentReports.flush();
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		ExtentTest test=extentReports.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
		}

}
