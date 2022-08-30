package ca.amazon;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import ca.amazon.basepackage.BaseTest;
import ca.amazon.pages.AmazonSignIn;
import net.sourceforge.tess4j.TesseractException;


public class TestAmazonSignIn extends BaseTest {
	AmazonSignIn as;
	
	TestAmazonSignIn() {
		super();
	}
	
	@BeforeTest
	public void StartSignInTest() throws InterruptedException {
		initiate();
		as=new AmazonSignIn();
		}

	
	@Test(priority=1)
	public void SigninButtonTest() throws InterruptedException, IOException, TesseractException {
		
		as.SignIn();
		String title = driver.getTitle().toString();
		Assert.assertEquals(title, "Amazon Sign In");
	}
	
	@Test(priority=2)
	public void EmailTest() throws InterruptedException {
		as.Email(prop.getProperty("username"));
	}
	
	@Test(priority=3)
	public void ContinueTest() {
		as.Continue();
	}
	
	@AfterTest
	public void EndSignInTest() {
		//quitbrowser();
		
	}

}
