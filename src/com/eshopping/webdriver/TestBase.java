package com.eshopping.webdriver;


import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
 


 

public class TestBase {
	// initilaze the property file
	public static Properties CONFIG = null;
	public static Properties OR = null;
	public static WebDriver dr = null;
	public static EventFiringWebDriver driver = null;
	 

	@BeforeClass
	public static void beforeClass() throws Exception {
		initialize();

	}

	@AfterClass
	public static void afterClass() throws Exception {
		dr.quit();
	}

	public static void initialize() throws Exception {
		if (driver == null) {
			// config property file
			CONFIG = new Properties();
			FileInputStream fn = new FileInputStream(
					System.getProperty("user.dir")+ "//src//config//CONFIG.properties");
			CONFIG.load(fn);

			// OR propperty
			OR = new Properties();
			fn = new FileInputStream(System.getProperty("user.dir")	+ "//src//config//OR.properties");
			OR.load(fn);

			// Initalize web driver and event firing
			// WebDriver dr = null;
			if (CONFIG.getProperty("browser").equals("Firefox")) {
				dr = new FirefoxDriver();
			} else if (CONFIG.getProperty("browser").equals("Chrome")) {
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//src//drivers//chromedriver_win32//chromedriver.exe");    
				   dr=new ChromeDriver();
				   dr.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				   dr.manage().window().setSize(new Dimension(1920, 1080));
				   dr.get(CONFIG.getProperty("URL"));
				   wait(3);
					
			}

			if (dr != null) {
				driver = new EventFiringWebDriver(dr);
				driver.manage().timeouts().implicitlyWait(30l, TimeUnit.SECONDS);
			}
		}
		
	}

	protected static void quit() throws Exception {
		if (dr != null) {
			dr.quit();
			dr = null;
		}
		if (dr != null) {
			dr.quit();
			dr = null;
		}

		OR = null;
		CONFIG = null;
	}

	public static WebElement getObject(String xpathvalKey) {
		try {
			return dr.findElement(By.xpath(OR.getProperty(xpathvalKey)));
		} catch (Throwable t) {
			// report an error
			return null;
		}
	}

	public static void assertTrue(String string, boolean elementPresent) {

	}

	protected boolean isElementPresent(By by) {
		try {
			dr.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	public boolean waitForElement(final WebElement element, int timeout) throws InterruptedException {
		wait(1);
		WebDriverWait elementWait = new WebDriverWait(dr, timeout);
		try {
			return elementWait.until(new ExpectedCondition <Boolean>(){
				@Override
				public Boolean apply(WebDriver d) {
					try{
					return element.isDisplayed();
					}catch(StaleElementReferenceException e){}
					finally{}
					return null;
				}});
		} catch (TimeoutException e){
			return false;
		}
	}
	
	
	public static void wait(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
