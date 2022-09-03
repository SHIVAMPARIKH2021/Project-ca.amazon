package ca.amazon.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ca.amazon.basepackage.BaseTest;

public class SignInRedirect extends BaseTest {
	
	@FindBy(id = "ap_password")
	private static WebElement Password;
	
	@FindBy(id = "signInSubmit")
	private static WebElement SignIn;

	@FindBy(xpath = "//input[@name='rememberMe'and@tabindex='4']")
	private static WebElement RememberMe;
	
	public SignInRedirect(){
		PageFactory.initElements(driver,this);
	}
	
	public void Password(String password) {
		Password.sendKeys(password);
	}
	
	public WebElement RememberMe() {
		RememberMe.click();
		return RememberMe;
	}
	
	public HomePage SignInAccount() {
		SignIn.click();
		return new HomePage();
	}
}
