package com.selenium.week5;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.selenium.customlibrary.UtilityLibrary;

public class BrowserCommandsTests {

	private WebDriver driver;
	private UtilityLibrary myLib;

	@BeforeMethod
	public void setup() {
		myLib = new UtilityLibrary();
		driver = myLib.startBrowser("chrome");
	}

	@AfterMethod
	public void teardown() throws Exception {
		Thread.sleep(10 * 1000);
		driver.close();
		driver.quit();
	}

	@Test(enabled = false)
	public void learn_Browser_Commands() throws Exception {
		String mySiteTitle = driver.getTitle();
		System.out.println("Title text: " + mySiteTitle);

		String mySiteCurrentURL = driver.getCurrentUrl();
		System.out.println("Current url: " + mySiteCurrentURL);

		driver.navigate().refresh(); // This refresh/reload the web-site

		driver.navigate().to("http://www.walmart.com/");
		Thread.sleep(10 * 1000);

		driver.navigate().back(); // go back to previous page

		driver.navigate().forward(); // go to next site, prerequisite, you have
										// the visit the other web-site
	}

	@Test(enabled = false)
	public void learn_CheckBox_RadioButton_Testing() throws Exception {
		driver.get("http://www.nngroup.com/articles/checkboxes-vs-radio-buttons/");
		myLib.selectCheckBox(By.name("permission"), true);

		myLib.selectCheckBox(By.name("discardinfo"), true);

		myLib.selectCheckBox(By.id("three"), false);
	}

	@Test
	public void learn_ExpliciteWait_Testing() {
		driver.get("http://www.amazon.com/");
		WebElement cartElem = driver.findElement(By.cssSelector("#nav-cart"));
		cartElem.click();

		// need to have Explicit wait here to dynamically wait until cart page
		// load is complete
		WebElement myDynamicElement = (new WebDriverWait(driver, 10))
				.until(ExpectedConditions.presenceOfElementLocated(By.
						cssSelector(".a-row.sc-cart-header>h1")));
		
		System.out.println("Text: "+ myDynamicElement.getText());
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
