package com.amazon.steps;

import com.amazon.pages.*;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

public class AmazonSteps {
	WebDriver driver = new EdgeDriver();
	HomePage homePage = new HomePage(driver);
	LoginPage loginPage = new LoginPage(driver);
	// SearchPage searchPage = new SearchPage(driver);
	// CartPage cartPage = new CartPage(driver);

	@Given("User is on the Amazon home page")
	public void userIsOnTheAmazonHomePage() {
		homePage.openAmazonHomePage("https://www.amazon.in");
	}

	@When("User hovers over {string} and clicks {string}")
	public void userHoversOverAndClicks(String account, String signIn) {
		loginPage.hoverOverAccountAndClickSignIn();
	}

	@And("User logs in with email {string} and password {string}")
	public void userLogsInWithEmailAndPassword(String email, String password) {
		loginPage.login(email, password);
	}

	@Then("I should see the homepage URL as {string}")
	public void i_should_see_the_homepage_url_as(String URL) {
		loginPage.validateURL(URL);
	}

	@Then("The Amazon logo should be displayed")
	public void the_amazon_logo_should_be_displayed() {
		loginPage.validateLogo();
		driver.quit();
	}

	@When("User logs in with emailMobileNo {string}")
	public void user_logs_in_with_emailMobileNo(String email) {
		loginPage.login(email);
	}

	@Then("user see the Error Login Message")
	public void user_see_the_error_login_message() {
		loginPage.emailErrorMessage();
		driver.quit();
	}
	
	@Then("user see the MobileNoError Login Message")
	public void user_see_the_MobileNoError_Login_Message() {
		loginPage.mobileErrorMesssage();
		driver.quit();
	}

	/*
	 * @And("User searches for {string}") public void userSearchesFor(String
	 * product) { homePage.searchProduct(product); }
	 * 
	 * 
	 * 
	 * @Then("The cart should have {string} item") public void
	 * theCartShouldHaveItem(String expectedCount) { String actualCount =
	 * cartPage.getCartCount(); Assert.assertEquals(actualCount, expectedCount,
	 * "Cart count did not match!"); driver.quit(); }
	 */
}
