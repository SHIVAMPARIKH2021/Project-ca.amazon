package ca.amazon.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import ca.amazon.basepackage.BaseTest;

public class BaseUtilsMethods extends BaseTest{
	private static Actions action;
	private static WebDriver driver;
	public static Actions action() {
		action = new Actions(driver);
		return action;
	}
}
