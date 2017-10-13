package com.microservices.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import com.microservices.utilities.TestBase;

public class PMPPostLoginPage extends TestBase {

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
	public static final By BY_PU_CREATE_BTN = By
			.xpath(".//*[@id='app']/section/div[2]/div/div[2]/div/form/div[8]/p[1]/button");
	public static final By BY_PU_CANCEL_BTN = By
			.xpath(".//*[@id='app']/section/div[2]/div/div[2]/div/form/div[8]/p[2]/button");

	public void assertPostLoginPageLoaded() {
		// TODO Auto-generated method stub
		try {

			Thread.sleep(5000);
			String welcomeText = getText(By.cssSelector("#app > section > div:nth-of-type(2) > div > div > div > h1"));
			Assert.assertEquals(welcomeText, "Welcome to VEON Partner Management");
			Reporter.log("Welcome Text appeared as: " + welcomeText);
			String welcomeText1 = getText(By.cssSelector("#app > section > div:nth-of-type(2) > div > div > div > p"));
			Assert.assertEquals(welcomeText1, "Please select your action in the header.");
			Reporter.log("Welcome Text appeared as: " + welcomeText1);

			Assert.assertTrue(driver.findElement(BY_ADMIN).isDisplayed());
			Assert.assertTrue(driver.findElement(BY_PARTNER).isDisplayed());
			Reporter.log("Adminisrtaor, Partners tabs are present");
			Reporter.log("Login Happened Successfully");

		} catch (Exception e) {
			// TODO: handle exception
			Reporter.log("Somethig went wrong. Login not happened. LOGIN FAILED");
			Assert.fail();

		}
	}

	public void clickOnLogout() throws InterruptedException {
		// TODO Auto-generated method stub
		click(BY_LOGOUT);
		Reporter.log("Clicked on Logout Button");
		Thread.sleep(5000);
	}

	public void clickOnAdminisrtatorTab() {
		// TODO Auto-generated method stub
		click(BY_ADMIN);
		Reporter.log("Clicked on Adminisrtaot tab");

	}

	public void assertAdminViewOpened() {
		try {
			if (isDisplayed(BY_NEW_ADMIN)) {
				Reporter.log("New admin creation button appeared. Admin tab opened successfully");
			}
		} catch (Exception e) {
			Reporter.log("Admin tab not opened properly. SOmethign went wrong");
			Assert.fail();
		}

	}

	public void clickOnNewAdminCreationButton() throws InterruptedException {
		driver.findElement(BY_NEW_ADMIN).sendKeys(Keys.ENTER);
		Reporter.log("Clicked on New Administrator tab ");
		Thread.sleep(5000);
	}

	public void assertNewAdminCreationForm() {
		try {

			/*
			 * Assert.assertTrue(driver.findElement(pMPPostLoginPage.BY_ADMIN).isDisplayed()
			 * );
			 * Assert.assertTrue(driver.findElement(pMPPostLoginPage.BY_PARTNER).isDisplayed
			 * ()); Reporter.log("Adminisrtaor, Partners tabs are present");
			 */
			/*
			 * driver.findElement(pMPPostLoginPage.BY_ADMIN).click();
			 * Reporter.log("Clicked on Adminisrtaot tab");
			 * 
			 * Assert.assertTrue(driver.findElement(pMPPostLoginPage.BY_NEW_ADMIN).
			 * isDisplayed()); Reporter.log("New Administrator tab opened successfully");
			 */

			/*
			 * driver.findElement(pMPPostLoginPage.BY_NEW_ADMIN).sendKeys(Keys.ENTER);
			 * Reporter.log("Clicked on New Administrator tab "); Thread.sleep(5000);
			 */
			Assert.assertTrue(driver.findElement(BY_FIRST_NAME).isDisplayed());
			Reporter.log("Firstname field exists ");
			Assert.assertTrue(driver.findElement(BY_LAST_NAME).isDisplayed());
			Reporter.log("Lastname field exists ");
			Assert.assertTrue(driver.findElement(BY_USER_NAME).isDisplayed());
			Reporter.log("Username field exists ");
			Assert.assertTrue(driver.findElement(BY_PHONE).isDisplayed());
			Reporter.log("Phone field exists ");
			Assert.assertTrue(driver.findElement(BY_STATUS).isDisplayed());
			Reporter.log("Status field exists ");

		} catch (AssertionError e) {
			// TODO: handle exception
			Reporter.log("Something went wrong. New Admin Creation FORM not displayed properly");
			Assert.fail();
		}

	}

	public void enterAFirstName(String fName) {
		// TODO Auto-generated method stub
		sendKeys(BY_FIRST_NAME, fName);
		Reporter.log("Entered the text into Firstname field as : " + fName);
	}

	public void enterALastName(String lName) {
		sendKeys(BY_LAST_NAME, lName);
		Reporter.log("Entered Lastname as : " + lName);
	}

	public void enterAUserName(String adminEmailId) {
		// TODO Auto-generated method stub
		sendKeys(BY_USER_NAME, adminEmailId);
		Reporter.log("Entered Username as : " + adminEmailId);
	}

	public void enterAPhoneNumber(String phoneNumber) {
		// TODO Auto-generated method stub
		sendKeys(BY_PHONE, phoneNumber);
		Reporter.log("Entered Phone number as : " + phoneNumber);
	}

	public void selectAdminAccountStatus(String string) {
		// TODO Auto-generated method stub
		selectFromDropDownVisibleText(BY_STATUS, string);
	}

	public void clickOnCreateAdminButton() throws InterruptedException {
		click(BY_CREATE_ADMIN);
		Reporter.log("Clicked on Create button");
		Thread.sleep(10000);
	}

	public void assertAdminpageAfterCreation() {
		try {
			Assert.assertTrue(isDisplayed(BY_NEW_ADMIN));
			Reporter.log("New Administrator tab appeared");
			Reporter.log("Admin creation happened Successfully");

		} catch (Exception e) {
			Reporter.log("Something went wrong. Creation oF Admin user failed");
			Assert.fail();
		}

	}

	public void assertAdminDetailsIntheTable() {
		// TODO Auto-generated method stub

	}

	public int findNoofPagesToSearchInAdmin() {

		WebElement element = findElement(By.xpath("(//div[@class='pagination']//a)[last()]"));
		Reporter.log("No fo pages are :" + Integer.parseInt(element.getText().trim()));
		return Integer.parseInt(element.getText().trim());
	}

	public void deleteAdminAccount(int pages, String adminEmailId) throws InterruptedException {

		outerloop: for (int i = 1; i <= pages; i++) {
			WebElement htmltable = driver.findElement(By.xpath("//*[@id='app']/section/div[2]/div[2]/div[1]/div"));
			java.util.List<WebElement> rows = htmltable.findElements(By.tagName("tr"));
			Reporter.log("List of rows size: " + rows.size());
			Reporter.log("Emailid to be searched in the page" + "" + "" + "   :" + adminEmailId);

			for (int rnum = 0; rnum < rows.size(); rnum++) {
				java.util.List<WebElement> columns = rows.get(rnum).findElements(By.tagName("td"));
				for (int cnum = 0; cnum < columns.size(); cnum++) {

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
							break outerloop;

						} catch (Exception e) {
							Reporter.log("Something went wrong. Deletion Not happened Successfully");
							Assert.fail();
						}
					}
				}
			}

			WebElement paginationElement = driver.findElement(By.xpath(
					"(.//*[@class='pagination']//li/a)[contains(text(),'"+(i+1)+"')]"));
			System.out.println(paginationElement.getText());
			paginationElement.click();
			Reporter.log("Clicked on the page number : " + (i + 1));
			Thread.sleep(2000);

		}
	}

	public void clickOnPartnerTab() {
		click(BY_PARTNER);
		Reporter.log("Clicked on Partner tab");
		waitForElementPresent(BY_CREATE_PARTNER, 30);
	}

	public void assertPartnerTabLoaded() {
		try {
			Assert.assertTrue(isDisplayed(BY_CREATE_PARTNER));
			Reporter.log("Create New Partner button appeared. Partners tab opened Successfully");
		} catch (Exception e) {
			Reporter.log("Somethign went wrong. partne tab not Opened");
			Assert.fail();
		}
	}

	public void clickOnCreatePartner() {
		// click(BY_CREATE_PARTNER);
		driver.findElement(BY_CREATE_PARTNER).sendKeys(Keys.ENTER);
		Reporter.log("Clicked on 'New Partner' button ");
		waitForElementPresent(BY_P_NAME, 30);
	}

	public void assertPartnerCreationForm() {
		try {
			waitFor(BY_P_NAME, 30);
			Thread.sleep(4000);
			Assert.assertTrue(driver.findElement(BY_P_NAME).isDisplayed());
			Assert.assertTrue(driver.findElement(BY_P_DESC).isDisplayed());
			Assert.assertTrue(driver.findElement(BY_P_EMAIL).isDisplayed());
			Assert.assertTrue(driver.findElement(BY_P_CREATE_BTN).isDisplayed());
			Assert.assertTrue(driver.findElement(BY_P_URL).isDisplayed());
			Assert.assertTrue(driver.findElement(BY_P_LANUAGE).isDisplayed());
			Reporter.log("All the Partner creations fields are opened properly");

		} catch (Exception e) {
			// TODO: handle exception

			Reporter.log("Some of the Partner fields does nto exists. PARTNER CREATION FORM NOT OPENED .FAILURE");
			Assert.fail();

		}

	}

	public void enterPartnerFirstName(String name) {
		sendKeys(BY_P_NAME, name);
		Reporter.log("Entered First nameas  : " + name);

	}

	public void enterPartnerDescription(String string) {

		sendKeys(BY_P_DESC, "Automation partner pls ingnore");
		Reporter.log("Entered Description as : " + string);

	}

	public void selectPartnerLanguage(String string) throws InterruptedException {
		// TODO Auto-generated method stub

		// selectFromDropDownVisibleText(BY_P_LANUAGE, value);

		click(BY_P_LANUAGE);
		Reporter.log("Clcked on Language drop down ");

		Thread.sleep(1000);
		Assert.assertTrue(isDisplayed(BY_P_LANUAGE_MENU));
		Reporter.log("Language list opened ");

		click(By.xpath(".//*[@id='app']/section/div[2]/div/div[2]/div/form/div[3]/p/div/ul/li[52]/a"));
		Reporter.log("Selected ENGLISH langauge ");

		Thread.sleep(3000);

	}

	public void selectPartnerCountry(String string) throws InterruptedException {
		click(By.xpath(".//*[@id='app']/section/div[2]/div/div[2]/div/form/div[4]/p/div/div/input"));
		Reporter.log("Clcked on Country drop down ");
		Thread.sleep(1000);
		Assert.assertTrue(isDisplayed(By.xpath(".//*[@id='app']/section/div[2]/div/div[2]/div/form/div[4]/p/div/ul")));
		Reporter.log("Country drop down list displayed");
		click(By.xpath(".//*[@id='app']/section/div[2]/div/div[2]/div/form/div[4]/p/div/ul/li[2]/a"));

		Reporter.log("Clicked on Russia langauge");
	}

	public void enterPartnerEamilId(String partnerEmailId) {
		sendKeys(BY_P_EMAIL, partnerEmailId);
		Reporter.log("Entered the email adress as : " + partnerEmailId);
	}

	public void enterPartnerPhoneNumber(String phoneNumber) {
		sendKeys(BY_P_PHONE, phoneNumber);
		Reporter.log("Entered the Phone Number as : " + phoneNumber);
	}

	public void enterPartnerUrl(String url) {
		sendKeys(BY_P_URL, url);
		Reporter.log("Entered URL as: " + url);

	}

	public void clickOnSubmittToCreatePartner() throws InterruptedException {
		click(BY_P_CREATE_BTN);
		Reporter.log("Clicked on Create partner submitt button");
		Thread.sleep(5000);
	}

	public void assertPartnerCreated() {
		try {

			Assert.assertTrue(isDisplayed(BY_CREATE_PARTNER));
			Reporter.log("New Partner tab appreared. New partner creation Successful ");

		} catch (Exception e) {
			// TODO: handle exception
			Reporter.log("Something went wrong while creating the Partner");
			Assert.fail();

		}
	}

	public void verifyNewPartnerDetails(int pages, String adminEmailId) {
		// TODO Auto-generated method stub
		outerloop: for (int i = 1; i <= pages; i++) {
			WebElement htmltable = driver.findElement(By.xpath(".//*[@id='app']/section/div[2]/div/div[2]/div/div"));
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
						String email_ele_xpath = "//*[@id='app']/section/div[2]/div/div[2]/div/div/div/div[2]/table/tbody/tr["
								+ rnum + "]/td[2]";
						driver.findElement(By.xpath(email_ele_xpath)).click();

						Reporter.log("Clicked on the Partner record to delete");
						try {
							Thread.sleep(5000);

							try {

								Assert.assertTrue(driver
										.findElement(
												By.xpath("//*[@id='app']/section/div[2]/div[1]/div/div/div[2]/div/div"))
										.isDisplayed());
								Reporter.log("Partnerr card detials shown");
								Assert.assertTrue(driver
										.findElement(
												By.xpath("//*[@id='app']/section/div[2]/div[2]/div/div/div/ul/li[1]/a"))
										.isDisplayed());
								Assert.assertTrue(driver
										.findElement(
												By.xpath("//*[@id='app']/section/div[2]/div[2]/div/div/div/ul/li[2]/a"))
										.isDisplayed());
								Assert.assertTrue(driver
										.findElement(
												By.xpath("//*[@id='app']/section/div[2]/div[2]/div/div/div/ul/li[3]/a"))
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

							/*
							 * Assert.assertTrue(driver.findElement(By.id("delete")).isDisplayed());
							 * Reporter.log("Delete User button displayed");
							 * driver.findElement(By.id("delete")).click();
							 * Reporter.log("Clicked on Delete User button");
							 * 
							 * driver.findElement( By.xpath(
							 * ".//*[@id='app']/section/div[2]/div/div[1]/div/div/div[2]/footer/a[2]"))
							 * .click(); Reporter.log("Clicked on OK on delete confirmaiton screen");
							 * Thread.sleep(5000);
							 */
							break outerloop;

						} catch (Exception e) {
							Reporter.log("Something went wrong");
							Assert.fail();
						}
					}
				}
			}

			WebElement paginationElement = driver.findElement(By.xpath(
					"(.//*[@id='app']/section/div[2]/div/div[2]/div/div/div/div[3]/div[2]/div/div/ul/li/a)[contains(text(),'"
							+ (i + 1) + "')]"));
			System.out.println(paginationElement.getText());
			paginationElement.click();
			Reporter.log("Clicked on the page number : " + (i + 1));

		}

	}

	public int findNoofPagesToSearchInPartner() {
		// TODO Auto-generated method stub
		WebElement element = findElement(By.xpath("(//div[@class='pagination']//a)[last()]"));
		Reporter.log("No fo pages are :" + Integer.parseInt(element.getText().trim()));
		return Integer.parseInt(element.getText().trim());
	}

	public void clickOnPartnerUserCreationButton() throws InterruptedException {
		click(BY_CREATE_PARTNER_USER);
		Reporter.log("Clicked on New user button");
		Thread.sleep(1000);
	}

	public void asertPartnerUserCreationForm() {

		try {

			Assert.assertTrue(isDisplayed(BY_PU_FNAME));
			Assert.assertTrue(isDisplayed(BY_PU_LNAME));
			Assert.assertTrue(isDisplayed(BY_PU_USERNAME));
			Assert.assertTrue(isDisplayed(BY_PU_PHONE));
			Assert.assertTrue(
					isDisplayed(By.xpath(".//*[@id='app']/section/div[2]/div/div[2]/div/form/div[5]/p/span/select")));
			Assert.assertTrue(
					isDisplayed(By.xpath(".//*[@id='app']/section/div[2]/div/div[2]/div/form/div[7]/p/span/select")));
			Assert.assertTrue(isDisplayed(BY_PU_FNAME));

			Reporter.log("All the partner user creation Form fields are exists.");

		} catch (Exception e) {
			// TODO: handle exception
			Reporter.log("Some of the fields are not loaded. Partner user creation form not loaded properly");
		}
		/*
		 * 
		 * 
		 * 
		 * Select dropDown=new Select(driver.findElement(By.name("role")));
		 * 
		 * dropDown.selectByVisibleText("Partner User");
		 * 
		 * Reporter.log("Selected the user role as : Partner User");
		 * driver.findElement(pMPPostLoginPage.BY_PU_CREATE_BTN).click();
		 * Reporter.log("Clicked on Create Button"); Thread.sleep(10000);
		 * 
		 * Assert.assertTrue(driver .findElement(By.xpath(
		 * ".//*[@id='app']/section/div[2]/div[3]/div/div/div/div[2]/div[1]/div/table/tbody/tr/td[2]"
		 * )) .isDisplayed());
		 * 
		 * Reporter.log("New user record exists in the table");
		 * 
		 */ }

	public void enterPartnerUserFirstName(String string) {
		sendKeys(BY_PU_FNAME, string);
		Reporter.log("Entered Partner user Firstname as : " + string);
	}

	public void enterPartnerUserLastName(String string) {
		// TODO Auto-generated method stub
		sendKeys(BY_PU_LNAME, string);
		Reporter.log("Entered Partner user LastName as : " + string);

	}

	public void enterPartnerUserUserName(String pEmail) {
		sendKeys(BY_PU_USERNAME, pEmail);
		Reporter.log("Entered Partner user Username/emailid as :" + pEmail);
	}

	public void enterPartnerUserPhoneNumber(String phoneNumber) {
		sendKeys(BY_PU_PHONE, phoneNumber);
		Reporter.log("Entered Partner user PhonenUmber as : " + phoneNumber);

	}

	public void selectPuserAccoutnStatus(String string) {

		selectFromDropDownVisibleText(
				By.xpath(".//*[@id='app']/section/div[2]/div/div[2]/div/form/div[5]/p/span/select"), string);

	}

	public void selectPartnerUserRole(String string) {

		selectFromDropDownVisibleText(
				By.xpath(".//*[@id='app']/section/div[2]/div/div[2]/div/form/div[7]/p/span/select"), string);

	}

	public void clickOnPUserCreationButton() {
		click(BY_PU_CREATE_BTN);
		Reporter.log("Clicked on Create Button");
		waitFor(BY_CREATE_PARTNER_USER);
	}

	public void assertPartnerUserCreated() {
		try {
			Assert.assertTrue(isDisplayed(By.xpath(
					".//*[@id='app']/section/div[2]/div[3]/div/div/div/div[2]/div[1]/div/div[2]/table/tbody/tr[1]/td[2]")));

			Reporter.log("New user record exists in the table");

		} catch (Exception e) {
			// TODO: handle exception
			Reporter.log("Something went worng. Partner User creation not happened proeprly");
			Assert.fail();
		}
	}

	public void clickOnPartnerUserRecord() {
		click(By.xpath(
				".//*[@id='app']/section/div[2]/div[3]/div/div/div/div[2]/div[1]/div/div[2]/table/tbody/tr[1]/td[2]"));
		Reporter.log("Clicked on the Partner user record to open it");
	}

	public void assertPartnerUserDetailsOpened() {

		try {
			
			Thread.sleep(3000);

			Assert.assertTrue(isDisplayed(By.xpath(".//*[@id='app']/section/div[2]/div/div[2]/div[2]/div")));
			Reporter.log("'Delete Partner user' button appeared");
			Assert.assertTrue(
					isDisplayed(By.xpath(".//*[@id='app']/section/div[2]/div/div[3]/div/form/div[7]/p[1]/button")));
			Reporter.log("Update partner user button appeared");

		} catch (Exception e) {
			// TODO: handle exception
			Reporter.log("Some fot the fields are not opened. Somwthing went wrong");
			Assert.fail();
		}

	}

	public void clickOnDeletePartnerUserButton() throws InterruptedException {
		Thread.sleep(2000);
		click(By.xpath(".//*[@id='app']/section/div[2]/div/div[2]/div[2]/div"));
		Reporter.log("Clicked on 'Delete Partner user' button");
		waitFor(By.xpath(".//*[@id='app']/section/div[2]/div/div[1]/div/div/div[2]"));
	}

	public void verifyConfirmDeletePopup() {

		try {

			Assert.assertTrue(isDisplayed(By.xpath(".//*[@id='app']/section/div[2]/div/div[1]/div/div/div[2]")));
			Reporter.log("Confirmation Popup Opened");
			Assert.assertEquals(
					findElement(By.xpath(".//*[@id='app']/section/div[2]/div/div[1]/div/div/div[2]/header/p"))
							.getText(),
					"Confirm Delete");
			Reporter.log("Confirm Delete header shown properly");
			
			Reporter.log(findElement(By.xpath(".//*[@id='app']/section/div[2]/div/div[1]/div/div/div[2]/section"))
					.getText());
			Assert.assertTrue(findElement(By.xpath(".//*[@id='app']/section/div[2]/div/div[1]/div/div/div[2]/section"))
					.getText().contains("You are about to delete the partner user"));
			Assert.assertTrue(findElement(By.xpath(".//*[@id='app']/section/div[2]/div/div[1]/div/div/div[2]/section"))
					.getText().contains("Do you want to proceed?"));
			Reporter.log("Message shown as : You are about to delete the partner user.");
			Reporter.log("Message shown as :  Do you want to proceed?");
			Assert.assertTrue(isDisplayed(By.linkText("Delete")));
			Reporter.log("Delete button appeared");
			Assert.assertTrue(isDisplayed(By.linkText("Cancel")));
			Reporter.log("Delete button appeared");

		} catch (AssertionError e) {
			// TODO: handle exception

			Reporter.log("Confirmation Popup for deletion fo Partner user  get Failed");
			Assert.fail();
		}

	}

	public void clickOnConfirmDelete() {
		// TODO Auto-generated method stub
		click(By.linkText("Delete"));
		Reporter.log("Clicked on DELETE button on the confirmaiton screen");

	}

	public void verifyDeletionOFPartnerUserHappend() {
		try {

			Thread.sleep(3000);
			Assert.assertTrue(isDisplayed(BY_CREATE_PARTNER_USER));
			Reporter.log("Create partner user button appeared. Partner User deletion happened successfully");

		} catch (Exception e) {
			// TODO: handle exception
			Reporter.log("Something went wrong. Partner User deletion Failed");
			Assert.fail();

		}
	}

	public void clickONEditPartnerButton() throws InterruptedException {
		//click(By.linkText("Edit"));
		Thread.sleep(2000);
		click(By.xpath("//a[contains(text(),'Edit')]"));
		Reporter.log("Clicked on 'Edit' partner field");
		Thread.sleep(2000);
	}

	public void verifyPartnerDetailsAreOpened() {
		// TODO Auto-generated method stub

		try {
			Assert.assertTrue(isDisplayed(By.xpath(".//*[@id='app']/section/div[2]/div/div[2]/div[2]/div")));
			Reporter.log("'Delete Partner' button Dispalyed. ");
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void clickOnDeletePartnerButton() {
		driver.findElement(By.xpath(".//*[@id='app']/section/div[2]/div/div[2]/div[2]/div")).click();
		Reporter.log("Clicked on 'Delete Partner' button");
	}

	public void assertPartnerDeleteWarningPopup() {
		try {
			Assert.assertTrue(isDisplayed(By.xpath(".//*[@id='app']/section/div[2]/div/div[1]/div/div/div[2]/footer/a[2]")));
					
			Reporter.log("Clicked on 'Delete' button on the Delete confirmation popup");
			Thread.sleep(4000);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void clickONConfirmDeletePartner() {
		click(By.xpath(".//*[@id='app']/section/div[2]/div/div[1]/div/div/div[2]/footer/a[2]"));

		Reporter.log("Clicked on 'Delete' button on the Delete confirmation popup");
	}

	public void assertPartnerDeletionHappened() {
		try {
            Thread.sleep(3000);
			Assert.assertTrue(driver.findElement(BY_CREATE_PARTNER).isDisplayed());
			Reporter.log("Deletetion of Partner User is Successfull");
		} catch (Exception e) {
			// TODO: handle exception
			Reporter.log("Partner deletion FAILED. Some thing went wrong");
			Assert.fail();
		}
	}

}
