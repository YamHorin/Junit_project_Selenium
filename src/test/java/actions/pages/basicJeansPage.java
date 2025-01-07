package actions.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import actions.checkItemInCart;

public class basicJeansPage extends BaseProductPage {

	public basicJeansPage(WebDriver driver, Logger logger, actions.checkItemInCart checkItemInCart) {
		super(driver, logger, checkItemInCart);
	}

}
