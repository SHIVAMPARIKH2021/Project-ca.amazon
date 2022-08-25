package ca.amazon.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import ca.amazon.basepackage.BaseTest;

public class Login extends BaseTest {
	static Actions action;

	@FindBy(id = "nav-link-accountList")
	private static WebElement dropdownmenu;
	
	@FindBy(linkText = "Start here.")
	private static WebElement Signinlink;
	
	public static CreateAccount clickonlink() {
		action.moveToElement(dropdownmenu).build().perform();
		return new CreateAccount();
	}	
}




