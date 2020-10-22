package com.ey.iiqcommons.elementpage;

import com.excelutils.ExcelUtils;
import com.ey.generics.EYUtils;
import com.ey.generics.UtilsFunctions;
import com.ey.iiqesourcestring.ResourceStrings;
import com.qa.testbase.TestBase;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ManageUserAccessElements extends TestBase {

	// ================manage Access elements===========================
	// quiicklink button

	@FindBy(xpath = "//i[@class='fa fa-list-ul fa-lg']")
	@CacheLookup
	WebElement quicklinkButton;

	@FindBy(xpath = "//a[@id='quickLinkCategoryAccess']")
	@CacheLookup
	WebElement manageAccessLink; // Manage UserAcess link

	@FindBy(xpath = "//a[@id='quickLinkCategoryAccess']//following::a[1]")
	@CacheLookup
	WebElement manageuserAccessLink;

	// user textbox
	@FindBy(id = "userSearchText")
	@CacheLookup
	WebElement searchinputTextbox;

	// Search button
	@FindBy(id = "userSearchBtn")
	@CacheLookup
	WebElement userSearchButton;

	@FindBy(xpath = "//button[@id='selectBtn-0']")
	WebElement selectUserLinkButton;

	@FindBy(id = "flowManageAccessBtn")
	@CacheLookup
	WebElement manageAccesButton;

	@FindBy(xpath = "//button[@id='itemsFilterBtn']")
	@CacheLookup
	WebElement userFilterButton;
	
	@FindBy(xpath = "//input[@id='itemsFilterPanelItem1']")
	@CacheLookup
	WebElement entitlementFiltersearchbox;
	
	
	@FindBy(xpath = "//input[@id='itemsFilterPanelItem2']")
	@CacheLookup
	WebElement entitlementFilterRemovesearchbox;
	
	
	

	@FindBy(xpath = "//input[@id='accessSearchText']")
	@CacheLookup
	WebElement entitlementTextbox;

	//@FindBy(xpath = "(//button[@role='checkbox'])[1]")
	@FindBy(xpath = "//i[@class='fa fa-check']")
	
	@CacheLookup
	WebElement entitlementLinkButton;
	
	@FindBy(xpath = "//input[@id='itemsFilterPanelItem1' and  @name='typeaheadInputField']")
	@CacheLookup
	WebElement EntitlementapplicationTextbox;

	@FindBy(xpath = "//input[@id='itemsFilterPanelItem1' and  @name='typeaheadInputField']")
	@CacheLookup
	WebElement globalSearchTextbox;

	@FindBy(id = "itemsFilterPanelApplyBtn")
	@CacheLookup
	WebElement applyButton;
	
	//remove
	@FindBy(xpath = "//li[@role='button'][2]")
	@CacheLookup
	WebElement entitlementRemoveButton;

	
	@FindBy(xpath ="//li[@role='button'][2]//following::input[1]")
	@CacheLookup
	WebElement entitlementRemoveSearchBox;
	
	
	
	@FindBy(xpath ="//button[@id='currentAccessSearchBtn']")
	@CacheLookup
	WebElement entitlementRemoveSearchButton;
	
	

	@FindBy(xpath = "//i[@class='fa fa-check']")
	@CacheLookup
	List<WebElement> roleName_btn;

	@FindBy(xpath = "//i[@class='fa fa-check']")
	@CacheLookup
	WebElement EntitlementButton;

	@FindBy(id = "accessRequestFooterNextBtn")
	@CacheLookup
	WebElement accessRequestButton;

	@FindBy(id = "submitBtn")
	@CacheLookup
	WebElement submitButton;

	@FindBy(xpath = "//button[@id='closeModalDialogBtn']")
	@CacheLookup
	WebElement closePopupBtn;

	@FindBy(xpath = "//li[contains(text(),'Request submitted successfully')]")
	@CacheLookup
	WebElement requestedID;

	@FindBy(xpath = "//li[starts-with(text(),'	Unable to notify, no email address for: ' )]")
	@CacheLookup
	WebElement unabletoNotifyMail;

	@FindBy(partialLinkText = "Home")
	@CacheLookup
	WebElement homeLink;
	
	
	public ManageUserAccessElements() {
		PageFactory.initElements(driver, this);
	}
	
	
	
	public boolean manageUserAccess(String userName,String applicationName,String roleName,String requestType,ExtentReports report,ExtentTest test,WebDriver driver) throws InterruptedException, IOException {
/*		test = report.startTest(ResourceStrings.IIQ_MANAGE_USER_ACCESS_ACCESSREQUESTS + " :: " + requestType);
		report.flush();
*/
		System.out.println("userName :" + userName);
		System.out.println("applicationName :" + applicationName);
		System.out.println("roleName : " + roleName);
		System.out.println("RequestType : " + requestType);
		TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO, ResourceStrings.IIQ_DASHBOARD, report,
				test, driver);
		
		test.assignCategory("Requestee Name : " + userName);
		test.assignCategory("RoleName Name : " + roleName);
		test.assignCategory("RequestType : " + requestType);
/*		
		boolean isRoleAlreadyAssigned = true;
		boolean isRoleAlreadyRemoved = true;
		String searchedUserOrRole = "";
		String userOrRoleSelectButton = "";*/
		
		List<String> userList = new ArrayList<String>();
		//List<String> RolesList = new ArrayList<String>();
		boolean isRequestSubmitted = false;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			System.out.println("manageUserAccess inside try");
			UtilsFunctions.functionClick(quicklinkButton);
			Thread.sleep(2000);
			UtilsFunctions.functionClick(manageAccessLink);
			uiElement(manageuserAccessLink);
			UtilsFunctions.functionClick(manageuserAccessLink);
			Thread.sleep(4000);
			System.out.println("After manageuserAccessLink ");

			if (null != userName && !userName.isEmpty()) {
				System.out.println("After manageuserAccessLink ");
				String[] splitArray = userName.split(",");
				Thread.sleep(2000);
				userList = Arrays.asList(splitArray);

				System.out.println("users list ==" + userList);

				int userCount = userList.size();
				System.out.println("userCount ==" + userCount);
				for (String user : userList) {
					System.out.println("user from list is ==" + user);
					Thread.sleep(3000);
					System.out.println("before userselect");
					

					js.executeScript("arguments[0].scrollIntoView(true);", searchinputTextbox);
					Thread.sleep(2000);
					searchinputTextbox.sendKeys(user);
					Thread.sleep(2000);
					searchinputTextbox.sendKeys(Keys.ENTER);
					// UtilsFunctions.functionTextBox(searchinputTextbox, user);
					System.out.println("after  userselect");
					js.executeScript("arguments[0].scrollIntoView(true);", selectUserLinkButton);
					js.executeScript("arguments[0].click();", selectUserLinkButton);
					TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
							ResourceStrings.IIQ_MANAGE_USER_ACCESS_SELECTUSER + user, report, test, driver);
					Thread.sleep(8000);

					// TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG,
					// LogStatus.INFO,ResourceStrings.IIQ_MANAGE_USER_ACCESS_AFTERUSERSELECT,
					// report, test, driver);
					Thread.sleep(3000);
					searchinputTextbox.clear();

				}
				
				
				
				
				Thread.sleep(3000);
				UtilsFunctions.functionClick(manageAccesButton);
				Thread.sleep(3000);
				
				
				
				

				if (requestType.equalsIgnoreCase("ADD")||requestType.equalsIgnoreCase("DENY") && null != roleName && !roleName.isEmpty()) {
					System.out.println("inside if Add/Deny Manage user Access Elements ");
					UtilsFunctions.functionClick(userFilterButton);
					System.out.println(" aafter userFilterButton");
					Thread.sleep(3000);
					/*js.executeScript("arguments[0].scrollIntoView(true);", entitlementFiltersearchbox);
					js.executeScript("arguments[0].click();", entitlementFiltersearchbox);*/
					Thread.sleep(3000);
					entitlementFiltersearchbox.sendKeys(applicationName);
					Thread.sleep(3000);
					entitlementFiltersearchbox.sendKeys(Keys.ENTER);
					Thread.sleep(3000);
					js.executeScript("arguments[0].scrollIntoView(true);", applyButton);
					
					UtilsFunctions.functionClick(applyButton);
					
					Thread.sleep(3000);
					
					
					String[] splitRole = roleName.split("#");

					for (int i = 0; i < splitRole.length; i++) {
						String role = splitRole[i];
						System.out.println("Role :" + role);

						UtilsFunctions.clickToParticularElement(entitlementTextbox);
						

						js.executeScript("arguments[0].scrollIntoView(true);", entitlementTextbox);
						Thread.sleep(2000);
						UtilsFunctions.functionTextBox(entitlementTextbox, role);
						entitlementTextbox.sendKeys(Keys.ENTER);
						Thread.sleep(4000);
					WebElement  entitlementLinkButtons=EYUtils.getElementPresent(By.xpath("(//button[@role='checkbox'])[1]"));
					js.executeScript("arguments[0].scrollIntoView(true);", entitlementLinkButtons);
					try {
					uiElement(entitlementLinkButtons).click();
					} catch (StaleElementReferenceException e) {
						entitlementLinkButtons=EYUtils.getElementPresent(By.xpath("(//button[@role='checkbox'])[1]"));
						uiElement(entitlementLinkButtons).click();
					}
						//UtilsFunctions.functionClick(entitlementLinkButton);

						TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
								ResourceStrings.IIQ_MANAGE_USER_ACCESS_ENTITLEMENT + role, report, test, driver);

						entitlementTextbox.clear();

					}
					Thread.sleep(2000);
					UtilsFunctions.functionClick(accessRequestButton);
					Thread.sleep(2000);
					TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
							ResourceStrings.IIQ_MANAGE_USER_ACCESS_ENTITLEMENT_REQEUESTTYPE + "==>" + requestType ,report,test,driver);
					UtilsFunctions.functionClick(submitButton);
					Thread.sleep(10000);
		}
				
				else if (requestType.equalsIgnoreCase("REMOVE") && null != roleName && !roleName.isEmpty()) {
					Thread.sleep(2000);
					UtilsFunctions.clickToParticularElement(entitlementRemoveButton);
					Thread.sleep(2000);
					
	             UtilsFunctions.functionClick(userFilterButton);
					
					Thread.sleep(3000);
					entitlementFilterRemovesearchbox.sendKeys(applicationName);
					Thread.sleep(3000);
					entitlementFilterRemovesearchbox.sendKeys(Keys.ENTER);
					Thread.sleep(3000);
					js.executeScript("arguments[0].scrollIntoView(true);", applyButton);
					
					UtilsFunctions.functionClick(applyButton);
					
					Thread.sleep(3000);
					
					
					String[] splitRole = roleName.split("#");
					for (int i = 0; i < splitRole.length; i++) {
						String role = splitRole[i];
						System.out.println("Role :" + role);
						
						UtilsFunctions.clickToParticularElement(entitlementRemoveSearchBox);
						UtilsFunctions.functionTextBox(entitlementRemoveSearchBox, role);
						entitlementRemoveSearchBox.sendKeys(Keys.ENTER);
						Thread.sleep(4000);

						WebElement  entitlementLinkButtons=EYUtils.getElementPresent(By.xpath("(//button[@role='checkbox'])[1]"));
						js.executeScript("arguments[0].scrollIntoView(true);", entitlementLinkButtons);
						try {
						uiElement(entitlementLinkButtons).click();
						} catch (StaleElementReferenceException e) {
							entitlementLinkButtons=EYUtils.getElementPresent(By.xpath("(//button[@role='checkbox'])[1]"));
							uiElement(entitlementLinkButtons).click();
						}
							

						TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
								ResourceStrings.IIQ_MANAGE_USER_ACCESS_ENTITLEMENT_REMOVE + role, report, test, driver);
						
						
						entitlementRemoveSearchBox.clear();
					}
					Thread.sleep(2000);
					UtilsFunctions.functionClick(accessRequestButton);
					Thread.sleep(2000);
					TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
							ResourceStrings.IIQ_MANAGE_USER_ACCESS_ENTITLEMENT_REQEUESTTYPE + "==>" + requestType ,report,test,driver);
					UtilsFunctions.functionClick(submitButton);
					Thread.sleep(10000);
				}

			}
		}

		catch (Exception e) {
		}
    
		int userSize = userList.size();
		if (userSize>1 && requestedID.isDisplayed() && requestedID != null) {

		String requestIDtext = requestedID.getText();
			System.out.println("requestIDs are : " + requestIDtext);
			logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
					ResourceStrings.IIQ_MANAGEACCESS_SUCCESSFULLY_SUBMITTED, report, test, driver);
		}
		else {
			String requestIDtext = requestedID.getText();
			System.out.println("requestID is : " + requestIDtext);
			logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
					ResourceStrings.IIQ_MANAGEACCESS_SUCCESSFULLY_SUBMITTED, report, test, driver);
			// Request submitted successfully. The ID for this // request is 15870.
			int RequestedIDnumber = (Integer.parseInt(requestIDtext.replaceAll("[^0-9]", "")));
			System.out.println("id :" + RequestedIDnumber);// 15870
			String RequestedID = String.valueOf(RequestedIDnumber);
	         
			ExcelUtils.setCellData(excelsheetPath, xlsheet, 1, 10, RequestedID);


		}

	

     return isRequestSubmitted;

	}
	
	
	
	public void SingleUserSingleAccess(String userName,String applicationName,String roleName,String requestType,ExtentReports report, ExtentTest test,
			WebDriver driver ) throws InterruptedException, IOException {
		test = report.startTest(ResourceStrings.IIQ_MANAGE_USER_ACCESS_ACCESSREQUESTS_SINGLE_USER_SINGLE_ACCESS+ " :: " + requestType);
		report.flush();
	

		manageUserAccess(userName,applicationName,roleName, requestType, report, test, driver);
		
		report.endTest(test);
	}
	
	public void SingleUserMultipleAccess(String userName,String applicationName,String roleName,String requestType,ExtentReports report, ExtentTest test,
			WebDriver driver ) throws InterruptedException, IOException {
		test = report.startTest(ResourceStrings.IIQ_MANAGE_USER_ACCESS_ACCESSREQUESTS_SINGLE_USER_MULTIPLE_ACCESS+ " :: " + requestType);
		report.flush();


		manageUserAccess(userName,applicationName,roleName, requestType, report, test, driver);
	
		
		report.endTest(test);
	}
	
	
	
	public void multipleUserSingleAccess(String userName,String applicationName,String roleName,String requestType,ExtentReports report, ExtentTest test,
			WebDriver driver ) throws InterruptedException, IOException {
		test = report.startTest(ResourceStrings.IIQ_MANAGE_USER_ACCESS_ACCESSREQUESTS_MULTIPLE_USER_SINGLEACCESS+ " :: " + requestType);
		report.flush();


		manageUserAccess(userName,applicationName,roleName,requestType,report,test,driver);
	
		
		report.endTest(test);
	}
	
	
	
	
	
	public void multipleUserMultipleAccess(String userName,String applicationName,String roleName,String requestType,ExtentReports report, ExtentTest test,
			WebDriver driver ) throws InterruptedException, IOException {
		test = report.startTest(ResourceStrings.IIQ_MANAGE_USER_ACCESS_ACCESSREQUESTS_MULTIPLE_USER_MULTIPLEACCESS + " :: " + requestType);
		report.flush();


		manageUserAccess(userName,applicationName,roleName, requestType, report, test, driver);
	
		
		report.endTest(test);
	}
	
	
	
	public void SingleUserSingleAccessAutoApproval(String userName,String applicationName,String roleName,String requestType,ExtentReports report, ExtentTest test,
			WebDriver driver ) throws InterruptedException, IOException {
		test = report.startTest(ResourceStrings.IIQ_MANAGE_USER_ACCESS_ACCESSREQUESTS_SINGLE_USER_SINGLE_ACCESS_AUTOAPPROVED + " :: " + requestType);
		report.flush();


		manageUserAccess(userName,applicationName,roleName, requestType, report, test, driver);
	
		
		report.endTest(test);
	}
	
	public void SingleUserMultipleAccessAutoApproval(String userName,String applicationName,String roleName,String requestType,ExtentReports report, ExtentTest test,
			WebDriver driver ) throws InterruptedException, IOException {
		test = report.startTest(ResourceStrings.IIQ_MANAGE_USER_ACCESS_ACCESSREQUESTS_MULTIPLE_USER_SINGLE_ACCESS_AUTOAPPROVED + " :: " + requestType);
		report.flush();


		manageUserAccess(userName,applicationName,roleName, requestType, report, test, driver);
	
		
		report.endTest(test);
	}
	
	
	

}