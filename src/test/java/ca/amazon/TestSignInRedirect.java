package ca.amazon;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ca.amazon.basepackage.BaseTest;
import ca.amazon.pages.SignIn;
import ca.amazon.pages.SignInRedirect;
import net.sourceforge.tess4j.TesseractException;

public class TestSignInRedirect extends BaseTest {

	SignIn si;
	SignInRedirect sr;
	
	public TestSignInRedirect() {
		super();
	}
	
	@BeforeMethod
	public void StartSignInRedirectTest() throws InterruptedException, IOException, TesseractException {
	initiate();
	si=new SignIn();
	si.Signin();
	si.Email(prop.getProperty("username"));
    sr=si.Continue(); //'sr' is initialized
	}
	
	@Test(priority=4)
	public void PasswordTest() throws InterruptedException {
		sr.Password(prop.getProperty("password"));
		sr.SignInAccount();
		Thread.sleep(EXPLICIT_WAIT);
		String title = driver.getTitle().toString();
		Assert.assertEquals(title,"Amazon.ca: Low Prices – Fast Shipping – Millions of Items");
	}
	
	@Test(priority=5)
	public void RememberMeTest() {
		sr.Password(prop.getProperty("password"));
		sr.RememberMe().isEnabled();
	}
	
	@Test(priority=6)
	public void SignInTest() {
		sr.Password(prop.getProperty("password"));
		sr.SignInAccount();
	}
	
	@AfterMethod
	public void EndSignInRedirectTest() {
		quitbrowser();
	}
}
