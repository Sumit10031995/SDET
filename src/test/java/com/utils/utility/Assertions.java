package com.utils.utility;

import org.testng.Assert;

import com.report.ExtentReportManager;

public class Assertions extends ExtentReportManager {
	public static boolean assertEquals(String ErrorMessage, String actual, String expected) {
		if (actual.equals(expected)) {
			logPass("Actual And Expected Values Are Matching : Actual Text=\"" + actual + "\" and Expected text=\""
					+ expected + "\"");
			Assert.assertEquals(actual, expected);
		} else {
			logFail("Actual And Expected Values Are Not Matching : Actual Text=\"" + actual + "\" and Expected text=\""
					+ expected + "\"");
			Assert.assertEquals(actual, expected, ErrorMessage);

		}
		return (expected.equals(expected)) ? true : false;

	}

	public static boolean assertTrue(String ErrorMessage, String actual, String expected) {
		if (actual.equals(expected)) {
			logPass("Actual And Expected Values Are Matching : Actual Text=\"" + actual + "\" and Expected text=\""
					+ expected + "\"");
			Assert.assertTrue(actual.equals(expected));
		} else {
			logFail("Actual And Expected Values Are Not Matching : Actual Text=\"" + actual + "\" and Expected text=\""
					+ expected + "\"");
			Assert.assertTrue(expected.equals(expected));

		}
		return (expected.equals(expected)) ? true : false;

	}

	public static boolean assertTrue(String ErrorMessage, boolean isTrue) {
		if (isTrue) {
			logPass("Is Expected Equal To Actual?\"" + String.valueOf(isTrue) + "\"");
			Assert.assertEquals(ErrorMessage, isTrue);
		} else {
			logFail(ErrorMessage);
			Assert.assertEquals(ErrorMessage, "Is Expected Equal To Actual?\"" + String.valueOf(isTrue) + "\"");
		}
		return isTrue;

	}
}
