package webPages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	WebDriver driver = null;
	
	
	
	By userIdField = By.id("main_user_login");	
	By password = By.name("password");
	By loginButton = By.id("login_button");
	By skip = By.xpath("/html[1]/body[1]/div[3]/div[1]/div[4]/div[1]/div[2]/div[11]/div[1]/form[1]/div[3]/div[1]/a[1]");
	
//	By userIdField = By.xpath("//input[@id='main_user_login']");	
//	By password = By.xpath("//form[@name='loginform']//input[@name='password']");
//	By loginButton = By.xpath("//a[contains(@class,'enterclick')][contains(text(),'Login')]");
//	By skip = By.xpath("/html[1]/body[1]/div[3]/div[1]/div[4]/div[1]/div[2]/div[11]/div[1]/form[1]/div[3]/div[1]/a[1]");
	

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		
	}
	
	public void loginAction(String username, String pwd) throws Exception {
		
		System.out.println("beforelogin");
		Thread.sleep(6000);

		driver.findElement(userIdField).sendKeys(username);
		
		driver.findElement(password).sendKeys(pwd);
		
	}
	
	public void clickSignIn() throws Exception {
		
		System.out.println("before button click");
		Thread.sleep(6000);

		//driver.findElement(loginButton).sendKeys(Keys.RETURN);
		driver.findElement(loginButton).click();
		System.out.println("after login");
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(skip));
		driver.findElement(skip).click();
		System.out.println("landing on home page");
		
	}
	
}
