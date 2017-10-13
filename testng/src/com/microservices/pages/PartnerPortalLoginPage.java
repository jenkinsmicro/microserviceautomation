package com.microservices.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.microservices.utilities.TestBase;

public class PartnerPortalLoginPage extends TestBase {

	public static final By BY_SIGNIN_DROP_DOWN = By.linkText("Sign in");
	public static final By BY_PP_USERNAME = By.id("email");
	public static final By BY_PP_PASSWORD = By.id("password");
	public static final By BY_PP_LOGIN_BTN = By.id("login");

	public static final By BY_PP_HOME = By.linkText("Home");
	public static final By BY_PP_SEGMENTEDITOR = By.linkText("Segment Editor");
	public static final By BY_PP_REPORTS = By.linkText("Reports");
	public static final By BY_PP_SETTINGS = By.linkText("Settings");
	public static final By BY_PP_LOGOUT_LINK = By.linkText("Log out");
	public static final By BY_PP_LOGOUT_DIV=By.xpath(".//*[@id='sessionDropdown']/div[2]/div");
	public static final By BY_PP_LOGOUT=By.id("logout");
	public static final By BY_PP_RESETPASSWORD=By.id("reset-password");

   
	public void launchPartnerPortal(String url) throws InterruptedException {
		Thread.sleep(2000);
		driver.get(url);

	}

	public void clickOnSignInDropDown() throws InterruptedException {

		driver.findElement(BY_SIGNIN_DROP_DOWN).click();
		Reporter.log("Clicked on Sign in dropdwon");
		//Thread.sleep(2000);
		

		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(BY_PP_USERNAME));
		
	}

	public void assertPageLoaded() {
		// TODO Auto-generated method stub
		try {
			Assert.assertTrue(driver.findElement(BY_PP_USERNAME).isDisplayed());
			Assert.assertTrue(driver.findElement(BY_PP_PASSWORD).isDisplayed());
			Assert.assertTrue(driver.findElement(BY_PP_LOGIN_BTN).isDisplayed());
			Reporter.log("Username, Password, Login button are diplayed properly");
			Reporter.log("Partner Portal Login Page Loaded Successfully");

		} catch (Exception e) {
			// TODO: handle exception
			Reporter.log("Some of the fields are not visible. Login Page NOT loaded successfully");
			Assert.fail();
		}
	}

	public void enterUserName(String ppUsername) {
		// TODO Auto-generated method stub
		try {
			driver.findElement(BY_PP_USERNAME).sendKeys(ppUsername);
			Reporter.log("Entered the Username as :" + ppUsername);
		} catch (Exception e) {
			Reporter.log("Something went wrong while submitting loging detials.");
			Assert.fail();
		}

	}

	public void enterPassword(String ppPassword) {
		try {
			driver.findElement(BY_PP_PASSWORD).sendKeys(ppPassword);
			Reporter.log("Entered the Password :" + ppPassword);

		} catch (Exception e) {
			Reporter.log("Something went wrong while submitting loging detials.");
			Assert.fail();

		}

	}

	public void clickOnLogIn() {

		try {

			driver.findElement(BY_PP_LOGIN_BTN).click();
			Reporter.log("Clicked on Submitt button");
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(BY_PP_HOME));

		} catch (Exception e) {
			// TODO: handle exception
			Reporter.log("Something went wrong while submitting loging detials.");
			Assert.fail();
		}

		// Thread.sleep(5000);

	}

	public void assertPostLoginScreen() {
		// TODO Auto-generated method stub

		try {

			Assert.assertTrue(driver.findElement(BY_PP_HOME).isDisplayed());
			Assert.assertTrue(driver.findElement(BY_PP_HOME).isDisplayed());
			Assert.assertTrue(driver.findElement(BY_PP_HOME).isDisplayed());

			Reporter.log("Home, Segment Editor, Settings tabs appeared. Login into Partner portal is Successful ");

		} catch (Exception e) {
			// TODO: handle exception
			Reporter.log("Somethig went wrong. Login Page not happened. LOGIN FAILED");
			Assert.fail();

		}

	}

	public void clickOnLogout() {
		// TODO Auto-generated method stub
		try {

			driver.findElement(BY_PP_LOGOUT_LINK).click();
			Reporter.log("Clicked on Logout drop down ");
			// Thread.sleep(1000);

			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout")));

		} catch (Exception e) {
			// TODO: handle exception
			Reporter.log("Somethig went wrong. Logout Page not loaded properly. LOGOUT FAILED");
			Assert.fail();

		}

	}

	public void assertLogoutDropDownLoaded() {
		// TODO Auto-generated method stub

		try {

			Assert.assertTrue(driver.findElement(BY_PP_LOGOUT_DIV).isDisplayed());
			Assert.assertTrue(driver.findElement(BY_PP_LOGOUT).isDisplayed());
			Assert.assertTrue(driver.findElement(BY_PP_RESETPASSWORD).isDisplayed());
			Reporter.log("Drop down section opened Seccessfully with Logout and Reset password links");

		} catch (Exception e) {
			// TODO: handle exception
			Reporter.log("Somethig went wrong. Logout Page not loaded properly. LOGOUT FAILED");
			Assert.fail();

		}

	}

	public void clickOnLogoutLink() {
		// TODO Auto-generated method stub
		try {
			
			driver.findElement(BY_PP_LOGOUT).click();
			Reporter.log("Clicked on Logout button to logout");
			Thread.sleep(3000);
			
		} catch (Exception e) {
			// TODO: handle exception
			
			Reporter.log("Somethig went wrong. Logout Page not loaded properly. LOGOUT FAILED");
			Assert.fail();
		}
	}

}
