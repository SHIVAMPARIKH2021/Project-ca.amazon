package ca.amazon;

import java.io.IOException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
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
	
	@BeforeTest
	public void StartSignInRedirectTest() throws InterruptedException, IOException, TesseractException {
	initiate();
	si=new SignIn();
	si.Signin();
	si.Email(prop.getProperty("username"));
    sr=si.Continue(); //'asr' is initialized
	}
	
	@Test(priority=1)
	public void PasswordTest() {
		sr.Password(prop.getProperty("password"));
	}
	
	@Test(priority=2)
	public void RememberMeTest() {
        sr.RememberMe();
	}
	
	@Test(priority=3)
	public void SignInTest() {
		sr.SignInAccount();
	}
	
	@AfterTest()
	public void EndSignInRedirectTest() {
		quitbrowser();
	}
}
