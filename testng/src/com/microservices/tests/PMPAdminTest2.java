package com.microservices.tests;

import org.testng.annotations.Test;

import com.microservices.pages.PMPLoginPage;
import com.microservices.pages.PMPPostLoginPage;
import com.microservices.utilities.ConfigReader;
import com.microservices.utilities.Driver;
import com.microservices.utilities.HelperMethods;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.IOException;

import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;

public class PMPAdminTest2 {

	public static WebDriver driver = null;
	PMPLoginPage pMPLoginPage;
	PMPPostLoginPage pMPPostLoginPage;
	ConfigReader configReader;
	HelperMethods helperMethods;
	String adminEmailId = null;
	String phoneNumber = null;

	@SuppressWarnings("static-access")
	@BeforeTest
	public void setUP() throws IOException {

		configReader = new ConfigReader();
		helperMethods = new HelperMethods();

		System.setProperty("webdriver.gecko.driver","C:\\Users\\kaligeti\\Documents\\Selenium_setup\\geckodriver-v0.13.0-win64\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get(configReader.getPMPUrl());

		driver.manage().window().maximize();
		// driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		pMPLoginPage = new PMPLoginPage();
		pMPPostLoginPage = new PMPPostLoginPage();
		adminEmailId = helperMethods.generateRandomText(8) + "@yopmail.com";
		phoneNumber = "+" + helperMethods.generatePhoneNumber(8);

	}

	@Test(priority = 1)
	public void verifyPMPPageLoaded() throws InterruptedException {

		Reporter.log("========PMP Page verification Test Case Started====");

		Thread.sleep(2000);

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

	@Test(priority = 2)
	public void verifyLogin() {
		Reporter.log("========Login Test Case Started====");

		try {
			driver.findElement(PMPLoginPage.BY_USERNAME).sendKeys(configReader.getPMPUsername());
			Reporter.log("Entered the Username as :" + configReader.getPMPUsername());
			driver.findElement(PMPLoginPage.BY_PASSWORD).sendKeys(configReader.getPMPPassword());
			Reporter.log("Entered the Password :" + configReader.getPMPPassword());
			driver.findElement(PMPLoginPage.BY_LOGIN_BTN).click();
			Reporter.log("Clicked on Submitt button");
			Thread.sleep(5000);
			String welcomeText = Driver.driver
					.findElement(By.cssSelector("#app > section > div:nth-of-type(2) > div > div > div > h1"))
					.getText();
			Assert.assertEquals(welcomeText, "Welcome to VEON Partner Management");
			Reporter.log("Welcome Text appeared as: " + welcomeText);
			String welcomeText1 = Driver.driver
					.findElement(By.cssSelector("#app > section > div:nth-of-type(2) > div > div > div > p")).getText();
			Assert.assertEquals(welcomeText1, "Please select your action in the header.");
			Reporter.log("Welcome Text appeared as: " + welcomeText1);

		} catch (Exception e) {
			// TODO: handle exception
			Reporter.log("Somethig went wrong. Login Page not happened. LOGIN FAILED");
			Assert.fail();

		}
	}

	@Test(priority = 3, dependsOnMethods = "verifyLogin")
	public void verifyLogout() {
		Reporter.log("========Logout Test Case Started====");

		try {

			driver.findElement(By.linkText("Logout")).click();
			Reporter.log("Clicked on Logout Button");
			Thread.sleep(2000);
			Assert.assertTrue(driver.findElement(PMPLoginPage.BY_USERNAME).isDisplayed());
			Assert.assertTrue(driver.findElement(PMPLoginPage.BY_PASSWORD).isDisplayed());
			Assert.assertTrue(driver.findElement(PMPLoginPage.BY_LOGIN_BTN).isDisplayed());
			Reporter.log("Username, Password, Login button are diplayed properly");
			Reporter.log("PMP Login Page Loaded Successfully. LOGOUT happened Successfully");

		} catch (Exception e) {
			// TODO: handle exception
			Reporter.log("Somethig went wrong. Login Page not happened. LOGOUT FAILED");
			Assert.fail();
		}

	}

	@SuppressWarnings("static-access")
	@Test(priority = 4)
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
			Reporter.log("Adminisrtaor, Partners tabs are prsent");
			driver.findElement(pMPPostLoginPage.BY_ADMIN).click();
			Reporter.log("Clicked on Adminisrtaot tab");

			Assert.assertTrue(driver.findElement(pMPPostLoginPage.BY_NEW_ADMIN).isDisplayed());
			Reporter.log("New Administrator tab opened successfully");

			driver.findElement(pMPPostLoginPage.BY_NEW_ADMIN).click();
			Reporter.log("Clicked on New Administrator tab ");
			Thread.sleep(1000);
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

	// @Test(priority = 5)
	@SuppressWarnings("static-access")
	public void verifyAdminDeletion() throws InterruptedException {

		Assert.assertTrue(driver.findElement(pMPPostLoginPage.BY_ADMIN).isDisplayed());
		Assert.assertTrue(driver.findElement(pMPPostLoginPage.BY_PARTNER).isDisplayed());
		Reporter.log("Adminisrtaor, Partners tabs are prsent");
		driver.findElement(pMPPostLoginPage.BY_ADMIN).click();
		Reporter.log("Clicked on Adminisrtaot tab");
		Thread.sleep(4000);

		java.util.List<WebElement> pagination = driver.findElements(By.xpath("//div[@class='pagination']//a"));
		int s = pagination.size();

		Reporter.log("No fo pages are :" + s);

		for (int i = 0; i <= s; i++) {

			WebElement htmltable = driver.findElement(By.xpath("//*[@id='app']/section/div[2]/div[2]/div[1]/div"));

			java.util.List<WebElement> rows = htmltable.findElements(By.tagName("tr"));
			Reporter.log("List of rows size: " + rows.size());
			for (int rnum = 0; rnum < rows.size(); rnum++)

			{

				java.util.List<WebElement> columns = rows.get(rnum).findElements(By.tagName("td"));

				for (int cnum = 0; cnum < columns.size(); cnum++)

				{
					Reporter.log(columns.get(cnum).getText());

					if (columns.get(cnum).getText().equals(adminEmailId)) {

						String email_ele_xpath = "//*[@id='app']/section/div[2]/div[2]/div[1]/div/div/div/table/tbody/tr["
								+ rnum + "]/td[2]";

						driver.findElement(By.xpath(email_ele_xpath)).click();

						try {

							Thread.sleep(5000);

							Assert.assertTrue(
									driver.findElement(By.xpath("//*[@id='app']/section/div[2]/div/div[2]/div[2]/div"))
											.isDisplayed());
							Reporter.log("Delete User button displayed");
							driver.findElement(By.xpath("//*[@id='app']/section/div[2]/div/div[2]/div[2]/div")).click();
							Reporter.log("Clicked on Delete User button");
							Thread.sleep(5000);

						} catch (Exception e) {
							// TODO: handle exception
							Reporter.log("Something went wrong");
							Assert.fail();

						}

						break;

					}

				}
			}

			driver.findElement(
					By.xpath(".//*[@id='app']/section/div[2]/div[2]/div[1]/div/div/div/div[2]/div[2]/div/div/ul/li["
							+ (i + 1) + "]/a"))
					.click();

		}
	}

	@SuppressWarnings("static-access")
	@Test(priority = 6)
	public void verifyPartnerCreation() throws InterruptedException {

		driver.findElement(pMPPostLoginPage.BY_PARTNER).click();
		Reporter.log("Clicked on Partner tab");
		Thread.sleep(4000);
		driver.findElement(pMPPostLoginPage.BY_CREATE_PARTNER).click();
		Reporter.log("Clicked on 'New Partner' button  ");

		Thread.sleep(1000);

		try {

			Assert.assertTrue(driver.findElement(pMPPostLoginPage.BY_P_NAME).isDisplayed());
			Assert.assertTrue(driver.findElement(pMPPostLoginPage.BY_P_DESC).isDisplayed());
			Assert.assertTrue(driver.findElement(pMPPostLoginPage.BY_P_EMAIL).isDisplayed());
			Assert.assertTrue(driver.findElement(pMPPostLoginPage.BY_P_CREATE_BTN).isDisplayed());
			Assert.assertTrue(driver.findElement(pMPPostLoginPage.BY_P_URL).isDisplayed());
			Assert.assertTrue(driver.findElement(pMPPostLoginPage.BY_P_LANUAGE).isDisplayed());
			Reporter.log("All the Partner creations fields are opened properly");

		} catch (Exception e) {
			// TODO: handle exception

			Reporter.log("Some of the Partner fields does nto exists. PARTNER CREATION FORM NOT OPENED .FAILURE");

		}

		try {

			driver.findElement(pMPPostLoginPage.BY_P_NAME).sendKeys("AutomationFirstname");
			Reporter.log("Entered First name ");

			driver.findElement(pMPPostLoginPage.BY_P_DESC).sendKeys("Automation partner pls ingnore");
			Reporter.log("Entered Description ");

			driver.findElement(pMPPostLoginPage.BY_P_LANUAGE).click();
			Reporter.log("Clcked on Language drop down ");

			Thread.sleep(1000);
			Assert.assertTrue(driver.findElement(pMPPostLoginPage.BY_P_LANUAGE_MENU).isDisplayed());
			Reporter.log("Language list opened ");

			driver.findElement(By.xpath(".//*[@id='app']/section/div[2]/div/div[2]/div/form/div[3]/p/div/ul/li[52]/a"))
					.click();
			Reporter.log("Selected ENGLISH langauge ");

			driver.findElement(pMPPostLoginPage.BY_P_EMAIL).sendKeys(adminEmailId);
			Reporter.log("Entered email adress");

			driver.findElement(pMPPostLoginPage.BY_P_PHONE).sendKeys(phoneNumber);
			Reporter.log("Entered the Phone Number ");

			driver.findElement(pMPPostLoginPage.BY_P_URL).sendKeys("www.testurl.com");
			Reporter.log("Entered URL ");

			driver.findElement(pMPPostLoginPage.BY_P_CREATE_BTN).click();

			Reporter.log("Clicked on Create partner submitt button");
			Thread.sleep(5000);
			Assert.assertTrue(driver.findElement(pMPPostLoginPage.BY_CREATE_PARTNER).isDisplayed());
			Reporter.log("New Partner tab appreared. New partner creation succssful ");

		} catch (Exception e) {
			// TODO: handle exception
			Reporter.log("Something went wrong while creating the Partner");
			Assert.fail();

		}

	}

	@SuppressWarnings("static-access")
	@Test(priority = 7)
	public void verifyPartnerUserCreation() throws InterruptedException {

		
		Thread.sleep(2000);

		driver.findElement(By.xpath(".//*[@id='app']/section/div[2]/div/div[2]/div/div[1]/div/table/tbody/tr[1]/td[2]"))
				.click();
		Reporter.log("Clicked on partner details");

		Thread.sleep(4000);
		try {

			Assert.assertTrue(
					driver.findElement(By.xpath("//*[@id='app']/section/div[2]/div[1]/div/div/div[2]/div/div"))
							.isDisplayed());
			Reporter.log("Partnerr card detials shown");

			Assert.assertTrue(
					driver.findElement(By.xpath("//*[@id='app']/section/div[2]/div[2]/div/div/div/ul/li[1]/a"))
							.isDisplayed());
			Assert.assertTrue(
					driver.findElement(By.xpath("//*[@id='app']/section/div[2]/div[2]/div/div/div/ul/li[2]/a"))
							.isDisplayed());
			Assert.assertTrue(
					driver.findElement(By.xpath("//*[@id='app']/section/div[2]/div[2]/div/div/div/ul/li[3]/a"))
							.isDisplayed());
			Assert.assertTrue(driver.findElement(By.linkText("New user")).isDisplayed());
			Reporter.log(
					"Users, Single sign on, Merhcant informaiton tabs are present. New user creation button exists");
			Thread.sleep(4000);

		} catch (AssertionError e) {
			// TODO: handle exception

			Reporter.log("Something went wrong. Some of the fields are not visible. Falilure");
			Assert.fail();
		}

		try {

			String pEmail = helperMethods.generateRandomText(8) + "@yopmail.com";
			driver.findElement(pMPPostLoginPage.BY_CREATE_PARTNER_USER).click();
			Reporter.log("Clicked on New user button");
			Thread.sleep(1000);

			driver.findElement(pMPPostLoginPage.BY_PU_FNAME).sendKeys("partneruserFirst");
			Reporter.log("Entered Partner user Firstname");
			driver.findElement(pMPPostLoginPage.BY_PU_LNAME).sendKeys("partneruserLast");
			Reporter.log("Entered Partner user LastName");
			driver.findElement(pMPPostLoginPage.BY_PU_USERNAME).sendKeys(pEmail);
			Reporter.log("Entered Partner user Username/emailid");
			driver.findElement(pMPPostLoginPage.BY_PU_PHONE).sendKeys(phoneNumber);
			Reporter.log("Entered Partner user PhonenUmber");
			driver.findElement(pMPPostLoginPage.BY_PU_CREATE_BTN).click();
			Reporter.log("Clicked on Create Button");
			Thread.sleep(10000);

			Assert.assertTrue(Driver.driver
					.findElement(By.xpath(
							".//*[@id='app']/section/div[2]/div[3]/div/div/div/div[2]/div[1]/div/table/tbody/tr/td[2]"))
					.isDisplayed());

			Reporter.log("New user record exists in the table");

		} catch (Exception e) {
			// TODO: handle exception
			Reporter.log("Something went worng. Partner User creation not happened proeprly");
			Assert.fail();
		}

	}

	@SuppressWarnings("static-access")
	@Test(priority = 8)
	public void verifyDeletePartnerUser() throws InterruptedException {

		
		// driver.findElement(By.linkText("Edit")).click();
		
		try {
			driver.findElement(
					By.xpath(".//*[@id='app']/section/div[2]/div[3]/div/div/div/div[2]/div[1]/div/table/tbody/tr/td[2]"))
					.click();
			Reporter.log("Clicked on the Partner user record to open it");
			driver.findElement(By.xpath(".//*[@id='app']/section/div[2]/div/div[2]/div[2]/div")).click();
			Reporter.log("Clicked on 'Delete Partner user' button");
			driver.findElement(By.xpath(".//*[@id='app']/section/div[2]/div/div[1]/div/div/div[2]/footer/a[2]")).click();
			Reporter.log("Clicked on 'Delete' button on the Delete confirmation popup");
			Thread.sleep(4000);
			Assert.assertTrue(driver.findElement(pMPPostLoginPage.BY_CREATE_PARTNER_USER).isDisplayed());
			Reporter.log("Deletetion of Partner User is Successfull");

		} catch (Exception e) {
			// TODO: handle exception
			
			Reporter.log("Partner deletion FAILED. Some thing went wrong");
			Assert.fail();
		}
		
		
	}
        @SuppressWarnings("static-access")
		@Test(priority=9)
        public void verifySingleSignOnCreation() throws InterruptedException{
        	
        	driver.findElement(By.xpath(".//*[@id='app']/section/div[2]/div[2]/div/div/div/ul/li[2]/a")).click();
        	Reporter.log("Clicked on Single Sign On Link");
        	driver.findElement(By.linkText("New client")).click();
        	
        	
        	
        	Reporter.log("Clicked on creat client button -NEW CLIENT");
        	driver.findElement(By.name("clientName")).sendKeys(helperMethods.generateRandomText(5));
        	driver.findElement(By.xpath(".//*[@id='app']/section/div[2]/div/div[2]/div[3]/div[1]/p/input")).sendKeys("http://testurl.com");
        	Reporter.log("Entered the urls into url field");
        	
        	driver.findElement(By.xpath(".//*[@id='app']/section/div[2]/div/div[2]/div[3]/div[1]/p/button")).click();
        	Reporter.log("Clicked on url add  button");
        	
        	driver.findElement(By.xpath(".//*[@id='app']/section/div[2]/div/div[3]/div/div/p[1]/button")).click();
        	Reporter.log("Clicked on SSO create button");
        	Thread.sleep(4000);

        	
        	try {
				
        		Assert.assertTrue(driver.findElement(By.xpath(".//*[@id='app']/section/div[2]/div[3]/div/div/div/div/div[2]/div/div[1]/div[2]/article")).isDisplayed());
        		Reporter.log("Created SSO account appeared in the SSO list. SSO creation happened Successfully");
            	
			} catch (Exception e) {
				// TODO: handle exception
				Assert.fail("Something went wrong. SSO creation failed");
			}
        	

		
		
	}
        
    @Test(priority=10)
    public void verifyMerchantCreation() {
    	
    	
    	driver.findElement(By.xpath(".//*[@id='app']/section/div[2]/div[2]/div/div/div/ul/li[3]/a")).click();
    	Reporter.log("Clicked on Merchant Information tab");
    	
    	
    	
    }
        
       
        
	@Test(priority = 11)
	public void verifyPartnerDeletion() throws InterruptedException {
		try {
			driver.findElement(By.linkText("Edit")).click();
			Reporter.log("Clicked on 'Edit' partner field");
			driver.findElement(By.xpath(".//*[@id='app']/section/div[2]/div/div[2]/div[2]/div")).click();
			Reporter.log("Clicked on 'Delete Partner' button");
			driver.findElement(By.xpath(".//*[@id='app']/section/div[2]/div/div[1]/div/div/div[2]/footer/a[2]")).click();
			Reporter.log("Clicked on 'Delete' button on the Delete confirmation popup");
			Thread.sleep(4000);
			Assert.assertTrue(driver.findElement(pMPPostLoginPage.BY_CREATE_PARTNER).isDisplayed());
			Reporter.log("Deletetion of Partner User is Successfull");
		} catch (Exception e) {
			// TODO: handle exception
			Reporter.log("Partner deletion FAILED. Some thing went wrong");
			Assert.fail();
		}
	}

	
	@AfterTest
	public void close() {
		driver.quit();
	}
	
	

}
