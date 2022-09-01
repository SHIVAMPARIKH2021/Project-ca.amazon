package ca.amazon.basepackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ca.amazon.utilities.BaseUtils;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class BaseTest {

	private static FileInputStream file;
	protected static Properties prop;
	protected static WebDriver driver;
	private static Logger log = Logger.getLogger(BaseTest.class);
	protected static final int EXPLICIT_WAIT=500;
	protected static final int IMPLICIT_WAIT=10000;
	protected static Actions action;
	
	

	
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
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		WebDriverWait wait = new WebDriverWait(driver, BaseTest.EXPLICIT_WAIT);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Hello, sign in']")));
		 
		
		
	}
	public static void initiate() {
		if(prop.getProperty("browser").toString().equals(BaseUtils.Chrome.toString())) {
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			driver = new ChromeDriver();
			log.info("Connecting with Chrome Browser");
			get();
		}
		else if(prop.getProperty("browser").toString().equals(BaseUtils.Edge.toString())) {
			System.setProperty("webdriver.edge.driver", "msedgedriver.exe");
			driver = new EdgeDriver();
			log.info("Connecting with Edge Browser");
			get();
		}
		else {
			System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
			driver = new FirefoxDriver();
			log.info("Connecting with FireFox Browser");
			get();
		}
	}
	public static void quitbrowser() {
		if(driver.equals(null)) {
		driver.quit();
		log.info("All the windows of this browser is closed");
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
	
	public static void Blocker() throws IOException, TesseractException {
		if (driver.getTitle().toString().equals("Amazon.ca")) {
		  File src = driver.findElement(By.cssSelector("body > div > div.a-row.a-spacing-double-large > div.a-section > div > div > form > div.a-row.a-spacing-large > div > div > div.a-row.a-text-center > img")).getScreenshotAs(OutputType.FILE);
		  //String path = System.getProperty("user.dir")+"/captcha.png";
		  String path = "S://captcha.png";
		  FileUtils.copyFile(src,new File("S://captcha.png"));
		  FileHandler.copy(src, new File(path));
		  ITesseract tessract = new Tesseract();
		  String imgtxt = tessract.doOCR(new File(path));
		  System.out.println(imgtxt);
		  String finaltxt = imgtxt.split("")[1].replaceAll("[^a-zA-Z]","");
		  System.out.println(finaltxt);
		  driver.findElement(By.cssSelector("#captchacharacters")).sendKeys(finaltxt);
		}
		else
		{
		  log.error("Can not automate the Script");
		  action = new Actions(driver);
		  action.moveToElement(driver.findElement(By.cssSelector("#navbar-backup-backup > div > div.nav-bb-right > a:nth-child(1)"))).build().perform();
		  driver.findElement(By.cssSelector("#navbar-backup-backup > div > div.nav-bb-right > a:nth-child(1)")).click();
		  action.moveToElement(driver.findElement(By.xpath("//span[text()= 'Hello, Sign in']"))).pause(100).build().perform();
		  driver.findElement(By.xpath("//span[text()='Sign in']")).click();
		  
		}
		
		
		
	}
}	
