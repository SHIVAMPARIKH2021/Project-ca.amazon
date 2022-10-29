package ca.amazon;

import java.io.IOException;
import java.net.MalformedURLException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;

import ca.amazon.basepackage.BaseTest;
import net.sourceforge.tess4j.TesseractException;

public class DryRun extends BaseTest {

	@FindBy(xpath = "//span[text()= 'Hello, Sign in']")
    private static WebElement SignInDropdown;
	
	@FindBy(xpath = "//span[text()='Sign in']")
    private static WebElement SignInButton;
	

@Test
public void primarytest() throws MalformedURLException {
	initiate();
	
	  String a = "Shivam"; String b = "Shiva"; if(a!=b){
	  System.out.println("Dry Run is Passed"); } else {
	  System.out.println("Dry Run is Failed");
	}	 
	  quitbrowser();
}
@Test
public void secondtest() throws IOException, TesseractException {
	
	
	/*
	 * initiate(); PageFactory.initElements(driver, DryRun.class); Actions action =
	 * new Actions(driver);
	 * action.moveToElement(SignInDropdown).pause(100).build().perform();
	 * //action.moveToElement(driver.findElement(By.
	 * xpath("//span[text()='Hello, Sign in']"))).build().perform();
	 * //action.moveToElement(driver.findElement(By.
	 * xpath("//span[text()='Hello, Sign in']"))).build().perform();
	 * SignInButton.click();
	 */
	 
}


}
