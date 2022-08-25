package ca.amazon.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AmazonSignInRedirect {
	
	@FindBy(id = "ap_password")
	private static WebElement Password;
	
	@FindBy(id = "signInSubmit")
	private static WebElement SignIn;

	@FindBy(xpath = "//input[@name='rememberMe'and@tabindex='4']")
	private static WebElement RememberMe;
	
	public static void Password(String password) {
		Password.sendKeys(password);
	}
	
	public static void RememberMe() {
		RememberMe.click();
	}
	
	public static HomePage SignIn() {
		SignIn.click();
		return new HomePage();
	}
}
