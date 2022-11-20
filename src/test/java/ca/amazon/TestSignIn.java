package ca.amazon;

import java.io.IOException;
import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ca.amazon.basepackage.BaseTest;
import ca.amazon.pages.SignIn;
import ca.amazon.pages.SignInRedirect;
import net.sourceforge.tess4j.TesseractException;


public class TestSignIn extends BaseTest {
	SignIn as;
	TestSignIn() {
		super();
	}
	
	@BeforeMethod
	public void StartSignInTest() throws InterruptedException, MalformedURLException {
		initiate();
		Thread.sleep(1000);
		as=new SignIn();
		}

	
	@Test(priority=1,groups="MyGroup")
	public void SigninButtonTest() throws InterruptedException, IOException, TesseractException {
		as.Signin();
		String title = driver.getTitle();
		Assert.assertEquals(title, "Amazon Sign In");
	}
	
	@Test(priority=2)
	public void EmailTest() throws InterruptedException {
		as.Signin();
		as.Email(prop.getProperty("username"));
		Assert.assertEquals(as.Continue().getClass(), new SignInRedirect().getClass(), "Error: We cannot find an account with that e-mail address");
	}
	
	@AfterMethod
	void EndSignInTest() {
		quitbrowser();
		
	}

}
