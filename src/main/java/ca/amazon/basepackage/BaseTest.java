package ca.amazon.basepackage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import ca.amazon.utilities.BaseUtils;

public class BaseTest {

	private static FileInputStream file;
	private static Properties prop;
	protected static WebDriver driver;

	
	public BaseTest() {
		try {
		prop = new Properties();	
		file = new FileInputStream("C:\\Users\\shiva\\eclipse-workspace\\ca.amazon\\src\\main\\java\\ca\\amazon\\configuration\\configuration.properties");
		prop.load(file);
	 }
		catch(IOException e) {
			e.printStackTrace();
			System.out.println("Configurations are not found; Restart the process or Check the program");
		}	
    }
	
	private static void get() {
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
	}
	public static void initiate() {
		if(prop.getProperty("browser").toString().equals(BaseUtils.Chrome.toString())) {
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			driver = new ChromeDriver();
			get();
		}
		else if(prop.getProperty("browser").toString().equals(BaseUtils.Edge.toString())) {
			System.setProperty("webdriver.edge.driver", "msedgedriver.exe");
			driver = new EdgeDriver();
			get();
		}
		else {
			System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
			driver = new FirefoxDriver();
			get();
		}
	}
}	
