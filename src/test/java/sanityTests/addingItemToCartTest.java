package sanityTests;

import static org.junit.Assert.*;

import java.io.FileReader;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import actions.addItemToCart;
import actions.addUserInfo;
import actions.checkItemInCart;
import actions.removeItemFromCart;
import actions.pages.BaseProductPage;

public class addingItemToCartTest {
	private WebDriver driver;
	private Logger logger;
	private addItemToCart addItemToCart;
	private removeItemFromCart removeItemFromCart;
	private JSONArray arrJson;
	
	@Before
	public void setUP()
	{
		driver = new ChromeDriver();
	    // 1 | open | / | 
	    driver.get("https://atid.store/store/");
	    // 2 | setWindowSize | 1599x961 | 
	    driver.manage().window().setSize(new Dimension(1599, 961));
		
		logger=LogManager.getLogger(addingItemToCartTest.class);
		addItemToCart = new addItemToCart(driver, logger, new checkItemInCart(driver, logger));
		//read json file 
		JSONParser jsonParser = new JSONParser();
		FileReader reader = null;
		  try {
			reader = new FileReader("productsToAddToCart.json");
			arrJson = (JSONArray)jsonParser.parse(reader);
		  }
			 catch (IOException | ParseException e) {
				e.printStackTrace();
			}
		
	

	}
	@After
	public void after()
	{
		driver.close();
	}
	
	
	@Test
	public void test1() {
		logger.info("begin sanity test: buying an item case 1");
		JSONObject obj = (JSONObject) arrJson.get(0);
		logger.info((String)obj.get("Description"));
		logger.info("result: "+(String)obj.get("result"));
		logger.info("begin add item to cart");
		
		try {
			addItemToCart
					.seeItemAndClick((String)obj.get("product"))
					.buyProduct(Integer.parseInt((String)obj.get("amount"))
					);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("could not buy the product item");
			logger.info("FAIL");
			fail();
		}
		logger.info("we got the item in the cart: TEST PASS");
		
		
	}
	@Test
	public void test2() {
		logger.info("begin sanity test: buying an item case 2");
		JSONObject obj = (JSONObject) arrJson.get(1);
		logger.info((String)obj.get("Description"));
		logger.info("result: "+(String)obj.get("result"));
		logger.info("begin add item to cart");
		
		try {
			addItemToCart
					.seeItemAndClick((String)obj.get("product"))
					.buyProduct(Integer.parseInt((String)obj.get("amount"))
					);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("could not buy the product item");
			logger.info("PASS");
			fail();
		}
		logger.info("we got the item in the cart: TEST FAIL");
		
	}
	@Test
	public void test3() {
		logger.info("begin sanity test: buying an item case 3");
		logger.info("begin add item to cart");
		JSONObject obj = (JSONObject) arrJson.get(2);
		logger.info((String)obj.get("Description"));
		logger.info("result: "+(String)obj.get("result"));
		logger.info("begin add item to cart");
		
		try {
			addItemToCart
					.seeItemAndClick((String)obj.get("product"))
					.buyProduct(Integer.parseInt((String)obj.get("amount"))
					);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("could not buy the product item");
			logger.info("FAIL");
			fail();
		}
		logger.info("we got the item in the cart WITH THE AMOUNT: TEST PASS");
		
	
		
	}
	@Test
	public void test4() {
		logger.info("begin sanity test: buying an item case 4");
		logger.info("begin add item to cart");
		JSONObject obj = (JSONObject) arrJson.get(3);
		logger.info((String)obj.get("Description"));
		logger.info("result: "+(String)obj.get("result"));
		logger.info("begin add item to cart");
		
		try {
			addItemToCart
					.seeItemAndClick((String)obj.get("product"))
					.buyProduct(Integer.parseInt((String)obj.get("amount"))
					);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("could not buy the product item");
			logger.info("PASS");
			fail();
		}
		logger.info("we got the item in the cart WITH THE AMOUNT: TEST FAIL");
		
	
		
	}
	@Test
	public void test5() {
		logger.info("begin sanity test: buying an item case 5");
		logger.info("begin add item to cart");
		JSONObject obj = (JSONObject) arrJson.get(4);
		logger.info((String)obj.get("Description"));
		logger.info("result: "+(String)obj.get("result"));
		logger.info("begin add item to cart");
		
			try {
				BaseProductPage product  = addItemToCart.seeItemAndClick((String)obj.get("product"));
				logger.info("first time buy the product");
				product.buyProductNoCheckInTable(Integer.parseInt((String)obj.get("amount")));
				logger.info("second time buy the product");
				product.buyProductNoCheckInTable(Integer.parseInt((String)obj.get("amount")));
								
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		checkItemInCart check = new checkItemInCart(driver, logger);
		if (
			check.isItemInCartWithAmoumt(
			(String)obj.get("product"), 
			Integer.parseInt((String)obj.get("amount"))*2
					)
			)
			logger.info("we got the item in the cart WITH THE AMOUNT: TEST PASS");
		else
			logger.info("we got the item in the cart WITH INCORRECT AMOUNT: TEST FAIL");

		
	}

}
