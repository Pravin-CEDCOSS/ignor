package loginfunctionality;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import method.Webutil;

public class Info extends InfoOr {

	Webutil webUtil;
	String[][] data;

	public Info(Webutil wu) {
		PageFactory.initElements(wu.getDriver(), Info.this);
		webUtil = wu;
		try {
			data = webUtil.readCSVData();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void infoData() {

		webUtil.sendKeys(Title, data[0][0], "Input title is successfully");
		webUtil.sendKeys(ProductType, data[0][2], "Input productvalue is sucessfully");
		webUtil.sendKeys(Vendor, data[0][3], "Input vender data is sucessfully");
		webUtil.sendKeys(Tags, data[0][4], "Input tags is sucessfully");
		webUtil.sendKeys(Price, data[0][5], "Input price is successfully");
		webUtil.sendKeys(Castprice, data[0][6], "Input Castprice is  successfully");
		webUtil.sendKeys(CastParItem, data[0][7], "Input Cost per item is successfully");
		webUtil.sendKeys(SKU, data[0][8], "Input Stock Keeping Unit is successfully");
		webUtil.sendKeys(barcode, data[0][9], "Barcode");
		webUtil.sendKeys(Waight, data[0][11], "Weight");
		webUtil.click(SaveBT, "Click on save button successfully");
		webUtil.click(ProductsTB, "Click On Product Tab Succeccfully");
		webUtil.sendKeys(FilterTF, "Task", "Input Data Succeccfully in filter Tab");

	}

}
