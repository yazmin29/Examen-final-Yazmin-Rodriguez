package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import generics.BasePage;

public class ProductPage extends BasePage {

	@FindBy(name = "Submit")
	private WebElement addToCart;

	@FindBy(xpath = "//span[contains(text(),'There is 1 item in your cart.')]")
	private WebElement successMessage;

	@FindBy(xpath = "//a[@title='Proceed to checkout']")
	private WebElement proceedToCheckout;

	@FindBy(xpath = "//*[@class='exclusive']") // Se creó este elemento para crear el assert que se despluega el boton
												// de agregar
	private WebElement condicionCarro;

	@FindBy(className = "icon-plus") // Se agrego este localizador para incrementar cantidad del producto
	private WebElement plusIcon;

	@FindBy(name = "quantity_1_1_0_0_hidden")
	private WebElement qty;

	@FindBy(id = "layer_cart")
	private WebElement mensajeConfirmacion;

	public ProductPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public String getHeaderTitle(String name) {
		return getTextFromElement(By.id("center_column"));// Se completo este metodo
	}

	public String getCantidadProducto() {

		return getTextFromElement(By.id("summary_products_quantity"));// Se creo este metodo para el assertTrue
	}

	public CheckoutPage addItemToCartNew() {
		clickOnElement(addToCart);
		return new CheckoutPage(driver);
	}

	public boolean addItemToCart() {
		clickOnElement(addToCart);
		return waitForElement(successMessage).isDisplayed();
	}

	public CheckoutPage addItemToCartAndGoToCheckout() {
		clickOnElement(addToCart);
		waitForElement(successMessage);
		clickOnElement(proceedToCheckout);
		return new CheckoutPage(driver);
	}

	public boolean condicionBotonCarro() {// Se creo este metodo para el assertTrue
		return condicionCarro.isDisplayed();

	}

	public boolean condicionMensajeAgregado() {// Se creo este metodo para el assertTrue
		return mensajeConfirmacion.isDisplayed();

	}

}
