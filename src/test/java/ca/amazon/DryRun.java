package ca.amazon;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import ca.amazon.basepackage.BaseTest;

public class DryRun extends BaseTest {
	Logger log = Logger.getLogger(DryRun.class);

@Test
public void primarytest() {
	initiate();
	
	  String a = "Shivam"; String b = "Shiva"; if(a!=b){
	  System.out.println("Dry Run is Passed"); } else {
	  System.out.println("Dry Run is Failed");
	}	 
	  quitbrowser();
}
@Test
public void secondtest() {
	initiate();
	Actions action = new Actions(driver);
	action.moveToElement(driver.findElement(By.xpath("//span[text()='Hello, Sign in']"))).build().perform();
	action.moveToElement(driver.findElement(By.xpath("//span[text()='Hello, Sign in']"))).build().perform();
	driver.findElement(By.xpath("//span[text()='Sign in']")).click();
}

}
