package ca.amazon.basepackage;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import ca.amazon.utilities.BaseUtils;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	private static FileInputStream file;
	protected static Properties prop;
	protected static WebDriver driver;
	private static Logger log = Logger.getLogger(BaseTest.class);
	protected static final int EXPLICIT_WAIT=500;
	protected static final int IMPLICIT_WAIT=1000;
	protected static Actions action;
	private static ExtentReports extentreport;
	private static ExtentTest extenttest;
	private static boolean flag;
	private static DesiredCapabilities dc;
	
	

	
	public BaseTest() {
		try {
		prop = new Properties();	
		file = new FileInputStream("C:\\Users\\shiva\\eclipse-workspace\\ca.amazon\\src\\main\\java\\ca\\amazon\\configuration\\configuration.properties");
		prop.load(file);
	 }
		catch(IOException e) {
			e.printStackTrace();
			System.out.println("Configurations are not found; Restart the process or Check the program");
			log.warn("Input Cofiguration Operation is Interrupted");
		}	
    }
	
	private static void get() {
		driver.get(prop.getProperty("url"));
		log.info("Opening the URL");
		driver.navigate().refresh();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		WebDriverWait wait = new WebDriverWait(driver, BaseTest.EXPLICIT_WAIT);
		
	}
	
	public static void initiate() throws MalformedURLException {
		
		switch(prop.getProperty("type")){
		
		case "local":
			if(prop.getProperty("browser").equalsIgnoreCase(BaseUtils.Chrome.toString())) {
				WebDriverManager.chromedriver().setup();
				//System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
				driver = new ChromeDriver();
				log.info("Connecting with Chrome Browser");
			}
			else if(prop.getProperty("browser").equalsIgnoreCase(BaseUtils.Edge.toString())) {
				WebDriverManager.edgedriver().setup();
				//System.setProperty("webdriver.edge.driver", "msedgedriver.exe");
				driver = new EdgeDriver();
				log.info("Connecting with Edge Browser");
			}
			else {
				WebDriverManager.firefoxdriver().setup();
				//System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
				driver = new FirefoxDriver();
				log.info("Connecting with FireFox Browser");
			}
			break;
			
		case "remote":
			driver = RemoteDriverFactory.getRemoteWebDriver(prop.getProperty("browser"));
			break;
		}
		
		get();
	}
	public static void quitbrowser() {
		if(driver != null) {
		driver.quit();
		log.info("All the windows of this browser are closed");
		}
		else
		{
			driver.close();
			log.info("Browser is closed");
		}
	}
	
	public static WebDriver windowhandler() {
		String currentHandle= driver.getWindowHandle();
		Set<String> handles=driver.getWindowHandles();
		for(String actual: handles) 
		{
		if(!actual.equalsIgnoreCase(currentHandle)) 
		{
		//Switch to the opened tab
		driver.switchTo().window(actual);
		}
	  }
		return driver;
	}
	
	public static void Blocker() {
	
		}
		
	}	
