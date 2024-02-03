package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.pages.AccountsPage;

public class T1  {
	
	
	@Test(priority = 1)
	public void a()
	{
		System.out.println("1");
		
	}
	

	@Test(priority = -1)
	public void b()
	{
		System.out.println("-1");
		
	}
	
	
}
