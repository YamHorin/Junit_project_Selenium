package actions.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import actions.checkItemInCart;

public class BaseProductPage {
	private WebDriver driver;
	private Logger logger;
	private checkItemInCart checkItemInCart;
	By title = By.cssSelector(".product_title");
	By description  = By.cssSelector("#tab-description");
	By amountProduct = By.name("quantity");
	By price = By.cssSelector("del .woocommerce-Price-amount, .woocommerce-Price-amount");
	By priceSale = By.cssSelector("ins .woocommerce-Price-amount");
	By buttonBuy = By.cssSelector(".single_add_to_cart_button");
	
	public BaseProductPage(WebDriver driver , Logger logger , checkItemInCart checkItemInCart ) {
		this.driver = driver;
		this.logger = logger;
		this.checkItemInCart = checkItemInCart;
		
	}
	public String getTitleProducr()
	{
		return  driver.findElement(title).getText();
		 
	}
	public void buyProduct(int amount) throws Exception
	{
	    driver.findElement(amountProduct).clear();
	    driver.findElement(amountProduct).sendKeys(String.format("%d",amount));
	    logger.info("action of selection anount has been made:" +amount);
	    double price = getPrice();
	    logger.info("let's buy the product for price: "+price+" total price"+(price*amount) );
	    driver.findElement(buttonBuy).click();
	    logger.info("let's check in the cart " );
	    String nameProduct = getTitleProducr();
	    logger.info("the product "+nameProduct+"is in the cart");
	    if (checkItemInCart.isItemInCartWithAmoumt(nameProduct, amount))
		    logger.info("we have the correct amount in the cart" );
	    else
	    {
		    logger.info("we don't have the correct amount in the cart" );
		    throw new Exception();

	    }
	}
	public void buyProductNoCheckInTable(int amount)
	{
	    driver.findElement(amountProduct).clear();
	    driver.findElement(amountProduct).sendKeys(String.format("%d",amount));
	    logger.info("action of selection anount has been made:" +amount);
	    double price = getPrice();
	    logger.info("let's buy the product for price: "+price+" total price"+(price*amount) );
	    driver.findElement(buttonBuy).click();
	    logger.info("let's check in the cart " );
	    String nameProduct = getTitleProducr();
	    logger.info("the product "+nameProduct+"is in the cart");

	}
	public String getDescription()
	{
		return driver.findElement(description).getText();
		
	}
	public double getPrice()
	{
		 try {
			 logger.info("let's find the current price");
			 return Double.parseDouble(driver.findElement(priceSale)
					 .getText()
					 .replaceAll("₪", "")
					 .trim());
		 }
		 catch (NoSuchElementException e) {
			 logger.info("let's find the sale price");
			 return Double.parseDouble(driver.findElement(price)
					 .getText()
					 .replaceAll("₪", "")
					 .trim());
		}
			
	}	
	
	

}
