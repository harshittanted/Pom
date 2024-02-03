package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.pages.AccountsPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;


@Epic("Epic - 101")
@Story("Story - 101-1")
public class LoginPageTest extends BaseTest {

	//1.Somehow you should call reference variable of Page Class in
	//Test Class
	
	//2.Due to protected LoginPage loginpage in BaseClass we
	//can use its method here
	
	@Description("Login Page Title Test")	
	@Test(priority = 1)
	public void loginPageTitleTest()
	{
		String actTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actTitle,AppConstants.LOGIN_PAGE_TITLE);
		
	}
	
	@Description("Login Page Url Test")
	@Test(priority = 2)
	public void loginPageUrlTest()
	{
		String actUrl = loginPage.getLoginPageUrl();
		Assert.assertTrue(actUrl.contains(AppConstants.LOGIN_PAGE_URL_FRACTION));
		
	}
	
	@Description("Forgot Password Link Test")
	@Test(priority = 3)
	public void isForgotPwdLinkExistTest()
	{
		
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
		
	}
	
	@Description("Check Logout link after logging in")
	@Test(priority = 4)
	public void logoutLinkTest()
	{
		
		accPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		Assert.assertEquals(accPage.isLogoutLinkExist(),true);
		
	}
	
	
	
}
