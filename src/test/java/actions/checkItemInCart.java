package actions;

import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class checkItemInCart {
	private WebDriver driver;
	private Logger logger;
	String UrlCart = "https://atid.store/cart-2/";
	By cartIcon = By.className("ast-site-header-cart-li"); 
	By tableSelector = By.cssSelector("table.shop_table:nth-child(1) > tbody:nth-child(2)");
	By emptyCart = By.className("cart-empty");

	public checkItemInCart(WebDriver driver, Logger logger) {
		this.driver = driver;
		this.logger = logger;
	}
	public boolean checkEmpty()
	{
		try {
			driver.findElement(emptyCart).getText();
			logger.info("the site saying the cart is empty");
			return true;
			
		} catch (NoSuchElementException e) {
			logger.info("the site saying the cart is not empty ");
			return false;
		}
	}
	
	public boolean isItemInCart(String nameItem)
	{
			driver.findElement(cartIcon).click();
   	    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));


			if (checkEmpty())
			{
				logger.info("the cart is empty - no products has added");
				return false;
			}
			
			WebElement table = driver.findElement(tableSelector); 
		  	
          List<WebElement> rows = table.findElements(By.tagName("tr"));

          for (WebElement row : rows) {
              WebElement productNameElement = row.findElement(By.cssSelector("td.product-name")); // Adjust selector if needed
              String productName = productNameElement.getText();
              if (productName.equalsIgnoreCase(nameItem)) {
                  return true; // Item is found in the cart
              }
          }
          return false; // Item is not found in the cart
	}
	public boolean isItemInCartWithAmoumt(String nameItem , int amount)
	{
		driver.findElement(cartIcon).click();
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		if (checkEmpty())
		{
			logger.info("the cart is empty - no products has added");
			return false;
		}
		
		
		String message  = String.format("let's found the %s product in the cart with the amount %d", nameItem, amount);
		logger.info(message);
		logger.info("let's go into the cart");

		
		WebElement table = driver.findElement(tableSelector); 
		logger.info("let's go into the table");
		List<WebElement> rows = table.findElements(By.tagName("tr"));

      for (WebElement row : rows) {
          WebElement productNameElement = row.findElement(By.cssSelector("td.product-name")); // Adjust selector if needed
          String productName = productNameElement.getText();
  
          WebElement element = row.findElement(By.cssSelector("td.product-quantity"));
          
          String amountStr = element.findElement(By.className("input-text")).getAttribute("value");
          
          if (productName.equalsIgnoreCase(nameItem) && Integer.parseInt(amountStr)== amount) {
        	  message = String.format("we found the %s product in the cart with the amount %d", productName, Integer.parseInt(amountStr));
        	  logger.info(message);
        	  return true; // Item is found in the cart and amount is equal
          }
      }
      return false; // Item is not found in the cart
	}
	
	

	
	

}
