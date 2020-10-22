package com.qa.testbase;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.maven.surefire.shade.common.org.apache.commons.io.FileUtils;
import org.mortbay.log.Log;
//import org.apache.commons.io.FileUtils;
//import org.ini4j.Ini;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
//import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.excelutils.ExcelUtils;
import com.ey.iiqesourcestring.ResourceStrings;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	public static Properties prop = null;
	public static WebDriver driver = null;
	public static File file = null;
	public static String location = System.getProperty("user.dir");
	public static DesiredCapabilities cap = null;
	public static long implicitWaitTimeout = 20;
	public static ExtentReports report;
	public static ExtentTest test;
	public static String reportName = "";
	public static WebDriverWait wait = null;
	public static final int SCRSHOT_LEVEL_ERROR = 1;
	public static final int SCRSHOT_LEVEL_INFO = 2;
	public static final int SCRSHOT_LEVEL_DEBUG = 3;
	public static final int SCRSHOT_LEVEL_SKIP = 5; // to avoid capturing
													// screenshot

	public static int m_nScrShotCaptureLevel = 1;
	public static String m_strScrShotFilePath = "./ScreenShots/";
	public static String isTestSuccess = "pending";
	public static String absoluteWebPath = "";
	public static int numberOfAttemptsToFindWebElement;
	public static Logger logger = null;
	public static FileInputStream fi = null;
	public static FileOutputStream fo = null;
	public static String excelsheetPath = System.getProperty("user.dir")+"./exceldata/ApplicationOnBoardingJDBC.xlsx";
	public static String xlsheet = "SingleuserMultipleAccess_DENY";
	public static  final  String ENVIRONMENT = "QA";

	static {
		cap = new DesiredCapabilities();
		try {
			file = new File(location + "./Resourse/ManageAccess.properties");
			fi = new FileInputStream(file);
			prop = new Properties();
			prop.load(fi);
			logger = Logger.getLogger("TestBase");
			PropertyConfigurator.configure("Log4j.properties");
			//logger.setLevel(Level.DEBUG);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		report = new ExtentReports(System.getProperty("user.dir") + "/Reports/ApplicationOnBoardingExtentReport.html",
				true);

		// File(System.getProperty("user.dir")+"\\extent-config.xml"));
		// extent.addSystemInfo("Environment","Environment Name")
		String strCaptureScrnShotLevel = prop.getProperty("screenshotLevel");
		if (strCaptureScrnShotLevel != null) {

			m_nScrShotCaptureLevel = Integer.parseInt(strCaptureScrnShotLevel);
		}
	}

	public TestBase() {
		super();

	}

	@BeforeSuite
	public static void browserLaunch() throws IOException {
		test = report.startTest(ResourceStrings.IIQ_LOGIN_TO_ITACCESS);
		report.flush();
	   Log.info("excelsheetPath :" + excelsheetPath);
	   Log.info("xlsheet :" + xlsheet);
		 String browserName = ExcelUtils.getCellData(excelsheetPath, xlsheet, 1, 0).trim();
		 String loginURL = ExcelUtils.getCellData(excelsheetPath, xlsheet, 1, 1).trim();
		 String connectorType = ExcelUtils.getCellData(excelsheetPath, xlsheet, 1, 2).trim();
		 String applicationName = ExcelUtils.getCellData(excelsheetPath, xlsheet, 1, 6).trim();
		 Log.info("loginURL : " + loginURL);
		 test.assignCategory("BrowserDetails : " + browserName);
		 test.assignCategory("Environment : " + ENVIRONMENT);
		 test.assignCategory("Connector type is : " + connectorType);
		 test.assignCategory("applicationName : " + applicationName);
		 test.assignAuthor("AuthorName :" + "====> " + "Ashwin");
		 test.setDescription("Browser launched and URL Passed successfully");

		 
		 Log.info("browserName :" + browserName);
		if (browserName != null) {

			if (browserName.equalsIgnoreCase("chrome")) {

				//System.setProperty("webdriver.chrome.silentOutput","true");
		
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				
			}

			else if (browserName.equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			}

			else if (browserName.equalsIgnoreCase("ie")) {
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
			}
          
			
			
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(400, TimeUnit.SECONDS);
			driver.get(loginURL);
			 Log.info("url passed successfully :");
			TestBase.logMessageToReport(SCRSHOT_LEVEL_DEBUG,LogStatus.PASS,ResourceStrings.IIQ_LOGIN_BROWSER + "===>" + ResourceStrings.IIQ_LOGIN_CONNECTORTYPE + connectorType, report, test, driver);
		      logger.info(ResourceStrings.IIQ_LOGIN_BROWSER);
			
		
		}

	}

	public static String captureScreenshot(String screenshotname) {

		try {

		TakesScreenshot ts = (TakesScreenshot) driver;
			String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			File source = ts.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(source, new File(location + "/ScreenShots/" + screenshotname + dateName + ".png"));

			
			

		} catch (Exception e) {

			Reporter.log("Exception while taking get screenshot" + e.getMessage());
			e.printStackTrace();
		}
		return location;
	}

	public static void logMessageToReport(int nLevel, LogStatus logStatus, String strLogMessage, ExtentReports report,
			ExtentTest test, WebDriver localDriver) {
		try {
			String strCaptureScrnShotLevel = prop.getProperty("screenshotLevel");

			if (strCaptureScrnShotLevel != null) {

				m_nScrShotCaptureLevel = Integer.parseInt(strCaptureScrnShotLevel);
			}
			if (logStatus.equals(LogStatus.FAIL)) {

				isTestSuccess = "fail";
			}
/*
			System.out.println("@@@@@ logMessageToReport()::inside method @@@@@");
			System.out.println("logMessageToReport()::inside method @@@@@ log nLevel: *************** " + nLevel);*/
			if (!strLogMessage.isEmpty()) {
				test.log(logStatus, strLogMessage);
				report.flush();
			}
		/*	System.out.println("logMessageToReport()::inside method @@@@@ log m_nScrShotCaptureLevel ****** "
					+ m_nScrShotCaptureLevel);*/
			if (m_nScrShotCaptureLevel >= nLevel) {
			/*	System.out.println("logMessageToReport()::inside method @@@@@ log m_nScrShotCaptureLevel "
						+ m_nScrShotCaptureLevel);*/
				Thread.sleep(5000);
				// Take base64Screenshot screenshot.
				String base64Screenshot = "data:image/jpeg;base64,"
						+ ((TakesScreenshot) localDriver).getScreenshotAs(OutputType.BASE64);

				test.log(logStatus, "Test Failed", test.addBase64ScreenShot(base64Screenshot));

				report.flush();
			}
		} catch (Exception e) {
			System.out.println("logMessageToReport():Exception while capturing screenshot" + e.getMessage());
		}
	}

	public static WebElement uiElement(WebElement webElement) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(webElement));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true);", webElement);
		} catch (Exception e) {

		}
		return webElement;
	}

	public static WebElement uiElement(WebElement webElement, WebDriver driver) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(webElement));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true);", webElement);
		} catch (Exception e) {
			System.out.println("In uiElement Catch Block");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
			}
		}
		return webElement;
	}

	public static void explicitWait(WebElement element, int time) {

		wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.visibilityOf(element));

	}

	public static WebElement uiElementWithoutTimeout(String string, WebDriver driver) {
		WebElement webElement = null;
		try {
			Thread.sleep(3000);
			webElement = driver.findElement(By.xpath(string));
			if (webElement == null) {
				throw new NoSuchElementException("Invalid Element");
			}
		} catch (Exception e) {
		}
		return webElement;
	}

	public static WebElement uiElement(String string, WebDriver driver) {
		WebElement webElement = null;
		boolean flag = true;
		int count = 1;
		while (flag) {
			if (count < numberOfAttemptsToFindWebElement) {
				try {
					webElement = driver.findElement(By.xpath(string));
					if (webElement.isDisplayed()) {
						flag = false;
						System.out.println("This Web Element was found after: " + count + " attempts");
					}
				} catch (Exception e) {
					try {
						Thread.sleep(500);
						WebDriverWait wait = new WebDriverWait(driver, 30);
						wait.ignoring(StaleElementReferenceException.class)
								.until(ExpectedConditions.visibilityOf(webElement));

					} catch (InterruptedException e1) {
					}
					count = count + 1;
					System.out.println("Number of Attempts to find this Web Element: " + count);
				}
			} else {
				System.out.println("Maximum number of attempts to find Web Element is " + count + " attempts");
				System.out.println(count + " attempts over");
				break;
			}
		}
		if (webElement == null) {
			throw new NoSuchElementException("Invalid Element");
		}
		return webElement;
	}


/* @AfterSuite 
 public static void tearDown() { 
	 driver.close();
}
	*/
}
