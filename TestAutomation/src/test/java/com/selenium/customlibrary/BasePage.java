package com.selenium.customlibrary;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BasePage {

	public static WebDriver driver;
	public static UtilityLibrary myLib;
	
	@BeforeMethod
	public void setup() {
		myLib = new UtilityLibrary();
		driver = myLib.startBrowser("chrome");
	}

	@AfterMethod
	public void teardown() {
		myLib.customWait(10);
		driver.close();
		driver.quit();
	}
}
