package ca.amazon.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ca.amazon.basepackage.BaseTest;

public class AmazonSignIn extends BaseTest {

	@FindBy(id = "ap_email")
	private static WebElement EmailOrMobile;
	
	@FindBy(xpath = "//input[@tabindex='5'and@id='continue']")
	private static WebElement ContinueButton;
	
	AmazonSignIn(){
		PageFactory.initElements(driver,AmazonSignIn.class);
	}
	
	public static void Email(String loginid) {
		EmailOrMobile.sendKeys(loginid);
	}
	
	public static void Mobilenumber(String mobileloginid) {
		EmailOrMobile.sendKeys(mobileloginid);
	}
	
	public static AmazonSignInRedirect Continue() {
		ContinueButton.click();
		return new AmazonSignInRedirect();
	}
}
