package ca.amazon.pages;

//import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ca.amazon.basepackage.BaseTest;

public class SignIn extends BaseTest {
     Actions action;
    
    @FindBy(xpath = "//span[text()= 'Hello, sign in']")
	private static WebElement SignInDropdown;
    
    @FindBy(xpath = "//span[text()='Sign in']")
    private static WebElement SignInButton;
    
	@FindBy(id = "ap_email")
	private static WebElement EmailOrMobile;
	
	@FindBy(xpath = "//input[@tabindex='5'and@id='continue']")
	private static WebElement ContinueButton;
	
	 public SignIn(){
		PageFactory.initElements(driver,this);
	}
	
	 public void Signin() throws InterruptedException{
		action = new Actions(driver);
		action.moveToElement(SignInDropdown).build().perform();
		action.moveToElement(SignInButton).build().perform();
		SignInButton.click();
	}
	
	public void Email(String loginid) {
		EmailOrMobile.sendKeys(loginid);
	}
	
	public void Mobilenumber(String mobileloginid) {
		EmailOrMobile.sendKeys(mobileloginid);
	}
	
	public SignInRedirect Continue() {
		ContinueButton.click();
		return new SignInRedirect();
	}
}
