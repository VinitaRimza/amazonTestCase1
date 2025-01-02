package com.amazon.steps;

import com.amazon.pages.HomePage;
import com.amazon.pages.ProductPage;
import com.amazon.pages.CartPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.testng.Assert.assertEquals;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SearchSteps {

    private static final Logger logger = LogManager.getLogger(SearchSteps.class);
    private WebDriver driver;
    private HomePage homePage;
    private ProductPage productPage;
    private CartPage cartPage;

    @Given("I am on the Amazon homepage")
    public void i_am_on_the_amazon_homepage() {
        logger.info("Opening Amazon homepage");
        driver = new EdgeDriver();
        driver.get("https://www.amazon.in/");
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
    }
    
    @Given("I have added a product to the cart")
    public void i_have_added_a_product_to_the_cart() {
    	logger.info("Adding product to cart");
    	cartPage = new CartPage(driver);
    	cartPage.addProductIntoTheCart();
          }

    @When("I search for {string}")
    public void i_search_for(String searchText) {
        logger.info("Searching for product: " + searchText);
        homePage.enterSearchText(searchText);
        homePage.clickSearchButton();
        productPage = new ProductPage(driver);
    }
    
    @When("I search for only {string}")
    public void i_search_for_only (String searchText) {
        logger.info("Searching for product: " + searchText);
        homePage.enterSearchText(searchText);
    }
    
    @Then("the search suggestions should contain {string}")
    public void the_search_suggestions_should_contain(String searchText) {
        boolean isTextInSuggestions = homePage.isSearchTextInSuggestions(searchText);
        Assert.assertTrue(isTextInSuggestions, "The search suggestions do not contain the expected text!");
        driver.quit();
    }

    @Then("the search results should display {string}")
    public void the_search_results_should_display(String expectedProduct) {
        logger.info("Verifying search result");
        String actualProduct = productPage.getProductTitle();
        expectedProduct = "\"" + expectedProduct + "\"";
        assertEquals(actualProduct, expectedProduct, "Search result mismatch!");
        driver.quit();
    }

    @When("I apply the {string} filter")
    public void i_apply_the_filter(String brand) {
        logger.info("Applying filter for brand: " + brand);
        // Logic to apply the filter (you'll need to implement this step)
        
    }

    @Then("the displayed product should be from the {string} brand")
    public void the_displayed_product_should_be_from_the_brand(String expectedBrand) {
        logger.info("Verifying brand of displayed product");
        String actualBrand = productPage.getProductBrand();
        assertEquals(actualBrand, expectedBrand, "Brand mismatch!");
        driver.quit();
    }

    @Then("I should see {string} message")
    public void i_should_see_message(String expectedMessage) {
        logger.info("Verifying message: " + expectedMessage);
        // Logic to verify no results message (you'll need to implement this step)
        driver.quit();
    }

    @When("I add the product to the cart")
    public void i_add_the_product_to_the_cart() {
        logger.info("Adding product to cart");
        // Logic for adding product to cart (you'll need to implement this step)
        
    }

    @Then("the cart should contain {int} item")
    public void the_cart_should_contain_item(int expectedCount) {
        logger.info("Verifying cart count");
        cartPage = new CartPage(driver);
        int actualCount = Integer.parseInt(cartPage.getCartCount());
        assertEquals(actualCount, expectedCount, "Cart count mismatch!");
        driver.quit();
    }

    @When("I remove the product from the cart")
    public void i_remove_the_product_from_the_cart() {
        logger.info("Removing product from cart");
       int removedCartCount = cartPage.removeProductFromCart();
        Assert.assertEquals(removedCartCount, 0, "Item is removed");
    }

    @Then("the cart should be empty")
    public void the_cart_should_be_empty() {
        logger.info("Verifying cart is empty");
        int actualCount = Integer.parseInt(cartPage.getCartCount());
        assertEquals(actualCount, 0, "Cart is not empty!");
        driver.quit();
    }
}

