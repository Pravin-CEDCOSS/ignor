package layer;

import org.testng.annotations.Test;

import loginfunctionality.LoginPage;
import loginfunctionality.Info;

public class validateDataOnProduct extends BaseTestClass {

	@Test
	public void tc001_Set_Ecommerce() {
		LoginPage login = new LoginPage(wu);
		login.validLogin();
		login.homePage();
		Info info=new Info(wu);
		info.infoData();
	}


}
