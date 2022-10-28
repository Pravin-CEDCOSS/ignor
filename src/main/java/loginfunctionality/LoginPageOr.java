package loginfunctionality;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPageOr {
	
   @FindBy(xpath = "//input[@id='account_email']")
   WebElement locEmailId;
   
   @FindBy(xpath = "//button[@type='submit']")
   WebElement locContinue;
   
   @FindBy(xpath = "//input[@id=\"account_password\"]")
   WebElement locPassword;
   
   @FindBy(xpath = "//button[@name='commit']") 
   WebElement locLoginBT;
   
   @FindBy(xpath = "//span[text()='Products']")
   WebElement locProducts;
   
   @FindBy(xpath = "//span[text()='Add product']")
   WebElement locAddProducts;
   
   
}
