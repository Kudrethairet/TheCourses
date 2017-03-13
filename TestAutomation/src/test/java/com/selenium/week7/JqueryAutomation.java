package com.selenium.week7;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.selenium.customlibrary.BasePage;

public class JqueryAutomation extends BasePage {

	@Test
	public void mouseHoverOverActionTesting() {
		driver.get("https://jqueryui.com/");
		driver.findElements(By.linkText("Menu")).get(0).click();

		WebElement iframeElem = driver.findElement(By.className("demo-frame"));
		driver.switchTo().frame(iframeElem);

		WebElement menuElem = driver.findElement(By.id("menu"));
		List<WebElement> liElems = menuElem.findElements(By.tagName("li"));
		for (WebElement temp : liElems) {
			if (temp.getText().contains("Electronics")) {
				// move the mouse pointer to this element
				WebElement hifi = driver.findElement(By.cssSelector("ul.ui-menu.ui-widget.ui-widget-content.ui-front"));
				myLib.moveMouseToElement(temp, hifi);
			}
		}
	}
}
