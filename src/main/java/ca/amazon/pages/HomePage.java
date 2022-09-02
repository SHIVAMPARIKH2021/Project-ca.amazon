package ca.amazon.pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ca.amazon.basepackage.BaseTest;

public class HomePage extends BaseTest {

	Actions action;
	private String item;
	
	@FindBy(id="twotabsearchtextbox")
	private static WebElement searchbar;
	
	@FindBy(id="nav-search-submit-button")
	private static WebElement searchbutton;
	
	@FindBy(tagName="img")
	private static List<WebElement> searchresult;
	
	public HomePage(){
		PageFactory.initElements(driver, this);
	}
	
	public void SearchItem(String item) {
		this.item=item;
		searchbar.sendKeys(item);
	}
	
	public int SearchButton() {
		searchbutton.click();
		List<WebElement> imglist = new ArrayList<WebElement>();
		imglist.addAll(searchresult);
		List<WebElement> totalResults = new ArrayList<WebElement>();
		for (int i=0;i<imglist.size();i++) {
			if((imglist.get(i).getAttribute("data-image-index")!= null)) {
			imglist.get(i).getAttribute("src");
			totalResults.add(imglist.get(i));
			}
		}
		
		return totalResults.size();
	}
}
