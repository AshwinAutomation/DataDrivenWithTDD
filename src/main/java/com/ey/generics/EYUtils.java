package com.ey.generics;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.ElementNotSelectableException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.testbase.TestBase;


public class EYUtils extends TestBase{
	

		public static long PAGE_LOAD_TIMEOUTS = 30;
		public static long IMPLICIT_TIMEOUTS = 50;
		public static long WEBDRIVER_TIMEOUTS = 300;
		public static long FLUENT_TIMEOUTS = 30;
		public static long FLUENT_POLLINGTIMEOUTS = 3;
		public static WebDriverWait wait = null;
		
		public static String flash;



		public static List<String> readFileInList(String strFileName)
		{
			List<String> lines = Collections.emptyList();
			try {
				lines = Files.readAllLines(Paths.get(strFileName), StandardCharsets.UTF_8);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			return lines;
		}

		/*public static void printCurrentTime(String strValue) 
		{
			LocalDateTime now = LocalDateTime.now();
		}

		public static void checkPageIsReady(WebDriver driver) 
		{  
			JavascriptExecutor js = (JavascriptExecutor)driver;

			//Initially bellow given if condition will check ready state of page.
			if (js.executeScript("return document.readyState").toString().equals("complete"))
			{ 
				return; 
			} 

			//This loop will rotate for 25 times to check If page Is ready after every 1 second.
			//You can replace your value with 25 If you wants to Increase or decrease wait time.
			for (int i=0; i<25; i++)
			{ 
				try 
				{
					Thread.sleep(1000);
				}
				catch (InterruptedException e) 
				{
					System.out.print("checkPageIsReady()::Exception occured in for loop");
				} 

				//To check page ready state.
				if (js.executeScript("return document.readyState").toString().equals("complete"))
				{ 
					break; 
				}   
			}
		}
*/
	

		public static boolean isWebElementDisplayed(WebElement element) 
		{
			WebElement webElement = null;
			boolean flag = true;
			boolean isLoadingCompleted = false;
			int count = 1;
			while (flag) 
			{
				if (count < 15) 
				{
					try 
					{
						if (element.isDisplayed()) 
						{
							flag = false;
							isLoadingCompleted = true;
						}
						else
						{
							count = count + 1;
							try 
							{
								Thread.sleep(1000);
							} 
							catch (InterruptedException e1) 
							{
							}
							flag = true;
						}
					} 
					catch (Exception e) 
					{
						count = count + 1;
						try 
						{
							Thread.sleep(1000);
						} 
						catch (InterruptedException e1) 
						{
						}
						flag = true;
						if (webElement == null) 
						{
							isLoadingCompleted = false;
							System.out.println("webelement is null");
							flag = false;
						}
					}
				}
			}
			return isLoadingCompleted;
		}
		public static boolean isWebElementDisplayedWithRefresh(String string, WebDriver driver) 
		{
			WebElement webElement = null;
			boolean flag = true;
			boolean isLoadingCompleted = false;
			int count = 1;
			while (flag) 
			{
				if (count < 5) 
				{
					try 
					{
						webElement = driver.findElement(By.xpath(string));
						if (webElement.isDisplayed()) 
						{
							flag = false;
							isLoadingCompleted = true;
						}
						else
						{
							driver.navigate().refresh();
							count = count + 1;
							try 
							{
								Thread.sleep(1000);
							} 
							catch (InterruptedException e1) 
							{
							}
							flag = true;
						}
					} 
					catch (Exception e) 
					{
						driver.navigate().refresh();
						count = count + 1;
						try 
						{
							Thread.sleep(1000);
						} 
						catch (InterruptedException e1) 
						{
						}
						flag = true;
					}
				}
				flag = false;
			}
			return isLoadingCompleted;
		}

		public static boolean isWebElementDissappeared(String string, WebDriver driver) 
		{
			WebElement webElement = null;
			boolean flag = true;
			boolean isLoadingCompleted = false;
			while (flag) 
			{	
				try 
				{
					Thread.sleep(2000);
					webElement = driver.findElement(By.xpath(string));
					if (webElement.isDisplayed()) 
					{
						System.out.println("Inside IF isWebElementDissappeared");
						flag = true;
					}
					else
					{
						System.out.println("Inside ELSE isWebElementDissappeared");
						flag = false;
						isLoadingCompleted = true;
					}
				} 
				catch (Exception e) 
				{
					System.out.println("Inside Catch isWebElementDissappeared");
					flag = false;
					isLoadingCompleted = true;
				}
			}
			return isLoadingCompleted;
		}

		
		static int counter = 0;
		
		
		
		
		
		public static WebElement refreshObject(By locator) 
		{
			try {
				counter=counter+1;
				return driver.findElement(locator);
			} catch (StaleElementReferenceException e) {
            return refreshObject(locator);
			}
			
		
		}
		

		


		 
		 
		 
		 public static WebElement getElement(WebDriver driver, WebElement element, By locator) throws InterruptedException
			{
				{
				try {
					Thread.sleep(3000);
					// Check visibility. If reference is not stale, it will return the same referenced. Otherwise it will go to catch.
					element.isDisplayed();
					System.out.println("getElement try");
					return element;
					
					// Relocate element in catch and return
				}catch(StaleElementReferenceException e)
				{
					
					WebDriverWait wait = new WebDriverWait(driver, 30);
					//wait.until(ExpectedConditions.visibilityOf(webElement));
					wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(element));
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("arguments[0].scrollIntoView(true);",element);

		         wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(element)));
					Thread.sleep(3000);
					System.out.println("getElement catch");
					return driver.findElement(locator);
					
					
				}catch(Exception e)
				{
					e.printStackTrace();
					return null;
				}}
				
			}
		    
    
    
    
	public static WebElement uiElement(WebElement webElement) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			//wait.until(ExpectedConditions.visibilityOf(webElement));
			wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(webElement));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true);",webElement);
		} catch (Exception e) {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			//wait.until(ExpectedConditions.visibilityOf(webElement));
			wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(webElement));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true);",webElement);

			System.out.println("getElement catch");
			//return driver.findElement(By.xpath(webElement));
			
			
		
		}
		return webElement;
	} 
	

	
	public static WebElement getElementPresent(By locator) {
		waitForElementPresent(locator);

		WebElement element = null;
		try {
			element = driver.findElement(locator);
			if (flash.equalsIgnoreCase("yes")) {
				UtilsFunctions.flash(element, driver);
			}
		} catch (Exception e) {
			//System.out.println("Some exception occurred while creating webelement " + locator);
		}
		return element;
	}

	
	public static void waitForElementPresent(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	
    
    
	 static void runTimeInfo(String messageType, String message) throws InterruptedException {
		 JavascriptExecutor js = (JavascriptExecutor) driver;
			// Check for jQuery on the page, add it if need be
			js.executeScript("if (!window.jQuery) {"
					+ "var jquery = document.createElement('script'); jquery.type = 'text/javascript';"
					+ "jquery.src = 'https://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js';"
					+ "document.getElementsByTagName('head')[0].appendChild(jquery);" + "}");
			Thread.sleep(5000);

			// Use jQuery to add jquery-growl to the page
			js.executeScript("$.getScript('https://the-internet.herokuapp.com/js/vendor/jquery.growl.js')");

			// Use jQuery to add jquery-growl styles to the page
			js.executeScript("$('head').append('<link rel=\"stylesheet\" "
					+ "href=\"https://the-internet.herokuapp.com/css/jquery.growl.css\" " + "type=\"text/css\" />');");
			Thread.sleep(5000);

			// jquery-growl w/ no frills
			js.executeScript("$.growl({ title: 'GET', message: '/' });");
	//'"+color+"'"
			if (messageType.equals("error")) {
				js.executeScript("$.growl.error({ title: 'ERROR', message: '"+message+"' });");
			}else if(messageType.equals("info")){
				js.executeScript("$.growl.notice({ title: 'Notice', message: 'your notice message goes here' });");
			}else if(messageType.equals("warning")){
				js.executeScript("$.growl.warning({ title: 'Warning!', message: 'your warning message goes here' });");
			}else
				System.out.println("no error message");
			// jquery-growl w/ colorized output
//			js.executeScript("$.growl.error({ title: 'ERROR', message: 'your error message goes here' });");
//			js.executeScript("$.growl.notice({ title: 'Notice', message: 'your notice message goes here' });");
//			js.executeScript("$.growl.warning({ title: 'Warning!', message: 'your warning message goes here' });");
			Thread.sleep(5000);
		}
	 
	 
	 
	 /**
	  * This is implicit wait
	  * @param timeout
	  * @param unit
	  */
	 public static void setImplicitWait(long timeout,TimeUnit unit) {
		driver.manage().timeouts().implicitlyWait(timeout, unit);
	}

		/**
		 * This will help us to get webdriver object
		 * @param element
		 * @param timeOutInSeconds
		 * @param pollingEveryInMiliSec
		 */
	 public  static WebDriverWait  getWait(long timeOutInSeconds,int pollingEveryInMiliSec) {
		WebDriverWait wait=new WebDriverWait(driver, timeOutInSeconds);
		wait.pollingEvery(pollingEveryInMiliSec, TimeUnit.SECONDS);
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(StaleElementReferenceException.class);
		wait.ignoring(ElementNotInteractableException.class);
		wait.ignoring(ElementNotVisibleException.class);
		wait.ignoring(ElementNotSelectableException.class);
		wait.ignoring(NoSuchFrameException.class);
		
		return wait;
	
		
	}
	/**
	 * This will help us to element is visible
	 * @param element
	 * @param timeOutInSeconds
	 * @param pollingEveryInMiliSec
	 */
	 public static void waitForElementVisibleWithPollingTime(WebElement element,int timeOutInSeconds,int pollingEveryInMiliSec) {
		logger.info("waiting for :" + element.toString() + "for :" +timeOutInSeconds+" seconds") ;
	 WebDriverWait wait=getWait(timeOutInSeconds, pollingEveryInMiliSec);
	 wait.until(ExpectedConditions.visibilityOf(element));
	 logger.info("element is visible now") ; 
	}
	 
		/**
		 * This will help us to element to be clickable
		 * @param element
		 * @param timeOutInSeconds
		 * @param pollingEveryInMiliSec
		 */
		
	 public static void waitForElementClickable(WebElement element,int timeOutInSeconds,int pollingEveryInMiliSec) {
		logger.info("waiting for :" + element.toString() + "for :" +timeOutInSeconds+" seconds") ;
	 WebDriverWait wait=getWait(timeOutInSeconds, pollingEveryInMiliSec);
	 wait.until(ExpectedConditions.elementToBeClickable(element));
	 logger.info("element is clickable now") ; 
	}
	 
		/**
		 * This will help us to get invisbleWebelement
		 * @param element
		 * @param timeOutInSeconds
		 * @param pollingEveryInMiliSec
		 */
	 
	 
	 public static boolean waitForElementNotPresent(WebElement element,long timeOutInSeconds,int pollingEveryInMiliSec) {
		logger.info("waiting for :" + element.toString() + "for :" +timeOutInSeconds+" seconds") ;
	 WebDriverWait wait=getWait(timeOutInSeconds, pollingEveryInMiliSec);
	  boolean status= wait.until(ExpectedConditions.invisibilityOf(element));
	 logger.info("element is present now") ; 
	 return status;
	}
	 
	 
	 
		/**
		 * This will help us to get wait for frame
		 * @param element
		 * @param timeOutInSeconds
		 * @param pollingEveryInMiliSec
		 */
	 
	 public static void waitForElementNotPresentInsideFrame(WebElement element,long timeOutInSeconds,int pollingEveryInMiliSec) {
		logger.info("waiting for :" + element.toString() + "for :" +timeOutInSeconds+" seconds") ;
	 WebDriverWait wait=getWait(timeOutInSeconds, pollingEveryInMiliSec);
	   wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
	 logger.info("frame is available now") ; 
	}
	 
	 
	 
	 
	 public static Wait<WebDriver> getFluentWait(long timeOutInSeconds,int pollingEveryInMiliSec) {
		Wait<WebDriver> fwait=new FluentWait<WebDriver>(driver)
				.withTimeout(timeOutInSeconds, TimeUnit.SECONDS)
				.pollingEvery(pollingEveryInMiliSec, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class)
				.ignoring(ElementNotInteractableException.class)
				.ignoring(ElementNotVisibleException.class);
      
		return fwait;
		
	}
	 
	 public WebElement waitForElement(WebElement element,long timeOutInSeconds,int pollingEveryInMiliSec) {
		 Wait<WebDriver>  fwait=getFluentWait(timeOutInSeconds, pollingEveryInMiliSec);
		 fwait.until(ExpectedConditions.visibilityOf(element));
		return element;
	}

	 
	}
    

