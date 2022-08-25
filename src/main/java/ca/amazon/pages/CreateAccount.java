package ca.amazon.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ca.amazon.basepackage.BaseTest;
import ca.amazon.utilities.TestUtils;

public class CreateAccount extends BaseTest {
	public static TestUtils testutil;

	@FindBy(id = "nav-link-accountList")
	private static WebElement dropdownmenu;
	
	@FindBy(linkText = "Start here.")
	private static WebElement Registrationlink;
	
	@FindBy(linkText = "Sign in")
	private static WebElement SigninButton;
	
	CreateAccount(){
		PageFactory.initElements(driver,CreateAccount.class);
	}
	
	public static void DropDownMenu() {
		testutil.action().moveToElement(dropdownmenu).build().perform();
	}
	
	public static Registration Signinlink() {
		Registrationlink.click();
		return new Registration();
	}
	
	public static AmazonSignIn Signin() {
		SigninButton.click();
		return new AmazonSignIn();
	}
}

