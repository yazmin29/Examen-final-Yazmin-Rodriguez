package tests;

import static org.testng.Assert.assertTrue;
import java.text.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import generics.BaseTest;
import pages.CheckoutPage;
import pages.contact_page;
import pages.HomePage;
import pages.ProductPage;

public class Tests_Search extends BaseTest {

	/**
	 * Este test realiza una búsqueda básica y confirma que lo que se muestra en el
	 * encabezado corresponde al criterio de búsqueda.
	 */

	@Test
	public void testValidSearch() {
		String name = "Blouse";// Nombre que producto a buscar.

		HomePage home = new HomePage(driver);// Instancia de la clase HomePage.

		ProductPage product = home.searchAndClick(name);// Instancia de la clase ProductPage e invocacion del metodo
														// searchAndClick.

		assertTrue(product.getHeaderTitle(name).contains(name));// Comparacion que los elementos son iguales.
		Assert.assertTrue(product.condicionBotonCarro());// Condicion que este visible el boton de agregar al carrito.
	}

	/**
	 * Este test realiza una búsqueda de un producto y redirecciona automáticamente
	 * al producto encontrado, luego confirma que lo que se muestra en el encabezado
	 * corresponde al criterio de búsqueda.
	 */

	@Test
	public void testSearchAndGo() {
		String name = "shirt";// Nombre que producto a buscar.

		HomePage home = new HomePage(driver);// Instancia de la clase HomePage.

		ProductPage product = home.searchAndClick(name);// Instancia de la clase ProductPage e invocacion del metodo
														// searchAndClick.

		assertTrue(product.getHeaderTitle(name).contains(name));// Comparacion que los elementos son iguales.
		Assert.assertTrue(product.condicionBotonCarro()); // Se agrego un nuevo criterio de aceptacion
	}

	/**
	 * Este test realiza una búsqueda de un producto, lo abre y lo agrega al
	 * carrito.
	 */

	@Test
	public void testAddToCart() {
		String name = "shirt";// Nombre que producto a buscar.
		HomePage home = new HomePage(driver);// Instancia de la clase HomePage.
		ProductPage product = home.searchAndClick(name);// Instancia de la clase ProductPage e invocacion del metodo
														// searchAndClick.
		product.addItemToCart();// Invocacion del metodo para agregar al carrito
		assertTrue(product.addItemToCart());// Condicion que le metodo addItemToCart se ejecuto
		Assert.assertTrue(product.condicionMensajeAgregado());// Confirma que el producto fue agregado al carrito
	}

	/**
	 * Este test realiza una búsqueda de un producto, lo abre y lo agrega al carrito
	 * y una vez agreado lo remueve.
	 */

	@Test
	public void testRemoveFromCart() {
		String name = "shirt";// Nombre que producto a buscar.
		HomePage home = new HomePage(driver);// Instancia de la clase HomePage.
		ProductPage product = home.searchAndClick(name);// Instancia de la clase ProductPage e invocacion del metodo
														// para realizar la busqueda.
		CheckoutPage checkout = product.addItemToCartAndGoToCheckout();// Instancia de la clase CheckoutPage e
																		// invocacion del metodo para agregar al
																		// carrito.
		checkout.removeItem();// Invocacion del metodo para eliminar el producto del carrito
		assertTrue(checkout.removeItem());// Condicion que se remueva el producto
		assertTrue(checkout.condicionCartEmpty());// Condicion que el carrito este vacio
	}

	/**
	 * Este test realiza una búsqueda de un producto, lo abre y lo agrega al carrito
	 * y una vez agreado incremente la cantidad de productos y confirme que hay más
	 * de 1.
	 */

	@Test
	public void testAddMoreItemsToCart() {

		String name = "shirt";// Nombre que producto a buscar.
		double cantidad = 1;// Declaracion de la variable cantidad para realizar el assertTrue.
		HomePage home = new HomePage(driver);// Instancia de la clase HomePage.
		ProductPage product = home.searchAndClick(name);// Instancia de la clase ProductPage e invocacion del metodo
														// searchAndClick.
		CheckoutPage checkout = product.addItemToCartAndGoToCheckout();// Instancia de la clase CheckoutPage e
																		// invocacion del metodo
																		// addItemToCartAndGoToCheckout.
		checkout.increaseQuantityFromCart();// Invocacion del metodo para incrementar la cantidad de producto a 2.
		double canti = Double.valueOf(product.getCantidadProducto().substring(0, 1));// Conversion del String extraido a
																						// double
		Assert.assertTrue(checkout.condicionCartFull());// Confirma que el carrito tiene productos
		assertTrue(cantidad < canti);// Comparar que el String extraido es mayor que 1

	}

	/*
	 */
	/**
	 * Este test permite validarq ue se envía un mensaje exitosamente.
	 */

	@Test
	public void testContactUs() {

		String email = "yazmin@hotmail.com";// Email al que se quiere enviar el mensaje
		String order = "Camisa Roja"; // La orden que se quiere enviar en el mensaje
		String message = "Me gutaría ver prendas más modernas";// Mensaje a enviar
		HomePage home = new HomePage(driver);// Instancia de la clase HomePage.
		contact_page contact = home.goToContact();// Instancia de la clase contact_page e invocacion del metodo para
													// redirigir a la pagina de contacto.
		contact = contact.sendEmail(email, order, message);// Invocacion del metodo para enviar el mensaje

		assertTrue(contact.isMessageDisplayed());// Confirmacion que se despliegue el mensaje que el mensaje fue enviado
													// exitosamente
	}

	/**
	 * Este test permite confirmar que el precio del producto se actualiza basado en
	 * el incremento/deducción de la cantidad de producto.
	 */

	@Test
	public void Test_Adding_Quantity() throws ParseException {
		String name = "shirt";// Se cambio la variable a minuscula
		HomePage home = new HomePage(driver);
		ProductPage product = home.searchAndClick(name);
		CheckoutPage checkout = product.addItemToCartAndGoToCheckout();

		double currentAmount = Double.valueOf(checkout.getCurrentProductTotal().substring(1));// Se cambio la variable a
																								// minuscula
		double unitPrice = Double.valueOf(checkout.getCurrentUnitPrice().substring(1));
		double expectedTotal = currentAmount + unitPrice;

		checkout.increaseQuantityFromCart();
		double newTotal = Double.valueOf(checkout.getCurrentProductTotal().substring(1));

		assertTrue(newTotal == expectedTotal);
		Assert.assertTrue(checkout.condicionCartFull());// Confirma que el carrito tiene productos
	}

}
