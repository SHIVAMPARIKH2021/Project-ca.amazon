package ca.amazon.pages;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ca.amazon.basepackage.BaseTest;

public class HomePage extends BaseTest {

	Actions action;
	List<WebElement> distincttotalResults;
	List<WebElement> distincttotalResults_page2;
	private String item;
	
	@FindBy(id="twotabsearchtextbox")
	private static WebElement searchbar;
	
	@FindBy(id="nav-search-submit-button")
	private static WebElement searchbutton;
	
	@FindBy(tagName="img")
	private static List<WebElement> searchresult;
	
	@FindBy(tagName="img")
	private static List<WebElement> searchresult_page2;
	
	@FindBy(xpath="//a[@aria-label='Go to page 2' and text()='2']")
	private static WebElement page2;
	
	//a[@aria-label='Go to page 2' and text()='2']
	
	//*[@id="search"]/div[1]/div[1]/div/span[3]/div[2]/div[69]/div/div/span/a[1]
	
	
	
	public HomePage(){
		PageFactory.initElements(driver, this);
	}
	
	public void searchItem(String item) {
		this.item=item;
		searchbar.sendKeys(item);
	}
	
	public List<WebElement> searchButton() {
		searchbutton.click();
		List<WebElement> imglist = new ArrayList<WebElement>(searchresult);
		LinkedHashSet<WebElement> set = new LinkedHashSet<WebElement>(imglist);
		List<WebElement> totalResults = new ArrayList<WebElement>(set);
	
		distincttotalResults = new ArrayList<WebElement>();
		
		for (int i=0;i<totalResults.size();i++) {
			if((totalResults.get(i).getAttribute("data-image-index")!= null)) {
			String s = totalResults.get(i).getAttribute("src").toString();
			distincttotalResults.add(totalResults.get(i));
			System.out.println(s);
			}
		}
		
		return distincttotalResults;
	}
	
	public List<WebElement> searchresult_page2() throws InterruptedException {
		driver.manage().timeouts().pageLoadTimeout(EXPLICIT_WAIT, TimeUnit.MILLISECONDS);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,9000)");
		Thread.sleep(EXPLICIT_WAIT);
		js.executeScript("window.scrollBy(0,-1050)");
		Thread.sleep(EXPLICIT_WAIT);
		page2.click();
		Thread.sleep(EXPLICIT_WAIT);
		List<WebElement> imglist_page2 = new ArrayList<WebElement>(searchresult_page2);
		LinkedHashSet<WebElement> set_page2 = new LinkedHashSet<WebElement>(imglist_page2);
		List<WebElement> totalResults_page2 = new ArrayList<WebElement>(set_page2);
	
		distincttotalResults_page2 = new ArrayList<WebElement>();
		
		for (int i=0;i<totalResults_page2.size();i++) {
			if((totalResults_page2.get(i).getAttribute("data-image-index")!= null)) {
			String s_page2 = totalResults_page2.get(i).getAttribute("data-image-index").toString();
			distincttotalResults_page2.add(totalResults_page2.get(i));
			System.out.println(s_page2);
			System.out.println(distincttotalResults_page2.size());
			}
		}
		
		return distincttotalResults_page2;
	}
	
	public boolean paginationAssertion() throws InterruptedException {
		this.distincttotalResults.equals(this.distincttotalResults_page2);
		return false;
	}
}
