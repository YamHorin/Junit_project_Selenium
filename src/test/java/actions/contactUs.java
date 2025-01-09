package actions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class contactUs {
	private WebDriver driver;
	private Logger logger;
	private JSONArray jsonArray;
	
    Map<String, By> mapByField = new HashMap();
    Map<String, By> mapByError = new HashMap();

    String filePath = "userInfo.json"; 
    String UrlCart = "https://atid.store/cart-2/";
    
    By contactUs = By.xpath("//*[@id=\"menu-item-829\"]/a");
    By	nameBy = By.id("wpforms-15-field_0");
    By subjectBy = By.id("wpforms-15-field_5");
    
    By messageBy = By.id("wpforms-15-field_2");
    By emailBy = By.id("wpforms-15-field_4");

    By submitBy = By.id("wpforms-submit-15");
    
    By nameErrorBy = By.id("wpforms-15-field_0-error");
    By subjectErrorBy = By.id("wpforms-15-field_5-error");
    By emailErrorBy = By.id("wpforms-15-field_4-error");
    By messageErrorBy = By.id("wpforms-15-field_2-error");
    
    
    
	public contactUs(WebDriver driver, Logger logger, JSONArray jsonArray) {
		this.driver = driver;
		this.logger = logger;
		this.jsonArray = jsonArray;
		makeMaps();
		
	}
	
	private void makeMaps() {
		mapByField.put("Name" ,nameBy );
		mapByField.put("Email" ,emailBy );
		mapByField.put("Message" ,messageBy );
		mapByField.put("Subject" , subjectBy);
		
		mapByError.put("Name", nameErrorBy);
		mapByError.put("Email", emailErrorBy);
		mapByError.put("Message", messageErrorBy);
		mapByError.put("Subject" , subjectErrorBy);
		
		
	}

	public boolean contact(int caseTest) throws Exception
	{
		JSONObject obj = (JSONObject) jsonArray.get(caseTest);
		logger.info(String.format("Now testing use case %d: %s", 
				caseTest+1,(String)obj.get("Use Case") ));
		driver.findElement(contactUs).click();
		
    	//WAIT 3 SECS
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		logger.info("now let's click on the contact us option");
		if (!driver.getTitle().contains("Contact"))
		{
			logger.info("Could not get to the check point");
			throw new Exception("Could not get to the check point");
		}
		logger.info("we now at the contact us screen");
		Set<String> Set = mapByField.keySet();
		for (String key : Set) {
			driver.findElement(mapByField.get(key)).click();
			if ((String)obj.get(key)!=null)
				driver.findElement(mapByField.get(key)).sendKeys((String)obj.get(key));
			logger.info(String.format("put %s in the page as: %s", key ,(String)obj.get(key) ));
		}
		driver.findElement(submitBy).click();
		logger.info("we clicked on the contact us check ");
		return !checkSuccess();


	}
	private boolean checkSuccess() {
		boolean foundError = false;
		Set<String> set = mapByError.keySet();
		String error = "";
		for (String key : set) {
			try {
				
				error = driver.findElement(mapByError.get(key)).getText();
				logger.info(String.format("we got error in %s field: %s", key , error));
				foundError=true;
				
			}
			catch(NoSuchElementException e)
			{
				//do nothing 
			}
		}
		if (!foundError)
			logger.info("we did not found errors in our action");
		return foundError;
	}

	
	public boolean findError(String field)
	{
		Set <String> listFields = mapByField.keySet();
		for (String key: listFields)
		{
			try {
				String error  = driver.findElement(mapByField.get(key)).getText();
				if (error !=null)
				{
					logger.info(String.format("we did found error in %s field", field));
					return true;
					
				}

			}catch(NoSuchElementException e){
				//do nothing
			}
			
		}
		
		
		logger.info(String.format("we did not found error in %s field", field));
		return false;
		
		
		
	}
	
    
    
    
	
}
