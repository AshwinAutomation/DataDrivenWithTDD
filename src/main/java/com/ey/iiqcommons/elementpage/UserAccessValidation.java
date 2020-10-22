package com.ey.iiqcommons.elementpage;

import java.awt.AWTException;
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

import com.ey.generics.EYUtils;
import com.ey.generics.UtilsFunctions;
import com.ey.iiqesourcestring.ResourceStrings;
import com.qa.testbase.TestBase;
import com.relevantcodes.extentreports.LogStatus;

public class UserAccessValidation  extends TestBase{
	//Identities
    @FindBy(partialLinkText="Identities")
    @CacheLookup
    WebElement identitiesLink;
    
    //Identity Warehouse
    @FindBy(partialLinkText="Identity Warehouse")
    @CacheLookup
    WebElement identityWarehouseLink;

    //user searchfield
    @FindBy(xpath="//input[starts-with(@id,'searchfield')]")
    @CacheLookup
    WebElement userSearchfield;
    
/*    //click to specific user
    @FindBy(xpath="(//div[starts-with(@id,'paginggrid')])[2]//table/tbody/tr[2]/td[1]")
    @CacheLookup
    WebElement userDetailsLink;*/
    
    @FindBy(xpath="//span[text()='Application Accounts']")
    @CacheLookup
    WebElement applicationAccountsTab;
  
    @FindBy(xpath="//a[@title='Click to view the application account details.']")
    @CacheLookup
    List<WebElement> applicationAccountsList;
  
    @FindBy(xpath="//span[text()='Entitlements']")
    @CacheLookup
    WebElement entitlementsTab;
  
    @FindBy(id="identityEntitlementAppNameField-inputEl")
    @CacheLookup
    WebElement entitlementSearchbox;
    
    
  public  UserAccessValidation() {
	PageFactory.initElements(driver, this);
}
    
    
    public void validateUserAccountsAndEntitlements(String userdetails,String requestType,String applicationName,String entitlement) throws InterruptedException, AWTException {
    	test=report.startTest(ResourceStrings.IIQ_MANAGE_USER_ACCESS_IDENTITYWAREHOUSE);
    	report.flush();
    	test.assignCategory("Userdetails :" + userdetails);
    	test.assignCategory("ApplicationName :" + applicationName);
    	test.assignCategory("RequestType :" + requestType);
    	test.assignCategory("EntitlementName :" + entitlement);


		System.out.println("Userdetails :" + userdetails);
		System.out.println("requestType :" + requestType);
		System.out.println("applicationName :" + applicationName);
		System.out.println("entitlement Name :" + entitlement);
		JavascriptExecutor js=(JavascriptExecutor)driver;
    	Thread.sleep(10000);
    	WebElement identities=EYUtils.getElementPresent(By.xpath("//img[@alt='SailPoint Logo']//following::a[8]"));
    	
    	try {
    		uiElement(identities).click();
			Thread.sleep(3000);
		} catch (StaleElementReferenceException exceptionMessage) {
			identities=driver.findElement(By.xpath("//img[@alt='SailPoint Logo']//following::a[8]"));
			Thread.sleep(3000);
			uiElement(identities).click();
			System.out.println(exceptionMessage.getMessage());
			
		}

    	
    	Thread.sleep(3000);
    	
    	WebElement identityWarehouseElement=EYUtils.getElementPresent(By.xpath("//img[@alt='SailPoint Logo']//following::a[9]"));
    	
    	
    	try {
    		uiElement(identityWarehouseElement).click();
    
			System.out.println("IdentitywareHouse click");
			Thread.sleep(3000);
		} catch (StaleElementReferenceException exceptionMessage) {
			identityWarehouseElement=driver.findElement(By.xpath("//img[@alt='SailPoint Logo']//following::a[9]"));
			
			uiElement(identityWarehouseElement).click();
			System.out.println(exceptionMessage.getMessage());
			
		}
    	TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
				ResourceStrings.IIQ_MANAGE_USER_ACCESS_IDENTITY,report, test, driver);
    	Thread.sleep(3000);
    	
    	
    	
    	WebElement userSearchfieldtextbox=EYUtils.getElementPresent(By.xpath("//input[starts-with(@id,'searchfield')]"));
    	
    	try {
    		EYUtils.waitForElementVisibleWithPollingTime(userSearchfieldtextbox, 30, 2);
    		uiElement(userSearchfieldtextbox).sendKeys(userdetails);
    		Thread.sleep(3000);
    		uiElement(userSearchfieldtextbox).sendKeys(Keys.ENTER);
    		
		} catch (StaleElementReferenceException e) {
			userSearchfieldtextbox=EYUtils.getElementPresent(By.xpath("//input[starts-with(@id,'searchfield')]"));
			Thread.sleep(3000);
			
			uiElement(userSearchfieldtextbox).sendKeys(userdetails);
    		Thread.sleep(3000);
    		uiElement(userSearchfieldtextbox).sendKeys(Keys.ENTER);
		}
		Thread.sleep(3000);
	
		//WebElement userDetailsLink=EYUtils.getElementPresent(By.xpath("(//div[starts-with(@id,'paginggrid')])[2]//table/tbody/tr[2]/td[1]"));
		WebElement userDetailsLink=EYUtils.getElementPresent(By.xpath("//span[text()='T-Number']//following::table[1]"));
		Thread.sleep(6000);
		
		try {
			EYUtils.waitForElementClickable(userDetailsLink, 30, 2);
			uiElement(userDetailsLink).click();
			TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
					ResourceStrings.IIQ_MANAGE_USER_ACCESS_VIEWIDENTITY +"==>" + userdetails,report, test, driver);
		} catch (StaleElementReferenceException e) {
			Thread.sleep(3000);
			userDetailsLink=EYUtils.getElementPresent(By.xpath("(//div[starts-with(@id,'paginggrid')])[2]//table/tbody/tr[2]/td[1]"));
			Thread.sleep(3000);
			EYUtils.waitForElementClickable(userDetailsLink, 30, 2);
			uiElement(userDetailsLink).click();	
			TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
					ResourceStrings.IIQ_MANAGE_USER_ACCESS_VIEWIDENTITY +"==>" + userdetails,report, test, driver);
		}
		Thread.sleep(3000);
		WebElement applicationAccountstab=EYUtils.getElementPresent(By.xpath("//span[text()='Application Accounts']"));

		try {
			uiElement(applicationAccountstab).click();
			TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
					ResourceStrings.IIQ_MANAGE_USER_ACCESS_APPLICATION_ACCOUNTS +":"+ userdetails +"==>"  +"ApplicationName :"+"==>" + applicationName+"Entitlement is Removed or denied: " +"==>" + entitlement, report, test, driver);
		} catch (StaleElementReferenceException e) {
			applicationAccountstab=EYUtils.getElementPresent(By.xpath("//span[text()='Application Accounts']"));
			uiElement(applicationAccountstab).click();
		}
		
		Thread.sleep(3000);
		
		if (requestType.equalsIgnoreCase("ADD")) {
		for (int i = 0; i < applicationAccountsList.size(); i++) {
			Thread.sleep(3000);
			WebElement accountsNumber=applicationAccountsList.get(i);
			Thread.sleep(3000);
			String accountText=accountsNumber.getText();
		
			Thread.sleep(3000);
			System.out.println("accountText :" + accountText);//all entitlement print
			Thread.sleep(3000);
			if (accountText.equalsIgnoreCase(applicationName)) {
				Thread.sleep(3000);
				try {
					 js.executeScript("arguments[0].scrollIntoView(true);", accountsNumber);
						Thread.sleep(1000);
						js.executeScript("arguments[0].click();", accountsNumber);
						System.out.println("after application click");
						TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
								ResourceStrings.IIQ_MANAGE_USER_ACCESS_APPLICATION_ACCOUNTS +"User Details:"+ userdetails +"==>" +"ApplicationName :"+ "==>"  + applicationName +"==>" +"Entitlement is Added: " , report, test, driver);
                   	
						
						Thread.sleep(3000);
                		List<WebElement>allRole=driver.findElements(By.xpath("//a[@title='Click to view the application account details.']//following::span[starts-with(@id,'name')]"));
       					for (int j = 0; j < allRole.size(); j++) {
       						System.out.println(" ADD Inside sub for loop");
				String rollName=allRole.get(j).getText();
				System.out.println("print allRoleName : " + rollName);
           if (rollName.contains(entitlement)) {
       		TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.PASS,
					ResourceStrings.IIQ_MANAGE_USER_ACCESS_APPLICATION_ACCOUNTS +":"+ userdetails +"ApplicationName :" + applicationName +"Entitlement is Added : " + rollName, report, test, driver);
                            }
           
           else {
        	   TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.FAIL,
						ResourceStrings.IIQ_MANAGE_USER_ACCESS_APPLICATION_ACCOUNTS +":"+ userdetails +"ApplicationName :" + applicationName +"Entitlement is not added : " + rollName, report, test, driver);
		}
           
			}
	
						js.executeScript("window.scrollBy(0,-1000)");
				} catch (StaleElementReferenceException e) {
					js.executeScript("arguments[0].scrollIntoView(true);", accountsNumber);
						
						Thread.sleep(3000);
						js.executeScript("arguments[0].click();", accountsNumber);
						Thread.sleep(3000);
						System.out.println("after application click");
						TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
								ResourceStrings.IIQ_MANAGE_USER_ACCESS_APPLICATION_ACCOUNTS +":"+ userdetails +"==>"  +"ApplicationName :"+"==>" + applicationName+"Entitlement is Added: " +"==>" + entitlement, report, test, driver);
						Thread.sleep(3000);
                		List<WebElement>allRole=driver.findElements(By.xpath("//a[@title='Click to view the application account details.']//following::span[starts-with(@id,'name')]"));
       					for (int j = 0; j < allRole.size(); j++) {
				String rollName=allRole.get(j).getText();
				System.out.println("print allRoleName : " + rollName );
           if (rollName.contains(entitlement)) {
       		TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
					ResourceStrings.IIQ_MANAGE_USER_ACCESS_APPLICATION_ACCOUNTS +":"+ userdetails +"ApplicationName :" + applicationName +"Entitlement is Added : " + rollName, report, test, driver);
			js.executeScript("window.scrollBy(0,-1000)");
                            }
           
           else {
        	   TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
						ResourceStrings.IIQ_MANAGE_USER_ACCESS_APPLICATION_ACCOUNTS +":"+ userdetails +"ApplicationName :" + applicationName +"Entitlement is not added : " + rollName, report, test, driver);
				js.executeScript("window.scrollBy(0,-1000)");
		}
           
			}

				}
                   break;
			}
	
		}
		}
		else if (requestType.equalsIgnoreCase("REMOVE") || requestType.equalsIgnoreCase("DENY")  ) {    	
				for (int i = 0; i < applicationAccountsList.size(); i++) {
					WebElement accountsNumber=applicationAccountsList.get(i);
					Thread.sleep(1000);
					String accountText=accountsNumber.getText();
					Thread.sleep(1000);
					System.out.println("accountText :" + accountText);
					Thread.sleep(1000);
					if (accountText.equalsIgnoreCase(applicationName)) {
						Thread.sleep(2000);
						try {
							 js.executeScript("arguments[0].scrollIntoView(true);", accountsNumber);
								
								Thread.sleep(1000);
								js.executeScript("arguments[0].click();", accountsNumber);
								System.out.println("after application click");
								Thread.sleep(1000);
		                   		TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
										ResourceStrings.IIQ_MANAGE_USER_ACCESS_APPLICATION_ACCOUNTS +":"+ userdetails +":" +"ApplicationName :" +"==>"+ applicationName + "==>" +  entitlement + "Entitlement is Removed or Denied: ", report, test, driver);
		                   	Thread.sleep(3000);
		                   	
		                   		List<WebElement>allRole=driver.findElements(By.xpath("//a[@title='Click to view the application account details.']//following::span[starts-with(@id,'name')]"));
		                   				
		                   		for (int j = 0; j < allRole.size(); j++) {
		                   			System.out.println("Inside sub for loop");
									String rollName=allRole.get(j).getText();
									System.out.println("print allRoleName : " + rollName );
		                       if (rollName.contains(entitlement)) {
		                   		TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
										ResourceStrings.IIQ_MANAGE_USER_ACCESS_APPLICATION_ACCOUNTS +":"+ userdetails +"ApplicationName :" + applicationName +"Entitlement is Removed or Denied: " + rollName, report, test, driver);
								js.executeScript("window.scrollBy(0,-1000)");
		                                        }
		                       
		                       else {
		                    	   TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
											ResourceStrings.IIQ_MANAGE_USER_ACCESS_APPLICATION_ACCOUNTS +":"+ userdetails +"ApplicationName :" + applicationName +"Entitlement is Removed or Denied: " + rollName, report, test, driver);
									js.executeScript("window.scrollBy(0,-1000)");
							}
		                       
								}
							
								
								
								TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
										ResourceStrings.IIQ_MANAGE_USER_ACCESS_APPLICATION_ACCOUNTS +":"+ userdetails +"==>"  +"ApplicationName :"+"==>" + applicationName+"Entitlement is Removed or denied: " +"==>" + entitlement, report, test, driver);
								js.executeScript("window.scrollBy(0,-1000)");
						
								
								
						} catch (StaleElementReferenceException e) {
							js.executeScript("arguments[0].scrollIntoView(true);", accountsNumber);
								
								Thread.sleep(3000);
								js.executeScript("arguments[0].click();", accountsNumber);
								Thread.sleep(3000);
								System.out.println("after application click");
								TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
										ResourceStrings.IIQ_MANAGE_USER_ACCESS_APPLICATION_ACCOUNTS +":"+ userdetails +"==>"  +"ApplicationName :"+"==>" + applicationName+"Entitlement is Removed or denied: " +"==>" + entitlement, report, test, driver);
				              	Thread.sleep(3000);
			                   	
		                   		List<WebElement>allRole=driver.findElements(By.xpath("//a[@title='Click to view the application account details.']//following::span[starts-with(@id,'name')]"));
		                   				
		                   		for (int j = 0; j < allRole.size(); j++) {
		                   			System.out.println(" Deny/remove Inside sub for loop");
									String rollName=allRole.get(j).getText();
									System.out.println("print allRoleName : " + rollName );
		                       if (rollName.contains(entitlement)) {
		                   		TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
										ResourceStrings.IIQ_MANAGE_USER_ACCESS_APPLICATION_ACCOUNTS +":"+ userdetails +"ApplicationName :" + applicationName +"Entitlement is Removed or Denied: " + rollName, report, test, driver);
								js.executeScript("window.scrollBy(0,-1000)");
		                                        }
		                       
		                       else {
		                    	   TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
											ResourceStrings.IIQ_MANAGE_USER_ACCESS_APPLICATION_ACCOUNTS +":"+ userdetails +"ApplicationName :" + applicationName +"Entitlement is Removed or Denied: " + rollName, report, test, driver);
									js.executeScript("window.scrollBy(0,-1000)");
							}
		                       
								}
						}
                          break;
					}
				}
		    	
		    	
		    	
		        System.out.println("application accounts work done");
	
		}
		

		
		Thread.sleep(5000);
		js.executeScript("window.scrollBy(0,-1000)");
		Thread.sleep(5000);
		System.out.println(" after Applicatipn accounts validation");
		
		WebElement entitlementDetailsTab=EYUtils.getElementPresent(By.xpath("//span[text()='Entitlements']"));
		Thread.sleep(5000);
		try {
			System.out.println("entitlementDetailsTab click inside try ");
			if (entitlementDetailsTab.isDisplayed()) {
				js.executeScript("arguments[0].scrollIntoView(true);",entitlementDetailsTab);
				System.out.println("entitlementsTab identified ready to click");
				
				js.executeScript("arguments[0].click();", entitlementDetailsTab);
			}
		
		} catch (StaleElementReferenceException e) {
			entitlementDetailsTab=EYUtils.getElementPresent(By.xpath("//span[text()='Entitlements']"));
			if (entitlementDetailsTab.isDisplayed()) {
				js.executeScript("arguments[0].scrollIntoView(true);",entitlementDetailsTab);
				System.out.println("entitlementsTab identified ready to click");
				
				js.executeScript("arguments[0].click();", entitlementDetailsTab);
			}
			js.executeScript("arguments[0].click();", entitlementDetailsTab);
		}
	
		Thread.sleep(5000);


		try {
			js.executeScript("arguments[0].scrollIntoView(true);",entitlementSearchbox);
			Thread.sleep(3000);
			UtilsFunctions.functionTextBox(entitlementSearchbox, applicationName);
			Thread.sleep(3000);
			entitlementSearchbox.sendKeys(Keys.ENTER);
			Thread.sleep(3000);
		} catch (StaleElementReferenceException e) {
			js.executeScript("arguments[0].scrollIntoView(true);",entitlementSearchbox);
			Thread.sleep(3000);
			UtilsFunctions.functionTextBox(entitlementSearchbox, applicationName);
			Thread.sleep(3000);
			entitlementSearchbox.sendKeys(Keys.ENTER);
			
		}	

		js.executeScript("window.scrollBy(0,1000)");
	
		TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
					ResourceStrings.IIQ_MANAGE_USER_ACCESS_ENTITLEMENTSDETAILS +":"+ userdetails + "==> "+"applicationName is : " + applicationName, report, test, driver);
	
			   report.endTest(test);
			   
    }
    
 

    
    
    
    
    
    public void validateUserAccountsAndEntitlementForMultipleuser(String userdetails,String requestType,String applicationName,String entitlement) throws InterruptedException, AWTException {
    	System.out.println("validateUserAccountsAndEntitlementForMultipleuser execution start");
    	
    	test=report.startTest(ResourceStrings.IIQ_MANAGE_USER_ACCESS_IDENTITYWAREHOUSE);
    	report.flush();
    	test.assignCategory("validateUserAccountsAndEntitlements : " + "Userdetails :" + userdetails +"applicationName :" + applicationName + "requestType :" + requestType + "entitlement Name :" + entitlement);

		
		System.out.println("Userdetails :" + userdetails);
		System.out.println("requestType :" + requestType);
		System.out.println("applicationName :" + applicationName);
		System.out.println("entitlement Name :" + entitlement);
		JavascriptExecutor js=(JavascriptExecutor)driver;
    	Thread.sleep(10000);

    	
    	
    	List<String> userList = new ArrayList<String>();
    	String[] splitArray = userdetails.split(",");
		Thread.sleep(2000);
		userList = Arrays.asList(splitArray);
		System.out.println("users list ==" + userList);

		int userCount = userList.size();
		System.out.println("userCount ==" + userCount);
		for (String user : userList) {
			System.out.println("user from list is ==" + user);
			Thread.sleep(3000);
    	
    	
	    	WebElement myWorkelement=EYUtils.getElementPresent(By.xpath("//img[@alt='SailPoint Logo']//following::a[8]"));
	    	
	    	try {
	    		uiElement(myWorkelement).click();
				Thread.sleep(3000);
			} catch (StaleElementReferenceException exceptionMessage) {
				myWorkelement=driver.findElement(By.xpath("//img[@alt='SailPoint Logo']//following::a[8]"));
				Thread.sleep(3000);
				uiElement(myWorkelement).click();
				System.out.println(exceptionMessage.getMessage());
				
			}

	    	
	    	Thread.sleep(3000);
	    	
	    	WebElement identityWarehouseElement=EYUtils.getElementPresent(By.xpath("//img[@alt='SailPoint Logo']//following::a[9]"));
	    	
	    	
	    	try {
	    		uiElement(identityWarehouseElement).click();
	    
				System.out.println("IdentitywareHouse click");
				Thread.sleep(3000);
			} catch (StaleElementReferenceException exceptionMessage) {
				identityWarehouseElement=driver.findElement(By.xpath("//img[@alt='SailPoint Logo']//following::a[9]"));
				
				Thread.sleep(3000);
				uiElement(identityWarehouseElement).click();
				System.out.println(exceptionMessage.getMessage());
				
			}

	    	Thread.sleep(3000);
    	
    	
    	
    	
    	WebElement userSearchfieldtextbox=EYUtils.getElementPresent(By.xpath("//input[starts-with(@id,'searchfield')]"));
    	
    	try {
    		EYUtils.waitForElementVisibleWithPollingTime(userSearchfieldtextbox, 30, 2);
    		uiElement(userSearchfieldtextbox).sendKeys(user);
    		Thread.sleep(3000);
    		uiElement(userSearchfieldtextbox).sendKeys(Keys.ENTER);
		} catch (StaleElementReferenceException e) {
			userSearchfieldtextbox=EYUtils.getElementPresent(By.xpath("//input[starts-with(@id,'searchfield')]"));
			Thread.sleep(3000);
			
			uiElement(userSearchfieldtextbox).sendKeys(user);
    		Thread.sleep(3000);
    		uiElement(userSearchfieldtextbox).sendKeys(Keys.ENTER);
		}
		Thread.sleep(3000);
	
		//WebElement userDetailsLink=EYUtils.getElementPresent(By.xpath("(//div[starts-with(@id,'paginggrid')])[2]//table/tbody/tr[2]/td[1]"));
		WebElement userDetailsLink=EYUtils.getElementPresent(By.xpath("//span[text()='T-Number']//following::table[1]"));
		Thread.sleep(6000);
		
		try {
			EYUtils.waitForElementClickable(userDetailsLink, 30, 2);
			uiElement(userDetailsLink).click();
		} catch (StaleElementReferenceException e) {
			userDetailsLink=EYUtils.getElementPresent(By.xpath("//span[text()='T-Number']//following::table[1]"));
			Thread.sleep(3000);
			EYUtils.waitForElementClickable(userDetailsLink, 30, 2);
			uiElement(userDetailsLink).click();	
		}
		Thread.sleep(3000);
		WebElement applicationAccountstab=EYUtils.getElementPresent(By.xpath("//span[text()='Application Accounts']"));

		try {
			uiElement(applicationAccountstab).click();
			TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
					ResourceStrings.IIQ_MANAGE_USER_ACCESS_APPLICATION_ACCOUNTS +":"+ user +"==>"  +"ApplicationName :"+"==>" + applicationName +"Entitlement is add or removed", report, test, driver);
		} catch (StaleElementReferenceException e) {
			applicationAccountstab=EYUtils.getElementPresent(By.xpath("//span[text()='Application Accounts']"));
			uiElement(applicationAccountstab).click();
		}
		
		Thread.sleep(3000);
		
		if (requestType.equalsIgnoreCase("ADD")) {
			System.out.println("inside add");
			
			
			
			List<WebElement> applicationAccountsList=driver.findElements(By.xpath("//a[@title='Click to view the application account details.']"));
			
			
			
			
		for (int i = 0; i < applicationAccountsList.size(); i++) {
			Thread.sleep(3000);
			WebElement accountsNumber=applicationAccountsList.get(i);
			Thread.sleep(3000);
			String accountText=accountsNumber.getText();
		
			Thread.sleep(3000);
			System.out.println("accountText :" + accountText);//all entitlement print
			Thread.sleep(3000);
			if (accountText.equalsIgnoreCase(applicationName)) {
				Thread.sleep(3000);
				try {
					 js.executeScript("arguments[0].scrollIntoView(true);", accountsNumber);
						Thread.sleep(1000);
						js.executeScript("arguments[0].click();", accountsNumber);
						System.out.println("after application click");
						TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
								ResourceStrings.IIQ_MANAGE_USER_ACCESS_APPLICATION_ACCOUNTS +"User Details:"+ userdetails +"==>" +"ApplicationName :"+ "==>"  + applicationName +"==>" +"Entitlement is Added: " , report, test, driver);
                   	
						
						Thread.sleep(3000);
                		List<WebElement>allRole=driver.findElements(By.xpath("//a[@title='Click to view the application account details.']//following::span[starts-with(@id,'name')]"));
       					for (int j = 0; j < allRole.size(); j++) {
       						System.out.println(" ADD Inside sub for loop");
				String rollName=allRole.get(j).getText();
				System.out.println("print allRoleName : " + rollName );
           if (rollName.contains(entitlement)) {
        	   System.out.println("inside rolename if");
       		TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
					ResourceStrings.IIQ_MANAGE_USER_ACCESS_APPLICATION_ACCOUNTS +":"+ userdetails +"ApplicationName :" + applicationName +"Entitlement is Added : " + rollName, report, test, driver);
                            }
           
           else {
        	   System.out.println("inside rolename if");
        	   TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.FAIL,
						ResourceStrings.IIQ_MANAGE_USER_ACCESS_APPLICATION_ACCOUNTS +":"+ userdetails +"ApplicationName :" + applicationName +"Entitlement is not added : " + rollName, report, test, driver);
		}
           
			}
						
						
						
						js.executeScript("window.scrollBy(0,-1000)");
				} catch (StaleElementReferenceException e) {
					js.executeScript("arguments[0].scrollIntoView(true);", accountsNumber);
						
						Thread.sleep(3000);
						js.executeScript("arguments[0].click();", accountsNumber);
						Thread.sleep(3000);
						System.out.println("after application click");
						TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
								ResourceStrings.IIQ_MANAGE_USER_ACCESS_APPLICATION_ACCOUNTS +":"+ userdetails +"==>"  +"ApplicationName :"+"==>" + applicationName+"Entitlement is Added: " +"==>" + entitlement, report, test, driver);
						Thread.sleep(3000);
                		List<WebElement>allRole=driver.findElements(By.xpath("//a[@title='Click to view the application account details.']//following::span[starts-with(@id,'name')]"));
       					for (int j = 0; j < allRole.size(); j++) {
				String rollName=allRole.get(j).getText();
				System.out.println("print allRoleName : " + rollName );
              if (rollName.contains(entitlement)) {
       		TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
					ResourceStrings.IIQ_MANAGE_USER_ACCESS_APPLICATION_ACCOUNTS +":"+ userdetails +"ApplicationName :" + applicationName +"Entitlement is Added : " + rollName, report, test, driver);
			js.executeScript("window.scrollBy(0,-1000)");
                            }
           else {
        	   TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.FAIL,
						ResourceStrings.IIQ_MANAGE_USER_ACCESS_APPLICATION_ACCOUNTS +":"+ userdetails +"ApplicationName :" + applicationName +"Entitlement is not added : " + rollName, report, test, driver);
				js.executeScript("window.scrollBy(0,-1000)");
		}
           
			}
		
				
				
				
				}
                   break;
			}
	
		}
		}
		    else if (requestType.equalsIgnoreCase("REMOVE") || requestType.equalsIgnoreCase("DENY")  ) {   
		    	System.out.println("inside else if remove/deny");
		    	List<WebElement> applicationAccountsList=driver.findElements(By.xpath("//a[@title='Click to view the application account details.']"));
				for (int i = 0; i < applicationAccountsList.size(); i++) {
					WebElement accountsNumber=applicationAccountsList.get(i);
					Thread.sleep(1000);
					String accountText=accountsNumber.getText();
					Thread.sleep(1000);
					System.out.println("accountText :" + accountText);
					Thread.sleep(1000);
					if (accountText.equalsIgnoreCase(applicationName)) {
						System.out.println("inside if accountText lists :");
						Thread.sleep(2000);
						try {
							 js.executeScript("arguments[0].scrollIntoView(true);", accountsNumber);
								
								Thread.sleep(1000);
								js.executeScript("arguments[0].click();", accountsNumber);
								System.out.println("after application click");
								Thread.sleep(1000);
		                   		TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
										ResourceStrings.IIQ_MANAGE_USER_ACCESS_APPLICATION_ACCOUNTS +":"+ userdetails +":" +"ApplicationName :" +"==>"+ applicationName + "==>" +  entitlement + "Entitlement is Removed or Denied: ", report, test, driver);
		                   	Thread.sleep(3000);
		                   	
		                   		List<WebElement>allRole=driver.findElements(By.xpath("//a[@title='Click to view the application account details.']//following::span[starts-with(@id,'name')]"));
		                   		System.out.println("allRole" + allRole);	
		                   		for (int j = 0; j < allRole.size(); j++) {
		                   			System.out.println("Inside sub for loop");
									String rollName=allRole.get(j).getText();
							
		                       if (rollName.contains(entitlement)) {
		                    	   System.out.println("rollName if block");
		                   		System.out.println("print allRoleName : " + rollName );
		                   		TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
										ResourceStrings.IIQ_MANAGE_USER_ACCESS_APPLICATION_ACCOUNTS +":"+ userdetails +"ApplicationName :" + applicationName +"Entitlement is Removed or Denied: " + rollName, report, test, driver);
								js.executeScript("window.scrollBy(0,-1000)");
		                                        }
		                       
		                       else {
		                    	   System.out.println("roll name else b lock");
		                    	   TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.FAIL,
											ResourceStrings.IIQ_MANAGE_USER_ACCESS_APPLICATION_ACCOUNTS +":"+ userdetails +"ApplicationName :" + applicationName +"Entitlement is Removed or Denied: " + rollName, report, test, driver);
									js.executeScript("window.scrollBy(0,-1000)");
							}
		                       
								}
							
								
								
								TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
										ResourceStrings.IIQ_MANAGE_USER_ACCESS_APPLICATION_ACCOUNTS +":"+ userdetails +"==>"  +"ApplicationName :"+"==>" + applicationName+"Entitlement is Removed or denied: " +"==>" + entitlement, report, test, driver);
								js.executeScript("window.scrollBy(0,-1000)");
						
								
								
						} catch (StaleElementReferenceException e) {
							js.executeScript("arguments[0].scrollIntoView(true);", accountsNumber);
								
								Thread.sleep(3000);
								js.executeScript("arguments[0].click();", accountsNumber);
								Thread.sleep(3000);
								System.out.println("after application click");
								TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
										ResourceStrings.IIQ_MANAGE_USER_ACCESS_APPLICATION_ACCOUNTS +":"+ userdetails +"==>"  +"ApplicationName :"+"==>" + applicationName+"Entitlement is Removed or denied: " +"==>" + entitlement, report, test, driver);
				              	Thread.sleep(3000);
			                   	
		                   		List<WebElement>allRole=driver.findElements(By.xpath("//a[@title='Click to view the application account details.']//following::span[starts-with(@id,'name')]"));
		                   				
		                   		for (int j = 0; j < allRole.size(); j++) {
		                   			System.out.println(" Deny/remove Inside sub for loop");
									String rollName=allRole.get(j).getText();
									System.out.println("print allRoleName : " + rollName );
		                       if (rollName.contains(entitlement)) {
		                   		TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
										ResourceStrings.IIQ_MANAGE_USER_ACCESS_APPLICATION_ACCOUNTS +":"+ userdetails +"ApplicationName :" + applicationName +"Entitlement is Removed or Denied: " + rollName, report, test, driver);
								js.executeScript("window.scrollBy(0,-1000)");
		                                        }
		                       
		                       else {
		                    	   TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.FAIL,
											ResourceStrings.IIQ_MANAGE_USER_ACCESS_APPLICATION_ACCOUNTS +":"+ userdetails +"ApplicationName :" + applicationName +"Entitlement is Removed or Denied: " + rollName, report, test, driver);
									js.executeScript("window.scrollBy(0,-1000)");
							}
		                       
								}
						}
                          break;
					}
				}
		    	
		    	
		    	
		        System.out.println("application accounts work done");
	
		}
		

		
		Thread.sleep(5000);
		js.executeScript("window.scrollBy(0,-1000)");
		System.out.println(" after Applicatipn accounts validation");
		
		WebElement entitlementDetailsTab=EYUtils.getElementPresent(By.xpath("//span[text()='Entitlements']"));
		Thread.sleep(5000);
		try {
			System.out.println("entitlementDetailsTab click inside try ");
			if (entitlementDetailsTab.isDisplayed()) {
				js.executeScript("arguments[0].scrollIntoView(true);",entitlementDetailsTab);
				System.out.println("entitlementsTab identified ready to click");
				
				js.executeScript("arguments[0].click();", entitlementDetailsTab);
			}
		
		} catch (StaleElementReferenceException e) {
			entitlementDetailsTab=EYUtils.getElementPresent(By.xpath("//span[text()='Entitlements']"));
			if (entitlementDetailsTab.isDisplayed()) {
				js.executeScript("arguments[0].scrollIntoView(true);",entitlementDetailsTab);
				System.out.println("entitlementsTab identified ready to click");
				
				js.executeScript("arguments[0].click();", entitlementDetailsTab);
			}
		}
	
		Thread.sleep(5000);
		WebElement entitlementSearchbox=EYUtils.getElementPresent(By.id("identityEntitlementAppNameField-inputEl"));
		
		try {
			js.executeScript("arguments[0].scrollIntoView(true);",entitlementSearchbox);
			Thread.sleep(3000);
			UtilsFunctions.functionTextBox(entitlementSearchbox, applicationName);
			Thread.sleep(3000);
			entitlementSearchbox.sendKeys(Keys.ENTER);
			Thread.sleep(3000);
		} catch (StaleElementReferenceException e) {
			entitlementSearchbox=EYUtils.getElementPresent(By.id("identityEntitlementAppNameField-inputEl"));
			js.executeScript("arguments[0].scrollIntoView(true);",entitlementSearchbox);
			Thread.sleep(3000);
			UtilsFunctions.functionTextBox(entitlementSearchbox, applicationName);
			Thread.sleep(3000);
			entitlementSearchbox.sendKeys(Keys.ENTER);
			
		}	

		js.executeScript("window.scrollBy(0,1000)");
	
		TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
					ResourceStrings.IIQ_MANAGE_USER_ACCESS_ENTITLEMENTSDETAILS +":"+ userdetails + "==> "+"applicationName is : " + applicationName, report, test, driver);
	
			   report.endTest(test);
			   
    }
    
    }
    
    
    
    

}
