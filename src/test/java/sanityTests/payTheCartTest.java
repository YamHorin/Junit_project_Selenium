package sanityTests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.IntStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
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

public class payTheCartTest {
	private WebDriver driver;
	private Logger logger;
	private addItemToCart addItemToCart;
	private addUserInfo addUserInfo;
	private removeItemFromCart removeItemFromCart;
	
	@Before
	public void setUP()
	{
		driver = new ChromeDriver();
	    // 1 | open | / | 
	    driver.get("https://atid.store/store/");
	    // 2 | setWindowSize | 1599x961 | 
	    driver.manage().window().setSize(new Dimension(1599, 961));
		
		logger=LogManager.getLogger(payTheCartTest.class);
		addItemToCart = new addItemToCart(driver, logger, new checkItemInCart(driver, logger));
		//read json file 
		JSONParser jsonParser = new JSONParser();
		FileReader reader = null;
		JSONArray arrJson = null;
		  try {
			reader = new FileReader("userInfoForShoppingCart.json");
			arrJson = (JSONArray)jsonParser.parse(reader);
		  }
			 catch (IOException | ParseException e) {
				e.printStackTrace();
			}
		
	
			addUserInfo = new addUserInfo(driver, logger , arrJson);

	}
	@After
	public void tearDown()
	{
		driver.close();
	}
	
	
	
	@Test
	public void test1() {
		logger.info("begin sanity test: pay for cart case 1");
		logger.info("begin add item to cart");
		
		try {
			addItemToCart.seeItemAndClick("atid black shoes").buyProduct(1);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("could not buy the product item");
			logger.info("fail");
			fail();
		}
		
		if (addUserInfo.checkForSuccess(addUserInfo.useCase(0)))
			logger.info("we got no errors in our process - TEST PASS");
		else
			logger.info("we got errors in our process - TEST FAIL");
	}
	
	@Test
	public void test2() {
		logger.info("begin sanity test: pay for cart case 2");
		logger.info("begin remove items from cart");
		
		try {
			removeItemFromCart = new removeItemFromCart(driver, logger);
			removeItemFromCart.removeAll();
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("could not remove the products items");
			logger.info("fail");
			fail();
		}
		
		if (!addUserInfo.checkForSuccess(addUserInfo.useCase(1)))
			logger.info("we could not complete paying for an empty cart - TEST PASS");
		else
			logger.info("we got no errors in our process and we paid for an empty cart - TEST FAIL");

	}
	@Test
	public void test3()
	{
		logger.info("begin sanity test: pay for cart case 3");
		logger.info("begin add items to cart");
		
		try {
			addItemToCart.seeItemAndClick("basic gray jeans").buyProduct(3);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("could not buy the product item");
			logger.info("fail");
			fail();
		}
		
		if (addUserInfo.checkForError(addUserInfo.useCase(2),"Last Name"))
			logger.info("we found our error in the site : PASS");
		else
		{
			logger.info("we didn't found our error in the site : FAIL");

		}
		
	}
	
	@Test
	public void test4()
	{
		logger.info("begin sanity test: pay for cart case 4");
		logger.info("begin add items to cart");
		
		try {
			addItemToCart.seeItemAndClick("basic gray jeans").buyProduct(5);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("could not buy the product item");
			logger.info("fail");
			fail();
		}
		
		if (addUserInfo.checkForError(addUserInfo.useCase(3),"address"))
			logger.info("we found our error in the site : PASS");
		else
		{
			logger.info("we didn't found our error in the site : FAIL");

		}
		
	}
	
	@Test
	public void test5()
	{
		logger.info("begin sanity test: pay for cart case 5");
		logger.info("begin add items to cart");
		
		try {
			addItemToCart.seeItemAndClick("basic gray jeans").buyProduct(5);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("could not buy the product item");
			logger.info("fail");
			fail();
		}
		
		if (addUserInfo.checkForError(addUserInfo.useCase(4),"email"))
			logger.info("we found our error in the site : PASS");
		else
		{
			logger.info("we didn't found our error in the site : FAIL");

		}
		
	}
	
	@Test
	public void test6()
	{
		logger.info("begin sanity test: pay for cart case 6");
		logger.info("begin add items to cart");
		
		try {
			addItemToCart.seeItemAndClick("basic gray jeans").buyProduct(5);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("could not buy the product item");
			logger.info("fail");
			fail();
		}
		
		if (addUserInfo.checkForError(addUserInfo.useCase(5),"phone"))
			logger.info("we found our error in the site : PASS");
		else
		{
			logger.info("we didn't found our error in the site : FAIL");

		}
		
	}
	
	@Test
	public void test7()
	{
		logger.info("begin sanity test: pay for cart case 7");
		logger.info("begin add items to cart");
		
		try {
			addItemToCart.seeItemAndClick("basic gray jeans").buyProduct(5);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("could not buy the product item");
			logger.info("fail");
			fail();
		}
		
		if (addUserInfo.checkForSuccess(addUserInfo.useCase(6)))
			logger.info("we didn't found error in the site : PASS");
		else
		{
			logger.info("we found error in the site : FAIL");

		}
		
	}
	
	@Test
	public void test8()
	{
		logger.info("begin sanity test: pay for cart case 8");
		logger.info("begin add items to cart");
		
		try {
			addItemToCart.seeItemAndClick("basic gray jeans").buyProduct(5);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("could not buy the product item");
			logger.info("fail");
			fail();
		}
		
		if (addUserInfo.checkForSuccess(addUserInfo.useCase(7)))
			logger.info("we didn't found error in the site : PASS");
		else
		{
			logger.info("we found error in the site : FAIL");

		}
		
	}
	

}
