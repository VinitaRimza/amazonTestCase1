package com.amazon.pages;

import java.time.Duration;

import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CartPage {

	private WebDriver driver;
	public static final Logger Log = Logger.getLogger(CartPage.class);

	// Locators
	private By cartCount = By.cssSelector("#nav-cart-count");
	private By removeButton = By.cssSelector("span.a-icon.a-icon-small-trash");

	public CartPage(WebDriver driver) {
		this.driver = driver;
	}

	public String getCartCount() {
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.cssSelector(
				"#search  .s-main-slot div:nth-child(8)>div>div>div>div>span>div>div>div:nth-child(2)>div:nth-child(2)>a"))
				.click();
		String parentWindow = driver.getWindowHandle();
		Set<String> allWindows = driver.getWindowHandles();
		for (String window : allWindows) {
			if (!window.equals(parentWindow)) {
				driver.switchTo().window(window);
				break;
			}
		}
		driver.findElement(By.cssSelector("input#add-to-cart-button")).click();
		String actualCountOfCart = driver.findElement(By.cssSelector("#nav-cart-count")).getText();
		Log.info(actualCountOfCart);
		int cartCount = Integer.parseInt(actualCountOfCart);
		// Assert.assertEquals(cartCount, 1, "Cart count is not as expected!");

		// return driver.findElement(cartCount).getText();
		return actualCountOfCart;
	}

	public int removeProductFromCart() {
		String actualCountOfCart = driver.findElement(By.cssSelector("#nav-cart-count")).getText();
		// System.out.println(actualCountOfCart);
		Log.info(actualCountOfCart);
		driver.findElement(By.cssSelector("a[href=\"/gp/cart/view.html?ref_=nav_cart\"]")).click();
		driver.findElement(By.cssSelector("span.a-icon.a-icon-small-trash")).click();
		String removedCountOfCart = driver.findElement(By.cssSelector("#nav-cart-count")).getText();
		int removedCartCount = Integer.parseInt(removedCountOfCart);
		return removedCartCount;
	//	Assert.assertEquals(removedCartCount, 0, "Item is removed");
	//	driver.quit();

		// System.out.println(removedCountOfCart);
		// int cartCount = Integer.parseInt(actualCountOfCart);
		// int removedCartCount = Integer.parseInt(removedCountOfCart);
		// Assert.assertEquals(removedCartCount, 0, "Item is removed");
		// driver.findElement(removeButton).click();
	}

	public void addProductIntoTheCart() {
		WebElement searchBox = driver.findElement(By.cssSelector("#twotabsearchtextbox"));
		searchBox.sendKeys("Tshirt for women");
		driver.findElement(By.cssSelector("input#nav-search-submit-button")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		driver.findElement(By.cssSelector(
				"#search  .s-main-slot div:nth-child(8)>div>div>div>div>span>div>div>div:nth-child(2)>div:nth-child(2)>a"))
				.click();
		String parentWindow = driver.getWindowHandle();
		Set<String> allWindows = driver.getWindowHandles();
		for (String window : allWindows) {
			if (!window.equals(parentWindow)) {
				driver.switchTo().window(window);
				break;
			}
		}
		driver.findElement(By.cssSelector("input#add-to-cart-button")).click();

	}
}
