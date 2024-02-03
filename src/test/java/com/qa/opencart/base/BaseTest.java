package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResultsPage;

public class BaseTest {

	WebDriver driver;
	protected Properties prop;
	
	//If not protected then when it is extended we wont be able to use loginPage
	protected LoginPage loginPage;
	protected AccountsPage accPage;
	protected SearchResultsPage searchResPage;
	protected ProductInfoPage productInfoPage;
	protected RegisterPage regPage;	
	DriverFactory df;
	
	protected SoftAssert softAssert;
	
	@BeforeTest
	public void setup()
	{
		softAssert = new SoftAssert();
		df = new DriverFactory();
		prop = df.initProp();
		//If we dont assign driver then we will get null pointer exception because the driver is passed in loginPage
		//The below one is example of call by reference where we are passing reference of properties in initDriver method
		driver = df.initDriver(prop);
		//If below is not written then it will be loginPage ref variable will be treated null hence it is important to initialise it
		loginPage = new LoginPage(driver);
		
		
	}
		
	
	@AfterTest
	public void teardown()
	{
		driver.quit();
	}

}
