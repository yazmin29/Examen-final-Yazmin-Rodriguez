package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
//import org.openqa.selenium.support.ui.Select;

import generics.BasePage;

public class contact_page extends BasePage {

	@FindBy(xpath = "//p[text()='Your message has been successfully sent to our team.']")
	private WebElement successMessage;

	@FindBy(id = "uniform-id_contact") // Se creo este elemento para activar el select
	private WebElement showSubjectHeadingField;

	@FindBy(xpath = "//*[@value='2']") // Se creo este elemento para seleccionar una opcion
	private WebElement subjectHeadingField;

	@FindBy(id = "email") // Se creo este elemento para enviar el parametro email
	private WebElement emailField;

	@FindBy(id = "id_order") // Se creo este elemento para enviar el parametro order
	private WebElement orderField;

	@FindBy(id = "message") // Se creo este elemento para enviar el parametro message
	private WebElement messageField;

	@FindBy(name = "submitMessage") // Se creo este elemento para enviar los elementos del formulario
	private WebElement btnSubmitMessageField;

	public contact_page(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);// se agregó esta linea de codigo en el contructor
	}

	public contact_page sendEmail(String email, String order, String message) {// se creo este metodo para realizar el
																				// envio de datos del formulario
		clickOnElement(showSubjectHeadingField);
		clickOnElement(subjectHeadingField);
		typeOnElement(emailField, email);
		typeOnElement(orderField, order);
		typeOnElement(messageField, message);
		clickOnElement(btnSubmitMessageField);
		return new contact_page(driver);
	}

	public boolean isMessageDisplayed() {
		return waitForElement(successMessage).isDisplayed();
	}

}
