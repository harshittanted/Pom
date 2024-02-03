package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountsPageTest extends BaseTest {
	
	//1.We need to login to move to accounts section
	//2.The @BeforeTest annotation pointing to Setup Method in BaseTest
	//is only opening the login url
	//3.to login we will have to pass @BeforeClass here
	
	@BeforeClass
	public void accPageSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}

	@Test
	public void accPageTitleTest() {
		String actAccPageTitle = accPage.getAccPageTitle();
		Assert.assertEquals(actAccPageTitle, AppConstants.ACCOUNTS_PAGE_TITLE);
	}

	@Test
	public void logoutLinkExistTest() throws InterruptedException {
		Assert.assertTrue(accPage.isLogoutLinkExist());
		Thread.sleep(3000);
	}
	
	@Test
	public void accPageHeadersCountTest() throws InterruptedException {
		int actAccPageHeadersCount = accPage.getAccountsPageHeaderCount();
		System.out.println("Actual Acc Page Headers count = " + actAccPageHeadersCount);
		Assert.assertEquals(actAccPageHeadersCount, AppConstants.ACCOUNTS_PAGE_HEADERS_COUNT);
		Thread.sleep(3000);
	}
	
	
	@Test
	public void accPageHeadersTest() throws InterruptedException {
		List<String> actAccPageHeadersList = accPage.getAccountsPageHeader();
		Assert.assertEquals(actAccPageHeadersList, AppConstants.EXPECTED_ACC_PAGE_HEADERS_LIST);
		Thread.sleep(3000);
	}
	
	@DataProvider
	public Object[][] getSearchKey()
	{
		return new Object[][]
		{
			{"macbook", 3},
			{"imac", 1},
			{"samsung", 2}	
		};
	}
		
	@Test(dataProvider = "getSearchKey")
	public void searchTest(String searchText,int productCount) throws InterruptedException {
		
		searchResPage = accPage.doSearch(searchText);
		int actResultsCount = searchResPage.getSearchResultsCount();
		Assert.assertEquals(actResultsCount,productCount);
		Thread.sleep(3000);
	}
}
