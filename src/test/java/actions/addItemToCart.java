package actions;

import org.openqa.selenium.WebDriver;

import actions.pages.BaseProductPage;
import actions.pages.anchorBraceletPage;
import actions.pages.basicJeansPage;
import actions.pages.blackShoesPage;
import actions.pages.greenShirtPage;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class addItemToCart {
	private WebDriver driver;
	private Logger logger;
	private checkItemInCart checkItemInCart;
	By AnchorBracelet = By.cssSelector("li.ast-col-sm-12:nth-child(1) > div:nth-child(2) > a:nth-child(1) > h2:nth-child(1)");
	By ATIDBlackShoes = By.xpath("/html/body/div/div[1]/div/div[2]/main/div/ul/li[2]/div[2]/a/h2");
	By BasicGrayJeans = By.cssSelector("li.ast-col-sm-12:nth-child(8) > div:nth-child(2) > a:nth-child(1)");
	By GreenTshirt = By.cssSelector("li.ast-col-sm-12:nth-child(5) > div:nth-child(2) > a:nth-child(1) > h2:nth-child(1)");
	By buttonBuy  = By.cssSelector(".single_add_to_cart_button");
	public addItemToCart(WebDriver driver , Logger logger , checkItemInCart checkItemInCart)
	{
		this.logger = logger;
		this.driver = driver;
		this.checkItemInCart   = checkItemInCart;
		
	}
	
	public BaseProductPage seeItemAndClick(String nameItem) throws Exception
	{

		logger.info("click on the item in the store: "+nameItem.toLowerCase());
		switch (nameItem.toLowerCase())
		{
		case "anchor-bracelet":
			driver.findElement(AnchorBracelet).click();
			if (!driver.getCurrentUrl().equalsIgnoreCase("https://atid.store/product/anchor-bracelet/"))
				throw new Exception("click falied");
			return new anchorBraceletPage(driver, logger, checkItemInCart);
		case "atid black shoes":
			driver.findElement(ATIDBlackShoes).click();
			if (!driver.getCurrentUrl().equalsIgnoreCase("https://atid.store/product/atid-black-shoes/"))
				throw new Exception("click failed");
			return new blackShoesPage(driver, logger, checkItemInCart);
		case "basic gray jeans":
			driver.findElement(BasicGrayJeans).click();
			if (!driver.getCurrentUrl().equalsIgnoreCase("https://atid.store/product/basic-gray-jeans/"))
				throw new Exception("click failed");
			return new basicJeansPage(driver, logger, checkItemInCart);
		case "green shirt":
			driver.findElement(GreenTshirt).click();
			if (!driver.getCurrentUrl().equalsIgnoreCase("https://atid.store/product/dnk-tshirt/"))
				throw new Exception("click failed");
			return new greenShirtPage(driver, logger, checkItemInCart);
		
		}
		return null;
		
	}
	
}
