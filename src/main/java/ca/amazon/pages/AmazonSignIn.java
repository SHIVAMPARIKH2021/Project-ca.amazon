package ca.amazon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ca.amazon.basepackage.BaseTest;

public class AmazonSignIn extends BaseTest {
    static Actions action;
    
    @FindBy(id = "nav-link-accountList-nav-line-1")
    private static WebElement SignInButton;
    
	@FindBy(id = "ap_email")
	private static WebElement EmailOrMobile;
	
	@FindBy(xpath = "//input[@tabindex='5'and@id='continue']")
	private static WebElement ContinueButton;
	
	protected AmazonSignIn(){
		PageFactory.initElements(driver,AmazonSignIn.class);
	}
	
	public static void SignIn() {
		action.moveToElement(driver.findElement(By.id("SignInButton"))).build().perform();
		SignInButton.click();
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
