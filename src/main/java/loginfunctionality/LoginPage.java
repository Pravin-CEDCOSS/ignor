package loginfunctionality;

import org.openqa.selenium.support.PageFactory;

import method.Webutil;

public class LoginPage extends LoginPageOr {
	
	Webutil webutil;
	
	public LoginPage(Webutil wu) {
		PageFactory.initElements(wu.getDriver(), LoginPage.this);
		webutil=wu;
	}


	


	public void validLogin() {
		webutil.sendKeys(locEmailId, "chiraggupta@cedcommerce.com", "input email id in email box successfully");
		webutil.click(locContinue, "clicked successfully");
		
		webutil.sendKeys(locPassword, "SeleniumTest", "input password in password text box is successfully");
		webutil.click(locLoginBT, "clicked successfully");
	
	}
	
	public void homePage() {
		webutil.click(locProducts,"Click on product");
		webutil.click(locAddProducts, "Clicked on Add product");
	
	}
	
	
	
	

}
