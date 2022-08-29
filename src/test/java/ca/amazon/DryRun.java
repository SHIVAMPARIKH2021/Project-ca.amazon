package ca.amazon;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import ca.amazon.basepackage.BaseTest;

public class DryRun extends BaseTest {

	@FindBy(xpath = "//span[text()= 'Hello, Sign in']")
    private static WebElement SignInDropdown;
	
	@FindBy(xpath = "//span[text()='Sign in']")
    private static WebElement SignInButton;
	

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
