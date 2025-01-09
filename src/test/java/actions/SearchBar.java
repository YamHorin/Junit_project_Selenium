package actions;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchBar {
	private WebDriver driver;
	private Logger logger;
	
	By bar =By.xpath("/html/body/div/header/div[1]/div[1]/div/div/div/div[3]/div[2]/div/div/a");
	By textInput = By.className("search-field");
	By results = By.className("ast-row");
	By resultTitle = By.className("entry-title");
	By reasultContext = By.className("entry-content");
	
	By noResults = By.cssSelector(".page-content > p:nth-child(1)");
	public SearchBar(WebDriver driver, Logger logger) {
		this.driver = driver;
		this.logger = logger;
	}
	public boolean checkNoResults()
	{
		logger.info("let's check for the no result message");
		try {
			String message = driver.findElement(noResults).getText();
			logger.info("we got a message in the site "+message);
			return true;
		}
		catch(NoSuchElementException e)
		{
			logger.info("no message found");
			return false;
		}
		
	}

	public List<searchResult> Search(String text)
	{
	
		logger.info("let's search for products in the search bar");
		driver.findElement(bar).click();
		logger.info("click on the icon search");
		if (text!=null)
			driver.findElement(textInput).sendKeys(text);
		driver.findElement(textInput).sendKeys(Keys.ENTER);
		logger.info("enter in the search bar: " + text);
		if (checkNoResults())
			return null;
		List<searchResult> searchResultsObjects = new ArrayList<searchResult>();
		try {
			WebElement element = driver.findElement(results);
			List<WebElement> listResultsElement =  element.findElements(By.tagName("article"));
			for (WebElement article : listResultsElement) {
				searchResult product = new searchResult(
						article.findElement(resultTitle).getText(), 
						article.findElement(reasultContext).getText());
				
				logger.info("found a result of a product: "+product.toString());
				searchResultsObjects.add(product);
			}
			
		}catch (NoSuchElementException  e) {
			logger.info("no results in the page");
		}
		
		logger.info("got "+searchResultsObjects.size()+"    results in the action");			
		return searchResultsObjects;
	}
	
	
}
