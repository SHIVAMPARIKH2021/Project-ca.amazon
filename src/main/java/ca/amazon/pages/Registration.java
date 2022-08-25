package ca.amazon.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ca.amazon.basepackage.BaseTest;
import ca.amazon.utilities.TestUtils;

public class Registration extends BaseTest{
	public static TestUtils testutils;

	@FindBy(id = "ap_customer_name")
	private static WebElement YourName;
	
	@FindBy(id = "ap_email")
	private static WebElement MobileNumberOrEmail;

	@FindBy(id = "ap_password")
	private static WebElement Password;
	
	@FindBy(id = "ap_password_check")
	private static WebElement PasswordAgain;
	
	@FindBy(id = "continue")
	private static WebElement ContinueButton;
	
	Registration(){
		PageFactory.initElements(driver,Registration.class);
	}
	
	public static void YourName(String name) {
		YourName.sendKeys(name);
	}
	
	public static void Mobilenumber(String mobile) {
		MobileNumberOrEmail.sendKeys(mobile);
	}
	
	public static void Email(String emailid) {
		MobileNumberOrEmail.sendKeys(emailid);
	}
	
	public static void Password(String password) {
		Password.sendKeys(password);
	}
	
	public static void PasswordAgain(String passwordagain) {
		PasswordAgain.sendKeys(passwordagain);
	}
	
	public static void ContinueButton() {
		ContinueButton.click();
	}
}
