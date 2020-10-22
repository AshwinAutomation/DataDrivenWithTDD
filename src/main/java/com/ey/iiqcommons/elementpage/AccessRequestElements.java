package com.ey.iiqcommons.elementpage;

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

import com.excelutils.ExcelUtils;
import com.ey.generics.EYUtils;
import com.ey.iiqesourcestring.ResourceStrings;
import com.qa.testbase.TestBase;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class AccessRequestElements extends TestBase {
//static List<String> storeList = new ArrayList<>();
	
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

/*	@FindBy(id = "accessRequests_requesteeValue0RemoveBtn")
	@CacheLookup
	WebElement removalButtonButton;*/
	public String userName = "";
	public String rollName = "";

   
	//
	
	public AccessRequestElements() {
		super();
		PageFactory.initElements(driver,this);
	}

	
	
        public void accessRequestsForSingleUserSingleAccess(String requestID) throws InterruptedException {
		System.out.println("enter in access request and access request start");
		test = report.startTest(ResourceStrings.IIQ_MANAGE_USER_ACCESS_ACCESSREQUEST);
		report.flush();
  		System.out.println("accessRequest  requstedID :" + requestID);
  		JavascriptExecutor js=(JavascriptExecutor)driver;
		WebElement myWorkElement=EYUtils.getElementPresent(By.xpath("//img[@alt='SailPoint Logo']//following::a[3]"));
		
		try {
			System.out.println("accessRequestsForSingleUserSingleAccess inside try");
			EYUtils.uiElement(myWorkElement).click();
		} catch (StaleElementReferenceException e) {
			myWorkElement=EYUtils.getElementPresent(By.xpath("//img[@alt='SailPoint Logo']//following::a[3]"));
			js.executeScript("arguments[0].scrollIntoView(true);", myWorkElement);
        myWorkElement.click();
		}
		
	
		Thread.sleep(10000);
		WebElement accessRequestElement=EYUtils.getElementPresent(By.xpath("//img[@alt='SailPoint Logo']//following::a[5]"));
		
		
		
		try {
			EYUtils.uiElement(accessRequestElement).click();
		} catch (StaleElementReferenceException e) {
			accessRequestElement=EYUtils.getElementPresent(By.xpath("//img[@alt='SailPoint Logo']//following::a[5]"));
			js.executeScript("arguments[0].scrollIntoView(true);", accessRequestElement);
			
		accessRequestElement.click();
		}

  		TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
  				ResourceStrings.IIQ_MANAGE_USER_ACCESS_ACCESSREQUEST_WITHSTATUS, report, test, driver);
  		Thread.sleep(10000);
  		List<String>requestedIdList=new ArrayList<String>();
  		String[] splitArray = requestID.split("/");
		Thread.sleep(2000);
		requestedIdList = Arrays.asList(splitArray);
  		System.out.println("requestedIdList : " + requestedIdList);
  		for (String requestid : requestedIdList) {
			
  			System.out.println("requestedID from list is ==" + requestid);
  			WebElement requestedIDInsertBox=EYUtils.getElementPresent(By.id("cardListSearchInput"));
		try {
			EYUtils.uiElement(requestedIDInsertBox).sendKeys(requestid);
		} catch (StaleElementReferenceException e) {
			requestedIDInsertBox=EYUtils.getElementPresent(By.id("cardListSearchInput"));
			js.executeScript("arguments[0].scrollIntoView(true);", requestedIDInsertBox);
			
			EYUtils.uiElement(requestedIDInsertBox).sendKeys(requestID);
		}
  		

		Thread.sleep(3000);
		WebElement requestedIDInsertBoxSerachButton=EYUtils.getElementPresent(By.id("cardListSearchBtn"));

  	
		try {
			EYUtils.uiElement(requestedIDInsertBoxSerachButton).click();
			TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
	  				ResourceStrings.IIQ_MANAGE_USER_ACCESS_ACCESSREQUEST_DETAILS_SPECIFIC +"==>" + requestID, report, test, driver);
		} catch (StaleElementReferenceException e) {
			requestedIDInsertBoxSerachButton=EYUtils.getElementPresent(By.id("cardListSearchBtn"));
			js.executeScript("arguments[0].scrollIntoView(true);", requestedIDInsertBoxSerachButton);
			EYUtils.uiElement(requestedIDInsertBoxSerachButton).click();

			TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
	  				ResourceStrings.IIQ_MANAGE_USER_ACCESS_ACCESSREQUEST_DETAILS_SPECIFIC +"==>" + requestID, report, test, driver);
		}
  		
		Thread.sleep(5000);
   		WebElement RequestedDetailsButton=EYUtils.getElementPresent(By.xpath("//button[starts-with(@id,'requestDetailsBtn')]"));
   	
  		
		try {
			EYUtils.uiElement(RequestedDetailsButton).click();
			js.executeScript("window.scrollBy(0,300)");
	  		TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
	  				ResourceStrings.IIQ_MANAGE_USER_ACCESS_ACCESSREQUEST_REQUESTEDUSERSWITHDETAILS, report, test, driver);
	  		js.executeScript("window.scrollBy(0,-300)");
		} catch (Exception e) {
			RequestedDetailsButton=EYUtils.getElementPresent(By.xpath("//button[starts-with(@id,'requestDetailsBtn')]"));
			EYUtils.uiElement(RequestedDetailsButton).click();
			js.executeScript("window.scrollBy(0,300)");
	  		TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
	  				ResourceStrings.IIQ_MANAGE_USER_ACCESS_ACCESSREQUEST_REQUESTEDUSERSWITHDETAILS, report, test, driver);
	  		js.executeScript("window.scrollBy(0,-300)");
		}

        Thread.sleep(4000);
        driver.navigate().back();
        Thread.sleep(3000);
  		System.out.println("out from access request and access request end");

  		}
	}

     public void accessRequestsForMultipleUser(String userName,String roleName,String applicationName,String requestType,ExtentReports report, ExtentTest test,
 			WebDriver driver) throws InterruptedException, IOException {
    	 
 		System.out.println(" accessRequestsForMultipleUser enter in access request and access request start");
 		test = report.startTest(ResourceStrings.IIQ_MANAGE_USER_ACCESS_ACCESSREQUEST);
 		report.flush();
   		JavascriptExecutor js=(JavascriptExecutor)driver;
   		String RequestedID=null;
		System.out.println("accessRequestsForMultipleUser userName :" + userName);
		System.out.println("roleName : " + roleName);
		System.out.println("RequestType : " + requestType);

 		WebElement myWorkElement=EYUtils.getElementPresent(By.xpath("//img[@alt='SailPoint Logo']//following::a[3]"));
 		
 		try {
 			System.out.println("accessRequestsForSingleUserSingleAccess inside try");
 			EYUtils.uiElement(myWorkElement).click();
 			Thread.sleep(3000);
 		} catch (StaleElementReferenceException e) {
 			myWorkElement=EYUtils.getElementPresent(By.xpath("//img[@alt='SailPoint Logo']//following::a[3]"));
 			js.executeScript("arguments[0].scrollIntoView(true);", myWorkElement);
         myWorkElement.click();
 			Thread.sleep(3000);
 		}

 		WebElement accessRequestElement=EYUtils.getElementPresent(By.xpath("//img[@alt='SailPoint Logo']//following::a[5]"));

 		try {
 			EYUtils.uiElement(accessRequestElement).click();
 		} catch (StaleElementReferenceException e) {
 			accessRequestElement=EYUtils.getElementPresent(By.xpath("//img[@alt='SailPoint Logo']//following::a[5]"));
 			js.executeScript("arguments[0].scrollIntoView(true);", accessRequestElement);
 			
 		accessRequestElement.click();
 		}
 		
 		Thread.sleep(15000);
 		
 		
		   List<String> userList = new ArrayList<String>();
			String[] splitArray = userName.split(",");
			Thread.sleep(2000);
			userList = Arrays.asList(splitArray);

			System.out.println("users list ==" + userList);
			int userCount = userList.size();
			System.out.println("userCount ==" + userCount);
			for (String user : userList) {
				System.out.println("user from list is ==" + user);
				Thread.sleep(3000);
			
				WebElement filterButton=EYUtils.getElementPresent(By.xpath("//span[text()='Filter']"));
				try {
					EYUtils.uiElement(filterButton).click();
					
				} catch (StaleElementReferenceException e) {
					filterButton=EYUtils.getElementPresent(By.xpath("//span[text()='Filter']"));
					EYUtils.uiElement(filterButton).click();
				}
				Thread.sleep(2000);
	            	  WebElement requsteeSerachbox=EYUtils.getElementPresent(By.id("accessRequests_requestee"));
				try {	
					EYUtils.uiElement(requsteeSerachbox).sendKeys(user);
					Thread.sleep(2000);
					EYUtils.uiElement(requsteeSerachbox).sendKeys(Keys.ENTER);
				} catch (StaleElementReferenceException e) {
					requsteeSerachbox=EYUtils.getElementPresent(By.id("accessRequests_requestee"));
					EYUtils.uiElement(requsteeSerachbox).sendKeys(user);
					Thread.sleep(2000);
					EYUtils.uiElement(requsteeSerachbox).sendKeys(Keys.ENTER);
				}
				
				Thread.sleep(2000);
				WebElement type=EYUtils.getElementPresent(By.id("cardListFilterPanelItem3"));
				try {
					
					EYUtils.uiElement(type).sendKeys("Request Access");
					Thread.sleep(2000);
					EYUtils.uiElement(type).sendKeys(Keys.ENTER);
				} catch (StaleElementReferenceException e) {
					EYUtils.uiElement(type).sendKeys("Request Access");
					Thread.sleep(2000);
					EYUtils.uiElement(type).sendKeys(Keys.ENTER);
				}
				
				
				WebElement applyButton=EYUtils.getElementPresent(By.id("cardListFilterPanelApplyBtn"));
				
			try {
					
				EYUtils.uiElement(applyButton).click();
		  		TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
		  				ResourceStrings.IIQ_MANAGE_USER_ACCESS_ACCESSREQUEST_WITHSTATUS+" ::ForUSer ::"+ user, report, test, driver);
					
				} catch (StaleElementReferenceException e) {
					applyButton=EYUtils.getElementPresent(By.id("cardListFilterPanelApplyBtn"));
					Thread.sleep(2000);
					EYUtils.uiElement(applyButton).click();
					TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
			  				ResourceStrings.IIQ_MANAGE_USER_ACCESS_ACCESSREQUEST_WITHSTATUS+" ::ForUSer ::"+ user, report, test, driver);
				}
			Thread.sleep(5000);
		List<WebElement> entitlementList=driver.findElements(By.xpath("(//a[starts-with(@aria-label,'Click to view details for entitlement ')])[1]"));
			System.out.println("entitlementList count : " + entitlementList.size());
			for (int i = 0; i < entitlementList.size(); i++) {
				System.out.println("inside sub for loop");
				Thread.sleep(1000);
				String captureEntitlementName=entitlementList.get(i).getText();
				System.out.println("captureEntitlementNames are :" + captureEntitlementName);
				System.out.println("captureEntitlement length :" +captureEntitlementName.length());
				String expectedEntitlement=roleName+" "+ "on" +" "+applicationName;
				System.out.println("expectedEntitlement :" + expectedEntitlement);   
				System.out.println("expectedEntitlementlength :" + expectedEntitlement.length());
				if (captureEntitlementName!=null&&captureEntitlementName.equalsIgnoreCase(expectedEntitlement)) {
					System.out.println("inside if equalsIgnoreCase captureEntitlementName");
					Thread.sleep(1000);
					WebElement requestedID=EYUtils.getElementPresent(By.xpath("//span[starts-with(@id,'identityRequestId')]"));
					String requestedIDtext=requestedID.getText();
					
					int RequestedIDnumber = (Integer.parseInt(requestedIDtext.replaceAll("[^0-9]", "").trim()));
					System.out.println("id :" + RequestedIDnumber);// 18738
		
					
				
					 
				   		WebElement RequestedDetailsButton=EYUtils.getElementPresent(By.xpath("//button[starts-with(@id,'requestDetailsBtn')]"));
					   	
				  		
							try {
								EYUtils.uiElement(RequestedDetailsButton).click();
								
						  		TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
						  				ResourceStrings.IIQ_MANAGE_USER_ACCESS_ACCESSREQUEST_REQUESTEDUSERSWITHDETAILS, report, test, driver);
							} catch (Exception e) {
								RequestedDetailsButton=EYUtils.getElementPresent(By.xpath("//button[starts-with(@id,'requestDetailsBtn')]"));
								EYUtils.uiElement(RequestedDetailsButton).click();
								
						  		TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
						  				ResourceStrings.IIQ_MANAGE_USER_ACCESS_ACCESSREQUEST_REQUESTEDUSERSWITHDETAILS, report, test, driver);
							}
							 RequestedID = String.valueOf(RequestedIDnumber);//18738
			 
					 
						System.out.println("RequestedID number :" + RequestedID);
						

		
                    Thread.sleep(4000);
                    driver.navigate().back();
                    Thread.sleep(3000);
					}
						}
			


				ExcelUtils.setCellDatas(excelsheetPath, xlsheet, 1, 10, RequestedID);
			Thread.sleep(1000);
			WebElement filterButtons=EYUtils.getElementPresent(By.xpath("//span[text()='Filter']"));
			try {
				System.out.println("filterButton click try block");
				EYUtils.uiElement(filterButtons).click();
			} catch (StaleElementReferenceException e) {
				filterButtons=EYUtils.getElementPresent(By.xpath("//span[text()='Filter']"));
				EYUtils.uiElement(filterButtons).click();
			}
/*			Thread.sleep(1000);
			WebElement removalButton=EYUtils.getElementPresent(By.id("accessRequests_requesteeValue0RemoveBtn"));
			try {
				System.out.println("filterButton click");
				EYUtils.uiElement(removalButton).click();
			} catch (StaleElementReferenceException e) {
				removalButton=EYUtils.getElementPresent(By.id("accessRequests_requesteeValue0RemoveBtn"));
				EYUtils.uiElement(removalButton).click();
			}*/
			Thread.sleep(1000);
			WebElement applyButtons=EYUtils.getElementPresent(By.id("cardListFilterPanelApplyBtn"));
		try {
				
			EYUtils.uiElement(applyButtons).click();
				Thread.sleep(2000);
			} catch (StaleElementReferenceException e) {
				applyButton=EYUtils.getElementPresent(By.id("cardListFilterPanelApplyBtn"));
			
				EYUtils.uiElement(applyButtons).click();
			}				

		    Thread.sleep(2000);
			
			}
	    System.out.println("outside loop");
     }
        
	

     
     
     
     
     
     
     
     public void accessRequestsForMultipleUserMultipleAccess(String userName,String entitlement,String applicationName,String requestType,ExtentReports report, ExtentTest test,
  			WebDriver driver) throws InterruptedException, IOException {
     	 
  		System.out.println(" accessRequestsForMultipleUser enter in access request and access request start");
  		test = report.startTest(ResourceStrings.IIQ_MANAGE_USER_ACCESS_ACCESSREQUEST);
  		report.flush();
    		JavascriptExecutor js=(JavascriptExecutor)driver;
    		String RequestedID=null;
 		System.out.println("accessRequestsForMultipleUser userName :" + userName);
 		System.out.println("accessRequestsForMultiple entitlement : " + entitlement);
 		System.out.println("RequestType : " + requestType);
 		System.out.println("applicationName : " + applicationName);

  		WebElement myWorkElement=EYUtils.getElementPresent(By.xpath("//img[@alt='SailPoint Logo']//following::a[3]"));
  		
  		try {
  			System.out.println("accessRequestsForSingleUserSingleAccess inside try");
  			EYUtils.uiElement(myWorkElement).click();
  			Thread.sleep(3000);
  		} catch (StaleElementReferenceException e) {
  			myWorkElement=EYUtils.getElementPresent(By.xpath("//img[@alt='SailPoint Logo']//following::a[3]"));
  			js.executeScript("arguments[0].scrollIntoView(true);", myWorkElement);
          myWorkElement.click();
  			Thread.sleep(3000);
  		}

  		WebElement accessRequestElement=EYUtils.getElementPresent(By.xpath("//img[@alt='SailPoint Logo']//following::a[5]"));

  		try {
  			EYUtils.uiElement(accessRequestElement).click();
  		} catch (StaleElementReferenceException e) {
  			accessRequestElement=EYUtils.getElementPresent(By.xpath("//img[@alt='SailPoint Logo']//following::a[5]"));
  			js.executeScript("arguments[0].scrollIntoView(true);", accessRequestElement);
  			
  		accessRequestElement.click();
  		}
  		
  		Thread.sleep(15000);
  		
  		
 		   List<String> userList = new ArrayList<String>();
 			String[] splitArray = userName.split(",");
 			Thread.sleep(2000);
 			userList = Arrays.asList(splitArray);
 			System.out.println("users list ==" + userList);
 			int userCount = userList.size();
 			System.out.println("userCount ==" + userCount);
 			

 	 			
 			
 			for (String user : userList) {
 				System.out.println("user from list is ==" + user);
 				Thread.sleep(3000);
 			
 				WebElement filterButton=EYUtils.getElementPresent(By.xpath("//span[text()='Filter']"));
 				try {
 					EYUtils.uiElement(filterButton).click();
 					
 				} catch (StaleElementReferenceException e) {
 					filterButton=EYUtils.getElementPresent(By.xpath("//span[text()='Filter']"));
 					EYUtils.uiElement(filterButton).click();
 				}
 				Thread.sleep(2000);
 	            	  WebElement requsteeSerachbox=EYUtils.getElementPresent(By.id("accessRequests_requestee"));
 				try {	
 					EYUtils.uiElement(requsteeSerachbox).sendKeys(user);
 					Thread.sleep(2000);
 					EYUtils.uiElement(requsteeSerachbox).sendKeys(Keys.ENTER);
 				} catch (StaleElementReferenceException e) {
 					requsteeSerachbox=EYUtils.getElementPresent(By.id("accessRequests_requestee"));
 					EYUtils.uiElement(requsteeSerachbox).sendKeys(user);
 					Thread.sleep(2000);
 					EYUtils.uiElement(requsteeSerachbox).sendKeys(Keys.ENTER);
 				}
 				
 				Thread.sleep(2000);
 				WebElement type=EYUtils.getElementPresent(By.id("cardListFilterPanelItem3"));
 				try {
 					
 					EYUtils.uiElement(type).sendKeys("Request Access");
 					Thread.sleep(2000);
 					EYUtils.uiElement(type).sendKeys(Keys.ENTER);
 				} catch (StaleElementReferenceException e) {
 					EYUtils.uiElement(type).sendKeys("Request Access");
 					Thread.sleep(2000);
 					EYUtils.uiElement(type).sendKeys(Keys.ENTER);
 				}
 				
 				
 				WebElement applyButton=EYUtils.getElementPresent(By.id("cardListFilterPanelApplyBtn"));
 				
 			try {
 					
 				EYUtils.uiElement(applyButton).click();
 		  		TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
 		  				ResourceStrings.IIQ_MANAGE_USER_ACCESS_ACCESSREQUEST_WITHSTATUS+" ::ForUSer ::"+ user, report, test, driver);
 					
 				} catch (StaleElementReferenceException e) {
 					applyButton=EYUtils.getElementPresent(By.id("cardListFilterPanelApplyBtn"));
 					Thread.sleep(2000);
 					EYUtils.uiElement(applyButton).click();
 					TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
 			  				ResourceStrings.IIQ_MANAGE_USER_ACCESS_ACCESSREQUEST_WITHSTATUS+" ::ForUSer ::"+ user, report, test, driver);
 				}
 			Thread.sleep(5000);
 			
 			
		       List<String> entitlementLists = new ArrayList<String>();
	 			String[] splitArrays = entitlement.split("#");
	 			Thread.sleep(2000);
	 			entitlementLists = Arrays.asList(splitArrays);
	 			System.out.println("entitlementList list ==" + entitlementLists);
	 			int entitlementCount = entitlementLists.size();
	 			System.out.println("entitlementCount ==" + entitlementCount);
	 			
	 			for (int j = 0; j < entitlementLists.size(); j++) {
					String aaa=entitlementLists.get(j);
					System.out.println("aaa :" + aaa);
			
	 			
	
 		    List<WebElement> entitlementList=driver.findElements(By.xpath("(//a[starts-with(@aria-label,'Click to view details for entitlement ')])[1]"));
 			System.out.println("entitlementList count : " + entitlementList.size());
 			for (int i = 0; i < entitlementList.size(); i++) {
 				System.out.println("inside sub for loop");
 				Thread.sleep(1000);
 				String captureEntitlementName=entitlementList.get(i).getText();
 				System.out.println("captureEntitlementNames are :" + captureEntitlementName);
 				System.out.println("captureEntitlement length :" +captureEntitlementName.length());
 				String expectedEntitlement=aaa+" "+ "on" +" "+applicationName;
 				System.out.println("expectedEntitlement :" + expectedEntitlement);   
 				System.out.println("expectedEntitlementlength :" + expectedEntitlement.length());
 		
 				if (captureEntitlementName!=null&&captureEntitlementName.equalsIgnoreCase(expectedEntitlement)) {
 					System.out.println("inside if equalsIgnoreCase captureEntitlementName");
 					Thread.sleep(1000);
 					WebElement requestedID=EYUtils.getElementPresent(By.xpath("//span[starts-with(@id,'identityRequestId')]"));
 					String requestedIDtext=requestedID.getText();
 					
 					int RequestedIDnumber = (Integer.parseInt(requestedIDtext.replaceAll("[^0-9]", "").trim()));
 					System.out.println("id :" + RequestedIDnumber);// 18738
 		
 					
 				
 					 
 				   		WebElement RequestedDetailsButton=EYUtils.getElementPresent(By.xpath("//button[starts-with(@id,'requestDetailsBtn')]"));
 					   	
 				  		
 							try {
 								EYUtils.uiElement(RequestedDetailsButton).click();
 								
 						  		TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
 						  				ResourceStrings.IIQ_MANAGE_USER_ACCESS_ACCESSREQUEST_REQUESTEDUSERSWITHDETAILS, report, test, driver);
 							} catch (Exception e) {
 								RequestedDetailsButton=EYUtils.getElementPresent(By.xpath("//button[starts-with(@id,'requestDetailsBtn')]"));
 								EYUtils.uiElement(RequestedDetailsButton).click();
 								
 						  		TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
 						  				ResourceStrings.IIQ_MANAGE_USER_ACCESS_ACCESSREQUEST_REQUESTEDUSERSWITHDETAILS, report, test, driver);
 							}
 							 RequestedID = String.valueOf(RequestedIDnumber);//18738
 			 
 					 
 						System.out.println("RequestedID number :" + RequestedID);
 						

 		
                     Thread.sleep(4000);
                     driver.navigate().back();
                     Thread.sleep(3000);
 					}
 						}
	 			}


 				ExcelUtils.setCellDatas(excelsheetPath, xlsheet, 1, 10, RequestedID);
 			Thread.sleep(1000);
 			WebElement filterButtons=EYUtils.getElementPresent(By.xpath("//span[text()='Filter']"));
 			try {
 				System.out.println("filterButton click try block");
 				EYUtils.uiElement(filterButtons).click();
 			} catch (StaleElementReferenceException e) {
 				filterButtons=EYUtils.getElementPresent(By.xpath("//span[text()='Filter']"));
 				EYUtils.uiElement(filterButtons).click();
 			}
 /*			Thread.sleep(1000);
 			WebElement removalButton=EYUtils.getElementPresent(By.id("accessRequests_requesteeValue0RemoveBtn"));
 			try {
 				System.out.println("filterButton click");
 				EYUtils.uiElement(removalButton).click();
 			} catch (StaleElementReferenceException e) {
 				removalButton=EYUtils.getElementPresent(By.id("accessRequests_requesteeValue0RemoveBtn"));
 				EYUtils.uiElement(removalButton).click();
 			}*/
 			Thread.sleep(1000);
 			WebElement applyButtons=EYUtils.getElementPresent(By.id("cardListFilterPanelApplyBtn"));
 		try {
 				
 			EYUtils.uiElement(applyButtons).click();
 				Thread.sleep(2000);
 			} catch (StaleElementReferenceException e) {
 				applyButton=EYUtils.getElementPresent(By.id("cardListFilterPanelApplyBtn"));
 			
 				EYUtils.uiElement(applyButtons).click();
 			}				

 		    Thread.sleep(2000);
 			
 			}
 	    System.out.println("outside loop");
      }
  
     
     
     
     
     
     
     

}
