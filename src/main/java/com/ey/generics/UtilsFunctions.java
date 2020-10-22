package com.ey.generics;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.testbase.TestBase;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;


public class UtilsFunctions extends TestBase {
	public static WebDriverWait wait;

	public static void functionClick(WebElement element) {
		try {
			Thread.sleep(2000);
			element.click();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	    catch (StaleElementReferenceException e) {
	    	e.printStackTrace();
	    }
	    catch (NoSuchElementException e) {
	    	e.printStackTrace();
	    }
		
		catch (Exception e) {
	    	e.printStackTrace();
	    }
		
	}

	public static void functionTextBox(WebElement element, String value) throws InterruptedException {
		if (element != null) {
			try {
			element.sendKeys(value);
			Thread.sleep(1000);
		}
			catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		    catch (StaleElementReferenceException e) {
		    	e.printStackTrace();
		    }
		    catch (NoSuchElementException e) {
		    	e.printStackTrace();
		    }
			catch (Exception e) {
		    	e.printStackTrace();
		    }
	}}

	public static void functionTextBoxStringToDouble(WebElement element, String value) {
		if (element != null) {

			element.clear();

			element.sendKeys(value);
		}
	}

	public static void functionFrame(WebElement element) {
		driver.switchTo().frame(element);
	}

	public static void functionDropdown(String attributeValue, WebElement element) {

		Select select = new Select(element);
		select.selectByVisibleText(attributeValue);
	}

	public static void functionDropdownForMultiple(WebElement element, String attributeValue) {

		Select select = new Select(element);
		List<WebElement> allValue = select.getOptions();
		System.out.println(allValue.size());
		for (int i = 0; i < allValue.size(); i++) {
			WebElement numericValue = allValue.get(i);
			String textValue = numericValue.getText();
			System.out.println("textValue :" + textValue);
			System.out.println("attributeValue : " + attributeValue);
			if (textValue.contains(attributeValue)) {
				numericValue.click();
				break;

			}
		}
	}

	public static void functionDatePicker(String date, WebElement elementforYear, WebElement elementforMonth) {
		// here date should come like 16-05-1991
		String  [] dateArr = date.split("-");// after split date should be{16,05,1991}
		String day = dateArr[0];// {for date ===like 1 to 31 days}
		String month = dateArr[1]; // {for month ===like jan to dec days}
		String year = dateArr[2]; // for year
		functionDropdown(year, elementforYear);
		functionDropdown(month, elementforMonth);
		String beforeXpath = "//*[@id='ui-datepicker-div']/table/tbody/tr[";
		String afterXpath = "]/td[";
		boolean flag = false;// it is used to break outer loop
		final int TOTAL_DAYS_IN_A_WEEK = 7;// it should be constant

		// loop for row,column
		// rowNum = 2,because date start from row2
		// rowNum <=7,because of total 7 rows
		// colNum = 1,because of date start from 1st column
		for (int rowNum = 1; rowNum <= 5; rowNum++) {

			for (int colNum = 1; colNum <= TOTAL_DAYS_IN_A_WEEK; colNum++) {

				String dayValue = driver.findElement(By.xpath(beforeXpath + rowNum + afterXpath + colNum + "]"))
						.getText();
				System.out.println("dayValue : " + dayValue);
				System.out.println("day" + day);
				if (dayValue.equals(day)) {
					System.out.println("inside if");
					driver.findElement(By.xpath(beforeXpath + rowNum + afterXpath + colNum + "]")).click();
					flag = true;// condition terminate
					System.out.println("after if");
					break;// it will stop after get exact date
				}
				if (flag) {
					break;
				}

			}
		}

	}

	public static void functionWindowHandle() {

		Set<String> allwindow = driver.getWindowHandles();
		System.out.println("size of window : " + allwindow.size() + " " + "value of window : " + allwindow);
		ArrayList<String> tabs = new ArrayList<String>(allwindow);
		tabs.get(0);// parent
		tabs.get(1);// child1
		driver.switchTo().window(tabs.get(1));
		String childWindowTitle = driver.getTitle();
		System.out.println("childWindowTitle : " + childWindowTitle);
		driver.switchTo().window(tabs.get(0));
		String parentWindowTitle = driver.getTitle();
		System.out.println("ParentWindow Title : " + parentWindowTitle);

	}

	public static void alertHandle() {
	}

	public static void nosuchElementexceptionHandle() {
	}

	public static void stealRefernceElementexceptionHandle() {
	}

	public static void scrolltoElement() {
	}

	public static void clickInvisibleElement(WebElement element) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(2000);
		element.click();
		/*
		 * wait = new WebDriverWait(driver, time); boolean
		 * invisible=wait.until(ExpectedConditions.invisibilityOfElementLocated((By)
		 * element)); if (invisible) { element.click();
		 * 
		 * }
		 */
	}

	public static void explicitWait(WebElement element, int time) {
		wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.elementToBeClickable(element));

	}

	public static void clickToParticularElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		element.click();
	}

	public static void scrollToParticularEntitilement(String entitlement, WebElement element) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", entitlement);

		js.executeScript("arguments[1].scrollIntoView(true);", element);

	}
	
	
	
	/*public static void waitForPageLoaded(WebDriver driver) {
		ExpectedCondition expectation = new ExpectedCondition(){
		@Override
		public Boolean apply (WebDriver driver) {
		return ((JavascriptExecutor) driver).executeScript(
		"return document.readyState").equals("complete");
		}
		};
		Wait<WebDriver> wait = new WebDriverWait(driver, 10);
		try {
		wait.until(expectation);
		} catch (Throwable error) {
		logger.error("Timeout waiting for Page Load Request to complete.");
		}
		}
	
*/	
	
	/*public boolean waitForJStoLoad() {

	    WebDriverWait waits = new WebDriverWait(driver, 30);

	    // wait for jQuery to load\\
	    wait.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete")); 
	    

	    ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {

	      @Override

	      public Boolean apply(WebDriver driver) {

	        try {

	          return ((Long)executeJavaScript("return jQuery.active") == 0);

	        }

	        catch (Exception e) {

	          return true;

	        }

	      }

	    };

	    // wait for Javascript to load

	    ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {

	      @Override

	      public Boolean apply(WebDriver driver) {

	    	  
	    	  
	    	  
	        return executeJavaScript("return document.readyState")

	            .toString().equals("complete");

	      }

	    };

	  return wait.until(jQueryLoad) && wait.until(jsLoad);

	}
*/


	public static void functionKeyboardEventOldSize() throws AWTException {
		Robot robot = new Robot();

		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_MINUS);
		robot.keyRelease(KeyEvent.VK_MINUS);
		robot.keyRelease(KeyEvent.VK_CONTROL);

	
	}
	
	

	public static void functionKeyboardEventNewSize() throws AWTException {
		Robot robot = new Robot();

		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_PLUS);
		robot.keyRelease(KeyEvent.VK_MINUS);
		robot.keyRelease(KeyEvent.VK_CONTROL);

	
	}
	
	
	
	
	public void staleElementReferenceHandle(String elementID){
		int count = 0;
		boolean clicked = false;
		while (count < 4 || !clicked){
		    try {
		       WebElement yourSlipperyElement= driver.findElement(By.xpath(elementID));
		       yourSlipperyElement.click(); 
		       clicked = true;
		     } catch (StaleElementReferenceException e){
		       e.toString();
		       System.out.println("Trying to recover from a stale element :" + e.getMessage());
		       count = count+1;
		     }     
		}}
	
	
	
	
	public static void staleElementReference(String elements){
		int count = 0;
		boolean clicked = false;
		while (count < 4 || !clicked){
		    try {
		       WebElement yourSlipperyElement= driver.findElement(By.id(elements));
		       yourSlipperyElement.click(); 
		       clicked = true;
		     } catch (StaleElementReferenceException e){
		       e.toString();
		       System.out.println("Trying to recover from a stale element :" + e.getMessage());
		       count = count+1;
		     }     
		}}

	
	
	
	
	
	int defaultTime = 55;

 public	boolean openForm(String myXpath) throws Exception {
	    int count = 0;
	    boolean clicked = false;
	    while (count < 4 || !clicked) {
	        try {
	            WebElement element = getWebElClickable(myXpath,defaultTime);
	            Actions act=new Actions(driver);
	            act.doubleClick(element).build().perform();
	            clicked = true;
	            System.out.println("Element have been clicked!");
	            break;
	        } catch (StaleElementReferenceException sere) {
	            sere.toString();
	            System.out.println("Trying to recover from: "+sere.getMessage());
	            count=count+1;
	        }
	    }
		return clicked;
	}
	    
	
	    public WebElement getWebElClickable(String xpath, int waitSeconds) {
	        wait = new WebDriverWait(driver, waitSeconds);
	        return wait.ignoring(StaleElementReferenceException.class).until(
	                ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(By.xpath(xpath))));
	    }
	
	


	
	
	
	
	
	
	public static void PageLoadedVerification() {
		for (int i = 0; i < 30; i++) {
			try {
				Thread.sleep(1000);
			}
		
				catch (Exception e) {
			    	e.printStackTrace();
			    }
			JavascriptExecutor js=(JavascriptExecutor)driver;
			if (js.executeScript("return document.readyState").toString().equals("complete")) {
				System.out.println("Page is loaded completely");
				break;
			} else {
				System.out.println("Page is  not loaded completely");
			}
			
		}
	}
	
	
	//java script method
	
	
	public static void flash(WebElement element, WebDriver driver) {
	//	JavascriptExecutor js =(JavascriptExecutor) driver;
		String bgcolor = element.getCssValue("backgroundColor");
		for (int i = 0; i < 20; i++) {
			changeColor("rgb(0,200,0)", element, driver);// 1
			changeColor(bgcolor, element, driver);// 2
		}
	}

	public static void changeColor(String color, WebElement element, WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);

		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
		}
	}

	public static void drawBorder(WebElement element, WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].style.border='3px solid red'", element);
	}

	public static void generateAlert(WebDriver driver, String message) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("alert('" + message + "')");
	}

	public static void clickElementByJS(WebElement element, WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].click();", element);

	}

	public static void refreshBrowserByJS(WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("history.go(0)");
	}

	public static String getTitleByJS(WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		String title = js.executeScript("return document.title;").toString();
		return title;
	}

	public static String getPageInnerText(WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		String pageText = js.executeScript("return document.documentElement.innerText;").toString();
		return pageText;
	}

	public static void scrollPageDown(WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}

	public static void scrollIntoView(WebElement element, WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public static String getBrowserInfo(WebDriver driver){
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		String uAgent = js.executeScript("return navigator.userAgent;").toString();     
		return uAgent;
	}
	
	public static void sendKeysUsingJSWithID(WebDriver driver, String id, String value){
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("document.getElementById('" + id + "').value='"+value+"'");
	}
	
	public static void sendKeysUsingJSWithClassName(WebDriver driver, String className, String value){
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("document.getElementById('" + className + "').value='"+value+"'");
	}
	
	public static void sendKeysUsingJSWithName(WebDriver driver, String name, String value){
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("document.getElementById('" + name + "').value='"+value+"'");
	}
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
}
