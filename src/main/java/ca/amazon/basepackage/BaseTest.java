package ca.amazon.basepackage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import ca.amazon.utilities.BaseUtils;

public class BaseTest {

	private static FileInputStream file;
	private static Properties prop;
	protected static WebDriver driver;
	private static Logger log = Logger.getLogger(BaseTest.class);;

	
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
}	
