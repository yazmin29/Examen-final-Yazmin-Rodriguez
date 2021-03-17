package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import generics.BasePage;

public class HomePage extends BasePage {

	@FindBy(id = "search_query_top")
	private WebElement searchField;

	@FindBy(className = "ac_results") // Se cambio el xpath por un className
	private List<WebElement> resultList;

	@FindBy(name = "submit_search")
	private WebElement searchButton;

	@FindBy(xpath = "//a[@title='Contact Us']")
	private WebElement contactUsLink;

	public HomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public ProductPage searchAndClick(String name) {
		typeOnElement(searchField, name);
		List<WebElement> results = getElements(resultList);
		for (WebElement webElement : results) {
			if (webElement.getText().contains(name)) {
				webElement.click();
				break;
			}
		}
		return new ProductPage(driver);
	}

	public ResultsPage searchFor(String name) {
		typeOnElement(searchField, name);
		clickOnElement(searchButton);
		return new ResultsPage(driver);
	}

	public contact_page goToContact() {
		clickOnElement(contactUsLink);
		return new contact_page(driver);
	}

}
