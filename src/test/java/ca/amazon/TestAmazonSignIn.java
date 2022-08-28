package ca.amazon;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import ca.amazon.pages.AmazonSignIn;

public class TestAmazonSignIn extends AmazonSignIn {
	
	TestAmazonSignIn() {
		super();
	}
	
	@BeforeTest
	public void StartTest() {
		initiate();
	}
	
	@Test
	public void SigninButtonTest() {
		AmazonSignin();
		String title = driver.getTitle().toString();
		Assert.assertEquals(title, "Amazon Sign In");
	}
	
	@AfterTest
	public void EndTest() {
		quitbrowser();
	}

}
