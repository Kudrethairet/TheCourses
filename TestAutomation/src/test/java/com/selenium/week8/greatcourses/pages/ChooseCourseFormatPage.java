package com.selenium.week8.greatcourses.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.selenium.customlibrary.BasePage;

public class ChooseCourseFormatPage extends BasePage{

	public ChooseCourseFormatPage waitUntilPageLoadComplete()
	{
		WebElement element = myLib.fluentWait(By.id("product-options-wrapper"));
		Assert.assertNotNull(element);		
		return this;
	}	
	
	
	
	
	public ChooseCourseFormatPage chooseCourseFormatType(int typeIndex)
	{
		WebElement parentSection = driver.findElement(By.id("media-format-radio"));
		List<WebElement> typesElems = parentSection.findElements(By.tagName("input"));
		if(typeIndex < 2)
		{
			WebElement targetElem = typesElems.get(typeIndex);
			myLib.clickOnHiddenElement(targetElem);
		}else
		{
			System.out.println("TypeIndex rage is between 0 and 1, you are using: " + typeIndex);
		}
		
		return this;
	}
	
	
	public ChooseCourseFormatPage click_OnAddToCartBtn()
	{
		myLib.clickButton(By.id("add-to-cart-btn"));
		return this;
	}
	
	public ChooseCourseFormatPage verifySuccsessMessage(String expectedMsg)
	{
		WebElement messageElem = driver.findElement(By.id("messages_product_view"));
		List<WebElement> spanElems = messageElem.findElements(By.tagName("span"));
		String actualText = spanElems.get(1).getText();
		Assert.assertEquals(actualText, expectedMsg);	
		return this;
	}
	
	public ChooseCourseFormatPage click_CheckOutNow_button()
	{
		myLib.clickButton(By.id("checkout-now"));
		return this;
	}
	
	
	///////////////////////Helper Methods //////////////////
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
