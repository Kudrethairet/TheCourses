package com.selenium.week6;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.selenium.customlibrary.UtilityLibrary;

public class UnitedAutomationTests {

	private WebDriver driver;
	private UtilityLibrary myLib;

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

	@Test
	public void searchTicket() {
		driver.get("http://www.united.com/");

		WebElement navTabParent = driver.findElement(By.className("tab-container"));
		List<WebElement> childrenElems = navTabParent.findElements(By.tagName("a"));
		for (WebElement temp : childrenElems) {
			String elemTxt = temp.getText();
			System.out.println("nav tabs text: " + elemTxt);
			if (elemTxt.contains("Hotel")) {
				// temp.click();
				myLib.clickButton(temp);
				break;
			}
		}
		myLib.customWait(2);
		for (WebElement temp : childrenElems) {
			String elemTxt = temp.getText();
			System.out.println("nav tabs text: " + elemTxt);
			if (elemTxt.contains("Flight")) {
				// temp.click();
				myLib.clickButton(temp);
				break;
			}
		}

		myLib.clickButton(By.cssSelector("#SearchTypeMain_oneWay"));
		myLib.customWait(2);
		myLib.selectCheckBox(By.id("SearchTypeMain_roundTrip"), true);

		myLib.enterTextField(By.id("Origin"), "IAD");
		myLib.customWait(1);
		WebElement fromResult = driver.findElement(By.cssSelector(".tt-suggestion.tt-selectable"));
		fromResult.click();

		myLib.enterTextField(By.id("Destination"), "Urumqi");
		myLib.customWait(1);
		List<WebElement> Results = driver.findElements(By.cssSelector(".tt-suggestion.tt-selectable"));
		for (WebElement temp : Results) {
			System.out.println("Text: " + temp.getText());
			if (temp.getText().contains("Urumqi")) {
				temp.click();
				break;
			}
		}

		// select departure date
		myLib.enterTextField(By.id("DepartDate"), "01/01/2017");
		myLib.enterTextField(By.id("ReturnDate"), "03/01/2017");
		moveMouseAway();

		clearPreselectedTravelers(); // clearing preselected values before
										// selecting new value
		selectTravelTypeNumber("Adults (18-64)", 2); // Adult
		selectTravelTypeNumber("Seniors (65+)", 1); // Senior
		selectTravelTypeNumber("Children (5-11)", 3); // Children

		myLib.customWait(3);

		// select cabin type
		myLib.selectDropDown(By.id("cabinType"), "Business or First");
		myLib.customWait(2);
		myLib.selectDropDown(By.id("cabinType"), "Economy");

		// select non-stop check-box
		myLib.selectCheckBox(By.id("NonStopOnly"), true);

		// click on 'search' button
		myLib.clickButton(By.id("flightBookingSubmit"));

		// Dynamically wait an element on the result page
		WebElement resultElem = myLib.fluentWait(By.className("flight-block-summary-container"));
		if (resultElem != null) {
			Assert.assertEquals(true, true);
		} else {
			Assert.assertEquals(true, false);
		}

		String image = myLib.captureScreenshot("Cheap-Ticket-United-IAD-to-Urumqi", "target/images/");
		System.out.println("image location: " + image);

	}
	
	
	/* ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
	/////////////////////// Private helper methods section ////////////////////
	/* ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/

	private void moveMouseAway() {
		driver.findElement(By.id("ui-id-2")).click();
	}

	private void clearPreselectedTravelers() {
		WebElement travelorSelectElem = driver.findElement(By.id("travelers-selector"));
		travelorSelectElem.click();
		myLib.customWait(1);

		WebElement dropdownElems = driver.findElement(By.id("travelers-select"));
		List<WebElement> stepperElems = dropdownElems.findElements(By.className("stepper-wrap"));
		for (WebElement temp : stepperElems) {
			List<WebElement> icons = temp.findElements(By.tagName("a"));
			WebElement minusIcon = icons.get(0);
			String classText = minusIcon.getAttribute("class");

			do {
				// System.out.println("before Text: " + classText);
				minusIcon.click();
				myLib.customWait(1);
				classText = minusIcon.getAttribute("class");
				// System.out.println("after Text: " + classText);
			} while (!classText.contains("disabled"));

		}
		moveMouseAway();
		myLib.customWait(1);
	}

	private void selectTravelTypeNumber(String travelerType, int travelerNumber) {
		// we are skipping travelers selector
		WebElement travelorSelectElem = driver.findElement(By.id("travelers-selector"));
		travelorSelectElem.click();
		myLib.customWait(1);

		// locate all the values in the drop-down
		WebElement dropdownElems = driver.findElement(By.id("travelers-select"));

		List<WebElement> stepperElems = dropdownElems.findElements(By.className("stepper-wrap"));
		for (WebElement temp : stepperElems) {
			if (temp.getText().contains(travelerType)) {
				List<WebElement> icons = temp.findElements(By.tagName("a"));
				for (int i = 0; i < travelerNumber; i++) {
					icons.get(1).click();
					myLib.customWait(1);
				}
				moveMouseAway();
				myLib.customWait(1);
				break;
			}
		}
	}

}
