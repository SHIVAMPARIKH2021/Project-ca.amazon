package ca.amazon.utilities;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import ca.amazon.basepackage.BaseTest;

public class TestUtils extends BaseTest {
	
private static Actions action;
private static Select select;


	public Actions action() {
		action = new Actions(driver);
		return action;
	}
	
	public Select select() {
		select = new Select((WebElement) driver);
		return select;
	}
}
