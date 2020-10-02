package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.base.BaseTest;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ElementUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
@Epic ("Epic-101:design testcase to check login page features")
@Story ("US-102: design testcase to check login page title, signup link and successful login")
public class LoginPageTest extends BaseTest{
	
	
	
	@Test(priority = 2)
	@Description("Verify the Login page title")
	@Severity(SeverityLevel.NORMAL)
	public void verifyLoginPageTitleTest() {
		String title = loginPage.getLoginPageTitle();
		System.out.println("login page title is: " + title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE, "Login page title mismatch...");
	}

	@Test(priority = 1 )
	public void verifySignUpLinkTest() {
		Assert.assertTrue(loginPage.verifySignUpLink(), "Sign up link is missing....");
	}
	
	//@Test(priority=3)
	public void doLoginTest() {
		loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
}
