/*
 * Copyright SocialTwist Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of SocialTwist.
 * You shall not disclose such confidential information and shall use it only
 * in accordance with the terms of the source code license agreement you
 * entered into with SocialTwist.
 */

package com.microservices.listners;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import com.microservices.tests.PMPAdminTests;
import com.microservices.utilities.Driver;
import com.microservices.utilities.TestBase;

public class CustomTestListener extends TestListenerAdapter {
	protected static final Logger logger = Logger.getLogger(CustomTestListener.class);
	// protected final static TafSelenium sel = TafSelenium.getSharedInstance();

	@Override
	public void onTestFailure(ITestResult tr) {

		File scrFile = ((TakesScreenshot) TestBase.driver).getScreenshotAs(OutputType.FILE);
		// Now you can do whatever you need to do with it, for example copy somewhere
		try {
			FileUtils.copyFile(scrFile, new File(
					"C:\\Users\\kaligeti\\Documents\\eclipse-workspace\\testng\\test-output\\screenshots\\"+tr.getName()+getDateTime()+".png"));
			Reporter.log("Screenshot has been taken with name : "+tr.getName()+getDateTime()+".png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Reporter.log("Error while taking the Screenshot");
		}

	}

	public static String getDateTime() {
		// Milliseconds are requiredto keep file names unique as Selenium runs
		// can be very fast.
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		Date date = new Date();
		String dateExtension = dateFormat.format(date);
		return dateExtension;
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("callig on OnFinish ...!!!");
		Iterator<ITestResult> failedTestCases = context.getFailedTests().getAllResults().iterator();
		Iterator<ITestResult> passedTestCases = context.getPassedTests().getAllResults().iterator();

		// remove duplicate entries from failed testcases.
		Set<Object> failedidentifierSet = new HashSet<Object>();
		Set<Object> passedidentifierSet = new HashSet<Object>();

		while (passedTestCases.hasNext()) {
			ITestResult passedTc = passedTestCases.next();
			ITestNGMethod method = passedTc.getMethod();
			System.out.println("passed method :: " + method);
			StringBuilder sb = new StringBuilder(method.getMethodName());
			Object[] parameters = passedTc.getParameters();
			if (parameters != null) {
				for (Object parameter : parameters) {
					if (parameter != null) {
						// assuming string parameter
						sb.append(parameter.toString());
					}
				}
			}
			String identifier = sb.toString();
			passedidentifierSet.add(identifier);
		}

		while (failedTestCases.hasNext()) {
			ITestResult failedTestCase = failedTestCases.next();
			ITestNGMethod method = failedTestCase.getMethod();
			StringBuilder sb = new StringBuilder(method.getMethodName());
			Object[] parameters = failedTestCase.getParameters();
			if (parameters != null) {
				for (Object parameter : parameters) {
					if (parameter != null) {
						// assuming string parameter
						sb.append(parameter.toString());
					}
				}
			}
			String identifier = sb.toString();
			if (passedidentifierSet.contains(identifier) || failedidentifierSet.contains(identifier)) {
				failedTestCases.remove();
			} else {
				failedidentifierSet.add(identifier);
			}
		}
	}

}
