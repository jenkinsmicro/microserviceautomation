package com.microservices.pages;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;

import com.microservices.utilities.TestBase;


public class PMPLoginPage extends TestBase{
	
	//WebDriver driver;
	
	public static final  By BY_USERNAME=By.name("username");
	public static final  By BY_PASSWORD=By.name("password");
	public static final  By BY_LOGIN_BTN=By.cssSelector("button.button.is-primary");
	public static final  By BY_ERROR_MSG=By.className("help is-danger");
	//public static final By BY_PMP_LOGIN=By.id("");
	//public static final By BY_PMP_LOGIN=By.id("");
	public void launchURL(String pmpUrl) {
		// TODO Auto-generated method stub
		
		driver.get(pmpUrl);
		
		
	}
	public void assertPMPPageLoaded() {
		// TODO Auto-generated method stub
		
		try {
			waitFor(BY_USERNAME);
			Assert.assertTrue(driver.findElement(BY_USERNAME).isDisplayed());
			Assert.assertTrue(driver.findElement(BY_PASSWORD).isDisplayed());
			Assert.assertTrue(driver.findElement(BY_LOGIN_BTN).isDisplayed());
			Reporter.log("Username, Password, Login button are diplayed properly");
			Reporter.log("PMP Login Page Loaded Successfully");

		} catch (Exception e) {
			// TODO: handle exception
			Reporter.log("Some of the fields are not visisble. Login Page nto loaded successfully");
			Assert.fail();
		}
		
	}
	
	
	public void enterUserName(String userName) {
		
		sendKeys(BY_USERNAME, userName);
		Reporter.log("Entered the Username as :" + userName);
	}
	
   public void enterPassword(String password) {
		
		sendKeys(BY_PASSWORD, password);
		Reporter.log("Entered the Password as :" + password);
	}
   
   public void clickOnLogin() {
		
		click(BY_LOGIN_BTN);
		Reporter.log("Clicked on the Login button");
		
	}
	
	
	
	
	
	

}


