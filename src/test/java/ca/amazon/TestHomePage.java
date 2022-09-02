package ca.amazon;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ca.amazon.basepackage.BaseTest;
import ca.amazon.pages.HomePage;
import ca.amazon.pages.SignIn;
import ca.amazon.pages.SignInRedirect;
import ca.amazon.utilities.BaseUtils;

public class TestHomePage extends BaseTest {

	SignIn si;
	SignInRedirect sr;
	HomePage hp;

	TestHomePage() {
		super();
	}

	@BeforeTest
	public void StartHomePageTest() throws InterruptedException {
		initiate();
		si=new SignIn();
		si.Signin();
		si.Email(prop.getProperty("username"));
		sr = si.Continue();
		sr.Password(prop.getProperty("password"));
		hp=new HomePage();
		hp=sr.SignInAccount();
	}

	@Test(priority=4)
	public void Search() {
		hp.SearchItem("Gaming Laptops");
		System.out.println(hp.SearchButton());
		Assert.assertEquals(hp.SearchButton(), BaseUtils.PAGE_RESULT);
	}

	@AfterMethod
	public void EndHomePageTest() {
		quitbrowser();
	}
}
