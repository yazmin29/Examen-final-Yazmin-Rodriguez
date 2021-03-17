package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import generics.BasePage;

public class CheckoutPage extends BasePage {

	@FindBy(className = "lighter") // Se cambio el xpath absoluto
	private WebElement header;

	@FindBy(xpath = "//p[text()='Your shopping cart is empty.']")
	private WebElement emptyCartMessage;

	@FindBy(className = "ajax_cart_no_product")
	private WebElement emptyCart;// Se creo este elemento para el assert de cartEmpty

	@FindBy(className = "ajax_cart_quantity") // Se creo este elemento para el assert de cartFull
	private WebElement fullCart;

	@FindBy(id = "total_product_price_1_1_0")
	private WebElement productTotalPrice;

	@FindBy(className = "icon-minus") // Se agrego este localizador
	private WebElement minusIcon;

	@FindBy(className = "icon-plus") // Se agrego este localizador
	private WebElement plusIcon;

	@FindBy(className = "icon-trash") // Se cambio este localizador para eliminar un producto del carrito
	private WebElement deleteItem;

	@FindBy(xpath = "//*[@id='product_price_1_1_0']")
	private WebElement unitPrice;

	@FindBy(name = "quantity_1_1_0_0_hidden")
	private WebElement qty;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public String getHeaderTitle() {
		return getTextFromElement(By.xpath("header"));// Se completo el metodo getHeaderTitle
	}

	public boolean removeItem() {
		clickOnElement(deleteItem);// Se agrego un elemento nuevo deleteItem
		return waitForElement(emptyCartMessage).isDisplayed();
	}

	public void reduceQuantityFromCart() {
		clickOnElement(minusIcon);// Se agrego un elemento nuevo minusIcon
	}

	public boolean increaseQuantityFromCart() {
		clickOnElement(plusIcon);// Se agrego un elemento nuevo plusIcon
		return waitForQtyChange("value", "2");
	}

	public String getCurrentProductTotal() {// Se completo el metodo getCurrentProductTotal
		return getTextFromElement(By.id("total_product_price_1_1_0"));
	}

	public String getCurrentUnitPrice() {
		return getTextFromElement(By.id("product_price_1_1_0"));// Se completo el metodo getCurrentUnitPrice
	}

	private boolean waitForQtyChange(String attribute, String value) {
		return waitForElementToChange(qty, attribute, value);
	}

	public boolean condicionCartEmpty() {// Se agrego este metodo para realizar un assertTrue que el carrito esta vacio
		return emptyCart.isDisplayed();

	}

	public boolean condicionCartFull() {// Se agrego este metodo para realizar un assertTrue que el carrito tiene
										// productos
		return fullCart.isDisplayed();

	}

}
