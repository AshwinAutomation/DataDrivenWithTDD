package com.ey.iiqcommons.elementpage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.ey.generics.EYUtils;
import com.ey.generics.UtilsFunctions;
import com.ey.iiqesourcestring.ResourceStrings;
import com.qa.testbase.TestBase;
import com.relevantcodes.extentreports.LogStatus;

public class WorkItemsElements extends TestBase {

	@FindBy(xpath = "//img[@alt='SailPoint Logo']//following::a[3]")
	@CacheLookup
	WebElement myworkLink;
	@FindBy(partialLinkText = "Home")
	@CacheLookup
	WebElement homeLink;
	@FindBy(xpath = "//img[@alt='SailPoint Logo']//following::a[5]")
	@CacheLookup
	WebElement accessRequestLink;
	@FindBy(partialLinkText = "Work Items")
	@CacheLookup
	WebElement workItemLink;

	@FindBy(xpath = "//button[@id='cardListFilterBtn']")
	@CacheLookup
	WebElement accessRequestFilterButton;

	@FindBy(id = "accessRequests_requestee")
	@CacheLookup
	WebElement accessRequestRequesteetextbox;

	@FindBy(id = "cardListFilterPanelApplyBtn")
	@CacheLookup
	WebElement accessRequestApplyButton;

	@FindBy(id = "cardListSearchInput")
	@CacheLookup
	WebElement requestedIDTextbox;

	@FindBy(xpath = "//span[contains(@id,'identityRequestId')]")
	@CacheLookup
	WebElement requestedIDTextMessage;

	@FindBy(xpath = "//span[contains(@id,'identityRequestId')]//following::span[2]")
	@CacheLookup
	WebElement requestedIDTextMessageStatus;

	@FindBy(id = "cardListSearchBtn")
	@CacheLookup
	WebElement RequestedIDsearchButton;

	@FindBy(xpath = "//button[starts-with(@id,'requestDetailsBtn')]")
	@CacheLookup
	WebElement accessRequestDetailsButton;

	@FindBy(xpath = "(//li[@class='list-group-item ng-binding'])[5]")
	@CacheLookup
	WebElement completionStatus;

	@FindBy(xpath = "(//li[@class='list-group-item ng-binding'])[11]")
	@CacheLookup
	WebElement executionStatus;

	@FindBy(xpath = "//h4[text()='Interactions']")
	@CacheLookup
	WebElement interaction;

	@FindBy(xpath = "//button[@id='cardListFilterBtn']")
	@CacheLookup
	WebElement workItemFilterButton;

	@FindBy(xpath = "//button[@id='-btn']")
	@CacheLookup
	WebElement workItemAddIcon;

	@FindBy(xpath = "(//*[normalize-space(text()) and normalize-space(.)='Type'])[2]/following::a[2]")
	@CacheLookup
	WebElement workItemAddRequestedId;

	@FindBy(xpath = "//input[starts-with(@id,'cardListFilterPanelAdditionalItem')]")
	@CacheLookup
	WebElement workItemAddRequestedIdSearchBox;

	@FindBy(xpath = "//button[@id='cardListFilterPanelApplyBtn']")
	@CacheLookup
	WebElement applyButton;

	@FindBy(css = "button[id^='workitem-forward-btn']")
	@CacheLookup
	WebElement forwardOwnerButton;

	@FindBy(xpath = "//input[@id='ownerNameSuggestBoxWorkItemListForward']")
	@CacheLookup
	WebElement forwardtoOwner;

	@FindBy(xpath = "(//button[@ng-repeat='button in buttons'])[2]")
	@CacheLookup
	WebElement forwardOKbutton;

	@FindBy(xpath = "//button[starts-with(@id,'workitem-goto-btn')]")
	@CacheLookup
	WebElement viewButton;

/*	@FindBy(xpath = "//button[contains(@id,'btnApproveApproval')]")
	@CacheLookup
	WebElement approvalButton;

	@FindBy(xpath = "//button[contains(@id,'btnRejectApproval')]")
	@CacheLookup
	WebElement rejectButton;*/

	@FindBy(xpath = "//button[contains(@id,'btnApproveAll')]")
	@CacheLookup
	WebElement approveAllButton;

	@FindBy(xpath = "//button[contains(@id,'btnRejectAll')]")
	@CacheLookup
	WebElement rejectAllButton;

	@FindBy(xpath = "//button[text()='Complete']")
	@CacheLookup
	WebElement completeButton;

	
	@FindBy(css ="input[value='Complete']")
	@CacheLookup
	WebElement manualActioncompleteButton;


	
	
	
	
/*	@FindBy(xpath = "//div[@class= 'quicklink-card-link quicklink-track-requests']//following::div[@class='panel-body']")
	@CacheLookup
	WebElement accessTrack;*/

	public WorkItemsElements() {
		PageFactory.initElements(driver, this);
	}

	
	
	
       public void manualWorkActions(String approverName,String entitlement) throws InterruptedException {
		System.out.println("Manual Workitem run");
		JavascriptExecutor js=(JavascriptExecutor)driver;
		List<String> RolesList = new ArrayList<String>();
		String[] splitRole = entitlement.split("#");
		RolesList = Arrays.asList(splitRole);
		System.out.println("users list ==" + RolesList);

		int entitlementCount = RolesList.size();
		System.out.println("entitlementCount list in size() ==" + entitlementCount);// 1
		Thread.sleep(10000);
		
		
		WebElement forwardOwnerButton=EYUtils.getElementPresent(By.cssSelector("button[id^='workitem-forward-btn']"));


		try {

			EYUtils.uiElement(forwardOwnerButton).click();
		} catch (StaleElementReferenceException e) {
			forwardOwnerButton=EYUtils.getElementPresent(By.cssSelector("button[id^='workitem-forward-btn']"));
			EYUtils.uiElement(forwardOwnerButton).click();
		}

		Thread.sleep(8000);  
		WebElement forwardtoOwner=EYUtils.getElementPresent(By.xpath("//input[@id='ownerNameSuggestBoxWorkItemListForward']"));
		try {
			EYUtils.uiElement(forwardtoOwner).sendKeys(approverName);
			Thread.sleep(3000);
			EYUtils.uiElement(forwardtoOwner).sendKeys(Keys.ENTER);

		} catch (StaleElementReferenceException e) {
			forwardtoOwner=EYUtils.getElementPresent(By.xpath("//input[@id='ownerNameSuggestBoxWorkItemListForward']"));
			EYUtils.uiElement(forwardtoOwner).sendKeys(approverName);
			Thread.sleep(3000);
			EYUtils.uiElement(forwardtoOwner).sendKeys(Keys.ENTER);
		}



		Thread.sleep(3000); 
		WebElement forwardOKbutton=EYUtils.getElementPresent(By.xpath("(//button[@ng-repeat='button in buttons'])[2]"));
		try {
			EYUtils.uiElement(forwardOKbutton).click();
		} catch (Exception e) {
			 forwardOKbutton=EYUtils.getElementPresent(By.xpath("(//button[@ng-repeat='button in buttons'])[2]"));
			EYUtils.uiElement(forwardOKbutton).click();
		}

		Thread.sleep(3000);
		WebElement viewButton=EYUtils.getElementPresent(By.xpath("//button[starts-with(@id,'workitem-goto-btn')]"));
		try {
			EYUtils.uiElement(viewButton).click();
			
			System.out.println("viewButton click");
		} catch (Exception e) {
			 viewButton=EYUtils.getElementPresent(By.xpath("//button[starts-with(@id,'workitem-goto-btn')]"));
			 EYUtils.uiElement(viewButton).click();
				System.out.println("viewButton click");
		}

		Thread.sleep(10000);
		js.executeScript("window.scrollBy(0,1000)");
		Thread.sleep(3000);
         WebElement manualActioncompleteButton=EYUtils.getElementPresent(By.cssSelector("input[value='Complete']"));
		try {
			js.executeScript("arguments[0].scrollIntoView(true);",manualActioncompleteButton);
			 EYUtils.uiElement(manualActioncompleteButton).click();
			System.out.println("manualActioncomplete");
		} catch (Exception e) {
			manualActioncompleteButton=EYUtils.getElementPresent(By.cssSelector("input[value='Complete']"));
			js.executeScript("arguments[0].scrollIntoView(true);", manualActioncompleteButton);
			UtilsFunctions.functionClick(manualActioncompleteButton);
			System.out.println("manualActioncompleteButton click");
		}

		Thread.sleep(5000);

		

		
	}
	

	
 public void workItem1LevelApprovalForSingleUser(String requestID, String testcaseType, String requestType, String managerName,
			String decision, String entitlement,String userName) throws InterruptedException {
		System.out.println("inside in workitems and execution start for 1 level approval :: workItems()");

		test = report.startTest(ResourceStrings.IIQ_MANAGE_USER_ACCESS_WORKITEMS);
		report.flush();
		test.assignCategory("managerApproval==> managerName is : " + managerName);
		test.assignCategory("decision : " + decision);
		JavascriptExecutor js=(JavascriptExecutor)driver;
		System.out.println("requestID :" + requestID);
		System.out.println("testcaseType :" + testcaseType);
		System.out.println("requestType :" + requestType);
		System.out.println("managerName :" + managerName);
		System.out.println("entitlement :" + entitlement);
		System.out.println("After flush");
		
		List<String> RolesList = new ArrayList<String>();
		String[] splitRole = entitlement.split("#");
		RolesList = Arrays.asList(splitRole);
		System.out.println("users list ==" + RolesList);
		int entitlementCount = RolesList.size();
		System.out.println("entitlementCount size() ==" + entitlementCount);// 1
		Thread.sleep(2000);
		
		List<String> userList = new ArrayList<String>();
		String[] splitArray = userName.split(",");
		Thread.sleep(2000);
		userList = Arrays.asList(splitArray);
		System.out.println("users list ==" + userList);//1
		int userCount = userList.size();
		System.out.println("userCount ==" + userCount);
		
		WebElement myWork = driver.findElement(By.xpath("//img[@alt='SailPoint Logo']//following::a[3]"));

		try {
			System.out.println("inside try workItems");
			// UtilsFunctions.functionClick(myworkLink);
			myWork.click();
			Thread.sleep(3000);
			UtilsFunctions.functionClick(workItemLink);
		} catch (StaleElementReferenceException exceptionMessage) {
			myWork = driver.findElement(By.xpath("//img[@alt='SailPoint Logo']//following::a[3]"));
			Thread.sleep(3000);
			myWork.click();
			Thread.sleep(3000);
			UtilsFunctions.functionClick(workItemLink);
			System.out.println(exceptionMessage.getMessage());

		}
		Thread.sleep(6000);
		TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
				ResourceStrings.IIQ_MANAGE_USER_ACCESS_ACCESSREQUEST_MANAGER_APPROVAL, report, test, driver);

		Thread.sleep(6000);

		try {
			UtilsFunctions.functionClick(workItemFilterButton);

		} catch (StaleElementReferenceException e) {
			UtilsFunctions.functionClick(workItemFilterButton);
		}

		Thread.sleep(3000);

		try {

			UtilsFunctions.functionClick(workItemAddIcon);
		} catch (StaleElementReferenceException e) {

			UtilsFunctions.functionClick(workItemAddIcon);
		}

		Thread.sleep(3000);
		try {
			UtilsFunctions.functionClick(workItemAddRequestedId);
		} catch (StaleElementReferenceException e) {
			UtilsFunctions.functionClick(workItemAddRequestedId);
		}

		Thread.sleep(6000);

		System.out.println("accessRequest  requstedID for workItems:" + requestID);
		Thread.sleep(8000);

		try {
			UtilsFunctions.functionTextBox(workItemAddRequestedIdSearchBox, requestID);
		} catch (StaleElementReferenceException e) {
			UtilsFunctions.functionTextBox(workItemAddRequestedIdSearchBox, requestID);
		}
		Thread.sleep(8000);
		try {

			UtilsFunctions.functionClick(applyButton);
		} catch (StaleElementReferenceException e) {

			UtilsFunctions.functionClick(applyButton);
		}
		Thread.sleep(3000);

		try {

			UtilsFunctions.functionClick(forwardOwnerButton);
		} catch (StaleElementReferenceException e) {
			UtilsFunctions.functionClick(forwardOwnerButton);
		}

		Thread.sleep(8000);

		try {

			UtilsFunctions.functionClick(forwardtoOwner);
		} catch (StaleElementReferenceException e) {

			UtilsFunctions.functionClick(forwardtoOwner);
		}

		Thread.sleep(3000);
		try {
			UtilsFunctions.functionTextBox(forwardtoOwner, managerName);
		} catch (StaleElementReferenceException e) {
			UtilsFunctions.functionTextBox(forwardtoOwner, managerName);
		}

		Thread.sleep(3000);
		try {
			forwardtoOwner.sendKeys(Keys.ENTER);
		} catch (Exception e) {
			forwardtoOwner.sendKeys(Keys.ENTER);
		}

		Thread.sleep(3000);
		try {
			UtilsFunctions.functionClick(forwardOKbutton);
		} catch (Exception e) {
			UtilsFunctions.functionClick(forwardOKbutton);
		}

		Thread.sleep(3000);
		try {
			UtilsFunctions.functionClick(viewButton);
			System.out.println("viewButton click");
		} catch (Exception e) {
			UtilsFunctions.functionClick(viewButton);
			System.out.println("viewButton click");
		}

		Thread.sleep(2000);

		TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
				ResourceStrings.IIQ_MANAGE_USER_ACCESS_ACCESSREQUEST_MANAGER_APPROVAL + ":" + decision, report, test,
				driver);


		System.out.println("before decision click");
		Thread.sleep(10000);
		if (testcaseType!=null && testcaseType.equalsIgnoreCase("SingleUserSingleAccess_ADD")
				&& requestType.equalsIgnoreCase("ADD")||testcaseType!= null && testcaseType.equalsIgnoreCase("SingleUserSingleAccess_REMOVE")&&requestType.equalsIgnoreCase("REMOVE")
				||testcaseType!=null && testcaseType.equalsIgnoreCase("SingleuserMultipleAccess_ADD")
						&& requestType.equalsIgnoreCase("ADD")||testcaseType!=null && testcaseType.equalsIgnoreCase("SingleuserMultipleAccess_REMOVE")
								&& requestType.equalsIgnoreCase("REMOVE")) {

			System.out.println("Inside if ADD or Remove ");
			System.out.println("testcaseType :" + testcaseType);
			System.out.println("requestType :" + requestType);
			System.out.println("decision :" + decision);
			Thread.sleep(8000);
			WebElement approvalBtns=EYUtils.getElementPresent(By.xpath("//button[contains(@id,'btnApproveApproval')]"));
			js.executeScript("arguments[0].scrollIntoView(true);", approvalBtns);
			System.out.println("approvalBtns  displayed : " );
			if (decision != null && decision.equalsIgnoreCase("Approve")&& entitlementCount == 1) {
				System.out.println("Approval button clicked");
				Thread.sleep(10000);
				try {
					System.out.println("Inside ADD or remove try block");
					if (approvalBtns.isDisplayed()) {
                       
						Thread.sleep(3000);
						js.executeScript("arguments[0].click();", approvalBtns);
					
					}
			

					Thread.sleep(8000);
					WebElement completebtn=EYUtils.getElementPresent(By.xpath("//button[text()='Complete']"));
					if (completebtn.isDisplayed()) {
						EYUtils.waitForElementVisibleWithPollingTime(completebtn, 30, 2);
						UtilsFunctions.functionClick(completebtn);
					}
					
				
				} catch (StaleElementReferenceException e) {
					System.out.println("Inside ADD or remove catch block");
					approvalBtns=EYUtils.getElementPresent(By.xpath("//button[contains(@id,'btnApproveApproval')]"));
					if (approvalBtns.isDisplayed()) {
						
						Thread.sleep(3000);
						js.executeScript("arguments[0].click();", approvalBtns);  
						EYUtils.uiElement(approvalBtns).click();
					}
			
					Thread.sleep(8000);
					WebElement completebtn=EYUtils.getElementPresent(By.xpath("//button[text()='Complete']"));
					if (completebtn.isDisplayed()) {
						EYUtils.waitForElementVisibleWithPollingTime(completebtn, 30, 2);
						EYUtils.uiElement(completebtn).click();
					
					}
				}

				
					TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
							ResourceStrings.IIQ_MANAGE_USER_ACCESS_ACCESSREQUEST_COMPLETE_APPROVAL, report, test,
							driver);

			Thread.sleep(5000);
			}

			else {
				
				WebElement approveAllBtns=EYUtils.getElementPresent(By.xpath("//button[contains(@id,'btnApproveAll')]"));
				Thread.sleep(10000);
				try {
					System.out.println("Inside else ADD or remove try block");
					if (approveAllBtns.isDisplayed()) {
                       
						Thread.sleep(3000);
						js.executeScript("arguments[0].click();", approveAllBtns);
					
					}
			

					Thread.sleep(8000);
					WebElement completebtn=EYUtils.getElementPresent(By.xpath("//button[text()='Complete']"));
					if (completebtn.isDisplayed()) {
						EYUtils.uiElement(completebtn).click();
						TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
								ResourceStrings.IIQ_MANAGE_USER_ACCESS_ACCESSREQUEST_COMPLETE_APPROVAL, report, test,
								driver);
					}
					
				
				} catch (StaleElementReferenceException e) {
					System.out.println("Inside else ADD or remove catch block");
					approveAllBtns=EYUtils.getElementPresent(By.xpath("//button[contains(@id,'btnApproveAll')]"));
					if (approveAllBtns.isDisplayed()) {
						
						Thread.sleep(3000);
						js.executeScript("arguments[0].click();", approveAllBtns);  
					}
			
					Thread.sleep(8000);
					WebElement completebtn=EYUtils.getElementPresent(By.xpath("//button[text()='Complete']"));
					if (completebtn.isDisplayed()) {
						EYUtils.uiElement(completebtn).click();
						TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
								ResourceStrings.IIQ_MANAGE_USER_ACCESS_ACCESSREQUEST_COMPLETE_APPROVAL, report, test,
								driver);
					}
				}
				
				

			

			}

		}
	
		
		else if (testcaseType != null && testcaseType.equalsIgnoreCase("SingleUserSingleAccess_DENY")
				&& requestType.equalsIgnoreCase("DENY")||testcaseType!=null && testcaseType.equalsIgnoreCase("SingleuserMultipleAccess_DENY")
						&& requestType.equalsIgnoreCase("DENY")) {
			System.out.println("inside else if rejectbutton for single user single access DENY");
			
			Thread.sleep(4000);
			if (decision != null && decision.equalsIgnoreCase("Reject") && entitlementCount == 1) {
				WebElement rejectBtns=EYUtils.getElementPresent(By.xpath("//button[contains(@id,'btnRejectApproval')]"));
				try {
				Thread.sleep(9000);
				
					if (rejectBtns.isDisplayed()) {
						System.out.println("reject button displayed");
						EYUtils.waitForElementVisibleWithPollingTime(rejectBtns, 30, 2);
						EYUtils.uiElement(rejectBtns).click();
					}
				
					Thread.sleep(9000);
					
					WebElement completeButtons=EYUtils.getElementPresent(By.xpath("//button[text()='Complete']"));
					if (completeButtons.isDisplayed()) {
						EYUtils.waitForElementVisibleWithPollingTime(completeButton, 30, 2);
						EYUtils.uiElement(completeButtons).click();

						TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
								ResourceStrings.IIQ_MANAGE_USER_ACCESS_ACCESSREQUEST_COMPLETE_APPROVAL, report, test,
								driver);
						Thread.sleep(10000);
					}
					
					
				} catch (StaleElementReferenceException e) {
					rejectBtns=EYUtils.getElementPresent(By.xpath("//button[contains(@id,'btnRejectApproval')]"));
					if (rejectBtns.isDisplayed()) {
						EYUtils.waitForElementVisibleWithPollingTime(rejectBtns, 30, 2);
						EYUtils.uiElement(rejectBtns).click();
					}
				
					Thread.sleep(9000);
					WebElement completeButtons=EYUtils.getElementPresent(By.xpath("//button[text()='Complete']"));
					if (completeButtons.isDisplayed()) {
						EYUtils.waitForElementVisibleWithPollingTime(completeButton, 30, 2);
						EYUtils.uiElement(completeButtons).click();

						TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
								ResourceStrings.IIQ_MANAGE_USER_ACCESS_ACCESSREQUEST_COMPLETE_APPROVAL, report, test,
								driver);
						
					}
					
				}
				
			}

			else {
				
				WebElement rejectAllBtns=EYUtils.getElementPresent(By.xpath("//button[contains(@id,'btnRejectAll')]"));
				
				Thread.sleep(9000);
				try {
					System.out.println("Inside else deny try block");
					if (rejectAllBtns.isDisplayed()) {
						EYUtils.waitForElementVisibleWithPollingTime(rejectAllBtns, 30, 2);
						//UtilsFunctions.functionClick(rejectBtns);
						EYUtils.uiElement(rejectAllBtns).click();
					}
				
					Thread.sleep(9000);
					WebElement completeButtons=EYUtils.getElementPresent(By.xpath("//button[text()='Complete']"));
					if (completeButtons.isDisplayed()) {
						EYUtils.waitForElementVisibleWithPollingTime(completeButtons, 30, 2);
						EYUtils.uiElement(completeButtons).click();

						TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
								ResourceStrings.IIQ_MANAGE_USER_ACCESS_ACCESSREQUEST_COMPLETE_APPROVAL, report, test,
								driver);
					}
					
					
				} catch (StaleElementReferenceException e) {

					System.out.println("Inside else deny catch block");
					rejectAllBtns=EYUtils.getElementPresent(By.xpath("//button[contains(@id,'btnRejectAll')]"));
					if (rejectAllBtns.isDisplayed()) {
						EYUtils.waitForElementVisibleWithPollingTime(rejectAllBtns, 30, 2);
						EYUtils.uiElement(rejectAllBtns).click();
					}
				
					Thread.sleep(9000);
					WebElement completeButtons=EYUtils.getElementPresent(By.xpath("//button[text()='Complete']"));
					if (completeButtons.isDisplayed()) {
						EYUtils.waitForElementVisibleWithPollingTime(completeButtons, 30, 2);
						EYUtils.uiElement(completeButtons).click();

						TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
								ResourceStrings.IIQ_MANAGE_USER_ACCESS_ACCESSREQUEST_COMPLETE_APPROVAL, report, test,
								driver);
					}
					
				}
				
				
				
		
				

			}
		}

		System.out.println("outside form workitems and execution end for 1 level approval :: workItems()");
		
		report.endTest(test);

	}
	

	

	public void workItemforMultipleUser1LevelApproval(String requestID, String testcaseType, String requestType, String ownerName,String managerName,
			String decision, String entitlement,String userName) throws InterruptedException {
		System.out.println("inside in workitems and execution start for 1 level approval for multiple User");

		test = report.startTest(ResourceStrings.IIQ_MANAGE_USER_ACCESS_WORKITEMS);
		report.flush();
		test.assignCategory("OwnerApproval==> ownerName is : " + ownerName);
		test.assignCategory("decision : " + decision);
		JavascriptExecutor js=(JavascriptExecutor)driver;
		Reporter.log("requestID :" + requestID);
		Reporter.log("testcaseType :" + testcaseType);
		Reporter.log("requestType :" + requestType);
		Reporter.log("ownerName :" + ownerName);
		Reporter.log("decision :" + decision);
		Reporter.log("entitlement :" + entitlement);
		Reporter.log("userName :" + userName);
		Reporter.log("After flush");
	
		
		WebElement myWork = driver.findElement(By.xpath("//img[@alt='SailPoint Logo']//following::a[3]"));

		try {
			System.out.println("inside try workItems");
			myWork.click();
			Thread.sleep(3000);
			UtilsFunctions.functionClick(workItemLink);
		} catch (StaleElementReferenceException exceptionMessage) {
			myWork = driver.findElement(By.xpath("//img[@alt='SailPoint Logo']//following::a[3]"));
			Thread.sleep(3000);
			myWork.click();
			Thread.sleep(3000);
			UtilsFunctions.functionClick(workItemLink);
			System.out.println(exceptionMessage.getMessage());

		}
		Thread.sleep(6000);
		TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
				ResourceStrings.IIQ_MANAGE_USER_ACCESS_ACCESSREQUEST_OWNERAPPROVAL, report, test, driver);

		Thread.sleep(6000);
		
		List<String> idList = new ArrayList<String>();
		
		String[] splitid = requestID.split("/");
		
		idList = Arrays.asList(splitid);
		
		System.out.println("idList ==" + idList);//multiple
		
	
		List<String> entitlementList = new ArrayList<String>();
		String[] splitRole = entitlement.split("#");
		entitlementList = Arrays.asList(splitRole);
		System.out.println("entitlementCount ==" + entitlementList);
		int entitlementCount = entitlementList.size();
		System.out.println("entitlementCount ==" + entitlementCount);// 1
		

		
		for (String requestedID : idList) {
			System.out.println("requestedID from list is ==" + requestedID);
			WebElement workItemFilterButton=EYUtils.getElementPresent(By.xpath("//button[@id='cardListFilterBtn']"));
			try {
				EYUtils.uiElement(workItemFilterButton).click();
			} catch (StaleElementReferenceException e) {
				workItemFilterButton=EYUtils.getElementPresent(By.xpath("//button[@id='cardListFilterBtn']"));
				EYUtils.uiElement(workItemFilterButton).click();
			}
			Thread.sleep(4000);
			WebElement workItemAddIcon=EYUtils.getElementPresent(By.xpath("//button[@id='-btn']"));
			try {
				EYUtils.uiElement(workItemAddIcon).click();
			} catch (StaleElementReferenceException e) {
				 workItemAddIcon=EYUtils.getElementPresent(By.xpath("//button[@id='-btn']"));
				EYUtils.uiElement(workItemAddIcon).click();
			}
		
			Thread.sleep(6000);


			
			List<WebElement>allID=driver.findElements(By.xpath("//input[@value='Add Filter']//following::a[@role='menuitem']"));
			try {
				for (int i = 0; i <allID.size(); i++) {
					WebElement requestid=allID.get(i);
					String alldata=requestid.getText();
					if (alldata.contains("Access Request ID")) {
						requestid.click();
					}
				}
				
			} catch (Exception e) {
				allID=driver.findElements(By.xpath("//input[@value='Add Filter']//following::a[@role='menuitem']"));
				for (int i = 0; i <allID.size(); i++) {
					WebElement requestid=allID.get(i);
					String alldata=requestid.getText();
					if (alldata.contains("Access Request ID")) {
						requestid.click();
					}
				}
			}
		
			
			
			Thread.sleep(6000);
			
			WebElement workItemAddRequestedIdSearchBox=EYUtils.getElementPresent(By.xpath("//input[starts-with(@id,'cardListFilterPanelAdditionalItem')]"));
			
			try {
				EYUtils.uiElement(workItemAddRequestedIdSearchBox).sendKeys(requestedID);
				
			} catch (StaleElementReferenceException e) {
				workItemAddRequestedIdSearchBox=EYUtils.getElementPresent(By.xpath("//input[starts-with(@id,'cardListFilterPanelAdditionalItem')]"));
				EYUtils.uiElement(workItemAddRequestedIdSearchBox).sendKeys(requestedID);
			}
			WebElement applyButton=EYUtils.getElementPresent(By.xpath("//button[@id='cardListFilterPanelApplyBtn']"));
			Thread.sleep(4000);
			try {
				EYUtils.uiElement(applyButton).click();
			} catch (StaleElementReferenceException e) {
				applyButton=EYUtils.getElementPresent(By.xpath("//button[@id='cardListFilterPanelApplyBtn']"));
				EYUtils.uiElement(applyButton).click();
			}
			Thread.sleep(3000);

	
			WebElement forwardOwnerButton=EYUtils.getElementPresent(By.cssSelector("button[id^='workitem-forward-btn']"));
			
			try {
				EYUtils.uiElement(forwardOwnerButton).click();
			} catch (StaleElementReferenceException e) {
				forwardOwnerButton=EYUtils.getElementPresent(By.cssSelector("button[id^='workitem-forward-btn']"));
				EYUtils.uiElement(forwardOwnerButton).click();
			}

			Thread.sleep(8000);
			WebElement forwardtoOwner=EYUtils.getElementPresent(By.xpath("//input[@id='ownerNameSuggestBoxWorkItemListForward']"));
			try {
				EYUtils.uiElement(forwardtoOwner).sendKeys(ownerName);
				Thread.sleep(2000);
				EYUtils.uiElement(forwardtoOwner).sendKeys(Keys.ENTER);
		} catch (StaleElementReferenceException e) {
			forwardtoOwner=EYUtils.getElementPresent(By.xpath("//input[@id='ownerNameSuggestBoxWorkItemListForward']"));
			EYUtils.uiElement(forwardtoOwner).sendKeys(ownerName);
			Thread.sleep(2000);
			EYUtils.uiElement(forwardtoOwner).sendKeys(Keys.ENTER);
		}
			

			WebElement forwardOKbutton=EYUtils.getElementPresent(By.xpath("(//button[@ng-repeat='button in buttons'])[2]"));
			
		
			Thread.sleep(3000);
			try {
				
				EYUtils.uiElement(forwardOKbutton).click();
			} catch (StaleElementReferenceException e) {
				forwardOKbutton=EYUtils.getElementPresent(By.xpath("(//button[@ng-repeat='button in buttons'])[2]"));
				EYUtils.uiElement(forwardOKbutton).click();
			}
			
			Thread.sleep(3000);
			
			
			WebElement viewButton=EYUtils.getElementPresent(By.xpath("//button[starts-with(@id,'workitem-goto-btn')]"));
			try {
				EYUtils.uiElement(viewButton).click();
				System.out.println("viewButton click");
			} catch (Exception e) {
				viewButton=EYUtils.getElementPresent(By.xpath("//button[starts-with(@id,'workitem-goto-btn')]"));
				EYUtils.uiElement(viewButton).click();
				System.out.println("viewButton click");
			}

			Thread.sleep(10000);


			TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
					ResourceStrings.IIQ_MANAGE_USER_ACCESS_ACCESSREQUEST_OWNERAPPROVAL + ":" + decision, report, test,
					driver);

			
			System.out.println("before decision click");
			
			
			
			if (testcaseType!=null && testcaseType.equalsIgnoreCase("MultipleuserSingleAccess_ADD")
					&& requestType.equalsIgnoreCase("ADD")||testcaseType!= null && testcaseType.equalsIgnoreCase("MultipleuserSingleAccess_REMOVE")&&requestType.equalsIgnoreCase("REMOVE")
					) {
				
				System.out.println("Inside ADD or remove block");
				System.out.println("testcaseType :" + testcaseType);
				System.out.println("requestType :" + requestType);
				System.out.println("decision :" + decision);
				Thread.sleep(8000);
				WebElement approvalBtns=EYUtils.getElementPresent(By.xpath("//button[contains(@id,'btnApproveApproval')]"));
				js.executeScript("arguments[0].scrollIntoView(true);", approvalBtns);
				System.out.println("approvalBtns  displayed : " );
				if (decision != null && decision.equalsIgnoreCase("Approve")&& entitlementCount == 1) {
					System.out.println("Inside if block for take decision approve/reject");
					Thread.sleep(10000);
					try {
						System.out.println("Inside ADD or remove try block");
						if (approvalBtns.isDisplayed()) {
							System.out.println("approve button displayed");
							Thread.sleep(4000);
							js.executeScript("arguments[0].click();", approvalBtns);
							System.out.println("approve clicked");
							System.out.println("approvalBtns click after");
							
						}
						Thread.sleep(6000);
						WebElement completebtn=EYUtils.getElementPresent(By.xpath("//button[text()='Complete']"));
						if (completebtn.isDisplayed()) {
							System.out.println("completebtn button displayed");
							EYUtils.uiElement(completebtn).click();
						}
						
					
					} catch (StaleElementReferenceException e) {
						System.out.println("Inside ADD or remove catch block");
						approvalBtns=EYUtils.getElementPresent(By.xpath("//button[contains(@id,'btnApproveApproval')]"));
						if (approvalBtns.isDisplayed()) {
							
							Thread.sleep(3000);
							js.executeScript("arguments[0].click();", approvalBtns);  
						}
						Thread.sleep(3000);
						WebElement completebtn=EYUtils.getElementPresent(By.xpath("//button[text()='Complete']"));
						Thread.sleep(8000);
						if (completebtn.isDisplayed()) {
							System.out.println("completebtn button displayed");
							EYUtils.waitForElementVisibleWithPollingTime(completebtn, 30, 2);
							EYUtils.uiElement(completebtn).click();
						}
					}
					Thread.sleep(10000);
					
						TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
								ResourceStrings.IIQ_MANAGE_USER_ACCESS_ACCESSREQUEST_COMPLETE_APPROVAL, report, test,
								driver);

				Thread.sleep(5000);
				}

				else {
					
					WebElement approveAllBtns=EYUtils.getElementPresent(By.xpath("//button[contains(@id,'btnApproveAll')]"));
					Thread.sleep(10000);
					try {
						System.out.println("Inside else ADD or remove try block");
						if (approveAllBtns.isDisplayed()) {
	                       
							Thread.sleep(3000);
							//js.executeScript("arguments[0].click();", approveAllBtns);
							EYUtils.waitForElementClickable(approvalBtns, 30, 2);
							EYUtils.uiElement(approvalBtns).click();
						}
				

						Thread.sleep(8000);
						WebElement completebtn=EYUtils.getElementPresent(By.xpath("//button[text()='Complete']"));
						if (completebtn.isDisplayed()) {
							EYUtils.waitForElementVisibleWithPollingTime(completebtn, 30, 2);
							EYUtils.uiElement(completebtn).click();
						}
						
					
					} catch (StaleElementReferenceException e) {
						System.out.println("Inside else ADD or remove catch block");
						approveAllBtns=EYUtils.getElementPresent(By.xpath("//button[contains(@id,'btnApproveAll')]"));
						if (approveAllBtns.isDisplayed()) {
							
							Thread.sleep(3000);
							js.executeScript("arguments[0].click();", approveAllBtns);  
							EYUtils.uiElement(approveAllBtns).click();
						}
				
						Thread.sleep(8000);
						WebElement completebtn=EYUtils.getElementPresent(By.xpath("//button[text()='Complete']"));
						if (completebtn.isDisplayed()) {
							EYUtils.uiElement(completebtn).click();
						}
					}
					Thread.sleep(3000);
						TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
								ResourceStrings.IIQ_MANAGE_USER_ACCESS_ACCESSREQUEST_COMPLETE_APPROVAL, report, test,
								driver);

				

				}

			}
		
	
			else if (testcaseType!=null && testcaseType.equalsIgnoreCase("MultipleuserSingleAccess_DENY")&& requestType.equalsIgnoreCase("DENY")) {
				
				System.out.println("Inside DEny block");
				System.out.println("testcaseType :" + testcaseType);
				System.out.println("requestType :" + requestType);
				System.out.println("decision :" + decision);
				Thread.sleep(8000);
				
				
				
				WebElement rejectBtn=EYUtils.getElementPresent(By.xpath("//button[contains(@id,'btnRejectApproval')]"));
				Thread.sleep(4000);
				if (decision != null && decision. equalsIgnoreCase("Reject") && entitlementCount == 1) {
					Thread.sleep(9000);
					try {
						uiElement(rejectBtn).click();
						Thread.sleep(4000);
						WebElement completebtn=EYUtils.getElementPresent(By.xpath("//button[text()='Complete']"));
						uiElement(completebtn).click();
						TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
								ResourceStrings.IIQ_MANAGE_USER_ACCESS_ACCESSREQUEST_OWNERAPPROVAL+":"+ownerName + "Decision is :" + decision, report, test,
								driver);
					} catch (StaleElementReferenceException e) {
						rejectBtn=EYUtils.getElementPresent(By.xpath("//button[contains(@id,'btnRejectApproval')]"));
						uiElement(rejectBtn).click();
						Thread.sleep(4000);
						WebElement completebtn=EYUtils.getElementPresent(By.xpath("//button[text()='Complete']"));
						uiElement(completebtn).click();
						TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
								ResourceStrings.IIQ_MANAGE_USER_ACCESS_ACCESSREQUEST_OWNERAPPROVAL+":"+ownerName + "Decision is :" + decision, report, test,
								driver);
					}
			}
				
				
				
				
				
			}
			

			
			
			
			Thread.sleep(10000);
		
		//after forloop
		System.out.println("After for loop");
		WebElement workItemFilterButtons=EYUtils.getElementPresent(By.xpath("//button[@id='cardListFilterBtn']"));
		try {
			EYUtils.uiElement(workItemFilterButtons).click();
		} catch (StaleElementReferenceException e) {
			EYUtils.uiElement(workItemFilterButtons).click();
		}
		
		Thread.sleep(2000);
		WebElement cancelButton=EYUtils.getElementPresent(By.xpath("//i[@class='fa fa-times fa-fw']"));
		try {
			EYUtils.uiElement(cancelButton).click();
		} catch (StaleElementReferenceException e) {
			EYUtils.uiElement(cancelButton).click();
		}

	
			Thread.sleep(2000);
		
			WebElement applyButtons=EYUtils.getElementPresent(By.xpath("//button[@id='cardListFilterPanelApplyBtn']"));
			try {
				EYUtils.uiElement(applyButtons).click();
			} catch (StaleElementReferenceException e) {
				applyButtons=EYUtils.getElementPresent(By.xpath("//button[@id='cardListFilterPanelApplyBtn']"));
				EYUtils.uiElement(applyButtons).click();
			}
		
			Thread.sleep(3000);
		}
            report.endTest(test);
	}
	

	
	
	
	public void workItemManagerAndOwnerApproval(String requestID, String testcaseType, String requestType,String managerName, String ownerName,
			String decision, String entitlement) throws InterruptedException {
		System.out.println("inside workItemsManagerAndOwnerApproval 2level approval");

		test = report.startTest(ResourceStrings.IIQ_MANAGE_USER_ACCESS_WORKITEMS);
		report.flush();
		test.assignCategory("Manager&OwnerApproval ==> ManagerName  is : " + managerName);
		test.assignCategory("Manager&OwnerApproval==> ownerName is : " + ownerName);
		test.assignCategory("decision : " + decision);
		test.assignCategory("entitlement : " + entitlement);
		System.out.println("requestID :" + requestID);
		System.out.println("testcaseType :" + testcaseType);
		System.out.println("requestType :" + requestType);
		System.out.println("decision :" + decision);
		System.out.println("managerName :" + managerName);
		System.out.println("ownerName :" + ownerName);
		System.out.println("ownerName :" + entitlement);
		System.out.println("After flush");
		JavascriptExecutor js=(JavascriptExecutor)driver;
		List<String> RolesList = new ArrayList<String>();
	//	String[] splitRole = entitlement.split("#");
	//	RolesList = Arrays.asList(splitRole);
		System.out.println("entitlementCount ==" + RolesList);
		int entitlementCount = RolesList.size();
		System.out.println("entitlementCount ==" + entitlementCount);// 1
		Thread.sleep(10000);
		// UtilsFunctions.PageLoadedVerification();
		WebElement myWork = driver.findElement(By.xpath("//img[@alt='SailPoint Logo']//following::a[3]"));
		try {
			System.out.println("inside try workItemsManagerAndOwnerApproval");
			// UtilsFunctions.functionClick(myworkLink);
			myWork.click();
			Thread.sleep(3000);
			UtilsFunctions.functionClick(workItemLink);
		} catch (StaleElementReferenceException exceptionMessage) {
			myWork = driver.findElement(By.xpath("//img[@alt='SailPoint Logo']//following::a[3]"));
			Thread.sleep(3000);
			myWork.click();
			Thread.sleep(3000);
			UtilsFunctions.functionClick(workItemLink);

		}
		Thread.sleep(6000);
		TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
				ResourceStrings.IIQ_MANAGE_USER_ACCESS_WORKITEMS, report, test, driver);

		Thread.sleep(6000);

		try {
			UtilsFunctions.functionClick(workItemFilterButton);

		} catch (StaleElementReferenceException e) {
			UtilsFunctions.functionClick(workItemFilterButton);
		}

		Thread.sleep(3000);

		try {

			UtilsFunctions.functionClick(workItemAddIcon);
		} catch (StaleElementReferenceException e) {

			UtilsFunctions.functionClick(workItemAddIcon);
		}

		Thread.sleep(3000);
		try {
			UtilsFunctions.functionClick(workItemAddRequestedId);
		} catch (StaleElementReferenceException e) {
			UtilsFunctions.functionClick(workItemAddRequestedId);
		}
		Thread.sleep(8000);
		System.out.println("accessRequest  requstedID for workItems:" + requestID);
		try {
			UtilsFunctions.functionTextBox(workItemAddRequestedIdSearchBox, requestID);
		} catch (StaleElementReferenceException e) {
			UtilsFunctions.functionTextBox(workItemAddRequestedIdSearchBox, requestID);
		}
		Thread.sleep(8000);
		try {

			UtilsFunctions.functionClick(applyButton);
		} catch (StaleElementReferenceException e) {

			UtilsFunctions.functionClick(applyButton);
		}
		Thread.sleep(3000);
		
		//forward to manager
		try {

			UtilsFunctions.functionClick(forwardOwnerButton);
		} catch (StaleElementReferenceException e) {
			UtilsFunctions.functionClick(forwardOwnerButton);
		}

		Thread.sleep(8000);
		try {

			UtilsFunctions.functionClick(forwardtoOwner);
		} catch (StaleElementReferenceException e) {

			UtilsFunctions.functionClick(forwardtoOwner);
		}

		Thread.sleep(3000);
		try {
			UtilsFunctions.functionTextBox(forwardtoOwner, managerName);
		} catch (StaleElementReferenceException e) {
			UtilsFunctions.functionTextBox(forwardtoOwner, managerName);
		}

		Thread.sleep(3000);
		try {
			forwardtoOwner.sendKeys(Keys.ENTER);
		} catch (Exception e) {
			forwardtoOwner.sendKeys(Keys.ENTER);
		}

		Thread.sleep(3000);
		try {
			UtilsFunctions.functionClick(forwardOKbutton);
		} catch (Exception e) {
			UtilsFunctions.functionClick(forwardOKbutton);
		}
		
		try {
			UtilsFunctions.functionClick(viewButton);
		} catch (Exception e) {
			UtilsFunctions.functionClick(viewButton);
		}
         System.out.println("Insert to manager approval");
		Thread.sleep(10000);
		WebElement approvalButtons=EYUtils.getElementPresent(By.xpath("//button[contains(@id,'btnApproveApproval')]"));
	
		if (testcaseType != null && testcaseType.equalsIgnoreCase("SingleUserSingleAccess_ADD")&&requestType.equalsIgnoreCase("ADD")||testcaseType.equalsIgnoreCase("SingleUserSingleAccess_REMOVE")&&requestType.equalsIgnoreCase("REMOVE")||testcaseType.equalsIgnoreCase("SingleUserSingleAccess_DENY")&&requestType.equalsIgnoreCase("DENY")||
				testcaseType != null && testcaseType.equalsIgnoreCase("SingleUserMultipleAccess_ADD")&&requestType.equalsIgnoreCase("ADD")||testcaseType.equalsIgnoreCase("SingleUserMultipleAccess_REMOVE")&&requestType.equalsIgnoreCase("REMOVE")||testcaseType. equalsIgnoreCase("SingleUserMultipleAccess_DENY")&&requestType.equalsIgnoreCase("DENY")) {
			if (entitlementCount == 1) {
				System.out.println("Inside sub if manager approval");
				Thread.sleep(10000);
		
				try {
					uiElement(approvalButtons).click();
					Thread.sleep(3000);
					
					WebElement completebtn=EYUtils.getElementPresent(By.xpath("//button[text()='Complete']"));
					uiElement(completebtn).click();
					Thread.sleep(3000);
				} catch (StaleElementReferenceException e) {
					approvalButtons=EYUtils.getElementPresent(By.xpath("//button[contains(@id,'btnApproveApproval')]"));
					uiElement(approvalButtons).click();
					Thread.sleep(3000);
					WebElement completebtn=EYUtils.getElementPresent(By.xpath("//button[text()='Complete']"));
					uiElement(completebtn).click();
					Thread.sleep(3000);
				
				}	
				
				
				
				
				Thread.sleep(10000);
				TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
						ResourceStrings.IIQ_MANAGE_USER_ACCESS_ACCESSREQUEST_MANAGERAPPROVAL + ":" + decision, report, test,
						driver);
				 System.out.println("manager Approval completed for single entitement");
				}
			else {
				WebElement approveAllBtns=EYUtils.getElementPresent(By.xpath("//button[contains(@id,'btnApproveAll')]"));
				Thread.sleep(10000);
				try {
					System.out.println("Inside else ADD or remove try block");
					if (approveAllBtns.isDisplayed()) {
                       
						Thread.sleep(3000);
						js.executeScript("arguments[0].click();", approveAllBtns);
				
					}
			

					Thread.sleep(8000);
					WebElement completebtn=EYUtils.getElementPresent(By.xpath("//button[text()='Complete']"));
					uiElement(completebtn).click();
					Thread.sleep(2000);
					TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
							ResourceStrings.IIQ_MANAGE_USER_ACCESS_ACCESSREQUEST_MANAGERAPPROVAL + ":" + decision, report, test,
							driver);
					
				
				} catch (StaleElementReferenceException e) {
					System.out.println("Inside else ADD or remove catch block");
					approveAllBtns=EYUtils.getElementPresent(By.xpath("//button[contains(@id,'btnApproveAll')]"));
					if (approveAllBtns.isDisplayed()) {
						
						Thread.sleep(3000);
						js.executeScript("arguments[0].click();", approveAllBtns);  
						EYUtils.uiElement(approveAllBtns).click();
					}
			
					Thread.sleep(8000);
					WebElement completebtn=EYUtils.getElementPresent(By.xpath("//button[text()='Complete']"));
					uiElement(completebtn).click();
					Thread.sleep(2000);
					TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
							ResourceStrings.IIQ_MANAGE_USER_ACCESS_ACCESSREQUEST_MANAGERAPPROVAL + ":" + decision, report, test,
							driver);
				}
				
                  System.out.println("manager Approval completed for multiple entitement");
			}
			
		}
		Thread.sleep(5000);
		
		
		
		
		
		
		
		//forward to owner

		System.out.println("Forward to ownerStart");
		
    	WebElement forwardButtons=EYUtils.getElementPresent(By.xpath("//button[starts-with(@id,'workitem-forward-btn')]"));
    	try {
    		uiElement(forwardButtons).click();
    		Thread.sleep(3000);
		} catch (StaleElementReferenceException e) {
			forwardButtons=EYUtils.getElementPresent(By.xpath("//button[starts-with(@id,'workitem-forward-btn')]"));
			Thread.sleep(3000);
			uiElement(forwardButtons).click();
    		
		}
    	Thread.sleep(4000);
     WebElement forwardtoOwnertextbox=EYUtils.getElementPresent(By.xpath("//input[@id='ownerNameSuggestBoxWorkItemListForward']"));
		try {
			System.out.println("ownerName insert");
			uiElement(forwardtoOwnertextbox).sendKeys(ownerName);
			Thread.sleep(3000);
			uiElement(forwardtoOwnertextbox).sendKeys(Keys.ENTER);
		} catch (StaleElementReferenceException e) {
			forwardtoOwnertextbox=EYUtils.getElementPresent(By.xpath("//input[@id='ownerNameSuggestBoxWorkItemListForward']"));
			uiElement(forwardtoOwnertextbox).sendKeys(ownerName);
			Thread.sleep(3000);
			uiElement(forwardtoOwnertextbox).sendKeys(Keys.ENTER);
		}
		Thread.sleep(4000);
		WebElement forwardOkBtn=EYUtils.getElementPresent(By.xpath("(//button[@ng-repeat='button in buttons'])[2]"));
		Thread.sleep(3000);

		try {
			uiElement(forwardOkBtn).click();
		} catch (StaleElementReferenceException e) {
			forwardOkBtn=EYUtils.getElementPresent(By.xpath("(//button[@ng-repeat='button in buttons'])[2]"));
			uiElement(forwardOkBtn).click();
		}

		Thread.sleep(3000);
		WebElement viewButtonElement=EYUtils.getElementPresent(By.xpath("//button[starts-with(@id,'workitem-goto-btn')]"));
	
		try {
			uiElement(viewButtonElement).click();
		} catch (Exception e) {
			viewButtonElement=EYUtils.getElementPresent(By.xpath("//button[starts-with(@id,'workitem-goto-btn')]"));
			uiElement(viewButtonElement).click();
		}

		Thread.sleep(7000);



	

		if (testcaseType != null && testcaseType.equalsIgnoreCase("SingleUserSingleAccess_ADD")
				&& requestType.equalsIgnoreCase("ADD")||testcaseType != null && testcaseType.equalsIgnoreCase("SingleUserSingleAccess_REMOVE")
				&& requestType.equalsIgnoreCase("REMOVE")||
				testcaseType != null && testcaseType.equalsIgnoreCase("SingleUserMultipleAccess_ADD")
				&& requestType.equalsIgnoreCase("ADD")||testcaseType != null && testcaseType.equalsIgnoreCase("SingleUserMUltipleAccess_REMOVE")
				&& requestType.equalsIgnoreCase("REMOVE")) {
			
			WebElement approvalBtns=EYUtils.getElementPresent(By.xpath("//button[contains(@id,'btnApproveApproval')]"));
			System.out.println("Inside main if add or remove");
			if (decision != null && decision. equalsIgnoreCase("Approve") && entitlementCount == 1) {
				System.out.println("Inside sub if");
				try {
					uiElement(approvalBtns).click();
					Thread.sleep(3000);
					System.out.println("approval btn click in tryblock");
					WebElement completebtn=EYUtils.getElementPresent(By.xpath("//button[text()='Complete']"));
					uiElement(completebtn).click();
					System.out.println("complete btn click in tryblock");
					Thread.sleep(3000);
				} catch (StaleElementReferenceException e) {
					approvalBtns=EYUtils.getElementPresent(By.xpath("//button[contains(@id,'btnApproveApproval')]"));
					uiElement(approvalBtns).click();
					Thread.sleep(3000);
					System.out.println("approval btn click in tryblock");
					WebElement completebtn=EYUtils.getElementPresent(By.xpath("//button[text()='Complete']"));
					uiElement(completebtn).click();
					System.out.println("complete btn click in tryblock");
					Thread.sleep(3000);
					TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
							ResourceStrings.IIQ_MANAGE_USER_ACCESS_ACCESSREQUEST_OWNERAPPROVAL+":"+ownerName + "Decision is :" + decision, report, test,
							driver);
				}		
	}

			else {
				WebElement approveAllBtns=EYUtils.getElementPresent(By.xpath("//button[contains(@id,'btnApproveAll')]"));
				Thread.sleep(10000);
				try {
					System.out.println("Inside else ADD or remove try block owner Approval");
					if (approveAllBtns.isDisplayed()) {
                       
						Thread.sleep(3000);
						js.executeScript("arguments[0].click();", approveAllBtns);
						Thread.sleep(3000);
						WebElement completebtn=EYUtils.getElementPresent(By.xpath("//button[text()='Complete']"));
						uiElement(completebtn).click();
						System.out.println("complete btn click in catchblock");
					}
			

					
				
				} catch (StaleElementReferenceException e) {
					System.out.println("Inside else ADD or remove catch block");
					approveAllBtns=EYUtils.getElementPresent(By.xpath("//button[contains(@id,'btnApproveAll')]"));
					if (approveAllBtns.isDisplayed()) {
						
						Thread.sleep(3000);
						js.executeScript("arguments[0].click();", approveAllBtns);  
						EYUtils.uiElement(approveAllBtns).click();
						Thread.sleep(3000);
						WebElement completebtn=EYUtils.getElementPresent(By.xpath("//button[text()='Complete']"));
						uiElement(completebtn).click();
						System.out.println("complete btn click in catchblock");
					}
			
			
				}
				

			}

		}

		else if (testcaseType != null && testcaseType.equalsIgnoreCase("SingleUserSingleAccess_DENY")
				&& requestType.equalsIgnoreCase("DENY")||testcaseType != null && testcaseType.equalsIgnoreCase("SingleUserMultipleAccess_DENY")
						&& requestType.equalsIgnoreCase("DENY")) {
			WebElement rejectBtn=EYUtils.getElementPresent(By.xpath("//button[contains(@id,'btnRejectApproval')]"));
			Thread.sleep(4000);
			if (decision != null && decision. equalsIgnoreCase("Reject") && entitlementCount == 1) {
				Thread.sleep(9000);
				try {
					uiElement(rejectBtn).click();
					Thread.sleep(4000);
					WebElement completebtn=EYUtils.getElementPresent(By.xpath("//button[text()='Complete']"));
					uiElement(completebtn).click();
					TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
							ResourceStrings.IIQ_MANAGE_USER_ACCESS_ACCESSREQUEST_OWNERAPPROVAL+":"+ownerName + "Decision is :" + decision, report, test,
							driver);
				} catch (StaleElementReferenceException e) {
					rejectBtn=EYUtils.getElementPresent(By.xpath("//button[contains(@id,'btnRejectApproval')]"));
					uiElement(rejectBtn).click();
					Thread.sleep(4000);
					WebElement completebtn=EYUtils.getElementPresent(By.xpath("//button[text()='Complete']"));
					uiElement(completebtn).click();
					TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
							ResourceStrings.IIQ_MANAGE_USER_ACCESS_ACCESSREQUEST_OWNERAPPROVAL+":"+ownerName + "Decision is :" + decision, report, test,
							driver);
				}
		}

			else {
				
				WebElement rejectAllBtns=EYUtils.getElementPresent(By.xpath("//button[contains(@id,'btnRejectAll')]"));
				Thread.sleep(9000);
				try {
					System.out.println("Inside else deny try block");
					if (rejectAllBtns.isDisplayed()) {
						EYUtils.waitForElementVisibleWithPollingTime(rejectAllBtns, 30, 2);
						//UtilsFunctions.functionClick(rejectBtns);
						EYUtils.uiElement(rejectAllBtns).click();
						Thread.sleep(9000);
						
						WebElement completebtn=EYUtils.getElementPresent(By.xpath("//button[text()='Complete']"));
						try {
							uiElement(completebtn).click();
						} catch (StaleElementReferenceException e) {
							completebtn=EYUtils.getElementPresent(By.xpath("//button[text()='Complete']"));
							uiElement(completebtn).click();
						}
		
							
					}
				
			
				} catch (StaleElementReferenceException e) 
				{

					System.out.println("Inside else deny catch block");
					rejectAllBtns=EYUtils.getElementPresent(By.xpath("//button[contains(@id,'btnRejectAll')]"));
					if (rejectAllBtns.isDisplayed()) {
						EYUtils.waitForElementVisibleWithPollingTime(rejectAllBtns, 30, 2);
						UtilsFunctions.functionClick(rejectAllBtns);
						System.out.println("rejectAllBtns click");
						Thread.sleep(9000);
						WebElement completebtn=EYUtils.getElementPresent(By.xpath("//button[text()='Complete']"));
						try {
							uiElement(completebtn).click();
						} catch (StaleElementReferenceException e2) {
							completebtn=EYUtils.getElementPresent(By.xpath("//button[text()='Complete']"));
							uiElement(completebtn).click();
							System.out.println("completebtn click");
						}
					}

				}
				
			}
			Thread.sleep(9000);
		}

		System.out.println("outside form workitems for manager and owner , execution end:: workItems()");
		report.endTest(test);
	}	
	
	
	
	
	
	
	public void workItemForMultipleUser2levelApproval(String requestID,String testcaseType,String requestType,String managerName,String ownerName,
			String decision,String entitlement,String userName) throws InterruptedException {
		
		
		
		
		System.out.println("inside in workitems and execution start for 2 level approval for multiple User");

		test = report.startTest(ResourceStrings.IIQ_MANAGE_USER_ACCESS_WORKITEMS);
		report.flush();
		test.assignCategory("OwnerApproval==> ownerName is : " + ownerName);
		test.assignCategory("decision : " + decision);
		JavascriptExecutor js=(JavascriptExecutor)driver;
		Reporter.log("requestID :" + requestID);
		Reporter.log("testcaseType :" + testcaseType);
		Reporter.log("requestType :" + requestType);
		Reporter.log("ownerName :" + ownerName);
		Reporter.log("decision :" + decision);
		Reporter.log("entitlement :" + entitlement);
		Reporter.log("userName :" + userName);
		WebElement myWork = driver.findElement(By.xpath("//img[@alt='SailPoint Logo']//following::a[3]"));

		try {
			System.out.println("inside try workItems");
			// UtilsFunctions.functionClick(myworkLink);
			myWork.click();
			Thread.sleep(3000);
			UtilsFunctions.functionClick(workItemLink);
		} catch (StaleElementReferenceException exceptionMessage) {
			myWork = driver.findElement(By.xpath("//img[@alt='SailPoint Logo']//following::a[3]"));
			Thread.sleep(3000);
			myWork.click();
			Thread.sleep(3000);
			UtilsFunctions.functionClick(workItemLink);
			System.out.println(exceptionMessage.getMessage());

		}	
	
		Thread.sleep(6000);
		TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
				ResourceStrings.IIQ_MANAGE_USER_ACCESS_WORKITEMS_WELCOME, report, test, driver);

		Thread.sleep(6000);
		
		List<String> entitlementList = new ArrayList<String>();
		String[] splitRole = entitlement.split("#");
		entitlementList = Arrays.asList(splitRole);
		System.out.println("entitlementCount ==" + entitlementList);
		int entitlementCount = entitlementList.size();
		System.out.println("entitlementCount ==" + entitlementCount);// 1
		
		
		
		List<String> idList = new ArrayList<String>();
		
		String[] splitid = requestID.split("/");
		
		idList = Arrays.asList(splitid);
		
		System.out.println("idList ==" + idList);//multiple
		
		
		for (String requestedID : idList) {
		
		
			System.out.println("requestedID from list is ==" + requestedID);
			WebElement workItemFilterButton=EYUtils.getElementPresent(By.xpath("//button[@id='cardListFilterBtn']"));
			try {
				EYUtils.uiElement(workItemFilterButton).click();
			} catch (StaleElementReferenceException e) {
				workItemFilterButton=EYUtils.getElementPresent(By.xpath("//button[@id='cardListFilterBtn']"));
				EYUtils.uiElement(workItemFilterButton).click();
			}
			Thread.sleep(4000);
			WebElement workItemAddIcon=EYUtils.getElementPresent(By.xpath("//button[@id='-btn']"));
			try {
				EYUtils.uiElement(workItemAddIcon).click();
			} catch (StaleElementReferenceException e) {
				 workItemAddIcon=EYUtils.getElementPresent(By.xpath("//button[@id='-btn']"));
				EYUtils.uiElement(workItemAddIcon).click();
			}
		
		
		
		
		
   Thread.sleep(6000);


			
			List<WebElement>allID=driver.findElements(By.xpath("//input[@value='Add Filter']//following::a[@role='menuitem']"));
			try {
				for (int i = 0; i <allID.size(); i++) {
					WebElement requestid=allID.get(i);
					String alldata=requestid.getText();
					if (alldata.contains("Access Request ID")) {
						requestid.click();
					}
				}
				
			} catch (Exception e) {
				allID=driver.findElements(By.xpath("//input[@value='Add Filter']//following::a[@role='menuitem']"));
				for (int i = 0; i <allID.size(); i++) {
					WebElement requestid=allID.get(i);
					String alldata=requestid.getText();
					if (alldata.contains("Access Request ID")) {
						requestid.click();
					}
				}
			}
		
		
		
		
          Thread.sleep(6000);
			
			WebElement workItemAddRequestedIdSearchBox=EYUtils.getElementPresent(By.xpath("//input[starts-with(@id,'cardListFilterPanelAdditionalItem')]"));
			
			try {
				EYUtils.uiElement(workItemAddRequestedIdSearchBox).sendKeys(requestedID);
				
			} catch (StaleElementReferenceException e) {
				workItemAddRequestedIdSearchBox=EYUtils.getElementPresent(By.xpath("//input[starts-with(@id,'cardListFilterPanelAdditionalItem')]"));
				EYUtils.uiElement(workItemAddRequestedIdSearchBox).sendKeys(requestedID);
			}
		
		
		
			Thread.sleep(4000);
			WebElement applyButton=EYUtils.getElementPresent(By.xpath("//button[@id='cardListFilterPanelApplyBtn']"));
			try {
				EYUtils.uiElement(applyButton).click();
			} catch (StaleElementReferenceException e) {
				applyButton=EYUtils.getElementPresent(By.xpath("//button[@id='cardListFilterPanelApplyBtn']"));
				EYUtils.uiElement(applyButton).click();
			}
			Thread.sleep(3000);


	
	
		  WebElement forwardManagerButton=EYUtils.getElementPresent(By.cssSelector("button[id^='workitem-forward-btn']"));
			
			try {
				EYUtils.uiElement(forwardManagerButton).click();
			} catch (StaleElementReferenceException e) {
				forwardManagerButton=EYUtils.getElementPresent(By.cssSelector("button[id^='workitem-forward-btn']"));
				EYUtils.uiElement(forwardManagerButton).click();
			}

	
			Thread.sleep(8000);
			WebElement forwardtoManager=EYUtils.getElementPresent(By.xpath("//input[@id='ownerNameSuggestBoxWorkItemListForward']"));
			try {
				EYUtils.uiElement(forwardtoManager).sendKeys(managerName);
				Thread.sleep(2000);
				EYUtils.uiElement(forwardtoManager).sendKeys(Keys.ENTER);
		} catch (StaleElementReferenceException e) {
			forwardtoManager=EYUtils.getElementPresent(By.xpath("//input[@id='ownerNameSuggestBoxWorkItemListForward']"));
			EYUtils.uiElement(forwardtoManager).sendKeys(managerName);
			Thread.sleep(2000);
			EYUtils.uiElement(forwardtoOwner).sendKeys(Keys.ENTER);
		}

	
			Thread.sleep(3000);
			
			WebElement forwardOKbutton=EYUtils.getElementPresent(By.xpath("(//button[@ng-repeat='button in buttons'])[2]"));
			try {
				
				EYUtils.uiElement(forwardOKbutton).click();
			} catch (StaleElementReferenceException e) {
				forwardOKbutton=EYUtils.getElementPresent(By.xpath("(//button[@ng-repeat='button in buttons'])[2]"));
				EYUtils.uiElement(forwardOKbutton).click();
			}
			
			Thread.sleep(3000);
			
			
			WebElement viewButton=EYUtils.getElementPresent(By.xpath("//button[starts-with(@id,'workitem-goto-btn')]"));
			try {
				EYUtils.uiElement(viewButton).click();
				System.out.println("viewButton click");
			} catch (Exception e) {
				viewButton=EYUtils.getElementPresent(By.xpath("//button[starts-with(@id,'workitem-goto-btn')]"));
				EYUtils.uiElement(viewButton).click();
				System.out.println("viewButton click");
			}

			Thread.sleep(10000);


			TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
					ResourceStrings.IIQ_MANAGE_USER_ACCESS_ACCESSREQUEST_OWNERAPPROVAL + ":" + decision, report, test,
					driver);

			
			System.out.println("before decision click");
			
			
			
			
			
			
			if (testcaseType!=null && testcaseType.equalsIgnoreCase("MultipleuserSingleAccess_ADD")
					&& requestType.equalsIgnoreCase("ADD")||testcaseType!= null && testcaseType.equalsIgnoreCase("MultipleuserSingleAccess_REMOVE")&&requestType.equalsIgnoreCase("REMOVE")
					||testcaseType!=null && testcaseType.equalsIgnoreCase("MultipleuserSingleAccess_DENY")
							&& requestType.equalsIgnoreCase("DENY")) {
				
				System.out.println("Inside ADD or remove block");
				System.out.println("testcaseType :" + testcaseType);
				System.out.println("requestType :" + requestType);
				System.out.println("decision :" + decision);
				Thread.sleep(8000);
				WebElement approvalBtns=EYUtils.getElementPresent(By.xpath("//button[contains(@id,'btnApproveApproval')]"));
				js.executeScript("arguments[0].scrollIntoView(true);", approvalBtns);
				System.out.println("approvalBtns  displayed : " );
				if (decision != null && decision.equalsIgnoreCase("Approve")&& entitlementCount == 1) {
					System.out.println("Inside if block for take decision approve/reject");
					Thread.sleep(10000);
					try {
						System.out.println("Inside ADD or remove try block");
						if (approvalBtns.isDisplayed()) {
							System.out.println("approve button displayed");
							Thread.sleep(4000);
							js.executeScript("arguments[0].click();", approvalBtns);
							System.out.println("approve clicked");
							System.out.println("approvalBtns click after");
							
						}
						Thread.sleep(6000);
						WebElement completebtn=EYUtils.getElementPresent(By.xpath("//button[text()='Complete']"));
						if (completebtn.isDisplayed()) {
							System.out.println("completebtn button displayed");
							EYUtils.uiElement(completebtn).click();
						}
						
					
					} catch (StaleElementReferenceException e) {
						System.out.println("Inside ADD or remove catch block");
						approvalBtns=EYUtils.getElementPresent(By.xpath("//button[contains(@id,'btnApproveApproval')]"));
						if (approvalBtns.isDisplayed()) {
							
							Thread.sleep(3000);
							js.executeScript("arguments[0].click();", approvalBtns);  
						}
						Thread.sleep(3000);
						WebElement completebtn=EYUtils.getElementPresent(By.xpath("//button[text()='Complete']"));
						Thread.sleep(8000);
						if (completebtn.isDisplayed()) {
							System.out.println("completebtn button displayed");
							EYUtils.waitForElementVisibleWithPollingTime(completeButton, 30, 2);
							UtilsFunctions.functionClick(completeButton);
						}
					}
					Thread.sleep(10000);
					
						TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
								ResourceStrings.IIQ_MANAGE_USER_ACCESS_ACCESSREQUEST_COMPLETE_APPROVAL, report, test,
								driver);

				Thread.sleep(5000);
				}

				else {
					
					WebElement approveAllBtns=EYUtils.getElementPresent(By.xpath("//button[contains(@id,'btnApproveAll')]"));
					Thread.sleep(10000);
					try {
						System.out.println("Inside else ADD or remove try block");
						if (approveAllBtns.isDisplayed()) {
	                       
							Thread.sleep(3000);
							//js.executeScript("arguments[0].click();", approveAllBtns);
							EYUtils.waitForElementClickable(approvalBtns, 30, 2);
							EYUtils.uiElement(approvalBtns).click();
						}
				

						Thread.sleep(8000);
						WebElement completebtn=EYUtils.getElementPresent(By.xpath("//button[text()='Complete']"));
						if (completebtn.isDisplayed()) {
							EYUtils.waitForElementVisibleWithPollingTime(completebtn, 30, 2);
							EYUtils.uiElement(completebtn).click();
						}
						
					
					} catch (StaleElementReferenceException e) {
						System.out.println("Inside else ADD or remove catch block");
						approveAllBtns=EYUtils.getElementPresent(By.xpath("//button[contains(@id,'btnApproveAll')]"));
						if (approveAllBtns.isDisplayed()) {
							
							Thread.sleep(3000);
							js.executeScript("arguments[0].click();", approveAllBtns);  
							EYUtils.uiElement(approveAllBtns).click();
						}
				
						Thread.sleep(8000);
						WebElement completebtn=EYUtils.getElementPresent(By.xpath("//button[text()='Complete']"));
						if (completebtn.isDisplayed()) {
							EYUtils.waitForElementVisibleWithPollingTime(completebtn, 30, 2);
							UtilsFunctions.functionClick(completebtn);
						}
					}
					
						TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
								ResourceStrings.IIQ_MANAGE_USER_ACCESS_ACCESSREQUEST_COMPLETE_APPROVAL, report, test,
								driver);

				

				}

			}
		
	
			
			
			
			//forward to owners
			
			
			
			
			  WebElement forwardOwnerButton=EYUtils.getElementPresent(By.cssSelector("button[id^='workitem-forward-btn']"));
				
				try {
					EYUtils.uiElement(forwardOwnerButton).click();
				} catch (StaleElementReferenceException e) {
					forwardOwnerButton=EYUtils.getElementPresent(By.cssSelector("button[id^='workitem-forward-btn']"));
					EYUtils.uiElement(forwardOwnerButton).click();
				}

		
			
			
			

				Thread.sleep(8000);
				WebElement forwardtoOwner=EYUtils.getElementPresent(By.xpath("//input[@id='ownerNameSuggestBoxWorkItemListForward']"));
				try {
					EYUtils.uiElement(forwardtoOwner).sendKeys(ownerName);
					Thread.sleep(2000);
					EYUtils.uiElement(forwardtoOwner).sendKeys(Keys.ENTER);
			} catch (StaleElementReferenceException e) {
				forwardtoManager=EYUtils.getElementPresent(By.xpath("//input[@id='ownerNameSuggestBoxWorkItemListForward']"));
				EYUtils.uiElement(forwardtoOwner).sendKeys(ownerName);
				Thread.sleep(2000);
				EYUtils.uiElement(forwardtoOwner).sendKeys(Keys.ENTER);
			}

		
			
				Thread.sleep(3000);
				
				WebElement forwardOKbuttons=EYUtils.getElementPresent(By.xpath("(//button[@ng-repeat='button in buttons'])[2]"));
				try {
					
					EYUtils.uiElement(forwardOKbuttons).click();
				} catch (StaleElementReferenceException e) {
					forwardOKbuttons=EYUtils.getElementPresent(By.xpath("(//button[@ng-repeat='button in buttons'])[2]"));
					EYUtils.uiElement(forwardOKbuttons).click();
				}
				
				Thread.sleep(9000);
				
				
				WebElement viewButtons=EYUtils.getElementPresent(By.xpath("//button[starts-with(@id,'workitem-goto-btn')]"));
				try {
					EYUtils.uiElement(viewButtons).click();
					System.out.println("viewButton click");
				} catch (Exception e) {
					viewButtons=EYUtils.getElementPresent(By.xpath("//button[starts-with(@id,'workitem-goto-btn')]"));
					EYUtils.uiElement(viewButtons).click();
					System.out.println("viewButton click");
				}

				Thread.sleep(10000);
			
				if (testcaseType!=null && testcaseType.equalsIgnoreCase("MultipleuserSingleAccess_ADD")
						&& requestType.equalsIgnoreCase("ADD")||testcaseType!= null && testcaseType.equalsIgnoreCase("MultipleuserSingleAccess_ADD")
						&&requestType.equalsIgnoreCase("REMOVE")) {
					
					System.out.println("Inside ADD or remove block");
					System.out.println("testcaseType :" + testcaseType);
					System.out.println("requestType :" + requestType);
					System.out.println("decision :" + decision);
					Thread.sleep(8000);
					WebElement approvalBtns=EYUtils.getElementPresent(By.xpath("//button[contains(@id,'btnApproveApproval')]"));
					js.executeScript("arguments[0].scrollIntoView(true);", approvalBtns);
					System.out.println("approvalBtns  displayed : " );
					
					if (decision != null && decision.equalsIgnoreCase("Approve")&& entitlementCount == 1) {
						System.out.println("Inside if block for take decision approve/reject");
						Thread.sleep(10000);
						try {
							System.out.println("Inside ADD or remove try block");
							if (approvalBtns.isDisplayed()) {
								System.out.println("approve button displayed");
								Thread.sleep(4000);
								js.executeScript("arguments[0].click();", approvalBtns);
								System.out.println("approve clicked");
								System.out.println("approvalBtns click after");
								
							}
							Thread.sleep(6000);
							WebElement completebtn=EYUtils.getElementPresent(By.xpath("//button[text()='Complete']"));
							if (completebtn.isDisplayed()) {
								System.out.println("completebtn button displayed");
								EYUtils.uiElement(completebtn).click();
							}
							
						
						} catch (StaleElementReferenceException e) {
							System.out.println("Inside ADD or remove catch block");
							approvalBtns=EYUtils.getElementPresent(By.xpath("//button[contains(@id,'btnApproveApproval')]"));
							if (approvalBtns.isDisplayed()) {
								
								Thread.sleep(3000);
								js.executeScript("arguments[0].click();", approvalBtns);  
							}
							Thread.sleep(3000);
							WebElement completebtn=EYUtils.getElementPresent(By.xpath("//button[text()='Complete']"));
							if (completebtn.isDisplayed()) {
								System.out.println("completebtn button displayed");
								EYUtils.waitForElementVisibleWithPollingTime(completeButton, 30, 2);
								UtilsFunctions.functionClick(completeButton);
							}
						}
						Thread.sleep(10000);
						
							TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
									ResourceStrings.IIQ_MANAGE_USER_ACCESS_ACCESSREQUEST_COMPLETE_APPROVAL, report, test,
									driver);

					Thread.sleep(5000);
					}

					else {
						
						WebElement approveAllBtns=EYUtils.getElementPresent(By.xpath("//button[contains(@id,'btnApproveAll')]"));
						Thread.sleep(10000);
						try {
							System.out.println("Inside else ADD or remove try block");
							if (approveAllBtns.isDisplayed()) {
		                       
								Thread.sleep(3000);
								//js.executeScript("arguments[0].click();", approveAllBtns);
								EYUtils.waitForElementClickable(approvalBtns, 30, 2);
								EYUtils.uiElement(approvalBtns).click();
							}
					

							Thread.sleep(8000);
							WebElement completebtn=EYUtils.getElementPresent(By.xpath("//button[text()='Complete']"));
							if (completebtn.isDisplayed()) {
								EYUtils.waitForElementVisibleWithPollingTime(completebtn, 30, 2);
								UtilsFunctions.functionClick(completebtn);
							}
							
						
						} catch (StaleElementReferenceException e) {
							System.out.println("Inside else ADD or remove catch block");
							approveAllBtns=EYUtils.getElementPresent(By.xpath("//button[contains(@id,'btnApproveAll')]"));
							if (approveAllBtns.isDisplayed()) {
								
								Thread.sleep(3000);
								js.executeScript("arguments[0].click();", approveAllBtns);  
								EYUtils.uiElement(approveAllBtns).click();
							}
					
							Thread.sleep(8000);
							WebElement completebtn=EYUtils.getElementPresent(By.xpath("//button[text()='Complete']"));
							if (completebtn.isDisplayed()) {
								EYUtils.waitForElementVisibleWithPollingTime(completebtn, 30, 2);
								UtilsFunctions.functionClick(completebtn);
							}
						}
						
							TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
									ResourceStrings.IIQ_MANAGE_USER_ACCESS_ACCESSREQUEST_COMPLETE_APPROVAL, report, test,
									driver);

					

					}

					
					
					
				}
				
				
				
				
				else if (testcaseType!=null && testcaseType.equalsIgnoreCase("MultipleuserSingleAccess_DENY")&& requestType.equalsIgnoreCase("DENY")) {
					
					System.out.println("Inside DEny block");
					System.out.println("testcaseType :" + testcaseType);
					System.out.println("requestType :" + requestType);
					System.out.println("decision :" + decision);
					Thread.sleep(8000);
					
					
					
					WebElement rejectBtn=EYUtils.getElementPresent(By.xpath("//button[contains(@id,'btnRejectApproval')]"));
					Thread.sleep(4000);
					if (decision != null && decision. equalsIgnoreCase("Reject") && entitlementCount == 1) {
						Thread.sleep(9000);
						try {
							uiElement(rejectBtn).click();
							Thread.sleep(4000);
							WebElement completebtn=EYUtils.getElementPresent(By.xpath("//button[text()='Complete']"));
							uiElement(completebtn).click();
							TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
									ResourceStrings.IIQ_MANAGE_USER_ACCESS_ACCESSREQUEST_OWNERAPPROVAL+":"+ownerName + "Decision is :" + decision, report, test,
									driver);
						} catch (StaleElementReferenceException e) {
							rejectBtn=EYUtils.getElementPresent(By.xpath("//button[contains(@id,'btnRejectApproval')]"));
							uiElement(rejectBtn).click();
							Thread.sleep(4000);
							WebElement completebtn=EYUtils.getElementPresent(By.xpath("//button[text()='Complete']"));
							uiElement(completebtn).click();
							TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
									ResourceStrings.IIQ_MANAGE_USER_ACCESS_ACCESSREQUEST_OWNERAPPROVAL+":"+ownerName + "Decision is :" + decision, report, test,
									driver);
						}
				}
						
				}
				
				Thread.sleep(10000);
				
				
				WebElement workItemFilterButtons=EYUtils.getElementPresent(By.xpath("//button[@id='cardListFilterBtn']"));
				try {
					EYUtils.uiElement(workItemFilterButtons).click();
				} catch (StaleElementReferenceException e) {
					EYUtils.uiElement(workItemFilterButtons).click();
				}
				
				Thread.sleep(2000);
				WebElement cancelButton=EYUtils.getElementPresent(By.xpath("//i[@class='fa fa-times fa-fw']"));
				try {
					EYUtils.uiElement(cancelButton).click();
				} catch (StaleElementReferenceException e) {
					EYUtils.uiElement(cancelButton).click();
				}

					Thread.sleep(2000);
				
					WebElement applyButtons=EYUtils.getElementPresent(By.xpath("//button[@id='cardListFilterPanelApplyBtn']"));
					try {
						EYUtils.uiElement(applyButtons).click();
					} catch (StaleElementReferenceException e) {
						applyButtons=EYUtils.getElementPresent(By.xpath("//button[@id='cardListFilterPanelApplyBtn']"));
						EYUtils.uiElement(applyButtons).click();
					}
				
					Thread.sleep(3000);
		}
		report.endTest(test);

		
		}
		
		
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	}
