package pages;

//Se elimino la importacion de DateTimeFormatter
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import generics.BasePage;

public class ResultsPage extends BasePage {

	public ResultsPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public String getHeaderTitle(String name) {
		return getTextFromElement(By.xpath("//span[contains(text(),'" + name + "')]"));
	}

	public void helloWorld() {
		System.out.println("This should not be here " + new Date());
	}

}
