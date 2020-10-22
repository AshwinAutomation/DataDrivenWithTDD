package com.ey.iiqcommons.elementpage;
import java.awt.AWTException;
import java.util.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.ey.generics.EYUtils;
import com.ey.iiqesourcestring.ResourceStrings;
import com.qa.testbase.TestBase;
import com.relevantcodes.extentreports.LogStatus;

public class TaskRun extends TestBase {
	// Setup
	@FindBy(xpath = "//img[@alt='SailPoint Logo']//following::a[22]")
	@CacheLookup
	WebElement setupLink;

	// Tasks
	@FindBy(xpath = "//img[@alt='SailPoint Logo']//following::a[27]")
	@CacheLookup
	WebElement tasksLink;

	// Search Bar for perform maintenance
	@FindBy(xpath = "//input[@id='tasksSearchField-inputEl']")
	@CacheLookup
	WebElement searchBarPerform;

	// SEARCH BUTTON Perform Maintenance
	@FindBy(xpath = "//td[@id='tasksSearchField-inputCell']//following::div[2]")
	@CacheLookup
	WebElement searchButton;

	// Perform Maintenance
	@FindBy(xpath = "//div[contains(@class,'x-grid-cell-inner') and text()='Perform Maintenance']")
	@CacheLookup
	WebElement performTextClick;

	@FindBy(xpath = "//div[contains(@class,'x-grid-cell-inner') and text()='Perform Identity Request Maintenance']")
	@CacheLookup
	WebElement performIdentityRequestMaintenanceTextClick;

	
	@FindBy(xpath = "(//div[contains(@class,'x-grid-cell-inner')]| //div[starts-with(text(),'Account Aggregation -' )])[2]")
	@CacheLookup
	WebElement AccountAggregationTextClick;

	
	// SaveNExecute
	@FindBy(xpath = "//input[@id='editForm:validateBeforeExecuteButton']")
	@CacheLookup
	WebElement saveNExecute;

	// OKBtn
	@FindBy(xpath = "//span[text()='OK']")
	@CacheLookup
	WebElement okButton;

	@FindBy(xpath = "//span[.='Task Results']")
	@CacheLookup
	WebElement gototaskResults;

	@FindBy(id = "resultsSearchField-inputEl")
	@CacheLookup
	WebElement searchTask;

	@FindBy(id = "resultFilterButton-btnEl")
	@CacheLookup
	WebElement searchbtn;
	@FindBy(xpath = "/html/body/div[1]/div/div[3]/div/div/div/div[2]/div[2]/div[2]/div[3]/div/table/tbody/tr[2]/td[1]/div")
	@CacheLookup
	WebElement click_taskResult;
	@FindBy(id = "taskResultsDetailsDiv")
	@CacheLookup
	WebElement taskResultDetails;

	@FindBy(id = "editForm:resultPanel")
	@CacheLookup
	WebElement resultPanel;

	@FindBy(xpath = "//*[@id='editForm:resultPanel']/div[2]/table/tbody/tr/td")
	@CacheLookup
	WebElement formError;
	@FindBy(partialLinkText = "Home")
	@CacheLookup
	WebElement homeLink;

	
	
	@FindBy(xpath = "//input[@value='Return to Tasks']")
	@CacheLookup
	WebElement returnToTask;
	@FindBy(xpath = "//span[text()='Tasks']")
	@CacheLookup
	WebElement taskTab;
	
	public TaskRun() {
		PageFactory.initElements(driver, this);
	}
	
     JavascriptExecutor js=(JavascriptExecutor)driver;
     

	public void taskRun(String performIdentityRequestMaintenance,String accountAggregation) throws InterruptedException, AWTException, ElementNotVisibleException {
    	test=report.startTest(ResourceStrings.IIQ_MANAGE_USER_ACCESS_TASK);
    	report.flush();
		System.out.println("performIdentityRequestMaintenance : " + performIdentityRequestMaintenance);
		System.out.println("accountAggregation : " + accountAggregation);
		JavascriptExecutor js=(JavascriptExecutor)driver;
		try {
		
		Thread.sleep(3000);
		WebElement setupAnchor=EYUtils.getElementPresent(By.xpath("//img[@alt='SailPoint Logo']//following::a[22]"));
		try {
			js.executeScript("arguments[0].scrollIntoView(true);", setupAnchor);
			EYUtils.uiElement(setupAnchor).click();
		} catch (StaleElementReferenceException e) {
			setupAnchor=EYUtils.getElementPresent(By.xpath("//img[@alt='SailPoint Logo']//following::a[22]"));
			setupAnchor.click();
			//EYUtils.uiElement(setupAnchor).click();
		}

		
		Thread.sleep(3000);
		WebElement taskAnchor=EYUtils.getElementPresent(By.xpath("//img[@alt='SailPoint Logo']//following::a[27]"));
		try {
			js.executeScript("arguments[0].scrollIntoView(true);", taskAnchor);
			EYUtils.uiElement(taskAnchor).click();
		} catch (Exception e) {
			taskAnchor=EYUtils.getElementPresent(By.xpath("//img[@alt='SailPoint Logo']//following::a[27]"));
			EYUtils.uiElement(taskAnchor).click();
		}
		
		

		Thread.sleep(4000);
		
		
		WebElement searchBar=EYUtils.getElementPresent(By.xpath("//input[@id='tasksSearchField-inputEl']"));
		try {
			js.executeScript("arguments[0].scrollIntoView(true);", searchBar);
			EYUtils.uiElement(searchBar).sendKeys(accountAggregation);
		} catch (Exception e) {
			searchBar=EYUtils.getElementPresent(By.xpath("//input[@id='tasksSearchField-inputEl']"));
			js.executeScript("arguments[0].scrollIntoView(true);", searchBar);
			EYUtils.uiElement(searchBar).sendKeys(accountAggregation);
		}
	   	TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
					ResourceStrings.IIQ_MANAGE_USER_ACCESS_TASK_RUN_ACCOUNT_AGGREGATION+accountAggregation,report, test, driver);
	    	Thread.sleep(3000);
		
		WebElement searchBarButton=EYUtils.getElementPresent(By.xpath("//td[@id='tasksSearchField-inputCell']//following::div[2]"));
		
		
		Thread.sleep(3000);
		
		try {
			js.executeScript("arguments[0].scrollIntoView(true);", searchBarButton);
			EYUtils.uiElement(searchBarButton).click();
		} catch (Exception e) {
			searchBarButton=EYUtils.getElementPresent(By.xpath("//td[@id='tasksSearchField-inputCell']//following::div[2]"));
			js.executeScript("arguments[0].scrollIntoView(true);", searchBarButton);
			EYUtils.uiElement(searchBarButton).click();
		}		
		
		
		Thread.sleep(2000);
		WebElement accountAggregationTextClick=EYUtils.getElementPresent(By.xpath("(//div[contains(@class,'x-grid-cell-inner')]| //div[starts-with(text(),'Account Aggregation -' )])[2]"));
		EYUtils.uiElement(accountAggregationTextClick).click();
		
		try {
			js.executeScript("arguments[0].scrollIntoView(true);", accountAggregationTextClick);
			EYUtils.uiElement(accountAggregationTextClick).click();
		} catch (Exception e) {
			accountAggregationTextClick=EYUtils.getElementPresent(By.xpath("(//div[contains(@class,'x-grid-cell-inner')]| //div[starts-with(text(),'Account Aggregation -' )])[2]"));
			EYUtils.uiElement(accountAggregationTextClick).click();
			
		}
		
		Thread.sleep(10000);
		WebElement saveAndexecuteButton=EYUtils.getElementPresent(By.xpath("//input[@id='editForm:validateBeforeExecuteButton' and @name='editForm:validateBeforeExecuteButton']"));

		try {
			js.executeScript("arguments[0].scrollIntoView(true);", saveAndexecuteButton);
			Thread.sleep(2000);
			EYUtils.uiElement(saveAndexecuteButton).click();
		} catch (StaleElementReferenceException e) {
			saveAndexecuteButton=EYUtils.getElementPresent(By.xpath("//input[@id='editForm:validateBeforeExecuteButton']"));
			js.executeScript("arguments[0].scrollIntoView(true);", saveAndexecuteButton);
			Thread.sleep(2000);
			EYUtils.uiElement(saveAndexecuteButton).click();
		}
		Thread.sleep(3000);
		
		WebElement okButon=EYUtils.getElementPresent(By.xpath("//span[text()='OK']"));
		try {
			js.executeScript("arguments[0].scrollIntoView(true);", okButon);
			EYUtils.uiElement(okButon).click();
			
		} catch (StaleElementReferenceException e) {
			okButon=EYUtils.getElementPresent(By.xpath("//span[text()='OK']"));
			js.executeScript("arguments[0].scrollIntoView(true);", okButon);
			EYUtils.uiElement(okButon).click();
		}

		
		System.out.println("runAccountAggregation end");
		System.out.println("call to taskResults method ");
		Thread.sleep(5000);
		
		
		Thread.sleep(4000);
	   WebElement taskResult=EYUtils.getElementPresent(By.xpath("//span[.='Task Results']"));
		try {
			js.executeScript("arguments[0].scrollIntoView(true);", taskResult);
			js.executeScript("arguments[0].click();", taskResult);
		} catch (StaleElementReferenceException e) {
			js.executeScript("arguments[0].scrollIntoView(true);", taskResult);
			js.executeScript("arguments[0].click();", taskResult);
		}
	   
	
		Thread.sleep(3000);
		//UtilsFunctions.functionKeyboardEventNewSize();
		Thread.sleep(5000);
		WebElement taskresultSearchbox=EYUtils.getElementPresent(By.id("resultsSearchField-inputEl"));
		try {
			EYUtils.uiElement(taskresultSearchbox).sendKeys(accountAggregation);
			Thread.sleep(3000);
			EYUtils.uiElement(taskresultSearchbox).sendKeys(Keys.ENTER);
		} catch (StaleElementReferenceException e) {
			taskresultSearchbox=EYUtils.getElementPresent(By.id("resultsSearchField-inputEl"));
			EYUtils.uiElement(taskresultSearchbox).sendKeys(accountAggregation);
			Thread.sleep(3000);
			EYUtils.uiElement(taskresultSearchbox).sendKeys(Keys.ENTER);
		}
		
		

	
		TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
				ResourceStrings.IIQ_MANAGE_USER_ACCESS_TASK_ACCOUNT_AGGREGATION_SUCCESSFUL,report, test, driver);
    	Thread.sleep(3000);
			Thread.sleep(10000);
			
			
			
			
			
			//UtilsFunctions.functionKeyboardEventOldSize();
			Thread.sleep(3000);
			
			
			js.executeScript("arguments[0].scrollIntoView(true);", taskTab);
			Thread.sleep(2000);
			js.executeScript("arguments[0].click();", taskTab);
			
			Thread.sleep(3000);
			//UtilsFunctions.functionKeyboardEventNewSize();
			
			
			Thread.sleep(3000);
			WebElement searchBarRIPM = EYUtils.getElementPresent(By.xpath("//input[@id='tasksSearchField-inputEl']"));
			
			
			try {
				EYUtils.uiElement(searchBarRIPM).sendKeys(performIdentityRequestMaintenance);
				Thread.sleep(3000);
		
				
				EYUtils.uiElement(searchBarRIPM).sendKeys(Keys.ENTER);
				Thread.sleep(3000);
			} catch (StaleElementReferenceException e) {
				searchBarRIPM = EYUtils.getElementPresent(By.xpath("//input[@id='tasksSearchField-inputEl']"));
				Thread.sleep(3000);
				EYUtils.uiElement(searchBarRIPM).sendKeys(performIdentityRequestMaintenance);
				Thread.sleep(3000);
				
				EYUtils.uiElement(searchBarRIPM).sendKeys(Keys.ENTER);
				Thread.sleep(3000);
			}
			
			
			
			TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
					ResourceStrings.IIQ_MANAGE_USER_ACCESS_TASK_RUN_PERFORM_IDENTITY_REQUEST_MAINTENANCE,report, test, driver);
			
			
          	WebElement SearchBarRIPMelement = EYUtils.getElementPresent(By.xpath("(//div[text()='Perform Identity Request Maintenance'])[1]"));
			Thread.sleep(3000);
			js.executeScript("arguments[0].scrollIntoView(true);", SearchBarRIPMelement);
			Thread.sleep(2000);
			
			try {
				js.executeScript("arguments[0].click();", SearchBarRIPMelement);
			} catch (StaleElementReferenceException e) {
				SearchBarRIPMelement = EYUtils.getElementPresent(By.xpath("(//div[text()='Perform Identity Request Maintenance'])[1]"));
				Thread.sleep(2000);
				js.executeScript("arguments[0].click();", SearchBarRIPMelement);
			}
			
			
		
			Thread.sleep(10000);
			
			
			
			
			
			
			WebElement saveNExecuteButton = EYUtils.getElementPresent(By.xpath("//input[@id='editForm:validateBeforeExecuteButton']"));
			Thread.sleep(3000);
			js.executeScript("arguments[0].scrollIntoView(true);", saveNExecuteButton);
			try {
					js.executeScript("arguments[0].click();", saveNExecuteButton);
			
      			Thread.sleep(2000);
			
			}
			catch (StaleElementReferenceException e) {
				saveNExecuteButton = EYUtils.getElementPresent(By.xpath("//input[@id='editForm:validateBeforeExecuteButton']"));
				js.executeScript("arguments[0].click();", saveNExecuteButton);
				
				

			
			}
			
			Thread.sleep(2000);
		  	WebElement buttonOK = EYUtils.getElementPresent(By.xpath("//span[text()='OK']"));
					
					js.executeScript("arguments[0].scrollIntoView(true);", buttonOK);
			
			try {
				js.executeScript("arguments[0].click();", buttonOK);
			} catch (StaleElementReferenceException e) {
				buttonOK = EYUtils.getElementPresent(By.xpath("//span[text()='OK']"));
				Thread.sleep(3000);
				js.executeScript("arguments[0].scrollIntoView(true);", buttonOK);
			}
			
			
			
			Thread.sleep(3000);
			
	
			// Click On Okbtn
			System.out.println("PIRM end");
			System.out.println("call to taskResults method ");
			Thread.sleep(3000);
			
			//UtilsFunctions.functionKeyboardEventOldSize();

			Thread.sleep(5000);
		  	WebElement ResultTask = EYUtils.getElementPresent(By.xpath("//span[.='Task Results']"));
			Thread.sleep(3000);
			js.executeScript("arguments[0].scrollIntoView(true);", ResultTask);
			
			
			
			
			
			try {
				js.executeScript("arguments[0].click();", ResultTask);
			} catch (StaleElementReferenceException e) {
				ResultTask = EYUtils.getElementPresent(By.xpath("//span[.='Task Results']"));
				Thread.sleep(3000);
				js.executeScript("arguments[0].click();", ResultTask);
				Thread.sleep(3000);
			}
			
			Thread.sleep(3000);
			//UtilsFunctions.functionKeyboardEventNewSize();

			
			
			//gototaskResults.click();
			
			Thread.sleep(5000);
		  	WebElement resultTaskTextbox= EYUtils.getElementPresent(By.id("resultsSearchField-inputEl"));
			Thread.sleep(3000);
			js.executeScript("arguments[0].scrollIntoView(true);", resultTaskTextbox);
			
			
			
			try {
				
				Thread.sleep(3000);
				EYUtils.uiElement(resultTaskTextbox).sendKeys(performIdentityRequestMaintenance);
				Thread.sleep(3000);
				EYUtils.uiElement(resultTaskTextbox).sendKeys(Keys.ENTER);
			} catch (StaleElementReferenceException e) {
				resultTaskTextbox= EYUtils.getElementPresent(By.id("resultsSearchField-inputEl"));
				Thread.sleep(3000);
				js.executeScript("arguments[0].scrollIntoView(true);", resultTaskTextbox);
				EYUtils.uiElement(resultTaskTextbox).sendKeys(performIdentityRequestMaintenance);
				Thread.sleep(3000);
				EYUtils.uiElement(resultTaskTextbox).sendKeys(Keys.ENTER);
			}
			
			
			TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
					ResourceStrings.IIQ_MANAGE_USER_ACCESS_TASK_PERFORM_IDENTITY_REQUEST_MAINTENANCE_SUCCESSFUL,report, test, driver);
			
					}
		catch (NoSuchElementException e) {
			
		}

		catch (StaleElementReferenceException e) {
		}

		catch (ElementNotInteractableException e) {
		}
		catch (Exception e) {
		}
			Thread.sleep(40000);
			report.endTest(test);
	}


   public void taskRun_PerformMaintenance(String performIdentityRequestMaintenance) throws InterruptedException, AWTException, ElementNotVisibleException {
		test=report.startTest(ResourceStrings.IIQ_MANAGE_USER_ACCESS_TASK);
		report.flush();
		System.out.println("performIdentityRequestMaintenance : " + performIdentityRequestMaintenance);
		JavascriptExecutor js=(JavascriptExecutor)driver;
		try {
		
		Thread.sleep(3000);
		WebElement setupAnchor=EYUtils.getElementPresent(By.xpath("//img[@alt='SailPoint Logo']//following::a[22]"));
		try {
			js.executeScript("arguments[0].scrollIntoView(true);", setupAnchor);
			EYUtils.uiElement(setupAnchor).click();
		} catch (StaleElementReferenceException e) {
			setupAnchor=EYUtils.getElementPresent(By.xpath("//img[@alt='SailPoint Logo']//following::a[22]"));
			setupAnchor.click();
			//EYUtils.uiElement(setupAnchor).click();
		}

		
		Thread.sleep(3000);
		WebElement taskAnchor=EYUtils.getElementPresent(By.xpath("//img[@alt='SailPoint Logo']//following::a[27]"));
		try {
			js.executeScript("arguments[0].scrollIntoView(true);", taskAnchor);
			EYUtils.uiElement(taskAnchor).click();
		} catch (Exception e) {
			taskAnchor=EYUtils.getElementPresent(By.xpath("//img[@alt='SailPoint Logo']//following::a[27]"));
			EYUtils.uiElement(taskAnchor).click();
		}
		
		

		Thread.sleep(4000);
		

			
			WebElement searchBarRIPM = EYUtils.getElementPresent(By.xpath("//input[@id='tasksSearchField-inputEl']"));
			
			
			try {
				EYUtils.uiElement(searchBarRIPM).sendKeys(performIdentityRequestMaintenance);
				Thread.sleep(3000);
		
				
				EYUtils.uiElement(searchBarRIPM).sendKeys(Keys.ENTER);
				Thread.sleep(3000);
			} catch (StaleElementReferenceException e) {
				searchBarRIPM = EYUtils.getElementPresent(By.xpath("//input[@id='tasksSearchField-inputEl']"));
				Thread.sleep(3000);
				EYUtils.uiElement(searchBarRIPM).sendKeys(performIdentityRequestMaintenance);
				Thread.sleep(3000);
				
				EYUtils.uiElement(searchBarRIPM).sendKeys(Keys.ENTER);
				Thread.sleep(3000);
			}
			
			TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
					ResourceStrings.IIQ_MANAGE_USER_ACCESS_TASK_RUN_PERFORM_IDENTITY_REQUEST_MAINTENANCE,report, test, driver);
			
			
	
	
			
			
          	WebElement SearchBarRIPMelement = EYUtils.getElementPresent(By.xpath("(//div[text()='Perform Identity Request Maintenance'])[1]"));
			Thread.sleep(3000);
			js.executeScript("arguments[0].scrollIntoView(true);", SearchBarRIPMelement);
			Thread.sleep(2000);
			
			try {
				js.executeScript("arguments[0].click();", SearchBarRIPMelement);
			} catch (StaleElementReferenceException e) {
				SearchBarRIPMelement = EYUtils.getElementPresent(By.xpath("(//div[text()='Perform Identity Request Maintenance'])[1]"));
				Thread.sleep(2000);
				js.executeScript("arguments[0].click();", SearchBarRIPMelement);
			}
			
			
		
			Thread.sleep(10000);
			
			
			
			
			
			
			WebElement saveNExecuteButton = EYUtils.getElementPresent(By.xpath("//input[@id='editForm:validateBeforeExecuteButton']"));
			Thread.sleep(3000);
			js.executeScript("arguments[0].scrollIntoView(true);", saveNExecuteButton);
			try {
					js.executeScript("arguments[0].click();", saveNExecuteButton);
			
      			Thread.sleep(2000);
			
			}
			catch (StaleElementReferenceException e) {
				saveNExecuteButton = EYUtils.getElementPresent(By.xpath("//input[@id='editForm:validateBeforeExecuteButton']"));
				js.executeScript("arguments[0].click();", saveNExecuteButton);
				
				

			
			}
			
			Thread.sleep(2000);
		  	WebElement buttonOK = EYUtils.getElementPresent(By.xpath("//span[text()='OK']"));
					
					js.executeScript("arguments[0].scrollIntoView(true);", buttonOK);
			
			try {
				js.executeScript("arguments[0].click();", buttonOK);
			} catch (StaleElementReferenceException e) {
				buttonOK = EYUtils.getElementPresent(By.xpath("//span[text()='OK']"));
				Thread.sleep(3000);
				js.executeScript("arguments[0].scrollIntoView(true);", buttonOK);
			}
			
			
			
			Thread.sleep(3000);
			
	
			// Click On Okbtn
			System.out.println("PIRM end");
			System.out.println("call to taskResults method ");
			Thread.sleep(3000);
			
			//UtilsFunctions.functionKeyboardEventOldSize();

			Thread.sleep(5000);
		  	WebElement ResultTask = EYUtils.getElementPresent(By.xpath("//span[.='Task Results']"));
			Thread.sleep(3000);
			js.executeScript("arguments[0].scrollIntoView(true);", ResultTask);
			
			
			
			
			
			try {
				js.executeScript("arguments[0].click();", ResultTask);
			} catch (StaleElementReferenceException e) {
				ResultTask = EYUtils.getElementPresent(By.xpath("//span[.='Task Results']"));
				Thread.sleep(3000);
				js.executeScript("arguments[0].click();", ResultTask);
				Thread.sleep(3000);
			}
			
			Thread.sleep(3000);
			//UtilsFunctions.functionKeyboardEventNewSize();

			
			
			//gototaskResults.click();
			
			Thread.sleep(5000);
		  	WebElement resultTaskTextbox= EYUtils.getElementPresent(By.id("resultsSearchField-inputEl"));
			Thread.sleep(3000);
			js.executeScript("arguments[0].scrollIntoView(true);", resultTaskTextbox);
			
			
			
			try {
				
				Thread.sleep(3000);
				EYUtils.uiElement(resultTaskTextbox).sendKeys(performIdentityRequestMaintenance);
				Thread.sleep(3000);
				EYUtils.uiElement(resultTaskTextbox).sendKeys(Keys.ENTER);
			} catch (StaleElementReferenceException e) {
				resultTaskTextbox= EYUtils.getElementPresent(By.id("resultsSearchField-inputEl"));
				Thread.sleep(3000);
				js.executeScript("arguments[0].scrollIntoView(true);", resultTaskTextbox);
				EYUtils.uiElement(resultTaskTextbox).sendKeys(performIdentityRequestMaintenance);
				Thread.sleep(3000);
				EYUtils.uiElement(resultTaskTextbox).sendKeys(Keys.ENTER);
			}
			
			TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG, LogStatus.INFO,
					ResourceStrings.IIQ_MANAGE_USER_ACCESS_TASK_PERFORM_IDENTITY_REQUEST_MAINTENANCE_SUCCESSFUL,report, test, driver);
			
			
	
	
	

	
		}
		catch (NoSuchElementException e) {
			
		}

		catch (StaleElementReferenceException e) {
		}

		catch (ElementNotInteractableException e) {
		}
		catch (Exception e) {
		}
			report.endTest(test);
	}



}
