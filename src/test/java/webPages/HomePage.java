package webPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

	WebDriver driver = null;
	WebDriverWait wait = null;

	//By loginButton = By.xpath("//li[@id='sign-in']//a[contains(text(),'Sign In')]"); //declaring the object locator
	By loginButton = By.id("sign-in"); //declaring the object locator


	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	public void click_Signin_Button() throws Exception {
		Thread.sleep(4000);
		//WebDriverWait wait = new WebDriverWait(driver, 10);
		//WebElement element = wait.until(ExpectedConditions.elementToBeClickable(loginButton));

		System.out.println("waited and going to click");
		driver.findElement(loginButton).click(); //action for the locator
		System.out.println("sign in page opened");
	}



}
