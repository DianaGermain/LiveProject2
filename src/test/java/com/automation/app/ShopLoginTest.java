package com.automation.app;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import pageObjects.ShopLoginPage;
import pageObjects.ShopProductPage;
import pageObjects.ShopYourAccount;
import pageObjects.ShoppingCart;

@Listeners(resources.Listeners.class) // annotation to use ITestListener
public class ShopLoginTest extends Hooks {

	public ShopLoginTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Test
	public void addRemoveItem() throws InterruptedException, IOException {

		ExtentManager.log("Starting ShopLoginTest...");

		Homepage home = new Homepage(); // now we can interact with elements from the homepage

		// handles cookie prompt
		home.getCookie().click();

		home.getTestStoreLink().click();
		ShopHomepage shopHome = new ShopHomepage();
		shopHome.getLoginBtn().click();
		
		FileInputStream workbookLocation = new FileInputStream(System.getProperty("user.dir") + ("\\src\\main\\java\\resources\\credentials.xlsx") );
		XSSFWorkbook workbook = new XSSFWorkbook(workbookLocation);
		XSSFSheet sheet = workbook.getSheetAt(0);
		
		Row row1 = sheet.getRow(1);
		Cell R1C0 = row1.getCell(0);
		Cell R1C1 = row1.getCell(1);
		
		String emailRow1 = R1C0.toString();
		String passwordRow1 = R1C1.toString();
		
		System.out.println(emailRow1);
		System.out.println(passwordRow1);
		
		ShopLoginPage shop = new ShopLoginPage();
		shop.getEmail().sendKeys(emailRow1);
		shop.getPassword().sendKeys(passwordRow1);
		shop.getSubmitBtn().click();
		
		ShopYourAccount yourAcc = new ShopYourAccount();
		
		try {
		yourAcc.getsignOut().click();
		ExtentManager.pass("User has signed in");
		}catch (Exception e) {
			ExtentManager.fail("User did not sign in");
			Assert.fail();
		}
		
		Row row2 = sheet.getRow(2);
		Cell cellR2C0 = row2.getCell(0);
		Cell cellR2C1 = row2.getCell(1);
		
		String emailRow2 = cellR2C0.toString();
		String passwordRow2 = cellR2C1.toString();
		
		shop.getEmail().sendKeys(emailRow2);
		shop.getPassword().sendKeys(passwordRow2);
		Thread.sleep(3000);
		shop.getSubmitBtn().click();
		
		try {
		yourAcc.getsignOut().click();
		ExtentManager.pass("User has signed in");
		}catch (Exception e) {
			ExtentManager.fail("User did not sign in");
			Assert.fail();
		}
	}

}
