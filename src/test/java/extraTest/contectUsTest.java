package extraTest;

import static org.junit.Assert.*;

import java.io.FileReader;
import java.io.IOException;

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
import actions.checkItemInCart;
import actions.contactUs;
import actions.removeItemFromCart;
import sanityTests.addingItemToCartTest;

public class contectUsTest {
	private WebDriver driver;
	private Logger logger;
	private contactUs contectUs;
	private JSONArray arrJson;
	
	
	@Before
	public void setUp()  {
		driver = new ChromeDriver();
	    driver.get("https://atid.store/store/");
	    driver.manage().window().setSize(new Dimension(1599, 961));
		
		logger=LogManager.getLogger(contectUsTest.class);
		//read json file 
		JSONParser jsonParser = new JSONParser();
		FileReader reader = null;
		  try {
			reader = new FileReader("contactUsInfoCases.json");
			arrJson = (JSONArray)jsonParser.parse(reader);
		  }
			 catch (IOException | ParseException e) {
				e.printStackTrace();
			}
		  contectUs = new contactUs(driver, logger, arrJson);
	}

	@After
	public void tearDown()  {
		driver.close();
	}

	@Test
	public void test1() {
		
		try {
			if (contectUs.contact(0))
				logger.info("ACTION PASS WITH NO ERRORS - TEST PASS");
		} catch (Exception e) {
			logger.info("ACTION PASS WITH ERRORS   "+e.getMessage()+" - TEST FAIL");

			
		}
	}
	@Test
	public void test2() {
		try {
			if (!contectUs.contact(1))
			{
				logger.info("ACTION PASS WITH ERRORS");
				logger.info("let's found error in the name field");
				if (contectUs.findError("name"))
					logger.info("error found in the field TEST PASS");
				else
					logger.info("error not found in the field TEST FAIL");

			}
			else
			{
				logger.info("ACTION PASS WITH NO ERRORS FAIL");

			}
		} catch (Exception e) {
			logger.info("ACTION PASS WITH ERRORS   "+e.getMessage()+" - TEST FAIL");

			
		}
	}
	@Test
	public void test3() {
		try {
			if (!contectUs.contact(2))
			{
				logger.info("ACTION PASS WITH ERRORS");
				logger.info("let's found error in the email field");
				if (contectUs.findError("email"))
					logger.info("error found in the field TEST PASS");
				else
					logger.info("error not found in the field TEST FAIL");

			}
			else
			{
				logger.info("ACTION PASS WITH NO ERRORS FAIL");

			}
		} catch (Exception e) {
			logger.info("ACTION PASS WITH ERRORS   "+e.getMessage()+" - TEST FAIL");

			
		}
	}
	@Test
	public void test4() {
		try {
			if (!contectUs.contact(3))
			{
				logger.info("ACTION PASS WITH ERRORS");
				logger.info("let's found error in the subject field");
				if (contectUs.findError("subject"))
					logger.info("error found in the field TEST PASS");
				else
					logger.info("error not found in the field TEST FAIL");

			}
			else
			{
				logger.info("ACTION PASS WITH NO ERRORS FAIL");

			}
		} catch (Exception e) {
			logger.info("ACTION PASS WITH ERRORS   "+e.getMessage()+" - TEST FAIL");

			
		}
	}
	@Test
	public void test5() {
		try {
			if (!contectUs.contact(4))
			{
				logger.info("ACTION PASS WITH ERRORS");
				logger.info("error found in the field TEST PASS");
			}
			else
			{
				logger.info("ACTION PASS WITH NO ERRORS FAIL");

			}
		} catch (Exception e) {
			logger.info("ACTION PASS WITH ERRORS   "+e.getMessage()+" - TEST FAIL");

			
		}
	}
	@Test
	public void test6() {
		try {
			if (!contectUs.contact(5))
			{
				logger.info("ACTION PASS WITH ERRORS");
				logger.info("let's found error in the email field");
				if (contectUs.findError("email"))
					logger.info("error found in the field TEST PASS");
				else
					logger.info("error not found in the field TEST FAIL");

			}
			else
			{
				logger.info("ACTION PASS WITH NO ERRORS FAIL");

			}
		} catch (Exception e) {
			logger.info("ACTION PASS WITH ERRORS   "+e.getMessage()+" - TEST FAIL");

			
		}
	}

}
