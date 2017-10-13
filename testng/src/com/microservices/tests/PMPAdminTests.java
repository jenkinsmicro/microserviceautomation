package com.microservices.tests;

import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.microservices.pages.PMPLoginPage;
import com.microservices.pages.PMPPostLoginPage;
import com.microservices.pages.PartnerDetailsPage;
import com.microservices.utilities.ConfigReader;
import com.microservices.utilities.Driver;
import com.microservices.utilities.HelperMethods;
import com.microservices.utilities.TestBase;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.IOException;

import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

public class PMPAdminTests extends TestBase {

	protected static final Logger logger = Logger.getLogger(PMPAdminTests.class);
	PMPLoginPage pMPLoginPage;
	PMPPostLoginPage pMPPostLoginPage;
	PartnerDetailsPage partnerDetailsPage;
	ConfigReader configReader;
	HelperMethods helperMethods;
	public static String adminEmailId = null;
	public static String phoneNumber = null;
	public static String partnerEmailId=null;
	public static String partnerUserEmail=null;

	@BeforeMethod(groups = { "sanity" }, alwaysRun = true)
	public void setUPOtherObejcts() throws IOException {

		configReader = new ConfigReader();
		helperMethods = new HelperMethods();
		pMPLoginPage = new PMPLoginPage();
		pMPPostLoginPage = new PMPPostLoginPage();
		partnerDetailsPage=new PartnerDetailsPage();
		
	}

	@Test(priority = 1, groups = { "sanity" }, alwaysRun = true)
	public void verifyPMPPageLoaded() throws InterruptedException {

		Reporter.log("========PMP Page verification Test Case Started====");

		Thread.sleep(2000);
		pMPLoginPage.launchURL(configReader.getPMPUrl());
		pMPLoginPage.assertPMPPageLoaded();
	}

	@Test(priority = 2, groups = { "sanity" }, alwaysRun = true)
	public void verifyLogin() {
		Reporter.log("========Login Test Case Started====");
		
		pMPLoginPage.enterUserName(configReader.getPMPUsername());
		pMPLoginPage.enterPassword(configReader.getPMPPassword());
		pMPLoginPage.clickOnLogin();
		pMPPostLoginPage.assertPostLoginPageLoaded();
	}

	@Test(priority = 3, dependsOnMethods = "verifyLogin", groups = { "sanity" }, alwaysRun = true)
	public void verifyLogout() throws InterruptedException {
		Reporter.log("========Logout Test Case Started====");
		pMPPostLoginPage.clickOnLogout();
		pMPLoginPage.assertPMPPageLoaded();
	}

	@SuppressWarnings("static-access")
	@Test(priority = 4, groups = { "sanity" }, alwaysRun = true)
	public void verifyAdminCreation() throws InterruptedException {
		
		pMPLoginPage.enterUserName(configReader.getPMPUsername());
		pMPLoginPage.enterPassword(configReader.getPMPPassword());
		pMPLoginPage.clickOnLogin();
		pMPPostLoginPage.assertPostLoginPageLoaded();
		pMPPostLoginPage.clickOnAdminisrtatorTab();
		pMPPostLoginPage.assertAdminViewOpened();
		pMPPostLoginPage.clickOnNewAdminCreationButton();
		pMPPostLoginPage.assertNewAdminCreationForm();
		
		pMPPostLoginPage.enterAFirstName("AdminFirstName");
		pMPPostLoginPage.enterALastName("AdminLastName");
		
		adminEmailId = helperMethods.generateRandomText(8) + "@yopmail.com";
		phoneNumber = "+" + helperMethods.generatePhoneNumber(8);
	    pMPPostLoginPage.enterAUserName(adminEmailId);
		pMPPostLoginPage.enterAPhoneNumber(phoneNumber);
    	pMPPostLoginPage.selectAdminAccountStatus("Active");
		pMPPostLoginPage.clickOnCreateAdminButton();
		
		pMPPostLoginPage.assertAdminpageAfterCreation();
		//pMPPostLoginPage.assertAdminDetailsIntheTable();
	}

	//@Test(priority = 5, groups = { "sanity" }, alwaysRun = true)
	public void verifyAdminDeletion() throws InterruptedException {
		
		int pages=pMPPostLoginPage.findNoofPagesToSearchInAdmin();
		pMPPostLoginPage.deleteAdminAccount(pages, adminEmailId);
		//pMPPostLoginPage.verifyAccountDeletionHappened(pages, adminEmailId);
	}

	

	@SuppressWarnings("static-access")
	@Test(priority = 6, groups = { "sanity" }, alwaysRun = true)
	public void verifyPartnerCreation() throws InterruptedException {
		
		pMPPostLoginPage.clickOnPartnerTab();
		pMPPostLoginPage.assertPartnerTabLoaded();
		pMPPostLoginPage.clickOnCreatePartner();
		pMPPostLoginPage.assertPartnerCreationForm();
		pMPPostLoginPage.enterPartnerFirstName("PartnerFirstName");
		pMPPostLoginPage.enterPartnerDescription("Automation partner please ignore");
		pMPPostLoginPage.selectPartnerLanguage("English");  //need to update this
		pMPPostLoginPage.selectPartnerCountry("Russia");  //need to update this

		partnerEmailId = helperMethods.generateRandomText(8) + "@yopmail.com";
		phoneNumber = "+" + helperMethods.generatePhoneNumber(8);
		pMPPostLoginPage.enterPartnerEamilId(partnerEmailId);
		pMPPostLoginPage.enterPartnerPhoneNumber(phoneNumber);
		
		pMPPostLoginPage.enterPartnerUrl("www."+helperMethods.generateRandomText(8)+".com");
		pMPPostLoginPage.clickOnSubmittToCreatePartner();
		pMPPostLoginPage.assertPartnerCreated();

		int pages=pMPPostLoginPage.findNoofPagesToSearchInPartner();
		pMPPostLoginPage.verifyNewPartnerDetails(pages, partnerEmailId);
	
	}

	@SuppressWarnings("static-access")
	@Test(priority = 7, groups = { "sanity" }, alwaysRun = true)
	public void verifyPartnerUserCreation() throws InterruptedException {
		
		pMPPostLoginPage.clickOnPartnerUserCreationButton();
		partnerUserEmail = helperMethods.generateRandomText(8) + "@yopmail.com";
		pMPPostLoginPage.asertPartnerUserCreationForm();
		
		pMPPostLoginPage.enterPartnerUserFirstName("partneruserFirstName");
		pMPPostLoginPage.enterPartnerUserLastName("partneruserLastName");
		pMPPostLoginPage.enterPartnerUserUserName(partnerUserEmail);
		pMPPostLoginPage.enterPartnerUserPhoneNumber(phoneNumber);
		
		pMPPostLoginPage.selectPuserAccoutnStatus("Active");
		pMPPostLoginPage.selectPartnerUserRole("Partner User");
		
		pMPPostLoginPage.clickOnPUserCreationButton();
		pMPPostLoginPage.assertPartnerUserCreated();
		//pMPPostLoginPage.verifyPartnerUserCreated();
	}

	//@Test(priority = 8, groups = { "sanity" }, alwaysRun = true)
	public void verifyDeletePartnerUser() throws InterruptedException {

		
		pMPPostLoginPage.clickOnPartnerUserRecord();
		pMPPostLoginPage.assertPartnerUserDetailsOpened();
		pMPPostLoginPage.clickOnDeletePartnerUserButton();
		pMPPostLoginPage.verifyConfirmDeletePopup();
		pMPPostLoginPage.clickOnConfirmDelete();
		pMPPostLoginPage.verifyDeletionOFPartnerUserHappend();
		
		//pMPPostLoginPage.assertDeletionOFPartnerUserHappend();
		
	
	}

	@SuppressWarnings("static-access")
	//@Test(priority = 9, groups = { "sanity" }, alwaysRun = true)
	public void verifySingleSignOnCreation() throws InterruptedException {

		driver.findElement(By.xpath(".//*[@id='app']/section/div[2]/div[2]/div/div/div/ul/li[2]/a")).click();
		Reporter.log("Clicked on Single Sign On Link");
		driver.findElement(By.linkText("New client")).click();

		Reporter.log("Clicked on creat client button -NEW CLIENT");
		driver.findElement(By.name("clientName")).sendKeys(helperMethods.generateRandomText(5));
		driver.findElement(By.xpath(".//*[@id='app']/section/div[2]/div/div[2]/div[3]/div[1]/p/input"))
				.sendKeys("http://testurl.com");
		Reporter.log("Entered the urls into url field");

		driver.findElement(By.xpath(".//*[@id='app']/section/div[2]/div/div[2]/div[3]/div[1]/p/button")).click();
		Reporter.log("Clicked on url add  button");

		driver.findElement(By.xpath(".//*[@id='app']/section/div[2]/div/div[3]/div/div/p[1]/button")).click();
		Reporter.log("Clicked on SSO create button");
		Thread.sleep(5000);

		try {

			Assert.assertTrue(driver
					.findElement(By.xpath(
							".//*[@id='app']/section/div[2]/div[3]/div/div/div/div/div[2]/div/div[1]/div[2]/article"))
					.isDisplayed());
			Reporter.log("Created SSO account appeared in the SSO list. SSO creation happened Successfully");

		} catch (Exception e) {
			// TODO: handle exception
			Assert.fail("Something went wrong. SSO creation failed");
		}

	}

	// merhcnat creation to be completed
	//@Test(priority = 10, groups = { "sanity" }, alwaysRun = true)
	public void verifyMerchantCreation() {

		driver.findElement(By.xpath(".//*[@id='app']/section/div[2]/div[2]/div/div/div/ul/li[3]/a")).click();
		Reporter.log("Clicked on Merchant Information tab");

		driver.findElement(By.linkText("Register merchant")).click();
		Reporter.log("Clicked on Register merchant button");

		try {

			Assert.assertTrue(
					driver.findElement(By.xpath(".//*[@id='app']/section/div[2]/div/div[2]/div[1]/form/div[1]/p/input"))
							.isDisplayed());
			Assert.assertTrue(driver
					.findElement(By.xpath(".//*[@id='app']/section/div[2]/div/div[2]/div[1]/form/div[2]/p/textarea"))
					.isDisplayed());
			Assert.assertTrue(driver
					.findElement(By.xpath(".//*[@id='app']/section/div[2]/div/div[2]/div[1]/form/div[3]/p/span/select"))
					.isDisplayed());
			Assert.assertTrue(driver
					.findElement(
							By.xpath(".//*[@id='app']/section/div[2]/div/div[2]/div[3]/div/div/div/div/div[1]/p/input"))
					.isDisplayed());
			Assert.assertTrue(driver
					.findElement(
							By.xpath(".//*[@id='app']/section/div[2]/div/div[2]/div[3]/div/div/div/div/div[2]/p/input"))
					.isDisplayed());
			Assert.assertTrue(driver
					.findElement(
							By.xpath(".//*[@id='app']/section/div[2]/div/div[2]/div[3]/div/div/div/div/div[3]/p/input"))
					.isDisplayed());
			Assert.assertTrue(
					driver.findElement(By.xpath(".//*[@id='app']/section/div[2]/div/div[3]/div/div/p[1]/button"))
							.isDisplayed());

			Reporter.log(
					"Name, Description, Status, Url, userid, password are exists. Merchant regisrtation form opened successfully");

		} catch (Exception e) {
			// TODO: handle exception

			Assert.fail("Some of the Merchant form fields are does not exists. Merchant form NOT OPENED PROPERLY");
		}

		try {
			driver.findElement(By.xpath(".//*[@id='app']/section/div[2]/div/div[2]/div[1]/form/div[1]/p/input"))
					.sendKeys(HelperMethods.generateRandomText(5));
			Reporter.log("Entered name value");
			driver.findElement(By.xpath(".//*[@id='app']/section/div[2]/div/div[2]/div[1]/form/div[2]/p/textarea"))
					.sendKeys(HelperMethods.generateRandomText(10));
			Reporter.log("Entered Descrption value");

			driver.findElement(
					By.xpath(".//*[@id='app']/section/div[2]/div/div[2]/div[3]/div/div/div/div/div[1]/p/input"))
					.sendKeys("http://" + HelperMethods.generateRandomText(10) + ".com");
			Reporter.log("Entered url value");

			driver.findElement(
					By.xpath(".//*[@id='app']/section/div[2]/div/div[2]/div[3]/div/div/div/div/div[2]/p/input"))
					.sendKeys(HelperMethods.generateRandomText(6));
			Reporter.log("Entered userId value");

			driver.findElement(
					By.xpath(".//*[@id='app']/section/div[2]/div/div[2]/div[3]/div/div/div/div/div[3]/p/input"))
					.sendKeys(HelperMethods.generateRandomText(7));
			Reporter.log("Entered Password value");

			driver.findElement(By.xpath(".//*[@id='app']/section/div[2]/div/div[3]/div/div/p[1]/button")).click();
			Reporter.log("Clicked on Create button");

			Thread.sleep(4000);

			Assert.assertTrue(driver
					.findElement(By.xpath(
							".//*[@id='app']/section/div[2]/div[3]/div/div/div/div/div[2]/div/table/tbody/tr[1]/td[2]"))
					.isDisplayed());
			Reporter.log(
					"merhcant list has been updated. Table has been created. Seems to eb merchant creation Successful");

		} catch (Exception e) {
			// TODO: handle exception

			Assert.fail("Merchant creation FAILED. Something went wrong");
		}

	}

	@Test(priority = 11, groups = { "sanity" }, alwaysRun = true)
	public void verifyPartnerDeletion() throws InterruptedException {
		
		
		pMPPostLoginPage.clickONEditPartnerButton();
		pMPPostLoginPage.verifyPartnerDetailsAreOpened();
		pMPPostLoginPage.clickOnDeletePartnerButton();
		pMPPostLoginPage.assertPartnerDeleteWarningPopup();
		pMPPostLoginPage.clickONConfirmDeletePartner();
		pMPPostLoginPage.assertPartnerDeletionHappened();
		
			
	}

}
