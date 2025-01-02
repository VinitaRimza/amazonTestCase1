package com.amazon.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static org.testng.Assert.assertTrue;

import java.time.Duration;

public class LoginPage {
	WebDriver driver;
	WebDriverWait wait;
	public String actualurl;
	public static final Logger Log = Logger.getLogger(LoginPage.class);
	

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}

	public void hoverOverAccountAndClickSignIn() {
		WebElement accountList = driver.findElement(By.cssSelector("#nav-link-accountList"));
		new org.openqa.selenium.interactions.Actions(driver).moveToElement(accountList).perform();
		WebElement signInButton = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("#nav-flyout-ya-signin .nav-action-inner")));
		signInButton.click();
	}

	public void login(String email, String password) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#ap_email"))).sendKeys(email);
		driver.findElement(By.cssSelector(".a-button-input")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#ap_password"))).sendKeys(password);
		driver.findElement(By.cssSelector("#signInSubmit")).click();
		actualurl = driver.getCurrentUrl();
		//System.out.println(actualurl);
		Log.info(actualurl);
	}

	public void login(String emailMobileNo) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#ap_email"))).sendKeys(emailMobileNo);
		driver.findElement(By.cssSelector(".a-button-input")).click();
	}

	public void emailErrorMessage() 
	{
		String ExpectedAlertMessage = "Wrong or Invalid email address or mobile phone number. Please correct and try again.";
		String AlertMessage = driver
				.findElement(By.cssSelector("div[id='auth-email-invalid-claim-alert'] div[class='a-alert-content']"))
				.getText();
		assertTrue(AlertMessage.equals(ExpectedAlertMessage), "Alert Message is proper");
	//	System.out.println("Invalid EmailId At the time of SignIn Alert Message is:: " + AlertMessage);
		Log.info(AlertMessage);
		driver.findElement(By.cssSelector("#ap_email")).clear();
		
				
	}
	
	public void mobileErrorMesssage()
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.cssSelector("div[id='auth-error-message-box'] h4[class='a-alert-heading']")));
		String ActualContentAlertMessage = driver
				.findElement(By.cssSelector("div[id='auth-error-message-box'] h4[class='a-alert-heading']")).getText();

		assertTrue(ActualContentAlertMessage.equals("Incorrect phone number"), "Alert Message for Mobile is proper");
	//	System.out.println("Invalid MobileNo At the time of SignIn Alert Message is:: " + ActualContentAlertMessage);
		Log.info(ActualContentAlertMessage);
		driver.findElement(By.cssSelector("#ap_email")).clear();
	}
	

	public void validateURL(String URL) {
		String expectedurl = URL;
		Assert.assertEquals(actualurl, expectedurl);
		String msg = "It is same as expected URL:Login Successful";
		//System.out.println("It is same as expected URL:Login Successful) ");
		Log.info(msg);
	}

	public void validateLogo() {
		boolean flag = true;
		WebElement ele = driver.findElement(By.cssSelector("#nav-logo-sprites"));

		if (flag) {
			flag = ele.isDisplayed();
			System.out.println("Flag value::" + flag);
			if (flag) {
				System.out.println("The Amazon Logo is Displayed");
			} else {
				System.out.println("The Amazon Logo is  not Displayed");
			}
		}
	}
}
