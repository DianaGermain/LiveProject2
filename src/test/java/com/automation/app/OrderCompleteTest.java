package com.automation.app;

import java.io.IOException;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.ExtentManager;
import base.Hooks;
import pageObjects.Homepage;
import pageObjects.OrderFormDelivery;
import pageObjects.OrderFormPayment;
import pageObjects.OrderFormPersInfo;
import pageObjects.OrderFormShippingMethod;
import pageObjects.ShopContentPanel;
import pageObjects.ShopHomepage;
import pageObjects.ShopProductPage;
import pageObjects.ShoppingCart;

@Listeners(resources.Listeners.class)  //annotation to use ITestListener 
public class OrderCompleteTest extends Hooks {

	public OrderCompleteTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}


	@Test
	public void endToEndTest() throws InterruptedException, IOException {
		
		ExtentManager.log("Starting Order Complete Test...");

		
		Homepage home = new Homepage(); // now we can interact with elements from the homepage
		ExtentManager.pass("Have successfully reached store homepage");
		
		Thread.sleep(3000);
		// handles cookie prompt
		home.getCookie().click();
		Thread.sleep(3000);
		
		home.getTestStoreLink().click();
		
		ShopHomepage shopHome = new ShopHomepage();
		shopHome.getProdOne().click();
		ExtentManager.pass("Have successfully clicked on product");
		
		ShopProductPage shopProd = new ShopProductPage();
		ExtentManager.pass("Have successfully reached shop productpage");
		
		Select option = new Select(shopProd.getSizeOption());
		option.selectByVisibleText("M");
		ExtentManager.pass("Have successfully increased quantity");
		
		
		shopProd.getQuantIncrease().click();
		ExtentManager.pass("Have successfully increased quantity");
		
		shopProd.getAddToCartBtn().click();
		Thread.sleep(3000);
		ExtentManager.pass("Have successfully added item to cart");

		
		ShopContentPanel cPanel = new ShopContentPanel();
		Thread.sleep(3000);
		
		cPanel.getcheckoutBtn().click();
		Thread.sleep(3000);

		ShoppingCart cart = new ShoppingCart();
		ExtentManager.pass("Have successfully reached shopping cart page");
		
		Thread.sleep(3000);
		cart.getHavePromo().click();
		ExtentManager.pass("Have successfully selected promo button");
		
		cart.getPromoTextbox().sendKeys("20OFF");
		cart.getPromoAddBtn().click();
		cart.getProceedCheckoutBtn().click();
		ExtentManager.pass("Have successfully clicked checkout button");
	
		OrderFormPersInfo pInfo = new OrderFormPersInfo();
		pInfo.getGenderMr().click();
		pInfo.getFirstNameField().sendKeys("John");
		pInfo.getLastnameField().sendKeys("Smith");
		pInfo.getEmailField().sendKeys("johnsmith@test.com");
		pInfo.getTermsConditionsCheckbox().click();
		Thread.sleep(3000);
		pInfo.getContinueBtn().click();
		ExtentManager.pass("Have successfully entered customer details");
		
		OrderFormDelivery orderDelivery = new OrderFormDelivery();
		orderDelivery.getAddressField().sendKeys("123 Main St");
		orderDelivery.getCityField().sendKeys("Houston");
		Select state = new Select(orderDelivery.getStateDropdown());
		state.selectByVisibleText("Texas");
		orderDelivery.getZipcodeField().sendKeys("77021");
		Thread.sleep(3000);
		ExtentManager.pass("Have successfully entered shipping address details");
		
		orderDelivery.getContinueBtn().click();
		
		OrderFormShippingMethod shipMethod = new OrderFormShippingMethod();
		Thread.sleep(3000);

		shipMethod.getDeliveryMsgTextbox().sendKeys("If I am not in, please leave delivery on porch");
		Thread.sleep(3000);
		shipMethod.getContinueBtn().click();
		ExtentManager.pass("Have successfully entered shipping delivery message details");
	
		OrderFormPayment orderPay = new OrderFormPayment();
		orderPay.getPayByCheckRadioBtn().click();
		orderPay.getTermsConditionsCheckbox().click();
		Thread.sleep(3000);
		orderPay.getOrderBtn().click();
		ExtentManager.pass("Have successfully ordered");
		

	}

}
