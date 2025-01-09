package sanityTests;

import static org.junit.Assert.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

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

import actions.SearchBar;
import actions.addItemToCart;
import actions.addUserInfo;
import actions.checkItemInCart;
import actions.removeItemFromCart;
import actions.searchResult;

public class searchItemInTheSiteTest {
	private WebDriver driver;
	private Logger logger;
	private JSONArray jsonArr;
	private SearchBar search;

	@Before
	public void setUp() throws Exception {
		driver = new ChromeDriver();
	    // 1 | open | / | 
	    driver.get("https://atid.store/store/");
	    // 2 | setWindowSize | 1599x961 | 
	    driver.manage().window().setSize(new Dimension(1599, 961));
		
		logger=LogManager.getLogger(searchItemInTheSiteTest.class);
		//read json file 
		JSONParser jsonParser = new JSONParser();
		FileReader reader = null;
		  try {
			reader = new FileReader("namesItemsForSearch.json");
			jsonArr = (JSONArray)jsonParser.parse(reader);
		  }
			 catch (IOException | ParseException e) {
				e.printStackTrace();
			}
		
	
			search = new SearchBar(driver, logger);

	}

	@After
	public void tearDown() throws Exception {
		driver.close();
	}

	@Test
	public void test1() {
		JSONObject obj = (JSONObject) jsonArr.get(0);
		String searchStr = (String)obj.get("name");
		logger.info("begin sanity test: search for item case 1");
		logger.info("case : "+ obj.get("case"));
		logger.info("expected Result : "+ obj.get("expected Result"));
		
		List<searchResult> results  = search.Search(searchStr);
		logger.info("we got our results now let's test and see if our String in the results (name or discription)");
		boolean isPass  = true;
		for (searchResult result : results)
		{
			String message =String.format("check if the result %s contain the input of our search %s, Whether the result"
					+ " was supposed to be displayed or not"
					, result.toString() 
					,(String)obj.get("name"));
			logger.info(message);
			if (!result.contain(searchStr))
			{
				logger.info("this result should not be display :"+result.toString());
				isPass = false;
			}
		}
		if (isPass)
			logger.info("TEST PASS");
		else
			logger.info("TEST FAIL");
		
	}
	@Test
	public void test2() {
		JSONObject obj = (JSONObject) jsonArr.get(1);
		String searchStr = (String)obj.get("name");
		logger.info("begin begin sanity test: search for item case 2");
		logger.info("case : "+ obj.get("case"));
		logger.info("expected Result : "+ obj.get("expected Result"));
		
		List<searchResult> results  = search.Search(searchStr);

		if (results==null)
			logger.info("TEST PASS");
		else
			logger.info("TEST FAIL");
		
	}
	@Test
	public void test3() {
		JSONObject obj = (JSONObject) jsonArr.get(2);
		String searchStr = (String)obj.get("name");
		logger.info("begin sanity test: search for item case 3");
		logger.info("case : "+ obj.get("case"));
		logger.info("expected Result : "+ obj.get("expected Result"));
		
		List<searchResult> results  = search.Search(searchStr);
		logger.info("we got our results now let's test and see if our String in the results (name or discription)");
		boolean isPass  = true;
		for (searchResult result : results)
		{
			String message =String.format("check if the result %s contain the input of our search %s, Whether the result"
					+ " was supposed to be displayed or not"
					, result.toString() 
					,(String)obj.get("name"));
			logger.info(message);
			if (!result.contain(searchStr))
			{
				logger.info("this result should not be display :"+result.toString());
				isPass = false;
			}
		}
		if (isPass)
			logger.info("TEST PASS");
		else
			logger.info("TEST FAIL");
		
	}
	@Test
	public void test4() {
		JSONObject obj = (JSONObject) jsonArr.get(3);
		String searchStr = (String)obj.get("name");
		logger.info("begin sanity test: search for item case 4");
		logger.info("case : "+ obj.get("case"));
		logger.info("expected Result : "+ obj.get("expected Result"));
		
		List<searchResult> results  = search.Search(searchStr);
		logger.info("we got our results now let's test and see if our String in the results (name or discription)");
		boolean isPass  = true;
		for (searchResult result : results)
		{
			String message =String.format("check if the result %s contain the input of our search %s, Whether the result"
					+ " was supposed to be displayed or not"
					, result.toString() 
					,(String)obj.get("name"));
			logger.info(message);
			if (!result.contain(searchStr))
			{
				logger.info("this result should not be display :"+result.toString());
				isPass = false;
			}
		}
		if (isPass)
			logger.info("TEST PASS");
		else
			logger.info("TEST FAIL");
	}
	@Test
	public void test5() {
		JSONObject obj = (JSONObject) jsonArr.get(4);
		String searchStr = (String)obj.get("name");
		logger.info("begin sanity test: search for item case 5");
		logger.info("case : "+ obj.get("case"));
		logger.info("expected Result : "+ obj.get("expected Result"));
		
		List<searchResult> results  = search.Search(searchStr);

		if (results==null)
			logger.info("TEST PASS");
		else
			logger.info("TEST FAIL");
	}
	@Test
	public void test6() {
		JSONObject obj = (JSONObject) jsonArr.get(5);
		String searchStr = (String)obj.get("name");
		logger.info("begin sanity test: search for item case 6");
		logger.info("case : "+ obj.get("case"));
		logger.info("expected Result : "+ obj.get("expected Result"));
		
		List<searchResult> results  = search.Search(searchStr);
		logger.info("we got our results now let's test and see if our String in the results (name or discription)");
		boolean isPass  = true;
		for (searchResult result : results)
		{
			String message =String.format("check if the result %s contain the input of our search %s, Whether the result"
					+ " was supposed to be displayed or not"
					, result.toString() 
					,(String)obj.get("name"));
			logger.info(message);
			if (!result.contain(searchStr))
			{
				logger.info("this result should not be display :"+result.toString());
				isPass = false;
			}
		}
		if (isPass)
			logger.info("TEST PASS");
		else
			logger.info("TEST FAIL");
	}
	@Test
	public void test7() {
		JSONObject obj = (JSONObject) jsonArr.get(6);
		String searchStr = (String)obj.get("name");
		logger.info("begin sanity test: search for item case 7");
		logger.info("case : "+ obj.get("case"));
		logger.info("expected Result : "+ obj.get("expected Result"));
		
		List<searchResult> results  = search.Search(searchStr);
		logger.info("we got our results now let's test and see if our String in the results (name or discription)");
		boolean isPass  = true;
		for (searchResult result : results)
		{
			String message =String.format("check if the result %s contain the input of our search %s, Whether the result"
					+ " was supposed to be displayed or not"
					, result.toString() 
					,(String)obj.get("name"));
			logger.info(message);
			if (!result.contain(searchStr))
			{
				logger.info("this result should not be display :"+result.toString());
				isPass = false;
			}
		}
		if (isPass)
			logger.info("TEST PASS");
		else
			logger.info("TEST FAIL");
	}

}
