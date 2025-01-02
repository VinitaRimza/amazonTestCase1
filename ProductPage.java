package com.amazon.pages;

import java.time.Duration;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import jdk.internal.org.jline.utils.Log;

public class ProductPage {

    private WebDriver driver;
    public static final Logger Log = Logger.getLogger(CartPage.class);

    // Locators
    private By productBrand = By.cssSelector("span.a-size-base-plus a-color-base");
    private By productTitle = By.cssSelector("span.a-text-normal");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getProductBrand() {
    	String brandcheckbox = driver
				.findElement(By.cssSelector(
						"div[id='p_123-title'] span[class='a-size-base a-color-base puis-bold-weight-text']"))
				.getText();
		//System.out.println(brandcheckbox);
    	Log.info(brandcheckbox);
		driver.findElement(By.cssSelector(
				"a[aria-label='Apply the filter Ben Martin to narrow results'] i[class='a-icon a-icon-checkbox']"))
				.click();
		String filterproductBrand = driver.findElement(By.cssSelector(
				"a[aria-label='Remove the filter Ben Martin to expand results'] span[class='a-size-base a-color-base a-text-bold']"))
				.getText();
		//System.out.println(filterproductBrand);
		Log.info(filterproductBrand);
		String actualProductBrand = driver.findElement(By.cssSelector(
				"div[class='s-widget-container s-spacing-small s-widget-container-height-small celwidget slot=MAIN template=SEARCH_RESULTS widgetId=search-results_2'] span[class='a-size-base-plus a-color-base']"))
				.getText();
		//System.out.println(actualProductBrand);
		//Assert.assertEquals(filterproductBrand, actualProductBrand, "Filter Product Brand Name is not Dispalyed");
       // return driver.findElement(productBrand).getText();
		return filterproductBrand;
    }

    public String getProductTitle() {
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//String expectedSearch = driver.findElement(By.cssSelector("#twotabsearchtextbox")).getAttribute("value");
		//expectedSearch = "\"" + expectedSearch + "\"";
		//System.out.println(expectedSearch);
		String actualSearch = driver.findElement(By.cssSelector("span.a-color-state.a-text-bold")).getText();
		//System.out.println(actualSearch);
	//	Assert.assertEquals(actualSearch, expectedSearch, "The result of search is not same");
       // return driver.findElement(productTitle).getText();
		return actualSearch;
    }
}
