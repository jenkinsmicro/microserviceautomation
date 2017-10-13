package com.microservices.tests;

import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.microservices.pages.PartnerPortalLoginPage;
import com.microservices.utilities.ConfigReader;
import com.microservices.utilities.HelperMethods;
import com.microservices.utilities.TestBase;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.io.IOException;

import org.testng.Reporter;

@Listeners(com.microservices.listners.CustomTestListener.class)
public class PartnerPortalTests extends TestBase {

	 protected static final Logger logger = Logger
	 .getLogger(PartnerPortalTests.class);
	
	ConfigReader configReader;
	HelperMethods helperMethods;
	PartnerPortalLoginPage ppTests;
	public static String adminEmailId = null;
	public static String phoneNumber = null;

	@BeforeMethod(groups = { "sanity" }, alwaysRun = true)
	public void setUPOtherObejcts() throws IOException {
	
		configReader = new ConfigReader();
		helperMethods = new HelperMethods();
		ppTests =new PartnerPortalLoginPage();
		

	}

	@Test(priority = 201, groups = { "sanity" }, alwaysRun = true)
	public void verifyPPpageLoaded() throws InterruptedException {

		Reporter.log("========PMP Page verification Test Case Started====");
		ppTests.launchPartnerPortal(configReader.getPPUrl());
		ppTests.clickOnSignInDropDown();
		ppTests.assertPageLoaded();
	}

	@Test(priority = 202, groups = { "sanity" }, alwaysRun = true)
	public void verifyLogin() {
		logger.info("========Login Test Case Started====");
		
		ppTests.enterUserName(configReader.getPPUsername());
		ppTests.enterPassword(configReader.getPPPassword());
		ppTests.clickOnLogIn();
		ppTests.assertPostLoginScreen();
		
	}
	
	@Test(priority = 203, dependsOnMethods = "verifyLogin", groups = { "sanity" }, alwaysRun = true)
	public void verifyLogout() throws InterruptedException {
		Reporter.log("========Logout Test Case Started====");
		
		ppTests.clickOnLogout();
		ppTests.assertLogoutDropDownLoaded();
		ppTests.clickOnLogoutLink();
		ppTests.clickOnSignInDropDown();
		ppTests.assertPageLoaded();

	
		
	}

}