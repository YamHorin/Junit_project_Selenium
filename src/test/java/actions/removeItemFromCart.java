package actions;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class removeItemFromCart {

	private WebDriver driver;
	private Logger logger;
	String UrlCart = "https://atid.store/cart-2/";
	By cartIcon = By.className("ast-site-header-cart-li"); 
	By tableSelector = By.cssSelector("table.shop_table:nth-child(1)");

	By emptyCart = By.className("cart-empty");
	
	public removeItemFromCart(WebDriver driver, Logger logger) {
		this.driver = driver;
		this.logger = logger;
	}
	public void remove(String itemName) throws Exception
	{
		driver.findElement(cartIcon).click();
		
		if (checkEmpty())
			return;
		
		WebElement table = driver.findElement(tableSelector); 
	  	
      List<WebElement> rows = table.findElements(By.tagName("tr"));

      for (WebElement row : rows) {
          WebElement productNameElement = row.findElement(By.cssSelector("td.product-name")); // Adjust selector if needed
          String productName = productNameElement.getText();
          if (productName.equalsIgnoreCase(itemName)) {
              driver.findElement(By.cssSelector("td.product-remove")).click();
              logger.info(String.format("the product %s has been removed from the cart",itemName));
          }
      }
      throw new Exception("the product is not in the cart");// Item is not found in the cart
	}
	public void removeAll()
	{
		logger.info("let's remove all the products from the cart");
		
		driver.findElement(cartIcon).click();

		if (checkEmpty())
			return;
		
		
		WebElement table = driver.findElement(tableSelector); 
	  	
      List<WebElement> rows = table.findElements(By.tagName("tr"));
      
      for (WebElement row : rows) {
          WebElement productNameElement = row.findElement(By.cssSelector("td.product-name")); // Adjust selector if needed
          String productName = productNameElement.getText();
              driver.findElement(By.cssSelector("td.product-remove")).click();
              logger.info(String.format("the product %s has been removed from the cart",productName));
          }
	}
	public boolean checkEmpty()
	{
		try {
			driver.findElement(emptyCart).getText();
			logger.info("the site saying the cart is empty ");
			return true;
			
		} catch (NoSuchElementException e) {
			logger.info("the site saying the cart is not empty ");
			return false;
		}
	}
	
}
