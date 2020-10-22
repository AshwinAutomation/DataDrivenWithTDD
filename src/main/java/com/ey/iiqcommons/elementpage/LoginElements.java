package com.ey.iiqcommons.elementpage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.ey.generics.UtilsFunctions;
import com.ey.iiqesourcestring.ResourceStrings;
import com.qa.testbase.TestBase;
import com.relevantcodes.extentreports.LogStatus;

public class LoginElements extends TestBase {
/*	@FindBy(id = "username")
	@CacheLookup
	WebElement uid;

	@FindBy(id = "password")
	@CacheLookup
	WebElement pwd;

	@FindBy(xpath = "//input[@id='password']//following::button")
	@CacheLookup
	WebElement loginButton;
*/
  @FindBy(id = "loginForm:accountId")
	@CacheLookup
	WebElement uid;

	@FindBy(id = "loginForm:password")
	@CacheLookup
	WebElement pwd;

	@FindBy(id = "loginForm:loginButton")
	@CacheLookup
	WebElement loginButton;
	
	
	
	
	  public LoginElements(){ 
		  super(); 
		  PageFactory.initElements(driver, this);
	  
	 }
	


	public void loginWithValidCredentials(String userName, String password) {

		test = report.startTest(ResourceStrings.IIQ_LOGIN + ":" + userName);
		report.flush();
        logger.info(ResourceStrings.IIQ_LOGIN + ":" + userName);
        test.assignCategory("Login details for user : " + userName);
		try {

			UtilsFunctions.functionTextBox(uid, userName);
			UtilsFunctions.functionTextBox(pwd, password);
			UtilsFunctions.functionClick(loginButton);
			if (driver.getTitle().equalsIgnoreCase(ResourceStrings.IIQ_HOME))

			{
				Thread.sleep(10000);
				
			TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.PASS,
						ResourceStrings.IIQ_LOGIN_SUCCESS +" :: " + userName, report, test, driver);
			 logger.info(ResourceStrings.IIQ_LOGIN_SUCCESS);
			} else {
			TestBase.logMessageToReport(SCRSHOT_LEVEL_ERROR, LogStatus.FAIL, ResourceStrings.IIQ_LOGIN_FAIL,
						report, test, driver);
			logger.info(ResourceStrings.IIQ_LOGIN_FAIL);
				driver.quit();

				Assert.assertFalse(true);
			}
		} catch (Exception e) {
			TestBase.logMessageToReport(SCRSHOT_LEVEL_ERROR, LogStatus.FAIL, ResourceStrings.IIQ_LOGIN_FAIL,
					report, test, driver);
	        logger.info(ResourceStrings.IIQ_LOGIN_FAIL);
		}
		report.endTest(test);
	}

}
