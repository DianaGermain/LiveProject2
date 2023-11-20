package com.automation.app;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestListener;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BasePage;
import base.ExtentManager;
import base.Hooks;
import pageObjects.Homepage;
import pageObjects.ShopContentPanel;
import pageObjects.ShopHomepage;
import pageObjects.ShopProductPage;
import pageObjects.ShoppingCart;

@Listeners(resources.Listeners.class)  //annotation to use ITestListener
public class AddRemoveItemBasketTest extends Hooks {

	public AddRemoveItemBasketTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Test
	public void addRemoveItem() throws InterruptedException, IOException {
		
		ExtentManager.log("Starting AddRemoveItemBasketTest...");
		
		Homepage home = new Homepage(); // now we can interact with elements from the homepage

		// handles cookie prompt
		home.getCookie().click();

		home.getTestStoreLink().click();
		ShopHomepage shopHome = new ShopHomepage();
		ExtentManager.pass("Reached the ShopHomePage");
		
		shopHome.getProdOne().click();

		ShopProductPage shopProd = new ShopProductPage();
		ExtentManager.pass("Reached the ShopProductPage");
		
		
		Select option = new Select(shopProd.getSizeOption());
		option.selectByVisibleText("M");
		ExtentManager.pass("Have successfully selected product size");
		Thread.sleep(3000);
		shopProd.getQuantIncrease().click();
		ExtentManager.pass("Have successfully increased quantity");
		Thread.sleep(3000);
		shopProd.getAddToCartBtn().click();
		ExtentManager.pass("Successfully added product to basket");
		Thread.sleep(3000);
		
		ShopContentPanel cPanel = new ShopContentPanel();
		Thread.sleep(3000);
		cPanel.getContinueShopBtn().click();
		Thread.sleep(3000);
		shopProd.getHomepageLink().click();
		Thread.sleep(3000);
		ExtentManager.pass("Successfully navigated to home page");
		shopHome.getProdTwo().click();
		Thread.sleep(3000);
		shopProd.getAddToCartBtn().click();
		Thread.sleep(3000);
		ExtentManager.pass("Successfully added to cart");
		cPanel.getcheckoutBtn().click();
		
		ShoppingCart cart = new ShoppingCart();
		cart.getDeleteItemTwo().click();
		
		//wait before assertion
		waitForElementInvisible(cart.getDeleteItemTwo(), 10);
		
		System.out.println(cart.getTotalAmount().getText());
		
		try {
		Assert.assertEquals(cart.getTotalAmount().getText(), "$45.23");
		ExtentManager.pass("Total amount matches the expected amount");
		} catch(AssertionError e) {
			Assert.fail("Cart amount did not match the expected amount, it found " + cart.getTotalAmount().getText() + " expected " + "$45.23");
			ExtentManager.fail("Total Amount did not match the expected amount");
		} 
	}

}
