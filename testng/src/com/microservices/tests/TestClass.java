package com.microservices.tests;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.microservices.pages.PMPLoginPage;
import com.microservices.pages.PMPPostLoginPage;
import com.microservices.utilities.ConfigReader;
import com.microservices.utilities.HelperMethods;
import com.microservices.utilities.TestBase;

public class TestClass extends TestBase {
	PMPLoginPage pMPLoginPage;
	PMPPostLoginPage pMPPostLoginPage;
	ConfigReader configReader;
	HelperMethods helperMethods;
	public static String adminEmailId = null;
	public static String phoneNumber = null;

	@SuppressWarnings("static-access")
	@BeforeMethod(groups = { "sanity" })
	public void setUPOtherObejcts() throws IOException {

		configReader = new ConfigReader();
		helperMethods = new HelperMethods();
		pMPLoginPage = new PMPLoginPage();
		pMPPostLoginPage = new PMPPostLoginPage();

	}

	@Test(priority = 1, groups = { "sanity" })
	public void verifyPMPPageLoaded() throws InterruptedException {
		Reporter.log("========PMP Page verification Test Case Started====");
		Thread.sleep(2000);
		driver.get(configReader.getPMPUrl());
		try {
			Assert.assertTrue(driver.findElement(PMPLoginPage.BY_USERNAME).isDisplayed());
			Assert.assertTrue(driver.findElement(PMPLoginPage.BY_PASSWORD).isDisplayed());
			Assert.assertTrue(driver.findElement(PMPLoginPage.BY_LOGIN_BTN).isDisplayed());
			Reporter.log("Username, Password, Login button are diplayed properly");
			Reporter.log("PMP Login Page Loaded Successfully");
		} catch (Exception e) {
			// TODO: handle exception
			Reporter.log("Some of the fields are not visisble. Login Page nto loaded successfully");
			Assert.fail();
		}
	}

	@SuppressWarnings("static-access")
	@Test(priority = 4, groups = { "sanity" })
	public void verifyAdminCreation() throws InterruptedException {

		try {
			driver.findElement(PMPLoginPage.BY_USERNAME).sendKeys(configReader.getPMPUsername());
			Reporter.log("Entered the Username as :" + configReader.getPMPUsername());
			driver.findElement(PMPLoginPage.BY_PASSWORD).sendKeys(configReader.getPMPPassword());
			Reporter.log("Entered the Password :" + configReader.getPMPPassword());
			driver.findElement(PMPLoginPage.BY_LOGIN_BTN).click();
			Reporter.log("Clicked on Submitt button");
			Thread.sleep(5000);
		} catch (Exception e) {
			// TODO: handle exception
			Reporter.log("Somethig went wrong. Login Page not happened. LOGIN FAILED");
			Assert.fail();
		}

		try {

			Assert.assertTrue(driver.findElement(pMPPostLoginPage.BY_ADMIN).isDisplayed());
			Assert.assertTrue(driver.findElement(pMPPostLoginPage.BY_PARTNER).isDisplayed());
			Reporter.log("Adminisrtaor, Partners tabs are present");
			driver.findElement(pMPPostLoginPage.BY_ADMIN).click();
			Reporter.log("Clicked on Adminisrtaot tab");

			Assert.assertTrue(driver.findElement(pMPPostLoginPage.BY_NEW_ADMIN).isDisplayed());
			Reporter.log("New Administrator tab opened successfully");

			driver.findElement(pMPPostLoginPage.BY_NEW_ADMIN).sendKeys(Keys.ENTER);
			;
			Reporter.log("Clicked on New Administrator tab ");
			Thread.sleep(5000);
			Assert.assertTrue(driver.findElement(pMPPostLoginPage.BY_FIRST_NAME).isDisplayed());
			Reporter.log("Firstname field exists ");
			Assert.assertTrue(driver.findElement(pMPPostLoginPage.BY_LAST_NAME).isDisplayed());
			Reporter.log("Lastname field exists ");
			Assert.assertTrue(driver.findElement(pMPPostLoginPage.BY_USER_NAME).isDisplayed());
			Reporter.log("Username field exists ");
			Assert.assertTrue(driver.findElement(pMPPostLoginPage.BY_PHONE).isDisplayed());
			Reporter.log("Phone field exists ");
			Assert.assertTrue(driver.findElement(pMPPostLoginPage.BY_STATUS).isDisplayed());
			Reporter.log("Status field exists ");

		} catch (AssertionError e) {
			// TODO: handle exception
			Reporter.log("Something went wrong. New Admin Creation FORM not displayed properly");
			Assert.fail();
		}

		// Filling proper data and submitting admin creation

		try {

			driver.findElement(pMPPostLoginPage.BY_FIRST_NAME).sendKeys("FirtstnameautomationAdmin");
			Reporter.log("Entered the text into Firstname field  ");
			driver.findElement(pMPPostLoginPage.BY_LAST_NAME).sendKeys("LastanmeautomationAdmin");
			Reporter.log("Entered Lastname field exists ");

			adminEmailId = helperMethods.generateRandomText(8) + "@yopmail.com";
			phoneNumber = "+" + helperMethods.generatePhoneNumber(8);

			driver.findElement(pMPPostLoginPage.BY_USER_NAME).sendKeys(adminEmailId);
			Reporter.log("Entered Username as : " + adminEmailId);
			driver.findElement(pMPPostLoginPage.BY_PHONE).sendKeys(phoneNumber);
			Reporter.log("Entered Phone field exists ");
			Assert.assertTrue(driver.findElement(pMPPostLoginPage.BY_STATUS).isDisplayed());
			Reporter.log("Status field exists ");
			driver.findElement(pMPPostLoginPage.BY_CREATE_ADMIN).click();
			Reporter.log("Clicked on Create button");
			Thread.sleep(10000);
			Assert.assertTrue(driver.findElement(pMPPostLoginPage.BY_NEW_ADMIN).isDisplayed());
			Reporter.log("New Administrator tab appeared");
			Reporter.log("Admin creation happened Successfully");
		} catch (Exception e) {
			// TODO: handle exception
			Reporter.log("Something went wrong. Creation oF Admin user failed");
			Assert.fail();
		}
	}

	@Test(priority = 5, groups = { "sanity" })
	@SuppressWarnings("static-access")
	public void verifyAdminDeletion() throws InterruptedException {

		
		java.util.List<WebElement> pagination = driver.findElements(By.xpath("//div[@class='pagination']//a"));
		int s = pagination.size() - 2;

		Reporter.log("No fo pages are :" + s);
		//outerloop:
		for (int i = 1; i <= s; i++) {
			WebElement htmltable = driver.findElement(By.xpath("//*[@id='app']/section/div[2]/div[2]/div[1]/div"));
			java.util.List<WebElement> rows = htmltable.findElements(By.tagName("tr"));
			Reporter.log("List of rows size: " + rows.size());
			Reporter.log("Emailid to be searched in the page" + "" + "" + "   :" + adminEmailId);

			for (int rnum = 0; rnum < rows.size(); rnum++) {
				java.util.List<WebElement> columns = rows.get(rnum).findElements(By.tagName("td"));
				for (int cnum = 0; cnum < columns.size(); cnum++) {
					// Reporter.log(columns.get(cnum).getText());

					if (columns.get(cnum).getText().equals(adminEmailId)) {
						Reporter.log(
								"Found the emaild id at :" + columns.get(cnum).getText() + ", " + rnum + ", " + cnum);
						String email_ele_xpath = "//*[@id='app']/section/div[2]/div[2]/div/div/div/div/div[2]/table/tbody/tr["
								+ rnum + "]/td[2]";
						driver.findElement(By.xpath(email_ele_xpath)).click();
						
						Reporter.log("Clicked on the admin record to delete");
						try {
							Thread.sleep(5000);
							Assert.assertTrue(driver.findElement(By.id("delete")).isDisplayed());
							Reporter.log("Delete User button displayed");
							driver.findElement(By.id("delete")).click();
							Reporter.log("Clicked on Delete User button");

							driver.findElement(
									By.xpath(".//*[@id='app']/section/div[2]/div/div[1]/div/div/div[2]/footer/a[2]"))
									.click();
							Reporter.log("Clicked on OK on delete confirmaiton screen");
							Thread.sleep(5000);
							break; //outerloop;

						} catch (Exception e) {
							// TODO: handle exception
							Reporter.log("Something went wrong");
							Assert.fail();
						}
						

					}
					
					
				}
				/*if(flag==1)
					break;*/
			}

			driver.findElement(
					By.xpath(".//*[@id='app']/section/div[2]/div[2]/div/div/div/div/div[3]/div[2]/div/div/ul/li["
							+ (i + 1) + "]/a"))
					.click();
			Reporter.log("Clicked on the page number : " + (i + 1));

		}
	}

}
