package actions;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.*;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.xml.datatype.DatatypeConstants.Field;


public class addUserInfo {

	private WebDriver driver;
	private Logger logger;
	private JSONArray jsonArray;
	
    String filePath = "userInfo.json"; 
    String UrlCart = "https://atid.store/cart-2/";
    
    By checkPotint = By.cssSelector(".checkout-button");
    By First_name = By.cssSelector("#billing_first_name");
    By Last_name = By.cssSelector("#billing_last_name");
    By Street_address = By.cssSelector("#billing_address_1");
    By Postcode_ZIP = By.cssSelector("#billing_postcode");
    By Town_City = By.cssSelector("#billing_city");
    By Phone = By.cssSelector("#billing_phone");
    By Email_address = By.name("billing_email");
    
    By placeOrder = By.xpath("//*[@id=\"place_order\"]");
    By shipping = By.xpath("//*[@id=\"ship-to-different-address-checkbox\"]");
    
    By First_name2 = By.cssSelector("#shipping_first_name");
    By Last_name2 = By.cssSelector("#shipping_last_name");
    By Street_address2 = By.cssSelector("#shipping_address_1");
    By Postcode_ZIP2 = By.cssSelector("#shipping_postcode");
    By Town_City2 = By.cssSelector("#shipping_city");
    By notes = By.cssSelector("#order_comments");
    
    By arrow = By.xpath("//*[@id=\"select2-billing_country-container\"]");
    By israelSelect = By.className("select2-results__option--highlighted");
    
    Map<String, By> mapByName = new HashMap();
    Map<String, By> mapByNameShip = new HashMap();

    public boolean checkForError(List<WebElement> listItems , String errorStr)
    {
    	if (listItems ==null)
    	{
			logger.info("order did not passed action but no instances of the error we looking for");
			return false;
    	}
    	
        if (listItems.size() > 0) {
        	logger.info("there are errors in the website");
        	for (WebElement webElement : listItems) {
        		String error = webElement.getText();
        		logger.info("we got an error : "+error);
        		
				if (error.equals(errorStr) || error.toLowerCase().contains(errorStr.toLowerCase()))
				{
					
					logger.info(String.format("order did not passed action because of %S", errorStr));
					return true;
				}
			}
			logger.info("order did not passed action but no instances of the error we looking for");
			return false;
        } else {
			logger.info("order did passed no errors");
            return false;
        }
    }
    
    public boolean checkForSuccess(List<WebElement> listItems)
    {
    	if (listItems == null)
    	{
        	logger.info("order did not passed ,action was not  complete");
            return false;
    	}
    	
        // Check if there is only one <li> element
        if (listItems.size() == 1) {
        	logger.info("not error in the website");
        	logger.info("order passed action complete");
            return true;
        } else {
            logger.info("order did not passed action FAIL "+listItems.size()+" errors");
            return false;
        }
    }
    
    public void makeMapShip()
    {
    	
    	mapByNameShip.put("First_name2", First_name2);
    	mapByNameShip.put("Last_name2", Last_name2);
    	mapByNameShip.put("Street_address2", Street_address2);
    	mapByNameShip.put("Postcode_ZIP2", Postcode_ZIP2);
    	mapByNameShip.put("Town_City2", Town_City2);
    }
    
    
    
    public void makeMap()
    {
    	mapByName.put("First_name", First_name);
    	mapByName.put("Last_name", Last_name);
    	mapByName.put("Street_address", Street_address);
    	mapByName.put("Postcode_ZIP", Postcode_ZIP);
    	mapByName.put("Town_City", Town_City);
    	mapByName.put("Phone", Phone);
    	mapByName.put("Email_address", Email_address);
    }
    
	public addUserInfo(WebDriver driver , Logger log ,JSONArray arr ) {
		this.driver = driver;
		this.logger = log;
		jsonArray = arr;
		makeMap();
	}
	
	public List<WebElement> useCase(int caseTest)
	{
		JSONObject obj = (JSONObject) jsonArray.get(caseTest);
		logger.info(String.format("Now testing use case %d: %s", caseTest+1,(String)obj.get("testCase") ));
		driver.get(UrlCart);
		logger.info("now we at the cart let's click on the checkpoint");
		try {
			driver.findElement(checkPotint).click();			
		}
		catch(NoSuchElementException e)
		{
			logger.info("Could not get to the check point");
			logger.info("got an error in the process " +e.getMessage());
			return null;
		}
		if (!driver.getTitle().contains("Checkout"))
		{
			logger.info("Could not get to the check point");
			return null;
		}
		logger.info("we now at the checkPoint");
		Set<String> Set = mapByName.keySet();
		for (String key : Set) {
			driver.findElement(mapByName.get(key)).click();
			if ((String)obj.get(key) !=null)
				driver.findElement(mapByName.get(key)).sendKeys((String)obj.get(key));
			logger.info(String.format("put %s in the page as: %s", key ,(String)obj.get(key) ));
		}
		
		//take care of country 
		logger.info(String.format("put %s in the page as: %s","country" ,"Israel" ));
	    driver.findElement(arrow).click();
	    driver.findElement(israelSelect).click();
	    driver.findElement(arrow).click();
	    
	       if (caseTest>5)
	        {
	        	//need to add shipment address
	    	   makeMapShip();
	    	   logger.info("click on the ship to a diffrent location option");
	    	   driver.findElement(shipping).click();
	   	    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		   		Set<String> Set2 = mapByNameShip.keySet();
				for (String key : Set2) {
					driver.findElement(mapByNameShip.get(key)).click();
					if ((String)obj.get(key) !=null)
						driver.findElement(mapByNameShip.get(key)).sendKeys((String)obj.get(key));
					logger.info(String.format("put %s in the page in shipping section  as: %s", key ,(String)obj.get(key) ));
				}
				if (caseTest==7)
				{
					driver.findElement(notes).click();
					driver.findElement(notes).sendKeys((String)obj.get("notes"));
					logger.info(String.format("put %s in the page in shipping section (the other section) as: %s", "notes" ,(String)obj.get("notes") ));
				}
	        }
	    
	    
	    
	    driver.findElement(placeOrder).click();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	    // Locate the <ul> element
        WebElement ulElement = driver.findElement(By.xpath("//*[@id=\"post-40\"]/div/div/section[2]/div/div/div/div/div/div/div/form[3]/div[1]/ul")); 
      

        List<WebElement> listItems = ulElement.findElements(By.tagName("li"));
        logger.info(listItems.size());
        return listItems;
	
		
	}

}
