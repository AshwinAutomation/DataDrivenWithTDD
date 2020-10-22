package com.ey.iiqcommons.elementpage;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import com.ey.generics.UtilsFunctions;
import com.qa.testbase.TestBase;

public class PerformMaintenance extends TestBase{
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
	public void runPerformanceMaintenance() throws Throwable {
		System.out.println();
		String taskName = prop.getProperty("runTask_PerformMaintenance");
		Thread.sleep(10000);
		System.out.println(" TaskName : " + taskName);
		Thread.sleep(20000);
		if (taskName.equalsIgnoreCase("Perform Maintenance")) {
			WebElement element = driver.findElement(By.partialLinkText("setupLink"));
			try {

				try {
					element.click();
					// UtilsFunctions.functionClick(setupLink);
					System.out.println("setupLink click");
					Thread.sleep(3000);
				} catch (StaleElementReferenceException exceptionMessage) {
					element = driver.findElement(By.partialLinkText("setupLink"));
					element.click();
					System.out.println(exceptionMessage.getMessage());
					Thread.sleep(3000);

				}

				// Click On SetUp
				Thread.sleep(2000);
				// Click on Tasks
				tasksLink.click();
				Thread.sleep(2000);

				// Search Bar

				UtilsFunctions.functionTextBox(searchBarPerform, taskName);
				// SearchBtnClick
				UtilsFunctions.functionClick(searchButton);
				Thread.sleep(3000);

				// Click On Perform Maintenance
				UtilsFunctions.functionClick(performTextClick);
				Thread.sleep(4000);
				// Click on saveNExecute
				JavascriptExecutor executor = (JavascriptExecutor) driver;

				executor.executeScript("arguments[0].scrollIntoView(true);", saveNExecute);
				Thread.sleep(2000);
				UtilsFunctions.functionClick(saveNExecute);

				Thread.sleep(2000);

				// Click On Okbtn
				UtilsFunctions.functionClick(okButton);
				Thread.sleep(2000);

			} catch (Exception e) {
				System.out.println(" Error in performance task");
			}
		}

	}


}
