package pageObjects;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.BasePage;

public class ShopContentPanel extends BasePage {
	
	public WebDriver driver;
	
	By continueShoppingButton = By.xpath("//button[contains(text(),'Continue')]"); //custom locator
	By checkoutBtn = By.cssSelector(".cart-content-btn .btn-primary");
	
	public ShopContentPanel() throws IOException {
		super();
	}
	
public WebElement getContinueShopBtn() throws IOException{
	this.driver = getDriver();
	return driver.findElement(continueShoppingButton);
}

public WebElement getcheckoutBtn() throws IOException{
	this.driver = getDriver();
	return driver.findElement(checkoutBtn);
}
}
