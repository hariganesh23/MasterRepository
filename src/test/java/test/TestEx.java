package test;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestEx {

	public static void main(String[] args) {
		
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extent.html");
	    
        // create ExtentReports and attach reporter(s)
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

		//String projectPath = System.getProperty("user.dir");
		//System.out.println(projectPath);

		//		System.setProperty("webdriver.chrome.driver", projectPath+"/Drivers/ChromeDriver/chromedriver.exe");
		WebDriverManager.chromedriver().setup();

		WebDriver driver = new ChromeDriver();
		
		// creates a toggle for the given test, adds all log events under it    
        ExtentTest test = extent.createTest("MyFirstTest", "Sample description");

		driver.get("https://www.shopclues.com/");
		driver.manage().window().maximize();
		
		test.pass("page opened");
//		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//		System.out.println("homepage");
//		driver.findElement(By.xpath("//li[@id='sign-in']//a[contains(text(),'Sign In')]")).click();
//		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//
//
//		System.out.println("beforelogin");
//		driver.findElement(By.xpath("//input[@id='main_user_login']")).sendKeys("hariganesheee@gmail.com");
//		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//		driver.findElement(By.xpath("//form[@name='loginform']//input[@name='password']")).sendKeys("Malar_Hari1");
//		System.out.println("before button click");
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		driver.findElement(By.xpath("//a[@id='login_button']")).click();
//		System.out.println("after login");
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		driver.findElement(By.xpath("//form[@class='default_form']//a[contains(text(),'Skip')]")).click();
//		System.out.println("landing on home page");
//
//		//click on the account name -> My profile
//		driver.findElement(By.xpath("/html[1]/body[1]/div[4]/div[1]/div[1]/div[4]/ul[1]/li[5]/a[1]")).click();
//		driver.findElement(By.xpath("//div[@class='hoverContent sc_actLinks']//a[contains(text(),'My Profile')]")).click();
//		//Address book
//		driver.findElement(By.xpath("//a[contains(text(),'My Address Book')]")).click();
//		//Add new Address
//		driver.findElement(By.xpath("//a[contains(text(),'Add New Address')]")).click();
//
//
//		driver.findElement(By.id("tag2")).sendKeys("Hari Ganesh");
//		driver.findElement(By.id("tag3")).sendKeys("Rajavel");
//		driver.findElement(By.id("tag4")).sendKeys("9345843287");
//		driver.findElement(By.id("tag5")).sendKeys("600054");
//		driver.findElement(By.id("tag6")).sendKeys("6A");
//		driver.findElement(By.id("tag7")).sendKeys("Ramalingam II Cross street, Gandhi Nagar");
//		driver.findElement(By.id("chooseState")).getAttribute("Value");
//		//		state.selectByValue("TN");
//
//		WebElement city = driver.findElement(By.xpath("//input[@name='city']"));
//		WebElement state = driver.findElement(By.id("chooseState"));
//
//		System.out.println("city = "+city.getAttribute("value"));
//		System.out.println("state = "+state.getAttribute("value"));
//
//
//		String c=city.getAttribute("value");
//
//		System.out.println("city = "+c);
//
//		if (c== "Avadi" )
//		{
//
//			System.out.println("City populated as per the Zip code");
//		}
//		else {
//			System.out.println("Incorrect City");
//		}
//
//		if (state.getAttribute("value")=="TN") {
//			System.out.println("State populated as per the Zip code");
//		}
//		else {
//			System.out.println("Incorrect State");
//		}
//
//		if (city.getText().contains("Avadi"))
//		{
//
//			System.out.println("City populated as per the Zip code");
//		}
//		else {
//			System.out.println("Incorrect City");
//		}
//
//		if (state.getText().contains("TN")) {
//			System.out.println("State populated as per the Zip code");
//		}
//		else {
//			System.out.println("Incorrect State");
//		}
//
//
//
//
//
//		driver.findElement(By.xpath("//label[contains(text(),'Home')]")).click();
//
//		driver.findElement(By.name("dispatch[profiles.update_addressbook]")).click();

		 // calling flush writes everything to the log file
        extent.flush();
        driver.close();
        driver.quit();

	}
}
