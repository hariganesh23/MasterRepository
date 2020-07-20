package test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MainTest {
	ExtentHtmlReporter htmlReporter;
	static ExtentReports extent;
	private static WebDriver driver = null;
	ExtentTest test;


	@BeforeSuite
	public void setUpTest() {

		htmlReporter = new ExtentHtmlReporter("extentReport.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);



	}

	@Parameters("browser")
	@BeforeTest
	public void setUpBrowser(String browser) {

		System.out.println("Broser Name is "+browser);
		System.out.println("Thread Id for "+browser+" is "+Thread.currentThread().getId());

		if(browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		}
		else if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
	}

	@Test(priority = 1)
	public void launchURL() throws Exception{
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


	@AfterSuite
	public static void tearDown() {
		extent.flush();
	}

}
