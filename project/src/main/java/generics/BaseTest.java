package generics;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

	protected WebDriver driver;
	// Se eliminó la instancia waitHere;

	@BeforeMethod
	public void setup() {
		String url = "http://automationpractice.com/";
		System.setProperty("webdriver.chrome.driver", "C:/WebDriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
