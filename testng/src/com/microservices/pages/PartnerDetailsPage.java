package com.microservices.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import com.microservices.utilities.TestBase;

public class PartnerDetailsPage extends TestBase {

	public static final By BY_ADMIN = By.linkText("Administrators");
	public static final By BY_PARTNER = By.linkText("Partners");
	public static final By BY_NEW_ADMIN = By.linkText("New administrator");
	public static final By BY_FIRST_NAME = By.id("firstName");
	public static final By BY_LAST_NAME = By.id("lastName");
	public static final By BY_USER_NAME = By.id("userName");
	public static final By BY_PHONE = By.id("phone");
	public static final By BY_STATUS = By.id("status");
	public static final By BY_CREATE_ADMIN = By.id("create");
	public static final By BY_CREATE_PARTNER = By.linkText("New Partner");

	// Partner related locators
	public static final By BY_P_NAME = By.name("name");
	public static final By BY_P_DESC = By.name("description");
	public static final By BY_P_LANUAGE = By.className("dropdown-toggle");
	public static final By BY_P_LANUAGE_MENU = By.className("dropdown-menu");
	public static final By BY_P_EMAIL = By.name("email");
	public static final By BY_P_PHONE = By.id("phone");
	public static final By BY_P_URL = By.name("url");
	public static final By BY_P_CREATE_BTN = By
			.xpath(".//*[@id='app']/section/div[2]/div/div[2]/div/form/div[9]/p[1]/button");
	public static final By BY_P_CANCEL_BTN = By
			.xpath(".//*[@id='app']/section/div[2]/div/div[2]/div/form/div[9]/p[2]/button");

	// Partner user related locators
	public static final By BY_CREATE_PARTNER_USER = By.linkText("New user");
	public static final By BY_PU_FNAME = By.name("firstName");
	public static final By BY_PU_LNAME = By.name("lastName");
	public static final By BY_PU_USERNAME = By.name("userName");
	public static final By BY_PU_PHONE = By.id("phone");
	public static final By BY_LOGOUT = By.linkText("Logout");
	public static final By BY_PU_CREATE_BTN = By.xpath(".//*[@id='app']/section/div[2]/div/div[2]/div/form/div[8]/p[1]/button");
	public static final By BY_PU_CANCEL_BTN = By.xpath(".//*[@id='app']/section/div[2]/div/div[2]/div/form/div[8]/p[2]/button");

	
	
	
}