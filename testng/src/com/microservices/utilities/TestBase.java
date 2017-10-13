package com.microservices.utilities;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import com.google.common.base.Function;
import junit.framework.TestCase;

public class TestBase {
	public static WebDriver driver;

	@BeforeClass
	public void setUP() throws IOException {

		Reporter.log("Initializing the driver ");
		System.setProperty("webdriver.gecko.driver",
				"C:\\Users\\kaligeti\\Documents\\Selenium_setup\\geckodriver-v0.13.0-win64\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		

		// Driver.initialize();

	}

	public void waitForElement(By locator, int timer) throws Exception {
		try {
			for (int i = 0; i < timer; i++) {
				try {
					driver.findElement(locator).isDisplayed();
					System.out.println("Element is available :" + locator);
					break;
				} catch (RuntimeException localRuntimeException) {
					Thread.sleep(1000);
					System.out.println("Waiting for........" + locator);
				}
			}
		} catch (RuntimeException localRuntimeException) {
			System.out.println("Error in performing Wait:" + localRuntimeException.getMessage() + "Fail");
			localRuntimeException.getMessage();
		}
	}

	/*
	 * getText
	 * 
	 * @param By
	 */
	public String getText(By by) {
		waitFor(by);
		return driver.findElement(by).getText();
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public void get(String url) {
		driver.get(url);
	}
	/*
	 * getPageSource
	 */

	public String getPageSource() {
		return driver.getPageSource();
	}

	/*
	 * Checking locator is present or not
	 */
	public void isElementPresent(By by, String msg) {
		if (!isElementPresent(by)) {
			Reporter.log(msg + " :: is not present. Check the screen shot");
			TestCase.fail(msg + " :: is not present. Check the screen shot");
		} else {
			Reporter.log(msg + " :: is present.");
		}
	}

	/*
	 * Checking locator is present or not
	 */
	public void isElementNotPresent(By by, String msg) {
		if (!isElementPresent(by)) {
			Reporter.log(msg + " :: is not present.");

		} else {
			Reporter.log(msg + " :: is present.");
			TestCase.fail(msg + " :: is present. Check the screen shot");
		}
	}

	/*
	 * Checking locator is present or not
	 */
	public void isElementDisplayed(By by, String msg) {

		if (isElementPresent(by)) {

			if (!isDisplayed(by)) {
				Reporter.log(msg + " :: is not displayed. Check the screen shot");
				TestCase.fail(msg + " :: is not displayed. Check the screen shot");
			} else {
				Reporter.log(msg + " :: is displayed.");
			}

		} else {
			Reporter.log(msg + " :: is not displayed.");
			TestCase.fail(msg + " :: is not displayed. Check the screen shot");
		}
	}

	/*
	 * Checking locator is present or not
	 */
	public void isElementNotDisplayed(By by, String msg) {

		if (isElementPresent(by)) {

			if (!isDisplayed(by)) {
				Reporter.log(msg + " :: is not displayed.");
			} else {
				Reporter.log(msg + " :: is displayed.");
				TestCase.fail(msg + " :: is displayed. Check the screen shot");
			}
		} else {
			Reporter.log(msg + " :: is not displayed.");
		}

	}

	/*
	 * isSelected
	 * 
	 * @param Locator
	 */
	public Boolean isSelected(By by) {
		return driver.findElement(by).isSelected();
	}

	/*
	 * currentpage refresh
	 * 
	 */

	public void refresh() {
		driver.navigate().refresh();
	}
	/*
	 * isDisplayed
	 * 
	 * @param Locator
	 */

	public Boolean isDisplayed(By by) {
		waitFor(by);
		return driver.findElement(by).isDisplayed();
	}

	/*
	 * getAttribute
	 * 
	 * @param By Attribute type
	 */
	public String getAttribute(By by, String type) {
		waitFor(by);
		return driver.findElement(by).getAttribute(type);
	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	/**
	 * Click
	 * 
	 * @param by
	 */
	public void click(By by) {
		isElementClickable(by);
		driver.findElement(by).click();
	}

	public void clickonIntroPage(By by) {
		waitForElementPresent(by, 60);
		driver.findElement(by).click();
	}

	public void check(By by) {
		driver.findElement(by).click();
	}

	public WebElement waitForElementPresent(final By locator, int timeoutInSec) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(timeoutInSec, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS);

		WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		});

		return foo;
	};

	//
	// select value from drop down
	public void selectFromDropDown(By by, String value) {
		WebElement select = findElement(by);
		org.openqa.selenium.support.ui.Select dropDown = new org.openqa.selenium.support.ui.Select(select);
		dropDown.selectByValue(value);
		Reporter.log("Selected the value from the dropdown : " + value);

	}

	public void selectFromDropDownVisibleText(By by, String value) {

		Select dropDown = new Select(findElement(by));
		dropDown.selectByVisibleText(value);
		Reporter.log("Selected the value from the dropdown : " + value);
	}

	public WebElement findElement(By by) {
		return driver.findElement(by);
	}

	public WebElement findElement(String by) {
		return driver.findElement(By.id(by));
	}

	public List<WebElement> findElements(By by) {
		return driver.findElements(by);
	}

	/**
	 * SendKeys
	 * 
	 * @param by
	 * @param value
	 */

	public void sendKeysWithKeyStoke(By by, Keys keyStroke) {
		waitFor(by);
		try {
			driver.findElement(by).sendKeys(keyStroke);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendKeyWithOutClear(By by, String value) {
		waitFor(by);
		driver.findElement(by).sendKeys(value);
		Reporter.log("Entered Value :: " + value);
	}

	public Boolean isEnabled(By by) {
		return driver.findElement(by).isEnabled();
	}

	/**
	 * Webdriver switchToFrame
	 * 
	 * @param frameName
	 * @return
	 */

	public void switchToFrame(String frameName) {
		switchToMainFrame();
		driver.switchTo().frame(frameName);
	}

	public void switchToMainFrame() {
		driver.switchTo().defaultContent();
	}

	public void switchToWindow(String windowName) {
		driver.switchTo().window(windowName);
	}

	public void switchToFrame(WebElement frameName) {
		driver.switchTo().frame(frameName);
	}

	/**
	 * 
	 * Commands related to alerts
	 * 
	 */

	public void acceptAlert() {
		driver.switchTo().alert().accept();
	}

	/**
	 * Switches to child window and returns the parent window handler.
	 * 
	 * @return
	 */
	public String switchToWindow() {
		String winHandle = driver.getWindowHandle();
		for (String handle : driver.getWindowHandles()) {
			switchToWindow(handle);
		}
		return winHandle;
	}

	/**
	 * An expectation for checking an element is visible and enabled such that you
	 * can click it.
	 * 
	 * @param by
	 */
	public void isElementClickable(By by) {
		(new WebDriverWait(driver, 60, 1000)).until(ExpectedConditions.elementToBeClickable(by));
	}

	/**
	 * clear Prama By
	 */
	public void clear(By by) {
		waitFor(by);
		driver.findElement(by).clear();
	}

	public void submit(By by) {
		waitFor(by);
		driver.findElement(by).submit();
	}

	/**
	 * SendKeys
	 * 
	 * @param by
	 * @param value
	 */
	public void sendKeys(By by, String value) {
		waitFor(by);
		try {
			driver.findElement(by).clear();
			driver.findElement(by).sendKeys(value);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void waitForPageToLoad(int timeoutInSec) {
		(new WebDriverWait(driver, timeoutInSec)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return (((org.openqa.selenium.JavascriptExecutor) driver).executeScript("return document.readyState")
						.equals("complete"));
			}
		});
	}

	public void focus(WebElement element) {
		new Actions(driver).moveToElement(element).click().perform();
	}

	/**
	 * Webdriver wait ..
	 * 
	 * @param by
	 * @return
	 */
	public boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public void waitFor(By by) {
		waitForPageElementToLoad(by, 30);
	}

	public void waitFor(By by, int timeoutInSec) {
		waitForPageElementToLoad(by, timeoutInSec);
	}

	public void waitForPageElementToLoad(final By by, int timeoutInSec) {
		(new WebDriverWait(driver, timeoutInSec, 1000)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				WebElement element = null;
				try {
					element = driver.findElement(by);
				} catch (NoSuchElementException e) {
					Reporter.log("Elemetn  is not present " + by + ":: Retrying.....");
				}
				return (element != null);
			}
		});
	}

	@AfterClass
	public void closeSession() throws Exception {

		driver.quit();
		// Driver.closeSession();
	}

}

