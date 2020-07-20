package test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.beust.jcommander.Parameter;

import config.PropertiesFile;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ExcellUtils;
import webPages.HomePage;
import webPages.LoginPage;
import webPages.MyProfile;

public class addAndDeleteAddress {

	ExtentHtmlReporter htmlReporter;
	static ExtentReports extent;
	ExtentTest test;

	private WebDriver driver = null;
	public static String browserName=null;



	String projectPath = System.getProperty("user.dir");
	String excelPath =projectPath+"/Data/ShopcluesTestData.xlsx";


	@BeforeSuite
	public void setUpTest() {

		htmlReporter = new ExtentHtmlReporter("extentReport2.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		//PropertiesFile.getProperties();	//from properties file		

	}

	@Parameters("browser")
	@BeforeTest
	public void setUpBrowser(String browser) {

		//	public void setUpBrowser() {
		//		WebDriverManager.chromedriver().setup();
		//		driver = new ChromeDriver();

		System.out.println("Broser Name is "+browser);
		System.out.println("Thread Id for "+browser+" is "+Thread.currentThread().getId());
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		}
		else if(browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}



	}

	@Test(priority = 1)
	public void launchURL() {

		try {
			test = extent.createTest("Test1", "launchURL");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.manage(). deleteAllCookies();
			driver.get("https://www.shopclues.com/");
			driver.manage().window().maximize();
			driver.manage().timeouts();
			System.out.println("homepage launched");

			test.info("Url launched successfully");
			test.addScreenCaptureFromPath("HomePage.png");
			test.pass("details", MediaEntityBuilder.createScreenCaptureFromPath("HomePagePass.png").build());
		}

		catch (Exception e) {
			test.fail(e);
		}

	}

	@Test(priority=2,dataProvider ="username")
	public void signIn(String username, String password)  {

		try {
			System.out.println(username);
			System.out.println(password);

			test = extent.createTest("Test2", "signIn");

			HomePage homePage = new HomePage(driver);
			homePage.click_Signin_Button();

			LoginPage loginPage = new LoginPage(driver);
			loginPage.loginAction(username, password);
			loginPage.clickSignIn();

			test.info("Signed in successfully");
			test.addScreenCaptureFromPath("SignedIn.png");
			test.pass("details", MediaEntityBuilder.createScreenCaptureFromPath("SignedInPass.png").build());
		}
		catch (Exception e) {
			test.fail(e);
		}


	}

	@Test(priority = 3,dataProvider = "userData")
	public void addAddress(String fName, String lName, String mobNo,String pinCode, String houseNo, String street, String city, String state, String addressType)  {
		try {
			System.out.println("in add address");
			test = extent.createTest("Test3", "addAddress");
			System.out.println("going to click my profile");

			Thread.sleep(3000);

			MyProfile myProfile = new MyProfile(driver);		
			myProfile.clickMyProfileAfterLogin();
			myProfile.clickAddressBook();
			myProfile.addNewAddress(fName, lName, mobNo, pinCode, houseNo, street, city, state, addressType);
			System.out.println(fName);
			System.out.println(lName);
			System.out.println(mobNo);


			Assert.assertEquals(myProfile.actualSuccessMessage, myProfile.expectedSuccessMessage);
			System.out.println("testPass");

			test.info("New Address added successfully");
			test.addScreenCaptureFromPath("AddressAdded.png");
			test.pass("details", MediaEntityBuilder.createScreenCaptureFromPath("AddressAddedPass.png").build());
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			test.fail(e);
		}	

	}

	//	@Test (priority = 4)
	//	public void deleteAddress() {
	//		try {
	//		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	//		MyProfile myProfile = new MyProfile(driver);
	//		myProfile.deleteSAvedAddress();
	//		}
	//		catch (Exception e){
	//			System.out.println(e.getMessage());
	//			e.printStackTrace();
	//		}
	//	}


	@DataProvider(name="username")
	public Object[][] getCrendentialData() {


		Object data[][] =getCredential(excelPath, "UserCredentials");
		return data;
	}

	public static Object[][] getCredential(String excelPath, String sheetName){

		ExcellUtils excel = new ExcellUtils(excelPath, sheetName);
		int rowCount = excel.getRowCount();
		int colCount = excel.getColCount();

		Object data [][] = new Object [rowCount-1][colCount];

		for (int i=1;i<rowCount;i++) {
			for(int j=0;j<colCount;j++) {
				String cellData = excel.getData(i,j);
				System.out.print(cellData+" | ");
				data[i-1][j]=cellData;
			}
			System.out.println();
		}
		return data;

	}
	@DataProvider(name="userData")
	public Object[][] getUserData() {

		Object data[][] =userData(excelPath, "UserDetails");
		return data;
	}

	public static Object[][] userData(String excelPath, String sheetName){

		ExcellUtils excel = new ExcellUtils(excelPath, sheetName);
		int rowCount = excel.getRowCount();
		int colCount = excel.getColCount();
		Object data [][] = new Object [rowCount-1][colCount];
		try {	

			for (int i=1;i<rowCount;i++) {
				for(int j=0;j<colCount;j++) {
					String cellData = excel.getData(i,j);
					System.out.println(cellData);
					data[i-1][j]=cellData;
				}
				System.out.println();
			}

		}
		catch (Exception exp){
			System.out.println("exp message in testData Method"+exp.getMessage());
			System.out.println("exp cause in testData Method"+exp.getCause());
			exp.printStackTrace();

		}
		return data;

	}

	@AfterTest
	public static void browserClose() {
		//driver.close();

	}

	@AfterSuite
	public static void tearDown() {
		extent.flush();
	}
}
