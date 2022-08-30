package ca.amazon;

import java.io.IOException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ca.amazon.basepackage.BaseTest;
import ca.amazon.pages.AmazonSignIn;
import ca.amazon.pages.AmazonSignInRedirect;
import net.sourceforge.tess4j.TesseractException;


public class TestAmazonSignInRedirect extends BaseTest {
	
	AmazonSignIn as;
	AmazonSignInRedirect asr;
	
	public TestAmazonSignInRedirect() {
		super();
	}
	
	@BeforeTest
	public void StartAmazonSignInRedirctTest() throws InterruptedException, IOException, TesseractException {
	initiate();
	as=new AmazonSignIn();
	as.SignIn();
	as.Email(prop.getProperty("username"));
    asr=as.Continue(); //'asr' is initialized
	}
	
	@Test(priority=4)
	public void PasswordTest() {
		asr.Password(prop.getProperty("password"));
	}
	
	@Test(priority=5)
	public void RememberMeTest() {
        asr.RememberMe();
	}
	
	@Test(priority=6)
	public void SignInTest() {
		asr.SignInAccount();
	}
	
	@AfterTest
	public void EndSignInRedirectTest() {
		quitbrowser();
	}
}
