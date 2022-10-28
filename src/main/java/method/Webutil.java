package method;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;
import shopify.webutility.method.WebUtility;

public class Webutil {
	private WebDriver driver;
	private static Webutil wu;
	private ExtentTest extTestLogger;
	private ExtentReports extReport;
    private Properties prop;
    private Workbook workbook;
	private Sheet sheetObj;
	
	
		public static Webutil getObject() {
			if (wu==null) {
				wu=new Webutil();
			}
			return wu;   
		}

	
		public  WebDriver getDriver() {
			return driver;
		}
	
		public Webutil(){
			
		}

	///// Wait........
	public void explicitWaitForElementVisibilty(WebElement weEle, int timeOutInSecond) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSecond));
		explicitWait.until(ExpectedConditions.visibilityOf(weEle));
	}

	public void explicitWaitForElementInvisibilty(WebElement weEle, int timeOutInSecond) {
		WebDriverWait explicitWait = new WebDriverWait(driver,  Duration.ofSeconds(timeOutInSecond));
		explicitWait.until(ExpectedConditions.invisibilityOf(weEle));
	}

	public void explicitWaitForElementEnabled(WebElement weEle, int timeOutInSecond) {
		WebDriverWait explicitWait = new WebDriverWait(driver,  Duration.ofSeconds(timeOutInSecond));
		explicitWait.until(ExpectedConditions.elementToBeClickable(weEle));
	}

	public void explicitWaitForElementText(WebElement weEle, int timeOutInSecond, String expectedText) {
		WebDriverWait explicitWait = new WebDriverWait(driver,  Duration.ofSeconds(timeOutInSecond));
		explicitWait.until(ExpectedConditions.textToBePresentInElement(weEle, expectedText));
	}

	public  void holdOn(int timeOutInSecond) {
		try {
			Thread.sleep(timeOutInSecond);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public  WebDriver launchBrowser(String browserName){
		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			extTestLogger.log(Status.INFO, "Chrome Browser Launch Successfuly");
		}else if(browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.chromedriver().setup();
			driver=new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			extTestLogger.log(Status.INFO, "Firefox Browser Launch Successfuly");
		}
		return driver;
	}

	public  void openUrl(String url) {
		try {
			driver.get(url);
			extTestLogger.log(Status.INFO, " url opened succesfuly");
		}catch(Exception e) {
			extTestLogger.log(Status.ERROR, " url doesn't open succesfuly");
			throw e;
		}
	}

	public void openLoginPage(Webutil wu, String browserName, String url) {
		wu.launchBrowser(browserName);
		wu.openUrl(url);

	}

	
	public WebElement getWebElement(By byObj) {
		WebElement weEle=null;
		try {
			
		}catch(NoSuchElementException e){
			e.printStackTrace();
			extTestLogger.log(Status.INFO, "exception occured while finding element "+e);
					
		}
		return weEle;
	}

	public void sendKeys(WebElement weEle, String sendValue,  String msg) {
		
		try {
			weEle.clear();
			weEle.sendKeys(sendValue);
		
		}catch(ElementNotInteractableException e) {
			/////javaScript
		}
		extTestLogger.log(Status.INFO, msg);
	}

	public void click(WebElement weEle,  String msg) {
		
		try {
			weEle.click();
		}catch(ElementNotInteractableException e) {
			jsClick(weEle);
		}
		extTestLogger.log(Status.INFO, msg);
	}


	public void jsClick(WebElement weEle) {
		

		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click", weEle);
	}


	/////// Actions...................
	public void actionsMouseOver(WebElement weEle, String msg) {

		Actions actObj=new Actions(driver);
		try {
			actObj.moveToElement(weEle).build().perform();
		}catch(ElementNotInteractableException e) {
			/////javaScript
		}		
		extTestLogger.log(Status.INFO, msg);
	}

	public void actionsRightClick(WebElement weEle,  String msg) {
		
		Actions actObj=new Actions(driver);
		try {
			actObj.contextClick(weEle).build().perform();
	
		}catch(ElementNotInteractableException e) {
			/////javaScript
		}		
		extTestLogger.log(Status.INFO, msg);
	}

	public void actionsDoubleClick(WebElement weEle,  String msg) {
		
		Actions actObj=new Actions(driver);
		try {
			actObj.doubleClick(weEle).build().perform();
		
		}catch(ElementNotInteractableException e) {
			/////javaScript
		}		
		extTestLogger.log(Status.INFO, msg);
	}


	////// DropDown......................
	public void selectByVisibleText( WebElement weEle, String sendVisibleText,   String msg) {
		
		Select selectObj=new Select(weEle);
		try {
			selectObj.selectByVisibleText(sendVisibleText);
		
		}catch(ElementNotInteractableException e) {
			/////javaScript
		}		
		extTestLogger.log(Status.INFO, msg);
	}

	public void selectByValue(WebElement weEle, String sendValue,   String msg) {
		
		Select selectObj=new Select(weEle);
		try {
			selectObj.selectByValue(sendValue);
		
		}catch(ElementNotInteractableException e) {
			/////javaScript
		}		
		extTestLogger.log(Status.INFO, msg);
	}

	public void selectByIndex( WebElement weEle, int indexNumber,  String msg) {
		
		Select selectObj=new Select(weEle);
		try {
			selectObj.selectByIndex(indexNumber);

		}catch(ElementNotInteractableException e) {
			/////javaScript
		}		
		extTestLogger.log(Status.INFO, msg);
	}

	public String getTimeStamp() {
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yyyy hh_mm_ss");
		String time=sdf.format(new Date());
		return time;
	}

	public String takeScreenshot(String snapShotName) {
		holdOn(5);
		String time=getTimeStamp();
		TakesScreenshot	tss= (TakesScreenshot) driver;
		File sourceFile=tss.getScreenshotAs(OutputType.FILE);	
		File destinationFile=new File("reports\\snap\\"+snapShotName+time+".jpeg");
		try {
			Files.copy(sourceFile, destinationFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destinationFile.getAbsolutePath();
	}


	public String getRandomName(int count) {
		String name="";
		for(int i=1; i<=count; i++) {
			int rnd=(int) (Math.random()*52);
			Character base=(rnd<26)?'A':'a';
			name=name+base.toString()+rnd%26;
		}
		return name;
	}

	public void getWindowHandleWithTitle( String expWindowTitle,  String msg) {
		Set<String> weHandleValue =driver.getWindowHandles();
		for(String getWnHndLValue:weHandleValue) {
			driver.switchTo().window(getWnHndLValue);
			String getWindowTitle = driver.getTitle();
			System.out.println(getWindowTitle);
			if(getWindowTitle.equalsIgnoreCase(expWindowTitle));
			break;
		}
		extTestLogger.log(Status.INFO, msg);
	}

	public void getWindowHandleWithUrl(String windowUrl,  String msg) {
		Set<String> weHandleValue =	driver.getWindowHandles();
		for(String getWnHndLValue:weHandleValue) {
			driver.switchTo().window(getWnHndLValue);
			String getWindowUrl = driver.getCurrentUrl();
			System.out.println(getWindowUrl);
			if(getWindowUrl.equalsIgnoreCase(windowUrl));
			break;
		}
		extTestLogger.log(Status.INFO, msg);
	}


	public void getWindowHandle(String windowUrlOrTitle,  String msg) {
		String title=driver.getTitle();
		Set<String> weHandleValue =	driver.getWindowHandles();
		for(String getWnHndLValue:weHandleValue) {
			driver.switchTo().window(getWnHndLValue);
			String getWindowUrl = driver.getCurrentUrl();
			System.out.println(getWindowUrl);

			if(getWindowUrl.equalsIgnoreCase(windowUrlOrTitle)) {
				break;
			}else if(title.equalsIgnoreCase(windowUrlOrTitle)){
				break;
			}
		}	

		extTestLogger.log(Status.INFO, msg);
	}

	
	public void htmlReport(String reportResultName) {
		ExtentHtmlReporter ExtHtmlRport = new ExtentHtmlReporter(reportResultName);
		ExtHtmlRport.config().setReportName("Function Reports Automation");
		ExtHtmlRport.config().setDocumentTitle("Zumia Functionality Reports");
		extReport =new ExtentReports();	
		extReport.attachReporter(ExtHtmlRport);
		extReport.setSystemInfo("username", System.getProperty("user.name"));
		extReport.setSystemInfo("OS", System.getProperty("user.os"));
		extReport.setSystemInfo("Envoirment", "QA");

	}



	public void setExtentLogger(String testCaseName) {
		extTestLogger=extReport.createTest(testCaseName);

	}



 public ExtentTest getLogger() {
		return extTestLogger;
	}

		public Properties getPropObj() {
			return prop;
		}


	//////    Validation...........

	public void verifyInnerText(WebElement weEle, String expectedText, String snapShotName) {
		
		String actualText=weEle.getText();
		if(actualText.equalsIgnoreCase(expectedText)) {
			extTestLogger.log(Status.PASS, " Where Actual Text is :- "+actualText+" & Expected is :- "+expectedText);
		}else {
			extTestLogger.log(Status.FAIL, " Where Actual Text is :- "+actualText+" & Expected is :- "+expectedText);
			String imgPath= takeScreenshot(snapShotName);
			try {
				extTestLogger.addScreenCaptureFromPath(imgPath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void verifyTextContains(WebElement weEle, String expectedText, String snapShotName){
		
		String actualText = weEle.getText();
		if(actualText.contains(expectedText)){
			extTestLogger.log(Status.PASS, "Text Verification is Passed where Actual is :- "+actualText+" & Expected is :- "+expectedText);
		}else
			extTestLogger.log(Status.PASS, "Text Verification is Failed where Actual is :- "+actualText+" & Expected is :- "+expectedText);
		String imgpath=takeScreenshot(snapShotName);
		try {
			extTestLogger.addScreenCaptureFromPath(imgpath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void verifyEnabled(WebElement weEle, String snapShotName) {
		
		boolean status = weEle.isEnabled();
		if(status){
			extTestLogger.log(Status.PASS, "Element is Enabled");
		}else {
			extTestLogger.log(Status.PASS, "Element is Disabled");
			String imgPath = takeScreenshot(snapShotName);
			try {
				extTestLogger.addScreenCaptureFromPath(imgPath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void verifyDisabled(WebElement weEle, String snapShotName) {
		
		boolean status = weEle.isEnabled();
		if(status==false){
			extTestLogger.log(Status.PASS, "Element is Disabled");
		}else {
			extTestLogger.log(Status.PASS, "Element is Enabled");
			String imgPath = takeScreenshot(snapShotName);
			try {
				extTestLogger.addScreenCaptureFromPath(imgPath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void verifyAttributeValue(WebElement weEle, String attributeName, String expectedAttributeValue, String snapShotName) {
		
		String actualAttributeValue = weEle.getAttribute(attributeName);
		if(actualAttributeValue.equalsIgnoreCase(expectedAttributeValue)){
			extTestLogger.log(Status.PASS, " Where Actual Attribute Value is :- "+actualAttributeValue+" & Expected Attribute Value is :- "+expectedAttributeValue);
		}else {
			extTestLogger.log(Status.FAIL, " Where Actual Attribute Value is :- "+actualAttributeValue+" & Expected Attribute Value is :- "+expectedAttributeValue);
			String imgPath = takeScreenshot(snapShotName);
			try {
				extTestLogger.addScreenCaptureFromPath(imgPath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


	public void verifyElementVisible(WebElement weEle, String snapShotName){
		
		boolean  weStatus = weEle.isDisplayed();
		Dimension dim =  weEle.getSize();
		int height = dim.getHeight();
		int width = dim.getWidth();
		if(weStatus==true && height>0 && width>0){
			extTestLogger.log(Status.PASS, "Element is Visible");
		}else
			extTestLogger.log(Status.FAIL, "Element is Invisible");
		String imgPath=takeScreenshot(snapShotName);
		try {
			extTestLogger.addScreenCaptureFromPath(imgPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void verifyElementInvisible(WebElement weEle, String snapShotName){
		
		boolean  weStatus = weEle.isDisplayed();
		Dimension dim =  weEle.getSize();
		int height = dim.getHeight();
		int width = dim.getWidth();
		if(weStatus==true && height>1 && width>1){
			extTestLogger.log(Status.PASS, "Element is Invisible");
		}else
			extTestLogger.log(Status.FAIL, "Element is Visible");
		String imgPath=takeScreenshot(snapShotName);
		try {
			extTestLogger.addScreenCaptureFromPath(imgPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void acceptPopUp() {
		driver.switchTo().alert().accept();
	}

	public void dismissPopUp() {
		driver.switchTo().alert().dismiss();
	}

	public void closeBrowser(String msg) {
		driver.close();
		extTestLogger.log(Status.PASS, msg);
		
	}

	public void quitBrowser(String msg) {
		driver.quit();
		extTestLogger.log(Status.PASS, msg);
		
	}


	public String[][] readCSVData() throws Exception {

		String[][] testData;
		
		String file="src/test/resources/TestData.csv";

		//Get the workbook
		Reader fileInputStream = new FileReader(file);
		Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(fileInputStream);


		int numberOfRecords = 0;
		int numberOfColumns = 0;


		for (CSVRecord record : records) {
			System.out.println("Reading record line #" + ++numberOfRecords);
			numberOfColumns = record.size();
		}

		testData = new String[numberOfRecords - 1][numberOfColumns];
		System.out.println("numberOfRecords = " + numberOfRecords);
		System.out.println("numberOfColumns = " + numberOfColumns);


		int currentRecord = 0;


		fileInputStream = new FileReader(file);
		records = CSVFormat.EXCEL.parse(fileInputStream);

		for (CSVRecord record : records) {

			System.out.println("Reading test data ");
			if (record.getRecordNumber() == 1) {
				System.out.println("record = " + record);
				continue;
			}

			for (int i = 0; i < record.size(); i++) {
				testData[currentRecord][i] = record.get(i);

			}
			currentRecord++;
		}


		return testData;
	}



	public void loaderConfigFile() {
		prop=new Properties();
		FileInputStream fis=null;		
		try {
			fis=new FileInputStream("src\\test\\resources\\config.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}


	}

	public int getRowCountofTcID(Sheet sheetobj,String testcaseIdName) {
		int activateRowNum=sheetobj.getLastRowNum();
		int count=0;
		for (int i = 1; i <= activateRowNum; i++) {
			Row rowObj=sheetobj.getRow(i);
			Cell cellObj=rowObj.getCell(1);
			String actualCellvalue=cellObj.getStringCellValue();
			if (actualCellvalue.equals(testcaseIdName)) {
				count++;
			}
		}
		return count;

	}



	public Object[][] readData(String sheetName ,String expTestCaseId)  {
		File file=new File("src\\main\\resources\\TestData");
		FileInputStream fis=null;
		try {
			fis = new FileInputStream(file);
			workbook=new XSSFWorkbook(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheetObj=workbook.getSheet(sheetName);		
		Object[][]arrObj=new Object[getRowCountofTcID(sheetObj,expTestCaseId)][1];
		int dataCount=0;
		int rowcount=sheetObj.getLastRowNum();
		for (int i = 1; i <= rowcount; i++) {		
			Row rowObj=sheetObj.getRow(i);
			Cell xcellObj=rowObj.getCell(1,MissingCellPolicy.CREATE_NULL_AS_BLANK);
			String actualTcId=xcellObj.getStringCellValue();
			if (expTestCaseId.equalsIgnoreCase(actualTcId)) {
				Map<String,String> dataMap=new HashMap<String, String>();
				int cellCount=rowObj.getLastCellNum();
				
				for (int j = 2; j <= cellCount-1; j=j+2) {
					Cell cellDataName=rowObj.getCell(j,MissingCellPolicy.CREATE_NULL_AS_BLANK);
					Cell cellDataValue=rowObj.getCell(j+1,MissingCellPolicy.CREATE_NULL_AS_BLANK);
					String dataKey=cellDataName.getStringCellValue();
					String dataValue=cellDataValue.getStringCellValue();
					dataMap.put(dataKey, dataValue);
				}
				arrObj[dataCount++][0]=dataMap;
			}
		}
		return arrObj;
	}	


    
    public Properties getProp() {
    	return prop;
    }

	public void loadConfiguration() {
		prop = new Properties();
		
		FileInputStream fis=null;
		try {
			 fis = new FileInputStream("config.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			prop.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	






}


