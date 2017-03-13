package com.selenium.week4;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.selenium.customlibrary.UtilityLibrary;

public class MorgageCalculatorTest {

	private WebDriver driver;
	private UtilityLibrary myLib;

	@BeforeMethod
	public void setup() {
		myLib = new UtilityLibrary();
		driver = myLib.startBrowser("chrome");

		// Go to url
		driver.get("http://www.mortgagecalculator.org/");
	}

	@AfterMethod
	public void teardown() {
		driver.close(); // close the browser but WebDriver object is still alive
		driver.quit(); // kills the WebDriver object and it's value is equal to
						// 'null'
	}
	
	@Test(enabled=false)
	public void testing_MorgageCalculator_Website_Title() {
		// Step1: get the page title and print it in console
		String websiteTitle = driver.getTitle();
		System.out.println("Title: " + websiteTitle);

		// Validation or Assertion in java
		Assert.assertEquals(websiteTitle, "Mortgage Calculator");
	}

	@Test
	public void testing_MorgageCalculator() {
		try {
			//Step1:
			// locate home-value text-field WebElement
			myLib.enterTextField(By.name("param[homevalue]"), "1000000");			
			
			//Step2:
			// locate loan-amount text-field WebElement
			myLib.enterTextField(By.id("loanamt"), "800000");
						
			//Step3:
			// locate interest rate text-box WebElement
			myLib.enterTextField(By.cssSelector("#intrstsrate"), "3.7");
			
			//Step4:
			myLib.enterTextField(By.xpath(".//*[@id='loanterm']"), "15");
						
			//Step5:
			// locate month drop-down WebElement
			myLib.selectDropDown(By.name("param[start_month]"), "Jan");
			
			//Step6:
			// locate year drop-down WebElement
			myLib.selectDropDown(By.name("param[start_year]"), "2017");
			
			//Step7:
			// locate property tax text-box WebElement
			myLib.enterTextField(By.cssSelector("#pptytax"), "2");

			//Step8:
			// locate PMI text-box WebElement
			myLib.enterTextField(By.cssSelector("#pmi"), "1.5");

			//Step9:
			// locate home-ins text-box WebElement
			myLib.enterTextField(By.cssSelector("#hoi"), "500");		
			
			//Step10:
			// locate monthly HOA text-box WebElement
			myLib.enterTextField(By.cssSelector("#hoa"), "65");			
			
			//Step11:
			// locate calculate button and click it
			myLib.clickButton(By.className("styled-button"));
			
			//wait some time to allow web-site to reload with latest data
			Thread.sleep(8 * 1000);
			
			//Step12:
			// locate 'Payment with PMI' result and validate
			List<WebElement> resultsElems = driver.findElements(By.cssSelector(".left-cell>h3"));
			WebElement resultPMI = resultsElems.get(0);
			resultPMI.click(); //This line move the screen view focus to the result page
			String resultText = resultPMI.getText();
			System.out.println("result info: " + resultText);
			Assert.assertEquals(resultText, "$7,571.29");
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			Thread.sleep(15 * 1000); // pause the test execution for 10 seconds
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
