package webPages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.google.common.base.Function;

import net.bytebuddy.asm.Advice.Return;

public class MyProfile {

	public String actualSuccessMessage = null;
	public String expectedSuccessMessage = null;


	WebDriver driver = null;	


	By userProfile = By.id("sc_uname");
	///html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[4]/ul[1]/li[3]/a[1]

	By myProfile = By.xpath("//div[@class='hoverContent sc_actLinks']//i[@class='usr_profile']");
	//Address book
	//By addressBook = By.xpath("//a[contains(text(),'My Address Book')]");
	By addressBook = By.linkText("My Address Book");
	//Add new Address
	By addNewAddress = By.linkText("Add New Address"); //("//a[contains(text(),'Add New Address')]");
	By firstName = By.id("tag2");
	By lastName = By.id("tag3");
	By mobileNo = By.id("tag4");
	By pinCode = By.id("tag5");
	By houseNo = By.id("tag6");
	By streetArea = By.id("tag7");
	By chooseState = By.id("chooseState");
	//		state.selectByValue("TN");
	By city = By.name("city");
	By addressType = By.xpath("//label[contains(text(),'Home')]");
	By saveAddress = By.name("dispatch[profiles.update_addressbook]");
	By addAddressSuccessMessage = By.xpath("//div[contains(text(),'Address has been added Successfully.')]");
	By hoverOverAddress =  By.xpath("//li[@class='slick-slide slick-current slick-active']");
	By deleteAddress = By.xpath("//li[@class='slick-slide slick-current slick-active']//a[@id='del_sel_prf']");
	By adressListRightButton = By.xpath("//button[contains(@class,'slick-next slick-arrow')]");

	public MyProfile(WebDriver driver) {
		this.driver = driver;
	}

	public void clickMyProfileAfterLogin() throws Exception {

		System.out.println("before clicking my profile");
		WebDriverWait wait = new WebDriverWait(driver, 5);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(userProfile));
		driver.findElement(userProfile).click();
		element = wait.until(ExpectedConditions.elementToBeClickable(myProfile));
		driver.findElement(myProfile).click();
		System.out.println("After clicking my profile");
	}

	public void clickAddressBook() throws Exception {
		Thread.sleep(10);
		driver.findElement(addressBook).click();
	}

	public void addNewAddress(String fName, String lName, String mobNo,String pCode, String hNo, String street, String city, String state, String addType) throws Exception {
		driver.findElement(addNewAddress).click();
		driver.findElement(firstName).sendKeys(fName);
		driver.findElement(lastName).sendKeys(lName);
		driver.findElement(mobileNo).sendKeys(mobNo);
		driver.findElement(pinCode).sendKeys(pCode);
		Thread.sleep(5000);
		driver.findElement(houseNo).sendKeys(hNo);
		driver.findElement(streetArea).sendKeys(street);
		// Waiting 30 seconds for an element to be present on the page, checking
		// for its presence once every 5 seconds.

		driver.findElement(addressType).click();
		driver.findElement(saveAddress).click();


		System.out.println("new address added");

		actualSuccessMessage = driver.findElement(addAddressSuccessMessage).getText();
		System.out.println("actualSuccessMessage "+actualSuccessMessage);
		expectedSuccessMessage = "Address has been added Successfully.";



	}

	public void deleteSAvedAddress() throws Exception {
		int addressCount = driver.findElements(hoverOverAddress).size();

		while(driver.findElement(adressListRightButton).isDisplayed()==true) {

			Actions actions = new Actions(driver);

			actions.moveToElement(driver.findElement(hoverOverAddress)).perform(); //hover over the address section
			//driver.findElement(hoverOverAddress)
			driver.findElement(deleteAddress).click();
			driver.switchTo().alert().accept();
			driver.findElement(hoverOverAddress);
			System.out.println("number of address "+addressCount);
			System.out.println("address deleted");			
		}


	}
}
