package com.microservices.utilities;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;

public class Driver {

	
	public static WebDriver driver=null;
	
	public static void initialize() {
		if(driver==null) {
			System.setProperty("webdriver.gecko.driver","C:\\Users\\kaligeti\\Documents\\Selenium_setup\\geckodriver-v0.13.0-win64\\geckodriver.exe");
			driver = new FirefoxDriver();
		
			driver.manage().window().maximize();
			//driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		}
	}
	
	
	public static void closeSession() throws Exception {
		try {
			if (driver!= null)
				driver.quit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	/*public static void close() {
		Reporter.log("Closing the browser");
		driver.close();
		driver=null;
	}
	
	public static void quit() {
		Reporter.log("Closing the browser");
		driver.quit();
		driver=null;
	}*/
	
}
