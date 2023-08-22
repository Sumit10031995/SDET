package com.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import com.utils.fileReader.PropertiesReader;

public class ExtentReportManager {
	private static final String reportsFilePath = PropertiesReader.getPropertyDetails("extent.reporter.spark.out")+ "Report"+ System.currentTimeMillis() + ".html";
	public static ExtentReports extentReport;

	public static ExtentReports setExtentReportConfig() {
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportsFilePath);
		sparkReporter.config().setDocumentTitle("My Test Report");
		sparkReporter.config().setReportName("SDET AUTOMATION REPORT");
		sparkReporter.config().setTheme(Theme.STANDARD);
		sparkReporter.config()
				.setCss(".r-img { width: 100%; height: auto; } .test { font-family: Arial, sans-serif; }");
		sparkReporter.config().setEncoding("UTF-8");
		sparkReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
		ExtentReports extentReport = new ExtentReports();
		extentReport.attachReporter(sparkReporter);
		return extentReport;
	}

	public static void logPass(String log) {
		Setup.extentTest.get().pass(MarkupHelper.createLabel(log, ExtentColor.GREEN));
	}

	public static void logFail(String log) {
		Setup.extentTest.get().fail(MarkupHelper.createLabel(log, ExtentColor.RED));
	}

	public static void logInfo(String log) {
		Setup.extentTest.get().info(MarkupHelper.createLabel(log, ExtentColor.GREY));
	}

	public static void logwarning(String log) {
		Setup.extentTest.get().warning(MarkupHelper.createLabel(log, ExtentColor.YELLOW));
	}
}
