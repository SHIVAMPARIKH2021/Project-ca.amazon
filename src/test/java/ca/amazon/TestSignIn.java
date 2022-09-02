package ca.amazon;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import ca.amazon.basepackage.BaseTest;
import ca.amazon.pages.SignIn;
import net.sourceforge.tess4j.TesseractException;


public class TestSignIn extends BaseTest {
	SignIn as;
	
	TestSignIn() {
		super();
	}
	
	@BeforeMethod
	public void StartSignInTest() throws InterruptedException {
		initiate();
		Thread.sleep(1000);
		as=new SignIn();
		}

	
	@Test(priority=5)
	public void SigninButtonTest() throws InterruptedException, IOException, TesseractException {
		as.Signin();
		String title = driver.getTitle().toString();
		Assert.assertEquals(title, "Amazon Sign In");
	}
	
	@Test(priority=6)
	public void EmailTest() throws InterruptedException {
		as.Signin();
		as.Email(prop.getProperty("username"));
	}
	
	@Test(priority=7)
	public void ContinueTest() throws InterruptedException {
		as.Signin();
		as.Email(prop.getProperty("username"));
		as.Continue();
	}
	
	@AfterMethod
	void EndSignInTest() {
		quitbrowser();
		
	}

}
