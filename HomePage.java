package com.amazon.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
	private WebDriver driver;

	// Locators
	private By searchBox = By.cssSelector("#twotabsearchtextbox");
	private By searchButton = By.cssSelector("#nav-search-submit-button");
	private By suggestionsList = By.cssSelector("div.two-pane-results-container>div>div");


       public void openAmazonHomePage(String url) {
        driver.get(url);
        driver.manage().window().maximize();
    }

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	public void enterSearchText(String searchText) {
		WebElement searchBoxElement = driver.findElement(searchBox);
		searchBoxElement.sendKeys(searchText);
	}

	public void clickSearchButton() {
		driver.findElement(searchButton).click();
	}

	public boolean isSearchTextInSuggestions(String expectedText) {
		List<WebElement> suggestions = driver.findElements(suggestionsList);
		for (WebElement suggestion : suggestions) {
			if (suggestion.getText().toLowerCase().contains(expectedText.toLowerCase())) {
				return true;
			}
		}
		return false;
	}
}
