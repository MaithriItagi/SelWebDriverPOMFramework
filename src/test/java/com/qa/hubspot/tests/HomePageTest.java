package com.qa.hubspot.tests;

import static org.testng.Assert.assertEquals;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.base.BaseTest;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.utils.Constants;

public class HomePageTest extends BaseTest{
	
	HomePage homePage;
	
	@BeforeClass
	public void homeSetup() {
		homePage=loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	@Test(priority=3)
	public void verfiyHomePageTitle() {
		String title = homePage.getHomePageTitle();
		Assert.assertEquals(title, Constants.HOME_PAGE_TITLE,"Home page Title mismatch");
	}
	
	@Test(priority=1)
	public void verifyHomePageHeader() {
		
		String header= homePage.getHomePageHeader();
		Assert.assertEquals(header, Constants.HOME_PAGE_HEADER,"Home page header mismatch");
		
	}
	
	@Test(priority=2)
	public void verfiyLoggedInUser() {
		String loggedInUser= homePage.getLoggedInUser();
		Assert.assertEquals(loggedInUser, prop.getProperty("accountname")," Logged In User Mismatch");
		
	}
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	
	

}
