package com.selenium.week8.greatcourses.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.selenium.customlibrary.BasePage;

public class CheckoutProgressPage extends BasePage{

	public CheckoutProgressPage waitUntilPageLoadComplete()
	{
		WebElement element = myLib.fluentWait(By.cssSelector("#login-email"));
		Assert.assertNotNull(element);		
		return this;
	}	
	
	public CheckoutProgressPage enterEmailAddress(String emailString) {
		myLib.enterTextField(By.id("login-email"), emailString);
		
		return this;
	}
	
	public CheckoutProgressPage select_NoYes_RadioButton(int radioBtnIndex)
	{
		WebElement radioBtn = driver.findElements(By.cssSelector(".control_input_cont>label")).get(radioBtnIndex);
		myLib.clickButton(radioBtn);
		return this;
	}
	
	public CheckoutProgressPage click_ContinueButton()
	{
		myLib.clickButton(By.cssSelector("#checkout-sigin"));
		waitUntilBillingAddressDisplay();
		return this;
	}
	
	public CheckoutProgressPage complete_BillingAddress(String firstName, String lastName, 
			String address, String city, String state, String zipcode, String password)
	{
		myLib.enterTextField(By.id("billing:firstname"), firstName);
		myLib.enterTextField(By.id("billing:lastname"), lastName);
		myLib.enterTextField(By.id("billing:street1"), address);
		myLib.enterTextField(By.id("billing:city"), city);
		myLib.selectDropDown(By.id("billing:region_id"), state);
		myLib.enterTextField(By.id("billing:postcode"), zipcode);
		myLib.enterTextField(By.id("billing:customer_password"), password);
		myLib.enterTextField(By.id("billing:confirm_password"), password);

		WebElement continueBtn = driver.findElement(By.id("billing-buttons-container"));
		WebElement btn = continueBtn.findElement(By.tagName("button"));
		myLib.clickButton(btn);
	
		return this;
	}
	
	public CheckoutProgressPage complete_CreditCardInfo(String creditCardNo, String month, String year)
	{
		WebElement frame = myLib.explicitWaitForCondition(By.id("pbridge_iframe"));
		myLib.switchToIframe(By.id("pbridge_iframe"));
		
		myLib.enterTextField(By.id("cc_number_paymentech"), creditCardNo);
		myLib.selectDropDown(By.id("cc_expiration_month_paymentech"), month);
		myLib.selectDropDown(By.id("cc_expiration_year_paymentech"), year);
		// click  continue button
		myLib.switchToDefault();
		
		return this;
	}
	
	
	
	private void waitUntilBillingAddressDisplay()
	{
		WebElement elem = myLib.explicitWaitForCondition(By.id("billing:firstname"));
		Assert.assertNotNull(elem);		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
