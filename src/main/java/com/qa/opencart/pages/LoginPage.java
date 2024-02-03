package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
	//1.This is null currently
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//2.private By locators - page locators
	
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password11");
	private By registerLink = By.linkText("Register");
	
	//3.public Parameterized Constructor
	//We will pass webdriver reference variable to call methods.
	//Hence public and assign this to instance variable
	
	public LoginPage(WebDriver driver)
	{
		//Initializing instance variable
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	//4.Methods
	@Step("Get Login Page Title")
	public String getLoginPageTitle()
	{
		String pageTitle = eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE,AppConstants.SHORT_TIME_OUT);
		System.out.println("Title of Login Page is "+pageTitle);
		return pageTitle;
	}
	
	@Step("Get Login Page Url")
	public String getLoginPageUrl()
	{
		String url = eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION,AppConstants.SHORT_TIME_OUT);
		System.out.println("Login Page url is "+url);
		return url;
	}
	
	@Step("Check if forgot Password Link Exist")
	public Boolean isForgotPwdLinkExist()
	{
		return eleUtil.waitForElementVisible(forgotPwdLink,AppConstants.MEDIUM_TIME_OUT).isDisplayed();
		
	}
	
	@Step("Login with credentials {0} and {1} and Navigate to accounts page ")
	public AccountsPage doLogin(String usname , String pwd)
	{
		System.out.println("App Credentials are :"+usname+" and :"+pwd);
		eleUtil.waitForElementVisible(emailId,AppConstants.SHORT_TIME_OUT).sendKeys(usname);
		eleUtil.getElement(password).sendKeys(pwd);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);
		
	}
	
	@Step("Navigate To Regsiteration Page")
	public RegisterPage navigateToRegisterPage()
	{
		
		eleUtil.waitForElementVisible(registerLink,AppConstants.SHORT_TIME_OUT).click();
		return new RegisterPage(driver);
		
	}
	
	
	
	
	
	
	
	
	
	

}
